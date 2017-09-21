Ext.define('App.ux.PictureViewPanel', {
    extend: 'Ext.panel.Panel',

    layout: 'card',
    store: null,
    pwin: null,
    params: {
        queryUrl: '',
        allowUpload: true,
        uploadTitle: '上传文件',
        uploadIconCls: 'icon519_16x16',
        uploadFileTypes: '*.*',
        uploadFileDesc: '全部文件',
        uploadParam: {
            datatype: 0,
            oid:0
        }
    },
    
    rpic: null, //iviewer 对象
    images: null, //当前Store中的全部图片
    curpicindex: 0, //当前图片索引

    uploadFunc: function (me) {
        if (me.pwin) {
            var win = me.pwin.add({
                xtype: 'window',
                title: me.params.uploadTitle,
                width: 800,
                height: 600,
                constrain: true,
                autoDestroy: false,
                closeAction: 'hide',
                layout: 'fit',
                modal: true,
                iconCls: me.params.uploadIconCls,
                pwin: me.pwin,
                items: [
                    {
                        xtype: 'uploadPanel',
                        postParams: me.params.uploadParam,
                        fileTypes: me.params.uploadFileTypes,
                        fileDesc: me.params.uploadFileDesc
                    }
                ],
                listeners: {
                    close: function () {
                        if (me.store) {
                            me.store.reload();
                        }
                    }
                }
            });

            win.show();
        }
    },

    setUploadBtnState: function (me) {
        var uploadBtn1 = me.down("[itemId=uploadBtn_1]");
        var uploadBtn2 = me.down("[itemId=uploadBtn_2]");

        if (!me.params.allowUpload)
        {
            uploadBtn1.setVisible(false);
            uploadBtn2.setVisible(false);
        }
    },

    setToolButtonState: function (me) {
        var fdBtn = me.down("[itemId=fdBtn]");//放大
        var sxBtn = me.down("[itemId=sxBtn]");//缩小
        var xzBtn = me.down("[itemId=xzBtn]");//向左
        var xyBtn = me.down("[itemId=xyBtn]");//向右
        var syzBtn = me.down("[itemId=syzBtn]");//上一张
        var xyzBtn = me.down("[itemId=xyzBtn]");//下一张

        if (me.images <= 0) {
            fdBtn.setDisabled(true);
            sxBtn.setDisabled(true);
            xzBtn.setDisabled(true);
            xyBtn.setDisabled(true);
            syzBtn.setDisabled(true);
            xyzBtn.setDisabled(true);
        }
        else
        {
            fdBtn.setDisabled(false);
            sxBtn.setDisabled(false);
            xzBtn.setDisabled(false);
            xyBtn.setDisabled(false);
            syzBtn.setDisabled(false);
            xyzBtn.setDisabled(false);
        }
    },

    //图片加载完成触发
    onImageFinishLoad: function (ev) {
        jQuery("#" + ev.target.id).fadeIn();
    },

    //显示图片
    showImage: function(me,src)
    {
        //var picBoxPanel = me.down("[itemId=picBoxPanel]");
        //var picBoxHeight = picBoxPanel.getHeight();
        //var picBoxWidth = picBoxPanel.getWidth();

        if (me.rpic == null || me.rpic == undefined) {
            me.rpic = jQuery("#picbox_" + me.id);

            me.rpic.iviewer({
                zoom: 100,
                src: src,
                onFinishLoad: me.onImageFinishLoad
            });
        }
        else {
            me.rpic.fadeOut(500, function () {
                me.rpic.iviewer('loadImage', src);
            });
        }
    },

    initComponent: function () {
        var me = this;

        me.store = Ext.create("Ext.data.Store", {
            pageSize: 50,
            fields: [
                { name: "Id" },
                { name: "Version" },
                { name: "Datatype" },
                { name: "Filename" },
                { name: "Filepath" },
                { name: "Thumb" },
                { name: "Newname" },
                { name: "Employeeid" },
                { name: "Companyid" },
                { name: "Oid" },
                { name: "Filesize" },
                { name: "Extensions" },
                { name: "Datacount" },
                { name: "Invalidcount" },
                { name: "State" },
                { name: "Remark" },
                { name: "Submittime" },
                { name: "Createtime" },
                { name: "Employee" },
                { name: "Company" }
            ],
            proxy: new Ext.data.proxy.Ajax({
                url: me.params.queryUrl,
                reader: {
                    type: 'json',
                    root: 'datas',
                    totalProperty: 'dataCount'
                }
            }),
            autoLoad: true,
            listeners: {
                datachanged: function( store, eOpts )
                {
                    me.images = [],

                    me.images = store.data.items.filter(function (value, index, datas) {
                        switch (value.data.Extensions) {
                            case ".jpg":
                            case ".png":
                                return true;
                        }
                        return false;
                    });

                    if (me.images.length > 0)
                    {
                        me.curpicindex = 0;
                        me.showImage(me, me.images[0].data.Filepath);

                        me.setToolButtonState(me);
                    }                    
                }
            }
        }),

        Ext.applyIf(me, {
            listeners: {
                'afterrender': function () {
                    me.setUploadBtnState(me);
                }
            },
            items: [
                {
                    xtype: 'panel',
                    layout: 'fit',
                    itemId: 'picBoxPanel',
                    html: '<div id="picbox_' + me.id + '" style="width:100%; height:100%; overflow:hidden;"></div>',
                    dockedItems: [
                        {
                            xtype: 'toolbar',
                            dock: 'top',
                            items: [
                                {
                                    xtype: 'button',
                                    iconCls: 'icon1150_16x16',
                                    tooltip: '放大',
                                    itemId: 'fdBtn',
                                    handler: function () {
                                        me.rpic.iviewer('zoom_by', 1);
                                    }
                                },
                                {
                                    xtype: 'button',
                                    iconCls: 'icon1151_16x16',
                                    tooltip: '缩小',
                                    itemId: 'sxBtn',
                                    handler: function () {
                                        me.rpic.iviewer('zoom_by', -1);
                                    }
                                },
                                {
                                    xtype: 'button',
                                    iconCls: 'icon68_16x16',
                                    tooltip: '向左转',
                                    itemId: 'xzBtn',
                                    handler: function () {
                                        me.rpic.iviewer('angle', -90);
                                    }
                                },
                                {
                                    xtype: 'button',
                                    iconCls: 'icon69_16x16',
                                    tooltip: '向右转',
                                    itemId: 'xyBtn',
                                    handler: function () {
                                        me.rpic.iviewer('angle', 90);
                                    }
                                },
                                {
                                    xtype: 'button',
                                    iconCls: 'icon61_16x16',
                                    tooltip: '上一张',
                                    itemId: 'syzBtn',
                                    handler: function () {
                                        if (me.curpicindex == 0) {
                                            me.curpicindex = me.images.length - 1;
                                        }
                                        else
                                        {
                                            me.curpicindex--;
                                        }
                                        me.showImage(me, me.images[me.curpicindex].data.Filepath);
                                    }
                                },
                                {
                                    xtype: 'button',
                                    iconCls: 'icon67_16x16',
                                    tooltip: '下一张',
                                    itemId: 'xyzBtn',
                                    handler: function () {
                                        if (me.curpicindex == (me.images.length - 1)) {
                                            me.curpicindex = 0;
                                        }
                                        else {
                                            me.curpicindex++;
                                        }

                                        me.showImage(me, me.images[me.curpicindex].data.Filepath);
                                    }
                                },
                                {
                                    xtype: 'button',
                                    iconCls: 'icon59_16x16',
                                    tooltip: '满比例显示',
                                    handler: function () {
                                        me.rpic.iviewer('set_zoom', 100);
                                    }
                                },
                                {
                                    xtype: 'button',
                                    iconCls: 'icon50_16x16',
                                    tooltip: '转到列表视图',
                                    handler: function () {
                                        me.getLayout().setActiveItem(1);
                                    }
                                }, '->',
                                {
                                    xtype: 'button',
                                    text: '上传文件',
                                    itemId: 'uploadBtn_1',
                                    iconCls: 'icon519_16x16',
                                    handler: function () {
                                        me.uploadFunc(me);
                                    }
                                },
                                {
                                    xtype: 'button',
                                    iconCls: 'icon65_16x16',
                                    handler: function () {
                                        me.store.reload();
                                    }
                                }
                            ]
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    layout: 'fit',
                    dockedItems: [
                        {
                            xtype: 'pagingtoolbar',
                            dock: 'bottom',
                            store: me.store,
                            displayInfo: true
                        },
                        {
                            xtype: 'toolbar',
                            dock: 'top',
                            items: [
                                {
                                    xtype: 'button',
                                    iconCls: 'icon812_16x16',
                                    text: '转到查看视图',
                                    handler: function () {
                                        me.getLayout().setActiveItem(0);
                                    }
                                }, '->',
                                {
                                    xtype: 'button',
                                    text: '上传文件',
                                    itemId: 'uploadBtn_2',
                                    iconCls: 'icon519_16x16',
                                    handler: function () {
                                        me.uploadFunc(me);
                                    }
                                },
                                {
                                    xtype: 'button',
                                    iconCls: 'icon65_16x16',
                                    handler: function () {
                                        me.store.reload();
                                    }
                                }
                            ]
                        }
                    ],
                    items: [
                        {
                            xtype: 'dataview',
                            autoScroll: true,
                            itemSelector: 'div.thumb-wrap',
                            store: me.store,
                            tpl: '<tpl for=".">' +
                                '<div style="margin-left:10px;margin-top:10px;float:left;display:inline-block;" class="thumb-wrap">' +
                                    '<img src="{Thumb}" />' +
                                    '<br/>' +
                                    '<div style="text-align:center;">{Filename}</div>' +
                                '</div>' +
                            '</tpl>',
                            listeners: {
                                'itemclick': function (view, record, item, index, e, eOpts) {
                                    
                                    switch(record.data.Extensions)
                                    {
                                        case ".jpg":
                                        case ".png":
                                            {
                                                Ext.Array.each(me.images, function (value, index, datas) {
                                                    if (record.data.Id == value.data.Id) {
                                                        me.curpicindex = index;
                                                    }
                                                });

                                                me.getLayout().setActiveItem(0);
                                                me.showImage(me, me.images[me.curpicindex].data.Filepath);
                                            }
                                            break;
                                        default:
                                            Ext.Msg.alert("尚不支持预览此类型文件！");
                                            break;
                                    }
                                    
                                },
                                'itemcontextmenu': function (view, record, item, index, e, eOpts) {
                                    e.preventDefault();
                                    e.stopEvent();

                                    /*
                                    var menu = new Ext.menu.Menu({
                                        items: [
                                            {
                                                iconCls: 'icon-note_information_16',
                                                text: '文件信息',
                                                listeners: {
                                                    'click': function () {
                                                        
                                                    }
                                                }
                                            }, '-',
                                            {
                                                iconCls: 'icon-download_16',
                                                text: '下载图片',
                                                listeners: {
                                                    'click': function () {
                                                        
                                                    }
                                                }
                                            }
                                        ]
                                    });

                                    menu.showAt(e.getXY());
                                    */
                                }
                            }
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

});