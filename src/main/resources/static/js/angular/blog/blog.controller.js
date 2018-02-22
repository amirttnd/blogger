angular
    .module("techBlogger")
    .controller("BlogController", ["Blog", "Category", "Comment", "$scope", "$window", "$location", function (Blog, Category, Comment, $scope, $window, $location) {
        var self = this;
        var firstPage = 1;

        self.blog = {};

        self.blog.tags = [];

        self.blog.relatedCategories = [];

        self.blog.category = {};

        self.currentPage = firstPage;

        self.max = 10;

        self.list = function (page) {
            page = page || 1;

            var params = {page: page, max: self.max};

            if ($location.search()["query"]) {
                self.query = params.query = $location.search()["query"];
            }

            if ($location.search()["categoryQ"]) {
                self.categoryQ = params.categoryQ = $location.search()["categoryQ"];

            }

            Blog.list(params, function (response) {
                self.blogs = response.blogs;
                self.currentPage = page;
                self.total = response.total;
            })
        };

        self.search = function () {
            $location.search({query: self.query});
            self.list(firstPage)
        };


        self.edit = function () {
            var id = $location.search()['id'];
            if (id) {
                Blog.get({id: id}, function (response) {
                    self.blog = response.blog || {};
                })
            }
        };


        Category.tree(function (response) {
            self.tree = response.tree;
        });

        self.save = function () {
            var params = {
                id: self.blog.id || '',
                shortHeading: self.blog.shortHeading,
                title: self.blog.title,
                briefIntroduction: self.blog.briefIntroduction,
                content: self.blog.content,
                categoryId: self.blog.category.id,
                relatedCategories: _.map(self.blog.relatedCategories, "id"),
                isPublished: self.blog.isPublished,
                tags: self.blog.tags
            };

            Blog.save(params, function (response) {
                console.log(response)
            })
        };

        self.setCategory = function (category) {
            self.blog.category = category;
        };

        self.addToRelatedCategory = function (category) {
            var index = _.findIndex(self.blog.relatedCategories, function (obj) {
                return category.id == obj.id;
            });

            if (index == -1) {
                self.blog.relatedCategories.push(category);
            } else {
                console.log("Category already exist")
            }
        };

        self.removeFromCategories = function (id) {
            _.remove(self.blog.relatedCategories, function (category) {
                return category.id == id
            });
        };
    }]);