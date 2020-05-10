/**
 * 
 */
package com.caoxianfei.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.caoxianfei.cms.domain.Category;
import com.caoxianfei.cms.domain.Channel;

/**
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0
 * @date 
 *	  创建于:2020年4月29日下午7:18:07
 */
public interface ChannelMapper {


	List<Channel> getChannelList();

	List<Category> getCategoryList(@Param("channelId")Integer channelId);

	
}
