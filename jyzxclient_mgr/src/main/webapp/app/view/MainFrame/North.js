Ext.define('App.view.MainFrame.North', {
    extend: 'Ext.Component',
	
	region: 'north',
	height: 35,

	initData: function (me) {
		/*
	    jQuery.ajax({
	        type: "POST",
	        url: datapath + 'Ajax.aspx?op=Common&om=QueryLoginInfo&_rnd=' + Math.random(),
	        dataType: "text",
	        cache: false,
	        async: false,
	        data: {},
	        success: function (response) {
	            var json = eval("(" + response + ")");
	            //Ext.IceFox.Msg.show("系统消息", json.msg);
	            if (json.result) {
	                logininfo = json.data
	                me.update(logininfo);
	            }
	        },
	        failure: function (resp) {

	        }
	    });
		*/
		me.update({
			employee: {
				Xm: '管理员'
			},
			company: {
				Gsmc: '哇哩哇哩哇'
			}
		});
	},

	initComponent: function () {
	    var me = this;

	    me.tpl = new Ext.XTemplate(
            "<div style=\"float:right;color:#FFFFFF\">" +
                "<span>欢迎您 {employee.Xm}</span>" +
                "&nbsp;&nbsp;|&nbsp;&nbsp;" +
                "<span>公司: {company.Gsmc}</span>" +
                "&nbsp;&nbsp;|&nbsp;&nbsp;" +
                "<span class=\"icon285_16x16\"  style=\"padding-left:18px;cursor:pointer;\" onclick=\"esetup();\" >设置" + "</span>" +
                "&nbsp;&nbsp;|&nbsp;&nbsp;" +
                "<span class=\"icon401_16x16\" style=\"padding-left:18px;cursor:pointer;\" onclick=\"logout();\">退出系统" + "</span>" +
            "</div>"
        );

	    Ext.applyIf(me, {
	        listeners: {
	            'afterrender': function () {
	                me.initData(me);
	            }
	        }
	    });

	    me.callParent();
	}
});