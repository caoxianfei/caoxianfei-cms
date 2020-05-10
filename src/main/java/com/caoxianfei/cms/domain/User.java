package com.caoxianfei.cms.domain;

import java.io.Serializable;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @ClassName: user
 * @Description:  用户
 * @author: caoxianfei
 * 2020年4月27日  下午3:42:20
 */
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;  //主键
	private String username; //用户名
	private String password; //密码
	private String nickname;//昵称
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;  //生日
	private Gender gender; //性别
	private Integer role;  //角色  0：普通用户 1:管理员
	private boolean locked; //账号是否禁用 false：未被禁用  true：已被禁用
	private Date created; //创建时间
	private Date updated;  // 修改时间
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", nickname=" + nickname
				+ ", birthday=" + birthday + ", gender=" + gender + ", role=" + role + ", locked=" + locked
				+ ", created=" + created + ", updated=" + updated + "]";
	}
	

	
	
}
