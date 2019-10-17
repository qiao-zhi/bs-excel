package cn.qs.controller;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.qs.service.BaseService;
import cn.qs.utils.DefaultValue;
import cn.qs.utils.JSONResultUtil;

/**
 * 所以控制层的基类
 * 
 * @author Administrator
 *
 */
public abstract class AbstractController<T, E extends Serializable> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractController.class);

	/**
	 * 页面所在的目录(由子类实现)
	 * 
	 * @return
	 */
	public abstract String getViewBasePath();

	/**
	 * service，由具体的Action注入对应实现
	 * 
	 * @return
	 */
	public abstract BaseService<T, E> getBaseService();

	/**
	 * 生成带basePath的页面路径
	 * 
	 * @param path
	 * @return
	 */
	public String getViewPath(String path) {
		return getViewBasePath() + "/" + path;
	}

	@RequestMapping("add")
	public String add() {
		return getViewPath("add");
	}

	@RequestMapping("doAdd")
	@ResponseBody
	public JSONResultUtil doAdd(T bean, HttpServletRequest request) {
		getBaseService().add(bean);
		return JSONResultUtil.ok();
	}

	@RequestMapping("list")
	public String list() {
		return getViewPath("list");
	}

	/**
	 * SpringDataJPA分页
	 * 
	 * @param condition
	 * @param request
	 * @return
	 */
	@RequestMapping("page")
	@ResponseBody
	public Page<T> page(@RequestParam Map condition, HttpServletRequest request) {
		int pageNum = 1;
		if (StringUtils.isNotBlank(MapUtils.getString(condition, "pageNum"))) { // 如果不为空的话改变当前页号
			pageNum = MapUtils.getInteger(condition, "pageNum");
		}
		int pageSize = DefaultValue.PAGE_SIZE;
		if (StringUtils.isNotBlank(MapUtils.getString(condition, "pageSize"))) { // 如果不为空的话改变当前页大小
			pageSize = MapUtils.getInteger(condition, "pageSize");
		}

		condition.put("pageNum", pageNum - 1);
		condition.put("pageSize", pageSize);

		Page<T> pages = null;
		// 开始分页
		try {
			pages = getBaseService().pageByCondition(condition);
		} catch (Exception e) {
			LOGGER.error("SpringDataJPA page error, viewbasePath : {}", getViewBasePath(), e);
		}
		
		System.out.println(JSONObject.toJSONString(pages));
		return pages;
	}

	/**
	 * mybatis分页(交由有需要的子类复写)
	 * 
	 * @param condition
	 * @param request
	 * @return
	 */
	@RequestMapping("page2")
	@ResponseBody
	public PageInfo<T> page2(@RequestParam Map condition, HttpServletRequest request) {
		return null;
	}

	@RequestMapping("delete")
	@ResponseBody
	public JSONResultUtil delete(E id) {
		getBaseService().delete(id);
		return JSONResultUtil.ok();
	}

	@RequestMapping("update")
	public String update(E id, ModelMap map, HttpServletRequest request) {
		T bean = getBaseService().findById(id);
		map.addAttribute("bean", bean);
		return getViewPath("update");
	}

	@RequestMapping("doUpdate")
	@ResponseBody
	public JSONResultUtil doUpdate(T bean) {
		getBaseService().update(bean);
		return JSONResultUtil.ok();
	}

	@RequestMapping("detail")
	public String detail(E id, ModelMap map, HttpServletRequest request) {
		T bean = getBaseService().findById(id);
		map.addAttribute("bean", bean);
		return getViewPath("detail");
	}

}
