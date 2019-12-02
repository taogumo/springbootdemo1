define(function (require, exports, module) {
    var m1 = require('common/m1');
    m1.show2();
    var m22 = require('common/m2');
    m22.showm2();

    var initUI = function () {
        $('#h').text("测试！！！！！！！！！！！！！！！！！！！")
        alert("测试方法")
    };

    exports.show = function () {
        alert('初始化方法show')
    };
    exports.data = "测试";

    return {
        initUI: initUI
    }
});