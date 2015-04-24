(function (a) {
	var b = [];
	a.fn.editableSelect = function (e) {
		var g = {
			bg_iframe: false,
			onSelect: false,
			items_then_scroll: 10,
			case_sensitive: false
		};
		var f = a.extend(g, e);
		if (f.bg_iframe && !a.browser.msie) {
			f.bg_iframe = false
		}
		var d = false;
		a(this).each(function () {
			var h = b.length;
			if (a(this).data("editable-selecter") !== null) {
				b[h] = new c(this, f);
				a(this).data("editable-selecter", h)
			}
		});
		return a(this)
	};
	a.fn.editableSelectInstances = function () {
		var d = [];
		a(this).each(function () {
			if (a(this).data("editable-selecter") !== null) {
				d[d.length] = b[a(this).data("editable-selecter")]
			}
		});
		return d
	};
	var c = function (d, e) {
		this.init(d, e)
	};
	c.prototype = {
		settings: false,
		text: false,
		select: false,
		select_width: 0,
		wrapper: false,
		list_item_height: 20,
		list_height: 0,
		list_is_visible: false,
		hide_on_blur_timeout: false,
		bg_iframe: false,
		current_value: "",
		init: function (d, e) {
			this.settings = e;
			this.wrapper = a(document.createElement("div"));
			this.wrapper.addClass("editable-select-options");
			this.select = a(d);
			this.text = a('<input type="text">');
			this.text.attr("name", this.select.attr("name"));
			this.text.data("editable-selecter", this.select.data("editable-selecter"));
			this.select.attr("disabled", "disabled");
			this.text[0].className = this.select[0].className;
			var f = this.select.attr("id");
			if (!f) {
				f = "editable-select" + b.length
			}
			this.text.attr("id", f);
			this.text.attr("autocomplete", "off");
			this.text.addClass("editable-select");
			this.select.attr("id", f + "-hidden-select");
			this.select.after(this.text);
			if (this.select.css("display") == "none") {
				this.text.css("display", "none")
			}
			if (this.select.css("visibility") == "hidden") {
				this.text.css("visibility", "visibility")
			}
			this.select.css("visibility", "hidden");
			this.select.hide();
			this.initInputEvents(this.text);
			this.duplicateOptions();
			this.setWidths();
			a(document.body).append(this.wrapper);
			if (this.settings.bg_iframe) {
				this.createBackgroundIframe()
			}
		},
		duplicateOptions: function () {
			var f = this;
			var d = a(document.createElement("ul"));
			this.wrapper.append(d);
			var e = this.select.find("option");
			e.each(function () {
				if (a(this).attr("selected")) {
					f.text.val(a(this).val());
					f.current_value = a(this).val()
				}
				var g = a("<li>" + a(this).val() + "</li>");
				f.initListItemEvents(g);
				d.append(g)
			});
			this.checkScroll()
		},
		checkScroll: function () {
			var d = this.wrapper.find("li");
			if (d.length > this.settings.items_then_scroll) {
				this.list_height = this.list_item_height * this.settings.items_then_scroll;
				this.wrapper.css("height", this.list_height + "px");
				$("ul",this.wrapper).css("height", this.list_height + "px");
				//                this.wrapper.css("overflow", "auto")
				$("ul",this.wrapper).css("overflow", "auto");
			} else {
				//                this.wrapper.css("height", "auto");
				$("ul",this.wrapper).css("overflow", "auto");
				$("ul",this.wrapper).css("overflow", "visible")
			//                this.wrapper.css("overflow", "visible")
			}
		},
		addOption: function (f) {
			var d = a("<li>" + f + "</li>");
			var e = a("<option>" + f + "</option>");
			this.select.append(e);
			this.initListItemEvents(d);
			this.wrapper.find("ul").append(d);
			this.setWidths();
			this.checkScroll()
		},
		initInputEvents: function (e) {
			var d = this;
			var f = false;
			a(document.body).click(function () {
				d.clearSelectedListItem();
				d.hideList()
			});
			e.focus(function () {
				d.showList();
				d.highlightSelected()
			}).click(function (g) {
				g.stopPropagation();
				d.showList();
				d.highlightSelected()
			}).keydown(function (g) {
				//				console.log(g.keyCode);
				switch (g.keyCode) {
					case 40:
						if (!d.listIsVisible()) {
							d.showList();
							d.highlightSelected()
						} else {
							g.preventDefault();
							d.selectNewListItem("down")
						}
						break;
					case 38:
						g.preventDefault();
						d.selectNewListItem("up");
						break;
					case 9:
						d.pickListItem(d.selectedListItem());
						break;
					case 27:
						g.preventDefault();
						d.hideList();
						return false;
						break;
					case 13:
						g.preventDefault();
						d.pickListItem(d.selectedListItem());
						return false
				}
			}).keyup(function (g) {
				if (f !== false) {
					clearTimeout(f);
					f = false
				}
				f = setTimeout(function () {
					if (d.text.val() != d.current_value) {
						d.current_value = d.text.val();
						d.highlightSelected()
					}
				}, 200)
			}).keypress(function (g) {
				if (g.keyCode == 13) {
					g.preventDefault();
					return false
				}
			})
		},
		initListItemEvents: function (d) {
			var e = this;
			d.mouseover(function () {
				e.clearSelectedListItem();
				e.selectListItem(d)
			}).mousedown(function (f) {
				f.stopPropagation();
				e.pickListItem(e.selectedListItem())
			})
		},
		selectNewListItem: function (f) {
			var d = this.selectedListItem();
			if (!d.length) {
				d = this.selectFirstListItem()
			}
			if (f == "down") {
				var e = d.next()
			} else {
				var e = d.prev()
			}
			if (e.length) {
				this.selectListItem(e);
				this.scrollToListItem(e);
				this.unselectListItem(d)
			}
		},
		selectListItem: function (d) {
			this.clearSelectedListItem();
			d.addClass("selected")
		},
		selectFirstListItem: function () {
			this.clearSelectedListItem();
			var d = this.wrapper.find("li:first");
			d.addClass("selected");
			return d
		},
		unselectListItem: function (d) {
			d.removeClass("selected")
		},
		selectedListItem: function () {
			return this.wrapper.find("li.selected")
		},
		clearSelectedListItem: function () {
			this.wrapper.find("li.selected").removeClass("selected")
		},
		pickListItem: function (d) {
			if (d.length) {

				if (d.text() != "时间") {
					this.text.val(d.text());
				}
				this.current_value = this.text.val()
			} 

			if (typeof this.settings.onSelect == "function") {
				this.settings.onSelect.call(this, d)
			}
			this.hideList()
		},
		listIsVisible: function () {
			return this.list_is_visible
		},
		showList: function () {
			this.positionElements();
			this.setWidths();
			this.wrapper.show();
			this.hideOtherLists();
			this.list_is_visible = true;
			if (this.settings.bg_iframe) {
				this.bg_iframe.show()
			}
		},
		highlightSelected: function () {
			var h = this;
			var f = this.text.val();
			if (f.length < 0) {
				if (highlight_first) {
					this.selectFirstListItem()
				}
				return
			}
			if (!h.settings.case_sensitive) {
				f = f.toLowerCase()
			}
			var g = false;
			var e = false;
			var d = this.wrapper.find("li");
			d.each(function () {
				if (!e) {
					var i = a(this).text();
					if (!h.settings.case_sensitive) {
						i = i.toLowerCase()
					}
					if (i == f) {
						e = true;
						h.clearSelectedListItem();
						h.selectListItem(a(this));
						h.scrollToListItem(a(this));
						return false
					} else {
						if (i.indexOf(f) === 0 && !g) {
							g = a(this)
						}
					}
				}
			});
			if (g && !e) {
				h.clearSelectedListItem();
				h.selectListItem(g);
				h.scrollToListItem(g)
			} else {
				if (!g && !e) {
					this.selectFirstListItem()
				}
			}
		},
		scrollToListItem: function (d) {
			if (this.list_height) {
				this.wrapper.scrollTop(d[0].offsetTop - (this.list_height / 2))
			}
		},
		hideList: function () {
			this.wrapper.hide();
			this.list_is_visible = false;
			if (this.settings.bg_iframe) {
				this.bg_iframe.hide()
			}
		},
		hideOtherLists: function () {
			for (var d = 0; d < b.length; d++) {
				if (d != this.select.data("editable-selecter")) {
					b[d].hideList()
				}
			}
		},
		positionElements: function () {
			var d = this.text.offset();
			d = {
				top: d.top,
				left: d.left
			};
			d.top += this.text[0].offsetHeight;
			this.wrapper.css({
				top: (d.top-2) + "px",
				left: d.left + "px"
			});
			this.wrapper.css("visibility", "hidden");
			this.wrapper.show();
			this.list_item_height = this.wrapper.find("li")[0].offsetHeight;
			this.wrapper.css("visibility", "visible");
			this.wrapper.hide()
		},
		setWidths: function () {
			this.select.show();
			var d = this.select.outerWidth() + 4;
			this.select.hide();
			var e = parseInt(this.text.css("padding-right").replace(/px/, ""), 10);
			this.text.width(d - e);
			this.wrapper.width(d + 4);
			if (this.bg_iframe) {
				this.bg_iframe.width(d + 4)
			}
		},
		createBackgroundIframe: function () {
			var d = a('<iframe frameborder="0" class="editable-select-iframe" frameBorder="0" scrolling="no" src="javascript:false;document.write(\'\');"></iframe>');
			var e = this.text.offset();
			e = {
				top: e.top,
				left: e.left
			};
			e.top += this.text[0].offsetHeight;
			d.css({
				top: e.top + "px",
				left: e.left + "px",
				width: this.select.width() + 2 + "px",
				height: this.wrapper.height() + "px"
			});
			a(document.body).append(d);
			this.bg_iframe = d
		}
	}
})(jQuery);