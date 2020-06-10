/**
 * 
 */
package com.caoxianfei.cms.service;

import java.util.List;

import com.caoxianfei.cms.domain.Comments;
import com.github.pagehelper.PageInfo;

/**
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0
 * @date 创建于:2020年6月8日下午9:25:11
 */
public interface CommentsService {

	int insert(Comments comments);

	PageInfo<Comments> selectByIdList(Integer id);
	
	int comments(Integer id);
}
