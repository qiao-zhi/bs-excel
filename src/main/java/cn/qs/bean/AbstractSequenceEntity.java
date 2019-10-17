package cn.qs.bean;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 所有实体类中相同的部分(自增ID类型的)
 * 
 * @author Administrator
 *
 */
@MappedSuperclass
public abstract class AbstractSequenceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Integer id;

	protected Date createtime;

	protected String creator;

	// 三个保留字段
	protected String remark1;
	protected String remark2;
	protected String remark3;

	public AbstractSequenceEntity() {
		this.createtime = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

}
