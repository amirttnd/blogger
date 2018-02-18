angular
    .module("techBlogger")
    .factory("User", ["resource", function ($resource) {
        return $resource("/api/user/:action/:email", {email: "@email"}, {
            "list": {
                method: "GET",
                params: {
                    action: "list"
                }
            },
            "save": {
                method: "POST",
                params: {
                    action: "save"
                }
            },
            "findBy": {
                method: "GET",
                params: {
                    action: "findBy"
                }
            }
        })
    }]);