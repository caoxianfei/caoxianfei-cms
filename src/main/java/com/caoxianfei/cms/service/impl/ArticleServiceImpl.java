/**
 * 
 */
package com.caoxianfei.cms.service.impl;

import java.text.SimpleDateFormat;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.caoxianfei.cms.dao.ArticleMapper;
import com.caoxianfei.cms.domain.Article;
import com.caoxianfei.cms.service.ArticleService;
import com.caoxianfei.utils.DateUtil;
import com.caoxianfei.utils.HLUtils;
import com.github.pagehelper.Page;
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
	
	@Resource
	private RedisTemplate<String, Article> redisTemplate;
	
	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;

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
		int update = articleMapper.update(article);
		
		//修改成功后，审核成功的
				if(update > 0 && article.getStatus() != null && article.getStatus() == 1) {
					
					//redis操作
					redisTemplate.delete("article_last");
					
					
					//根据id获取article对象
					Article a = articleMapper.getById(article.getId());
					
					//将数据存入到es中
					IndexQuery query = new IndexQuery();
					
					query.setObject(a);

					elasticsearchTemplate.index(query);
					
				}
				
				//热门
				if(update > 0 && article.getHot()!=null ) {
					//redis操作
					redisTemplate.delete("article_hot");
				}
		
		return update;
	}

	/**
	 * 使用es进行高亮查询
	 */
	public PageInfo<Article> selectFromES(Integer pageNum, Integer pageSize, String key) {
		
		//根据标题和内容进行高亮查询
		PageInfo<Article> info = HLUtils.findByHighLight(elasticsearchTemplate, Article.class, pageNum, pageSize, new String[] {"title"}, key);
		
		return info;
	}

	public PageInfo<Article> selectLast(Article article, Integer pageNum, Integer pageSize) {
		//第一次访问时，从mysql中获取数据，将数据缓存在redis
				//第二次及以后访问时，直接从redis中获取数据
				//新文章设置热点后，要修改redis中的数据为最新的数据
			ListOperations<String, Article> opsForList = redisTemplate.opsForList();
		
			//判断redis中有没有对应的键值对
			List<Article> list = opsForList.range("article_last", 0, -1);
		
			//如果是空值，从mysql中获取数据，将数据缓存在redis
			if(list == null || list.size() == 0) {
			//设置分页参数
			PageHelper.startPage(pageNum, pageSize);
			
			//从mysql中查询数据
			list = articleMapper.selects(article);
			
			//将数据存入到redis中
			opsForList.rightPushAll("article_last", list);
		}
		
		return new PageInfo<Article>(list);
	}

	public PageInfo<Article> selectHot(Article article, Integer pageNum, Integer pageSize) {
		
		ListOperations<String, Article> opsForList = redisTemplate.opsForList();
		
		
		//判断是否有值，则从mysql中获取所有数据，将数据缓存在redis
		if(!redisTemplate.hasKey("article_hot")) {
			//从mysql中获取所有数据
			List<Article> all_list = articleMapper.selects(article);
			
			//将数据存入到redis中
			opsForList.rightPushAll("article_hot", all_list);
		}
		
		//直接查询redis中的分页数据 
		List<Article> list = opsForList.range("article_hot", (pageNum - 1) * pageSize, pageNum * pageSize - 1);
		
		//设置人性化时间
		for (Article article2 : list) {
			article2.setDisplayDate(DateUtil.getDisplayTime(article2.getCreated()));
		}
		
		//获取总条数
		Long total = opsForList.size("article_hot");
		
		//设置分页对象
		Page<Article> page = new Page<Article>(pageNum, pageSize);
		
		//设置当前页数据
		page.addAll(list);
		
		//设置总条数
		page.setTotal(total);
		
		return new PageInfo<Article>(page);
	}



	
	
}
