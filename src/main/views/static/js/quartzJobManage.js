$(function () {
	
	$('#JobTable').DataTable({
		serverSide: false,
		iDisplayLength: 10,// 每页显示行数
		"paging": true,
        "searching": false,
        "ordering": false, 
        "info": true,
        "autoWidth": false,
        "entries": false
	});
	
	// time控件
	$(".timepicker").timepicker({
		defaultTime: 'current',
	    minuteStep: 10,
	    disableFocus: true,
	    template: 'dropdown',
		defaultTime:'',
		showMeridian:false
	});
});

//添加Job
function subm() {
	if($("#jobName").val() == ""){
		alert("请输入任务名");
		return;
	}
	if($("#jobClassPath").val() == "" || $("#jobClassPath").val() =="--请选择任务类路径--"){
		alert("请输入任务类路径");
		return;
	}
	if($("#jobClass").val() == "" || $("#jobClass").val() =="--请选择任务类--"){
		alert("请输入任务类");
		return;
	}
	if($("#jobDescription").val() == ""){
		alert("请输入任务描述");
		return;
	}
	var arguments = $('#arguments').val();
	if(arguments != "" && !isJsonFormat(arguments)){
		alert("参数不符合格式！");
		return;
	}
	$.ajax({
		type : "POST",
		dataType : "json",//服务器返回的数据类型
		url : path+"quartz/createJob",
		data : $('#saveForm').serialize(),

		success : function(result) {
			if (result) {
				alert("添加成功");
				$("#saveForm").get(0).reset();
				var url = path+"quartz";
				window.location.href = url;
			}else{
				alert("添加失败！");
			}
		},
		error : function() {
			alert("异常！");
		}
	});
	
}


//弹出修改Job框
function openUpdateModal(){
	var check = $("input[name='selectJob']:checked");
	if(check.length != 1){
		alert("请选择一条任务修改");
		return;
	}
	
	var jobName = "";
	var jobClassPath = $("#updateJobClassPath").val();
	check.each(function(){
		jobName = $(this).closest("tr").find("[name='jobName']").text();
		var jobDescription = $(this).closest("tr").find("[name='jobDescription']").text();
		var classN = $(this).closest("tr").find("[name='className']").text().split('.');
		var className = classN[classN.length-1];
		var arguments = $(this).closest("tr").find("[name='arguments']").text();
		$("#updateJobName").val(jobName);
//		$("#updateJobClass").val(className);
//		$("#updateJobDescription").val(jobDescription);
//		$("#updateArguments").val(arguments);
	});
	jobName = $("#updateJobName").val();
	$.ajax({
		type:"POST",
		dataType:"json",
		url:path+"quartz/searchJobByJobName",
		//contentType:"application/json",
		data: {jobName:jobName,jobClassPath:jobClassPath},
		success : function (result) {
			if(result.jobDetail !=null){
				$("#updateJobModal").modal('show');
				$("#updateForm").get(0).reset();
				$("#updateJobName").val(jobName);
				$("#updateJobDescription").val(result.jobDetail.jobDescription);
				$("#updateArguments").val(result.jobDetail.arguments);
				$.each(result.jobClassNameList, function(index, item) {
					$("#updateJobClass").append("<option value="+item+">" + item+ "</option>");
				});
				$("#updateJobClass").val(result.jobDetail.className);
			}else{
				alert("未查询到该Job信息，请刷新页面！");
			}
        },
        error : function() {
            alert("查询Job异常！");
        }
	});
	
}

//修改Job
function updateJob(){
	if($("#updateJobName").val() == ""){
		alert("请输入任务名");
		return;
	}
	if($("#updateJobClassPath").val() == "" || $("#updateJobClassPath").val() =="--请选择任务类路径--"){
		alert("请输入任务类路径");
		return;
	}
	if($("#updateJobClass").val() == "" || $("#jobClass").val() =="--请选择任务类--"){
		alert("请输入任务类");
		return;
	}
	if($("#updateJobDescription").val() == ""){
		alert("请输入任务描述");
		return;
	}
	var arguments = $('#updateArguments').val();
	if(arguments != "" && !isJsonFormat(arguments)){
		alert("参数不符合格式！");
		return;
	}
	$.ajax({
		type : "POST",
		dataType : "json",//服务器返回的数据类型
		url : path+"quartz/updateJob",
		data : $('#updateForm').serialize(),

		success : function(result) {
			if (result) {
				alert("修改成功");
				$("#updateForm").get(0).reset();
				var url = path+"quartz";
				window.location.href = url;
			}else{
				alert("修改失败！");
			}
		},
		error : function() {
			alert("异常！");
		}
	});
}


