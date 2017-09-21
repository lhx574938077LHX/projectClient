Ext.define('App.view.LoginWindow', {
    extend: 'Ext.window.Window',

    width: 327,
    layout: {
        type: 'fit'
    },
    title: '用户登录',
    resizable: false,

    submitLogin: function (me) {
    	
        var form = me.down("form");
        
        if (form.isValid()) {
            var values = form.getValues();
            var conn = new Ext.data.Connection();
            values.username = me.down("[name=j_username]").getValue();
            values.password = me.down("[name=j_password]").getValue();
            values.vcode = me.down("[name=vcode]").getValue();
            conn.request({
                url: 'login/loginJ.do',
                method: 'POST',
                params: values,
                callback: function (data, success, response) {
                	//window.location="index.jsp"
                    if (true) {
                        var json = eval("(" + response.responseText + ")");
                        if (json.result) {
                        	window.location="indexV.do";
                        }
                        else {
                            Ext.Msg.show({
                                title: '消息',
                                msg: json.msg,
                                buttons: Ext.Msg.YES,
                                icon: Ext.Msg.WARNING
                            });
                            var vcodeimg = me.down("[itemId=vcodeimg]");
                            vcodeimg.setSrc('/jyzxclient_mgr/common/getVCode.do?' + Math.random());
                        }
                    }
                    else {
                        Ext.Msg.show({
                            title: '消息',
                            msg: '网络超时！',
                            buttons: Ext.Msg.YES,
                            icon: Ext.Msg.WARNING
                        });
                        var vcodeimg = me.down("[itemId=vcodeimg]");
                        vcodeimg.setSrc("/jyzxclient_mgr/common/getVCode.do?" + Math.random());
                    }
                }
            });
        }
        
    },

    initComponent: function () {
        var me = this;

        this.buttons = [
            {
                text: "登录",
                listeners: {
                    'click': function () {
                        me.submitLogin(me);
                    }
                }
            },
            {
                text: "退出",
                listeners: {
                    'click': function () {
                        if (navigator.userAgent.indexOf("MSIE") > 0) {
                            if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
                                window.opener = null; 
                                window.close();
                            }
                            else {
                                window.open('', '_top'); 
                                window.top.close();
                            }
                        }
                        else if (navigator.userAgent.indexOf("Firefox") > 0) {
                            window.location.href = 'about:blank';
                            //window.history.go(-2);  
                        }
                        else {
                            window.opener = null;
                            window.open('', '_self', '');
                            window.close();
                        }
                    }
                }
            }
        ];

        Ext.applyIf(me, {
            listeners: {
                'afterrender': function () {
                    Ext.fly("vcodeimg").on('click', function () {
                        var vcodeimg = me.down("[itemId=vcodeimg]");
                        vcodeimg.fireEvent('click', vcodeimg);
                    });
                }
            },
            items: [
                {
                    xtype: 'form',
                    layout: {
                        align: 'stretch',
                        type: 'vbox'
                    },
                    items: [
                        {
                            xtype: 'container',
                            margin: '0 0 10 0',
                            layout: {
                                type: 'fit'
                            },
                            height: 100,
                            items: [
                                {
                                    xtype:'image',
                                    src:'images/loginpic_1.png'
                                }
                            ]
                        },
                        {
                            xtype: 'container',
                            margin: '10 50 8 0',
                            layout: {
                                align: 'stretch',
                                type: 'hbox'
                            },
                            items: [
                                {
                                    xtype: 'textfield',
                                    flex: 1,
                                    fieldLabel: '用户名',
                                    labelAlign: 'right',
                                    name: 'j_username',
                                    validator: function (value) {
                                        if (value == "") {
                                            return "用户名不能为空";
                                        }

                                        if (Utils.GetStringTrueLength(value) > 50) {
                                            return "用户名的长度不能大于50字节！";
                                        }

                                        return true;
                                    },
                                    listeners: {
                                        'specialkey': function (field, e) {
                                            if (e.getKey() == Ext.EventObject.ENTER) {
                                                var password = me.down("[name=j_password]");
                                                password.focus();
                                            }
                                        }
                                    }
                                }
                            ]
                        },
                        {
                            xtype: 'container',
                            margin: '0 50 8 0',
                            layout: {
                                align: 'stretch',
                                type: 'hbox'
                            },
                            items: [
                                {
                                    xtype: 'textfield',
                                    flex: 1,
                                    fieldLabel: '密　码',
                                    labelAlign: 'right',
                                    name: 'j_password',
                                    inputType: 'password',
                                    validator: function (value) {
                                        if (value == "") {
                                            return "密码不能为空";
                                        }

                                        if (Utils.GetStringTrueLength(value) > 20) {
                                            return "密码的长度不能大于20字节！";
                                        }

                                        return true;
                                    },
                                    listeners: {
                                        'specialkey': function (field, e) {
                                            if (e.getKey() == Ext.EventObject.ENTER) {
                                                var vcode = me.down("[name=vcode]");
                                                vcode.focus();
                                            }
                                        }
                                    }
                                }
                            ]
                        },
                        {
                            xtype: 'container',
                            margin: '0 50 20 0',
                            layout: {
                                align: 'stretch',
                                type: 'hbox'
                            },
                            items: [
                                {
                                    xtype: 'textfield',
                                    flex: 1,
                                    fieldLabel: '验证码',
                                    labelAlign: 'right',
                                    name: 'vcode',
                                    validator: function (value) {
                                        if (value == "") {
                                            return "验证码不能为空";
                                        }

                                        if (Utils.GetStringTrueLength(value) > 4) {
                                            return "验证码的长度不能大于4字节！";
                                        }

                                        return true;
                                    },
                                    listeners: {
                                        'specialkey': function (field, e) {
                                            if (e.getKey() == Ext.EventObject.ENTER) {
                                                me.submitLogin(me);
                                            }
                                        }
                                    }
                                },
                                {
                                    xtype: 'image',
                                    margin: '0 0 0 5',
                                    id: 'vcodeimg',
                                    itemId: 'vcodeimg',
                                    width: 80,
                                    height: 25,
                                    style: 'cursor:pointer;',
                                    src: '/jyzxclient_mgr/common/getVCode.do?' + Math.random(),
                                    listeners: {
                                        'click': function (img) {
                                        	
                                            var vcodeimg = me.down("[itemId=vcodeimg]");
                                            vcodeimg.setSrc('/jyzxclient_mgr/common/getVCode.do?' + Math.random());
                                            console.log('/jyzxclient_mgr/common/getVCode.do?' + Math.random());
                                        }
                                    }
                                }
                            ]
                        }
                    ]
                }
            ]
        });

        me.callParent();
    }

});