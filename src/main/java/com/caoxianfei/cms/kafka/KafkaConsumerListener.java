/**
 * 
 */
package com.caoxianfei.cms.kafka;

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
 *	  创建于:2020年7月3日下午9:17:03
 */
@Component
public class KafkaConsumerListener implements MessageListener<String, String>{
	
	@Resource
	private ArticleService articleService;

	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;
	
	public void onMessage(ConsumerRecord<String, String> data) {
		
		String key = data.key();
		String value = data.value();
		
		//添加数据
		if(key !=null && "article_add".equals(key)) {
			
			//将json转换成对象
			Article article = JSON.parseObject(value,Article.class);
			
			//存入mysql中
			articleService.insert(article);
			
			//将数据存入到es中
			//创建查询对象
			IndexQuery query = new IndexQuery();
			
			//设置数据
			query.setObject(article);

			//添加数据
			elasticsearchTemplate.index(query);
			
			System.out.println(article);
		}
		
	}

}
