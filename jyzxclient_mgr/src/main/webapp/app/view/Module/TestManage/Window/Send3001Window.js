Ext.define('App.view.Module.TestManage.Window.Send3001Window',
{
    extend : 'Ext.window.Window',
    
    autoShow : true,
    width : 600,
    layout : 'fit',
    title : '发送3001报文',
    
    buttons : [
        {
            xtype : 'button',
            iconCls : 'icon962_16x16',
            text : '发送',
            handler : function ()
            {
                var me = this.up("window");
                var form = me.down("form");
                if (form.isValid())
                {
                    var fvalues = form.getValues();
                    jQuery.ajax(
                    {
                        type : "POST",
                        url : 'testManage/send3001Data.do', //表单提交页面
                        dataType : "text",
                        cache : false,
                        async : false,
                        data : {
                        	realName:fvalues.realName,
                        	idCard:fvalues.idCard,
                        	url : fvalues.serviceUrl
                        },
                        success : function (response)
                        {
                            var json = null;
                            try
                            {
                                json = eval("(" + response + ")");
                                var area = me.down("[itemId=result]");
                                var str = "反馈信息为："+json.data;
                                console.log(json);
                                if(json.redata!=null&&json.redata.length>0){
                                	for(var i=0;i<json.redata.length;i++){
                                		str = str + "\n\t";
                                		str = str + "borrowType:" + json.redata[i].borrowType+", ";
                                		str = str + "borrowState:" + json.redata[i].borrowState+", ";
                                		str = str + "borrowAmount:" + json.redata[i].borrowAmount+", ";
                                		str = str + "contractDate:" + json.redata[i].contractDate+", ";
                                		str = str + "loanPeriod:" + json.redata[i].loanPeriod+", ";
                                		str = str + "repayState:" + json.redata[i].repayState+", ";
                                		str = str + "arrearsAmount:" + json.redata[i].arrearsAmount+", ";
                                		str = str + "companyCode:" + json.redata[i].companyCode;
                                	}
                                }
                                area.setText(str);
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
//                                me.pwin.store.reload();
//                                me.close();
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
                            margin : '0 30 20 20',
                            layout :
                            {
                                type : 'hbox',
                                align : 'stretch'
                            },
                            items : [
                                {
                                    xtype : 'label',
                                    flex : 1,
                                    name : 'result',
                                    itemId : 'result',
                                    labelWidth : 70
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
