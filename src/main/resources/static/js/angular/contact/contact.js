angular
    .module("techBlogger")
    .factory("Contact", ["$resource", function ($resource) {
        return $resource("/api/contact/:action", {}, {
            save: {
                method: "POST",
                params: {
                    action: "save"
                }
            },
            delete: {
                method: "DELETE",
                params: {
                    action: "delete"
                }
            },
            get: {
                method: "GET",
                params: {
                    action: "get"
                }
            }
        })
    }]);