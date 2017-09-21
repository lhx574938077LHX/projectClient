Ext.define('App.view.Navigation.AboutNavTree', {
    extend: 'Ext.tree.TreePanel',
	
    id: 'aboutNavTree',
	border: false,
	itemId: 'navtree_aboutNavTree',
	rootVisible: false,
    pnid: 0,
	
    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
			store: new Ext.data.TreeStore({
				proxy: {
					type: 'ajax',
					url: 'authority/searchViewTree.do?pid=' +me.pnid
//					url: datapath + 'TreeNode/AboutNavTree.json'
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