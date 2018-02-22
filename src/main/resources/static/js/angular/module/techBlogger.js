angular
    .module("techBlogger", ['ngJsTree', 'ngResource', 'ngTagsInput', 'ckeditor', 'bw.paging', "ngSanitize"])
    .config(function ($locationProvider) {
        $locationProvider.html5Mode({
            enabled: true,
            requireBase: false
        });
    });