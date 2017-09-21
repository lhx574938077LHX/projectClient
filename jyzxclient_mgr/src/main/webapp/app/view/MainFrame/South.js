Ext.define('App.view.MainFrame.South', {
    extend: 'Ext.TabPanel',
	
	region: 'south',
	split: true,
		
    initComponent: function() {
        var me = this;
		
        Ext.applyIf(me, {

        });

        me.callParent(arguments);
    }
});