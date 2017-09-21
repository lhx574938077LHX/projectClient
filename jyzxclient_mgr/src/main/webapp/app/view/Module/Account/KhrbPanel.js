Ext.define('App.view.Module.Account.KhrbPanel', {
    extend: 'Ext.panel.Panel',

    closable: true,
    id: 'khrbPanel',
    title: '对接信息',
    layout: 'fit',
    autoScroll: true,
    pwin : null,
    store : null,
    
    searchs:function(me)
    {
    	var me = this;
    	var cpName = me.down("[name=cpName]").getValue();
    	var bdName = me.down("[name=bdName]").getValue();
    	var joinState = me.down("[name=joinState]").getValue();
    	
    	var startdate = me.down("[name=startdate]").getValue();
    	var enddate = me.down("[name=enddate]").getValue();
    	
    	if(startdate!=null){
    		startdate = Date.parse(me.down("[name=startdate]").getValue());
    	}else{
    		startdate = 0;	
    	}
    	if(enddate!=null){
    		enddate = Date.parse(me.down("[name=enddate]").getValue());
    	}else{
    		enddate = 0;
    	}
    	if(joinState==null){
    		joinState = -1;
    	}    	
    	console.log(startdate+"---"+enddate+"---"+joinState+"---"+bdName+"---"+cpName);
    	
        me.store.getProxy().url = "account/selectJoinCondition.do?startdate="+startdate+"&enddate="+enddate+"&joinState="+joinState+"&bdName="+bdName+"&cpName="+cpName;
        me.store.loadPage(1);
    },
    
    deriveDatas:function(me)
    {
    	var me = this;
    	var cpName = me.down("[name=cpName]").getValue();
    	var bdName = me.down("[name=bdName]").getValue();
    	var joinState = me.down("[name=joinState]").getValue();
    	
    	var startdate = me.down("[name=startdate]").getValue();
    	var enddate = me.down("[name=enddate]").getValue();
    	
    	if(startdate!=null){
    		startdate = Date.parse(me.down("[name=startdate]").getValue());
    	}else{
    		startdate = 0;	
    	}
    	if(enddate!=null){
    		enddate = Date.parse(me.down("[name=enddate]").getValue());
    	}else{
    		enddate = 0;
    	}
    	if(joinState==null){
    		joinState = -1;
    	}   
    	var fvalues = {
            	startdate : startdate,
            	enddate : enddate,
            	joinState : joinState,
            	bdName : bdName,
            	cpName : cpName
    	}
    	var url = "account/deriveDatas.do?startdate="+startdate+"&enddate="+enddate+"&joinState="+joinState+"&bdName="+bdName+"&cpName="+cpName;
    	window.open(url);
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
				    fieldLabel : '客户经理',
				    labelAlign : 'right',
				    name : 'bdName',
				    labelWidth : 70   
               },{
                   xtype : 'combobox',
                   fieldLabel : '对接状态',
                   labelAlign : 'right',
                   name : 'joinState',
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
                                    s_name: '开发联调',
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
            	   xtype : 'label',
            	   text: "最近更新时间:"
               },{
				    xtype : 'datefield',
				    labelAlign : 'right',
				    name : 'startdate',
				    format : 'Y-m-d',
				    labelWidth : 90
				},{
	            	   xtype : 'label',
	            	   text: "-"
	               },
				{
				    xtype : 'datefield',
				    labelAlign : 'right',
				    name : 'enddate',
				    format : 'Y-m-d',
				    labelWidth : 90
				},
				{
				    xtype : 'button',
				    text : '查询',
				    iconCls : 'icon635_16x16',
				    handler : function ()
				    {
				        var me = this.up("[id=khrbPanel]");
				        me.searchs();
				    }
				},
				{
				    xtype : 'button',
				    text : '导出数据',
				    iconCls : 'icon655_16x16',
				    handler : function ()
				    {
				        var me = this.up("[id=khrbPanel]");
				        me.deriveDatas(me);
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
								name:"companyId"	//请求公司ID
							},
							{
								name:"companyName"	//请求公司ID
							},
							{
								name:"startTime"	//开始时间
							},
							{
								name:"endTime"	//结束时间
							},
							{
								name:"startTimeExt"	//开始时间
							},
							{
								name:"endTimeExt"	//结束时间
							},
							{
								name:"joinState"	//对接状态 0.未开始1.排期2.对接中3.对接后暂停4.已完成
							},
							{
								name:"joinStateExt"	//对接状态 0.未开始1.排期2.对接中3.对接后暂停4.已完成
							},
							{
								name:"timeInformation"	//实时信息
							},
							{
								name:"remark"	//备注
							},
							{
								name:"bdName"	//客户经理
							},
							{
								name:"clientEmail"	//客户邮箱
							},
							{
								name:"updateTime"	//最近更新时间
							},
							{
								name:"updateTimeExt"	//最近更新时间
							},
							{
								name:"isP3001"	//是否开放被查询 0.不开放1.开放
							},
							{
								name:"isP3001Ext"	//是否开放被查询 0.不开放1.开放
							},
							{
								name:"recTime"	//创建时间
							},
							{
								name:"recTimeExt"	//创建时间
							}
							],
                listeners :
                {
                    load : function (store, records, successful, eOpts)
                    {
                        for (index in records)
                        {
                        	 records[index].data.recTimeExt = Ext.Date.format(new Date(records[index].data.recTime), "Y-m-d");
                        	 
                        	 if(records[index].data.startTime!=null){                       	 
                        		 records[index].data.startTimeExt = Ext.Date.format(new Date(records[index].data.startTime), "Y-m-d");
                        	 }                       	 
                        	 if(records[index].data.endTime!=null){
                        	 	records[index].data.endTimeExt = Ext.Date.format(new Date(records[index].data.endTime), "Y-m-d");
                        	 }
                        	 if(records[index].data.updateTime!=null){
                        		 records[index].data.updateTimeExt = Ext.Date.format(new Date(records[index].data.updateTime), "Y-m-d");
                        	 }
                        	 switch(records[index].data.joinState){
	                        	case 1:
	                        		records[index].data.joinStateExt="等待开发-排期";
	                            	break;
	                            case 2:
	                            	records[index].data.joinStateExt="对接联调";
	                            	break;
	                            case 3:
	                            	records[index].data.joinStateExt="等待上线-排期";
	                            	break;
	                            case 4:
		                            records[index].data.joinStateExt="系统上线";
	                            	break;
	                            case 5:
		                            records[index].data.joinStateExt="等待开发-终止";
	                            	break;
	                            case 6:
		                            records[index].data.joinStateExt="等待上线-终止";
	                            	break;
	                        	case 0:
	                        		records[index].data.joinStateExt="建群发文";
	                            	break;
	                        }
                            switch(records[index].data.isP3001){
	                        	case 1:
	                        		records[index].data.isP3001Ext="开放";
	                            	break;
	                        	case 0:
	                        		records[index].data.isP3001Ext="不开放";
	                            	break;
	                        }

                        }
                        var grid = me.down("gridpanel");
                        grid.reconfigure(store);
                    }
                },
                proxy : new Ext.data.proxy.Ajax(
                {
                    url : 'account/selectJoinCondition.do',
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
                    itemId : 'KhrbGroupGrid',
                    selType : 'rowmodel',
                    plugins : [
                               {
                                   ptype : 'rowexpander',
                                   rowBodyTpl : new Ext.XTemplate(
                                       '<table border="1" cellpadding="10" cellspacing="10" class="icefox_viewtable" style="width:700px;">' +
                                       '<tbody>' +
	                                      '<tr>' +
	                                   	'<td class="fieldtitle">公司名称</td>' +
	                                   	'<td>{companyName}</td>' +
	                                      '</tr>' +
	                                      '<tr>' +
	                                   	'<td class="fieldtitle">开始时间</td>' +
	                                   	'<td>{startTimeExt}</td>' +
	                                      '</tr>' +
	                                      '<tr>' +
	                                   	'<td class="fieldtitle">结束时间</td>' +
	                                   	'<td>{endTimeExt}</td>' +
	                                      '</tr>' +
	                                      '<tr>' +
	                                   	'<td class="fieldtitle">对接状态 </td>' +
	                                   	'<td>{joinStateExt}</td>' +
	                                      '</tr>' +
	                                      '<tr>' +
	                                   	'<td class="fieldtitle">实时信息</td>' +
	                                   	'<td>{timeInformation}</td>' +
	                                      '</tr>' +
	                                      '<tr>' +
	                                   	'<td class="fieldtitle">备注</td>' +
	                                   	'<td>{remark}</td>' +
	                                      '</tr>' +
	                                      '<tr>' +
	                                   	'<td class="fieldtitle">客户经理</td>' +
	                                   	'<td>{bdName}</td>' +
	                                      '</tr>' +
	                                      '<tr>' +
	                                   	'<td class="fieldtitle">客户邮箱</td>' +
	                                   	'<td>{clientEmail}</td>' +
	                                      '</tr>' +
	                                      '<tr>' +
	                                   	'<td class="fieldtitle">最近更新时间</td>' +
	                                   	'<td>{updateTimeExt}</td>' +
	                                      '</tr>' +
	                                      '<tr>' +
	                                   	'<td class="fieldtitle">是否开放被查询 </td>' +
	                                   	'<td>{isP3001Ext}</td>' +
	                                      '</tr>' +
	                                      '<tr>' +
	                                   	'<td class="fieldtitle">创建时间</td>' +
	                                   	'<td>{recTimeExt}</td>' +
	                                      '</tr>' +
                                    '</tbody>' +
                                 '</table>')
                               }
                           ],
                    columns : [
                        	{
                        		xtype: 'gridcolumn',
                        		text: '公司名称',
                        		dataIndex:"companyName"
                        	},
                        	{
                        		xtype: 'gridcolumn',
                        		text: '开始时间',
                        		dataIndex:"startTimeExt"
                        	},
                        	{
                        		xtype: 'gridcolumn',
                        		text: '结束时间',
                        		dataIndex:"endTimeExt"
                        	},
                        	{
                        		xtype: 'gridcolumn',
                        		text: '对接状态 ',
                        		dataIndex:"joinStateExt"
                        	},
                        	{
                        		xtype: 'gridcolumn',
                        		text: '客户经理',
                        		dataIndex:"bdName"
                        	},
                        	{
                        		xtype: 'gridcolumn',
                        		text: '客户邮箱',
                        		dataIndex:"clientEmail",
                        		width:250
                        	},
                        	{
                        		xtype: 'gridcolumn',
                        		text: '最近更新时间',
                        		dataIndex:"updateTimeExt"
                        	},
                        	{
                        		xtype: 'gridcolumn',
                        		text: '是否开放被查询 ',
                        		dataIndex:"isP3001Ext"
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
