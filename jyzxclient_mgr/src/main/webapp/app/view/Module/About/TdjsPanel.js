Ext.define('App.view.Module.About.TdjsPanel', {
    extend: 'Ext.panel.Panel',

    closable: true,
    id: 'tdjsPanel',
    title: '团队介绍',
    layout: 'fit',
    autoScroll: true,

    initComponent: function () {
        var me = this;

        Ext.applyIf(me, {
            items: [

            ]
        });

        me.callParent(arguments);
    }

});
