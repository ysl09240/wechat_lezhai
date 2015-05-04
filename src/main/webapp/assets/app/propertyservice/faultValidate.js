/**
 * Created by songlinyang on 15-4-24.
 */


$(function() {


    jQuery.validator.addMethod("isPhone", function(value, element) {
        console.log("llllllllllllllllll");
        var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
        return this.optional(element) || (mobile.test(value));
    }, "电话号码不正确");

    $("#addFaultForm").validate({
            rules: {
                description:{
                    required:true
                },
                repairPeople:{
                    required:true
                },
                contactNumber:{
                    required: true,
                    maxlength:11,
                    isPhone:true
                },
                adress:{
                    required:true
                }

            },
            messages: {
                description:{
                    required:"请输入报修描述"
                },
                repairPeople:{
                    required:"请输报修人"
                },
                contactNumber:{
                    required: "请输入联系电话",
                    maxlength:"确认电话不能大于11个字符"
                },
                address:{
                    required:"请输入报修地点"
                }
            },
            errorPlacement: function(error, element) {
                element.addClass("error");
                //error.appendTo(element.parent());
            },
            submitHandler:function(form){
                form.submit();
            }
        });
})
