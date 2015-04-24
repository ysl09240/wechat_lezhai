/**
 * <pre>
 * lezhai365.com 级联下拉 组件模块
 * </pre>
 * @fileName     :  confirm.js
 * @encoding     :  UTF-8
 * @link         :  http://lezhai365.com
 * @create On    ： 2014-8-27 23:22:12
 * @author       :  Hui.Wang [huzi.wh@gmail.com]  <http://imhuzi.net>
 * @version      :  1.0
 * @description  :
 * <pre>
 * - requirejs 模块
 * - 基于jquery1.9.1
 * - usage: 
 * 
 * require(['confirm'],function(confirm){
 *     confirm(jqueryObj,options);
 *  })
 *  
 * </pre>
 */

'use strict';
define(function(require, exports, module) {

    var html =
            '<div class="lz-confirm">'
            + '<span class = "bg"></span>'
            + '<div class = "content">'
            + '<div class="dialog-inner"></div>'
            + '<div class="btns">'
            + '<a class="btn btn-xs btn-primary ok" href="javascript:void(0);"><span></span></a>'
            + '<a class="btn btn-xs btn-default cancel mlm" href="javascript:void(0);"><span></span></a>'
            + '</div>'
            + '</div>'
            + '</div>';

    var getPositon = function($this, settings) {
        var windows_width = document.body.scrollWidth;
        var windows_height = document.body.scrollHeight;
        var position = $this.offset()
        var pos = new Object;
        switch (settings.direction) {
            case "top":
                pos.x = position.left + $this.width() / 2.0 - settings.width / 2.0;
                pos.y = position.top - settings.height - settings.offset;
                break;
            case "bottom":
                pos.x = position.left + $this.width() / 2.0 - settings.width / 2.0;
                pos.y = position.top + $this.height() + settings.offset;
                break;
        }
        pos.x = pos.x + settings.width > windows_width ? windows_width - settings.width - 20 : pos.x;
        pos.x = pos.x < 10 ? 10 : pos.x;
        pos.y = pos.y + settings.height > windows_height ? windows_height - settings.height - 20 : pos.y;
        pos.y = pos.y < 10 ? 10 : pos.y;
        return pos;
    }

    var showDialog = function($html, pos, settings) {
        var final_pos = new Object;
        switch (settings.direction) {
            case "top":
                $html.css("top", pos.y + 40 + "px");
                final_pos.y = pos.y + 40
                break;
            case "bottom":
                $html.css("top", pos.y - 40 + "px");
                final_pos.y = pos.y - 40
                break;
        }
        $html.animate({top: pos.y, opacity: "show"}, settings.speed);
        $html.find('.ok').unbind('click').click(function() {
            $html.animate({top: final_pos.y, opacity: "hide"}, settings.speed, function() {
                if (settings.ok != null) {
                    settings.ok();
                }
                $html.remove();
            });
        });

        $html.find('.cancel').unbind('click').click(function() {
            $html.animate({top: final_pos.y, opacity: "hide"}, settings.speed, function() {
                if (settings.cancel != null) {
                    settings.cancel();
                }
                $html.remove();
            });

        });
        //延迟隐藏
        if(settings.type === 'tips'){

            setTimeout(function(){
                $html.animate({top: final_pos.y, opacity: "hide"}, settings.speed, function() {
                    if (settings.cancel != null) {
                        settings.cancel();
                    }
                    $html.remove();
                });
            },2000);
        }


    }

    var setDialog = function($html, pos, content, settings) {
        $html.attr("id", settings.id);
        $html.height(settings.height);
        $html.width(settings.width);
        $html.css("left", pos.x + "px");
        $html.css("top", pos.y + "px");
        $html.find(".content").height(settings.height - 30 - 10);
        $html.find(".content").width(settings.width - 40 - 10);
        $html.find(".dialog-inner").html(content);
        if (settings.okVal) {
            $html.find(".ok>span").html(settings.okVal);
        } else {
            $html.find(".ok").hide();
        }
        if (settings.cancelVal) {
            $html.find(".cancel>span").html(settings.cancelVal);
        } else {
            $html.find(".cancel").hide();
        }

        //如果是提示框
        if(settings.type === 'tips'){
            $html.find(".btns").hide();
        }
        return $html
    };

    //延迟隐藏
    var delayHide =  function($html, pos, settings){


    };

    var confirm = function(element, options) {
        var defaluts = {
            'id':'lz-confirm',
            'content': "您确认删除吗？",
            'classname': 'lz-confirm',
            'direction': 'top',
            'okVal': '确定',
            'cancelVal': '取消',
            'height': 100,
            'width': 200,
            'offset': 10,
            'speed': 100,
            'ok': null,
            'type':'confirm', //[confirm,tips]
            'cancel': null
        };

        var settings = $.extend(defaluts, options);

       $("#" + settings.id).remove();

        $("body").append(html);
        var $html = $("body").find("." + settings.classname + ":last");
        var pos = getPositon(element, settings);
        setDialog($html, pos, settings.content, settings);
        showDialog($html, pos, settings);


    };

    return confirm;
});


