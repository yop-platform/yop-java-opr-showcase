var Refund = {
    options:{
        //获取base路径
        path : GV.ctxPath
    },

    updateStatus: function (payOrderId, refundRequestId, uniqueRefundNo) {
        $.ajax({
            type: "POST",
            url: GV.ctxPath + "/refundOrder/updateStatus",
            data: {"payOrderId": payOrderId,
                    "refundRequestId": refundRequestId,
                    "uniqueRefundNo": uniqueRefundNo},
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
    }

}