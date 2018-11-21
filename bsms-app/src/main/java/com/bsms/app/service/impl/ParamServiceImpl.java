package com.bsms.app.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsms.app.mapper.TblDicParamMapper;
import com.bsms.app.model.TblDicParam;
import com.bsms.app.model.TblDicParamExample;
import com.bsms.app.service.ParamService;

@Service
public class ParamServiceImpl implements ParamService {

	@Autowired
	private TblDicParamMapper tblDicParamMapper;

	@Override
	public List<TblDicParam> selectParamsByParentCode(TblDicParam tblDicParam) {

		TblDicParamExample example = new TblDicParamExample();
		com.bsms.app.model.TblDicParamExample.Criteria paramCriteria = example.createCriteria();

		if (StringUtils.isNotBlank(tblDicParam.getParentCode())) {
			paramCriteria.andParentCodeEqualTo(tblDicParam.getParentCode());
		}
		List<TblDicParam> list = tblDicParamMapper.selectByExample(example);

		return list;
	}

}
