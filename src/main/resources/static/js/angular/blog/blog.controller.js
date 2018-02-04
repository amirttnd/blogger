angular
    .module("techBlogger")
    .controller("BlogController", ["Blog", "Category", "$scope", function (Blog, Category, $scope) {
        var self = this;

        self.tags = [];

        self.relatedCategories = [];

        self.mainCategory = {};


        Category.tree(function (response) {
            self.tree = response.tree;
            console.log(self.tree)
        });

        self.setMainCategory = function (category) {
            self.mainCategory = category;
        };

        self.addToRelatedCategory = function (category) {
            self.relatedCategories.push(category);
        }
    }]);