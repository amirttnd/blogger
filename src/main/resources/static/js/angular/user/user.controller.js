angular
    .module("techBlogger")
    .controller("UserController", ["User", function (User) {
        var self = this;
        self.users = [];

        self.list = function () {
            User.list(function (response) {
                self.users = response.users;
                console.log(self.users)
            })
        }
    }]);