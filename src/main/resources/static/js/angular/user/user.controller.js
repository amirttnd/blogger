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

            if (email) {
                User.findBy({email: email}, function (response) {
                    self.user = response.user;
                    self.roles = response.roles;
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
                    authorities: authorities
                };
                User.save(params, function (response) {
                    console.log(response)
                })
            }
        }
    }]);