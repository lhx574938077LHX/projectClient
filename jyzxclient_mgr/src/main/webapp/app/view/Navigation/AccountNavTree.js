Ext.define('App.view.Navigation.AccountNavTree', {
    extend: 'Ext.tree.TreePanel',
	
    id: 'accountNavTree',
	border: false,
	itemId: 'navtree_accountNavTree',
	rootVisible: false,
    pnid: 0,
	
    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
			store: new Ext.data.TreeStore({
				proxy: {
					type: 'ajax',
					url: 'authority/searchViewTree.do?pid=' +me.pnid
//					url: datapath + 'TreeNode/AccountNavTree.json'
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