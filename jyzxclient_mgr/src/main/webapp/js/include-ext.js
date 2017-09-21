(function() {
    function getQueryParam(name) {
        var regex = RegExp('[?&]' + name + '=([^&]*)');

        var match = regex.exec(location.search) || regex.exec(scriptPath);
        return match && decodeURIComponent(match[1]);
    }

    function hasOption(opt, queryString) {
        var s = queryString || location.search;
        var re = new RegExp('(?:^|[&?])' + opt + '(?:[=]([^&]*))?(?:$|[&])', 'i');
        var m = re.exec(s);

        return m ? (m[1] === undefined || m[1] === '' ? true : m[1]) : false;
    }

    function loadCss(url) {
        document.write('<link rel="stylesheet" type="text/css" href="' + url + '"/>');
    }

    function loadScript(url, defer) {
        document.write('<script type="text/javascript" src="' + url + '"' +
                (defer ? ' defer' : '') + '></script>');
    }

    Ext = window.Ext || {};

    // The value of Ext.repoDevMode gets replaced during a build - do not change this line
    // 2 == internal dev mode, 1 == external dev mode, 0 == build mode
    Ext.devMode = 0;

    var scriptEls = document.getElementsByTagName('script'),
        scriptPath = scriptEls[scriptEls.length - 1].src,
        rtl = getQueryParam('rtl'),
        themeName = getQueryParam('theme') || 'neptune',
        includeCSS = !hasOption('nocss', scriptPath),
        useDebug = hasOption('debug'),
        hasOverrides = !hasOption('nooverrides', scriptPath) && !!{
            // TODO: remove neptune
            neptune: 1,
            triton: 1,
            classic: 1,
            gray: 1,
            triton: 1,
            'neptune-touch': 1,
            crisp: 1,
            'crisp-touch': 1
        }[themeName],
        devMode = Ext.devMode,
        extDir = scriptPath,
        rtlSuffix = (rtl ? '-rtl' : ''),
        debugSuffix = (devMode ? '-debug' : ''),
        cssSuffix = rtlSuffix + debugSuffix + '.css',
        themePackageDir, chartsJS, uxJS, themeOverrideJS, extPrefix, extPackagesRoot;

    rtl = rtl && rtl.toString() === 'true';

    //extDir = extDir.substring(0, extDir.lastIndexOf('/')) + "/extjs";
   
    extPrefix = useDebug ? '/ext' : '/ext-all';
    
    var version = 5;
    if(version == 6)
	{
    	extDir = "http://jsapi.renrencui.org/ext-6.0.0/build";
	    extPackagesRoot = devMode ? (extDir + '/build') : extDir;
	    
    	uxJS = extPackagesRoot + '/packages/ux/classic/ux' + debugSuffix + '.js';
	    chartsJS = extPackagesRoot + '/packages/charts/classic/charts.js';
	    themePackageDir = extPackagesRoot + '/classic/theme-' + themeName + '/';

	    if (includeCSS) {
	        loadCss(themePackageDir + 'resources/theme-' + themeName + '-all' + cssSuffix);
	        loadCss(extPackagesRoot + '/packages/charts/classic/' + themeName + '/resources/charts-all' + cssSuffix);
	        loadCss(extPackagesRoot + '/packages/ux/classic/' + themeName + '/resources/ux-all' + cssSuffix);
	    }
	    
	    if (hasOverrides) themeOverrideJS = themePackageDir + 'theme-' + themeName + debugSuffix + '.js';
	}
    
    if(version == 5)
	{
    	extDir = "http://jsapi.renrencui.org/ext-5.1.1/build";
	    extPackagesRoot = devMode ? (extDir + '/build') : extDir;
	    
    	uxJS = extPackagesRoot + '/packages/ext-ux/build/ext-ux' + debugSuffix + '.js';
	    chartsJS = extPackagesRoot + '/packages/sencha-charts/build/sencha-charts.js';
	    themePackageDir = extPackagesRoot + '/packages/ext-theme-' + themeName + '/build/';

	    if (includeCSS) {
	        loadCss(themePackageDir + 'resources/ext-theme-' + themeName + '-all' + cssSuffix);
	        loadCss(extPackagesRoot + '/packages/sencha-charts/build/' + themeName + '/resources/sencha-charts-all' + cssSuffix);
	        loadCss(extPackagesRoot + '/packages/ext-ux/build/classic/resources/ext-ux-all' + cssSuffix);
	    }
	    
	    if (hasOverrides) themeOverrideJS = themePackageDir + 'ext-theme-' + themeName + debugSuffix + '.js';
	}

    document.write('<script type="text/javascript" src="' + extDir + extPrefix + rtlSuffix + '.js"></script>');
    
    if (hasOverrides) {
        if (devMode) {
            if (window.ActiveXObject) {
                Ext = {
                    _beforereadyhandler: function() {
                        Ext.Loader.loadScript({url: themeOverrideJS});
                    }
                };
            } else {
                loadScript(themeOverrideJS, true);
            }
        } else {
            loadScript(themeOverrideJS, true);
            // ux and charts js are not needed in dev mode because they are included in bootstrap
            loadScript(uxJS);
            loadScript(chartsJS);
        }
    }

})();
