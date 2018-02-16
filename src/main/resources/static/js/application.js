$(document).ready(function () {
    $(window).scroll(function () {
        if ($(window).scrollTop() >= 135) {
            $("header.header").addClass("fixed")
        } else {
            $("header.header").removeClass("fixed")
        }
    })
});

var Scroll = {
    toTop: function () {
        $('html,body').animate({scrollTop: 0}, 1000);
    }
};

var Notification = {
    show: function (message, options) {
        var $notification = $("#notification");
        if (options) {
            if (options.class && typeof options.class === "string") {
                $notification.addClass(options.class)
            }

            if (options.css && typeof  options.css == "object") {
                $notification.css(options.css)
            }
        }
        if (message) {
            $notification.html(message).addClass("show");
            setTimeout(function () {
                Notification.hide();
            }, 2500)
        }
    },
    hide: function () {
        $("#notification")
            .removeClass("show")
            .removeAttr("class")
            .removeAttr("style");

    }
};