angular
    .module("techBlogger")
    .factory("Comment", ["$resource", function ($resource) {
        return $resource("/api/comment/:action/:id", {id: "@id"}, {
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
            },
            findAllByReferenceId: {
                method: "GET",
                params: {
                    action: "findAllByReferenceId"
                }
            }
        })
    }]);