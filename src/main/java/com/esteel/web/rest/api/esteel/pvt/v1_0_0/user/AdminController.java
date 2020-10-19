package com.esteel.web.rest.api.esteel.pvt.v1_0_0.user;

import static com.esteel.common.interaction.Interactive.execute;
import static com.esteel.rest.common.Responses.from;
import static com.esteel.rest.common.RestResponse.ok;

import com.esteel.common.core.Page;
import com.esteel.rest.security.User;
import com.esteel.admin.service.AdminUserDubboService;
import com.esteel.admin.service.request.user.AdminUserRequest;
import com.esteel.web.pojo.approve.authority.AccountInfo;
import com.esteel.web.sevice.user.AdminService;
import io.swagger.annotations.*;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.esteel.common.dubbo.PageResponse;
import com.esteel.rest.common.RestResponse;
import com.esteel.web.pojo.approve.authority.AccountPageQueryRequest;

import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;
import java.security.Principal;

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

	@Reference(version = AdminUserDubboService.DUBBO_VERSION, interfaceClass = AdminUserDubboService.class)
	private AdminUserDubboService adminUserDubboService;


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
	public RestResponse<PageResponse<AccountInfo>> queryAccountsPage(AccountPageQueryRequest pageRequest,
																	 @AuthenticationPrincipal @ApiIgnore User user) {
		
		PageResponse<AccountInfo> accountsPage = adminService.queryAccountsPage(pageRequest);
		
		return ok(accountsPage);
	}

	@ApiOperation(value="查询邀请客户列表")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(
					name = "Authorization",
					value = "用户Token",
					dataType = "string",
					paramType = "header",
					example = "Bearer 0b79bab50daca910b000d4f1a2b675d604257e42",
					required = true)
	})
	@GetMapping("/queryAccountsPage/V2")
	public Page<AccountInfo> queryAccountsPageV2(
			@AuthenticationPrincipal @ApiIgnore User user,
			final Principal principal,
			@ApiParam(value = "账号名") @RequestParam(value = "userName",required = false) String userName,
			@ApiParam(value = "邮箱") @RequestParam(value = "email",required = false) String email,
			@ApiParam(value = "页码", required = true) @NotNull(message = "页码未填写") @RequestParam(value = "page_no", required = false) Integer pageNo,
			@ApiParam(value = "页大小", required = true) @NotNull(message = "页大小未填写") @RequestParam(value = "page_size", required = false) Integer pageSize) {

		String name = principal.getName();
		return execute(() -> {
			AdminUserRequest request = new AdminUserRequest();
			request.setEmail(email);
			request.setUserName(userName);
			request.setCurrentPage(pageNo);
			request.setPageSize(pageSize);
			return adminUserDubboService.queryAdminUserV2(request);
		}, page -> from(page, item -> {
			AccountInfo accountInfo = new AccountInfo();
			accountInfo.setEmail(item.getEmail());
			accountInfo.setAccountId(item.getId());
			accountInfo.setAssignFlag(item.getAutoStatus());
			accountInfo.setAssignNums(item.getNum());
			accountInfo.setStatus(item.getStatus());
			accountInfo.setUserName(item.getUserName());
			return accountInfo;
		}, pageNo, pageSize));
	}
}
