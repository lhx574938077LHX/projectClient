Ext.define('App.view.Module.Account.Window.EditJoinConditionWindow',
{
    extend : 'Ext.window.Window',
    
    autoShow : true,
    width : 600,
    layout : 'fit',
    title : '修改对接信息',
    companyId : null,
    detailId : null,
    pwin : null,
    record:null,
    
    initData : function ()
    {
    	 var me = this;
         jQuery.ajax(
         {
             type : "POST",
             url : 'account/selectJoinConditionOne.do?companyId='+me.companyId, //表单提交页面
             dataType : "text",
             cache : false,
             async : false,
             success : function (response)
             {
                 var json = null;
                 try
                 {
                     json = eval("(" + response + ")");
                     console.log(json.data);//输出查询数据
                     
                     var startTime = me.down("[itemId=startTime]");
                     if(json.data.startTime!=null){
                   	 	startTime.setValue(new Date(json.data.startTime));
                     }
                     
                     var endTime = me.down("[itemId=endTime]");
                     if(json.data.endTime!=null){
                    	 endTime.setValue(new Date(json.data.endTime));
                     }
                     
                     var joinState = me.down("[itemId=joinState]");
                     joinState.setValue(json.data.joinState);   
                     
                     var timeInformation = me.down("[itemId=timeInformation]");
                     timeInformation.setValue(json.data.timeInformation);  
                     
                     var remark = me.down("[itemId=remark]");
                     remark.setValue(json.data.remark); 
                     
                     var bdName = me.down("[itemId=bdName]");
                     bdName.setValue(json.data.bdName); 
                     
                     var clientEmail = me.down("[itemId=clientEmail]");
                     clientEmail.setValue(json.data.clientEmail); 
                     
                     var isP3001 = me.down("[itemId=isP3001]");
                     isP3001.setValue(json.data.isP3001);
                     
                     me.detailId = json.data.id;
                     
                 }
                 catch (ex)
                 {
                     json =
                     {
                         result : false,
                         msg : '服务器返回结果异常！'
                     };
                 }
             },
             failure : function (resp)
             {
                 Ext.toast(
                 {
                     html : "与服务器连接失败",
                     closable : false,
                     align : 't',
                     slideInDuration : 400,
                     minWidth : 400
                 }
                 );
             }
         }
         );
    },
    
    buttons : [
        {
            xtype : 'button',
            iconCls : 'icon938_16x16',
            text : '保存',
            handler : function ()
            {
                var me = this.up("window");
                var form = me.down("form");
                if (form.isValid())
                {
                    var fvalues = form.getValues();
                    fvalues.id = me.detailId;
                    if(fvalues.startTime!=null&& fvalues.startTime!=''&&fvalues.startTime!=undefined ){
                    	fvalues.startTime = Date.parse(fvalues.startTime);
                    }
                    if(fvalues.endTime!=null&& fvalues.endTime!=''&&fvalues.endTime!=undefined ){
                    	fvalues.endTime = Date.parse(fvalues.endTime);
                    }
                    
                    jQuery.ajax(
                    {
                        type : "POST",
                        url : 'account/updateJoinCondition.do', //表单提交页面
                        dataType : "text",
                        cache : false,
                        async : false,
                        data : fvalues,
                        success : function (response)
                        {
                            var json = null;
                            try
                            {
                                json = eval("(" + response + ")");
                            }
                            catch (ex)
                            {
                                json =
                                {
                                    result : false,
                                    msg : '服务器返回结果异常！'
                                };
                            }
                            
                            Ext.toast(
                            {
                                html : json.msg,
                                closable : false,
                                align : 't',
                                slideInDuration : 400,
                                minWidth : 400
                            }
                            );
                            
                            if (json.result)
                            {
                                me.pwin.store.reload();
                                me.close();
                            }
                        },
                        failure : function (resp)
                        {
                            Ext.toast(
                            {
                                html : "与服务器连接失败",
                                closable : false,
                                align : 't',
                                slideInDuration : 400,
                                minWidth : 400
                            }
                            );
                        }
                    }
                    );
                    
                }
            }
        },{
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
                                    xtype : 'datefield',
                                    flex : 1,
                                    fieldLabel : '开始时间',
                                    name : 'startTime',
                                    itemId : 'startTime',
                                    labelAlign : 'right',
                                    editable:false,
                                    labelWidth : 70
                                }, {
                                    xtype : 'datefield',
                                    flex : 1,
                                    fieldLabel : '完成时间',
                                    name : 'endTime',
                                    itemId : 'endTime',
                                    labelAlign : 'right',
                                    editable:false,
                                    labelWidth : 70
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
                                    xtype : 'combobox',
                                    fieldLabel : '对接状态',
                                    labelAlign : 'right',
                                    name : 'joinState',
                                    itemId : 'joinState',
                                    flex : 1,
                                    displayField: 's_name',
                                    valueField: 's_value',
                                    typeAhead:false,
                                    editable:false,
                                    labelWidth : 70,
                                    emptyText : '请选择...',
                                    store : Ext.create('Ext.data.Store', {
                                        fields: [
                                                 {
                                                     name: 's_name'
                                                 },
                                                 {
                                                     name: 's_value'
                                                 }
                                             ],
                                             data: [
                                                 {
             	                                    s_name: '建群发文',
             	                                    s_value: 0
             	                                 },
                                                 {
                                                     s_name: '等待开发-排期',
                                                     s_value: 1
                                                 },
                                                 {
                                                     s_name: '对接联调',
                                                     s_value: 2
                                                 },
                                                 {
                                                     s_name: '等待上线-排期',
                                                     s_value: 3
                                                 },
                                                 {
                                                     s_name: '系统上线',
                                                     s_value: 4
                                                 },
                                                 {
                                                     s_name: '等待开发-终止',
                                                     s_value: 5
                                                 },
                                                 {
                                                     s_name: '等待上线-终止',
                                                     s_value: 6
                                                 }
                                             ]
                                         })
                                },{
                                    xtype : 'textfield',
                                    flex : 1,
                                    fieldLabel : '客户经理',
                                    name : 'bdName',
                                    itemId : 'bdName',
                                    labelAlign : 'right',
                                    labelWidth : 70
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
                                    xtype : 'textfield',
                                    flex : 1,
                                    fieldLabel : '客户邮箱',
                                    name : 'clientEmail',
                                    itemId : 'clientEmail',
                                    labelAlign : 'right',
                                    labelWidth : 70
                                },{
                                    xtype : 'combobox',
                                    fieldLabel : '是否被查',
                                    labelAlign : 'right',
                                    name : 'isP3001',
                                    flex : 1,
                                    itemId : 'isP3001',
                                    typeAhead:false,
                                    editable:false,
                                    labelWidth : 70,
                                    displayField: 's_name',
                                    valueField: 's_value',
                                    emptyText : '请选择...',
                                    store : Ext.create('Ext.data.Store', {
                                        fields: [
                                                 {
                                                     name: 's_name'
                                                 },
                                                 {
                                                     name: 's_value'
                                                 }
                                             ],
                                             data: [
                                                 {
             	                                    s_name: '不开放',
             	                                    s_value: 0
             	                                 },
                                                 {
                                                     s_name: '开放',
                                                     s_value: 1
                                                 }
                                             ]
                                         })
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
                                    xtype : 'textfield',
                                    flex : 1,
                                    fieldLabel : '实时信息',
                                    name : 'timeInformation',
                                    itemId : 'timeInformation',
                                    labelAlign : 'right',
                                    labelWidth : 70
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
                                    xtype : 'textarea',
                                    fieldLabel : '备注',
                                    labelAlign : 'right',
                                    name : 'remark',
                                    itemId : 'remark',
                                    flex : 1,
                                    labelWidth : 70
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
