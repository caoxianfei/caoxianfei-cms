/**
 * 
 */
package com.caoxianfei.cms.test.testkafka;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.caoxianfei.cms.domain.Article;
import com.caoxianfei.cms.domain.Category;
import com.caoxianfei.cms.domain.Channel;
import com.caoxianfei.cms.service.ChannelService;
import com.caoxianfei.utils.DateUtil;
import com.caoxianfei.utils.RandomUtil;
import com.caoxianfei.utils.StreamUtil;

/**
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0
 * @date 
 *	  创建于:2020年7月10日上午8:38:57
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class test {

	@Resource
	private ChannelService channelService;
	
	@Resource
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Test
	public void test01() throws Exception {
		
		File file = new File("C:\\Users\\caocao\\Desktop\\test");
		//获取文件目录下的所有子文件
		File[] listFiles = file.listFiles();
		
		for (File file2 : listFiles) {
			//创建对象
			Article article = new Article();
			//将 文件名称作为  文章的标题
			String replace = file2.getName().replace(".txt", "");
			//将替换的字符串 写入  article 文件中
			article.setTitle(replace);
			//使用工具类 读出 文章内所有的文本内容
			String readTextFile = StreamUtil.readTextFile(file2);
			//将文本内容赋值到  article对象中
			article.setContent(readTextFile);
			//判断文章内容是否大于50字
			if(readTextFile.length()>50) {
				//切割字符串 得到前50个字
				String substring = readTextFile.substring(0, 50);
				//   赋值到  摘要字段上  
				article.setSummary(substring);
			}
			//生成一个 随机的点击量
			int nextInt = RandomUtil.nextInt(0, 10000);
			// set到  点击量字段上
			article.setHits(nextInt);
			//生成一个随机的 是否热门数值
			int nextInt2 = RandomUtil.nextInt(1);
			//set到  热门字段上
			article.setHot(nextInt2);
			//随机生成 频道id
			List<Channel> channels = channelService.Channels();
			//得到栏目的 下标
			int nextInt3 = RandomUtil.nextInt(1,channels.size());
			article.setChannelId(nextInt3);
			//得到一个  分类的id
			List<Category> categoryList = channelService.getCategoryList(article.getChannelId());
			//  得到一个 频道的下标
			int nextInt4 = RandomUtil.nextInt(1,categoryList.size() );
			
			// set到   对应的频道上
			article.setCategoryId(categoryList.get(nextInt4 - 1).getId());
			//模拟时间
			Date parse = new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01");
			Date parse1 = new SimpleDateFormat("yyyy-MM-dd").parse("2020-07-10");
			Date randomDate = DateUtil.randomDate(parse, parse1);
			
			//将时间  set到 对应的  创建时间和 修改时间上
			article.setCreated(randomDate);
			article.setUpdated(randomDate);
			
			article.setContentType(0);
			
			article.setUserId(19);
			
			article.setStatus(1);
			
			article.setDeleted(0);
			
			kafkaTemplate.sendDefault("article_add", JSON.toJSONString(article));
			
			
		}
		
		
	}
	
		
	
}
