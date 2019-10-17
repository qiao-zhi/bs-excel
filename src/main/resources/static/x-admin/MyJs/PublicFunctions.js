/**
 * PublicFunctions.js 一些公共的函数
 */

/**
 * 判断是否是系统管理员角色
 */
function isAdmin() {
	if (typeof admin != "undefined") {
		return true;
	} 

	return false;
}

/**
 * 一个值如果是null或者''返回-
 * 
 * @param value
 *            需要处理的值
 * @param length
 *            需要截取的字符的长度的值,未指定的时候返回全部
 * @returns {*} 处理过的值
 */
function replaceNull(value, length) {
	// 判断截取的值是否为空
	if (value == null || value == undefined || value == ""
			|| value == 'undefined') {
		
		return "-";
	}
	// 判断长度是否为空
	if (length == null || length == '') {
		return value;
	}
	
	return value.toString().substr(0, length);
}

var NumberUtils = {
	toFixedDecimal : function(value, scale) {
		var defaultValue = 0.0;

		if (!value || isNaN(parseFloat(value))) {
			value = defaultValue;
		}

		if (!scale) {
			scale = 0;
		}

		value = parseFloat(value);
		return value.toFixed(scale);
	},

	toFixedDecimalWithPercent : function(value, scale) {
		value = NumberUtils.toFixedDecimal(value, 10);

		if (!scale) {
			scale = 0;
		}

		value = parseFloat(value * 100);
		return value.toFixed(scale) + "%";
	}
};

function serializeCheckboxValuesByName(name, spliter) {
	if (!spliter) {
		spliter = ",";
	}

	return getSerializeCheckboxValues("[name='" + name + "']:checked", spliter);
}

function serializeCheckboxValuesByClass(clazz, spliter) {
	if (!spliter) {
		spliter = ",";
	}

	return getSerializeCheckboxValues("." + clazz + ":checked", spliter);
}

function getSerializeCheckboxValues(selector, spliter) {
	if (!spliter) {
		spliter = ",";
	}

	var values = [];
	$(selector).each(function() {
		values.push($(this).val());
	});

	if (values.length > 0) {
		return values.join(spliter);
	}

	return "";
}

/**
 * 关闭当前页的函数
 */
function closeNowPage() {
	var closeTable = $(".layui-tab-title", parent.document).children("li");
	closeTable.each(function() {
		if ($(this).attr("class") == "layui-this") {
			$(this).children("i").trigger("click");// 触发其点击事件(关闭当前tab)--trigger用于触发一个元素的指定事件
		}
	})
}

/**
 * 导出excel的工具类
 * @formId 表单ID
 * @url 下单的URL
 */
function exportExcel(formId, url) {
	try {
		var queryForm = $("#" + formId);
		var exportForm = $("<form action='" + url + "' method='post'></form>")

		queryForm.find("input").each(
				function() {
					var name = $(this).attr("name");
					var value = $(this).val();
					exportForm.append("<input type='hidden' name='" + name + "' value='" + value + "'/>");
				});

		queryForm.find("select").each(
				function() {
					var name = $(this).attr("name");
					var value = $(this).val();
					exportForm.append("<input type='hidden' name='" + name + "' value='" + value + "'/>")
				});

		$(document.body).append(exportForm);
		exportForm.submit();
	} catch (e) {
		console.log(e);
	} finally {
		exportForm.remove();
	}
}

/*******************S   提取的公共分页相关函数****************************/
/**
 * 分页查询的函数
 */
function queryFY(){
    $.ajax({
       url: pageUrl,
       data: $("#queryForm").serialize(),
       dataType: 'json',
       type:'post',
       async: true,
       success: showTable
    });
}

/**
 * 显示分页插件
 */
function showPage(total,pageNum,pageSize){
    //使用layui的分页插件
    layui.use(['laypage', 'layer'], function(){
        var laypage = layui.laypage,layer = layui.layer;

        //执行一个laypage实例
        laypage.render({
            elem: 'pageDiv', //注意，这里的 test1 是 ID，不用加 # 号
            count: total, //数据总数，从服务端得到
            limit:pageSize,//每页显示的条数。laypage将会借助 count 和 limit 计算出分页数。
            curr:pageNum,//当前页号
            limits:[6,10,15,20],
            layout:['limit','prev', 'page', 'next','skip','count'],//显示哪些分页组件
            jump: function(obj, first){//点击页号的时候执行的函数
                //obj包含了当前分页的所有参数，比如：
                // console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                // console.log(obj.limit); //得到每页显示的条数

                $("[name='pageNum']").val(obj.curr);//向隐藏域设置当前页的值
                $("[name='pageSize']").val(obj.limit);//向隐藏域设置当前页的大小
                if(!first){//首次不执行(点击的时候才执行)
                	queryFY();//执行查询分页函数(这个函数必须写在这里)
                }
            }

        });
    });
}

/**
 * 清空条件后查询
 */
function clearQueryCondition(obj) {
    //1.清空条件
    var form = $(obj).parents("form");
    form.find("input").val("");
    form.find("select").val("");
    
    //2.重新查询一次
    queryFY();
}

/**
 * 删除
 */
function remove(id) {
	var layer = layui.layer;
	layer.confirm('确认删除?', function(index){
		$.post(deleteUrl, {"id": id},function(result) {
			if(result.success == true) {
				layer.msg("删除成功!");
				queryFY();
			} else {
				layer.msg(result.msg);
			}
		});
		
	  layer.close(index);
	});
}

/**
 * 修改
 */
function update(id, width, height) {
	var url = updateUrl + '?id=' + id;

	if (!width) {
		width = 600;
	}
	
	if (!height) {
		height = 400;
	}
	
	x_admin_show('修改', url, width, height);
}
/*******************E   提取的公共分页相关函数****************************/