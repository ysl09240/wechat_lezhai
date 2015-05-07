/**
 * <pre>
 * lezhai365.com 级联下拉 组件模块
 * </pre>
 * @fileName     :  cselect.js
 * @encoding     :  UTF-8
 * @link         :  http://lezhai365.com
 * @create On    ： 2014-8-28 1:10:34
 * @author       :  Hui.Wang [huzi.wh@gmail.com]  <http://imhuzi.net>
 * @version      :  1.0
 * @description  :
 * <pre>
 * - requirejs 模块
 * - 基于jquery1.9.1
 * change log:
 * 2014-11-19 增加默认的请求参数 async.defaultParam
 * </pre>
 */

'use strict';
(function($){
    /**
     * 思路:
     * 1.每个下拉框可以单独异步加载， 
     * data-ajax,data-params
     * 2.可以通过一组参数配置级联,将ajax参数配置在第一个下拉上面，
     *  通过，data-next指定下一个select
     * 
     */



    /**
     * 初始化级联下拉
     * 
     * @param {type} options
     * @returns {_L18._cselect}
     */
    var _cselect = function (options) {
        return function (options) {
            // default options
            var defaultOptions = {
                async: {
                    enable: true,
                    uri: '',
                    autoParam: ['id'],//子级自动将id作为请求参数
                    defaultParam:null //默认请参数,每个请求都会带着这些参数
                },
                kv: ['id', 'name'],  //那些字段作为key也就是value,v表示label
                data: [], //初始化参数
                linked: []//
            };

            var _options = $.extend(defaultOptions, options);
            this.options = _options;
//            this.init();

            var _self = this;
            var _options = _self.options;

            _self.init();
            //bind on change event
            var groupElementIdArr = _options.group;
            var idArrLen = groupElementIdArr.length;
            (idArrLen > 1) && $.each(groupElementIdArr, function (index, id) {
                // last item not bind on change event
                (idArrLen - 1 !== index) && $("#" + id).on('change', _options, function (event) {
                    console.log(_options);
                    console.log("----------------------");
                    _self.onChange(event, _options);
                });
            });

            (_options.element.data('sync') && _options.async.enable) && _self.loadData(_options).done(function (res) {
                _options.data = res.data;
                _self.renderHtml(_options.element, _options);
            });
        };
    }();

    _cselect.prototype = {
        /**
         * 初始化
         * @returns {boolean}
         */
        init: function () {
            var cselectGroupArr = [];
            var options = this.options;
            var element = options.element;
            var hasNext = element.data('_next');
            while (hasNext) {
                cselectGroupArr.push(hasNext);
                hasNext = $("#" + hasNext).data('_next');
            }

            //if group length > 0 add first element
            cselectGroupArr.length && cselectGroupArr.unshift(element.attr('id'));
            options.group = cselectGroupArr;
        },
         /**
         * 
         * @returns {Array}
         */
        getGroup: function(element) {
            var cselectGroupArr = [];
            var hasNext = element.data('_next');
            while (hasNext) {
                console.log(hasNext);
                cselectGroupArr.push(hasNext);
                hasNext = $("#" + hasNext).data('_next');
            }
            return cselectGroupArr;
        },
        /**
         * 渲染 option
         * @param {type} element
         * @param {type} options
         * @returns {undefined}
         */
        renderHtml: function (element, options) {
            var _self = this;
            var $element = $(element).empty();
            var defaultValue = $element.data('default');
            var optionStr = '<option value=""></option>';
            var data = options.data;

            //可以指定键值
            var kv = options.kv;
            var plaecholder = $element.data('placeholder');
            if (plaecholder !== "undefined") {
                $element.append($('<option value="">' + plaecholder + '</option>'));
            }
            for (var i = 0, len = data.length; i < len; i++) {
                var _t = $(optionStr);
                var _val = data[i][kv[0]];
                // linked array is not null and $element.id in linked Array 
                if ($.inArray($element.attr('id'), options.linked) >= 0) {
                    _val = data[i][kv[0]] + '-' + data[i][kv[1]];
                }

                _t.val(_val);
                _t.text(data[i][kv[1]]);
//                //config auto param
                $.each(options.async.autoParam, function(_i, o) {
                    _t.attr("data-" + o, data[i][o]);
                });

                //设置默认值
                if(defaultValue && _val === defaultValue){
                    _t.prop('selected',true);
                }
                $element.append(_t);
            }
            //  如果有默认值，触发change事件
            if (defaultValue) {
                $element.change();
            }
            // else set next empty
            var arr = _self.getGroup($('#' + $($element).data('_next')));
            arr.push($($element).data('_next'));
            $.each(arr, function (i, id) {
            var plaecholder = $('#' + id).data('placeholder');
                $('#' + id).empty().append('<option value="">' + plaecholder + '</option>');
            });
//            }

        },
        //加载数据
        loadData: function (options, param) {
            if(options.async.defaultParam){
                if (!$.isFunction(options.async.defaultParam)) {
                    param = $.extend(param,options.async.defaultParam);
                } else {
                    param = $.extend(param,options.async.defaultParam());
                }
            }
            return $.ajax({
                url: options.async.uri,
                data: param
            });
        },
        // on change
        onChange: function (event, options) {
            var _self = this;
            var nextId = $(event.currentTarget).data("_next");
            var param = $(event.currentTarget).find("option:selected").data();
            param[$(event.currentTarget).attr('name')] = $(event.currentTarget).find("option:selected").val();

            param && _self.loadData(options, param).done(function (res) {
                options.data = res.data;
                _self.renderHtml($("#" + nextId), options);
            });
        }
    };

    $.fn.cselect = function (options) {
        this.each(function () {
            //if options is null,set default
            new _cselect(options);
        });
        return this;
    };
})(jQuery);
