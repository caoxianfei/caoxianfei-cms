/**
 * 
 */
package com.caoxianfei.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caoxianfei.cms.dao.CommentsMapper;
import com.caoxianfei.cms.domain.Comments;
import com.caoxianfei.cms.service.CommentsService;
import com.caoxianfei.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0
 * @date 
 *	  创建于:2020年6月8日下午9:25:32
 */
@Service
public class CommentsServiceImpl implements CommentsService{

	@Autowired
	private CommentsMapper mapper;
	
	
	public int insert(Comments comments) {
		// TODO Auto-generated method stub
		 mapper.insert(comments);
		 mapper.comments(comments.getArticleId());
		return 1;
	}


	public PageInfo<Comments> selectByIdList(Integer id) {
		// TODO Auto-generated method stub
		PageHelper.startPage(1, 5);
		List<Comments> list = mapper.selectByIdList(id);
		for (Comments comments : list) {
			comments.setDisplaytime(DateUtil.getDisplayTime(comments.getCreated()));
		}
		
		return new PageInfo<Comments>(list);
	}


	public int comments(Integer id) {
		// TODO Auto-generated method stub
		return mapper.comments(id);
	}



}
