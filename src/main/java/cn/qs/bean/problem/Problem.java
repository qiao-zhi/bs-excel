package cn.qs.bean.problem;

import javax.persistence.Column;
import javax.persistence.Entity;

import cn.qs.bean.AbstractSequenceEntity;

@Entity
public class Problem extends AbstractSequenceEntity {

	private String systemName;
	private String systemType;
	private String testType;
	private String version;
	@Column(length = 500)
	private String testManufacture;
	private String testPerson;
	private String testDate;
	private String problemType;
	private String problemName;
	private String riskLevel;
	@Column(length = 500)
	private String problemDesc;
	private String resolveMethod;
	@Column(length = 500)
	private String resolvePlan;
	@Column(length = 500)
	private String resolveResult;
	private String finishDate;
	@Column(length = 500)
	private String remark;
	private String result;

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTestManufacture() {
		return testManufacture;
	}

	public void setTestManufacture(String testManufacture) {
		this.testManufacture = testManufacture;
	}

	public String getTestPerson() {
		return testPerson;
	}

	public void setTestPerson(String testPerson) {
		this.testPerson = testPerson;
	}

	public String getTestDate() {
		return testDate;
	}

	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}

	public String getProblemType() {
		return problemType;
	}

	public void setProblemType(String problemType) {
		this.problemType = problemType;
	}

	public String getProblemName() {
		return problemName;
	}

	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	public String getProblemDesc() {
		return problemDesc;
	}

	public void setProblemDesc(String problemDesc) {
		this.problemDesc = problemDesc;
	}

	public String getResolveMethod() {
		return resolveMethod;
	}

	public void setResolveMethod(String resolveMethod) {
		this.resolveMethod = resolveMethod;
	}

	public String getResolvePlan() {
		return resolvePlan;
	}

	public void setResolvePlan(String resolvePlan) {
		this.resolvePlan = resolvePlan;
	}

	public String getResolveResult() {
		return resolveResult;
	}

	public void setResolveResult(String resolveResult) {
		this.resolveResult = resolveResult;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
