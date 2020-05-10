package com.caoxianfei.cms.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caoxianfei.cms.dao.SlideMapper;
import com.caoxianfei.cms.domain.Slide;
import com.caoxianfei.cms.service.SlideService;




@Service
public class SlideServiceImpl implements SlideService {
	
	@Autowired
	private SlideMapper slideMapper;

	public List<Slide> getList() {
		return slideMapper.getList();
	}

}
