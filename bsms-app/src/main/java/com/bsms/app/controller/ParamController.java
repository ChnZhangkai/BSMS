package com.bsms.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bsms.app.annotation.CiticLog;
import com.bsms.app.model.TblDicParam;
import com.bsms.app.service.ParamService;
import com.bsms.app.vo.HTTPResut;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CiticLog
@RestController
@Api(tags = "参数数据管理")
@RequestMapping(value = "/backstage")
public class ParamController {
	
	@Autowired
	private ParamService paramService;
	
	@ApiOperation(value = "根据父编号查询子参数列表")
	@RequestMapping(value = "/param", method = RequestMethod.GET)
	public HTTPResut<List<TblDicParam>> selectParamByParentCode(@RequestBody TblDicParam param){
		
		List<TblDicParam> list = paramService.selectParamsByParentCode(param);
		
		return HTTPResut.success(list);
	}
	
}
