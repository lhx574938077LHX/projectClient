Ext.define('App.view.MainFrame.East', {
    extend: 'Ext.TabPanel',
	
	region: 'east',
	split: true,
		
    initComponent: function() {
        var me = this;
		
        Ext.applyIf(me, {

        });

        me.callParent(arguments);
    }
});