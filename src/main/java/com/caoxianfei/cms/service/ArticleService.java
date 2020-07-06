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

	PageInfo<Article> selects(Article article,Integer pageNum,Integer pageSize);
	
	Article select(Integer id); 
	
	int insert (Article article); //发布文章
	
	int update(Article article);

	/**
	 *	 功能: 从es 中 高亮查询
	 *	@return :
	 *  
	 */
	PageInfo<Article> selectFromES(Integer pageNum, Integer pageSize, String key);

	/**
	 *	 功能:  查询最新文章
	 *	@return :
	 *  
	 */
	PageInfo<Article> selectLast(Article article,Integer pageNum,Integer pageSize);

	/**
	 *	 功能: 查询热点文章
	 *	@return :
	 *  
	 */
	PageInfo<Article> selectHot(Article article, Integer pageNum, Integer pageSize);
	
	
}
