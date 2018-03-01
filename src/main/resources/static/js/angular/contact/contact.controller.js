angular
    .module("techBlogger")
    .controller("ContactController", ["Contact", function (Contact) {
        var self = this;

        self.contact = {};

        self.save = function () {
            if (_.size(self.contact) > 0) {
                Contact.save(self.contact, function (response) {
                    if (response.status) {
                        self.contact = {};
                        Notification.show("Your query has been reported!", {timeout: 5000})

                    }
                });
            }
        }

    }]);