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
            }
        })
    }]);