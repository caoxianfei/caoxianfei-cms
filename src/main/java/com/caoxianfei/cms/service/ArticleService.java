/**
 * 
 */
package com.caoxianfei.cms.service;

import java.util.List;

import com.caoxianfei.cms.domain.Article;
import com.github.pagehelper.PageInfo;

/**
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0
 * @date 
 *	  创建于:2020年4月29日下午7:22:52
 */
public interface ArticleService {

	boolean add(Article article);
	
	PageInfo<Article> getMyArticleList(int pageNum, int uid);

	Article getArticleById(Integer articleId);

	PageInfo<Article> getArticleList(Article article, Integer pageNum);

	Boolean update(Article article);

	PageInfo<Article> getByChannelIdAndCategoryId(Integer channelId, Integer categoryId, Integer pageNum);
	
	PageInfo<Article> getHotList(Integer pageNum);

	List<Article> getLastArticles();
}
