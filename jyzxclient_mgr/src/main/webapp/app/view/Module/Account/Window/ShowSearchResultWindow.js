Ext.define('App.view.Module.Account.Window.ShowSearchResultWindow', {
    extend: 'Ext.window.Window',

    autoShow: true,
    height: 700,
    width: 900,
    id:'showSearchResultWindow',
    title: '查询内容查看',
    pwin : null,
    layout : 'fit',
    store : null,
    autoScroll: true,
    fatherId:null,
    seTrxNo:null,
    
      buttons : [
 				{
				    xtype : 'button',
				    iconCls : 'icon757_16x16',
				    text : '重新发送',
				    handler : function ()
				    {
				        var me = this.up("[id=showSearchResultWindow]");
				        console.log(me.fatherId);
				        jQuery.ajax(
	                    {
	                    	  type : "POST",
	                    	  url : 'account/reSendSearchResult.do', //表单提交页面
	                    	  dataType : "text",
	                          cache : false,
	                          async : false,
	                          data : {
	                        	  id:me.fatherId
	                          },
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
	                            	  me.store.removeAll();
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
	                    })
				    }
				},
                 {
                     xtype : 'button',
                     iconCls : 'icon937_16x16',
                     text : '关闭',
                     handler : function ()
                     {
                         var me = this.up("[id=showSearchResultWindow]");
                         me.store.removeAll();
                         me.close();
                     }
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
                        		name:"searchLogId"	//查询ID
                        	},
                        	{
                        		name:"searchTrxNo"	//查询流水号
                        	},
                        	{
                        		name:"reqCompanyId"	//请求公司ID
                        	},
                        	{
                        		name:"reqCompanyCode"	//请求公司编码
                        	},
                        	{
                        		name:"rspCompanyId"	//响应公司ID
                        	},
                        	{
                        		name:"rspCompanyCode"	//响应公司编码
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
                        		name:"resultType"	//记录类型 0.常规记录1.快速查询记录同步2.快速查询记录异步
                        	},
                        	{
                        		name:"resultTypeExt"	//记录类型 0.常规记录1.快速查询记录同步2.快速查询记录异步
                        	},
                        	{
                        		name:"dataType"	//记录类型 0.常规记录1.JD_DATA2.SL_DATA
                        	},
                        	{
                        		name:"dataTypeExt"	//记录类型 0.常规记录1.JD_DATA2.SL_DATA
                        	}
                              ],
        			listeners : 
    				{
                        load : function (store, records, successful, eOpts)
                        {
                        	for (index in records)
                            {
	                        	 records[index].data.recTimeExt = Ext.Date.format(new Date(records[index].data.recTime), "Y-m-d");
	                        	 switch (records[index].data.resultType) {
									case 0:
										records[index].data.resultTypeExt="常规记录";
										break;
									case 1:
										records[index].data.resultTypeExt="快速查询记录同步";
										break;
									case 2:
										records[index].data.resultTypeExt="快速查询记录异步";
										break;
								}
	                        	 switch (records[index].data.dataType) {
									case 0:
										records[index].data.dataTypeExt="常规记录";
										break;
									case 1:
										records[index].data.dataTypeExt="JD_DATA";
										break;
									case 2:
										records[index].data.dataTypeExt="SL_DATA";
										break;
	                        	}
                            }
                            var grid = me.down("gridpanel");
                            grid.reconfigure(store);
                        }
    				},
                    proxy : new Ext.data.proxy.Ajax(
                            {
                                url : 'account/selectSearchResult.do?trxNo='+me.seTrxNo,
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
                });
        
        Ext.applyIf(me, {
        	tools:[
   				{
   					type: 'maximize'
   				},
   				{
   					type: 'restore'
   				}
           	],
            items: [
                {
                    xtype: 'gridpanel',
                    store : me.store,
                    selType : 'rowmodel',
                    itemId : 'ShowSearchResultGroupGrid',
                    columns: [
                    new Ext.grid.RowNumberer(),//加序号
                	{
                		xtype: 'gridcolumn',
                		text: '查询流水号',
                		dataIndex:"searchTrxNo"
                	},
                	{
                		xtype: 'gridcolumn',
                		text: '借款类型 ',
                		dataIndex:"borrowType"
                	},
                	{
                		xtype: 'gridcolumn',
                		text: '借款状态',
                		dataIndex:"borrowState"
                	},
                	{
                		xtype: 'gridcolumn',
                		text: '借款金额 ',
                		dataIndex:"borrowAmount"
                	},
                	{
                		xtype: 'gridcolumn',
                		text: '合同日期',
                		dataIndex:"contractDate"
                	},
                	{
                		xtype: 'gridcolumn',
                		text: '批贷期数',
                		dataIndex:"loanPeriod"
                	},
                	{
                		xtype: 'gridcolumn',
                		text: '还款状态',
                		dataIndex:"repayState"
                	},
                	{
                		xtype: 'gridcolumn',
                		text: '欠款金额',
                		dataIndex:"arrearsAmount"
                	},
                	{
                		xtype: 'gridcolumn',
                		text: '创建时间',
                		dataIndex:"recTimeExt"
                	},
                	{
                		xtype: 'gridcolumn',
                		text: '记录类型 ',
                		dataIndex:"resultTypeExt"
                	},
                	{
                		xtype: 'gridcolumn',
                		text: '记录类型 ',
                		dataIndex:"dataTypeExt"
                	}]
                }
            ]
        });

        me.callParent(arguments);
    }

});