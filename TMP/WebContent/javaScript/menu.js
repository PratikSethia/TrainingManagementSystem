$(document).ready(function() {
	/*$('#M1').hover(function(){
		$('#M1MenuList').show();
	},
	function(){
		$('#M1MenuList').hide();
	}
	);*/
	/*	$(this).click(function(){
	 $('.list').toggle();
	
	 });*/

	$('.menu').each(function() {

		var menuListId = this.id;
		$('#' + menuListId).click(function() {

			$.ajax({
				type : 'GET',
				url : 'SubMenuListServlet',
				dataType : 'json',
				data : 'menuListId=' + menuListId,
				success : function(data, textStatus, jqXHR) {

					var menu = eval(data);
					var flag = true;
					var table = document.getElementById("table");
					for(var i = table.rows.length-1; i >= 0; i--)
					{
					    table.deleteRow(i);
					}
					if(flag==true){
					
					
						
						for (var count = 0; count < menu.length; count++) {
						
							var row = table.insertRow(-1);
							var cell1 = row.insertCell(0);
							cell1.innerHTML = menu[count].subMenuRole;
						}
						
					}
					flag = false;


					$('#M1MenuList').toggle();

				},
				error : function(data) {
					alert('fail');
				}
			});

			/*var xhttp;    
			 
			  xhttp = new XMLHttpRequest();
			  xhttp.onreadystatechange = function() {
			    if (xhttp.readyState == 4 && xhttp.status == 200) {
			       // var myArr = JSON.parse(xhttp.responseText);
			    	
			    }else{
			    	
			    	alert("fail");
			    }
			  };
			  xhttp.open("GET", "SubMenuListServlet?menuListId="+menuListId , true);
			  xhttp.send();*/
		});

	});
})