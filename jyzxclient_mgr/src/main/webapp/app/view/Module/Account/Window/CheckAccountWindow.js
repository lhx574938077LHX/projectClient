Ext.define('App.view.Module.Account.Window.CheckAccountWindow', {
    extend: 'Ext.window.Window',

    autoShow: true,
    height: 700,
    width: 1200,
    id:'checkAccountWindow',
    title: '查询信息查看',
    detailId: null,
    pwin : null,
    layout : 'fit',
    store : null,
    autoScroll: true,
    seTrxNo:null,
   
    
    search:function(me)
    {
    	var me = this;
    	var seTrxNo = me.down("[name=seTrxNo]").getValue();
    	
        me.store.getProxy().url = "account/checkAccount.do";
        me.store.reload(
        {
            params :
            {
            	trxNo : seTrxNo,
            	id : me.detailId
            }
        }
        );   
    },
    
    dockedItems: [
          {
              xtype: 'toolbar',
              flex: 1,
              dock: 'top',
              items: [
                  {
                      xtype: 'textfield',
                      fieldLabel: '查询编码',
                      labelAlign: 'right',
					  name : 'seTrxNo',
					  labelWidth : 70
                  },
				  {
				      xtype : 'button',
				      text : '查询',
				      iconCls : 'icon635_16x16',
				      handler : function ()
				      {
				    	  var me = this.up("[id=checkAccountWindow]");
					      me.search();
				      }
				}
              ]
          }
      ],
      buttons : [
                 {
                     xtype : 'button',
                     iconCls : 'icon937_16x16',
                     text : '关闭',
                     handler : function ()
                     {
                         var me = this.up("[id=checkAccountWindow]");
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
                        		name:"searchTrxNo"	//查询流水号
                        	},
                        	{
                        		name:"realName"	//姓名
                        	},
                        	{
                        		name:"idCard"	//身份证号
                        	},
                        	{
                        		name:"reqCompanyId"	//请求公司ID
                        	},
                        	{
                        		name:"reqCompanyCode"	//公司编码
                        	},
                        	{
                        		name:"state"	//异步状态 0.未知1.待查询 2.查询中 3.待反馈结果 4.反馈次数上限 5.回调上报已完成6.非回调上报完成
                        	},
                        	{
                        		name:"syncState"	//同步状态 0.未知1.查询中 2.已完成3.需重复报送
                        	},
                        	{
                        		name:"searchType"	//查询类型 0.旧异步接口查询1.网站查询2.网站批量查询3.快速查询接口4.新异步接口查询
                        	},
                        	{
                        		name:"stateExt"	//异步状态 0.未知1.待查询 2.查询中 3.待反馈结果 4.反馈次数上限 5.回调上报已完成6.非回调上报完成
                        	},
                        	{
                        		name:"syncStateExt"	//同步状态 0.未知1.查询中 2.已完成3.需重复报送
                        	},
                        	{
                        		name:"searchTypeExt"	//查询类型 0.旧异步接口查询1.网站查询2.网站批量查询3.快速查询接口4.新异步接口查询
                        	},
                        	{
                        		name:"recTime"	//创建时间
                        	},
                        	{
                        		name:"endTime"	//完成时间
                        	},
                        	{
                        		name:"recTimeExt"	//创建时间
                        	},
                        	{
                        		name:"endTimeExt"	//完成时间
                        	}
                         ],
        			listeners : 
    				{
                        load : function (store, records, successful, eOpts)
                        {
                            for (index in records)
                            {
                            	 records[index].data.recTimeExt = Ext.Date.format(new Date(records[index].data.recTime), "Y-m-d");
                            	 records[index].data.endTimeExt = Ext.Date.format(new Date(records[index].data.endTime), "Y-m-d");
                            	 switch (records[index].data.state) {
                            	 	case 0:
	 									records[index].data.stateExt="未知";
	 									break;
	 								case 1:
	 									records[index].data.stateExt="待查询";
	 									break;
	 								case 2:
	 									records[index].data.stateExt="查询中";
	 									break;
	 								case 3:
	 									records[index].data.stateExt="待反馈结果";
	 									break;
	 								case 4:
	 									records[index].data.stateExt="反馈次数上限";
	 									break;
	 								case 5:
	 									records[index].data.stateExt="回调上报已完成";
	 									break;
	 								case 6:
	 									records[index].data.stateExt="非回调上报完成";
	 									break;
	 							}
                            	 switch (records[index].data.syncState) {
    								case 0:
    									records[index].data.syncStateExt="未知";
    									break;
    								case 1:
    									records[index].data.syncStateExt="查询中";
    									break;
    								case 2:
    									records[index].data.syncStateExt="已完成";
    									break;
    								case 3:
    									records[index].data.syncStateExt="需重复报送";
    									break;
    							}
                            	 switch (records[index].data.searchType) {
	 								case 0:
	 									records[index].data.searchTypeExt="旧异步接口查询";
	 									break;
	 								case 1:
	 									records[index].data.searchTypeExt="网站查询";
	 									break;
	 								case 2:
	 									records[index].data.searchTypeExt="网站批量查询";
	 									break;
	 								case 3:
	 									records[index].data.searchTypeExt="快速查询接口";
	 									break;
	 								case 4:
	 									records[index].data.searchTypeExt="新异步接口查询";
	 									break;
	 							}

                            }
                            var grid = me.down("gridpanel");
                            grid.reconfigure(store);
                        }
    				},
                    proxy : new Ext.data.proxy.Ajax(
                            {
                                url : 'account/checkAccount.do?id='+me.detailId,
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
                    itemId : 'CheckGroupGrid',
                    loadMask : true, 
                    listeners:{
                    	  itemdblclick:function(dataview, record, item, index, e){
                    		  console.log(record.data.searchTrxNo);
		                      var trxNo=record.data.searchTrxNo
		                      var searchLogId = record.data.id;
                              var window = me.add(
                               {
                                   constrainHeader : true,
                                   autoDestroy : false,
                                   closeAction : 'destroy',
                                   modal : true,
                                   seTrxNo:trxNo,
                                   fatherId:searchLogId,
                                   xtype : 'showSearchResultWindow',
                                   pwin : me
                             });
		                       window.show();	
                    	  },
                    	  width:400,height:300
                    	},
                    columns: [
                            new Ext.grid.RowNumberer(),//加序号
                        	{
                        		xtype: 'gridcolumn',
                        		text: '查询流水号',
                        		dataIndex:"searchTrxNo",
                        		width:250
                        	},
                        	{
                        		xtype: 'gridcolumn',
                        		text: '姓名',
                        		dataIndex:"realName"
                        	},
                        	{
                        		xtype: 'gridcolumn',
                        		text: '身份证号',
                        		dataIndex:"idCard",
                        		width : 200
                        	},
                        	{
                        		xtype: 'gridcolumn',
                        		text: '异步状态',
                        		dataIndex:"stateExt"
                        	},
                        	{
                        		xtype: 'gridcolumn',
                        		text: '同步状态 ',
                        		dataIndex:"syncStateExt"
                        	},
                        	{
                        		xtype: 'gridcolumn',
                        		text: '查询类型',
                        		dataIndex:"searchTypeExt"
                        	},
                        	{
                        		xtype: 'gridcolumn',
                        		text: '创建时间',
                        		dataIndex:"recTimeExt"
                        	},
                        	{
                        		xtype: 'gridcolumn',
                        		text: '完成时间',
                        		dataIndex:"endTimeExt"
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