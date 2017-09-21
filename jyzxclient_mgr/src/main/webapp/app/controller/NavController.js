Ext.define('App.controller.NavController', {
    extend: 'Ext.app.Controller',

    init: function () {
        this.control({
            'treepanel[id=aboutNavTree]': {
                itemclick: this.onAboutNavTreeItemClick
            },
            'treepanel[id=helpNavTree]': {
                itemclick: this.onHelpNavTreeItemClick
            },
            'treepanel[id=accountNavTree]': {
                itemclick: this.onAccountNavTreeItemClick
            },
            'treepanel[id=testManageNavTree]': {
                itemclick: this.onTestManageNavTreeItemClick
            }
        });
    },

    onTestManageNavTreeItemClick: function (dataview, record, item, index, e, eOpts) {
        if (record.data.leaf) {
            if (record.data.id) {
                this.showTabItem(record.data.oid, 'App.view.Module.TestManage.' + Utils.ChangeCase(record.data.oid + "") + "Panel");
            }
        }
    },
    
    onAboutNavTreeItemClick: function (dataview, record, item, index, e, eOpts) {
        if (record.data.leaf) {
            if (record.data.id) {
                this.showTabItem(record.data.oid, 'App.view.Module.About.' + Utils.ChangeCase(record.data.oid + "") + "Panel");
            }
        }
    },
    onHelpNavTreeItemClick: function (dataview, record, item, index, e, eOpts) {
        if (record.data.leaf) {
            if (record.data.id) {
                this.showTabItem(record.data.oid, 'App.view.Module.Help.' + Utils.ChangeCase(record.data.oid + "") + "Panel");
            }
        }
    },
    onAccountNavTreeItemClick: function (dataview, record, item, index, e, eOpts) {
        if (record.data.leaf) {
            if (record.data.id) {
                this.showTabItem(record.data.oid, 'App.view.Module.Account.' + Utils.ChangeCase(record.data.oid + "") + "Panel");
            }
        }
    },
	
    showTabItem: function (id, clsname) {
    	if (clsname) {
            var navTabPanel = Ext.getCmp("contentTabPanel");
            var tabPanelItem = Ext.getCmp(id + "Panel");

            if (!tabPanelItem) {
                var tabpanel = Ext.create(clsname);
                navTabPanel.add(tabpanel);
                navTabPanel.setActiveTab(tabpanel);
            }
            else {
                navTabPanel.setActiveTab(tabPanelItem);
            }
        }
    }
});