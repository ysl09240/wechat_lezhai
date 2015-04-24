/**
 * Created by songlinyang on 15-4-24.
 */


$().ready(function() {


    jQuery.validator.addMethod("isPhone", function(value, element) {
        console.log("llllllllllllllllll");
        var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
        return this.optional(element) || (mobile.test(value));
    }, "电话号码不正确");

    $("#addComplaintForm").validate({
            rules: {
                contactNumber:{
                    required: true,
                    maxlength:11,
                    isPhone:true
                }
            },
            messages: {
                contactNumber:{
                    required: "请输入联系电话",
                    maxlength:"确认电话不能大于11个字符"
                }
            },
            errorPlacement: function(error, element) {
                error.appendTo($(".errorMessage"));
            },
            submitHandler:function(form){
                form.submit();
            }
        });
})
