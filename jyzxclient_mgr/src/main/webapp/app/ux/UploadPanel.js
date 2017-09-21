Ext.define('App.ux.UploadPanel', {
    extend: 'Ext.panel.Panel',

    layout: 'fit',
    swfupload: null, //swfupload对象
    swfconfig: null,
    postParams: {}, //post参数
    uploadUrl: 'quitController/uploadQuitAudit.do', //文件上传处理地址
    flashUrl: 'Public/swfupload/swfupload.swf', //swflash的swf文件地址
    flash9Url: 'Public/swfupload/swfupload_fp9.swf', //flash9的swf文件地址
    fileSize: 1024 * 50 * 102400,//上传文件体积上限，单位MB
    filePostName: '',
    fileTypes: '*.*',//允许上传的文件类型 
    fileDesc: 'All Files',//文件类型描述
    fileUploadLimit: '0',//限定用户一次性最多上传多少个文件，在上传过程中，该数字会累加，如果设置为“0”，则表示没有限制 
    fileQueueLimit: '100',//上传队列数量限制，该项通常不需设置，会根据file_upload_limit自动赋值   
    debug: false,

    dockedItems: [
        {
            xtype: 'toolbar',
            dock: 'top',
            items: [
                {
                    xtype: 'button',
                    itemId: 'addFileBtn',
                    iconCls: 'icon738_16x16',
                    text: '选取文件'
                },
                {
                    xtype: 'button',
                    text: '开始上传',
                    iconCls: 'icon760_16x16',
                    handler: function ()
                    {
                        var me = this.up("panel");
                        if (me.swfupload && me.store.getCount() > 0) {
                            if (me.swfupload.getStats().files_queued > 0) {
                                me.swfupload.stopped = false;
                                me.swfupload.startUpload();
                            }
                        }
                    }
                },
                {
                    xtype: 'button',
                    text: '全部取消',
                    iconCls: 'icon751_16x16',
                    handler: function () {
                        var me = this.up("panel");
                        if (me.swfupload) {
                            me.swfupload.cancelQueue();
                            me.store.removeAll();
                            var stats = me.swfupload.getStats();
                            var label = me.down("[itemId=queueLabel]");
                            label.setText(label.text = "当前队列中文件个数:" + stats.files_queued);
                        }
                    }
                },
                {
                    xtype: 'label',
                    itemId: "queueLabel",
                    text: '当前队列中文件个数:0',
                    margins: '0 0 0 10'
                }
            ]
        },
        {
            xtype: 'toolbar',
            dock: 'bottom',
            items: [
                {
                    xtype: 'label',
                    text: '上传进度:'
                },
                {
                    xtype: 'progressbar',
                    flex: 1,
                    name: 'progressBar'
                },
                {
                    xtype: 'label',
                    text: '平均速度:'
                },
                {
                    xtype: 'label',
                    name: 'currentSpeed',
                    text: '0 kb/s'
                },
                {
                    xtype: 'label',
                    text: '剩余时间:'
                },
                {
                    xtype: 'label',
                    name: 'timeRemaining',
                    text: '0s'
                }
            ]
        }
    ],

    store: Ext.create("Ext.data.Store", {
        fields: [
            {
                name: "id"
            },
            {
                name: "FileName"
            },
            {
                name: "FileType"
            },
            {
                name: "FileSize"
            },
            {
                name: "FileProgress"
            },
            {
                name: "FileState"
            }
        ]
    }),

    formatFileState: function (n) {//文件状态
        switch (n) {
            case SWFUpload.FILE_STATUS.QUEUED:
                return '已加入队列';
            case SWFUpload.FILE_STATUS.IN_PROGRESS:
                return '正在上传';
            case SWFUpload.FILE_STATUS.ERROR:
                return '上传失败';
            case SWFUpload.FILE_STATUS.COMPLETE:
                return '上传成功';
            case SWFUpload.FILE_STATUS.CANCELLED:
                return '取消上传';
            default:
                return n;
        }
    },

    onFileError: function (file, errorCode, msg) {
        var msg = "";
        switch (errorCode) {
            case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
                msg = '待上传文件列表数量超限，不能选择！';
                break;
            case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
                msg = '文件太大，不能选择！文件大小不能超过' + this.settings.file_size_limit / 1024 + 'MB';
                break;
            case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
                msg = '该文件大小为0，不能选择！';
                break;
            case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
                msg = '该文件类型不可以上传！';
                break;
            case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
                msg = "上传已经停止";
                break;
            case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
                msg = "所有文件已经取消！";
                break;
            default:
                msg = "未知错误!";
                break;
        }

        return msg;
    },

    uploadProgress: function (file, bytesComplete, totalBytes) {
        //处理进度条
        var me = this.settings.custom_settings.scope_handler;

        for(var i=0;i<me.store.getCount();i++){
            var record =me.store.getAt(i);
            if(record.get('id')==file.id){
                record.set('FileProgress', file.percentUploaded / 100);
                record.set('FileState', file.filestatus);
                record.commit();
            }
        }

        //更新进度条
        var pb = me.down("[name=progressBar]");
        pb.updateProgress(file.percentUploaded/100,SWFUpload.speed.formatPercent(file.percentUploaded),true);

        //更新当前速度
        var speed = me.down("[name=currentSpeed]"); 
        var speedNum=Math.ceil(file.averageSpeed/8/1024);
        var unit=speedNum/1024<0?"KB/s":"MB/s";
        var speedValue=speedNum/1024<0?speedNum:speedNum/1024;
        speedValue=Math.ceil(speedValue);
        speed.setText(speedValue + unit);
        
        //更新剩余时间
        var timeRemaining = me.down("[name=timeRemaining]");
        timeRemaining.setText(SWFUpload.speed.formatTime(file.timeRemaining));
    },

    uploadComplete: function (file) {
        var me = this.settings.custom_settings.scope_handler;

        var model = me.store.getById(file.id);
        if (model)
        {
            model.set("FileState", file.filestatus);
            model.commit();
            var stats = this.getStats();
            var queueLabel = me.down("[itemId=queueLabel]");
            queueLabel.setText("当前队列中文件个数: " + stats.files_queued);
        }
    },
    
    onQueued: function (file) {
        var me = this.settings.custom_settings.scope_handler;
        var stats = this.getStats();
        var queueLabel = me.down("[itemId=queueLabel]");
        queueLabel.setText("当前队列中文件个数: " + stats.files_queued);

        me.store.add({
            id: file.id,
            FileName: file.name,
            FileSize: file.size,
            FileType: file.type,
            FileState: file.filestatus,
            FileProgress: 0
        });
    },

    uploadStart: function (file) {
    },

    uploadSuccess: function (file) {
    },
    
    initComponent: function () {
        var me = this;
        
        if (!me.swfconfig)
        {
            me.swfconfig = {
                upload_url: this.uploadUrl,
                flash_url: this.flashUrl,
                flash9_url: me.flash9Url,
                file_size_limit: this.fileSize || (1024 * 50),//上传文件体积上限，单位MB
                file_post_name: this.filePostName,
                file_types: this.fileTypes || "*.*",  //允许上传的文件类型 
                file_types_description: this.fileDesc || "All Files",  //文件类型描述
                file_upload_limit: this.fileUploadLimit || "0",  //限定用户一次性最多上传多少个文件，在上传过程中，该数字会累加，如果设置为“0”，则表示没有限制 
                file_queue_limit: this.fileQueueLimit || "10",//上传队列数量限制，该项通常不需设置，会根据file_upload_limit自动赋值              
                post_params: Ext.apply({ASPSESSID:sid},this.postParams),
                use_query_string: true,
                debug: this.debug,
                button_cursor: SWFUpload.CURSOR.HAND,
                button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
                custom_settings: {//自定义参数
                    scope_handler: this
                },
                swfupload_loaded_handler: function () {
                    //console.log("swf组件成功初始化");
                },// 当Flash控件成功加载后触发的事件处理函数
                file_dialog_start_handler: function () { },// 当文件选取对话框弹出前出发的事件处理函数
                file_dialog_complete_handler: function () { },//当文件选取对话框关闭后触发的事件处理
                upload_start_handler: this.uploadStart,// 开始上传文件前触发的事件处理函数
                upload_success_handler: this.uploadSuccess,// 文件上传成功后触发的事件处理函数 
                upload_progress_handler: this.uploadProgress,
                upload_complete_handler: this.uploadComplete,
                upload_error_handler: this.onFileError,
                file_queue_error_handler: this.onFileError,
                file_queued_handler: this.onQueued
            };
        }

        Ext.applyIf(me, {
            listeners: {
                'afterrender': function () {
                    var addBtn = me.down("[itemId=addFileBtn]");
                    var em = addBtn.getEl().child('em');

                    if (em == null) {
                        em = Ext.get(addBtn.getId() + '-btnWrap');
                    }

                    var placeHolderId = Ext.id();

                    em.setStyle({
                        position: 'relative',
                        display: 'block'
                    });

                    em.createChild({
                        tag: 'div',
                        id: placeHolderId
                    });

                    this.swfupload = new SWFUpload(Ext.apply(this.swfconfig,{
                        button_width : em.getWidth(),
                        button_height : em.getHeight(),
                        button_placeholder_id : placeHolderId
                    }));

                    this.swfupload.stopped = true;

                    Ext.get(this.swfupload.movieName).setStyle({
                        position: 'absolute',
                        top: 0,
                        left: -2
                    });
                }
            },
            items: [
                {
                    xtype: 'gridpanel',
                    store: me.store,
                    listeners: {
                        'itemdblclick': function (gridpanel,record, item, index, e, eOpts )
                        {
                            /*
                            me.swfupload.cancelUpload(record.data.id);
                            var stats = me.swfupload.getStats();
                            var queueLabel = me.down("[itemId=queueLabel]");
                            queueLabel.setText("当前队列中文件个数: " + stats.files_queued);
                            me.store.remove(record);
                            */
                        }
                    },
                    columns: [
                        {
                            xtype: 'rownumberer'
                        },
                        {
                            xtype: 'gridcolumn',
                            width: 200,
                            dataIndex: 'FileName',
                            text: '文件名'
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'FileType',
                            text: '类型'
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'FileSize',
                            text: '大小'
                        },
                        {
                            xtype: 'widgetcolumn',
                            dataIndex: 'FileProgress',
                            width: 120,
                            text: '进度',
                            widget: {
                                xtype: 'progressbarwidget',
                                animate: true,
                                textTpl: [
                                    '{percent:number("0")}%'
                                ]
                            }
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'FileState',
                            text: '状态',
                            renderer:this.formatFileState
                        },
                        {
                            xtype: 'actioncolumn',
                            text: '操作',
                            items: [
                                /*
                                {
                                    iconCls: 'icon1153_16x16',
                                    tooltip: '上传',
                                    handler: function (grid, rowIndex, colIndex) {
                                        var record = grid.getStore().getAt(rowIndex);

                                        if (me.swfupload) {
                                            me.swfupload.startUpload(record.data.id);
                                        } 
                                    }
                                },*/
                                {
                                    iconCls: 'icon347_16x16',
                                    tooltip: '移除',
                                    handler: function(grid, rowIndex, colIndex) {
                                        var record = grid.getStore().getAt(rowIndex);
                                        
                                        if (me.swfupload) {
                                            me.swfupload.cancelUpload(record.data.id);
                                            grid.getStore().remove(record);
                                            var stats = me.swfupload.getStats();
                                            var queueLabel = me.down("[itemId=queueLabel]");
                                            queueLabel.setText("当前队列中文件个数: " + stats.files_queued);
                                        } 
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