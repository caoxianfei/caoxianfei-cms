package com.caoxianfei.cms.test.kafka;

import java.io.File;
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


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class ProducerTest {

	@Resource
	private KafkaTemplate<String, String> kafkaTemplate;

	@Resource
	private ChannelService channelService;

	@Test
	public void producerTest() throws Exception {
		// 获取文件夹
		File dirs = new File("C:\\Users\\caocao\\Desktop\\test");

		// 获取其下的子文件
		File[] files = dirs.listFiles();

		// 循环遍历
		for (File file : files) {

			Article article = new Article();

//		(1)使用工具包中流工具方法读取文件，不得乱码。（4分）
			// 读取文件
			String content = StreamUtil.readTextFile(file);
			

//		(2)将文件名作为Article对象的title属性值。（2分）
			String title = file.getName().replace(".txt", "");
			article.setTitle(title);

			
//		(3)文本内容作为Article对象的content属性值。（2分）
			article.setContent(content);

			
//		(4)在文本内容中截取前140个字作为摘要。（2分）
			String summary = content;

			if (content.length() > 140) {
				summary = content.substring(0, 140);
			}

			article.setSummary(summary);
			

//		(5)“点击量”和“是否热门”、“频道”字段要使用随机值。（2分）

			// 点击量
			article.setHits(RandomUtil.nextInt(1, 10000));

			// 是否热门
			article.setHot(RandomUtil.nextInt(1));

			// 频道=====================
			// 获取所有频道
			List<Channel> channels = channelService.Channels();

			// 随机下标
			int channel_index = RandomUtil.nextInt(channels.size() - 1);

			// 根据下标获取对象
			Channel channel = channels.get(channel_index);

			article.setCategoryId(channel.getId());

			// 栏目=====================
			// 获取所有栏目
			List<Category> categories = channelService.getCategoryList(channel.getId());

			int cate_index = RandomUtil.nextInt(categories.size() - 1);

			Category category = categories.get(cate_index);

			article.setCategoryId(category.getId());

			
//		(6)文章发布日期从2019年1月1日模拟到今天。（2分）   -2
			Date parse = new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-01");
			Date parse1 = new SimpleDateFormat("yyyy-MM-dd").parse("2019-07-03");
			Date randomDate = DateUtil.randomDate(parse, parse1);

			// 创建日期
			article.setCreated(randomDate);

			// 修改日期
			article.setUpdated(randomDate);

			
//		(7)其它的字段随便模拟。

			// 设置状态为已审核
			article.setStatus(1);

			// 文章类型为：文章
			article.setContentType(0);

			// 设置用户
			article.setUserId(23);

			// 设置是否删除
			article.setDeleted(0);

			
//		(8)编写Kafka生产者，然后将生成Article对象通过Kafka发送到消费端。（4分）

			kafkaTemplate.sendDefault("article_add", JSON.toJSONString(article));

		}

	}

}
