angular
    .module("techBlogger")
    .factory("Category", ["$resource", function ($resource) {
        return $resource("/api/category/:action?id=:id", {id: "@id"}, {
            tree: {
                method: "GET",
                params: {
                    action: "tree"
                }
            }
        })
    }]);