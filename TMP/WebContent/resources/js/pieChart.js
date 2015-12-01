window.onload = function () {
	var chart = new CanvasJS.Chart("bottom-dashboardDiv",
	{
		title:{
			text: "COURSES DETAILS "
		},     
                animationEnabled: true,     
		data: [
		{        
			type: "doughnut",
			startAngle: 80,                          
			toolTipContent: "{legendText}: {y} - <strong>#percent% </strong>", 					
			showInLegend: true,
			dataPoints: [
				{y: 65899660, indexLabel: "Course Completed #percent%", legendText: "Course Completed" },
				{y: 60929152, indexLabel: "Course Left #percent%", legendText: "Course Left" },
				{y: 2175850,  indexLabel: "Course In Process #percent%", legendText: "Course In Process" }			
			]
		}
		]
	});
	chart.render();
	}
