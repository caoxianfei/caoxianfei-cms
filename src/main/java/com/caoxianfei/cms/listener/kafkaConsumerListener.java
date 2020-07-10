/**
 * 
 */
package com.caoxianfei.cms.listener;

import javax.annotation.Resource;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.caoxianfei.cms.domain.Article;
import com.caoxianfei.cms.service.ArticleService;

/**
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0
 * @date 
 *	  创建于:2020年7月10日上午9:17:15
 */
@Component
public class kafkaConsumerListener implements MessageListener<String, String>{
	
	@Resource
	private ArticleService atricleService;
	
	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;

	public void onMessage(ConsumerRecord<String, String> data) {
		// TODO Auto-generated method stub
		String key = data.key();
		String value = data.value();
		if(key !=null && "article_add".equals(key)) {
			
			Article article = JSON.parseObject(value, Article.class);
			
			 atricleService.insert(article);
			 
			 IndexQuery query = new IndexQuery();
			 
			 query.setObject(article);
			 	
			 elasticsearchTemplate.index(query);
			 
		}
		
	}

	
	
}
