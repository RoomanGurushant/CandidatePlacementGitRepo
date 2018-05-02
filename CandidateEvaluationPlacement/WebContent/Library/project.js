// JavaScript Document
 $(function(){
				// Dialog
                $('#dialog-modal').dialog({
                    autoOpen: false,
                    width: 900,
                    height: 500,
                    zIndex: 200,
                    modal: true,
                    title: "Select Project"
				});
                $('#dialog_link').click(function(){
                    $.ajax({
                        //url: "home.jsp",
                        success: function(data){
                            $('#dialog-modal').html('<iframe src="../project/project-list.jsp?group=selectproject" width="100%" height="100%" frameborder=0></iframe>');
					}
                    });
                    $('#dialog-modal').dialog('open');
				return true;
                });
		});

function SelectProject(id, name, indexid, compid, spanid)
{ 
    var projectid=window.parent.document.getElementById("project_id").value; 
    //var spanidObj=window.parent.document.getElementById("span_prop_client_id");	
    var spanprojectid=window.parent.document.getElementById("span_project_id");
    var compstr;
    if(projectid==id)
    {
        spanprojectid.value=projectid;
        id=projectid;
    }
    else
    {
        spanprojectid.value=id;
        id=id;
    }	
 compstr = "<a href=../project/project-list.jsp?project_id=" + id + " target = _blank><b>" + name + " (" + id + ")</b></a>";		
	//spanidObj.innerHTML = compstr;
    window.parent.document.getElementById("span_prop_client_id").innerHTML = compstr;
	window.parent.showHint('project-check.jsp?project_id='+id,'dr_task_id');
	window.parent.$('#dialog-modal').dialog('close');
}

