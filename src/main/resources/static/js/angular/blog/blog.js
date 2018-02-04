angular
    .module("techBlogger")
    .factory("Blog", ["$resource", function ($resource) {
        return $resource("/api/blog/:action?id=:id", {id: "@id"}, {
            save: {
                method: "POST",
                params: {
                    action: "save"
                }
            }
        })
    }]);