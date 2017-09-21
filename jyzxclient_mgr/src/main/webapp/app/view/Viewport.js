Ext.define('App.view.Viewport', {
    extend: 'Ext.container.Viewport',
    layout: {
        type: 'border',
        padding: '5 5 5 5'
    },

    initComponent: function () {
        var me = this;

        Ext.applyIf(me, {

            items: [
				Ext.create("App.view.MainFrame.North"),
				Ext.create("App.view.MainFrame.West"),
				//Ext.create("App.view.MainFrame.East"),
				//Ext.create("App.view.MainFrame.South"),
				Ext.create("App.view.MainFrame.Center")
            ]
        });

        me.callParent(arguments);
    }
});