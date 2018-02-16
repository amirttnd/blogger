angular
    .module("techBlogger")
    .controller("CommentController", ["Comment", "$window", function (Comment, $window) {
        var self = this;
        self.comments = [];
        self.blogId = $window.blogId;
        self.commentModel = {};
        self.totalComments = 0;

        self.list = function () {
            Comment.findAllByReferenceId({referenceId: blogId}, function (response) {
                console.log(response);
                self.comments = response.comments;
                self.setTotalComments();
            })
        };

        self.setTotalComments = function () {
            if (self.comments) {
                self.totalComments = _.reduce(self.comments, function (sum, elem) {
                    return sum + _.size(elem.replies) + 1;
                }, 0);
            }
        };

        self.save = function () {
            var params = jQuery.extend({referenceId: self.blogId}, self.commentModel);
            if (!self.commentModel.isInProgress) {
                self.commentModel.isInProgress = true;
                Comment.save(params, function (response) {
                    if (response.status) {
                        self.comments.unshift({comment: response.comment});
                        self.totalComments++;
                        self.commentModel.isInProgress = false;
                        Notification.show("Thanks for your comment!");
                    }
                })
            }
        };

        self.saveReply = function (comment, replies) {
            var params = jQuery.extend({referenceId: self.blogId, parentId: comment.comment.id}, comment.reply);
            if (!comment.reply.isInProgress) {
                comment.reply.isInProgress = true;
                Comment.save(params, function (response) {
                    if (response.status) {
                        replies = replies || [];
                        replies.unshift(response.comment);
                        self.totalComments++;
                        comment.reply.isInProgress = false;
                        Notification.show("Added your reply!")

                    }
                })
            }
        };

        self.delete = function (comment) {
            Comment.delete({id: comment.id}, function (response) {
                comment.isDeleted = response.status;
                if (comment.isDeleted) {
                    self.totalComments--;
                    Notification.show("Successfully deleted.")
                }
            })
        }
    }]);