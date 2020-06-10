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
}
