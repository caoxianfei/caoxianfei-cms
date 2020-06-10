/**
 * 
 */
package com.caoxianfei.cms.dao;

import java.util.List;import org.apache.poi.ss.usermodel.Comment;

import com.caoxianfei.cms.domain.Article;
import com.caoxianfei.cms.domain.Comments;

/**
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0
 * @date 
 *	  创建于:2020年6月8日下午8:22:57
 */
public interface CommentsMapper {

	int insert(Comments comments);
	
	List<Comments> selectByIdList(Integer id);
	
	int comments(Integer id);
	
}
