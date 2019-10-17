package cn.qs.service.problem;

import java.util.List;
import java.util.Map;

import cn.qs.bean.problem.Problem;
import cn.qs.service.BaseSequenceService;

public interface ProblemService extends BaseSequenceService<Problem> {

	List<String> listDistinctSystemType();

	List<Map<String, Object>> listByCondition2(Map condition);

}
