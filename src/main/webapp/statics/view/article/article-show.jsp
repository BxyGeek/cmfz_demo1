<%@page contentType="text/html; UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="app" value="${pageContext.request.contextPath}"></c:set>
<script>

    $("#article_table").jqGrid({
        url : '${app}/article/queryAllArticles',
        datatype : "json",
        colNames : [ '编号', '标题', '内容', '创建时间', '作者', '操作' ],
        colModel : [
            {name : 'id',width : 35,hidden:true},
            {name : 'articleName',width : 40,editable:true,align:'center'},
            {name : 'content',width : 100,editable:true},
            {name : 'createDate',width : 40,align:'center'  },
            {name : 'guruId',width : 40,align:'center'},
            {name : 'options',width : 50,align:'center',
                formatter:function(cellvalue, options, row){
                    //return " <div class='btn btn-warning' onclick=\"openModal('edit','"+row.id+"')\">修改</div>    ";//<div class='btn btn-danger'>删除</a></div>
                    return " <div class='btn btn-warning' onclick=\"openModal('edit','" + row.id + "')\">修改</div>  <div class='btn btn-danger'><a href='${app}/article/option?oper=del&id=" + row.id + "'>删除</a></div> ";
                }
            }
        ],
        fit:true,
        styleUI:'Bootstrap',
        autowidth:true,
        height:500,
        rowNum : 2,
        rowList : [ 2,5,10, 20, 30 ],
        pager : '#article_pager',
        sortname : 'createDate',
        viewrecords : true,
        sortorder : "desc",
        //caption : "文章详细信息",
        editurl : "${app}/article/option"
    }).navGrid("#article_pager", {edit : false,add : false,del : true,search:false},
        {
            //控制修改
            closeAfterEdit:close,
            afterSubmit:function (response) {
                console.log("response*******"+response);
                var code = response.responseJSON.code;
                var id = response.responseJSON.data;
                console.log("code*******"+code);
                console.log("data*******"+id);
                if (code == 200){
                    $.ajaxFileUpload({
                        url:'${app}/article/upload',
                        fileElementId:'cover',
                        data:{id:id},
                        type:'post',
                        success:function () {
                            $('#banner_table').trigger('reloadGrid');
                        }
                    })
                }
                return "可以了";
            }
        },
        {
            //控制添加
            //关闭添加对话框
            closeAfterAdd:close,
            afterSubmit:function (response) {
                console.log("response*******"+response);
                var code = response.responseJSON.code;
                var id = response.responseJSON.data;
                console.log("code*******"+code);
                console.log("data*******"+id);
                if (code == 200){
                    $.ajaxFileUpload({
                        url:'${app}/article/upload',
                        fileElementId:'cover',
                        data:{id:id},
                        type:'post',
                        success:function () {
                            $('#banner_table').trigger('reloadGrid');
                        }
                    })
                }
                return "可以了";
            }
        },
        {
            //删除
        }
    );
    KindEditor.create('#editor_id', {
            //初始化参数
            width:'700px',
            wellFormatMode:true,
            resizeType:1,
            //themeType:'common'
            //shadowMode:false
            //图片空间按钮
            allowFileManager:true,
            //图片空间的路径
            fileManagerJson:'${app}/article/browser',
            //文件上传的路径
            uploadJson:'${app}/article/upload',
            afterBlur:function () {
                this.sync();
            }
    })
//    添加文章
    function saveArticle() {
        $.ajax({
            url:'${app}/article/option',
            type:'POST',
            dataType:'JSON',
            //使用表单的序列化传递参数
            data:$("#article-form").serialize(),
            success:function () {
                //关闭模态框
                $('#myModal').modal('hide');
                //刷新表单
                $('#article_table').trigger('reloadGrid');
            }
            //    ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQC5W8l/YVRWXxELbjHazGm/vsnHHqJ1Hg2OWsm5APLm1jsrqnjIOCMA/LO9Mq2PmMFD6LjwE2hRViwHigAgUKH/u1uuiwGFCWw9n3aes5BSDV/K5h8mbE+QmXLuclmbLkrsMspw/XafSvA2n6etEEVgPg+Q1QawJRyapNM3/lLV+Z4utyIKrJTZ5HjX55SXxhNhS7HqBr4Z0Unvt9Tk+xOzpVY1j/oA0r+yeaK33Z8amlfbQGz30siqmhZx2M5MqTaowyVUwPjUSTwYeceL4xkPBKPbcmZR2aMp9cG+UjpYJfCPcBzGKsJb3ICJokK7xX06eslFBIJ5+4ROBzMt7BvJ
            //    ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDixo8edjVrqrB/JoCL10R0/PbALb3t+eZKmIYZ1aoAmwlQKUCdnryJwGxtwse3MkSrZ7J1knNLLrpeIVnMreBY0nvOXG8RAe0KPmfx19+EeGit9E79XKOD4sxHw6Dvzi31oQhS6NHPld2kG7G1NeNp6rW41jRWVduSjxYPONHmH8BR/6FC7/79gD1WHKYGh9Gx2KL9jdcNmV3kSJJ9Wq8hv07trTcoI0LN1a3Z+fuKEvnOdxBchZZA8JUA+XzCIV17V1cZ8pPs++RMP8ENiSZv1A5sN7+pRiRVjLtuGXV2y9Dk7F8+6jhhUFlfnk5uMswPPKepAyDa/htUAWXgvl1L bxy@bxydeMacBook-Pro.local




        })
    }
//打开模态框
    function openModal(oper,id) {
        KindEditor.html('#editor_id','');
        var article = $('#article_table').jqGrid('getRowData',id);
        $('#article-id').val(article.id);
        $('#article-title').val(article.articleName);
        $('#article-author').val(article.guruId);
        //使用KindEditor对象向某个id下追加
        KindEditor.html('#editor_id',article.content);
        $('#myModal').modal('show');
        $('#article-author').val(article.guruId);
        $('#article-oper').val(oper);
    }

</script>
<ul class="nav nav-pills">
    <li role="presentation" class="active">
        <button type="button" class="btn btn-primary">
            文章列表
        </button>
    </li>
    <li role="presentation" class="active">
        <button type="button" class="btn btn-primary" onclick='openModal("add","")'>
            添加文章
        </button>
    </li>

</ul>

<table id="article_table"></table>
<div id="article_pager"></div>

<%--定义点击按钮及模态框--%>


<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width: 730px">
            <%--头--%>
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">我的文章</h4>
            </div>
            <%--内容--%>
            <div class="modal-body">

                <form class="form-horizontal" id="article-form">
                    <input type="hidden" name="id" id="article-id">
                    <input type="hidden" name="oper" id="article-oper">
                    <div class="form-group">
                        <label for="article-title" class="col-sm-2 control-label">标题</label>
                        <div class="col-sm-10">
                            <input type="text" name="articleName" class="form-control" id="article-title" placeholder="请输入标题">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="article-author" class="col-sm-2 control-label">作者</label>
                        <div class="col-sm-10">
                            <input type="text" name="guruId" class="form-control" id="article-author" placeholder="请输入作者">
                        </div>
                    </div>
                    <textarea id="editor_id" name="content" style="width:700px;height:300px;">

                    </textarea>
                </form>


            </div>
            <%--脚--%>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="saveArticle()">Save</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>



