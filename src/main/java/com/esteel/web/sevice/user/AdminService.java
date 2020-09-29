package com.esteel.web.sevice.user;

import com.esteel.user.service.AdminUserDubboService;
import com.esteel.user.service.request.admin.AdminUserRequest;
import com.esteel.user.service.response.admin.AdminUserResponse;
import com.esteel.web.pojo.util.PageResponseTransfer;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import com.esteel.common.dubbo.DubboResponse;
import com.esteel.common.dubbo.PageResponse;
import com.esteel.web.pojo.approve.authority.AccountInfo;
import com.esteel.web.pojo.approve.authority.AccountPageQueryRequest;

import static com.esteel.common.dubbo.DubboResponse.data;

/**
 * 运营用户权限管理服务
 *
 * @author liujie
 *
 * @since 2020-05-20 14:29:05
 */
@Service
public class AdminService {
	/**
	 * 运营用户服务
	 */
	@Reference(version = AdminUserDubboService.DUBBO_VERSION, interfaceClass = AdminUserDubboService.class)
	private AdminUserDubboService adminUserDubboService;

	
	/**
	 * 分页查询运营系统用户账号
	 * 
	 * @param pageRequest 查询参数
	 * @return 系统账号列表
	 */
	public PageResponse<AccountInfo> queryAccountsPage(AccountPageQueryRequest pageRequest) {
		// 转换请求对象
		AdminUserRequest queryRequest = pageRequest.transAdminUserRequest();
		
		// DUBBO调用：分页查询用户账号
		DubboResponse<PageResponse<AdminUserResponse>> response = adminUserDubboService.queryAdminUser(queryRequest);
		
		// 转换对象返回
		return (new PageResponseTransfer<AccountInfo>() {}).transfer(data(response));
	}

}
