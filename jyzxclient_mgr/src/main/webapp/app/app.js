Ext.Loader.setConfig({
    enabled: true
});

Ext.onReady(function () {
    Ext.BLANK_IMAGE_URL = 'images/s.gif ';

    Ext.ClassManager.addNameAliasMappings({
    	//插件
        "App.ux.Portlet": ["widget.portlet"],
    	"App.ux.PortalPanel": ["widget.portalpanel"],
        "App.ux.PortalColumn": ["widget.portalcolumn"],
    	"App.ux.UEditor": ["widget.ueditor"],
    	
        "App.ux.UploadPanel": ["widget.uploadPanel"],
        "App.ux.PictureViewPanel": ["widget.pictureViewPanel"],
        "App.view.LoginWindow":["widget.loginWindow"],
        "App.ux.CommonUploadPanel":["widget.commonUploadPanel"],
        "App.ux.LPicBoxPanel":["widget.lpicboxpanel"],
        
        //导航树
        "App.view.Navigation.AboutNavTree":["widget.aboutNavTree"],
        "App.view.Navigation.HelpNavTree":["widget.helpNavTree"],
        "App.view.Navigation.AccountNavTree":["widget.accountNavTree"],
        "App.view.Navigation.TestManageNavTree":["widget.testManageNavTree"],
        
        //账户管理
	    "App.view.Module.Account.Window.AddAccountWindow":["widget.addAccountWindow"],
	    "App.view.Module.Account.Window.EditAccountWindow":["widget.editAccountWindow"],
	    "App.view.Module.Account.Window.CheckAccountWindow":["widget.checkAccountWindow"],
	    "App.view.Module.Account.Window.ShowSearchResultWindow":["widget.showSearchResultWindow"],
	    "App.view.Module.Account.Window.EditJoinConditionWindow":["widget.editJoinConditionWindow"],
    	"App.view.Module.Account.Window.ShowAccountWindow":["widget.showAccountWindow"],
    	
    	//测试管理
	    "App.view.Module.TestManage.Window.AddJdDataWindow":["widget.addJdDataWindow"],
    	"App.view.Module.TestManage.Window.EditJdDataWindow":["widget.editJdDataWindow"],
    	"App.view.Module.TestManage.Window.Send3001Window":["widget.send3001Window"],
    	"App.view.Module.TestManage.Window.Send3002Window":["widget.send3002Window"],
    	"App.view.Module.TestManage.Window.TestDatasWindow":["widget.testDatasWindow"]
    });

    /*
    if (Ext.isIE) {
        Ext.getBody().setStyle('position', 'relative');
    }
    */
});

Ext.application({
    name: 'App',
    autoCreateViewport: true,
    
    views: [
    ],

    controllers: [
        "NavController",
        "UIExtController"
    ],

    launch: function () {
        
    }
});
