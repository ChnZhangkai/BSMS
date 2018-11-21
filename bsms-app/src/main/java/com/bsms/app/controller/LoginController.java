package com.bsms.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bsms.app.annotation.CiticLog;
import com.bsms.app.exception.BusinessException;
import com.bsms.app.model.TblEeqUser;
import com.bsms.app.service.LoginService;
import com.bsms.app.vo.HTTPResut;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CiticLog
@RestController
@Api(tags = "营销活动管理")
@RequestMapping(value = "/backstage")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@ApiOperation(value = "登录")
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public HTTPResut<TblEeqUser> login(TblEeqUser user) throws BusinessException {
		
		TblEeqUser tblEeqUser = loginService.login(user);
		
		return HTTPResut.success(tblEeqUser);
	}
	
}
