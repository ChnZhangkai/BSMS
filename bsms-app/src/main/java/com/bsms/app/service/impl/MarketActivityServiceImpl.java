package com.bsms.app.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsms.app.constants.Constants;
import com.bsms.app.mapper.TblMarketingActivityMapper;
import com.bsms.app.model.TblDicParam;
import com.bsms.app.model.TblMarketingActivity;
import com.bsms.app.model.TblMarketingActivityExample;
import com.bsms.app.service.MarketActivityService;
import com.bsms.app.service.ParamService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class MarketActivityServiceImpl implements MarketActivityService{
	
	@Autowired
	private ParamService paramService;
	
	@Autowired
	private TblMarketingActivityMapper tblMarketingActivityMapper;

	@Override
	public PageInfo<TblMarketingActivity> selecTblMarketingActivities(TblMarketingActivity activity) {
		
		TblMarketingActivityExample example = new TblMarketingActivityExample();
		com.bsms.app.model.TblMarketingActivityExample.Criteria activityCriteria = example.createCriteria();
		
		if (StringUtils.isNotBlank(activity.getActivityName())) {
			activityCriteria.andActivityNameEqualTo(activity.getActivityName());
		}
		
		if (StringUtils.isNotBlank(activity.getStatus())) {
			activityCriteria.andStatusEqualTo(activity.getStatus());
		}
		
		if (StringUtils.isNotBlank(activity.getStartTime())) {
			activityCriteria.andStartTimeGreaterThanOrEqualTo(activity.getStartTime());
		}
		
		if (StringUtils.isNotBlank(activity.getEndTime())) {
			activityCriteria.andEndTimeLessThanOrEqualTo(activity.getEndTime());
		}
		
		PageHelper.startPage(activity.getPageNum(), activity.getPageSize());
		List<TblMarketingActivity> list = tblMarketingActivityMapper.selectByExample(example);
		
		PageInfo<TblMarketingActivity> pageInfo = new PageInfo<>(list);
		
		TblDicParam parentParam = new TblDicParam();
		parentParam.setParentCode(Constants.MARKETINGSTATUS);
		List<TblDicParam> statusParams = paramService.selectParamsByParentCode(parentParam);
		
		parentParam.setParentCode(Constants.POINTTYPE);
		List<TblDicParam> pointParams = paramService.selectParamsByParentCode(parentParam);
		
		parentParam.setParentCode(Constants.CARDTYPE);
		List<TblDicParam> cardParams = paramService.selectParamsByParentCode(parentParam);
		
		List<TblMarketingActivity> activityList = pageInfo.getList();
		for (TblMarketingActivity a : activityList) {
			for (TblDicParam p : statusParams) {
				if (a.getStatus().equals(p.getParamCode())) {
					a.setStatus(p.getParamName());
				}
			}
			for (TblDicParam p : pointParams) {
				if (a.getJfType().equals(p.getParamCode())) {
					a.setJfType(p.getParamName());
				}
			}
			for (TblDicParam p : cardParams) {
				if (a.getCardType().equals(p.getParamCode())) {
					a.setCardType(p.getParamName());
				}
			}
		}
		
		return pageInfo;
	}

	@Override
	public void deleteMarketingActicitiesById(Long id) {
		
		TblMarketingActivityExample example = new TblMarketingActivityExample();
		com.bsms.app.model.TblMarketingActivityExample.Criteria activityCriteria = example.createCriteria();
		activityCriteria.andIdEqualTo(id);
		
		tblMarketingActivityMapper.deleteByExample(example);
		
	}

}
