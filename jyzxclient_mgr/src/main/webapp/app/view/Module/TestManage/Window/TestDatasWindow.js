Ext.define('App.view.Module.TestManage.Window.TestDatasWindow',
{
    extend : 'Ext.window.Window',
    
    autoShow : true,
    width : 600,
    layout : 'fit',
    title : '测试数据',
    
    buttons : [
        {
            xtype : 'button',
            iconCls : 'icon962_16x16',
            text : '测试',
            handler : function ()
            {
                var me = this.up("window");
                var form = me.down("form");
                if (form.isValid())
                {
                    var filePath = me.down("[name=filePath]").getValue();
                    var filePaths = filePath.split(".");
                    if(filePaths[filePaths.length-1]!='xlsx'&& filePaths[filePaths.length-1]!='xls'){
                        Ext.toast(
                        {
                            html : "请上传excle表格",
                            closable : false,
                            align : 't',
                            slideInDuration : 400,
                            minWidth : 400
                        });
                        return;
                    }
                    
                    var fileNames = filePath.split("\\");
                    var fileName = fileNames[fileNames.length-1]
                    
                    var fvalues = form.getValues();
                    var companyCode = fvalues.companyCode;
                    var sign = fvalues.sign;
                    form.submit({
                    	url:'testManage/fileUpload.do',
                    	method: 'post',
                    	params:{
                    		companyCode:companyCode,
                    		sign:sign
                    	},
                    	waitMsg: '数据测试中，请等待...',
                    	success: function (form, action, response) {
                    		 json = eval("(" + action.response.responseText + ")");
                    		 Ext.toast(
                             {
                                 html : '测试成功',
                                 closable : false,
                                 align : 't',
                                 slideInDuration : 400,
                                 minWidth : 400
                             }
                             );
                    		 
                    		 var url = "testManage/saveFile.do?path="+json.data+"&filename="+fileName;
                    		 window.location=url;
                             me.close();
                         },
	                     failure: function (form, action,response) {
	                    	 Ext.toast(
                             {
                                 html : '测试失败',
                                 closable : false,
                                 align : 't',
                                 slideInDuration : 400,
                                 minWidth : 400
                             }
                             );
	                     }
                    })
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
		                        xtype: 'textfield',  
		                        fieldLabel: '公司编码',  
                                labelAlign : 'right',
                                labelWidth : 70,
                                flex : 1,
		                        name:'companyCode',
		                        itemId:'companyCode',
                                validator : function (value)
                                {
                                    if (value == null || value.trim() == "")
                                    {
                                        return "不可以为空！";
                                    }
                                    return true;
                                }
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
		                        xtype: 'textfield',  
		                        fieldLabel: '公司签名',  
                                labelAlign : 'right',
                                labelWidth : 70,
                                flex : 1,
		                        name:'sign',
		                        itemId:'sign',
                                validator : function (value)
                                {
                                    if (value == null || value.trim() == "")
                                    {
                                        return "不可以为空！";
                                    }
                                    return true;
                                }
		                    }
					    ]
					},
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
		                        xtype: 'fileuploadfield',  
		                        emptyText: '请先选择一个文件',  
                                labelWidth : 70,
                                labelAlign : 'right',
		                        fieldLabel: '文件',  
                                flex : 1,
		                        buttonText: '选择文件' ,
		                        name:'filePath',
		                        itemId:'filePath',
                                validator : function (value)
                                {
                                    if (value == null || value.trim() == "")
                                    {
                                        return "不可以为空！";
                                    }
                                    return true;
                                }
		                    }
					   ]
					}
//					,{
//					    xtype : 'container',
//                        margin : '5 30 8 0',
//                        layout :
//                        {
//                            type : 'hbox',
//                            align : 'stretch'
//                        },
//					    items : [ 					
//		                    {
//								xtype:'filefield',
//                                flex : 1,
//								fieldLabel:'上传图片',
//                                labelAlign : 'right',
//								name:'image',
//                                labelWidth : 70,
//								id:'image',
//								buttonText:'选择图片',
//								buttonConfig:{iconCls:'upload'},
//								listeners:{
//									change:function(btn, value, eOpts){
//									var img_reg = /\.([jJ][pP][gG]){1}$|\.([jJ][pP][eE][gG]){1}$|\.([gG][iI][fF]){1}$|\.([pP][nN][gG]){1}$|\.([bB][mM][pP]){1}$/;
//									if ( img_reg.test(value) ) {
//										var img = Ext.getCmp('img');
//										var file = btn.fileInputEl.dom.files[0];
//										var url = URL.createObjectURL(file);
//										img.setSrc(url);
//									} else {
//										Ext.Msg.alert('提示', '请选择图片类型的文件！');
//										return ;
//									}
//									}
//								}
//							}
//					   ]
//					},{
//					    xtype : 'container',
//                        margin : '5 30 8 10',
//                        layout :
//                        {
//                            type : 'hbox',
//                            align : 'stretch'
//                        },
//					    items : [         
//							{
//								xtype:'fieldset',
//								title:'图片预览',
//							    height:100,
//                                flex : 1,
//							    items:[
//							         {xtype:'image',id:'img'}
//							    ]
//							 }
//					    ]
//					}
                    ]
                }
            ]
        }
        );
        
        me.callParent(arguments);
    }
    
}
);
