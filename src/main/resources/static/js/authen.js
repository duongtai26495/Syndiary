

const username = document.getElementById("username_login");
const password = document.getElementById("password_login");	

function validate_login(){
if(username.value === "" || password.value === ""){
console.log("Check loi")
	return false;
}	
else{
	return true;
}

}

$(".login_form").keyup(function(e) {
      if (e.which == 13) {
        password.focus();
    }
 
    });
    
window.onload = function() {
  username.focus();
}
