angular
    .module("techBlogger")
    .controller("CategoryController", ["Category", "$compile", "$scope", function (Category, $compile, $scope) {
        var self = this;

        var jsTreeConfig = {
            "types": {
                "default": {
                    "icon": "fa fa-flash"
                }
            },
            "core": { // core options go here
                "multiple": true, // no multiselection
                "check_callback": true,
                "themes": {
                    "dots": true, // no connecting dots between dots
                    "variant": "large"

                },
                "state": {
                    opened: true
                }
            },
            "plugins": ["state", "types"] // activate the state plugin on this instance
        };

        Category.tree(function (response) {
            self.tree = response.tree;
            setTimeout(function () {
                var $categoryTree = $("#category-tree");
                $categoryTree.jstree(jsTreeConfig);
                var tree = angular.element($categoryTree.html());
                $categoryTree.html(tree);
                $compile(tree)($scope);
                $scope.$digest()

            }, 0)
        });

        self.addChild = function (node) {
            self.tree[0].subCategories=[]
            if (angular.isDefined(node) && node.subCategories) {
                node.subCategories.push({id: "Dafda", name: "dfdsafsdfsda"})
            }
        }

    }]);

var AngularHelper = (function () {
    var AngularHelper = function () {
    };

    /**
     * ApplicationName : Default application name for the helper
     */
    var defaultApplicationName = "techBlogger";

    /**
     * Compile : Compile html with the rootScope of an application
     *  and replace the content of a target element with the compiled html
     * @$targetDom : The dom in which the compiled html should be placed
     * @htmlToCompile : The html to compile using angular
     * @applicationName : (Optionnal) The name of the application (use the default one if empty)
     */
    AngularHelper.Compile = function ($targetDom, htmlToCompile, applicationName) {
        var $injector = angular.injector(["ng", applicationName || defaultApplicationName]);

        $injector.invoke(["$compile", "$rootScope", function ($compile, $rootScope) {
            //Get the scope of the target, use the rootScope if it does not exists
            var $scope = $targetDom.html(htmlToCompile).scope();
            $compile($targetDom)($scope || $rootScope);
            $rootScope.$digest();
        }]);
    }

    return AngularHelper;
})();