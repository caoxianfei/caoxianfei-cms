/**
 * 
 */
package com.caoxianfei.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.caoxianfei.cms.domain.Article;

/**
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0
 * @date 
 *	  创建于:2020年4月29日下午7:20:07
 */
public interface ArticleMapper {

	boolean add(Article article);
	
	List<Article> getMyArticleList(int uid);

	Article getArticleById(Integer articleId);


	List<Article> getArticleList(Article article);

	Boolean update(Article article);

	List<Article> getByChannelIdAndCategoryId(@Param("channelId")Integer channelId, @Param("categoryId")Integer categoryId);
	
	List<Article> getHotList();

	List<Article> getLastArticles();


	
}
