/**
 * 
 */
package com.caoxianfei.cms.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caoxianfei.cms.dao.ChannelMapper;
import com.caoxianfei.cms.domain.Category;
import com.caoxianfei.cms.domain.Channel;
import com.caoxianfei.cms.service.ChannelService;

/**
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0
 * @date 
 *	  创建于:2020年4月29日下午7:23:24
 */
@Service
public class ChannelServiceImpl implements ChannelService{

	 @Autowired
	 private ChannelMapper mapper;

	public List<Channel> getChannelList() {
		return mapper.getChannelList();
	}

	public List<Category> getCategoryList(Integer channelId) {
		return mapper.getCategoryList(channelId);
	}

}
