Ext.define('App.ux.MyLegendItem', {
	extend: 'Ext.chart.Legend',
    legendLabels: {},
    getLabelText: function() {
        var me = this,
            series = me.series,
            idx = me.yFieldIndex;

        function getSeriesProp(name) {
            var val = series[name];
            
            return (Ext.isArray(val) ? val[idx] : val);
        }
        
        var title = getSeriesProp('title') || getSeriesProp('yField');

        return me.legendLabels[title];
    }
 });