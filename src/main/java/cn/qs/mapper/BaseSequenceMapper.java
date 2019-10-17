package cn.qs.mapper;

import org.springframework.data.repository.NoRepositoryBean;

/**
 * 通用的CRUDMapper接口
 * 
 * @author Administrator
 *
 * @param <T>
 * @param <E>
 */
@NoRepositoryBean
public interface BaseSequenceMapper<T> extends BaseMapper<T, Integer> {

}
