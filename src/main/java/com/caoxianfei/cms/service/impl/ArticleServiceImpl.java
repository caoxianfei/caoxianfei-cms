/**
 * 
 */
package com.caoxianfei.cms.service.impl;

import java.text.SimpleDateFormat;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
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
	
	
	 @Autowired
	 private ThreadPoolTaskExecutor executor;
	 

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

	public void sethits(String key, final Article article) {
		
//		为CMS系统文章最终页（详情页），每访问一次就同时往文章表的浏览量字段加1，如果一篇文章集中一时刻上百万次浏览，就会给数据库造成压力。现在请你利用Redis提高性能，
//		当用户浏览文章时，将“Hits_${文章ID}_${用户IP地址}”为key，查询Redis里有没有该key，如果有key，则不做任何操作。如果没有，则使用Spring线程池异步执行数据库加1操作，
//		并往Redis保存key为Hits_${文章ID}_${用户IP地址}，value为空值的记录，而且有效时长为5分钟。
		
//		(2)Redis判断（4分）。Redis写入（4分），设定有效时长（4分）
		if(!redisTemplate.hasKey(key)) {
		
			//获取操作对象
			ValueOperations<String, Article> opsForValue = redisTemplate.opsForValue();
		
			//存入值	有效时长为5分钟
			opsForValue.set(key, null,Duration.ofMinutes(5));
		
//		(3)Spring线程池使用。（8分）
			executor.execute(new Runnable() {
				
				public void run() {
					
//					(4)异步执行数据库加1操作。（4分）
					
					Integer hits = article.getHits();
					
					//处理空值
					if(hits == null) {
						hits = 0;
					}
					
					//设置点击量
					article.setHits(hits + 1);
					
					//将数据修改到数据库
					articleMapper.update(article);
				}
			});
		
		
		}
		
		
		
		
//		//获取一个  redis String 类型的对象
//		ValueOperations<String, Article> opsForValue = redisTemplate.opsForValue();
//		//通过我们的键值 来获取一个  article 对象
//		 Article article2 = opsForValue.get(key);
//		//如果  对象为空的话  说明 我们还未点击过该对象
//		if(article2 == null ) {
//			//设置 键值过期为  5 分钟
//			opsForValue.set(key, null, Duration.ofMinutes(5));
//			//  进行 浏览量  加  1  的操作
//			article.setHits(article.getHits() + 1);
//		}
//			
//		
////			executor.execute(new Runnable() {
////				 
////				public void run() {
////					// TODO Auto-generated method stub
////					if(article2.getHits() <  1 ) {
////						article2.setHits(0);
////					}
////					
////					article2.setHits(article2.getHits() + 1);
////					
////					
////				}
////			});
//			//进行点击量修改后的一个 增加操作
//			articleMapper.update(article);
			
			
			
			
		}
		
		
	}



	
	

