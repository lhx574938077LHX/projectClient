var Common ={
		
}

function esetup() {
    var setupwin = Ext.create("App.view.Module.Common.Window.SetupWindow", {
        modal: true
    });

    setupwin.show();
}

function logout(){

    jQuery.ajax(
        {
            type : "POST",
            url : 'login/logOutJ.do', //表单提交页面
            dataType : "text",
            cache : false,
            async : false,
            success : function (response)
            {
            	window.location.href="login.do";
            },
            failure : function (resp)
            {
                Ext.toast(
                {
                    html : "与服务器连接失败",
                    closable : false,
                    align : 't',
                    slideInDuration : 400,
                    minWidth : 400
                }
                );
            }
        })
}