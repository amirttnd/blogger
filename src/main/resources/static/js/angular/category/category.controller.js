angular
    .module("techBlogger")
    .controller("CategoryController", ["Category", "$compile", "$scope", function (Category, $compile, $scope) {
        var self = this;


        self.categoryTree = function () {
            Category.tree(function (response) {
                self.tree = response.tree;
            });
        };

        self.addChild = function (node, parentId) {
            if (node.name) {
                Category.addChild({
                    name: node.name,
                    id: parentId
                }, function (response) {
                    if (response.status) {
                        node.subCategories = node.subCategories || [];
                        node.subCategories.push(response.category);
                        node.name = '';
                    }
                })
            }
        };

        self.toggle = function (category) {
            if (category && category.id) {
                Category.toggle({id: category.id}, function (response) {
                    if (response.status) {
                        category.active = response.category.active
                    }
                })
            }
        };

        self.deleteNode = function (id) {
            if (id) {
                Category.delete({id: id}, function (response) {
                    if (response.status) {
                        self.categoryTree()
                    }
                });
            }
        };

        self.allowAddToChild = function (node) {
            node.canAddChild = true;

        };

        self.save = function () {
            if (self.categoryName) {
                Category.save({name: self.categoryName}, function (response) {
                    self.tree = self.tree || [];
                    if (response.status) {
                        self.tree.push({category: response.category});
                        self.categoryName = ''
                    }
                })
            }
        };

        self.categoryTree()

    }]);
