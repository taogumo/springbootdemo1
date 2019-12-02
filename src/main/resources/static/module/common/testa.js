define(function (require, exports, module) {
    var data = 1;
    var show11111 = function () {
        alert("测试")
    };

    exports.data1 = 1;
    exports.show2 = function () {
        alert("测试2")
    };

    var testb = require("common/testb");
    testb.showTestB()


    exports.initUI = function () {
        $('#h').text("测试！！！！！！！！！！！！！！！！！！！")
        alert("测试方法")
        testb.showTestB()
    };
    testb.showTestC();
    /*
        return{
            data1:data,
            show1:show
        }*/
});