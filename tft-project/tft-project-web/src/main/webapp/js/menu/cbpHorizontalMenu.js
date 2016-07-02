var cbpHorizontalMenu = function() {
	function i() {
		t.on("click", s);
		e.on("click", function(e) {
			e.stopPropagation()
		})
	}
	function s(t) {
		if (r !== -1) {
			e.eq(r).removeClass("cbp-hropen")
		}
		var i = $(t.currentTarget).parent("li"), s = i.index();
		if (r === s) {
			i.removeClass("cbp-hropen");
			r = -1
		} else {
			i.addClass("cbp-hropen");
			r = s;
			n.off("click").on("click", o)
		}
		return false
	}
	function o(t) {
		e.eq(r).removeClass("cbp-hropen");
		r = -1
	}
	var e = $("#cbp-hrmenu > ul > li"), t = e.children('a[href^="#"]'), n = $("body"), r = -1;
	return {
		init : i
	}
}()