//删除Job
function deleteJobs(){
	var selectedData = [];
	var check = $("input[name='selectJob']:checked");   //选中的复选框
	if(check.length == 0){
		alert("请选择要删除的任务");
		return;
	}
	var msg = "确定删除该任务？";
    if (confirm(msg)==false){
        return;
    }
	check.each(function(){
		selectedData.push({
			jobName:$(this).closest("tr").find("[name='jobName']").text()
		});
	});
	$.ajax({
		type:"POST",
		dataType:"json",//服务器返回的数据类型
		url:path+"quartz/deleteJob",
		contentType:"application/json",
		data:JSON.stringify(selectedData),
		success:function(result){
			if(result){
				alert("删除成功");
				var url = path+"quartz";
				window.location.href = url;
				
			}else{
				alert("失败");
			}
		},
		error:function(){
			alert("删除任务异常！");
		}
	});
}


//执行Job任务
function executeJob(){
	var selectedData = [];
	var check = $("input[name='selectJob']:checked");   //选中的复选框
	if(check.length == 0){
		alert("请选择要执行的任务");
		return;
	}
	var msg = "确定要执行该任务？";
    if (confirm(msg)==false){
        return;
    }
	check.each(function(){
		selectedData.push({
			jobName:$(this).closest("tr").find("[name='jobName']").text()
		});
	});
	$.ajax({
		type:"POST",
		dataType:"json",//服务器返回的数据类型
		url:path+"quartz/executeJob",
		contentType:"application/json",
		data:JSON.stringify(selectedData),
		success:function(result){
			if(result){
				alert("任务执行成功！");
			}else{
				alert("任务执行失败！");
			}
		},
		error:function(){
			alert("任务执行异常！");
		}
	});
}


//按任务名称搜索Job
function searchJobByJobName(){
	var jobname = $("#searchJobName").val();
	alert(jobname);
	if(jobname == ""){
		alert("请输入要查询的任务名称");
		return;
	}
	$.ajax({
		type:"POST",
		dataType:"json",
		url:path+"quartz/searchJobByJobName",
		//contentType:"application/json",
		data: "jobName="+jobname,
		success : function (result) {
			reloadJobTable(result);
        },
        error : function() {
            alert("查询Job异常！");
        }
	});
}


function reloadJobTable(result){
	$('#JobTable').dataTable().fnClearTable();    //清空表格
    $('#JobTable').dataTable().fnAddData(packagingdatatabledata(result),true);
}

function packagingdatatabledata(data) {
	var a = []; //全部行数据的list
	
	var banddata = data.jobResponseTempDtos;
	var caozuo = "操作";
	var index = 1;
	for ( var key in banddata) {
		var tempObj = []; //一行数据的list
		var check = '<input class="checkbox" type="checkbox" name="selectJob" th:text='+banddata[key].jobName+'/>';
		tempObj.push(check);
		tempObj.push(index);
		tempObj.push(banddata[key].jobName);
		tempObj.push(banddata[key].cronExpression);
		tempObj.push(banddata[key].jobDescription);
		tempObj.push(banddata[key].jobStatus);
		tempObj.push(banddata[key].className);
		tempObj.push(banddata[key].arguments);
		tempObj.push(caozuo);
		a.push(tempObj);
		index = index + 1;
	}

	return a;
}


$(function () {
	$("#openTrigger").on("hidden.bs.modal", function() {
//	    $(".modal-content").children().remove();
//	    $("#openRecord").removeData("bs.modal");
	});
	$("#openRecord").on("hidden.bs.modal", function() {
	    $(".modal-content").children().remove();
//	    $("#openTrigger").removeData("bs.modal");
	});
});

function isJsonFormat(str) {  
    try {  
        $.parseJSON(str);  
    } catch (e) {  
        return false;  
    }  
    return true;  
}  

function selectAll(checkbox) {
    $('input[type=checkbox]').prop('checked', $(checkbox).prop('checked'));
}

function checkField(JobClassPath){
	if(JobClassPath == ''){
		$("#jobClass").empty();  
		$("#jobClass").prepend("<option value='0'>--请选择任务类--</option>"); 
		return;
	}
	$.ajax({
		type:"POST",
		dataType:"json",
		url:path+"quartz/searchAllJobClassName",
		data: "JobClassPath="+JobClassPath,
		success : function (result) {
			var sel = document.getElementById("jobClass");
			var sel2 = document.getElementById("updateJobClass");
			for(var i=0;i<result.length;i++){
				var className = result[i];
				var option = new Option(className, className);  
				sel.options.add(option);
//					sel2.options.add(option);
			}
        },
        error : function() {
            alert("查询改路径下类名异常！");
        }
	});
}

function resetJobForm(){
	document.getElementById("jobForm").reset();
	$("#searchJobName").val(null);
	$("#beginTime").val(null);
	$("#endTime").val(null);
}

