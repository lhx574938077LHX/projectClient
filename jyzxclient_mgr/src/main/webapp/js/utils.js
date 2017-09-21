var Utils = {
    //*************************系统常用函数*********************************
    GetTypeValue: function (typearray, value) {
        var result = "";

        for (var i = 0; i < typearray.length; i++) {
            if (typearray[i].s_value == value) {
                result = typearray[i].s_name;
                return result;
            }
        }

        return result;
    },
    //*************************常用验证函数*********************************
    //验证是否是中国手机号码
    IsMobile: function (value) {
    	return (/^1[3|5|8|4|7]\d{9}$/).test(value.trim());
    },
    //验证是否是合法的18位身份证号码
    getIDChar18:function(id) {
	    var arr = id.split(''), 
	    sum = 0, 
	    vc = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2]; 
	    for (var i = 0; i < 17; i++) sum += vc[i] * parseInt(arr[i]);    
	    return ['1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'][sum % 11];   
	    },
	    ValidID:function (id) {    
       if (/^[1-6]\d{5}\d{4}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([\d|x|X]{1})$/.test(id)) {  
            var c = id.charAt(17), 
            rc = getIDChar18(id);  
                 if (c == rc) 
                   this.showRst('您输入的身份证号码正确！<br>生日：' + id.substr(6, 8) + '<br>性别：' + ['女', '男'][parseInt(id.charAt(16)) % 2]);  
                   else 
                   this.showRst('您输入的身份证号码检验码错误，第18位校验码应该为' + rc + '！');    
                    } 
                   else this.showRst('请输入正确的身份证号码！');  
        } ,
        
       showRst: function (msg) {
	    alert(msg);
      } ,           

    //验证是否是中文姓名
    IsChineseName: function (value) {
        return (/^([\u4e00-\u9fa5]){2,15}$/).test(value.trim());
    },

    IsBankCardNum: function (value) {
        return (/^\d{19}$/).test(value.trim());
    },

    //验证字符串是否是二代18位身份证
    IsIdcard: function (value) {
        if (this.GetStringTrueLength(value) != 18)
        {
            return false;
        }

        var jqyz = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
        var vcode = [ '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' ];

        var jssum = 0;
        for (var i = 0; i < this.GetStringTrueLength(value) - 1; i++)
        {
            jssum += new Number(value[i]) * jqyz[i];
        }

        return (value[17]) == (vcode[jssum % 11]);
    },

    IsTel: function (value) {
        //"兼容格式: 国家代码(2到3位)-区号(2到3位)-电话号码(7到8位)-分机号(3位)"
        //return (/^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/.test(value.Trim()));
        return (/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/.test(value.trim()));
    },

    IsZipCode: function (value) {
        return (/^([0-9]{6})$/).test(value.trim());
    },

    IsEmail: function (value) {
        return (/^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/).test(value.trim());
    },
    //*************************字符串函数*********************************
    ClearHTML: function (htmlstr) {
        var regex1 = /<style[^>]*>[\s\S\u0391-\uFFE5a]*?<\/[^>]*style>/g;
        var regex2 = /<script[^>]*>[\s\S\u0391-\uFFE5a]*?<\/[^>]*script>/g;
        var regex3 = /<!--([\s\S\u0391-\uFFE5a]*?)-->/g;
        var regex4 = /<!([\s\S\u0391-\uFFE5a^\/]*?)\/*>/g;
        var regex5 = /<(?:.|\s)*?>/g;

        htmlstr = htmlstr.replace(regex1, "");
        htmlstr = htmlstr.replace(regex2, "");
        htmlstr = htmlstr.replace(regex3, "");
        htmlstr = htmlstr.replace(regex4, "");
        htmlstr = htmlstr.replace(regex5, "");
        htmlstr = htmlstr.replace("   ", "");
        htmlstr = htmlstr.replace(" ", "");
        htmlstr = htmlstr.replace("\t", "");
        htmlstr = htmlstr.replace("\r", "");
        htmlstr = htmlstr.replace("\n", "");
        htmlstr = htmlstr.replace("<", "&lt;");
        htmlstr = htmlstr.replace(">", "&gt;");

        return htmlstr;
    },

    //首字母大写
    ChangeCase: function (tmpStr) {
        var index;
        var tmpChar;
        var preString;
        var postString;
        var strlen;
        strLen = tmpStr.length;
        if (strLen > 0) {
            for (index = 0; index < strLen; index++) {
                if (index == 0) {
                    tmpChar = tmpStr.substring(0, 1).toUpperCase();
                    postString = tmpStr.substring(1, strLen);
                    tmpStr = tmpChar + postString;
                }
                else {
                    tmpChar = tmpStr.substring(index, index + 1);
                    if (tmpChar == " " && index < (strLen - 1)) {
                        tmpChar = tmpStr.substring(index + 1, index + 2).toUpperCase();
                        preString = tmpStr.substring(0, index + 1);
                        postString = tmpStr.substring(index + 2, strLen);
                        tmpStr = preString + tmpChar + postString;
                    }
                }
            }
        }
        return tmpStr;
    },

    //取得字符串真实长度
    GetStringTrueLength: function (str) {
        return str.replace(/[^\x00-\xff]/g, "xx").length;
    },

    StringToDate: function (str) {
        if (typeof str == 'string') {
            var results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) *$/);

            if (results && results.length > 3)
                return new Date(parseInt(results[1]), parseInt(results[2]) - 1, parseInt(results[3]));

            results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) +(\d{1,2}):(\d{1,2}):(\d{1,2}) *$/);

            if (results && results.length > 6)
                return new Date(parseInt(results[1]), parseInt(results[2]) - 1, parseInt(results[3]), parseInt(results[4]), parseInt(results[5]), parseInt(results[6]));

            results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) +(\d{1,2}):(\d{1,2}):(\d{1,2})\.(\d{1,9}) *$/);

            if (results && results.length > 7)
                return new Date(parseInt(results[1]), parseInt(results[2]) - 1, parseInt(results[3]), parseInt(results[4]), parseInt(results[5]), parseInt(results[6]), parseInt(results[7]));
        }
    },

    //用于转换用JavaScriptSerializer的日期
    GetJSERTime: function (value) {
        return eval('new ' + (value.replace(/\//g, '')));
    }
};

Date.prototype.format = function (fmt) {
    //author: meizz 
    var o =
    {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};


Date.prototype.addDays = function(d)
{
    this.setDate(this.getDate() + d);
    return this;
};

Date.prototype.addWeeks = function(w)
{
    this.addDays(w * 7);
    return this;
};

Date.prototype.addMonths= function(m)
{
    var d = this.getDate();
    this.setMonth(this.getMonth() + m);

    if (this.getDate() < d)
        this.setDate(0);

    return this;
};

Date.prototype.addYears = function(y)
{
    var m = this.getMonth();
    this.setFullYear(this.getFullYear() + y);

    if (m < this.getMonth()) 
    {
        this.setDate(0);
    }

    return this;
};



/**   
*转换日期对象为日期字符串   
* @param date 日期对象   
* @param isFull 是否为完整的日期数据,   
*               为true时, 格式如"2000-03-05 01:05:04"   
*               为false时, 格式如 "2000-03-05"   
* @return 符合要求的日期字符串   
*/    
function getSmpFormatDate(date, isFull) {  
	var pattern = "";  
	if (isFull == true || isFull == undefined) {  
		pattern = "yyyy-MM-dd hh:mm:ss";  
	} else {  
		pattern = "yyyy-MM-dd";  
	}  
	return getFormatDate(date, pattern);  
}  
/**   
*转换当前日期对象为日期字符串   
* @param date 日期对象   
* @param isFull 是否为完整的日期数据,   
*               为true时, 格式如"2000-03-05 01:05:04"   
*               为false时, 格式如 "2000-03-05"   
* @return 符合要求的日期字符串   
*/    

function getSmpFormatNowDate(isFull) {  
	return getSmpFormatDate(new Date(), isFull);  
}  
/**   
*转换long值为日期字符串   
* @param l long值   
* @param isFull 是否为完整的日期数据,   
*               为true时, 格式如"2000-03-05 01:05:04"   
*               为false时, 格式如 "2000-03-05"   
* @return 符合要求的日期字符串   
*/    

function getSmpFormatDateByLong(l, isFull) {  
	return getSmpFormatDate(new Date(l), isFull);  
}  
/**   
*转换long值为日期字符串   
* @param l long值   
* @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss   
* @return 符合要求的日期字符串   
*/    

function getFormatDateByLong(l, pattern) {  
	return getFormatDate(new Date(l), pattern);  
}  
/**   
*转换日期对象为日期字符串   
* @param l long值   
* @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss   
* @return 符合要求的日期字符串   
*/    
function getFormatDate(date, pattern) {  
	if (date == undefined) {  
		date = new Date();  
	}  
	if (pattern == undefined) {  
		pattern = "yyyy-MM-dd hh:mm:ss";  
	}  
	return date.format(pattern);  
}  