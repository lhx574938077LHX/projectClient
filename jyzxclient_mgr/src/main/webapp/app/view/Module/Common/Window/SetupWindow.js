Ext.define('App.view.Module.Common.Window.SetupWindow', {
    extend: 'Ext.window.Window',

    autoShow: true,
    height: 383,
    width: 516,
    layout: 'fit',
    title: '设置',

    initComponent: function () {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'form',
                    layout: 'fit',
                    items: [
                        {
                            xtype: 'tabpanel',
                            activeTab: 0,
                            tabPosition: 'left',
                            items: [
                                {
                                    xtype: 'panel',
                                    title: '安全设置',
                                    layout: {
                                        type: 'vbox',
                                        align: 'stretch'
                                    },
                                    items: [
                                        {
                                            xtype: 'fieldset',
                                            margin: '5 8 8 8',
                                            collapsible: true,
                                            title: '密码修改',
                                            layout: {
                                                type: 'vbox',
                                                align: 'stretch'
                                            },
                                            items: [
                                                {
                                                    xtype: 'container',
                                                    flex: 1,
                                                    margin: '5 30 8 0',
                                                    layout: {
                                                        type: 'hbox',
                                                        align: 'stretch'
                                                    },
                                                    items: [
                                                        {
                                                            xtype: 'textfield',
                                                            flex: 1,
                                                            fieldLabel: '原始密码',
                                                            labelAlign: 'right',
                                                            labelWidth: 80,
                                                            name: 'oldpass',
                                                            validator: function (value) {
                                                                if (value == "") {
                                                                    return "原始密码不能为空";
                                                                }
                                                                return true;
                                                            },
                                                            inputType: 'password'
                                                        }
                                                    ]
                                                },
                                                {
                                                    xtype: 'container',
                                                    flex: 1,
                                                    margin: '0 30 8 0',
                                                    layout: {
                                                        type: 'hbox',
                                                        align: 'stretch'
                                                    },
                                                    items: [
                                                        {
                                                            xtype: 'textfield',
                                                            flex: 1,
                                                            fieldLabel: '新密码',
                                                            labelAlign: 'right',
                                                            labelWidth: 80,
                                                            name: 'newpass',
                                                            validator: function (value) {
                                                                if (value == "") {
                                                                    return "密码不能为空";
                                                                }

                                                                if (Utils.GetStringTrueLength(value) > 20) {
                                                                    return "密码的长度不能大于20字节！";
                                                                }
                                                                return true;
                                                            },
                                                            inputType: 'password'
                                                        }
                                                    ]
                                                },
                                                {
                                                    xtype: 'container',
                                                    flex: 1,
                                                    margin: '0 30 8 0',
                                                    layout: {
                                                        type: 'hbox',
                                                        align: 'stretch'
                                                    },
                                                    items: [
                                                        {
                                                            xtype: 'textfield',
                                                            flex: 1,
                                                            fieldLabel: '效验密码',
                                                            labelAlign: 'right',
                                                            labelWidth: 80,
                                                            name: 'vpass',
                                                            validator: function (value) {
                                                                if (value == "") {
                                                                    return "校验密码不能为空";
                                                                }
                                                                if (Utils.GetStringTrueLength(value) > 20) {
                                                                    return "密码的长度不能大于20字节！";
                                                                }
                                                                var newpass = me.down("[name=newpass]").getValue();
                                                                if( value != newpass ) {
                                                                	return "新密码和校验密码填写不一致";
                                                                }
                                                                return true;
                                                            },
                                                            inputType: 'password'
                                                        }
                                                    ]
                                                },
                                                {
                                                    xtype: 'container',
                                                    flex: 1,
                                                    margin: '8 30 8 0',
                                                    layout: {
                                                        type: 'vbox',
                                                        align: 'center',
                                                        pack: 'center'
                                                    },
                                                    items: [
                                                        {
                                                            xtype: 'button',
                                                            flex: 1,
                                                            text: '确认修改',
                                                            handler: function () {
                                                                var form = me.down("form");

                                                                if (form.isValid()) {
                                                                    var fvalues = form.getValues();
                                                                    fvalues.oldpass = hex_md5(hex_sha1(fvalues.oldpass));
                                                                    fvalues.newpass = hex_md5(hex_sha1(fvalues.newpass));
                                                                    fvalues.vpass = hex_md5(hex_sha1(fvalues.vpass));
                                                                    jQuery.ajax({
                                                                        type: "POST",
                                                                        url: 'loginController/changePassword.do',
                                                                        dataType: "text",
                                                                        cache: false,
                                                                        async: false,
                                                                        data: fvalues,
                                                                        success: function (response) {
                                                                            var json = eval("(" + response + ")");
                                                                            if( json.result ) {
                                                                            	Ext.toast({
                                                                                    html: json.msg,
                                                                                    closable: false,
                                                                                    align: 't',
                                                                                    slideInDuration: 400,
                                                                                    minWidth: 400
                                                                                });
                                                                            	jQuery.ajax({
                                                                                    type: "POST",
                                                                                    url:'loginController/logout.do',
                                                                                    dataType: "text",
                                                                                    cache: false,
                                                                                    async: false,
                                                                                    data: {},
                                                                                    success: function (response) {
                                                                                        var json = eval("(" + response + ")");
                                                                                        if (json.result) {
                                                                                            window.location.href = "index.jsp";
                                                                                        }
                                                                                    },
                                                                                    failure: function (resp) {

                                                                                    }
                                                                                });
                                                                            } else {
                                                                            	 Ext.toast({
                                                                                     html: json.msg,
                                                                                     closable: false,
                                                                                     align: 't',
                                                                                     slideInDuration: 400,
                                                                                     minWidth: 400
                                                                                 });
                                                                            }
                                                                        },
                                                                        failure: function (resp) {

                                                                        }
                                                                    });
                                                                }
                                                            }
                                                        }
                                                    ]
                                                }
                                            ]
                                        }
                                    ]
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