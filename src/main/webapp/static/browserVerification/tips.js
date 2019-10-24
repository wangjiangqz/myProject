(function(window) {
    function getPath(){
        var pathName = document.location.pathname;
        var index = pathName.substr(1).indexOf("/");
        var result = pathName.substr(0,index+1);
        return result;
    }
        if($.jbrowser.IEVersion()=='0' ||$.jbrowser.IEVersion()=='IE7' ||$.jbrowser.IEVersion()=='IE8'
            ||window.navigator.userAgent.indexOf('compatible') != -1){
            var str = '<div class="hint">'+
                '<img src="'+getPath()+'/static/browserVerification/tishi.png" alt="" class="hint_img" onabort="this.src=this.src">'+
                '<a href="http://www.firefox.com.cn/" target="_blank">推荐火狐浏览器</a>'+
                '</div>';
            var str2 = '<div class="footer_img">'+
                '<ul class="liulanqi">'+
                '<li>'+
                '<img src="'+getPath()+'/static/browserVerification/goole.png" alt="" class="hint_img" onabort="this.src=this.src"> '+
                '<a href="https://www.google.cn/intl/zh-CN/chrome/" class="footer_size" target="_blank">谷歌浏览器</a>'+
                '</li>'+
                '<li class="browser">'+
                '<img src="'+getPath()+'/static/browserVerification/360.png" alt="" class="hint_img" onabort="this.src=this.src">'+
                '<div  class="browser_title">'+
                '<a href="https://browser.360.cn/se/"  class="footer_size" target="_blank">360浏览器 (极速模式)</a>'+
                '</div>'+
                '</li>'+
                '<li >'+
                '<img src="'+getPath()+'/static/browserVerification/ie.png" alt="" class="hint_img" onabort="this.src=this.src">'+
                '<div >'+
                '<a href="https://support.microsoft.com/zh-cn/help/17621/internet-explorer-downloads"  class="footer_size" target="_blank">IE10+</a>'+
                '</div>'+
                '</li>'+
                '</ul>'+
                '<p>推荐浏览器下载</p>'+
                '</div>';
            document.documentElement.style.overflowY = 'hidden';
            var tishi = "<div class='index_tishi' style=''>" +
                "<h2>" +str + "<br/></h2><h2>" +
                str2 + "</h2></div>";
            document.writeln(tishi);
            document.execCommand("Stop");
        };

})(window);