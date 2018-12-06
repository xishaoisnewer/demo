$(document).ready(function(){

	initRecordTable();
});

function initRecordTable(){
	return $('#recordTable').DataTable({
		iDisplayLength: 10,// 每页显示行数
        "processing": true,
        "serverSide": true,
        "paging": true,
        "searching": false,
        "ordering": false, 
        "info": true,
        "autoWidth": true,
        "retrieve":true,
        "destroy":true,
        "ajax": {
            "url":path+"quartz/getQuartzRecordList",
            "type":"POST",
            "data": function (aoData) {
                var param = {};
                param.draw = aoData.draw;
                param.start = aoData.start;
                param.length = aoData.length;

                var searchForm = getFormJson("#quartzRecordForm");
                param.jobName = searchForm.jobName;
                param.triggerName = searchForm.triggerName;

                return param;
            }
        },
        "columns": [
        	{
                "data": null,
                "render": function(data, type, row, meta) {
                    return meta.row + 1;
                }
            },
            { "data": "status" },
            { "data": "jobName" },
            { "data": "triggerName" },
            { "data": "runType" },
            { "data": "runDesc" },
            { "data": "runStartTime" },
            { "data": "runEndTime" },
            { "data": "runTime" },
            { "data": "operator" }
        ]
    });
    
}

function getQuartzRecord(){
	reloadQuartzRecord();
}

/**
 * 刷新table
 */
function reloadQuartzRecord(){
	$("#recordTable").dataTable().fnDraw(false);
}