angular
    .module("techBlogger")
    .controller("CommentController", ["Comment", "$window", function (Comment, $window) {
        var self = this;
        self.comments = [];
        self.blogId = $window.blogId;
        self.commentModel = {};

        self.list = function () {
            Comment.findAllByReferenceId({referenceId: blogId}, function (response) {
                console.log(response);
                self.comments = response.comments
            })
        };

        self.getCommentSize = function () {
            if (self.comments) {
                return self.comments.length;
            }
            return 0;
        };

        self.save = function () {
            var params = jQuery.extend({referenceId: self.blogId}, self.commentModel);
            Comment.save(params, function (response) {
                console.log(response);
                if (response.status) {
                    self.comments.unshift({comment: response.comment});
                }
            })
        };

        self.delete = function (comment) {
            Comment.delete({id: comment.id}, function (response) {
                comment.isDeleted = response.status;
            })
        }
    }]);