angular
    .module("techBlogger")
    .factory("Category", ["$resource", function ($resource) {
        return $resource("/api/category/:action?id=:id", {id: "@id"}, {
            tree: {
                method: "GET",
                params: {
                    action: "tree"
                }
            },
            addChild: {
                params: {
                    action: "addChild"
                }
            },
            save: {
                params: {
                    action: "save"
                }
            },
            update: {
                params: {
                    action: "update"
                }
            },
            toggle: {
                params: {
                    action: "toggle"
                }
            },
            delete: {
                params: {
                    action: "delete"
                }
            },
            show: {
                method: "GET",
                params: {
                    action: "show"
                }
            }
        })
    }]);