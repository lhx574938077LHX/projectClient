Ext.define('App.view.Module.TestManage.CsglPanel', {
    extend: 'Ext.panel.Panel',

    closable: true,
    id: 'csglPanel',
    title: '测试管理',
    layout: 'fit',
    autoScroll: true,
    urlDownload : null,
    
    TestDatasWindow: function (me)
    {
        var window = me.add(
            {
                constrainHeader : true,
                autoDestroy : false,
                closeAction : 'destory',
                pwin : me,
                modal : true,
                xtype : 'testDatasWindow'
            }
            );
        window.show();
    },
    
    Send3001Window : function (me)
    {
        var window = me.add(
            {
                constrainHeader : true,
                autoDestroy : false,
                closeAction : 'hide',
                pwin : me,
                modal : true,
                xtype : 'send3001Window'
            }
            );
        window.show();
    },
    
    Send3002Window : function (me)
    {
        var window = me.add(
            {
                constrainHeader : true,
                autoDestroy : false,
                closeAction : 'hide',
                pwin : me,
                modal : true,
                xtype : 'send3002Window'
            }
            );
        window.show();
    },
    
    AddJdDataWindow : function (me)
    {
        var window = me.add(
            {
                constrainHeader : true,
                autoDestroy : false,
                closeAction : 'hide',
                pwin : me,
                modal : true,
                xtype : 'addJdDataWindow'
            }
            );
        window.show();
    },
    
    EditJdDataWindow: function (me, mymodel, detailId)
    {
        var window = me.add(
            {
                constrainHeader : true,
                autoDestroy : false,
                closeAction : 'hide',
                detailId : detailId,
                record : mymodel,
                pwin : me,
                modal : true,
                xtype : 'editJdDataWindow'
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
					    xtype : 'button',
					    iconCls : 'icon587_16x16',
					    text : '添加数据',
					    listeners :
					    {
					        'click' : function ()
					        {
					            var me = this.up("[id=csglPanel]");
					            me.AddJdDataWindow(me);
					        }
					    }
					},
	                {
	                    xtype : 'button',
	                    iconCls : 'icon590_16x16',
	                    text : '修改数据',
	                    listeners :
	                    {
	                        'click' : function (gridpanel, record, item, index, e, eOpts)
	                        {
	                            var me = this.up("[id=csglPanel]");
	                            var selmodel = me.down("[itemId=CsglGroupGrid]").getSelectionModel();
	                            var hsel = selmodel.hasSelection();
	                            if (hsel)
	                            {
	                                var mymodel = selmodel.getSelection()[0];
	                                me.EditJdDataWindow(me, mymodel, mymodel.data.id);
	                            }
	                            else
	                            {
	                                Ext.toast(
	                                {
	                                    html : "请选择你要修改的数据！",
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
                      iconCls : 'icon589_16x16',
                      text : '删除数据',
                      handler : function ()
                      {
                          var me = this.up("[id=csglPanel]");
                          var selmodel = me.down("[itemId=CsglGroupGrid]").getSelectionModel();
                          var hsel = selmodel.hasSelection();
                          if (hsel)
                          {
                              var mymodel = selmodel.getSelection()[0];
                              var fvalues =
                              {
                                  id : mymodel.data.id
                              };
                              Ext.Msg.show(
                              {
                                  title : '消息',
                                  msg : "请确认您是否要删除这条的数据？",
                                  buttons : Ext.Msg.YESNO,
                                  icon : Ext.Msg.QUESTION,
                                  fn : function (btnTxt)
                                  {
                                      if (btnTxt == "yes")
                                      {
                                          jQuery.ajax(
                                          {
                                              type : "POST",
                                              url : 'testManage/deleteJdData.do', //表单提交页面
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
                                                  
                                                  me.store.reload();
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
                              }
                              );
                          }
                          else
                          {
                              Ext.toast(
                              {
                                  html : "请选择要删除的数据",
                                  closable : false,
                                  align : 't',
                                  slideInDuration : 400,
                                  minWidth : 400
                              }
                              );
                          }
                      }
                  },
					{
					    xtype : 'button',
					    iconCls : 'icon595_16x16',
					    text : '发送3001报文',
					    listeners :
					    {
					        'click' : function ()
					        {
					            var me = this.up("[id=csglPanel]");
					            me.Send3001Window(me);
					        }
					    }
					},
					{
					    xtype : 'button',
					    iconCls : 'icon596_16x16',
					    text : '发送3002报文',
					    listeners :
					    {
					        'click' : function ()
					        {
					            var me = this.up("[id=csglPanel]");
					            me.Send3002Window(me);
					        }
					    }
					},
					{
					    xtype : 'button',
					    iconCls : 'icon525_16x16',
					    text : '测试数据',
					    listeners :
					    {
					        'click' : function ()
					        {
					            var me = this.up("[id=csglPanel]");
					            me.TestDatasWindow(me);
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
	                		name:"realName"	//姓名
	                	},
	                	{
	                		name:"idCard"	//身份证号
	                	},
	                	{
	                		name:"companyId"	//公司ID
	                	},
	                	{
	                		name:"companyCode"	//公司编码
	                	},
	                	{
	                		name:"borrowType"	//借款类型 0.未知1.个人信贷2.个人抵押3.企业信贷4.企业抵押
	                	},
	                	{
	                		name:"borrowState"	//借款状态 0.未知1.拒贷2.批贷已放款3.批贷未放款4.借款人放弃申请5.审核中6.待放款
	                	},
	                	{
	                		name:"borrowAmount"	//借款金额 0.未知 1.[0,2], 2.[2,4], 3.[4,6], 4.[6,8] ........
	                	},
	                	{
	                		name:"contractDate"	//合同日期
	                	},
	                	{
	                		name:"loanPeriod"	//批贷期数
	                	},
	                	{
	                		name:"repayState"	//还款状态 0.未知1.正常2.M1 3.M2 4.M3 5.M4 6.M5 7.M6 8.M6+ 9.已还清
	                	},
	                	{
	                		name:"arrearsAmount"	//欠款金额
	                	},
	                	{
	                		name:"recTime"	//创建时间
	                	},
	                	{
	                		name:"recTimeExt"	//创建时间
	                	},
	                	{
	                		name:"borrowTypeExt"	//借款类型 0.未知1.个人信贷2.个人抵押3.企业信贷4.企业抵押
	                	},
	                	{
	                		name:"borrowStateExt"	//借款状态 0.未知1.拒贷2.批贷已放款3.批贷未放款4.借款人放弃申请5.审核中6.待放款
	                	},
	                	{
	                		name:"borrowAmountExt"	//借款金额 0.未知 1.[0,2], 2.[2,4], 3.[4,6], 4.[6,8] ........
	                	},
	                	{
	                		name:"contractDateExt"	//合同日期
	                	},
	                	{
	                		name:"repayStateExt"	//还款状态 0.未知1.正常2.M1 3.M2 4.M3 5.M4 6.M5 7.M6 8.M6+ 9.已还清
	                	},
	                	{
	                		name:"arrearsAmountExt"	//欠款金额
	                	}
                	],
                    listeners :
                    {
                        load : function (store, records, successful, eOpts)
                        {
                            for (index in records)
                            {
                            	 records[index].data.recTimeExt = Ext.Date.format(new Date(records[index].data.recTime), "Y-m-d");
                            	 records[index].data.contractDateExt = Ext.Date.format(new Date(records[index].data.contractDate), "Y-m-d");
                            	 if(records[index].data.arrearsAmount!=null&&records[index].data.arrearsAmount!=0){
                            		 records[index].data.arrearsAmountExt=records[index].data.arrearsAmount/100000;
                            	 }else{
                            		 records[index].data.arrearsAmountExt=0;
                            	 }
                            	 switch(records[index].data.repayState){//还款状态 0.未知1.正常2.M1 3.M2 4.M3 5.M4 6.M5 7.M6 8.M6+ 9.已还清
	 	                        	case 0:
	 	                        		records[index].data.repayStateExt="未知";
	 	                            	break;
	 	                        	case 1:
	 	                        		records[index].data.repayStateExt="正常";
	 	                            	break;
	 	                            case 2:
	 	                            	records[index].data.repayStateExt="M1";
	 	                            	break;
	 	                            case 3:
	 	                            	records[index].data.repayStateExt="M2";
	 	                            	break;
	 	                            case 4:
	 		                            records[index].data.repayStateExt="M3";
	 	                            	break;
	 	                            case 5:
	 		                            records[index].data.repayStateExt="M4";
	 	                            	break;
	 	                            case 6:
	 		                            records[index].data.repayStateExt="M5";
	 	                            	break;
	 	                            case 7:
	 		                            records[index].data.repayStateExt="M6";
	 	                            	break;
	 	                            case 8:
	 		                            records[index].data.repayStateExt="M6+";
	 	                            	break;
	 	                            case 9:
	 		                            records[index].data.repayStateExt="已还清";
	 	                            	break;
	 	                        }
                            	 switch(records[index].data.borrowType){
	 	                        	case 1:
	 	                        		records[index].data.borrowTypeExt="个人信贷";
	 	                            	break;
	 	                            case 2:
	 	                            	records[index].data.borrowTypeExt="个人抵押";
	 	                            	break;
	 	                            case 3:
	 	                            	records[index].data.borrowTypeExt="企业信贷";
	 	                            	break;
	 	                            case 4:
	 		                            records[index].data.borrowTypeExt="企业抵押";
	 	                            	break;
	 	                        	case 0:
	 	                        		records[index].data.borrowTypeExt="未知";
	 	                            	break;
	 	                        }
                            	 switch(records[index].data.borrowState){
	 	                        	case 1:
	 	                        		records[index].data.borrowStateExt="拒贷";
	 	                            	break;
	 	                            case 2:
	 	                            	records[index].data.borrowStateExt="批贷已放款";
	 	                            	break;
	 	                            case 4:
	 		                            records[index].data.borrowStateExt="借款人放弃申请";
	 	                            	break;
	 	                            case 5:
	 		                            records[index].data.borrowStateExt="审核中";
	 	                            	break;
	 	                            case 6:
	 		                            records[index].data.borrowStateExt="待放款";
	 	                            	break;
	 	                        	case 0:
	 	                        		records[index].data.borrowStateExt="未知";
	 	                            	break;
	 	                        }
                            	switch(records[index].data.borrowAmount){
	 	                        	case -1:
	 	                        		records[index].data.borrowAmountExt="8千-1万";
	 	                            	break;
	 	                            case -2:
	 	                            	records[index].data.borrowAmountExt="6-8千";
	 	                            	break;
	 	                            case -3:
	 	                            	records[index].data.borrowAmountExt="4-6千";
	 	                            	break;
	 	                            case -4:
	 		                            records[index].data.borrowAmountExt="3-4千";
	 	                            	break;
	 	                            case -5:
	 		                            records[index].data.borrowAmountExt="2-3千";
	 	                            	break;
	 	                            case -6:
	 		                            records[index].data.borrowAmountExt="1-2千";
	 	                            	break;
	 	                            case -7:
	 		                            records[index].data.borrowAmountExt="1千以下";
	 	                            	break;
	 	                        	case 0:
	 	                        		records[index].data.borrowAmountExt="未知";
	 	                            	break;
	 	                        	case 1:
	 	                        		records[index].data.borrowAmountExt="1万-2万";
	 	                            	break;
	 	                            default:
	 	                            	records[index].data.borrowAmountExt=(records[index].data.borrowAmount*2-2)+"-"+(records[index].data.borrowAmount*2)+"万"
	 	                            	break;
                            	}
                            }
                            var grid = me.down("gridpanel");
                            grid.reconfigure(store);
                        }
                    },
                    proxy : new Ext.data.proxy.Ajax(
                    {
                        url : 'testManage/queryJdDataLJP.do',
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
        
        Ext.applyIf(me, {
            items: [
                    {
                        xtype : 'gridpanel',
                        store : me.store,
                        itemId : 'CsglGroupGrid',
                        selType : 'rowmodel',
                        
                        columns : [
                               	{
                            		xtype: 'gridcolumn',
                            		text: '姓名',
                            		dataIndex:"realName"
                            	},
                            	{
                            		xtype: 'gridcolumn',
                            		text: '身份证号',
                            		dataIndex:"idCard",
                            		width:200
                            	},
                            	{
                            		xtype: 'gridcolumn',
                            		text: '公司编码',
                            		dataIndex:"companyCode",
                            		width:200
                            	},
                            	{
                            		xtype: 'gridcolumn',
                            		text: '借款类型',
                            		dataIndex:"borrowTypeExt"
                            	},
                            	{
                            		xtype: 'gridcolumn',
                            		text: '借款状态',
                            		dataIndex:"borrowStateExt"
                            	},
                            	{
                            		xtype: 'gridcolumn',
                            		text: '借款金额',
                            		dataIndex:"borrowAmountExt"
                            	},
                            	{
                            		xtype: 'gridcolumn',
                            		text: '合同日期',
                            		dataIndex:"contractDateExt"
                            	},
                            	{
                            		xtype: 'gridcolumn',
                            		text: '批贷期数',
                            		dataIndex:"loanPeriod"
                            	},
                            	{
                            		xtype: 'gridcolumn',
                            		text: '还款状态',
                            		dataIndex:"repayStateExt"
                            	},
                            	{
                            		xtype: 'gridcolumn',
                            		text: '欠款金额',
                            		dataIndex:"arrearsAmountExt"
                            	},
                            	{
                            		xtype: 'gridcolumn',
                            		text: '创建时间',
                            		dataIndex:"recTimeExt"
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
