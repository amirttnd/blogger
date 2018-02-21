angular
    .module("techBlogger")
    .controller("CategoryController", ["Blog", "Category", "$scope", "$location", function (Blog, Category, $scope, $location) {
        var self = this;
        self.category = {};
        self.currentPage = 0;
        self.max = 10;
        self.totalBlogs = 0;

        self.show = function (page) {

            page = page || 0;

            var friendlyUrl = $location.search()["friendlyUrl"];

            Category.show({friendlyUrl: friendlyUrl, page: page}, function (response) {
                self.category = response.category;
                self.totalBlogs = response.category.totalBlogs || 0;
                self.currentPage = page + 1
            })
        };

        self.moreBlogs = function (page) {
            self.show(page);
        };

        self.toggleRecommendation = function (blog) {
            Blog.toggleRecommendation({id: blog.id}, function (response) {
                    blog.isRecommended = response.blog.isRecommended
                }
            )
        };


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

        self.newCategory = function () {
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

        self.update = function (category) {
            if (category) {
                Category.update(category, function (response) {
                    self.tree = self.tree || [];
                    if (response.status) {
                        category.canEdit = false;
                    }
                })
            }
        };

        self.toggleCanEdit = function (category) {
            category.canEdit = !category.canEdit;
        };

        self.categoryTree()

    }]);
