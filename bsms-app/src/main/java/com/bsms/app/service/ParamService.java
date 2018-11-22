package com.bsms.app.service;

import java.util.List;

import com.bsms.app.model.TblDicParam;

public interface ParamService {

	// 根据父参数编码查询子编码列表
	List<TblDicParam> selectParamsByParentCode(TblDicParam tblDicParam);
	
}
