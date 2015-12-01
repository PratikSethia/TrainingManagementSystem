

$(document).ready(function(){
	$('#userName').focusin(function(){
		$('#userName').addClass('box');
		$('#user').text("Enter User Name");
	});
	
	$('#userName').focusout(function(){
		$('#userName').removeClass('box');
		$('#user').text("");
	});
	
	$('#password').focusin(function(){
		$('#password').addClass('box');
		$('#pass').text("Enter Password");
	});
	
	$('#password').focusout(function(){
		$('#password').removeClass('box');
		$('#pass').text("");
	});
	
	$('#userName').keyup(function(){
		var userNameLength = $('#userName').val().length;
		$('#userName').text(userNameLength);
		if(userNameLength<15){
			$('#user').text("");
		}else{
			$('#user').text("Not exceeded than 15 characters");
			
		}	
	});
	
	$('#password').keyup(function(){
		var passwordLength = $('#password').val().length;
		//$('#userName').text(userNameLength);
		if(passwordLength<=10&&passwordLength>=4){
			$('#pass').text("");
		}else{
			$('#pass').text("Password range is between 4 -10");
			
		}	
	});
	
	
	$('#register').click(function(){
		 $( "#dialog" ).dialog({
			 
			 height:450,
			 width:700,
			 show: {
		         effect: "blind",
		         duration: 500
		       },
		       hide: {
		         effect: "explode",
		         duration: 500
		         }
		       
		 });
	});
	
	   $('#check').change(function() {
	        if($('#check').is(':checked')) {
	            //alert("checked");
	            var userName = $('#userName').val();
	            var password = $('#password').val();
	         
	            $.cookie("userName",userName);
	            $.cookie("password",password);
	            alert($.cookie("userName"));
	            
	        }
	    });
	   
	   /*alert("ram")
	   $('#userName').val($.cookie("userName"));*/
	   
});



;