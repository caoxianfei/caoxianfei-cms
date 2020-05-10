/**
 * 
 */
package com.caoxianfei.cms.service;

import java.util.List;


import com.caoxianfei.cms.domain.Category;
import com.caoxianfei.cms.domain.Channel;

/**
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0
 * @date 
 *	  创建于:2020年4月29日下午7:21:58
 */
public interface ChannelService {

	List<Channel> getChannelList();

	List<Category> getCategoryList(Integer channelId);
	
}
