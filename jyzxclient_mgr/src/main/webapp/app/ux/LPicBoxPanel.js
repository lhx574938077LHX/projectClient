Ext.define('App.ux.LPicBoxPanel', {
    extend: 'Ext.panel.Panel',

    xdconsultid: 0,
    atype: 0,
    vboxid: '',
    rpic: null,
    pcmp: 'csrwPanel',

    layout: {
        type: 'fit'
    },

    viewPluginParams: {
        'viewportWidth': '100%',
        'viewportHeight': '100%',
        'startScale' : 0.5,
		'startX' : 0,
		'startY' : 0,
		'animTime' : 500,
		'draggInertia' : 10,
		'intNavEnable' : false,
		'intNavPos' : 'B',
		'intNavAutoHide' : false
    },
    curpicindex:0,
    picdatastore: null,
    setPicBtnState: function(me)
    {
        var fowardPicBtn = me.down("[itemId=fowardPicBtn]");
        var nextPicBtn = me.down("[itemId=nextPicBtn]");
        var dataCount = me.picdatastore.getTotalCount();

        if (fowardPicBtn != null) {
            if (me.curpicindex == 0) {

                fowardPicBtn.setDisabled(true);
            }
            else {
                fowardPicBtn.setDisabled(false);

            }
        }
        if (nextPicBtn != null) {
            if (me.curpicindex == dataCount - 1) {
                nextPicBtn.setDisabled(true);
            }
            else {
                nextPicBtn.setDisabled(false);
            }
        }
    },
    
    initComponent: function () {
        var me = this;
        me.vboxid = Ext.id('', 'vlightbox_');

        me.picdatastore = Ext.create("Ext.data.Store", {
            fields: [
                {
                    name: 'W_id'
                },
                {
                    name: 'W_createtime'
                },
                {
                    name: 'W_filename'
                },
                {
                    name: 'W_newfilename'
                },
                {
                    name: 'W_path'
                },
                {
                    name: 'W_thumbfilename'
                }
            ],
            proxy: new Ext.data.proxy.Ajax({
                url: 'custInfo/queryFileStreamJP.do?id=1',
                reader: {
                    type: 'json',
                    root: 'datas',
                    totalProperty: 'dataCount'
                }
            }),
            autoLoad: false,
            listeners: {
                datachanged: function( store, eOpts )
                {
                    if (store.getTotalCount() > 0)
                    {
                        var bigimgsrc = store.data.items[0].data.W_path + store.data.items[0].data.W_newfilename;
                        me.viewPluginParams.contentUrl = bigimgsrc;

                        if (me.rpic != null)
                        {
                            me.rpic.lhpMegaImgViewer('destroy');
                        }
                        console.log(me.viewPluginParams);
                        me.rpic = jQuery("#picbox_" + me.id);
                        me.rpic.lhpMegaImgViewer(me.viewPluginParams);
                        me.curpicindex = 0;

                        me.setPicBtnState(me);
                    }
                }
            }
        });

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'panel',
                    itemId: 'cardPanel',
                    layout: {
                        type: 'card'
                    },
                    items: [
                        {
                            xtype: 'panel',
                            layout: {
                                type: 'fit'
                            },
                            dockedItems: [
                                {
                                    xtype: 'toolbar',
                                    dock: 'top',
                                    items: [
                                        {
                                            xtype: 'button',
                                            text: '放大',
                                            iconCls: 'icon-magnifier_zoom_in_16',
                                            listeners: {
                                                'click': function () {
                                                    if (me.rpic != null) {
                                                        me.rpic.lhpMegaImgViewer('zoom');
                                                    }
                                                }
                                            }
                                        },
                                        {
                                            xtype: 'button',
                                            text: '缩小',
                                            iconCls: 'icon-magnifier_zoom_out_16',
                                            listeners: {
                                                'click': function () {
                                                    if (me.rpic != null) {
                                                        me.rpic.lhpMegaImgViewer('unzoom');
                                                    }
                                                }
                                            }
                                        },
                                        {
                                            xtype: 'button',
                                            text: '充满显示',
                                            iconCls: 'icon-arrow_out_16',
                                            listeners: {
                                                'click': function () {
                                                    if (me.rpic != null) {
                                                        me.rpic.lhpMegaImgViewer('fitToViewport');
                                                    }
                                                }
                                            }
                                        }, '-',
                                        {
                                            xtype: 'button',
                                            text: '上一张',
                                            iconCls: 'icon-arrow_left_16',
                                            itemId: 'fowardPicBtn',
                                            listeners: {
                                                'click': function () {
                                                    if (me.rpic != null) {
                                                        var dataCount = me.picdatastore.getTotalCount();
                                                        var fowardPicBtn = me.down("[itemId=fowardPicBtn]");
                                                        var nextPicBtn = me.down("[itemId=nextPicBtn]");

                                                        if (dataCount == 0) {

                                                            fowardPicBtn.setDisabled(true);
                                                            nextPicBtn.setDisabled(true);
                                                            return;
                                                        }
                                                        else {
                                                            if (me.curpicindex != 0) {
                                                                me.curpicindex = me.curpicindex - 1;
                                                                var bigimgsrc = me.picdatastore.data.items[me.curpicindex].data.W_path + me.picdatastore.data.items[me.curpicindex].data.W_newfilename;
                                                                me.viewPluginParams.contentUrl = bigimgsrc;
                                                                me.rpic.lhpMegaImgViewer('destroy');
                                                                me.rpic = jQuery("#picbox_" + me.id);
                                                                me.rpic.lhpMegaImgViewer(me.viewPluginParams);
                                                            }
                                                            me.setPicBtnState(me);
                                                        }
                                                    }
                                                }
                                            }
                                        },
                                        {
                                            xtype: 'button',
                                            text: '下一张',
                                            iconCls: 'icon-arrow_right_16',
                                            itemId: 'nextPicBtn',
                                            listeners: {
                                                'click': function () {
                                                    if (me.rpic != null) {
                                                        var dataCount = me.picdatastore.getTotalCount();

                                                        var fowardPicBtn = me.down("[itemId=fowardPicBtn]");
                                                        var nextPicBtn = me.down("[itemId=nextPicBtn]");

                                                        if (dataCount == 0) {
                                                            fowardPicBtn.setDisabled(true);
                                                            nextPicBtn.setDisabled(true);
                                                            return;
                                                        }
                                                        else {
                                                            if (me.curpicindex != (dataCount - 1)) {
                                                                me.curpicindex = me.curpicindex + 1;
                                                                var bigimgsrc = me.picdatastore.data.items[me.curpicindex].data.W_path + me.picdatastore.data.items[me.curpicindex].data.W_newfilename;
                                                                me.viewPluginParams.contentUrl = bigimgsrc;
                                                                me.rpic.lhpMegaImgViewer('destroy');
                                                                me.rpic = jQuery("#picbox_" + me.id);
                                                                me.rpic.lhpMegaImgViewer(me.viewPluginParams);
                                                            }
                                                            me.setPicBtnState(me);
                                                        }
                                                    }
                                                }
                                            }
                                        }, '-',
                                        {
                                            xtype: 'button',
                                            iconCls: 'icon-application_view_tile_16',
                                            text: '列表显示',
                                            listeners: {
                                                'click': function () {
                                                    var cardPanel = me.down("[itemId=cardPanel]");
                                                    cardPanel.getLayout().setActiveItem(1);
                                                }
                                            }
                                        }, '->',
                                        {
                                            xtype: 'button',
                                            iconCls: 'icon-arrow_refresh',
                                            listeners: {
                                                'click': function () {
                                                    me.picdatastore.reload();
                                                }
                                            }
                                        }
                                    ]
                                }
                            ],
                            items: [
                                {
                                    xtype: 'panel',
                                    layout: {
                                        type: 'fit'
                                    },
                                    height: 480,
                                    widht: 640,
                                    html: '<div  id="picbox_' + me.id + '" style="width:100%; height:100%; overflow:hidden;"></div>',
                                    listeners: {
                                        'afterrender': function () {
                                            me.picdatastore.reload();
                                        }
                                    }
                                }
                            ]
                        },
                        {
                            xtype: 'panel',
                            layout: {
                                type: 'fit'
                            },
                            dockedItems: [
                                {
                                    xtype: 'toolbar',
                                    dock: 'top',
                                    items: [
                                        {
                                            xtype: 'button',
                                            text: '查看备注',
                                            iconCls: 'icon-comment',
                                            listeners: {
                                                'click': function ()
                                                {
                                                    var titlestr = "";
                                                    titlestr = ValueMatch.ZlyqMatch(me.atype) + "-资料备注";
                                                    Ext.getCmp(me.pcmp).add({
                                                        closeAction: 'hide',
                                                        autoShow: true,
                                                        xtype: 'grzlremarkviewwindow',
                                                        //modal: true,
                                                        title: titlestr,
                                                        otype: me.atype,
                                                        xdconsultid: me.xdconsultid,
                                                        collapsible: true,
                                                        constrainHeader: true,
                                                        bodyCls: 'p-no-boder',
                                                        resizable: true
                                                    });
                                                }
                                            }
                                        },
                                        {
                                            xtype: 'button',
                                            text: '注意事项'
                                        },'->',
                                        {
                                            xtype: 'button',
                                            iconCls: 'icon-arrow_refresh',
                                            listeners: {
                                                'click': function () {
                                                    var imageview = me.down("[itemId=imageview]");
                                                    imageview.getStore().reload();
                                                }
                                            }
                                        }
                                    ]
                                }
                            ],
                            items: [
                                {
                                    xtype: 'dataview',
                                    itemId: 'imageview',
                                    autoScroll: true,
                                    itemSelector: 'a',
                                    tpl: [
                                        '<div id="' + me.vboxid + '">',
									        '<tpl for=".">',
										        '<a class="vlightbox1" href="javascript:void(0);" title="{W_filename}"><img src="/{W_path}{W_thumbfilename}" alt="{W_filename}"/>',
                                                    '<span>{W_filename}</span>',
                                                '</a>',
									        '</tpl>',
                                        '</div>'
                                    ],
                                    listeners: {
                                        'itemclick': function (view, record, item, index, e, eOpts) {
                                            var bigimgsrc = record.data.W_path + record.data.W_newfilename;

                                            me.viewPluginParams.contentUrl = bigimgsrc;
                                            me.rpic.lhpMegaImgViewer('destroy');
                                            me.rpic = jQuery("#picbox_" + me.id);
                                            me.rpic.lhpMegaImgViewer(me.viewPluginParams);

                                            me.curpicindex = index;

                                            me.setPicBtnState(me);
                                                                                       
                                            var cardPanel = me.down("[itemId=cardPanel]");
                                            cardPanel.getLayout().setActiveItem(0);
                                        },
                                        'itemcontextmenu': function (view, record, item, index, e, eOpts) {
                                            e.preventDefault();
                                            e.stopEvent();

                                            var menu = new Ext.menu.Menu({
                                                items: [
                                                    {
                                                        iconCls: 'icon-note_information_16',
                                                        text: '文件信息',
                                                        listeners: {
                                                            'click': function () {
                                                                var thumbimgsrc = record.data.W_path + record.data.W_thumbfilename + "?ranseed=" + Math.random();
                                                                var bigimgsrc = record.data.W_path + record.data.W_newfilename + "?ranseed=" + Math.random();
                                                                Ext.getCmp(me.pcmp).add({
                                                                    closeAction: 'hide',
                                                                    autoShow: true,
                                                                    xtype: 'picfileinfowindow',
                                                                    modal: true,
                                                                    collapsible: true,
                                                                    constrainHeader: true,
                                                                    bodyCls: 'p-no-boder',
                                                                    resizable: false,
                                                                    thumbimgsrc: thumbimgsrc,
                                                                    bigimgsrc: bigimgsrc,
                                                                    attachmentid: record.data.W_id
                                                                });
                                                            }
                                                        }
                                                    }, '-',
                                                    {
                                                        iconCls: 'icon-download_16',
                                                        text: '下载图片',
                                                        listeners: {
                                                            'click': function () {
                                                                var bigimgsrc = record.data.W_path + record.data.W_newfilename;
                                                                window.open(bigimgsrc);
                                                            }
                                                        }
                                                    }
                                                ]
                                            });

                                            menu.showAt(e.getXY());
                                        }
                                    },
                                    store: me.picdatastore
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