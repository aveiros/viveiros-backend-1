function show(selector) {
    $(selector).show();
}

function setMessage(msg) {
    $("#message").text(msg);
}

function handleLogin() {
    var email = $("#email").val();
    var password = $("#password").val();

    if(!email.length) {
        setMessage("Please enter an email.");
        return;
    }

    if(!password.length) {
        setMessage("Please enter a password.");
        return;
    }

    setMessage("");
    var body = { email: email, password: password };
    var login = $.ajax({ type: "POST", url: "/login", data: JSON.stringify(body), contentType: "application/json", dataType: 'json' });
    login.done(function(data) {
        if(data.error) {
            switch(data.error) {
                case "USER_NOT_FOUND":
                   setMessage("User not found.");
                   break;
                case "PASSWORD_INCORRECT":
                   setMessage("Password incorrect.");
                   break;
                default:
                   setMessage("Unknown error.");
            }
        } else {
            document.location.href = "/members";
        }
    });
}

$(document).ready(function () {
    var user = $.ajax({ type: "GET", url: "/user/me" });
    user.done(function() {
        document.location.href = "/members";
    }).fail(function() {
        show("#container");
    });
});
