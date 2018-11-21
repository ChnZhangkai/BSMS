package com.bsms.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bsms.app.annotation.CiticLog;
import com.bsms.app.exception.BusinessException;
import com.bsms.app.model.TblMarketingActivity;
import com.bsms.app.service.MarketActivityService;
import com.bsms.app.vo.HTTPResut;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CiticLog
@RestController
@Api(tags = "后台管理系统代码")
@RequestMapping(value = "/backstage")
public class MarketActivityController {
	
	@Autowired
	private MarketActivityService bMSystemService;
	
	@ApiOperation(value = "营销活动管理查询")
	@RequestMapping(value = "/marketing")
	public HTTPResut<PageInfo<TblMarketingActivity>> selectMarketingActivity(@RequestBody TblMarketingActivity activity)throws BusinessException{
		
		PageInfo<TblMarketingActivity> info = bMSystemService.selecTblMarketingActivities(activity);
		
		return HTTPResut.success(info);
	}
	
	@ApiOperation(value = "通过Id删除")
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public HTTPResut<String> deleteMarketingActicitiesById(@RequestParam Long id){
		
		bMSystemService.deleteMarketingActicitiesById(id);
		
		return HTTPResut.success("success");
	}
	
}
