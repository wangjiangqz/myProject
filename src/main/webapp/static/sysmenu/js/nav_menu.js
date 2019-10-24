$(function(){
    jQuery.fn.isChildAndSelfOf = function(b){
        return (this.closest(b).length > 0);
    };
    // nav_menu收缩展开
    $('.nav_menu-item a').on('click',function(){
        var _that=$(this);

        if (!$('.nav_menu').hasClass('nav_menu-mini')) {
            if($(_that).next().length>0){//判断是当前菜单时候有下级菜单
                if ($(_that).next().css('display') == "none") {
                    if($(_that).next('ul').length>0){
                        //关闭其他,如果是自己或者父级则不关闭
                        $('.nav_menu-item').children('ul').each(function(){
                            if(!$(_that).next('ul').isChildAndSelfOf($(this))){
                                $(this).slideUp(300);
                            }
                        });
                    }else{
                        $('.nav_menu-item').children('ul').slideUp(300);
                    }
                    //如果是第一级，隐藏其他第一级，显示当前第一级
                    if($(_that).parent('li').hasClass("first_level")){
                        $("#parent_ul").find(".first_level").hide();
                        $(_that).parent('li').show();
                    }
                    //判断当前一级有没有下一级，如果没有，选中第一个菜单，如果有，不做操作
                    if($(_that).next('ul').find("ul").length<=0){
                        trueaClick($(_that).next('ul').find(".true_a:eq(0)"));
                        //去除掉同级
                    }else{
                        //触发下一级点击
                        $(_that).next('ul').find("a:eq(0)").click();
                    }
                    //显示父级li
                    $(_that).next('ul').slideDown(300);
                    $(_that).parent('li').addClass('nav_menu-show').siblings('li').removeClass('nav_menu-show');
                    //默认选中当前菜单的第一个
                    if($(_that).next("ul").find("ul").length<=0){
                        $(_that).parent('li').find("li:first a:first span").click();
                    }
                }else{
                    //收缩已展开
                    //如果是第一级，不收缩
                    if(!$(_that).parent('li').hasClass("first_level")){
                        $(_that).next('ul').slideUp(300);
                        $('.nav_menu-item.nav_menu-show').removeClass('nav_menu-show');
                    }
                }
            }else{
                trueaClick($(_that));
                $('.nav_menu-item').children('ul').each(function(){
                    if(!$(_that).parents('ul:eq(0)').isChildAndSelfOf($(this))){
                        $(this).slideUp(300);
                    }
                });
            }

        }
    });
    $(".left_menu").find("img").click(function(){
        $(this).next("a").click();
    });
    $(".left_menu").find("a").click(function(){
        $("#"+$(this).attr("id")+"_true").click();
        $(".a-select").removeClass("a-select");
        $(this).addClass("a-select");
        //切换数据
        $(".left_menu span").css("visibility","hidden");
        $(".left_menu img").each(function () {
            $(this).attr("src",ctx+$(this).attr("licon"));
        });
        $(this).prev("img").attr("src",ctx+$(this).prev("img").attr("sicon"));
        $(this).find("span").css("visibility","visible");
    });
    $(".left_menu").find("a:first").click();
    //初始化后默认选中第一个

    //$('.nav_menu-item a:eq(0)').click();
});
function trueaClick(ths){
    $(ths).parent("li").addClass('nav_menu-show').siblings('li').removeClass('nav_menu-show');
}
