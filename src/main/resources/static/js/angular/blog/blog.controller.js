angular
    .module("techBlogger")
    .controller("BlogController", ["Blog", "Category", "$scope", function (Blog, Category, $scope) {
        var self = this

        Category.tree(function (response) {
            self.tree = response.tree;
            console.log(self.tree)
        })
    }]);