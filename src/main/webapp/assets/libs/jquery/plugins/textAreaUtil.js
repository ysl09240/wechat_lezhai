/**
 * <pre>
 * lezhai365.com 级联下拉 组件模块
 * </pre>
 * @fileName     :  textAreaUtil.js
 * @encoding     :  UTF-8
 * @link         :  http://lezhai365.com
 * @create On    ： 2014-10-17 1:10:34
 * @author       :  Hui.Wang [huzi.wh@gmail.com]  <http://imhuzi.net>
 * @version      :  1.0
 * @description  :
 * <pre>
 * - requirejs 模块
 * - 基于jquery1.9.1
 * </pre>
 */

'use strict';
define(['jquery'], function ($) {

    var selection = document.selection;

    return {
        /**
         * @method getCursorPosition 获取鼠标位置
         * @param ta textarea element
         * @return pos
         */
        getCursorPos: function (ta) {
            var pos = 0;
            if ($.support.msie) {
                ta.focus();
                var range = null;
                range = selection.createRange();
                var rangeCopy = range.duplicate();
                rangeCopy.moveToElementText(ta);
                rangeCopy.setEndPoint("EndToEnd", range);
                ta.selectionStart = rangeCopy.text.length - range.text.length;
                ta.selectionEnd = ta.selectionStart + range.text.length;
                pos = ta.selectionStart;
            } else {
                if (ta.selectionStart || ta.selectionStart == "0") {
                    pos = ta.selectionStart;
                }
            }
            return pos;
        },
        selectionStart: function (ta) {
            if (!selection) {
                return ta.selectionStart;
            }
            ta.focus();
            var range = selection.createRange(),
                    sls = 0;
            var h = document.body.createTextRange();
            h.moveToElementText(ta);
            for (sls; h.compareEndPoints("StartToStart", range) < 0; sls++) {
                h.moveStart("character", 1);
            }

            return sls;
        },
        selectionBefore: function (f) {
            return f.value.slice(0, this.selectionStart(f))
        },
        selectText: function (f, g, h) {
            f.focus();
            if (!selection) {
                f.setSelectionRange(g, h);
                return;
            }
            var j = f.createTextRange();
            j.collapse(1);
            j.moveStart("character", g);
            j.moveEnd("character", h - g);
            j.select()
        },
        insertText: function (ta, text, k, j) {
            ta.focus();
            j = j || 0;
            if (!selection) {
                var l = ta.value,
                        n = k - j,
                        f = n + text.length;
                ta.value = l.slice(0, n) + text + l.slice(k, l.length);
                this.selectText(ta, f, f);
                return;
            }
            var m = selection.createRange();
            m.moveStart("character", -j);
            m.text = text
        },
        getSelectedText: function (ta) {
            var sText = "";
            var stuff = function (_ta) {
                if (_ta.selectionStart != undefined && _ta.selectionEnd != undefined) {
                    return _ta.value.substring(_ta.selectionStart, _ta.selectionEnd);
                } else {
                    return "";
                }
            };
            if (window.getSelection) {
                sText = stuff(ta)
            } else {
                sText = selection.createRange().text;
            }
            return sText;
        },
        setCursor: function (ta, lastPos, firstPos) {
            lastPos = lastPos == null ? ta.value.length : lastPos;
            firstPos = firstPos == null ? 0 : firstPos;
            ta.focus();
            if (ta.createTextRange) {
                var f = ta.createTextRange();
                f.move("character", lastPos);
                f.moveEnd("character", firstPos);
                f.select()
            } else {
                ta.setSelectionRange(lastPos, lastPos + firstPos)
            }
        }
    };

});
