var popWin = {
    scrolling: 'no',
    //是否显示滚动条 no,yes,auto

int: function() {
        this.mouseClose();
        this.closeMask();
        //this.mouseDown();

    },

showWin: function(width, height, title, src) {
        var iframeHeight = height - 52;
        var marginLeft = width / 2;
        var marginTop = height / 2;
        var inntHtml = '';
        inntHtml += '<div id="mask" style="width:100%; height:100%;position:fixed;top:0;left:0; z-inde:1999;"></div>'
        inntHtml += '<div id="maskTop" style="width: ' + width + 'px; height: ' + height + 'px; position: fixed; top:10%; left: 50%; margin-left: -' + marginLeft + 'px;  z-index: 2999;">'
        inntHtml += '<div id="maskTitle" style="position: relative;">'
        inntHtml += '' + title + ''
        inntHtml += '<div id="popWinClose" style="width:22px; height:21px; cursor:pointer; position: absolute; top:13px; right:17px; background:url(images/close.png)"></div>'
        inntHtml += '</div>'
        inntHtml += '<iframe width="' + width + '" height="' + iframeHeight + '" frameborder="0" scrolling="no" allowTransparency="true"' + this.scrolling + '" src="' + src + '"></iframe>';

        $("body").append(inntHtml);
        this.int();


    },

mouseClose: function() {
        $("#popWinClose").on('mouseenter', 
        function() {
            $(this).css("background-image", "url(images/closed.png)");

        });

        $("#popWinClose").on('mouseleave', 
        function() {
            $(this).css("background-image", "url(images/close.png)");

        });

    },

closeMask: function() {
        $("#popWinClose").on('click', 
        function() {
            $("#mask,#maskTop").fadeOut(function() {
                $(this).remove();

            });

        });

    }

};