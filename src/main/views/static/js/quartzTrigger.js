var jobName = $("#jobNameSelect").val();

//$(function () {
//	$('#myTab li:eq(1) a').tab('show');
//});

$(document).ready(function(){

    $('#triggerTable').DataTable({
    	"processing": true,
        "serverSide": true,
        "paging": false,
        "lengthChange": false,
        "searching": false,
        "ordering": false, 
        "info": false,
        "autoWidth": false,
        "retrieve":true,
        "destroy":true,
        "ajax": {
            "url":path+"quartz/getTriggerList",
            "type":"POST",
            "data": {  
                "jobName": jobName  
            }
        },
        "columns": [
        	{ 
        	  "data": "triggerName" ,
        	  "sClass": "text-left",
              "render": function (data, type, full, meta) {
                   return "<input type='checkbox' value="+data+" class='checkchild' name='checkTrigger'/>";
               },
               "bSortable": false
            },
            {
                "data": null,
                "render": function(data, type, row, meta) {
                    return meta.row + 1;
                }
            },
            { "data": "jobName" },
            { "data": "triggerName" },
            { "data": "cronExpression" },
            { "data": "triggerDescription" },
            { "data": "triggerStatus" }
        ]
    });
});


/**
 * 查询trigger
 */
//function queryTriggerList(jobName) {
//	$("#jobNameSelect").val(jobName);
//    $.ajax({
//        type: "POST",
//        dataType: "json",//服务器返回的数据类型
//        url: "/quartz/getTriggerList", //"+jobName ,
//        data: "jobName="+jobName,
//        success: function (result) {
//        	if (result.success == true) {
//        		reloadTrigger(result);
//            };
//        },
//        error : function() {
//            alert("查询trigger异常！");
//        }
//    });
//}

/**
 * 新增trigger 表单提交
 */
$("#triggerForm").submit(function(){
	var addOrUpdate = $("#addOrUpdate").val();
	if(addOrUpdate == 1){
		addTrigger();
	}else{
		updateTrigger();
	}
});

/**
 * 新增trigger
 */
function addTrigger() {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: path+"quartz/addTrigger" ,
        data: $('#triggerForm').serialize(),
        success: function (result) {
            if (result.success == true) {
            	$("#triggerForm").get(0).reset();
            	$('#cronCollapse').collapse('hide');
            	
            	reloadTrigger();
            }else{
            	alert("新增trigger失败，请检查！"+result.msg);
            }
        },
        error : function() {
            alert("新增trigger异常！");
        }
    });
}

/**
 * 修改trigger
 */
function updateTrigger() {
    $.ajax({
        type: "POST",
        dataType: "json",//服务器返回的数据类型
        url: path+"quartz/updateTrigger" ,
        data: $('#triggerForm').serialize(),
        success: function (result) {
        	if (result.success == true) {
            	$("#triggerForm").get(0).reset();
            	$('#cronCollapse').collapse('hide');
            	
            	reloadTrigger(result);
            }else{
            	alert("修改trigger失败，请检查！"+result.msg);
            }
        },
        error : function() {
            alert("修改trigger异常！");
        }
    });
}

/**
 * 删除trigger
 */
function deleteTrigger() {
	var selectedData = [];
	var check = $("input[name='checkTrigger']:checked");//选中的复选框  
	if (check.length == 0) {
		alert("请选择一条定时器进行删除");
		return;
	}
	var msg = "确定删除该定时器？";
    if (confirm(msg)==false){
        return;
    }
	var jobName = $("#jobNameSelect").val();
    check.each(function(){
    	var triggerName = this.value;
		selectedData.push({
			triggerName : triggerName,
			jobName : jobName
		});
    }); 
    $.ajax({
        type: "POST",
        dataType: "json",//服务器返回的数据类型
        url: path+"quartz/deleteTrigger" ,
        data: JSON.stringify(selectedData),
		contentType : "application/json",
        success: function (result) {
        	if (result.success == true) {
        		alert("删除trigger成功！");
        		reloadTrigger();
            }else{
            	alert("删除trigger失败！");
            }
        },
        error : function() {
            alert("删除trigger异常！");
        }
    });
}

function openAddCronCollapse(){
	//data-toggle="collapse" data-target="#cronCollapse"
	$("#triggerForm").get(0).reset();
	$('#cronCollapse').collapse('show');
	var jobName = $("#jobNameSelect").val();
	$("#jobName").val(jobName);
	
	$("#addOrUpdate").val(1);//新增
}

function closeAddCronCollapse(){
	//data-toggle="collapse" data-target="#cronCollapse"
	$("#triggerForm").get(0).reset();
	$('#cronCollapse').collapse('hide');
}

function openUpdateCronCollapse(){
	var check = $("input[name='checkTrigger']:checked");//选中的复选框  
	if (check.length != 1) {
		alert("请选择一条计划进行修改");
		return;
	}
	$("#triggerForm").get(0).reset();
	var jobName = $("#jobNameSelect").val();
    check.each(function(){
        var triggerName = this.value;
        var corn = $(this).closest("tr").find("td:eq(4)").text();
        var desc = $(this).closest("tr").find("td:eq(5)").text();
        $("#jobName").val(jobName);
        $("#triggerName").val(triggerName);
        $("#updateCron").val(corn);
        $("#updateDesc").val(desc);
    });
    $('#cronCollapse').collapse('show');
    $("#addOrUpdate").val(2);//更新
}

/**
 * 刷新table
 */
function reloadTrigger(){
	$("#triggerTable").dataTable().fnDraw(false);
}

function reloadTriggerTable(result){
	$('#triggerTable').dataTable().fnClearTable();    //清空表格
    $('#triggerTable').dataTable().fnAddData(packagingdatatabledata(result),true);
}

function packagingdatatabledata(data) {
	var a = []; //全部行数据的list
	var check = '<input class="checkbox" type="checkbox" name="checkTrigger"/>';
	var banddata = data.triggerInfoDtoList;
	var index = 1;
	for ( var key in banddata) {
		var tempObj = []; //一行数据的list
		tempObj.push(check);
		tempObj.push(index);
		tempObj.push(banddata[key].jobName);
		tempObj.push(banddata[key].triggerName);
		tempObj.push(banddata[key].cronExpression);
		tempObj.push(banddata[key].triggerDescription);
		tempObj.push(banddata[key].triggerStatus);
		a.push(tempObj);
		index = index + 1;
	}

	return a;
}

function selectAllTrigger(checkbox) {
    $('input[name=checkTrigger]').prop('checked', $(checkbox).prop('checked'));
}