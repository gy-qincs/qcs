function XConfirm(msg, ok, cancel, title, buttons, callback) {
    if (!title)title = R.String.CONFIRM_TITLE;
    if (typeof buttons != "string")buttons = R.Array.CONFIRM_BUTTON.join(",");
    if (!callback)callback = function (index) {
        if (index == 1) {
            ok();
        } else {
            cancel();
        }
    };
    if (typeof ok != "function")ok = function () {
    };
    if (typeof cancel != "function")cancel = function () {
    };
    navigator.notification.confirm(msg, callback, title, buttons);
}

/**
 * 
 * @desc msg is not null, callback null , title is not null, buttons null
 * */
function XAlert(msg, callback, title, buttons) {
    if (!title) title = R.String.ALERT_TITLE;
    if (typeof buttons != "string")buttons = R.Array.ALERT_BUTTON.join(",");
    if (typeof callback != "function")callback = function () {
    };
    navigator.notification.alert(msg, callback, title, buttons);
}
/**
 * 弹出式提示信息
 * @param msg
 * @param duration
 */
var toast = (function () {
    var c, timer;
    return function Toast(msg, position, duration) {
        if (msg === undefined)msg = "undefined";
        if(msg === null)msg = "null";
        if(msg === false)msg = "false";
        if(msg === "")return;
        var fadeDuration = 200;
        if (typeof duration != "number") duration = 2000;
        if (c && c.parents().length > 0) {
            c.remove();
            clearTimeout(timer);
            c = undefined;
        }
        c = $("<div>").addClass("TOAST").css({"-webkit-transition" : "opacity "+ fadeDuration +"ms 0ms ease-in",opacity:1});
        var t = $("<span>").addClass("CONTENT");
        t.html(msg.toString());
        c.append(t);
        switch (position) {
            case "top":
                c.style.top = "20%";
                c.style.bottom = "";
                break;
            case "middle":
                c.style.bottom = "50%";
                c.style.top = "50%";
                break;
            case "bottom":
                c.style.top = "";
                c.style.bottom = "20%";
                break;
        }
        $("body").append(c);
        timer = setTimeout(function () {
            c.css({opacity:0});
            setTimeout(function () {
                c.remove();
                c = undefined;
                delete(c);
            }, fadeDuration);
        }, duration);
    }
})();

var Loading = (function () {
    function Waiting() {
        var _this = this;
        var loading = $("<div>").addClass("MASK"),
            content = $("<div>").addClass("LOADING"),
            img = $("<div>").addClass("LOADING_IMG"),
            text = $("<div>").addClass("LOADING_CONTENT");
        content.append(img).append(text);
        loading.append(content);
        var _request, visible = false;

        function onBackButton() {
            if (_request !== false)_this.hide();
        }

        this.__defineGetter__("isVisible", function () {
            return visible;
        });

        this.__defineSetter__("request",function(request){
            if(request)_request = request;
        });
        this.__defineGetter__("request",function(){
            return _request;
        });

        this.show = function (msg, request) {
            if(request)_request = request;
            text.html(msg);
            if (null == loading.get(0).parentNode)$("body").append(loading);
            visible = true;
            document.addEventListenerNS("active", "backbutton", onBackButton, false);
        };

        this.setText = function (txt) {
            text.html(txt);
        };

        this.hide = function () {
            text.html("");
            loading.remove();
            visible = false;
            if (_request && _request.isRequesting) _request.abort();
            document.removeEventListenerNS("active", "backbutton", onBackButton, false);
            _request = undefined;
        }
    }

    return new Waiting();
})();

var ModalWindow = (function () {
    var container, header, loading, iframe;
    var isOpen = false;
    var width = "100%", height = "100%";
    var handler = {
        onload:function () {
        },
        onclick:function () {
        },
        onclose:function () {
        }
    };

    function ModalWindow(w, h) {
        var _this = this;

        this.setUrl = function (url) {
            iframe.src = url;
        };
        this.getIframe = function () {
            return iframe;
        };
        this.getWindow = function () {
            return iframe.get(0).contentWindow;
        };
        this.getDocument = function () {
            return iframe.get(0).contentDocument;
        };
        this.setWidth = function (w) {
            width = w;
            container.css({width:w});
        };
        this.setHeight = function (h) {
            height = h;
            container.css({height:h});
        };

        this.show = function (url) {
            if (typeof url != "string" && url.length <= 0)return;
            this.src = url;
            iframe.bind("load", function (e) {
                loading.remove();
                e.currentTarget = iframe.get(0);
                if (typeof handler.onload == "function")handler.onload(e);
            });
            iframe.bind("click", function (e) {
                if (typeof handler.onclick == "function")handler.onclick(e);
            });
            iframe.attr("src", url);
            $("body").append(container);
            isOpen = true;
            document.addEventListenerNS("active", "backbutton", _this.close, false);
        };
        this.close = function () {
            container.css({"-webkit-animation-name":"zoom-out"});
            setTimeout(function () {
                container.remove();
                isOpen = false;
                if (typeof handler.onclose == "function")handler.onclose();
            }, 280);
            document.removeEventListenerNS("active", "backbutton", _this.close, false);
        };

        iframe = create(_this);
        if (!isNaN(w)) {
            width = w;
            this.setWidth(w);
        }
        if (!isNaN(h)) {
            height = h;
            this.setHeight(h);
        }
    }

    ModalWindow.prototype.__defineGetter__("isOpen", function () {
        return isOpen;
    });
    ModalWindow.prototype.__defineSetter__("onload", function (func) {
        if (typeof func == "function")handler.onload = func;
    });
    ModalWindow.prototype.__defineGetter__("onclick", function (func) {
        if (typeof func == "function")handler.onclick = func;
    });
    ModalWindow.prototype.__defineSetter__("onclose", function (func) {
        if (typeof func == "function")handler.onclose = func;
    });

    function create(obj) {
        container = $("<div>").addClass("mw_container");
        header = $("<div>").addClass("mw_header");
        loading = $("<div>").addClass("mw_loading").html("<h3>loading...</h3>");
        var image = new Image(48, 48);
        image.src = View.getResource("close_btn.png");
        header.append(image);
        header.bind("click", obj.close);
        var iframe = $("<iframe>").addClass("mw_iframe");
        iframe.attr("id", "pop_window");
        iframe.attr("scrolling", "no");
        container.append(header);
        container.append(loading);
        container.append(iframe);
        return iframe;
    }

    return ModalWindow;
})();