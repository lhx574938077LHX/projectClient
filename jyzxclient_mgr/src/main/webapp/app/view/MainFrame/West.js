Ext.define('App.view.MainFrame.West', {
    extend: 'Ext.TabPanel',
	
	id: 'navTabPanel',
	region: 'west',
	collapsible: true,
	title: '功能导航',
	iconCls: 'icon42_16x16',
	split: true,
	width: 200,
	minWidth: 100,
	tabPosition: 'bottom',
	minHeight: 100,
	
    initComponent: function() {
        var me = this;
		
        Ext.applyIf(me, {
            listeners: {
                'afterrender': function () {
                    jQuery.ajax({
                        type: "POST",
                        url: 'authority/selectMenuAuthority.do',
                        dataType: "json",
                        cache: false,
                        async: false,
                        data: {
                            pid: 0
                        },
                        success: function (response) {
                            var json = response.datas;
                  	    	console.log(json)
                            for (var item in json) {
                            	console.log(item);
	                            me.add({
	                                layout: {
	                                    type: 'fit'
	                                },
	                                title: json[item].text,
	            					autoScroll: true,
	                                iconCls: json[item].iconcls,
	                                layout: {
	                                    type: 'accordion',
	                                    animate: true
	                                },
	                                itemId: json[item].oid+'Panel',
	                                items: []
	                            });
	                            me.add({
	            					title: '帮助',
	            					autoScroll: true,
	            					iconCls: 'icon538_16x16',
	            					items: [
	            						Ext.create("App.view.Navigation.HelpNavTree")
	            					]
	                            });
	                  	    	 var gnnavPanel = me.down("[itemId=gnnavPanel]");
	                	    	 var helpPanel = me.down("[itemId=helpPanel]");
	                	    	 console.log(gnnavPanel);
	                             if(gnnavPanel){
	                            	 jQuery.ajax({
	                                     type: "POST",
	                                     url: 'authority/selectMenuAuthority.do',
	                                     dataType: "json",
	                                     cache: false,
	                                     async: false,
	                                     data: {
	                                         pid: 1
	                                     },
	                                     success: function (response) {
	                                         var jsons = response.datas;
	                                         console.log(jsons);
	                                         for (var i in jsons) {
	                                        	 var xtype=jsons[i].oid+"NavTree";
	                                        	 console.log(jsons[i].id);
	    	                                     gnnavPanel.add({
	    	                                    	 xtype: 'panel',
	                                                 layout: {
	                                                     type: 'fit'
	                                                 },
	                                                 title: jsons[i].text,
	                                                 iconCls: jsons[i].iconcls,
	                                                 items: [
	                                                         {
	                                                             pnid: jsons[i].id,
	                                                             xtype: xtype
	                                                         }
	                                                     ]
	    	                                     });
	                                         }
	                                     }
	                            	 })
	                             }
	                        	 if(helpPanel){
	                        	 }
                            }
                        },
                        failure: function (resp) {

                        }
                    });
                }
            },
            tools: [
                {
                    type: 'refresh',
                    listeners: {
                        'click': function(){
                            var navtrees = me.query("*[itemId^=navtree_]");

                            Ext.Array.each(navtrees, function (tree) {
                                tree.getStore().reload();
                            });
                        }
                    }
                }
            ],
            /*
            items: [
                {
                    title: '功能',
                    iconCls: 'icon22_16x16',
                    layout: {
                        type: 'accordion',
                        animate: true
                    },
                    itemId: 'gnnavPanel',
                    items: [
                        {
                            xtype: 'panel',
                            title: '关于平台',
                            iconCls: 'icon448_16x16',
                            layout: {
                                type: 'fit'
                            },
                            items: [
                                {
                                    xtype: 'aboutNavTree'
                                }
                            ]
                        },
                        {
                            xtype: 'panel',
                            title: '客户管理',
                            iconCls: 'icon456_16x16',
                            layout: {
                                type: 'fit'
                            },
                            items: [
                                {
                                    xtype: 'accountNavTree'
                                }
                            ]
                        },
                        {
                            xtype: 'panel',
                            title: '测试管理',
                            iconCls: 'icon457_16x16',
                            layout: {
                                type: 'fit'
                            },
                            items: [
                                {
                                    xtype: 'testManageNavTree'
                                }
                            ]
                        }
                    ]
                },
				{
					title: '帮助',
					autoScroll: true,
					iconCls: 'icon538_16x16',
					items: [
						Ext.create("App.view.Navigation.HelpNavTree")
					]
				}
            ]
            */
        });

        me.callParent();
    }
});