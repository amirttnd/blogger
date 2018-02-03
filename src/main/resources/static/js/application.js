$(document).ready(function () {
    CKEDITOR.editorConfig = function (config) {
        config.language = 'es';
        config.height = 100;
        config.toolbarCanCollapse = true;
        config.uiColor = '#9AB8F3'


    };
    CKEDITOR.replaceAll("ckTextEditor");


});