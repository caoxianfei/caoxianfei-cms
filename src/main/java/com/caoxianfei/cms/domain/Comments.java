package com.caoxianfei.cms.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0
 * @date 
 *	  创建于:2020年6月8日下午8:09:29
 */
public class Comments implements Serializable {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer userId;
	private Integer articleId;
	private String content;
	private Date created;
	
	private String displaytime;
	
	@Override
	public String toString() {
		return "Comments [id=" + id + ", userId=" + userId + ", articleId=" + articleId + ", content=" + content
				+ ", created=" + created + ", displaytime=" + displaytime + ", user=" + user + ", article=" + article
				+ "]";
	}
	public Comments() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getDisplaytime() {
		return displaytime;
	}
	public void setDisplaytime(String displaytime) {
		this.displaytime = displaytime;
	}
	private  User user;
	private Article article;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	
	
	
	

	
}
