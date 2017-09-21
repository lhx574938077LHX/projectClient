Ext.define('App.view.Module.Account.YhzhPanel', {
    extend: 'Ext.panel.Panel',

    closable: true,
    id: 'yhzhPanel',
    title: '用户账号',
    layout: 'fit',
    autoScroll: true,
    pwin : null,
    store : null,
    
    searchs:function(me)
    {
    	var me = this;
    	var cpName = me.down("[name=cpName]").getValue();
    	var cpCode = me.down("[name=cpCode]").getValue();
    	
        me.store.getProxy().url = "account/queryAccountLJP.do?cpName="+cpName+"&cpCode="+cpCode;
        me.store.loadPage(1);;
    },
    
    AddAccountWindow : function (me)
    {
        var window = me.add(
            {
                constrainHeader : true,
                autoDestroy : false,
                closeAction : 'hide',
                pwin : me,
                modal : true,
                xtype : 'addAccountWindow'
            }
            );
        window.show();
    },
    ShowAccountWindow: function (me, mymodel)
    {
        var window = me.add(
            {
                constrainHeader : true,
                autoDestroy : false,
                record : mymodel,
                closeAction : 'hide',
                pwin : me,
                modal : true,
                xtype : 'showAccountWindow'
            }
            );
        window.show();
    },
    
    EditJoinConditionWindow: function (me, mymodel, detailId)
    {
        var window = me.add(
            {
                constrainHeader : true,
                autoDestroy : false,
                closeAction : 'hide',
                companyId : detailId,
                pwin : me,
                modal : true,
                xtype : 'editJoinConditionWindow'
            }
            );
        window.show();
    },
    
    EditAccountWindow : function (me, mymodel, detailId)
    {
        var window = me.add(
            {
                constrainHeader : true,
                autoDestroy : false,
                closeAction : 'hide',
                record : mymodel,
                detailId : detailId,
                pwin : me,
                modal : true,
                xtype : 'editAccountWindow'
            }
            );
        window.show();
    },
    
    dockedItems: [
	      {
	          xtype: 'toolbar',
	          dock: 'top',
	          items: [

					{
					    xtype : 'textfield',
					    fieldLabel : '公司名称',
					    labelAlign : 'right',
					    name : 'cpName',
					    labelWidth : 70
					},{
					    xtype : 'textfield',
					    fieldLabel : '机构代码',
					    labelAlign : 'right',
					    name : 'cpCode',
					    labelWidth : 70
					},
					{
					    xtype : 'button',
					    text : '查询',
					    iconCls : 'icon635_16x16',
					    handler : function ()
					    {
					        var me = this.up("[id=yhzhPanel]");
					        me.searchs();
					    }
					},  
					{
					    xtype : 'button',
					    iconCls : 'icon587_16x16',
					    text : '添加账户',
					    listeners :
					    {
					        'click' : function ()
					        {
					            var me = this.up("[id=yhzhPanel]");
					            me.AddAccountWindow(me);
					        }
					    }
					},
	                {
	                    xtype : 'button',
	                    iconCls : 'icon590_16x16',
	                    text : '修改账号',
	                    listeners :
	                    {
	                        'click' : function (gridpanel, record, item, index, e, eOpts)
	                        {
	                            var me = this.up("[id=yhzhPanel]");
	                            var selmodel = me.down("[itemId=YhzhGroupGrid]").getSelectionModel();
	                            var hsel = selmodel.hasSelection();
	                            if (hsel)
	                            {
	                                var mymodel = selmodel.getSelection()[0];
	                                me.EditAccountWindow(me, mymodel, mymodel.data.id);
	                            }
	                            else
	                            {
	                                Ext.toast(
	                                {
	                                    html : "请选择你要修改的账户！",
	                                    closable : false,
	                                    align : 't',
	                                    slideInDuration : 400,
	                                    minWidth : 400
	                                }
	                                );
	                            }
	                        }
	                    }
	                },
	                {
	                    xtype : 'button',
	                    iconCls : 'icon594_16x16',
	                    text : '查看查询信息',
	                    listeners :
	                    {
	                        'click' : function (gridpanel, record, item, index, e, eOpts)
	                        {
	                            var me = this.up("[id=yhzhPanel]");
	                            var selmodel = me.down("[itemId=YhzhGroupGrid]").getSelectionModel();
	                            var hsel = selmodel.hasSelection();
	                            if (hsel)
	                            {
	                                var mymodel = selmodel.getSelection()[0];
			                        var id=mymodel.id;
                                    var window = me.add(
	                                 {
	                                     constrainHeader : true,
	                                     autoDestroy : false,
	                                     closeAction : 'destroy',
	                                     modal : true,
	                                     detailId:id,
	                                     xtype : 'checkAccountWindow',
	                                     pwin : me
	                               });
    		                       window.show();	
	                            }
	                            else
	                            {
	                                Ext.toast(
	                                {
	                                    html : "请选择你要修改的账户！",
	                                    closable : false,
	                                    align : 't',
	                                    slideInDuration : 400,
	                                    minWidth : 400
	                                }
	                                );
	                            }
	                        }
	                    }
	                },
	                {
	                    xtype : 'button',
	                    iconCls : 'icon590_16x16',
	                    text : '修改对接信息',
	                    listeners :
	                    {
	                        'click' : function (gridpanel, record, item, index, e, eOpts)
	                        {
	                            var me = this.up("[id=yhzhPanel]");
	                            var selmodel = me.down("[itemId=YhzhGroupGrid]").getSelectionModel();
	                            var hsel = selmodel.hasSelection();
	                            if (hsel)
	                            {
	                                var mymodel = selmodel.getSelection()[0];
	                                me.EditJoinConditionWindow(me, mymodel, mymodel.data.id);
	                            }
	                            else
	                            {
	                                Ext.toast(
	                                {
	                                    html : "请选择你要修改的账户！",
	                                    closable : false,
	                                    align : 't',
	                                    slideInDuration : 400,
	                                    minWidth : 400
	                                }
	                                );
	                            }
	                        }
	                    }
	                },
	                {
	                    xtype : 'button',
	                    iconCls : 'icon587_16x16',
	                    text : '查看基本信息',
	                    listeners :
	                    {
	                        'click' : function (gridpanel, record, item, index, e, eOpts)
	                        {
	                            var me = this.up("[id=yhzhPanel]");
	                            var selmodel = me.down("[itemId=YhzhGroupGrid]").getSelectionModel();
	                            var hsel = selmodel.hasSelection();
	                            if (hsel)
	                            {
	                                var mymodel = selmodel.getSelection()[0];
	                                me.ShowAccountWindow(me, mymodel);
	                            }
	                            else
	                            {
	                                Ext.toast(
	                                {
	                                    html : "请选择你要查看的账户！",
	                                    closable : false,
	                                    align : 't',
	                                    slideInDuration : 400,
	                                    minWidth : 400
	                                }
	                                );
	                            }
	                        }
	                    }
	                }
	          ]
	      }
	  ],

    initComponent: function() {
        var me = this;
        
        me.store = Ext.create("Ext.data.Store",
            {
                pageSize : 40,
                fields : [
                      	{
                    		name:"id"	//标识列
                    	},
                    	{
                    		name:"connSign"	//通信签名
                    	},
                    	{
                    		name:"companyName"	//公司名
                    	},
                    	{
                    		name:"companyCode"	//公司编码
                    	},
                    	{
                    		name:"integral"	//积分
                    	},
                    	{
                    		name:"state"	//当前状态 0.未知1.待审批 2.未通过审核 3.已开通 4.已停用
                    	},
                    	{
                    		name:"stateExt"	//当前状态 0.未知1.待审批 2.未通过审核 3.已开通 4.已停用
                    	},
                    	{
                    		name:"serviceUrl"	//服务地址
                    	},
                    	{
                    		name:"p00001"	//3001被查 当前状态 0.未开通1.已开通
                    	},
                    	{
                    		name:"p00002"	//3002报送 当前状态 0.未开通1.已开通
                    	},
                    	{
                    		name:"p00003"	//加密方式 0.未开通1.签名通信2.RSA加密
                    	},
                    	{
                    		name:"p00004"	//1003功能 0.未开通1.已开通
                    	},
                    	{
                    		name:"p00001Ext"	//3001被查 当前状态 0.未开通1.已开通
                    	},
                    	{
                    		name:"p00002Ext"	//3002报送 当前状态 0.未开通1.已开通
                    	},
                    	{
                    		name:"p00003Ext"	//加密方式 0.未开通1.签名通信2.RSA加密
                    	},
                    	{
                    		name:"p00004Ext"	//1003功能 0.未开通1.已开通
                    	},
                    	{
                    		name:"recTime"	//创建时间
                    	},
                    	{
                    		name:"recTimeExt"	//创建时间
                    	},
                    	{
                    		name:"sourceType"	//数据源类型 0.公开1.私有
                    	},
                    	{
                    		name:"excluded"	//要排除的公司CODE 用|号分割
                    	},
                    	{
                    		name:"bindIp"	//绑定IP 用|号分割
                    	},
                    	{
                    		name:"integralOverdraft"	//积分透支额度
                    	},
                    	{
                    		name:"startChargingTime"	//开始计费时间
                    	},
                    	{
                    		name:"p00005"	//黑名单产品功能 0.未开通1.已开通
                    	},
                    	{
                    		name:"p00005Ext"	//黑名单产品功能 0.未开通1.已开通
                    	},
                    	{
                    		name:"p00005Param"	//黑名单产品版本0.简版1.【明细版1】 显示最新的催收记录2.【明细版2】显示逾期最高的催收记录3.【明细版3】显示所有的记录
                    	},
                    	{
                    		name:"p00005ParamExt"	//黑名单产品版本0.简版1.【明细版1】 显示最新的催收记录2.【明细版2】显示逾期最高的催收记录3.【明细版3】显示所有的记录
                    	}

                ],
                listeners :
                {
                    load : function (store, records, successful, eOpts)
                    {
                        for (index in records)
                        {
                        	 records[index].data.recTimeExt = Ext.Date.format(new Date(records[index].data.recTime), "Y-m-d");
                        	 switch(records[index].data.state){
	                        	case 1:
	                        		records[index].data.stateExt="待审批";
	                            	break;
	                            case 2:
	                            	records[index].data.stateExt="未通过审核";
	                            	break;
	                            case 3:
	                            	records[index].data.stateExt="已开通";
	                            	break;
	                            case 4:
		                            records[index].data.stateExt="已停用";
	                            	break;
	                        	case 0:
	                        		records[index].data.stateExt="未知";
	                            	break;
	                        }
                        	switch (records[index].data.p00001) {
								case 0:
									records[index].data.p00001Ext="未开通";
									break;
								case 1:
									records[index].data.p00001Ext="已开通";
									break;
							}
                        	switch (records[index].data.p00002) {
								case 0:
									records[index].data.p00002Ext="未开通";
									break;
								case 1:
									records[index].data.p00002Ext="已开通";
									break;
							}
                        	switch (records[index].data.p00003) {
								case 0:
									records[index].data.p00003Ext="未开通";
									break;
								case 1:
									records[index].data.p00003Ext="已开通";
									break;
							}
                        	switch (records[index].data.p00004) {
								case 0:
									records[index].data.p00004Ext="未开通";
									break;
								case 1:
									records[index].data.p00004Ext="已开通";
									break;
							}
                        	switch (records[index].data.p00005) {
								case 0:
									records[index].data.p00005Ext="未开通";
									break;
								case 1:
									records[index].data.p00005Ext="已开通";
									break;
							}
                        	switch (records[index].data.p00005Param) {
								case 0:
									records[index].data.p00005ParamExt="简版";
									break;
								case 1:
									records[index].data.p00005ParamExt="【明细版1】 显示最新的催收记录";
									break;
								case 2:
									records[index].data.p00005ParamExt="【明细版2】显示逾期最高的催收记录";
									break;
								case 3:
									records[index].data.p00005ParamExt="【明细版3】显示所有的记录";
									break;
							}

                        }
                        var grid = me.down("gridpanel");
                        grid.reconfigure(store);
                    }
                },
                proxy : new Ext.data.proxy.Ajax(
                {
                    url : 'account/queryAccountLJP.do',
                    reader :
                    {
                        type : 'json',
                        root : 'datas',
                        totalProperty : 'dataCount'
                    },
                    actionMethods :
                    {
                        create : 'POST',
                        read : 'POST',
                        update : 'POST',
                        destroy : 'POST'
                    }
                }
                ),
                autoLoad : true
            }
            );
        
        Ext.applyIf(me,
        {
            items : [
                {
                    xtype : 'gridpanel',
                    store : me.store,
                    itemId : 'YhzhGroupGrid',
                    selType : 'rowmodel',
                    
                    columns : [
                        	{
								xtype: 'gridcolumn',
								text: '标识列',
								dataIndex:"id"
							},
							{
								xtype: 'gridcolumn',
								text: '通信签名',
								dataIndex:"connSign",
								width : 280
							},
							{
								xtype: 'gridcolumn',
								text: '公司名称',
								dataIndex:"companyName"
							},
							{
								xtype: 'gridcolumn',
								text: '公司编码',
								dataIndex:"companyCode",
								width : 200
							},
							{
								xtype: 'gridcolumn',
								text: '当前状态',
								dataIndex:"stateExt"
							},
							{
								xtype: 'gridcolumn',
								text: '回调地址',
								dataIndex:"serviceUrl",
								width : 350
							},
							{
								xtype: 'gridcolumn',
								text: '3001被查',
								dataIndex:"p00001Ext"
							},
							{
								xtype: 'gridcolumn',
								text: '3002报送',
								dataIndex:"p00002Ext"
							},
							{
								xtype: 'gridcolumn',
								text: '加密方式',
								dataIndex:"p00003Ext"
							},
							{
								xtype: 'gridcolumn',
								text: '1003功能',
								dataIndex:"p00004Ext"
							},
							{
								xtype: 'gridcolumn',
								text: '创建时间',
								dataIndex:"recTimeExt"
							},
							{
								xtype: 'gridcolumn',
								text: '黑名单产品功能',
								dataIndex:"p00005Ext"
							},
							{
								xtype: 'gridcolumn',
								text: '黑名单产品版本',
								dataIndex:"p00005ParamExt"
							}
                        
                    ],
                    dockedItems : [
                        {
                            xtype : 'pagingtoolbar',
                            dock : 'bottom',
                            width : 360,
                            store : me.store,
                            displayInfo : true
                        }
                    ]
                }
            ]
        });
        
        me.callParent(arguments);
    }

});
