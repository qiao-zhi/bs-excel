package cn.qs.controller.problem;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.qs.bean.problem.Problem;
import cn.qs.bean.user.User;
import cn.qs.controller.AbstractSequenceController;
import cn.qs.service.BaseService;
import cn.qs.service.problem.ProblemService;
import cn.qs.utils.BeanUtils;
import cn.qs.utils.DefaultValue;
import cn.qs.utils.JSONResultUtil;
import cn.qs.utils.export.ExcelReader;
import cn.qs.utils.system.SystemUtils;

@Controller
@RequestMapping("problem")
public class ProblemController extends AbstractSequenceController<Problem> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProblemController.class);

	@Autowired
	private ProblemService problemService;

	@Override
	public String getViewBasePath() {
		return "problem";
	}

	@Override
	public BaseService<Problem, Integer> getBaseService() {
		return problemService;
	}

	/**
	 * Mybatis分页(重写方法)
	 * 
	 * @param condition
	 * @return
	 */
	@RequestMapping("page3")
	@ResponseBody
	public PageInfo<Map<String, Object>> page3(@RequestParam Map condition) {
		int pageNum = 1;
		if (StringUtils.isNotBlank(MapUtils.getString(condition, "pageNum"))) { // 如果不为空的话改变当前页号
			pageNum = MapUtils.getInteger(condition, "pageNum");
		}
		int pageSize = DefaultValue.PAGE_SIZE;
		if (StringUtils.isNotBlank(MapUtils.getString(condition, "pageSize"))) { // 如果不为空的话改变当前页大小
			pageSize = MapUtils.getInteger(condition, "pageSize");
		}

		User loginUser = SystemUtils.getLoginUser();
		if (!"系统管理员".equals(loginUser.getRoles())) {
			String userblank = loginUser.getUserblank();
			List<String> parentNames = null;
			if (StringUtils.isNotBlank(userblank)) {
				parentNames = Arrays.asList(userblank.split(","));
			} else {
				parentNames = problemService.listDistinctSystemType();
			}

			condition.put("parentNames", parentNames);
		}

		// 开始分页
		PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> users = new ArrayList<>();
		try {
			users = problemService.listByCondition2(condition);
		} catch (Exception e) {
			LOGGER.error("getUsers error！", e);
		}
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(users);

		return pageInfo;
	}

	@RequestMapping("import")
	public String importFile() {
		return getViewPath("import");
	}

	@RequestMapping("/doImport")
	@ResponseBody
	public JSONResultUtil<String> doImport(MultipartFile file, HttpServletRequest request) {
		if (file == null) {
			return JSONResultUtil.error("文件接收失败");
		}

		try {
			InputStream inputStream = file.getInputStream();
			ExcelReader excelReader = new ExcelReader(inputStream);
			String[] headers = new String[] { "index", "systemName", "systemType", "testType", "version",
					"testManufacture", "testPerson", "testDate", "problemType", "problemName", "riskLevel",
					"problemDesc", "resolveMethod", "resolvePlan", "resolveResult", "finishDate", "remark", "result" };
			List<Map<String, Object>> readSheetDatas = excelReader.readSheetDatas(4, headers, 1);
			List<Problem> maps2Beans = BeanUtils.maps2Beans(readSheetDatas, Problem.class);
			for (Problem problem : maps2Beans) {
				try {
					problemService.add(problem);
				} catch (Exception ignore) {
					// ignore
				}
			}
		} catch (Exception e) {
			LOGGER.error("文件处理失败", e);
			return JSONResultUtil.error("文件处理失败");
		}

		return JSONResultUtil.ok();
	}
}
