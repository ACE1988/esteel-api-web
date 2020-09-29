package com.esteel.web.rest.api.esteel.pub.v1_0_0.user;

import static com.esteel.rest.common.RestResponse.ok;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.esteel.web.pojo.approve.auth.AuthInfo;
import com.esteel.web.pojo.approve.auth.LoginRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esteel.rest.common.RestResponse;
import com.esteel.web.sevice.user.LoginService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 运营后台登录管理
 *
 * @author liujie
 *
 * @since 2020-01-16 10:17:30
 */
@Api(value = "esteel-api-web", produces = "application/json")
@Validated
@RestController
@RequestMapping(value = "/common", produces = "application/json")
public class SignInController {
	/**
	 * 登录服务
	 */
	@Resource
	private LoginService loginService;

	@ApiOperation(value = "登陆", notes = "登陆")
	@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Successful — 请求已完成"),
		@ApiResponse(code = 500, message = "服务器不能完成请求") 
	})
	@PostMapping(value = "/signIn")
	public RestResponse<AuthInfo> login(@Valid @RequestBody LoginRequest loginRequest) {
		// 登录
		AuthInfo authInfo = loginService.login(loginRequest);
		
		return ok(authInfo);
	}
}
