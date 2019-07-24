<%@page contentType="text/html; UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="app" value="${pageContext.request.contextPath}"></c:set>
<script>
    $(function () {
        $("#album_table").jqGrid({
            url : '${app}/album/queryAll',
            editurl:'${app}/album/option',
            datatype : "json",
            height : 500,
            colNames : [ '编号', '标题', '封面', '数量', '评分','作者', '播音','简介','创建日期' ],
            colModel : [

                {name : 'id',width : 35,hidden:true},
                {name : 'title',width : 60,editable:true},
                {name : 'cover',width : 70,editable:true,edittype:'file',align:'center',
                    formatter:function(cellvalue, options, data){
                        return "<img style='height:100px' src='${app}/statics/image/picture/"+cellvalue+"'/>";
                    }
                },
                {name : 'amount',width : 80,align : "center"},
                {name : 'score',width : 80,align : "center"},
                {name : 'author',width : 80,align : "center",editable:true},
                {name : 'broadcast',width : 150,align : "center",editable:true},
                {name : 'brief',width : 150,align : "center",editable:true},
                {name: 'createDate', width: 150}

            ],
            styleUI:'Bootstrap',
            autowidth:true,
            rowNum : 3,
            rowList : [ 3,5,10,20,30 ],
            pager : '#album_pager',
            sortname : 'id',
            viewrecords : true,
            sortorder : "desc",
            multiselect : false,
            subGrid : true,
            caption : "展示所有专辑详细信息",

            subGridRowExpanded : function(subgrid_id, albumId) {
                var subgrid_table_id = subgrid_id + "_t";  //子表格的table id
                var pager_id = "p_" + subgrid_table_id;    //子表格pager id
                $("#" + subgrid_id).html(                  //向子表格追加标签
                    "<table id='" + subgrid_table_id +"' class='scroll'></table>" +
                    "<div id='" + pager_id + "' class='scroll'></div>");
                $("#" + subgrid_table_id).jqGrid(
                    {
                        url : "${app}/chapter/queryChaptersByAlbumId?albumId=" + albumId,
                        editurl:'${app}/chapter/option?albumId=' + albumId,
                        datatype : "json",
                        colNames : [ '编号', '章节名称', '大小', '时长','创建时间','播放章节' ],
                        colModel : [
                            {name : "id", width : 80,key : true,align : "center",hidden:true},
                            {name : "chapterName",  width : 30,align : "center",editable:true},
                            {name : "size",width : 30,align : "center"},
                            {name : "duration",width : 30,align : "center"},
                            {name : "createDate",width : 70,align : "center"},
                            {
                                name : "fileName",width : 70,align : "center",edittype:'file',editable:true,
                                formatter:function(cellvalue, options, data){
                                    return "<audio controls loop><source src='${app}/statics/audio/"+cellvalue+"' type='audio/mpeg'></source></audio>";
                                }
                            }

                        ],
                        styleUI:'Bootstrap',
                        autowidth:true,
                        rowNum : 3,
                        rowList : [ 3,5,10,20,30 ],
                        pager : pager_id,

                        sortname : 'id',
                        sortorder : "asc",
                        height : '400'//100%
                    });
                $("#" + subgrid_table_id).jqGrid('navGrid',
                    "#" + pager_id, {
                        edit : true,
                        add : true,
                        del : true
                    },{
                        //控制修改
                    },{
                        //控制添加
                        closeAfterAdd:true,
                        afterSubmit:function (response) {
                            console.log("response*******"+response);
                            var code = response.responseJSON.code;
                            var id = response.responseJSON.data;
                            console.log("code*******"+code);
                            console.log("data*******"+id);
                            if (code == 200){
                                $.ajaxFileUpload({
                                    url:'${app}/chapter/upload',
                                    fileElementId:'fileName',
                                    data:{id:id},
                                    type:'post',
                                    success:function () {
                                        $('#subgrid_table_id').trigger('reloadGrid');
                                    }
                                })
                            }
                            return "可以了";
                        }
                    },{
                        //控制删除
                    } );
            }
        }).navGrid("#album_pager", {edit : true,add : true,del : true})

    })

</script>


<table id="album_table"></table>
<div id="album_pager"></div>