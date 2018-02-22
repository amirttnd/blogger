angular
    .module("techBlogger")
    .controller("UserController", ["User", "$location", function (User, $location) {
        var self = this;
        var firstPage = 1;

        self.users = [];
        self.currentPage = 1;
        self.max = 10;

        self.list = function (page) {
            page = page || firstPage;
            var params = {page: page, max: self.max};

            if ($location.search()["query"]) {
                self.query = params.query = $location.search()["query"];
            }

            User.list(params, function (response) {
                self.users = response.users;
                self.currentPage = page;
                self.total = response.total;
            })
        };

        self.search = function () {
            $location.search({query: self.query});
            self.list(firstPage);
        };

        self.edit = function () {
            var email = $location.search()["email"];

            User.roles(function (response) {
                self.roles = response.roles;
            });

            if (email) {
                User.findBy({email: email}, function (response) {
                    self.user = response.user;
                })
            }
        };

        self.save = function () {
            if (self.user) {
                var authorities = _.map(self.user.authorities, function (role) {
                    return role.authority;
                });
                var params = {
                    email: self.user.email,
                    fullName: self.user.fullName,
                    enabled: self.user.enabled,
                    authorities: authorities,
                    password: self.user.passwd
                };
                User.save(params, function (response) {
                    console.log(response)
                })
            }
        }
    }]);