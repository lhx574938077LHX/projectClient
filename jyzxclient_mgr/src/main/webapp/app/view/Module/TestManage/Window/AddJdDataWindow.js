Ext.define('App.view.Module.TestManage.Window.AddJdDataWindow',
{
    extend : 'Ext.window.Window',
    
    autoShow : true,
    width : 600,
    layout : 'fit',
    title : '添加数据',
    
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
                    if(fvalues.contractDate!=null&& fvalues.contractDate!=''&&fvalues.contractDate!=undefined ){
                    	fvalues.contractDate = Date.parse(fvalues.contractDate);
                    }    
                    fvalues.arrearsAmount = fvalues.arrearsAmount * 100000;
                    jQuery.ajax(
                    {
                        type : "POST",
                        url : 'testManage/addJdData.do', //表单提交页面
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
        },
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
                                    fieldLabel : '姓名',
                                    name : 'realName',
                                    itemId : 'realName',
                                    labelAlign : 'right',
                                    labelWidth : 70,
                                    validator : function (value)
                                    {
                                        if (value == null || value.trim() == "")
                                        {
                                            return "姓名不可以为空！";
                                        }
                                        return true;
                                    }
                                },
                                {
                                    xtype : 'textfield',
                                    flex : 1,
                                    fieldLabel : '身份证号',
                                    name : 'idCard',
                                    itemId : 'idCard',
                                    labelAlign : 'right',
                                    labelWidth : 70,
                                    validator : function (value)
                                    {
                                        if (value == null || value.trim() == "")
                                        {
                                            return "身份证号不可以为空！";
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
                                    fieldLabel : '借款类型',
                                    labelAlign : 'right',
                                    flex : 1,
                                    name : 'borrowType',
                                    itemId : 'borrowType',
                                    displayField: 's_name',
                                    valueField: 's_value',
                                    typeAhead : false,
                                    editable : false,
                                    labelWidth : 70,
                                    emptyText : '请选择...',
                                    store : Ext.create('Ext.data.Store',
                                    {
                                        fields : [
                                            {
                                                name : 's_name'
                                            },
                                            {
                                                name : 's_value'
                                            }
                                        ],
                                        data : [
                                            {
                                                s_name : '未知',
                                                s_value : 0
                                            },
                                            {
                                                s_name : '个人信贷',
                                                s_value : 1
                                            },
                                            {
                                                s_name : '个人抵押',
                                                s_value : 2
                                            },
                                            {
                                                s_name : '企业信贷',
                                                s_value : 3
                                            },
                                            {
                                                s_name : '企业抵押',
                                                s_value : 4
                                            }
                                        ]
                                    }
                                    )
                                },
                                {
                                    xtype : 'combobox',
                                    fieldLabel : '借款状态',
                                    labelAlign : 'right',
                                    name : 'borrowState',
                                    flex : 1,
                                    itemId : 'borrowState',
                                    displayField: 's_name',
                                    valueField: 's_value',
                                    typeAhead : false,
                                    editable : false,
                                    labelWidth : 70,
                                    emptyText : '请选择...',

                                    store : Ext.create('Ext.data.Store',
                                    {
                                        fields : [
                                            {
                                                name : 's_name'
                                            },
                                            {
                                                name : 's_value'
                                            }
                                        ],
                                        data : [
                                            {
                                                s_name : '未知',
                                                s_value : 0
                                            },
                                            {
                                                s_name : '拒贷',
                                                s_value : 1
                                            },
                                            {
                                                s_name : '批贷已放款',
                                                s_value : 2
                                            },
                                            {
                                                s_name : '借款人放弃申请',
                                                s_value : 4
                                            },
                                            {
                                                s_name : '审核中',
                                                s_value : 5
                                            },
                                            {
                                                s_name : '待放款',
                                                s_value : 6
                                            }
                                        ]
                                    }
                                    )
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
                                    xtype : 'numberfield',
                                    flex : 1,
                                    fieldLabel : '借款金额',
                                    name : 'borrowAmount',
                                    itemId : 'borrowAmount',
                                    labelAlign : 'right',
                                    labelWidth : 70
                                },
                                {
                                    xtype : 'datefield',
                                    labelAlign : 'right',
                                    fieldLabel : '合同日期',
                                    flex : 1,
                                    name : 'contractDate',
                                    itemId : 'contractDate',
                                    format : 'Y-m-d',
                                    labelWidth : 70,
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
                                    xtype : 'numberfield',
                                    flex : 1,
                                    fieldLabel : '批贷期数',
                                    name : 'loanPeriod',
                                    itemId : 'loanPeriod',
                                    labelAlign : 'right',
                                    labelWidth : 70
                                },
                                {
                                    xtype : 'combobox',
                                    fieldLabel : '还款状态',
                                    labelAlign : 'right',
                                    name : 'repayState',
                                    flex : 1,
                                    itemId : 'repayState',
                                    displayField: 's_name',
                                    valueField: 's_value',
                                    typeAhead : false,
                                    editable : false,
                                    labelWidth : 70,
                                    emptyText : '请选择...',
                                    store : Ext.create('Ext.data.Store',
                                    {
                                        fields : [
                                            {
                                                name : 's_name'
                                            },
                                            {
                                                name : 's_value'
                                            }
                                        ],
                                        data : [
                                            {
                                                s_name : '未知',
                                                s_value : 0
                                            },
                                            {
                                                s_name : '正常',
                                                s_value : 1
                                            },
                                            {
                                                s_name : 'M1',
                                                s_value : 2
                                            },
                                            {
                                                s_name : 'M2',
                                                s_value : 3
                                            },
                                            {
                                                s_name : 'M3',
                                                s_value : 4
                                            },
                                            {
                                                s_name : 'M4',
                                                s_value : 5
                                            },
                                            {
                                                s_name : 'M5',
                                                s_value : 6
                                            },
                                            {
                                                s_name : 'M6',
                                                s_value : 7
                                            },
                                            {
                                                s_name : 'M6+',
                                                s_value : 8
                                            },
                                            {
                                                s_name : '已还清',
                                                s_value : 9
                                            }
                                        ]
                                    }
                                    )
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
                                    xtype : 'numberfield',
                                    flex : 1,
                                    fieldLabel : '欠款金额',
                                    name : 'arrearsAmount',
                                    itemId : 'arrearsAmount',
                                    labelAlign : 'right',
                                    labelWidth : 70,
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
        }
        );
        
        me.callParent(arguments);
    }
    
}
);
