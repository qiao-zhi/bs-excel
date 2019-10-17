package cn.qs.bean.user;

import java.util.Date;

import javax.persistence.Entity;

import cn.qs.bean.AbstractSequenceEntity;

@Entity
public class User extends AbstractSequenceEntity {

	private String username;

	private String password;

	private String fullname;

	private String sex;

	private String phone;

	private String email;

	private Date updatetime;

	private String roles;

	private String userblank;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname == null ? null : fullname.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles == null ? null : roles.trim();
	}

	public String getUserblank() {
		return userblank;
	}

	public void setUserblank(String userblank) {
		this.userblank = userblank == null ? null : userblank.trim();
	}
}