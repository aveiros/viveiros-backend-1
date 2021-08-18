function show(selector) {
    $(selector).show();
}

function setMessage(msg) {
    $("#message").text(msg);
}

function handleLogout() {
    var logout = $.ajax({ type: "GET", url: "/logout" });
    logout.done(function() {
        document.location.href = "/";
    });
}

$(document).ready(function () {
    var user = $.ajax({ type: "GET", url: "/user/me" });
    user.done(function(data) {
        setMessage("Hello :)");
        show("#container");
    });
});