angular
    .module("techBlogger")
    .controller("CategoryController", ["Category", function (Category) {
        var self = this;

        Category.tree(function (response) {
            self.tree = response.tree;
            setTimeout(function () {
                $("#category-tree").jstree({
                    "types": {
                        "default": {
                            "icon": "fa fa-flash"
                        }
                    },
                    "core": { // core options go here
                        "multiple": true, // no multiselection
                        "check_callback": true,
                        "themes": {
                            "dots": true, // no connecting dots between dots
                            "variant": "large"

                        },
                        "state": {
                            opened: true
                        }
                    },
                    "plugins": ["state", "types"] // activate the state plugin on this instance
                });
            }, 0)
        });

    }]);