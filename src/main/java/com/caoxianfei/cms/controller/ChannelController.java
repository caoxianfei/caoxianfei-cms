/**
 * 
 */
package com.caoxianfei.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caoxianfei.cms.domain.Category;
import com.caoxianfei.cms.domain.Channel;
import com.caoxianfei.cms.service.ChannelService;

/**
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0
 * @date 
 *	  创建于:2020年4月29日下午7:16:29
 */
@RequestMapping("/channel")
@Controller
public class ChannelController {

	
	@Autowired
	private ChannelService  service;
	
	/**
	 * 
	 *   说明：获取菜单列表
	 * @author  caoxianfei
	 * @version v1.0
	 *  创建于：2020年4月29日
	 */
	@PostMapping("getChannelList.do")
	@ResponseBody
	public List<Channel> getChannelList(){
		return service.getChannelList();
	}
	
	@RequestMapping("getCategoryList.do")
	@ResponseBody
	public List<Category> getCategoryList(Integer channelId){
		return service.getCategoryList(channelId);
	}
	
	
}
