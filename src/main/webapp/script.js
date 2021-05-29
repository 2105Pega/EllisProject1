document.body.onload = function () {
    var cookies = document.cookie.split('; ');
    var cookie;
    for(var i=0; i<cookies.length; i++) {
        cookie = cookies[i];
        let [name, value] = cookie.split('=');
        if (name === 'username') {
            document.getElementById('navbar-right-logged-out').style.display = 'none';
            document.getElementById('navbar-right-logged-in').style.display = 'initial';
            document.getElementById('welcome-message').innerHTML = "Welcome, " + value;
        }
    }

    document.getElementById('logout').onclick = function() {
        document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/JDBCBank;";
        document.cookie = "jws=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/JDBCBank;";
        location.reload();
    }
}
