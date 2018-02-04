angular
    .module("techBlogger")
    .controller("BlogController", ["Blog", "Category", "$scope", function (Blog, Category, $scope) {
        var self = this;

        self.tags = [];

        self.relatedCategories = [];

        self.category = {};


        Category.tree(function (response) {
            self.tree = response.tree;
            console.log(self.tree)
        });

        self.setCategory = function (category) {
            self.category = category;
        };

        self.addToRelatedCategory = function (category) {
            var index = _.findIndex(self.relatedCategories, function (obj) {
                return category.id == obj.id;
            });

            if (index == -1) {
                self.relatedCategories.push(category);
            } else {
                console.log("Category already exist")
            }
        };

        self.removeFromCategories = function (id) {
            _.remove(self.relatedCategories, function (category) {
                return category.id == id
            });
        }
    }]);