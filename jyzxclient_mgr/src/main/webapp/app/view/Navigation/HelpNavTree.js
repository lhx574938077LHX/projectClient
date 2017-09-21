Ext.define('App.view.Navigation.HelpNavTree', {
    extend: 'Ext.tree.TreePanel',
	
    id: 'helpNavTree',
    itemId: 'navtree_helpNavTree',
    border: false,
    rootVisible: false,
     		
    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            store: new Ext.data.TreeStore({
				proxy: {
					type: 'ajax',
//					url: 'authority/searchViewTree.do?pid=' +me.pnid
					url: datapath + 'TreeNode/HelpNavTree.json'
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