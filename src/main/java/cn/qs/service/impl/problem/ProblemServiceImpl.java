package cn.qs.service.impl.problem;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qs.bean.problem.Problem;
import cn.qs.mapper.BaseMapper;
import cn.qs.mapper.problem.ProblemCustomMapper;
import cn.qs.mapper.problem.ProblemMapper;
import cn.qs.service.impl.AbastractBaseSequenceServiceImpl;
import cn.qs.service.problem.ProblemService;

@Service
public class ProblemServiceImpl extends AbastractBaseSequenceServiceImpl<Problem> implements ProblemService {

	@Autowired
	private ProblemMapper problemMapper;

	@Autowired
	private ProblemCustomMapper problemCustomMapper;

	@Override
	public BaseMapper<Problem, Integer> getBaseMapper() {
		return problemMapper;
	}

	@Override
	public List<String> listDistinctSystemType() {
		return problemCustomMapper.listDistinctSystemType();
	}

	@Override
	public List<Map<String, Object>> listByCondition2(Map condition) {
		return problemCustomMapper.listByCondition2(condition);
	}

}
