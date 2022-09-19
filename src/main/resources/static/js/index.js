document.getElementById('logo_heading').innerHTML = "Syndiary".toUpperCase();

function login(){
    let username = document.getElementById("username_login").value;
    let password = document.getElementById("password_login").value;

    console.log("username: "+username+"\npassword: "+password);
    let data = new FormData();
    data.append('username', username);
    data.append('password', password);

    let config = {
      method: 'POST',
      data : data
    };
    try{
    let url_login = 'http://192.168.1.19:8080/auth/login';
    fetch(url_login, config)
                .then(response => response.json())
                .then(result => console.log(result.data))
    }catch(error){
      console.log("Error: "+error);
    };
}