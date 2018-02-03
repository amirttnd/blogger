angular
    .module("techBlogger")
    .controller("CategoryController", ["Category", "$compile", "$scope", function (Category, $compile, $scope) {
        var self = this;

        var jsTreeConfig = {
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
        };

        Category.tree(function (response) {
            self.tree = response.tree;
            setTimeout(function () {
                $("#category-tree").jstree(jsTreeConfig);
                $compile($("#category-tree").html())($scope);
                self.tree[0].subCategories.push({id:52,name:"dfadsf"})

            }, 0)
        });

        self.addChild = function (node) {
            alert()
            if (angular.isDefined(node) && node.subCategories) {
                node.subCategories.push({})
            }
        }

    }]);