document.getElementById('logo_heading').innerHTML = "Syndiary".toUpperCase();

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
