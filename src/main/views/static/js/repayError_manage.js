$(function () {
    // 差错列表
    if ($('#data-table').length != 0) {
        var table = $('#data-table').DataTable({
            ajax: {
                url: Path + "/repay/getRepayErrorList",
                type: "POST",
                data: function (aoData) {
                    var param = {};
                    param.draw = aoData.draw;
                    param.start = aoData.start;
                    param.length = aoData.length;

                    getFormJson(param, "#searchForm");

                    return param;
                }
            },
            aoColumns: [
                {
                    mDataProp: "id",
                    sTitle: "编号",
                    sDefaultContent: "",//此列默认值为""，以防数据中没有此值，DataTables加载数据的时候报错
                    sWidth: "5%",
                    sClass: "text-center"
                },
                {
                    mDataProp: "phaseId",
                    sTitle: "还款计划编号",
                    sDefaultContent: "",//此列默认值为""，以防数据中没有此值，DataTables加载数据的时候报错
                    sWidth: "10%",
                    sClass: "text-center"
                },
                {
                    mDataProp: "contractNo",
                    sTitle: "合同编号",
                    sDefaultContent: "",
                    sWidth: "10%",
                    sClass: "text-center"
                },
                {
                    mDataProp: "withholdType",
                    sTitle: "扣款方式",
                    sDefaultContent: "",
                    sWidth: "8%",
                    sClass: "text-center"
                },
                {
                    mDataProp: "repayType",
                    sTitle: "还款类型",
                    sDefaultContent: "",
                    sWidth: "8%",
                    sClass: "text-center"
                },
                {
                    mDataProp: "dueDate",
                    sTitle: "应还日期",
                    sDefaultContent: "",
                    sWidth: "8%",
                    sClass: "text-center"
                },
                {
                    mDataProp: "amount",
                    sTitle: "金额",
                    sDefaultContent: "",
                    sWidth: "8%",
                    sClass: "text-center"
                },
                {
                    mDataProp: "principle",
                    sTitle: "实际本金",
                    sDefaultContent: "",
                    sWidth: "8%",
                    sClass: "text-center"
                },
                {
                    mDataProp: "interest",
                    sTitle: "实际利息",
                    sDefaultContent: "",
                    sWidth: "8%",
                    sClass: "text-center"
                },
                {
                    mDataProp: "penalty",
                    sTitle: "违约金",
                    sDefaultContent: "",
                    sWidth: "8%",
                    sClass: "text-center"
                },
                {
                    mDataProp: "errorType",
                    sTitle: "差错类型",
                    sDefaultContent: "",
                    sWidth: "8%",
                    sClass: "text-center"
                },
                {
                    mDataProp: "createTime",
                    sTitle: "创建时间",
                    sDefaultContent: "",
                    sWidth: "10%",
                    sClass: "text-center",
                    render: function (data, type, row, meta) {
                        return new Date(data).Format("yyyy-MM-dd hh:mm:ss"); //date的格式 Thu Apr 26 2016 00:00:00 GMT+0800
                    }
                }
            ]
        });
    }

    // 日期控件
    $(".datetimepicker").datetimepicker({
        language: 'zh-CN',
        showMeridian: 1,
        autoclose: true,//选择后自动关闭
        todayHighlight: true,//高亮当前日期
        startView: 'month',//打开后首先显示的视图
        format: 'yyyy-mm-dd',//显示格式  yyyy-mm-dd hh:ii:ss
        minView: 'month'//设置只显示到月份
    });

    //重置
    $("#searchForm").find(".reset_btn").click(function () {
        $("#searchForm").find("input").val("");
    });

    var type;
    var confirmStr;

    /*差错文件创建的开始/结束日期、合同号 查询 刷新*/
    $("#searchBtn").click(function () {
        var searchForm = getFormJson("#searchForm");
        if (checkInput(searchForm.startDate) && checkInput(searchForm.endDate)
            && searchForm.startDate == searchForm.endDate) {
            alert("差错起始日期和结束日期不能相同!");
            return;
        }
        $('#data-table').dataTable().fnDraw(false);
    });

    /*同步正常还款差错文件*/
    $("#syncNormalFile").click(function () {
        type = "1";
        confirmStr = "确定同步正常还款差错文件吗?";
        synsRepayErrorFile(type, confirmStr);
    });

    /*同步提前结清差错文件*/
    $("#syncRepaymentFile").click(function () {
        type = "2";
        confirmStr = "确定同步提前结清差错文件吗?";
        synsRepayErrorFile(type, confirmStr);
    });

    /*同步违约金差错文件*/
    $("#syncPenaltyFile").click(function () {
        type = "3";
        confirmStr = "确定同步违约金差错文件吗?";
        synsRepayErrorFile(type, confirmStr);
    });

    /*同步吉林正常还款文件*/
    $("#sendNormalFile").click(function () {
        type = "1";
        confirmStr = "确定补发正常还款全量文件吗?";
        sendRepayFile(type, confirmStr);
    });

    /*上传吉林提前结清文件*/
    $("#sendRepaymentFile").click(function () {
        type = "9";
        confirmStr = "确定补发正常提前结清全量文件吗?";
        sendRepayFile(type, confirmStr);
    });
});

/**
 * 拉取差错文件请求
 * @param type 文件类型
 * @param confirmStr 提交 message
 */
var synsRepayErrorFile = function (type, confirmStr) {
    if (!checkInput($("#syncDate").val())) {
        alert("请选择文件同步日期!");
        return;
    } else {
        if (confirm(confirmStr)) {
            $.ajax({
                dataType: 'json',
                url: Path + "/repay/syncRepayErrorFile",
                data: {
                    "syncDate": $("#syncDate").val(),
                    "type": type
                },
                type: 'POST',
                success: function (data) {
                    if (data.code = 1) {
                        alert(data.msg);
                    } else {
                        alert(data.msg);
                    }
                },
                error: function () {
                }
            });
        }
    }
};

/**
 * 同步还款文件请求
 * @param type 文件类型
 * @param confirmStr 提交 message
 */
var sendRepayFile = function (type, confirmStr) {
    if (!checkInput($("#syncDate").val())) {
        alert("请选择文件同步日期!");
        return;
    } else {
        if (confirm(confirmStr)) {
            $.ajax({
                dataType: 'json',
                url: Path + "/repay/sendRepayFile",
                data: {
                    "syncDate": $("#syncDate").val(),
                    "type": type
                },
                type: 'POST',
                success: function (data) {
                    if (data.code = 1) {
                        alert(data.msg);
                    } else {
                        alert(data.msg);
                    }
                },
                error: function () {
                }
            });
        }
    }
};

/**
 * 校验是否输入日期
 * @returns {boolean}
 */
function checkInput(val) {
    var result = true;
    if (val == null || val == "") {
        result = false;
    }
    return result;
}

/**
 * 获得表单的JSON
 * @param param
 * @param frm
 * @returns {*}
 */
var getFormJson = function (param, frm) {
    if (param == undefined) {
        param = {};
    }
    var a = $(frm).serializeArray();
    $.each(a, function () {
        if (param[this.name] !== undefined) {
            if (!param[this.name].push) {
                param[this.name] = [param[this.name]];
            }
            param[this.name].push(this.value || '');
        } else {
            param[this.name] = this.value || '';
        }
    });
    return param;
};
