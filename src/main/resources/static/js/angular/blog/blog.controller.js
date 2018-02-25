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
        self.blogs = [];
        self.pages = 0;
        self.max = 10;
        self.isInProgress = false;
        self.User = User;

        /**
         * Used in front page on blog list page
         */
        self.list = function (page) {
            if (!self.isInProgress) {
                self.isInProgress = true;
                page = page || firstPage;

                var params = {page: page, max: self.max};

                if ($location.search()["query"]) {
                    self.query = params.query = $location.search()["query"];
                }

                if ($location.search()["categoryQ"]) {
                    self.categoryQ = params.categoryQ = $location.search()["categoryQ"];
                }

                Blog.list(params, function (response) {
                    self.blogs = _.concat(self.blogs, response.blogs || []);
                    self.currentPage = page;
                    self.total = response.total;
                    self.pages = response.pages;
                    self.isInProgress = false;
                });
            }
        };


        /**
         * Used in admin on blog list page
         */

        self.getAll = function (page) {

            page = page || firstPage;

            var params = {page: page, max: self.max};

            if ($location.search()["query"]) {
                self.query = params.query = $location.search()["query"];
            }

            if ($location.search()["categoryQ"]) {
                self.categoryQ = params.categoryQ = $location.search()["categoryQ"];
            }

            Blog.list(params, function (response) {
                self.blogs = response.blogs || [];
                self.currentPage = page;
                self.total = response.total;
                self.pages = response.pages;
            });
        };

        /**
         * Used in admin on blog list page
         */
        self.search = function () {
            $location.search({query: self.query});
            self.getAll(firstPage)
        };


        /**
         * Used in front page
         */
        self.nextPage = function () {
            if (self.currentPage < self.pages) {
                self.list(self.currentPage + 1)
            }
        };


        self.edit = function () {
            var id = $location.search()['id'];
            if (id) {
                Blog.get({id: id}, function (response) {
                    self.blog = response.blog || {};
                })
            }
        };

        self.canEdit = function (blog) {
            if (blog && blog.user) {
                return User.isAdmin || blog.user.email == User.email;
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

            if (!self.blog.id || self.canEdit(self.blog)) {
                Blog.save(params, function (response) {
                    if (response.blog) {
                        self.blog = response.blog;
                        Notification.show("Successfully saved")
                    } else {
                        Notification.show("Could not save")
                    }
                    console.log(response)
                })
            }else{
                Notification.show("You are not author of this blog.")
            }

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