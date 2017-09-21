Ext.define('App.view.Module.About.PtjjPanel', {
    extend: 'Ext.panel.Panel',

    closable: true,
    id: 'ptjjPanel',
    title: '平台简介',
    layout: 'fit',
    autoScroll: true,

    dockedItems: [
	      {
	          xtype: 'toolbar',
	          dock: 'top',
	          items: [
	              
	          ]
	      }
	  ],

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                
            ]
        });

        me.callParent(arguments);
    }

});
