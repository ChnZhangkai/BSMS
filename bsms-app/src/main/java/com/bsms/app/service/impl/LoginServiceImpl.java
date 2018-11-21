package com.bsms.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsms.app.mapper.TblEeqUserMapper;
import com.bsms.app.model.TblEeqUser;
import com.bsms.app.model.TblEeqUserExample;
import com.bsms.app.model.TblEeqUserExample.Criteria;
import com.bsms.app.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private TblEeqUserMapper tblEeqUserMapper;
	
	@Override
	public TblEeqUser login(TblEeqUser user) {
		TblEeqUserExample example = new TblEeqUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(user.getUserId()).andUserPwdEqualTo(user.getUserPwd());
		
		List<TblEeqUser> list = tblEeqUserMapper.selectByExample(example);
		
		return list.get(0);
	}

}
