/**
 *
 * @FileName     :  validate.js
 * @Encoding     :  UTF-8
 * @Link         :  http://lezhai365.com
 * @Create On    ： 2013-11-13 1:42:08
 * @Author       :  Hui.Wang [wanghui@lezhai365.com]
 * @Version      :  1.0
 * @Description  :
 *
 */
;
(function($) {
    //默认选项值
    //a
    var optionDefaults = {
        errorRequired: "此项为必填项",
//        msgType:'inline',//inline,tooltip,popover
        errorTempl: '<div class="help-block col-xs-12 col-sm-reset inline validate-error plm">{msg}</div>',
        successTempl: '<span class="help-block col-xs-12 col-sm-reset inline validate-success  plm">{msg}</span>',
        optionTempl: '<span class="help-block col-xs-12 col-sm-reset inline validate-option  plm">{msg}</span>',
        callback: null,
        showSuccess: false,
        cls: {
            item: ".form-group", //每一个表单条目样式 c
            error: ".validate-error", // 验证出错的样式 b
            success: ".validate-success", // 验证成功的样式 success
            process: ".validate-process", // 处理中
            option: ".validate-option" //提示选项 e
        }
    };
    //i, h, k, g, j
    var _validate = function(elem, validateRules, errors, optionsMsg, config) {
        if (!validateRules || !elem) {
            return;
        }

        //远程验证列表
        this.asyncList = [];
        this.asyncEndHandle = null;
        //初始化
        this._init(elem, validateRules, errors, optionsMsg, config);
    };
    _validate.prototype = {
        //
//        _init: function(i, h, l, g, k) {
        _init: function(elem, validateRules, errors, optionsMsg, config) {
            var $node;
            $node = this.node = $(elem);
            this.form = ($node.prop("tagName").toLowerCase() === "form") ? $node : $node.find("form");
            this.config = $.extend(optionDefaults, config);
            this.rules = validateRules;
            this.errorMsg = errors || {};
            this.optionMsg = optionsMsg || {};
            $node.data("validateForm", this);
            this._bindEvent();
            console.log(this.form);
        },
        /**
         *  将class选择器转换成 class string
         *  example for: ".has-error"> has-error
         * @returns {undefined}
         */
        _toClassString: function(str){
            return str.split('.').splice(1,1).join();
        },
        /**
         * 绑定事件
         */
        _bindEvent: function() {
            //如果已经绑定过了就返回
            if (this.node.data("hasBindValidateEvent")) {
                return;
            }

            this.node.data("hasBindValidateEvent", true);
            //给form绑定submit事件
            this.form.submit($.proxy(function(g) {
                //TODO:1.验证表单,2.执行submit处理函数
                this.validate();
                this._handleFormSubmit(g);
                //一次给表单下的input select textarea绑定change,blur,focus事件
            }, this)).find("input, select, textarea").unbind(".validate").bind({
                "change.validate": $.proxy(function(g) {
                    this._handleBlur(g);
                }, this),
                "blur.validate": $.proxy(function(g) {
                    $(g.currentTarget).removeClass("focus");
                    this._handleBlur(g);
                }, this),
                "focus.validate": $.proxy(function(g) {
                    $(g.currentTarget).addClass("focus");
                    this._handleFocus(g);
                }, this)

            });

            //绑定规则
            this._bindRules();
        },
        /**
         * 绑定规则
         * @returns {undefined}
         */
        _bindRules: function() {
            var rules = this.rules, rulesItem;
            //对规则进行循环 把每个验证规则绑定到相应的字段元素中
            //rules = {
            //     name:{
            //         elems:'#selector',
            //         rule1:function(){}
            //     },
            //     phone:{
            //         elems:'#selector',
            //         rule1:function(){}
            //     }
            // };
            for (rulesItem in rules) {
                if (rules.hasOwnProperty(rulesItem)) {
                    //在当前表单中找到所有该该规则的元素
                    $(rules[rulesItem].elems, this.form).each(function(k, m) {
                        //将规则绑定到对应表单字段上
                        var $obj = $(m),
                                rule = $obj.data("validate-rules") || "";
                        $obj.data("validate-rules", rule + "," + rulesItem);
                    });
                }
            }
        },
        /**
         * 输入域失去光标事件处理
         * @param {event} n
         * @returns {undefined}
         */
        _handleBlur: function(n) {
            var cls = this.config.cls;
            //h
            var elem = $(n.target),
                    //o
                    parent = elem.parents(cls.item),
                    l, g, m, j, p = false,
                    //q
                    rule = elem.data("validate-rules");
            parent.find(cls.option).hide();
            //没有规则 return
            if (!rule) {
                return;
            }
            //a = q.split
            console.log('rule--------------');
            console.log(rule);
            rule = rule.split(",").slice(1);
            for (l = 0, m = rule.length; l < m; l++) {
                g = this.rules[rule[l]];
                //对每一个验证规则进行验证（校验规则，校验错误提示，当前对象）
                this.validate(g, this.errorMsg[rule[l]], elem);
            }
        },
        /**
         *  focus的时候，根据h的name显示提示
         */
        _handleFocus: function(h) {
            //g
            var tagName = h.target.getAttribute("name"), i;
            if (!tagName) {
                return;
            }

            if (i = this.optionMsg[tagName.toLowerCase()]) {
                //显示提示信息
                this.displayOptionMsg($(h.target), i);
            }
        },
        /**
         * form submit处理函数
         */
        _handleFormSubmit: function(i) {
            var cls = this.config.cls;
            var errors, process, _this = this;
            //找到所有验证失败的element
            errors = this.form.find(cls.error);
            if (errors.length > 0) {
                i.preventDefault();
                $(_this.form).trigger("hasError");
                return false;
            }
            process = this.form.find(cls.process);
            if (process.length > 0) {
                i.preventDefault();
                this.asyncEndHandle = function() {
                    _this.asyncEndHandle = null;
                    _this._handleFormSubmit(i);
                };
                return false;
            }
            if (_this.config.callback) {
                _this.config.callback(_this.form);
            } else {
                _this.form[0].submit();
            }
        },
        /**
         *  清空错误消息
         */
        clearErrorMsg: function(g) {
            var cls = this.config.cls;
            var $parent = g.parents(cls.item);
            $parent.find(cls.error).hide();
        },

        /**
         *  显示错误提示
         *  
         * @param {type} $elem
         * @param {type} msg
         * @returns {undefined}
         */
        displayError: function($elem, msg) {
            var cls = this.config.cls;
            var $item = $elem.parents(cls.item),
                    $optionsMsg = $item.find(cls.option),
                    $errors = $item.find(cls.error);
            $item.find(cls.success).hide();
            $optionsMsg.hide();
            if ($errors.length === 0 || $item.hasClass(this._toClassString(cls.error))) {
                $(this.config.errorTempl.replace("{msg}", msg)).appendTo($elem.parent()).show();
                return;
            }
            $errors.show().html(msg);
            return;
        },

        /**
         *  显示
         * @param {type} i
         * @returns {undefined}
         */
        displaySuccess: function($elem) {
            var cls = this.config.cls;
            var $item = $elem.parents(cls.item),
                    $optionsMsg = $item.find(cls.option),
                    $success = $item.find(cls.success);
            //如果还在进行远程验证处理则返回
            if ($item.hasClass('has-success')) {
                return;
            }
            $item.addClass('has-success').removeClass('has-error has-process');
            $optionsMsg.hide();
            if ($success.length === 0) {
                $(this.config.successTempl).appendTo($elem.parent()).show();
                return;
            }
            $success.show();
            return;
        },
        /**
         *  显示提示
         */
        displayOptionMsg: function($elem, msg) {
            var cls = this.config.cls;
            if (!msg) {
                return;
            }
            //如果已经显示了error，return
            var $item = $elem.parents(cls.item),
                    $optionsMsg = $.find(cls.option),
                    g = $item.hasClass('has-error') || $item.hasClass('has-success') || $item.hasClass('has-process');
            if (g) {
                return;
            }

            //没有则，根据模版显示错误提示
            if ($optionsMsg.length === 0) {
                $(this.config.optionTempl.replace("{msg}", msg)).appendTo($elem.parent()).show();
                return;
            }
            $optionsMsg.show().html(msg);
            return;
        },
        /**
         * 远程验证
         */
        //i h g
        asyncValidate: function(elem, url, callback) {
            var cls = this.config.cls;
            if (!elem || !url) {
                return;
            }
            //j
            var $item = elem.parent();
            if ($item.hasClass(this._toClassString(cls.process))) {
                return;
            }
            $item.addClass(this._toClassString(cls.process));
            this.asyncList.push($.getJSON(url, $.proxy(function(k) {
                var asyncList = this.asyncList;
                callback && callback(k);
                $item.removeClass(this._toClassString(cls.process));
                this.asyncList.pop();
                if (asyncList.length === 0) {
                    this.asyncEndHandle && this.asyncEndHandle();
                }
            }, this)));
            $("body").ajaxError(function(i, j, k) {
                alert("远程验证失败！\n请稍候重试或将此问题反馈给我们");
            });
        },
        /**
         * 规则（rules），错误信息(errors)，当前对象(this)
         * m l h
         */
        validate: function(rules, errors, elem) {
            var _this = this;
            var config = this.config;
            var cls = config.cls;
            var requireMsg = this.errorMsg.errorRequired,
                    /**
                     * v验证规则，x提示信息，t当前元素，w>this
                     *  rule ,errormsg, elem, _this
                     */
                    doItemValidate = function(rule, errorMsg, $elem, _this) {
                        //u
                        var $item = $elem.parents(cls.item),
                                isError = false,
                                ruleItem;

                        //如果是必填项
                        if (rule.isRequired && $.trim($elem.val()) === "") {
                            _this.displayError($elem, requireMsg || config.errorRequired);
                            isError = true;
                            $item.addClass('has-error').removeClass('has-success has-process');;
                        } else {
                            //对自定义规则的验证函数进行循环
                            for (ruleItem in rule) {
                                if (rule.hasOwnProperty(ruleItem) && typeof rule[ruleItem] === "function") {

                                    //验证函数必须返回一个
                                    if (rule[ruleItem]($elem, _this)) {
                                        _this.displayError($elem, errorMsg[ruleItem]);
                                        $item.addClass('has-error').removeClass('has-success has-process');
                                        isError = true;
                                        break
                                    }
                                }
                            }
                            if (!isError) {
                                _this.clearErrorMsg($elem);
                                $item.removeClass('has-error').addClass('has-success');
                                //是否显示验证成功标志
                                _this.config.showSuccess && _this.displaySuccess($elem);
                            }
                        }
                    },
                    g, _rules, _errorMsgs, _ruleItem;

            if (!rules) {
                _rules = this.rules;
                //o
                _errorMsgs = this.errorMsg;
                for (_ruleItem in _rules) {
                    if (_rules.hasOwnProperty(_ruleItem)) {
                        g = _rules[_ruleItem];
                        $(g.elems, this.form).each($.proxy(function(k, q) {
                            doItemValidate(g, _errorMsgs[_ruleItem], $(q), this);
                        }, this));
                    }
                }
            } else {
                doItemValidate(rules, errors, elem, this);
            }
        }

    },

	$.extend({
		validate: {
			isEmail: function (g) {
				return /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(g);
			},
			isUrl: function(g){
				return /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(g);
			},
			isPhone: function(g){
				var tel = /^\d{3,4}-?\d{7,8}$/;
				var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
				return mobile.test(g)||tel.test(g);
			}
		}
	});

    //扩展为jquery插件
    $.fn.validateForm = function(validateRules, validateErrors, optionsMsg, config) {
        var _optionsMsg = optionsMsg,
                _config = config;
        //如果只传入了3个参数
        if (arguments.length === 3) {
            _config = null;
            _optionsMsg = optionsMsg;
        }
        this.each(function() {
            new _validate(this, validateRules, validateErrors, _optionsMsg, _config);
        });
        return this;
    };
})(jQuery);
