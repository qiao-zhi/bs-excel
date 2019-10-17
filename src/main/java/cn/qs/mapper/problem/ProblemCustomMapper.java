package cn.qs.mapper.problem;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProblemCustomMapper {

	List<String> listDistinctSystemType();

	List<Map<String, Object>> listByCondition2(Map condition);

}
