/**
 *
 * @FileName     :  checkbox.group.js
 * @Encoding     :  UTF-8
 * @Link         :  http://lezhai365.com
 * @Create On    ： 2013-11-15 3:55:27
 * @Author       :  Hui.Wang [wanghui@lezhai365.com]
 * @Version      :  1.0
 * @Description  :
 * group checkbox select plugin
 */
(function($) {
    $.fn.checkboxGroup = function(options) {
        var settings = $.extend({
            groupName: "group_name",
            groupSelector: "",
            enabledOnly: false
        }, options || {});
        /**控制checkBox*/
        var ctrl_box = this;

        var grp_slctr = (settings.groupSelector == "") ? "input[name=" + settings.groupName + "]" : settings.groupSelector;
        /**直选中可用的checkbox*/
        if (settings.enableOnly) {
            grp_slctr += ":enabled";
        }

        ctrl_box.click(function(event) {
            var e = event || window.event;
            chk_val = e.target.checked;
            $(grp_slctr).attr("checked", chk_val);
            $(ctrl_box).attr("checked", chk_val);
        });
        /**单个checkbox的click事件*/
        $(grp_slctr).click(function() {
            /**若果checkbox组中有一个被取消选中，则取消ctrl_box的选中*/
            if (!this.checked) {
                $(ctrl_box).attr("checked", false);
            } else {
                if ($(grp_slctr).size() == $(grp_slctr + ":checked").size()) {
                    $(ctrl_box).attr("checked", true);
                }
            }
        });
        return this;
    };
})(jQuery);
