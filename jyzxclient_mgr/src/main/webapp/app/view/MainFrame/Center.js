Ext.define('App.view.MainFrame.Center', {
    extend: 'Ext.TabPanel',
	
	region: 'center',
	id: "contentTabPanel",
	split: true,
	plugins: Ext.create("App.ux.TabCloseMenu", {}),
		
    initComponent: function() {
        var me = this;
		
        Ext.applyIf(me, {
			items: [{
				title: '欢迎',
				iconCls: 'icon544_16x16',
				closeable: true,
				autoScroll: false,
				layout: 'fit'
			}]
        });

        me.callParent(arguments);
    }

});