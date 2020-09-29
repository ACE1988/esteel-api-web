package com.esteel.web.rest.api.esteel.pvt.v1_0_0.user;

import static com.esteel.rest.common.RestResponse.ok;

import com.esteel.web.pojo.approve.authority.AccountInfo;
import com.esteel.web.sevice.user.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esteel.common.dubbo.PageResponse;
import com.esteel.rest.common.RestResponse;
import com.esteel.web.pojo.approve.authority.AccountPageQueryRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 运营系统-用户账号管理API
 *
 * @author liujie
 *
 * @since 2020-01-17 17:52:41
 */
@Api(value = "esteel-api-web", produces = "application/json")
@Validated
@RestController
@RequestMapping(value = "/authority", produces = "application/json")
public class AdminController {
	/**
	 * 权限管理服务
	 */
	@Autowired
	private AdminService adminService;

	@ApiOperation(value = "分页查询账号列表")
	@ApiImplicitParams(value = {
		@ApiImplicitParam(name = "authorization", 
						  value = "用户Token", 
						  dataType = "string", 
						  paramType = "header", 
						  example = "Bearer 0b79bab50daca910b000d4f1a2b675d604257e42",
						  required = true)
	})
	@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Successful — 请求已完成"),
		@ApiResponse(code = 500, message = "服务器不能完成请求") 
	})
	@GetMapping("/queryAccountsPage")
	public RestResponse<PageResponse<AccountInfo>> queryAccountsPage(AccountPageQueryRequest pageRequest) {
		
		PageResponse<AccountInfo> accountsPage = adminService.queryAccountsPage(pageRequest);
		
		return ok(accountsPage);
	}
}
