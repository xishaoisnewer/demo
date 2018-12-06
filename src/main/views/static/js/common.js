(function(window, $) {
    $.fn.serializeJson = function() {
        var serializeObj = {};
        var array = this.serializeArray();
        $(array).each(
            function() {
                if (serializeObj[this.name]) {
                    if ($.isArray(serializeObj[this.name])) {
                        serializeObj[this.name].push(this.value);
                    } else {
                        serializeObj[this.name] = [
                            serializeObj[this.name], this.value ];
                    }
                } else {
                    serializeObj[this.name] = this.value;
                }
            });
        return serializeObj;
    };
})(window, jQuery);

$(".checkbox-toggle").click(function () {
    var clicks = $(this).data('clicks');
    if (clicks) {
        //Uncheck all checkboxes
        $("#dataTable input[type='checkbox']").iCheck("uncheck");
        $(".fa", this).removeClass("fa-check-square-o").addClass('fa-square-o');
    } else {
        //Check all checkboxes
        $("#dataTable input[type='checkbox']").iCheck("check");
        $(".fa", this).removeClass("fa-square-o").addClass('fa-check-square-o');
    }
    $(this).data("clicks", !clicks);
});


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

/**
 * 日期格式化
 * @param fmt
 * @returns {*}
 * @constructor
 */
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

var contextPath = $("#contextPath").val();
var path= $("#Path").val();

<!-- ================== begin dataTables JS ================== -->
$.fn.dataTable.defaults.dom = "<'row'<'col-sm-12'tr>>\n\t\t\t<'row'<'col-sm-12 col-md-3'i><'col-sm-12 col-md-1 dataTables_pager'l><'col-sm-12 col-md-8 'p>>";
$.fn.dataTable.defaults.pagingType = "full_numbers";
$.fn.dataTable.defaults.language = {
    sProcessing: "处理中...",
    sLengthMenu: "每页显示 _MENU_ 条记录",
    sZeroRecords: "没有匹配结果",
    sInfo: "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
    sInfoEmpty: "从 0 到 0 /共 0 条数据",
    sInfoFiltered: "(由 _MAX_ 项结果过滤)",
    sSearch: "搜索:",
    sEmptyTable: "表中数据为空",
    sLoadingRecrods: "载入中...",
    paginate: {
        first: '<<',
        previous: '<',
        next: '>',
        last: '>>'
    },
    aria: {
        paginate: {
            first: 'First',
            previous: 'Previous',
            next: 'Next',
            last: 'Last'
        },
        decimal: '',
        sThousands: ',',
        sUrl: '',
        infoPostFix: '',
        thousands: ','
    }
};
$.fn.dataTable.defaults.serverSide = true;//开启服务器模式，使用服务器端处理配置datatable
$.fn.dataTable.defaults.bFilter = false;// 过滤栏
$.fn.dataTable.defaults.searching = false;//搜索功能
$.fn.dataTable.defaults.bSort = false;//是否启动各个字段的排序功能
$.fn.dataTable.defaults.stateSave = false;//cookie状态记录功能，翻页或者改变了每页显示数量会访问上一次关闭页面时的内容
$.fn.dataTable.defaults.lengthChange = true;// 每行显示记录数
$.fn.dataTable.defaults.iDisplayLength = 25;// 每页显示行数
$.fn.dataTable.defaults.aLengthMenu = [10, 25, 50, 100, 200];//更改显示记录数选项
$.fn.dataTable.defaults.autoWidth = true;//自动列宽计算
$.fn.dataTable.defaults.bDestory = true;
$.fn.dataTable.defaults.rocessing = false;//进度条
<!-- ================== end dataTables JS ================== -->