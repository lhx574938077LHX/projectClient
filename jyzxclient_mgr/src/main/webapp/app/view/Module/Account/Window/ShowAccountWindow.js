Ext.define('App.view.Module.Account.Window.ShowAccountWindow',
{
    extend : 'Ext.window.Window',
    
    autoShow : true,
    width : 600,
    layout : 'fit',
    title : '信息查看',
    record : null,
    pwin : null,
    
    initData : function ()
    {
        var me = this;
        var companyName = me.down("[name=companyName]");
        companyName.setText(me.record.data.companyName);
        
        var serviceUrl = me.down("[name=serviceUrl]");
        serviceUrl.setText(me.record.data.serviceUrl);
        
        var companyCode = me.down("[name=companyCode]");
        companyCode.setText(me.record.data.companyCode);
        
        var connSign = me.down("[name=connSign]");
        connSign.setText(me.record.data.connSign);
    },
    
    buttons : [
         {
            xtype : 'button',
            iconCls : 'icon937_16x16',
            text : '关闭',
            handler : function ()
            {
                var me = this.up("window");
                me.close();
            }
        }
    ],
    
    initComponent : function ()
    {
        var me = this;
        var data = [['0', '未开通'],['1', '开通']];
        Ext.applyIf(me,
        {
            
            tools : [
                {
                    type : 'maximize'
                },
                {
                    type : 'restore'
                }
            ],
            listeners :
            {
                afterrender : function ()
                {
                    me.initData();
                }
            },
            items : [
                {
                    xtype : 'form',
                    layout :
                    {
                        type : 'vbox',
                        align : 'stretch'
                    },
                    items : [
                        {
                            xtype : 'container',
                            margin : '5 30 8 0',
                            layout :
                            {
                                type : 'hbox',
                                align : 'stretch'
                            },
                            items : [
                                {
                             	   xtype : 'label',
                            	   text: "公司名称:",
                            	   width:100
                               },{
                             	   xtype : 'label',
                            	   name:'companyName'
                            
                               }
                            ]
                        },{
                            xtype : 'container',
                            margin : '5 30 8 0',
                            layout :
                            {
                                type : 'hbox',
                                align : 'stretch'
                            },
                            items : [
                                {
                             	   xtype : 'label',
                            	   text: "公司编码:",
                            	   width:100
                               },{
                             	   xtype : 'label',
                            	   name: "companyCode"
                               }
                            ]
                        },
                        {
                            xtype : 'container',
                            margin : '0 30 8 0',
                            layout :
                            {
                                type : 'hbox',
                                align : 'stretch'
                            },
                            items : [
                                {
                             	   xtype : 'label',
                            	   text: "回调地址:",
                            	   width:100
                               },{
                                    xtype : 'label',
                                    flex : 1,
                                    name : 'serviceUrl'
                                }
                            ]
                        },
                        {
                            xtype : 'container',
                            margin : '0 30 8 0',
                            layout :
                            {
                                type : 'hbox',
                                align : 'stretch'
                            },
                            items : [
                                {
                             	   xtype : 'label',
                            	   text: "公司签名:",
                            	   width:100
                               },{
                                    xtype : 'label',
                                    flex : 1,
                                    name : 'connSign'
                                }
                            ]
                        }
                    ]
                }
            ]
        });
        
        me.callParent(arguments);
    }
    
});
