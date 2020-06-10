/**
 * 
 */
package com.caoxianfei.cms.service;

import java.util.List;

import com.caoxianfei.cms.domain.Category;
import com.caoxianfei.cms.domain.Channel;




/**
 * 
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0
 * @date 
 *	  创建于:2020年5月31日上午8:21:19
 */
public interface ChannelService {

	List<Channel> Channels();

	List<Category> getCategoryList(Integer channelId);
	
}
