
let app_name = "Syndiary";
document.getElementById('logo_heading_desktop').innerHTML = app_name.toUpperCase();
document.getElementById('logo_heading_mobile').innerHTML = app_name.toUpperCase();


function openMenu() {
	document.getElementById("menu_drawer").style.left = '0%';
}
function closeMenu(){
	document.getElementById("menu_drawer").style.left = '-100%';
}
//
//
//function login(){
//    let username = document.getElementById("username_login").value;
//    let password = document.getElementById("password_login").value;
//
//    console.log("Access_token: "+localStorage.getItem("access_token"));
//
//    var formdata = new FormData();
//    formdata.append("username", username);
//    formdata.append("password", password);
//
//    var requestOptions = {
//      method: 'POST',
//      body: formdata,
//      redirect: 'follow'
//    };
//
//    fetch("http://192.168.1.15:8080/auth/login", requestOptions)
//      .then(response => response.json())
//      .then(result => localStorage.setItem("access_token",result.access_token))
//      .catch(error => console.log('error', error));
//
//    console.log("Access_token: "+localStorage.getItem("access_token"));
//}
//
