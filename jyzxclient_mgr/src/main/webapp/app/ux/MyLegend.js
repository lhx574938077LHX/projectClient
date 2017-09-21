Ext.define('App.ux.MyLegend', {
	extend: 'Ext.chart.Legend',
    legendLabels: {},
    createLegendItem: function(series, yFieldIndex) {
        var me = this;
        
        return Ext.create("App.ux.MyLegendItem",{
            legend: me,
            series: series,
            legendLabels: me.legendLabels,
            surface: me.chart.surface,
            yFieldIndex: yFieldIndex
        });
    }
 });