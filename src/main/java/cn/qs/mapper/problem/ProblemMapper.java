package cn.qs.mapper.problem;

import org.apache.ibatis.annotations.Mapper;

import cn.qs.bean.problem.Problem;
import cn.qs.mapper.BaseSequenceMapper;
@Mapper
public interface ProblemMapper extends BaseSequenceMapper<Problem> {

}
