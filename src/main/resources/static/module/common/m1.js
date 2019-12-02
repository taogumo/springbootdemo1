define(function (require, exports, module) {
    //私有方法
    var data1 = 1;
    var show1 = function () {
        alert('m1show1')
    };

    //公共方法
    exports.data2 = 2;
    exports.show2 = function () {
        alert('m2show2')
    }
});