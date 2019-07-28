<%@page contentType="text/html; UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="app" value="${pageContext.request.contextPath}"></c:set>
<script>

    $("#banner_table").jqGrid({
        url : '${app}/banner/queryAll',
        datatype : "json",
        colNames : [ '编号', '名称', '封面', '描述', '状态','日期' ],
       // cellsubmit:'right',
        colModel : [
            {name : 'id',width : 35,hidden:true},
            {name : 'bannerName',width : 60,editable:true},
            {name : 'cover',width : 70,editable:true,edittype:'file',align:'center',
                formatter:function(cellvalue, options, data){
                    return "<img style='height:100px' src='${app}/statics/image/picture/"+cellvalue+"'/>";
                }
            },
            {name : 'description',width : 80,editable:true},
            {name : 'status',width : 30,editable:true,edittype:'select',
                editoptions: { value:"1:正常;2:冻结"},
                formatter:function(cellvalue, options, data){

                    var temp = ""
                    if(cellvalue==1){
                        temp = "正常";
                    } else if(cellvalue==0){
                        temp = "冻结";
                    } else {
                        temp = "待定";
                    }
                    return temp;
                }
            },
            {name : 'createDate',width : 150}
        ],
        fit:true,
        styleUI:'Bootstrap',
        autowidth:true,
        height:500,
        rowNum : 3,
        rowList : [ 3,5,10, 20, 30 ],
        pager : '#banner_pager',
        sortname : 'createDate',
        viewrecords : true,
        sortorder : "desc",
        caption : "轮播图详细信息",
        editurl : "${app}/banner/option"
    }).navGrid("#banner_pager", {edit : true,add : true,del : true},
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
                        url:'${app}/banner/upload',
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
                        url:'${app}/banner/upload',
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


</script>
<table id="banner_table"></table>
<div id="banner_pager"></div>