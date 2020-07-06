package com.caoxianfei.cms.test.es;

import java.util.List;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.caoxianfei.cms.domain.Article;
import com.caoxianfei.cms.service.ArticleService;
import com.github.pagehelper.PageInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class ESTest {
	
	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@Resource
	private ArticleService articleService;
	
	@Test
	public void testES() {
		//将数据从mysql中查询出来
		Article article = new Article();
		
		//设置状态为已审核
		article.setStatus(1);
		
		PageInfo<Article> pageInfo = articleService.selects(article, 1, 10000);
		
		List<Article> list = pageInfo.getList();
		
		//将数据存入到es中
		IndexQuery query = new IndexQuery();
		
		//循环遍历
		for (Article a : list) {
			
			//设置数据
			query.setObject(a);
			
			elasticsearchTemplate.index(query );
		}
		
		System.out.println("存储完毕");
	}
	
}
