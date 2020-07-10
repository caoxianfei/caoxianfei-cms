/**
 * 
 */
package com.caoxianfei.cms.test.testEs;

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

/**
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0
 * @date 
 *	  创建于:2020年7月10日上午9:21:31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath:spring-beans.xml")
public class test {
	
	@Resource
	private ElasticsearchTemplate es;

	@Resource
	private ArticleService articleService;
	
	@Test
	public void test01() {
		Article article = new Article();
		PageInfo<Article> selects = articleService.selects(article, 0, 10000);
		
		IndexQuery query = new IndexQuery();
		
		query.setObject(selects.getList());
		
		es.index(query);
		
		
	}
	
}
