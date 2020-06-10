/**
 * 
 */
package com.caoxianfei.cms.service.impl;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caoxianfei.cms.dao.ArticleMapper;
import com.caoxianfei.cms.domain.Article;
import com.caoxianfei.cms.service.ArticleService;
import com.caoxianfei.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0
 * @date 
 *	  创建于:2020年4月29日下午7:24:18
 */
@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private ArticleMapper articleMapper;

	public PageInfo<Article> selects(Article article, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<Article> list = articleMapper.selects(article);
		for (Article article2 : list) {
			
			article2.setDisplayDate(DateUtil.getDisplayTime(article2.getCreated()));
		}
		return new PageInfo<Article>(list);
	}

	public Article select(Integer id) {
		// TODO Auto-generated method stub
	
		return articleMapper.select(id);
	}

	public int insert(Article article) {
		// TODO Auto-generated method stub
		return articleMapper.insert(article);
	}

	public int update(Article article) {
		// TODO Auto-generated method stub
		return articleMapper.update(article);
	}

	
	
}
