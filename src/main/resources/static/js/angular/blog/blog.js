angular
    .module("techBlogger")
    .factory("Blog", ["$resource", function ($resource) {
        return $resource("/api/blog/:action?id=:id", {id: "@id"}, {
            save: {
                method: "POST",
                params: {
                    action: "save"
                }
            },
            partialUpdate: {
                method: "POST",
                params: {
                    action: "partialUpdate"
                }
            },
            list: {
                method: "GET",
                params: {
                    action: "list"
                }
            },
            get: {
                method: "GET",
                params: {
                    action: "get"
                }
            },
            toggleRecommendation: {
                method: "GET",
                params: {
                    action: "toggleRecommendation"
                }
            },
            delete: {
                method: "GET",
                params: {
                    action: "delete"
                }
            }
        })
    }]);