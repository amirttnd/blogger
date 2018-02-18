angular
    .module("techBlogger")
    .controller("UserController", ["User", "$location", function (User, $location) {
        var self = this;
        self.users = [];

        self.list = function () {
            User.list(function (response) {
                self.users = response.users;
                console.log(self.users)
            })
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