var pageUrl = "/user/page2.html";
var deleteUrl = "/user/delete.html";
var updateUrl = "/user/updateUser.html";

$(function(){
	queryFY();
});

function showTable(pageInfo){
    var total = pageInfo.total;//总数
    var pageNum = pageInfo.pageNum;//页号
    var pageSize = pageInfo.pageSize;//页大小

    var beans = pageInfo.list;
    $("#tbody").html("");//清空表格中数据并重新填充数据
    for(var i=0,length_1 = beans.length;i<length_1;i++){
        var index = (pageNum - 1) * pageSize + i + 1;
        var tr = "<tr>"
            +'<td>'+index+'</td>'
            +'<td>'+replaceNull(beans[i].username)+'</td>'
            +'<td>'+replaceNull(beans[i].fullname)+'</td>'
            +'<td>'+replaceNull(beans[i].sex)+'</td>'
            +'<td>'+replaceNull(beans[i].email)+'</td>'
            +'<td>'+replaceNull(beans[i].phone)+'</td>'
            +'<td>'+replaceNull(beans[i].createtime)+'</td>'
            +'<td>'+replaceNull(beans[i].roles)+'</td>'
            +'<td>'+replaceNull(beans[i].userblank)+'</td>'
            +'<td>';
        
        if (isAdmin()) {
        	tr+='<a href=javascript:void(0) title="点击修改用户" onclick="update(\''+beans[i].id+'\')"><i class="layui-icon">&#xe642;</i></a>'
        	+'<a href=javascript:void(0) title="点击删除该用户" onclick="remove(\''+beans[i].id+'\')"><i class="layui-icon">&#xe640;</i></a>';
        } else {
        	tr += "-";
        }
            
    	tr +='</td></tr>'
        $("#tbody").append(tr);
    }

    //开启分页组件
    showPage(total,pageNum,pageSize);
}