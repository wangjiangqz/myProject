<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="名称"%>
<%@ attribute name="imgUrl" type="java.lang.String" required="true" description="图片路径"%>
<%@ attribute name="value" type="java.lang.String" required="false" description="隐藏域值（ID）"%>
<%@ attribute name="kind" type="java.lang.String" required="true" description="single单附件more多附件"%>
<%@ attribute name="filetype" type="java.lang.String" required="false" description="文件类型"%>
<%@ attribute name="filesize" type="java.lang.String" required="false" description="文件大小"%>
<%@ attribute name="buttonName" type="java.lang.String" required="false" description="按钮名称"%>
<%@ attribute name="isFilterEncryption" type="java.lang.String" required="false" description="yes过滤加密no忽略加密，默认不校验加密"%>

<!-- DOC DOCX XLS XLSX BMP PNG JPG JPEG TXT RAR 7Z ZIP PDF NO-->
<style>
    .showFile{
        margin-top:8px;
        margin-left:8px;
        background-color:#FFF;
        background-repeat:no-repeat;
        background-image: url('${ctxStatic}/images/fileico_v5.png');
    }

	.showFile-WY{
	    width:32px;
		height:32px;
		background-position:-0px -64px;
	}

	.showFile-DOC{
        width:32px;
        height:32px;
        background-position:-64px -64px;
    }

    .showFile-DOCX{
        width:32px;
        height:32px;
        background-position:-64px -64px;
    }

    .showFile-XLS{
        width:32px;
        height:32px;
        background-position:-32px -64px;
    }

    .showFile-XLSX{
        width:32px;
        height:32px;
        background-position:-32px -64px;
    }

    .showFile-BMP{
        width:32px;
        height:32px;
        background-position:-96px -96px;
    }

    .showFile-PNG{
        width:32px;
        height:32px;
        background-position:-64px -96px;
    }

    .showFile-JPG{
        width:32px;
        height:32px;
        background-position:-0px -96px;
    }

    .showFile-JPEG{
        width:32px;
        height:32px;
        background-position:-0px -96px;
    }

    .showFile-TXT{
        width:32px;
        height:32px;
        background-position:-672px -64px;
    }

    .showFile-RAR{
        width:32px;
        height:32px;
        background-position:-160px -64px;
    }

    .showFile-7Z{
        width:32px;
        height:32px;
        background-position:-128px -64px;
    }

    .showFile-ZIP{
        width:32px;
        height:32px;
        background-position:-160px -64px;
    }

    .showFile-PDF{
        width:32px;
        height:32px;
        background-position:-512px -64px;
    }

    .showFile-NO{
        width:32px;
        height:32px;
        background-position:-384px -64px;
    }

</style>

<div id="fileupload-buttonbar${id}" class="fileupload-buttonbar" >
    <div id="fileSpan${id}" class="btn btn-primary fileinput-button" >
        <i  class="glyphicon glyphicon-plus"></i>
        <input type="file" id="file${id}" name="file${id}"
            style="opacity:0;position: absolute;width: 100px;height: 32px;"/>
        <input type="hidden" id="${id}" name ="${name}" value="${value}" class="haveFileId"/>
        <span style="margin-left: 3px;" id="label${id}">${(buttonName == null or buttonName == '')?'图片上传':buttonName}</span>
    </div>
    <br/>
</div>

