var PayOrder = {
    options:{
        //获取base路径
        path : GV.ctxPath
    },

    updateStatus: function (orderId) {
        $.ajax({
            type: "POST",
            url: GV.ctxPath + "/payOrder/updateStatus",
            data: {"orderId": orderId},
            dataType: "json",
            success: function(data){
                if (data.code == '200'){
                    alert("更新成功！");
                    window.location.href = "list";
                } else {
                    alert("更新异常！" + data.msg);
                }
            },
            error: function () {
                alert("更新异常！");
                return false;
            }
        });
    },

    refund: function (orderId, payUniqueOrderNo) {
        $.ajax({
            type: "POST",
            url: GV.ctxPath + "/payOrder/refund",
            data: {"orderId": orderId,
                "payUniqueOrderNo": payUniqueOrderNo},
            dataType: "json",
            success: function(data){
                if (data.code == '200' || data.code == '500'){
                    alert(data.msg);
                    window.location.href = GV.ctxPath + "/refundOrder/list";
                }
            },
            error: function () {
                alert("退款异常！");
                return false;
            }
        });
    }

}