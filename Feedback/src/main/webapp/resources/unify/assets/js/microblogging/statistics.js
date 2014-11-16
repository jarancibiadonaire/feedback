	$(document).ready(function(){
		$('#chart').highcharts({
	        data: {
	            table: document.getElementById('datatable')
	        },
	        chart: {
	            type: 'column'
	        },
	        title: {
	            text: 'Tags m\u00e1s asociados en Feedback'
	        },
	        xAxis: {
	            type: 'category',
	            labels: {
	                rotation: -45
	            }
	        },
	        yAxis: {
	            allowDecimals: false,
	            title: {
	                text: 'N Asociaciones'
	            }
	        },
	        tooltip: {
	            formatter: function () {
	                return '<b>' + this.series.name + '</b><br/>' +
	                    this.point.y + ' asociaciones';
	            }
	        }
	    });
		$('#chart2').highcharts({
	        data: {
	            table: document.getElementById('datatable2')
	        },
	        chart: {
	            type: 'column'
	        },
	        title: {
	            text: 'Feeds m\u00e1s comentados en Feedback'
	        },
	        xAxis: {
	            type: 'category',
	            labels: {
	                rotation: -45
	            }
	        },
	        yAxis: {
	            allowDecimals: false,
	            title: {
	                text: 'N Comentarios'
	            }
	        },
	        tooltip: {
	            formatter: function () {
	                return '<b>' + this.series.name + '</b><br/>' +
	                    this.point.y + ' comentarios';
	            }
	        }
	    });
		$(".serie").each(function(){
			$(this).find(".chartN").highcharts({
				data: {
		            table: $(this).find('.datatableN')[0]
		        },
		        chart: {
		            type: 'pie',
		            width: $(this).width()
		        },		        
		        title: {
		            text: $(this).find(".serie-name").html()
		        },
		        tooltip: {
		            formatter: function () {
		            	return this.point.y + ' votos';
		            }
		        },
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
		                    style: {
		                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
		                    }
		                }
		            }
		        },
		    });
		});
	});