<script type="text/javascript">

    /*随机数方法*/
    function randomNum(iNumber,iNow){
        var arrNumber = [];
        var newNumber = [];
        for(var i=0;i<=iNumber;i++){
            arrNumber.push(i);
        }

        for(var i=0;i<iNow;i++){
            newNumber.push(arrNumber.splice(Math.floor(Math.random()*arrNumber.length),1)); //这里注意用法Math.floor向下取整，随机数取得数【0-1）不会越界，如果使用Math.round则，后面数组的长度则-1，否则数组下标越界，会有问题
        }

        return newNumber;

    }

	var addId = randomNum(1000,1);               // 页面文件ID保持递增
	var fileArray = new Array(); // 存储页面文件ID的数组
    $("#${id}Img").attr("src","${ctx}/base/tBaFile/showImage?id=${id}&folderPath=${imgUrl}&fileName=${id}&fileType=.${filetype}&v="+addId);

	var jqXHR = $("#file${id}").fileupload({
                autoUpload: true,                // 是否自动上传
                url: "${ctx}/sys/picConfig/save", // 上传地址
                formData: {name: "${id}", filesize: "${filesize}", isFilterEncryption : "${isFilterEncryption}"
                    , imgUrl : "${imgUrl}"},       // 传递name
                sequentialUploads: true,         // 是否按顺序上传文件
                dataType: 'json',                // json格式，不加上ie9不能传值
                //maxFileSize: 50 * 1024 * 1024,   // 文件大小，ie9不起作用
                done: function (e, data) {
                    var isFilterEncryption = "${isFilterEncryption}"; // 是否过滤加密文件
                    var id = "${id}";     // 传入的Id号
                    var result = data.result["result"];     // 上传的结果
                    if(isFilterEncryption == null || isFilterEncryption == ""){
                        isFilterEncryption = "no";
                    }
                    if(isFilterEncryption == "yes" && result == "error"){
                        var messageError = data.result["messageError"];     // 上传的结果
                        if("havePassword" == messageError){
                            top.$.jBox.tip("您上传的图片存在加密问题，请重新上传!", "warning");
                        }else {
                            top.$.jBox.tip("您上传的图片存在问题，请重新上传!", "warning");
                        }
                    }else {
                    	top.$.jBox.tip("图片上传成功","success");
                        $("#${id}Img").attr("src","${ctx}/base/tBaFile/showImage?id=${id}&folderPath=${imgUrl}&fileName=${id}&fileType=.${filetype}&v="+addId);
                    }
                },
                progressall: function (e, data) {
                    loading('正在提交，请稍等...');
                },
                fail: function (e, data) {
                    top.$.jBox.tip("上传图片过大，上传失败!", "warning");
                },
                change: function (e, data){ // 文件上传入口
                    var flag = "0"; // 1:不启动；0：启动
                    var filename = data.files[0].name; // 获取文件名
                    var lastLength = filename.lastIndexOf(".");
                    var fileNewType = filename.substring(lastLength + 1,filename.length).toUpperCase();
                    var flagType = 0;
                    var fileType = "${filetype}";
                    var tipType = "";
                    var strs = new Array(); //定义储存文件类型的数组
                    strs = fileType.split(",");
                    for (i = 0;i < strs.length;i++ ){
                        var temp = strs[i].toUpperCase();
                        if(temp != "" && fileNewType == temp){
                            flagType = 1;
                        }
                        if(temp != ""){
                            if(tipType == ""){
                                tipType = temp;
                            }else{
                                tipType = tipType + "或者" +temp;
                            }
                        }
                    }
                    if(flagType == 0 && fileType != ""){
                        top.$.jBox.tip("附件类型只能为" + tipType + "格式!请重新选择!", "warning");
                        $("#file${id}").fileupload("destory");
                    }
                    var activeUploads = $("#file${id}").fileupload("active");// 正在执行的文件数
                    if(0 != activeUploads && 0 != fileArray.length){
                        for(var i = 0;i < fileArray.length;i++) {
                            var tempId = fileArray[i];
                            var tempLength = $("#fileupload-buttonbar${id}").children("#newBody" + tempId).length;
                            if(0 == tempLength) { //被删除的文件长度为0
                                 flag = "1";
                            }
                        }
                    }
                    if("1" == flag){// 文件正在上传
                        top.$.jBox.tip("后台正在处理文件，请稍等!", "warning");
                        $("#file${id}").fileupload("destory");
                    }else{
                        addId = addId + 1;             // 上传页面文件ID递增
                        fileArray.push(addId);         // 将页面的ID放在数组中
                    }
                    var kind = "${kind}";              // 文件上传的类型 single：单附件 more：多附件
                    <%--if(kind != "more"){//单附件--%>
                       <%--$("#fileSpan${id}").css("display", "none"); // 单附件上传要隐藏按钮--%>
                       <%--$("#fileupload-buttonbar${id}").find("br").remove();--%>
                    <%--}else{//多附件--%>
                    <%--}--%>
                    var filenameShow = filename;
                    if(filename.length > 11){
                        filenameShow = filename.substring(0, 11) + "...";
                    }
                    if(fileNewType != "DOC" && fileNewType != "DOCX" && fileNewType != "XLS" && fileNewType != "XLSX"
                        && fileNewType != "BMP" && fileNewType != "JPG" && fileNewType != "JPEG" && fileNewType != "PNG"
                        && fileNewType != "GIF" && fileNewType != "PDF" && fileNewType != "TXT" && fileNewType != "RAR"
                        && fileNewType != "ZIP" && fileNewType != "7Z" ){
                        fileNewType = "NO";
                    }
                }
            });
</script>