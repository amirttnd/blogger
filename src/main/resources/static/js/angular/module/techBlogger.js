angular
    .module("techBlogger", ['ngJsTree', 'ngResource', 'ngTagsInput', 'ckeditor'])
    .config(function ($locationProvider) {
        $locationProvider.html5Mode({
            enabled: true,
            requireBase: false
        });
    });