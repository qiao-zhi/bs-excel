var pageUrl = "/problem/page3.html";
var deleteUrl = "/problem/delete.html";
var updateUrl = "/problem/update.html";

$(function(){
	queryFY();
});

/**
 * 填充表格数据
 * @param pageInfo  ajax返回的参数信息
 */
function showTable(pageInfo) {
    var total = pageInfo.total;//总数
    var pageNum = pageInfo.pageNum;//页号
    var pageSize = pageInfo.pageSize;//页大小

    var beans = pageInfo.list;
    $("#tbody").html("");//清空表格中数据并重新填充数据
    for(var i=0,length_1 = beans.length; i<length_1; i++){
        var index = (pageNum - 1) * pageSize + i + 1;
        var tr = "<tr>"
            +'<td>'+index+'</td>'
            +'<td>'+replaceNull(beans[i].system_name)+'</td>'
            +'<td>'+replaceNull(beans[i].system_type)+'</td>'
            +'<td>'+replaceNull(beans[i].test_type)+'</td>'
            +'<td>'+replaceNull(beans[i].version)+'</td>'
            +'<td>'+replaceNull(beans[i].test_manufacture)+'</td>'
            +'<td>'+replaceNull(beans[i].test_person)+'</td>'
            +'<td>'+replaceNull(beans[i].test_date)+'</td>'
            +'<td>'+replaceNull(beans[i].problem_type)+'</td>'
            +'<td>'+replaceNull(beans[i].problem_name)+'</td>'
            +'<td>'+replaceNull(beans[i].risk_level)+'</td>'
            +'<td>'+replaceNull(beans[i].problem_desc)+'</td>'
            +'<td>'+replaceNull(beans[i].resolve_method)+'</td>'
            +'<td>'+replaceNull(beans[i].resolve_plan)+'</td>'
            +'<td>'+replaceNull(beans[i].resolve_result)+'</td>'
            +'<td>'+replaceNull(beans[i].finish_date)+'</td>'
            +'<td>'+replaceNull(beans[i].remark)+'</td>'
            +'<td>'+replaceNull(beans[i].result)+'</td>'
            +'<td>';
	        if(isAdmin()){
	        	tr+='<a href=javascript:void(0) title="修改" onclick="update(\''+beans[i].id+'\', 800, 400)"><i class="layui-icon">&#xe642;</i></a>'
	        		+'<a href=javascript:void(0) title="删除" onclick="remove(\''+beans[i].id+'\')"><i class="layui-icon">&#xe640;</i></a>'
	        }
            
	        tr+='<a href="/message/detail/'+beans[i].id+'" target="_blank" title="查看详情"><i class="layui-icon">&#xe615;</i></a>';
        	tr +='</td></tr>'
        		
        $("#tbody").append(tr);
    }

    //开启分页组件
    showPage(total, pageNum, pageSize);
}