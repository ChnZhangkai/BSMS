package com.bsms.app.service;

import com.bsms.app.model.TblMarketingActivity;
import com.github.pagehelper.PageInfo;

public interface MarketActivityService {
	
	// 营销活动管理查询
	PageInfo<TblMarketingActivity> selecTblMarketingActivities(TblMarketingActivity activity);
	
	// 删除营销活动
	void deleteMarketingActicitiesById(Long id);
	
	// 营销活动新增
	void addMarketingActivity(TblMarketingActivity activity);
	
	void udateMarketingActivity(TblMarketingActivity activity);
	
}
