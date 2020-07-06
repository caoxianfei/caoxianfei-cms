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
		
		/**
		 * 
		 *	 功能: 文章模糊查询
		 *	@return :   List<Article>
		 *
		 */
		List<Article> selects(Article article);   //查询多个文章
		
		Article select(Integer id);   // 回显 单个文章内容

		int insert (Article article); //发布文章
	
		int update(Article article);
		//根据id获取对象
		Article getById(Integer id);
}
