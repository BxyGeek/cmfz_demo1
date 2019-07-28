<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="app" value="${pageContext.request.contextPath}"></c:set>
<!doctype html>
<html lang="en">
<head>
    <title>KindEdit使用</title>
    <script charset="utf-8" src="${app}/statics/kindeditor/kindeditor-all.js"></script>
    <script charset="utf-8" src="${app}/statics/kindeditor/lang/zh-CN.js"></script>
    <script>
        KindEditor.ready(function(K) {
            window.editor = K.create('#editor_id', {
                //初始化参数
                width:'1000px',
                wellFormatMode:true,
                resizeType:1,
                //themeType:'common'
                //shadowMode:false
                //图片空间按钮
                allowFileManager:true,
                //图片空间的路径
                fileManagerJson:'${app}/article/browser',
                //文件上传的路径
                uploadJson:'${app}/article/upload'

            });
        })
    </script>
</head>
<body>
    <textarea id="editor_id" name="content" style="width:700px;height:300px;">
            &lt;strong&gt;HTML内容&lt;/strong&gt;
    </textarea>

</body>
</html>