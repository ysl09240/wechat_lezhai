/**
 * Created by songlinyang on 15-4-24.
 */

$(function () {

    setTimeout(function () {
        console.log($.cselect);
        $('#estate-select').cselect({
            element: $('#estate-select'),
            async: {
                enable: true,
                uri: '/' + window.wx.pmcSignName + '/infomation/house/tree',
                autoParam: ['id', 'childtype', 'floor', 'pid'],
                defaultParam:function(){
                    return {
                        openid:window.wx.openid,
                        housingEstateId:$('#estate-select>option:selected').val()
                    };
                }
            }
        });
    }, 0);

    jQuery.validator.addMethod("isPhone", function (value, element) {
        var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
        return this.optional(element) || (mobile.test(value));
    }, "电话号码不正确");

    $("#authhouseForm").validate({
        rules: {
            houseInfoId:{
                required:true
            },
            ownerName:{
                required:true
            },
            ownerPhone: {
                required: true,
                maxlength: 11,
                isPhone: true
            }
        },
        messages: {
            houseInfoId:{
              required:"请选择房间"
            },
            ownerName:{
                required:"请输入业主姓名"
            },
            ownerPhone: {
                required: "请输入联系电话",
                maxlength: "确认电话不能大于11个字符"
            }
        },
        errorPlacement: function (error, element) {
            error.appendTo($(".wx-item"));
        },
        submitHandler: function (form) {
            form.submit();
        }
    });
});
