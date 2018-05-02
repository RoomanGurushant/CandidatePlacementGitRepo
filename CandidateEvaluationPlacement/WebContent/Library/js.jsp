<script src="../assets/js/jquery.min.js" type="text/javascript"></script>
<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCUg7WMAy31k0Mn39WGP8BV2uBzwr3XnlA"></script>
<script src="../assets/js/home/jquery.gmap.min.js"></script>

<script src="../assets/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../assets/js/bs-select.js" type="text/javascript"></script>
<script src="../assets/js/jquery_toast.js" type="text/javascript"></script>
<script src="../assets/js/jquery-inputmask.min.js" type="text/javascript"></script>

<!-- new timepicker -->
<script type="text/javascript" src="../assets/js/bootstrap-material-datetimepicker.js" async></script>
<!-- new timepicker -->
<!-- SELECT 2 -->
<script src="../assets/js/select2.full.min.js" type="text/javascript"></script>
<script src="../assets/js/components-select2.min.js" type="text/javascript"></script>

<!-- ========== -->
<!-- DROPDOWN LIST MULTI SELECT -->
<script src="../assets/js/bootstrap-multiselect.js" type="text/javascript" ></script>
<!-- ========================= -->
<script src="../assets/js/footable.js" type="text/javascript" async></script>
<script type="text/javascript" src="../Library/Validate.js" async></script>
<script type="text/javascript" src="../Library/dynacheck.js" async></script>
<script>
					$(window).load(function(){
						 $('.date-mask').inputmask('d/m/y', {
						      placeholder: 'dd/mm/yyyy'
						});
						 $('.datetime-mask').inputmask('d/m/y h:s', {
						      placeholder: 'dd/mm/yyyy hh:mm'
						});
					})
					</script>

<script>
<!-- ****************** modal window width to 80%****************** -->
if($(window).width() > 992)
{	
	var window_width = $(window).width();
	window_width = 0.8*window_width;
	$(".modal-80").css({'width':window_width});
}

<!-- ****************** modal window Ajax call to check for success or error****************** -->
function showHintModal(url,Hint,data) {
	$('#'+Hint).html('<div id=loading align=center><img align=center src=\"../admin-ifx/loading.gif\" /></div>');
	$.ajax({
		type:'POST',
		url:url,
		data:data,
		cache: false,
		success: function (data){
// 			alert(data);
			if(data.trim() != 'SignIn'){
				if(data.trim().includes('Error') == false){
					UINotific8.init(data);
					setTimeout('$("#Hintclicktocall, #Hintclicktocall80").modal("hide");',1000);
				}
				else{
					UINotific8.init(data);
					$('#'+Hint).fadeIn(500).html('');
				}
			} else{
				window.location.href = "../portal/";
			}
		}
	});
}
function showHintDoc(url,Hint,data) {
	$('#'+Hint).html('<div id=loading align=center><img align=center src=\"../admin-ifx/loading.gif\" /></div>');
	$.ajax({
		type:'POST',
		url:url,
		data:data,
		processData: false,
		contentType:false,
		cache: false,
		success: function (data){
			if(data.trim() != 'SignIn'){
				if(data.trim().includes('Error') == false){
					UINotific8.init(data);
					setTimeout('$("#Hintclicktocall, #Hintclicktocall80").modal("hide");',1000);
				}
				else{
					$('#'+Hint).fadeIn(500).html("<center><font color='red'><b>"+data.trim()+"</b></font></center>");
				}
			} else{
				window.location.href = "../portal/";
			}
		}
	});
}
	
$("#Hintclicktocall, #Hintclicktocall80").on('shown.bs.modal',function(){
	   $('.datetimepicker').bootstrapMaterialDatePicker({ format : 'DD/MM/YYYY HH:mm', switchOnClick : true, clearButton : true });
	   $('.datepicker').bootstrapMaterialDatePicker({ format : 'DD/MM/YYYY', switchOnClick : true,weekStart : 0, time: false,  clearButton : true });
	   $('.timepicker').bootstrapMaterialDatePicker({ format : 'HH:mm', switchOnClick : true, shortTime:true, date: false, clearButton : true });
	   FormElements();
	   
	   $('.date-mask').inputmask('d/m/y', {
		      placeholder: 'dd/mm/yyyy'
		});
		 $('.datetime-mask').inputmask('d/m/y h:s', {
		      placeholder: 'dd/mm/yyyy hh:mm'
		});
});
$("#HintclicktocallMap").on('shown.bs.modal',function(){
	/* Start of Scripts for google maps in model window */
	   $.getScript('../assets/js/home/jquery.gmap.min.js');
	   $.getScript('http://maps.googleapis.com/maps/api/js?key=AIzaSyCUg7WMAy31k0Mn39WGP8BV2uBzwr3XnlA');
	   loadMap();
	   /* End of Scripts for google maps in model window */
});

 $(function() {
	 $(".hint").hide();
 });
 FormElements();
 
 
 
</script>
<script language="JavaScript" type="text/javascript"> 
	function loadMap() {
		var latitude = $("#latitude").val();
		var longitude = $("#longitude").val(); 
		var location_name = $("#location_name").val();
		
// 			var mapProp = new google.maps.LatLng(12.9981382, 77.5523740);
		var mapProp = new google.maps.LatLng(latitude, longitude);
		var mapCanvas = document.getElementById('googleMap');
		var map_options = {
			center : mapProp,
			zoom : 16,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		}
		var map = new google.maps.Map(mapCanvas, map_options);

		var marker = new google.maps.Marker({
			position : mapProp,
			draggable : true,
			animation : google.maps.Animation.DROP,
			map : map
		});
		marker.setMap(map);
		marker.addListener('click', toggleBounce);

		var infowindow = new google.maps.InfoWindow(
				{
					content : location_name
// 						content : "Rajajinagar, Bangalore - 560010. "
				});
		infowindow.open(map, marker);
	}
	function toggleBounce() {
		if (marker.getAnimation() !== null) {
			marker.setAnimation(null);
		} else {
			marker.setAnimation(google.maps.Animation.BOUNCE);
		}
	}
	google.maps.event.addDomListener(window, 'load', loadMap);
	
	$("#HintclicktocallMap").on('shown.bs.modal',function(){
		google.maps.event.trigger(map, "resize");
	});
</script>	

  