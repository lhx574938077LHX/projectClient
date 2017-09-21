Ext.define('App.ux.UEditor', {
    extend: 'Ext.Component',

    content: '',

    editorResize: function (me) {
        var editor = jQuery("#" + me.getId() + "-editor [class^=edui-editor]");

        var toolbarbox = jQuery("#" + editor[0].id + "_toolbarbox");
        //var iframeholder = jQuery("#" + editor[0].id + "_iframeholder");
        var bottombar = jQuery("#" + editor[0].id + "_bottombar");

        me.UEditor.setHeight(me.getHeight() - bottombar[0].clientHeight - toolbarbox[0].clientHeight);
    },

    initComponent: function () {
        var me = this;

        this.addStateEvents("change");

        this.html = "<div id='" + this.getId() + "-editor' style='width:100%;height:100%;'></div>";

        this.callParent(arguments);

    	console.log(this.getHeight());
        this.on('boxready', function () {
            this.UEditor = UE.getEditor(this.getId() + '-editor', {
                initialFrameHeight: this.getHeight() - 153,
                zIndex: 999999,
                readonly: this.disabled,
                maximumWords: 1000000
            });

            this.UEditor.ready(function () {
                me.initialized = true;
                me.editorResize(me);
                me.setValue(me.content);
            });

            this.UEditor.addListener('contentChange', (function () {
                me.fireEvent("change", me);
            }));
        }, this);

        this.on('resize', function (comp, width, height, oldWidth, oldHeight, eOpts) {
            if (this.initialized) {
                me.editorResize(me);
            }
        }, this);
    },

    reset: function () {
        if (this.UEditor) {
            this.UEditor.setContent("");
        }
    },

    setValue: function (value) {
        this.content = value;
        if (this.UEditor) {
            this.UEditor.setContent(value);
        }
    },

    getValue: function () {
        if (this.UEditor) {
            return this.UEditor.getContent();
        } else {
            return '';
        }
    },

    getRawValue: function () {
        if (this.UEditor) {
            return this.UEditor.getContentTxt();
        } else {
            return '';
        }
    },

    setRawValue: function (value) {
        if (this.UEditor) {
            this.UEditor.setContentTxt(value);
        }
    }
});