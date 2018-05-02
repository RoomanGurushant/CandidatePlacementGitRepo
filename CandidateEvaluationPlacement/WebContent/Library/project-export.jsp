// JavaScript Document function SelectProject(id, name, indexid, compid,
spanid) { alert("hiiiiiii"); var
projectid=window.parent.document.getElementById("project_id").value; var
spanidObj=window.parent.document.getElementById("span_prop_client_id");
var
spanprojectid=window.parent.document.getElementById("span_project_id");
var compstr; if(projectid==id) { spanprojectid.value=projectid;
id=projectid; } else { spanprojectid.value=id; id=id; } compstr = "
<a href=../project/project-list.jsp?project_id= " + id + " target=_blank><b>"
		+ name + " (" + id + ")</b></a>
"; spanidObj.innerHTML = compstr;
showHint('project-check.jsp?status='+status+'&project_id='+id+'&list_cartitems=yes',session_id,'quote_details');
window.parent.$('#dialog-modal').dialog('close'); }

