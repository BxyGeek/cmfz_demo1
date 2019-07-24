<%@page contentType="text/html; UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="app" value="${pageContext.request.contextPath}"></c:set>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="shortcut icon" href="statics/ico/favicon.ico">
    <title>持明法洲后台管理系统</title>

    <link rel="stylesheet" href="statics/boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="statics/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <link rel="shortcut icon" href="statics/ico/favicon.ico">

    <script src="statics/boot/js/jquery-2.2.1.min.js"></script>
    <script src="statics/boot/js/bootstrap.min.js"></script>
    <%--引入jqgrid--%>
    <script src="statics/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="statics/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <script src="statics/jqgrid/js/ajaxfileupload.js"></script>
    <%--引入kindedit相关--%>
    <script charset="utf-8" src="${app}/statics/kindeditor/kindeditor-all.js"></script>
    <script charset="utf-8" src="${app}/statics/kindeditor/lang/zh-CN.js"></script>

    <script>

        $(function(){

        })


    </script>
</head>
<body>
<%--顶部--%>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">持明法洲后台管理系统</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">


            <ul class="nav navbar-nav navbar-right">
                <li><a>您好：${admin.adminName}</a></li>
                <li><a href="${app}/admin/exit">安全退出</a></li>
            </ul>
        </div>
    </div>
</nav>
<%--中间衫格系统--%>
<div class="row">
    <%--左侧--%>
    <div class="col-xs-2">
        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingOne">
                    <h4 class="panel-title text-center">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                            <h4>轮播图管理</h4>
                        </a>
                    </h4>
                </div>
                <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                    <div class="panel-body text-center">
                        <a href="javascript:$('#contentLayout').load('${app}/statics/view/banner/banner-show.jsp')" class="btn btn-default">查询轮播图</a>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingTwo">
                    <h4 class="panel-title text-center">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                            <h4>专辑管理</h4>
                        </a>
                    </h4>
                </div>
                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                    <div class="panel-body text-center">
                        <a href="javascript:$('#contentLayout').load('${app}/statics/view/album/album-show.jsp')" class="btn btn-default">查询专辑</a>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingThree">
                    <h4 class="panel-title text-center">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                            <h4>章节管理</h4>
                        </a>
                    </h4>
                </div>
                <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                    <div class="panel-body text-center">
                        <a href="javascript:$('#contentLayout').load('${app}/statics/view/article/article-show.jsp')" class="btn btn-default">查询文章</a>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingFour">
                    <h4 class="panel-title text-center">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                            <h4>用户管理</h4>
                        </a>
                    </h4>
                </div>
                <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
                    <div class="panel-body text-center">
                        <a href="javascript:$('#contentLayout').load('${app}/statics/view/user/user-show.jsp')" class="btn btn-default">查询用户</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%--右侧--%>
    <div class="col-xs-10" id="contentLayout">
        <div class="jumbotron" style="padding-left: 88px">
            <h2>欢迎使用持明法洲后台管理系统</h2>
        </div>
        <img src="statics/image/shouye.jpg" style="width: 100%">

    </div>
</div>

<%--底部--%>
<footer class="navbar-fixed-bottom">
    <div class="panel panel-footer">
        <h3 style="text-align: center"><small>持明法洲后台管理系统@百知教育2019-7-18</small></h3>
    </div>
</footer>
</body>

</html>
