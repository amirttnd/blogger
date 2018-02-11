$(document).ready(function () {
    //$('html,body').animate({scrollTop: 65}, 1000);
    $(window).scroll(function () {
        if ($(window).scrollTop() >= 135) {
            $("#navigation").addClass("fixed")
        } else {
            $("#navigation").removeClass("fixed")
        }
    })
});