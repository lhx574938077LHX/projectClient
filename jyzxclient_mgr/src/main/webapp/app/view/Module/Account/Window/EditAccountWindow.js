Ext.define('App.view.Module.Account.Window.EditAccountWindow',
{
    extend : 'Ext.window.Window',
    
    autoShow : true,
    width : 600,
    layout : 'fit',
    title : '修改账号',
    record : null,
    detailId : null,
    pwin : null,
    
    initData : function ()
    {
        var me = this;
        var companyName = me.down("[itemId=companyName]");
        companyName.setValue(me.record.data.companyName);
        
        var companyCode = me.down("[itemId=companyCode]");
        companyCode.setValue(me.record.data.companyCode);
        
        var serviceUrl = me.down("[itemId=serviceUrl]");
        serviceUrl.setValue(me.record.data.serviceUrl);
        
        var p00001 = me.down("[itemId=p00001]");
        p00001.setValue(me.record.data.p00001);
        
        var p00001 = me.down("[itemId=p00001]");
        p00001.setValue(me.record.data.p00001);
        
        var p00002 = me.down("[itemId=p00002]");
        p00002.setValue(me.record.data.p00002);
        
        var p00003 = me.down("[itemId=p00003]");
        p00003.setValue(me.record.data.p00003);
        
        var p00004 = me.down("[itemId=p00004]");
        p00004.setValue(me.record.data.p00004);
        
        var p00005 = me.down("[itemId=p00005]");
        p00005.setValue(me.record.data.p00005);
        
        var p00005Param = me.down("[itemId=p00005Param]");
        p00005Param.setValue(me.record.data.p00005Param);
        
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
                    jQuery.ajax(
                    {
                        type : "POST",
                        url : 'account/updateAccount.do', //表单提交页面
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
                                    xtype : 'textfield',
                                    flex : 1,
                                    fieldLabel : '公司名称',
                                    name : 'companyName',
                                    itemId : 'companyName',
                                    labelAlign : 'right',
                                    labelWidth : 70,
                                    validator: function(value) {
                				    	if( value == null || value.trim() == "" ) {
                				    		return "公司名称不可以为空！";
                				    	}
                				    	return true;
                			    	}
                                },{
                                    xtype : 'textfield',
                                    flex : 1,
                                    fieldLabel : '公司编码',
                                    name : 'companyCode',
                                    itemId : 'companyCode',
                                    labelAlign : 'right',
                                    labelWidth : 70,
                                    validator: function(value) {
                				    	if( value == null || value.trim() == "" ) {
                				    		return "公司编码不可以为空！";
                				    	}
                				    	return true;
                			    	}
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
                                    fieldLabel : '回调地址',
                                    name : 'serviceUrl',
                                    itemId : 'serviceUrl',
                                    labelAlign : 'right',
                                    labelWidth : 70,
                                    validator: function(value) {
                				    	if( value == null || value.trim() == "" ) {
                				    		return "回调地址不可以为空！";
                				    	}
                				    	return true;
                			    	}
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
                                    fieldLabel : '3001被查',
                                    labelAlign : 'right',
                                    flex : 1,
                                    name : 'p00001',
                                    itemId : 'p00001',
                                    typeAhead:false,
                                    editable:false,
                                    labelWidth : 70,
                                    emptyText : '请选择...',
                                    store : data,
                                    validator: function(value) {
                				    	if( value == null || value.trim() == "" ) {
                				    		return "不可为空！";
                				    	}
                				    	return true;
                			    	}
                                },{
                                    xtype : 'combobox',
                                    fieldLabel : '3002报送',
                                    labelAlign : 'right',
                                    name : 'p00002',
                                    flex : 1,
                                    itemId : 'p00002',
                                    typeAhead:false,
                                    editable:false,
                                    labelWidth : 70,
                                    emptyText : '请选择...',
                                    store : data,
                                    validator: function(value) {
                				    	if( value == null || value.trim() == "" ) {
                				    		return "不可为空！";
                				    	}
                				    	return true;
                			    	}
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
                                    fieldLabel : '加密方式',
                                    labelAlign : 'right',
                                    name : 'p00003',
                                    itemId : 'p00003',
                                    flex : 1,
                                    displayField: 's_name',
                                    valueField: 's_value',
                                    typeAhead:false,
                                    editable:false,
                                    labelWidth : 70,
                                    emptyText : '请选择...',
                                    validator: function(value) {
                				    	if( value == null || value.trim() == "" ) {
                				    		return "不可为空！";
                				    	}
                				    	return true;
                			    	},
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
             	                                    s_name: '未开通',
             	                                    s_value: 0
             	                                 },
                                                 {
                                                     s_name: '签名通信',
                                                     s_value: 1
                                                 },
                                                 {
                                                     s_name: 'RSA加密',
                                                     s_value: 2
                                                 }
                                             ]
                                         })
                                },{
                                    xtype : 'combobox',
                                    fieldLabel : '1003查询',
                                    labelAlign : 'right',
                                    name : 'p00004',
                                    flex : 1,
                                    itemId : 'p00004',
                                    typeAhead:false,
                                    editable:false,
                                    labelWidth : 70,
                                    emptyText : '请选择...',
                                    store : data,
                                    validator: function(value) {
                				    	if( value == null || value.trim() == "" ) {
                				    		return "不可为空！";
                				    	}
                				    	return true;
                			    	}
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
                                    fieldLabel : '黑名单功能',
                                    labelAlign : 'right',
                                    name : 'p00005',
                                    itemId : 'p00005',
                                    flex : 1,
                                    typeAhead:false,
                                    editable:false,
                                    labelWidth : 70,
                                    emptyText : '请选择...',
                                    store : data,
                                    validator: function(value) {
                				    	if( value == null || value.trim() == "" ) {
                				    		return "不可为空！";
                				    	}
                				    	return true;
                			    	}
                                },{
                                    xtype : 'combobox',
                                    fieldLabel : '黑名单版本',
                                    labelAlign : 'right',
                                    name : 'p00005Param',
                                    itemId : 'p00005Param',
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
             	                                    s_name: '简版',
             	                                    s_value: 0
             	                                 },
                                                 {
                                                     s_name: '【明细版1】 显示最新的催收记录',
                                                     s_value: 1
                                                 },
                                                 {
                                                     s_name: '【明细版2】显示逾期最高的催收记录',
                                                     s_value: 2
                                                 },
                                                 {
                                                     s_name: '【明细版3】显示所有的记录',
                                                     s_value: 3
                                                 }
                                             ]
                                         }),
                                    validator: function(value) {
                				    	if( value == null || value.trim() == "" ) {
                				    		return "不可为空！";
                				    	}
                				    	return true;
                			    	}
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
