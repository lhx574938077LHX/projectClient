Ext.define('App.controller.UIExtController', {
	extend: 'Ext.app.Controller',
	
	maximizeMode: 'mini', /* full mini */
	
	init: function() {
        this.control({
             'tool[type=maximize]': {
                 click: this.onMaximizeBtnClick
             },
			 'tool[type=restore]': {
				 click: this.onRestoreBtnClick
			 },
			 'viewport' : {
				resize: function( panel, width, height, oldWidth, oldHeight, eOpts ){
					var wins = panel.query("window");
					Ext.Object.each(wins, function(index, obj, objs) {
						if(obj.s_maximized){
							obj.x = 0;
							obj.y = 0;
							if(obj.getCollapsed())
							{
								obj.setWidth(obj.constrainTo.lastBox.width);
							}
							else
							{
								obj.setSize(obj.constrainTo.lastBox.width,obj.constrainTo.lastBox.height);
							}
						}
					});
				}
			 },
			 'panel>window': {
				render: function( win, eOpts ){
					this.onMaximizableWindowRender(win,eOpts);
				},
				move: function( win, toIdx ){
					this.onMaximizableWindowMove(win, toIdx);
				}
			 }
         });
    },
	
	onMaximizableWindowMove: function (win, toIdx) {
	    try
	    {
            if(win.s_maximized){
			    win.setX(win.oldx);
			    win.setY(win.oldy);
			    win.setHeight(win.oldheight);
			    win.setWidth(win.oldwidth);
			    win.s_maximized = false;
			    var restoretool = win.query('tool[type=restore]')[0];
			    var maximizetool = win.query('tool[type=maximize]')[0];
			    restoretool.setVisible(false);
			    maximizetool.setVisible(true);
		    }
	    }
	    catch (ex)
	    {
	    }
	},
	
	onMaximizableWindowRender: function (win, eOpts) {
	    try
	    {
		    win.s_maximized = false;
		    var restoretool = win.query('tool[type=restore]');
		    if(restoretool.length>0){
			    restoretool[0].setVisible(false);
		    }
	    }
	    catch (ex)
	    {
	    }
	},

	onMaximizeBtnClick: function (tool, e, eOpts) {
	    try {
	        var pwin = Ext.getCmp(tool.ownerCt.ownerCt.id);
	        pwin.oldheight = pwin.getHeight();
	        pwin.s_maximized = true;
	        pwin.oldwidth = pwin.getWidth();
	        pwin.oldx = pwin.getX();
	        pwin.oldy = pwin.getY();
	        if (this.maximizeMode == 'full') {
	            pwin.setHeight(Ext.getBody().getHeight());
	            pwin.setWidth(Ext.getBody().getWidth());
	            pwin.setX(Ext.getBody().getX());
	            pwin.setY(Ext.getBody().getY());
	        }
	        else {
	            pwin.setX(pwin.constrainTo.getX());
	            pwin.setY(pwin.constrainTo.getY());
	            pwin.setHeight(pwin.constrainTo.lastBox.height);
	            pwin.setWidth(pwin.constrainTo.lastBox.width);
	            pwin.setHeight(pwin.constrainTo.lastBox.height);
	        }

	        var restoretool = pwin.query('tool[type=restore]')[0];
	        var maximizetool = pwin.query('tool[type=maximize]')[0];
	        restoretool.setVisible(true);
	        maximizetool.setVisible(false);
	    }
	    catch (ex)
	    {

	    }		
	},
	
	onRestoreBtnClick: function (tool, e, eOpts) {
	    try {
	        var pwin = Ext.getCmp(tool.ownerCt.ownerCt.id);
	        pwin.s_maximized = false;
	        pwin.setHeight(pwin.oldheight);
	        pwin.setWidth(pwin.oldwidth);
	        pwin.setX(pwin.oldx);
	        pwin.setY(pwin.oldy);
	        var restoretool = pwin.query('tool[type=restore]')[0];
	        var maximizetool = pwin.query('tool[type=maximize]')[0];
	        restoretool.setVisible(false);
	        maximizetool.setVisible(true);
	    }
	    catch (ex)
	    {
	    }		
	}
});
