Ext.define('App.view.Navigation.TestManageNavTree', {
    extend: 'Ext.tree.TreePanel',
	
    id: 'testManageNavTree',
    itemId: 'navtree_testManageNavTree',
    border: false,
    rootVisible: false,
     		
    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            store: new Ext.data.TreeStore({
				proxy: {
					type: 'ajax',
					url: 'authority/searchViewTree.do?pid=' +me.pnid
//					url: datapath + 'TreeNode/TestManageNavTree.json'
				},
				useArrows: true,
				root: {
					expanded: true
				}
			})
        });

        me.callParent(arguments);
    }

});