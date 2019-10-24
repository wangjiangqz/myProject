/**
 * Created by yan on 2018/1/18.
 */
$(document).ready(function() {

    $('#password').keyup(function () {
        $('.pwd-checklist ').css('display','block');
        $('#strengthLevel').addClass('strengthLv'+($(this).val().length <8 ? 0:getLvl($(this).val())));

        if(getLvl($(this).val())=='3'&&$(this).val().length>=8&&$(this).val().length<=20){
            $('.pwdPower').css('display','block');

            $('#strengthLevel').removeAttr('class');
            $('#strengthLevel').addClass('strengthLv3');
            $('.power').text('强');

        }else if(getLvl($(this).val())=='2'&&$(this).val().length>=8&&$(this).val().length<=20){
            $('.pwdPower').css('display','block');

            $('#strengthLevel').removeAttr('class');
            $('#strengthLevel').addClass('strengthLv2');
            $('.power').text('中');

        }else if(getLvl($(this).val())=='1'&&$(this).val().length>=8&&$(this).val().length<=20){
            $('.pwdPower').css('display','block');

            $('#strengthLevel').removeAttr('class');
            $('#strengthLevel').addClass('strengthLv1');
            $('.power').text('弱');

        }
        if($(this).val().length>=8){
            $('.x1').attr('src','');
            $('.x1').attr('src',$('.ctxStatic').val()+'/images/o.png');

        }else{
            $('.x1').attr('src','');
            $('.x1').attr('src',$('.ctxStatic').val()+'/images/x.png');
        }


        if(!$(this).val()){
            $('#strengthLevel').removeAttr('class');
            $('#strengthLevel').addClass('strengthLv0');
            $('.pwdPower').css('display','none');
            $('.pwd-checklist ').css('display','none');
        }
        /*   var testPart=/@"^(?=.*[0-9].*)(?=.*[A-Z].*)(?=.*[a-z].*).{8,20}$"/;*/
        var testPart = /[a-zA-Z0-9]*/;
        var value=$(this).val();

        if(testPart.test(value) ){

            $('.x2').attr('src','');
            $('.x2').attr('src',$('.ctxStatic').val()+'/images/o.png');

        }else{
            $('.x2').attr('src','');
            $('.x2').attr('src',$('.ctxStatic').val()+'/images/x.png');

        }
          var patrn = /[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、]/im;

        if (patrn.test(value)) {// 如果包含特殊字符返回false
            $('.x3').attr('src','');
            $('.x3').attr('src',$('.ctxStatic').val()+'/images/o.png');
        }else{
            $('.x3').attr('src','');
            $('.x3').attr('src',$('.ctxStatic').val()+'/images/x.png');

        }

    });


    $('#newPassword').keyup(function () {
        $('.pwd-checklist ').css('display','block');
        $('#strengthLevel').addClass('strengthLv'+($(this).val().length <8 ? 0:getLvl($(this).val())));

        if(getLvl($(this).val())=='3'&&$(this).val().length>=8&&$(this).val().length<=20){
            $('.pwdPower').css('display','block');

            $('#strengthLevel').removeAttr('class');
            $('#strengthLevel').addClass('strengthLv3');
            $('.power').text('强');

        }else if(getLvl($(this).val())=='2'&&$(this).val().length>=8&&$(this).val().length<=20){
            $('.pwdPower').css('display','block');

            $('#strengthLevel').removeAttr('class');
            $('#strengthLevel').addClass('strengthLv2');
            $('.power').text('中');

        }else if(getLvl($(this).val())=='1'&&$(this).val().length>=8&&$(this).val().length<=20){
            $('.pwdPower').css('display','block');

            $('#strengthLevel').removeAttr('class');
            $('#strengthLevel').addClass('strengthLv1');
            $('.power').text('弱');

        }
        if($(this).val().length>=8){
            $('.x1').attr('src','');
            $('.x1').attr('src',$('.ctxStatic').val()+'/images/o.png');

        }else{
            $('.x1').attr('src','');
            $('.x1').attr('src',$('.ctxStatic').val()+'/images/x.png');
        }


        if(!$(this).val()){
            $('#strengthLevel').removeAttr('class');
            $('#strengthLevel').addClass('strengthLv0');
            $('.pwdPower').css('display','none');
            $('.pwd-checklist ').css('display','none');
        }
        /*   var testPart=/@"^(?=.*[0-9].*)(?=.*[A-Z].*)(?=.*[a-z].*).{8,20}$"/;*/
        var testPart = /[a-zA-Z0-9]*/;
        var value=$(this).val();

        if(testPart.test(value) ){

            $('.x2').attr('src','');
            $('.x2').attr('src',$('.ctxStatic').val()+'/images/o.png');

        }else{
            $('.x2').attr('src','');
            $('.x2').attr('src',$('.ctxStatic').val()+'/images/x.png');

        }
        var patrn = /[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、]/im;

        if (patrn.test(value)) {// 如果包含特殊字符返回false
            $('.x3').attr('src','');
            $('.x3').attr('src',$('.ctxStatic').val()+'/images/o.png');
        }else{
            $('.x3').attr('src','');
            $('.x3').attr('src',$('.ctxStatic').val()+'/images/x.png');

        }

    });

});
function getLvl(txt) {
    //默认级别是0
    var lvl = 0;
    //判断这个字符串中有没有数字
    if (/[0-9]/.test(txt)) {
        lvl++;
    }
    //判断字符串中有没有字母
    if (/[a-zA-Z]/.test(txt)) {
        lvl++;
    }
    //判断字符串中有没有特殊符号
    if (/[^0-9a-zA-Z_]/.test(txt)) {
        lvl++;
    }
    return lvl;
}

