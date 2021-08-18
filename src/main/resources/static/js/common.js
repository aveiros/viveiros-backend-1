$(document).ajaxComplete(function(event, xhr, settings) {
    if(xhr.status === 401 && !(document.location.pathname === "" || document.location.pathname === "/")) {
        document.location.href = "/";
    }
});