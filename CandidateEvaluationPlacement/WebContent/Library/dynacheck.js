    eG:/Program Files/WebProjectWorkspace/.metadata/.plugins/org.eclipse.wst.jsdt.core/libraries/system.js  b	\�� bG:/Program Files/WebProjectWorkspace/.metadata/.plugins/org.eclipse.wst.jsdt.core/libraries/xhr.js  b	\� qG:/Program Files/WebProjectWorkspace/.metadata/.plugins/org.eclipse.wst.jsdt.core/libraries/baseBrowserLibrary.js  b	^� cG:/Program Files/WebProjectWorkspace/.metadata/.plugins/org.eclipse.wst.jsdt.core/libraries/dom5.js  b	\m� lG:/Program Files/WebProjectWorkspace/.metadata/.plugins/org.eclipse.wst.jsdt.core/libraries/browserWindow.js  b	\G+                                                                                                                                                                                                                                                                                                                                                                                                                                                                   -y);
						$.ajax({
							url: url,
							type: 'GET',
							success: function (data){
									//alert("data=="+data);
									if(data.trim() != 'SignIn'){
										$('#list_'+Hint).html('' + data.trim() + '');
										FormElements();
									} else{
										window.location.href = "../portal/";
									}
							}
						});
			 }else{
					$(".listbody_row_td").html('');
					$(".listbody_row").hide();
			 }
 }
 
 /*  Javascript for Footable*/
 function showHintFootable(url, Hint) {
 	$('#'+Hint).html('<div id=loading align=center><img align=center src=\"../admin-ifx/loading.gif\" /></div>');
 		$.ajax({
 			url: url,
 			type: 'GET',
 			success: function (data){ 
 				if(data.trim() != 'SignIn') {
 					$('#'+Hint).show();
 					$('#'+Hint).fadeIn(500).html('' + data + '');
 					$('.footable').footable().trigger('footable_intialized');
 					FormElements();
 				} else {
 					window.location.href = "../portal/";
 				}
 			}  
 		});
 	//alert("success");
 }

function GetReplace(str)
{
  if (str.length!=0)
  {
    var re;
    re = /&/g; 
    str = str.replace(re, "nbsp");
    str=escape(str);
  }
  return str;
}

function GetReplaceString(str)
{
  if (str.length!=0)
  {
    var re;
//    re = /&/g; 
    re = /[^\d\,]/g;
    str = str.replace(re, "");
    str=escape(str);
  }
  return str;
}
