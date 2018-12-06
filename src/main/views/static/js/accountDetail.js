$(document).ready(function(){

    initRecordTable();
});

function initRecordTable(){
    var searchForm = $('#accountDetailForm').serializeJson();
    if(searchForm.startPaymentDate == "" && searchForm.endPaymentDate == ""){
        $('#startPaymentDate').val(getNowFormatDate());
        $('#endPaymentDate').val(getNowFormatDate());
    }
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
            "url":path+"accountDetail/list",
            "type":"POST",
            "data": function (aoData) {
                var param = {};
                searchForm = $('#accountDetailForm').serializeJson();

                param = searchForm;
                param.draw = aoData.draw;
                param.start = aoData.start;
                param.length = aoData.length;

                // param.startPaymentDate = searchForm.startPaymentDate;
                // param.endPaymentDate = searchForm.endPaymentDate;
                // param.startDueDate = searchForm.startDueDate;
                // param.endDueDate = searchForm.endDueDate;
                // param.assetChannel = searchForm.assetChannel;
                // param.salesChannel = searchForm.salesChannel;
                // param.repayType = searchForm.repayType;
                // param.line = searchForm.line;

                return param;
            }
        },
        "columns": [
            { "data": "assetChannel" },
            { "data": "repaymentType" },
            { "data": "line" },
            { "data": "salesChannel" },
            {
                "data": "pushDate",
                render: function(data, type, row){
                    return new Date(data).Format("yyyy-MM-dd hh:mm:ss");
                }
            },
            // {
            //     "data": "maturityDate",
            //     render: function(data, type, row){
            //         return new Date(data).Format("yyyy-MM-dd hh:mm:ss");
            //     }
            // },
            { "data": "amount" },
            { "data": "principleInterest" },
            { "data": "premium" },
            { "data": "preAmount" },
            { "data": "consumerFinance" }
        ]
    });

}

/**
 * 获取当前时间，格式YYYY-MM-dd
 * @returns {string}
 */
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
}

/**
 * 获得表单的JSON
 * @param frm
 * @returns
 */
var getFormJson = function (frm) {
    var o = {};
    var a = $(frm).serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

function getAccountDetail(){
    reloadAccountDetail();
}

/**
 * 刷新table
 */
function reloadAccountDetail(){
    $("#recordTable").dataTable().fnDraw(false);
}