// JavaScript Document
$(function(){
    // Dialog
    $('#dialog-modal').dialog({
        autoOpen: false,
        width: 900,
        height: 500,
        zIndex: 200,
        modal: true,
        title: "Select Contact"
    });
    $('#dialog_link').click(function(){

        $.ajax({
            //url: "home.jsp",
            success: function(data){
                $('#dialog-modal').html('<iframe src="../account/account-contact-list.jsp?group=select_contract_contact" width="100%" height="100%" frameborder=0></iframe>');
            }
        });
		$('#dialog-modal').dialog('open');
        return true;
    });
});


function ShowNameHint(){
				var fname = document.getElementById("txt_contact_fname").value;
				var lname = document.getElementById("txt_contact_lname").value;
				showHint('contract-check.jsp?contact_fname='+fname+'&contact_lname='+lname,'contract_details');
				}

//For selecting existing contact
function SelectContact(contact_id, contact_name, account_id, account_name){
//alert("contact_id=="+contact_id+" account_id=="+account_id);
    var contact_link = '<a href="../account/account-contact-list.jsp?contact_id='+contact_id+'">'+contact_name+'</a>';
	var status = document.getElementById("txt_status").value;
    var account_link = '<a href="../account/account-list.jsp?account_id='+account_id+'">'+account_name+'</a>';
	//alert("selected_account=="+selected_account);
	var selected_account = document.getElementById("span_acct_id").value;
        var contract_account = document.getElementById("acct_id").value;
      //  alert("selected_account=="+selected_account+" contract_account=="+contract_account);
	if(status!='Update' && selected_account==0){
    document.getElementById("contact_name").style.display = 'none';
    document.getElementById("contact_mobile").style.display = 'none';
//    document.getElementById("contact_phone").style.display = 'none';
    document.getElementById("contact_email").style.display = 'none';
	document.getElementById("contact_address1").style.display = 'none';
    document.getElementById("contact_city").style.display = 'none';
    document.getElementById("contact_state").style.display = 'none';
	document.getElementById("selected_contact").style.display = '';
	document.getElementById("copy_cont_address_link").innerHTML = "";
	}
    document.getElementById("span_cont_id").value = contact_id;
    document.getElementById("span_acct_id").value = account_id;
	if(status=='Add' && contract_account==0){
	document.getElementById("selected_contact_id").innerHTML = contact_link;
    document.getElementById("selected_account_id").innerHTML = account_link;
	}else if(status=='Update' || selected_account!=0){		
    document.getElementById("span_contract_contact_id").innerHTML = contact_link;
    document.getElementById("span_contract_account_id").innerHTML = account_link;
	}
	//alert("contact_id=="+contact_id);
	GetContactLocationDetails(contact_id);
	document.getElementById("contract_details").innerHTML = "";
    $('#dialog-modal').dialog('close');
}
//end

//function to copy contact address in billing address fields
                function CopyContactAddress(){
        var contact_address1 = document.getElementById("txt_contact_address1").value;
        var city_obj = document.getElementById("dr_contact_city_id");
        var state_obj = document.getElementById("dr_contact_state_id");
        var contact_city = city_obj.options[city_obj.selectedIndex].text;
        var contact_pin = document.getElementById("txt_contact_pin").value;
        var contact_state = state_obj.options[state_obj.selectedIndex].text;

        document.getElementById("txt_bill_address").value = contact_address1;
        if(contact_city!='Select'){
            document.getElementById("txt_bill_city").value = contact_city;
        }
        document.getElementById("txt_bill_pin").value = contact_pin;
        if(contact_state!='Select'){
            document.getElementById("txt_bill_state").value = contact_state;
        }
    }


//function for populating contract address according to the contact
function GetContactLocationDetails(contract_contact_id){
        showHint('../service/contract-check.jsp?contract_contact_id='+contract_contact_id,contract_contact_id,'contract_contact');

        setTimeout('PopulateContactLocationDetails()',800);
    }

	function PopulateContactLocationDetails(){	
        var contact = document.getElementById('account').value;		
		var contact_arr = contact.split('[&%]');
        document.getElementById('txt_bill_address').value=contact_arr[0];
        document.getElementById('txt_bill_city').value=contact_arr[1];
        document.getElementById('txt_bill_pin').value=contact_arr[2];
        document.getElementById('txt_bill_state').value=contact_arr[3];
//alert(supplier_arr[10]);

       // document.getElementById('span_acct_id').value=contact_arr[5];
        //document.getElementById('span_cont_id').value=contact_arr[7];
	}


	//function to copy billing address in shipping address fields
	function CopyBillingAddress(){
		var billing_address = document.getElementById("txt_bill_address").value;
		var billing_city = document.getElementById("txt_bill_city").value;
		var billing_pin = document.getElementById("txt_bill_pin").value;
		var billing_state = document.getElementById("txt_bill_state").value;
		document.getElementById("txt_ship_address").value = billing_address;
		document.getElementById("txt_ship_city").value = billing_city;
		document.getElementById("txt_ship_pin").value = billing_pin;
		document.getElementById("txt_ship_state").value = billing_state;
		}

function CheckNumeric(num){
    if(isNaN(num) || num=='' || num==null)
    {
        num=0;
    }
    return num;
}