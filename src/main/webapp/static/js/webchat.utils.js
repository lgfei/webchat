/**
 * 各种工具类
 */
(function () {
	if (typeof webchat === "undefined") {
		webchat = {};
	}
	if (!webchat.util) {
		webchat.util = {};
	}
	/**
	 * 表单操作工具类
	 */
	(function ($) {
		webchat.util.form = function(){
			var formUtil = this;
		    // 获取表单参数
		    formUtil.getParam = function(formId,formData){
		        if(formData == null){
		        	formData = {};
		        }
		        var arr = $('#'+formId).serializeArray();
		        $.each(arr, function() {
		        	formData[this.name] = this.value;
		        });
		        
		        return formData;
		    };
		};
	})(jQuery);
	/**
	 * 页面操作工具类
	 */
	(function ($) {
		webchat.util.page = function(){
			var pageUtil = this;
		    // 页面地址参数
			pageUtil.getUrlParam = function(name){
			   var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			   var r = window.location.search.substr(1).match(reg);
			   if(r != null){
				   return unescape(r[2]);
			   }
			   return null;
		    };
		};
	})(jQuery);
})();

/**
 * 日期格式化
 */
Date.prototype.format = function (fmt) {
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