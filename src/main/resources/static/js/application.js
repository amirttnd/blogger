$(document).ready(function () {
    $(window).scroll(function () {
        if ($(window).scrollTop() >= 135) {
            $("header.header").addClass("fixed")
        } else {
            $("header.header").removeClass("fixed")
        }
    });
});

(function (d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s);
    js.id = id;
    js.src = "https://connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.12&appId=1781663582136622&autoLogAppEvents=1";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

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

Util = {
    parseBoolean: function (value) {
        /^true$/.test(value);
    }
};