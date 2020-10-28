package com.esteel.web.sevice.user;

import static com.esteel.common.dubbo.DubboResponse.data;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.esteel.admin.service.UserRoleDubboService;
import com.esteel.admin.service.request.role.AdminUserRoleRequest;
import com.esteel.admin.service.response.role.AdminUserRoleResponse;
import com.esteel.user.service.UserDubboService;
import com.esteel.user.service.request.admin.LoginUserRequest;
import com.esteel.user.service.response.admin.UserResponse;
import com.esteel.web.pojo.approve.auth.AuthInfo;
import com.esteel.common.dubbo.DubboResponse;
import com.esteel.admin.service.AdminUserDubboService;
import com.esteel.admin.service.request.user.AdminUserLoginRequest;
import com.esteel.admin.service.response.user.AdminLoginResponse;
import com.esteel.web.pojo.approve.auth.LoginRequest;
import com.esteel.web.pojo.approve.auth.RoleInfo;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 * 登录服务
 *
 * @author liujie
 *
 * @since 2020-01-17 17:29:40
 */
@SuppressWarnings("deprecation")
@Slf4j
@Service
public class LoginService {
	/**
	 * 系统用户信息服务
	 */
	@Reference(version = AdminUserDubboService.DUBBO_VERSION, interfaceClass = AdminUserDubboService.class)
	private AdminUserDubboService adminUserDubboService;

	@Reference(version =UserDubboService.DUBBO_VERSION,interfaceClass = UserDubboService.class)
	private UserDubboService userDubboService ;

	@Reference(version =UserRoleDubboService.DUBBO_VERSION,interfaceClass = UserRoleDubboService.class)
	private UserRoleDubboService userRoleDubboService ;
	
	@Resource
	AuthorizationServerTokenServices authorizationServerTokenServices;
	
	/**
	 * 系统账号登录
	 * 
	 * @param loginRequest 登录请求
	 * @return
	 */
	public AuthInfo login(LoginRequest loginRequest) {
		/*
		 * 验证登录账号密码
		 */
		AdminUserLoginRequest userLoginRequest = loginRequest.transLoginRequest();
		DubboResponse<AdminLoginResponse> response = adminUserDubboService.adminUserLogin(userLoginRequest);
		
		// 获取登录用户信息，登录失败时抛出异常
		AdminLoginResponse loginResponse = data(response);
		/*
		 * 获取用户角色&权限列表
		 */
//		AdminUserRoleRequest userRoleQueryRequest = new AdminUserRoleRequest();
//		userRoleQueryRequest.setUserId(loginResponse.getUserId());
//		DubboResponse<AdminUserRoleResponse> userRoleResponse = adminUserDubboService.queryAdminUserRole(userRoleQueryRequest);
//		AdminUserRoleResponse userRoles = data(userRoleResponse);
		
		AuthInfo authInfo = new AuthInfo();
		authInfo.setUserId(loginResponse.getUserId());
		authInfo.setUserName(loginResponse.getUserName());
		authInfo.setEmail(loginRequest.getAccount());
//		authInfo.setRoles(userRoles.getRoleList().stream().map(RoleInfo::new).collect(Collectors.toList()));
		/*
		 * 创建token
		 */
		log.info("authInfo：{}", authInfo);
		OAuth2Authentication auth2Authentication = convertAuthentication("operation", authInfo);
		OAuth2AccessToken accessToken = authorizationServerTokenServices.createAccessToken(auth2Authentication);
		// 返回认证信息
		String token = "bearer ".concat(accessToken.getValue());
		authInfo.setToken(token);
		
//		List<AdminPermissionPoJo> permissionList = new ArrayList<>();
//		if (userRoles.getPermissions() != null) {
//			userRoles.getPermissions().forEach((k,v) -> {
//				permissionList.addAll(v);
//			});
//			List<PermissionInfo> permissionInfos = permissionList.stream().map(PermissionInfo::new).collect(Collectors.toList());
//			// permission去重
//			authInfo.setPermissions(permissionInfos.stream().distinct().collect(Collectors.toList()));
//		}
		
		return authInfo;
	}

	/**
	 * 系统账号登录
	 *
	 * @param loginRequest 登录请求
	 * @return
	 */
	public AuthInfo userLogin(LoginRequest loginRequest) {
		/*
		 * 验证登录账号密码
		 */
		LoginUserRequest userLoginRequest = new LoginUserRequest();
		userLoginRequest.setMobile(loginRequest.getAccount());
		userLoginRequest.setPassword(loginRequest.getPassword());
		DubboResponse<UserResponse> response = userDubboService.userLogin(userLoginRequest);

		// 获取登录用户信息，登录失败时抛出异常
		UserResponse loginResponse = data(response);
		/*
		 * 获取用户角色&权限列表
		 */
		AdminUserRoleRequest adminUserRoleRequest = new AdminUserRoleRequest();
		adminUserRoleRequest.setUserId(loginResponse.getUserId());
		DubboResponse<List<AdminUserRoleResponse>> userRoleResponse = userRoleDubboService.queryAdminUserRoleByUserId(adminUserRoleRequest);
		List<AdminUserRoleResponse> userRoles = data(userRoleResponse);

		AuthInfo authInfo = new AuthInfo();
		authInfo.setUserId(loginResponse.getUserId());
		authInfo.setUserName(loginResponse.getUserName());
		authInfo.setEmail(loginRequest.getAccount());
		//角色信息
		authInfo.setRoles(userRoles.stream().map(item -> {
			RoleInfo roleInfo = new RoleInfo();
			roleInfo.setRoleId(item.getRoleId());
			roleInfo.setRoleName(item.getRoleName());
			return  roleInfo ;
		}).collect(Collectors.toList()));
		/*
		 * 创建token
		 */
		log.info("authInfo：{}", authInfo);
		OAuth2Authentication auth2Authentication = convertAuthentication("operation", authInfo);
		OAuth2AccessToken accessToken = authorizationServerTokenServices.createAccessToken(auth2Authentication);
		// 返回认证信息
		String token = "bearer ".concat(accessToken.getValue());
		authInfo.setToken(token);

		//角色菜单
//		List<AdminPermissionPoJo> permissionList = new ArrayList<>();
//		if (userRoles.getPermissions() != null) {
//			userRoles.getPermissions().forEach((k,v) -> {
//				permissionList.addAll(v);
//			});
//			List<PermissionInfo> permissionInfos = permissionList.stream().map(PermissionInfo::new).collect(Collectors.toList());
//			// permission去重
//			authInfo.setPermissions(permissionInfos.stream().distinct().collect(Collectors.toList()));
//		}

		return authInfo;
	}
	
	private OAuth2Authentication convertAuthentication(String client, AuthInfo authInfo) {
		AuthInfo principal = new AuthInfo();
		principal.setUserId(authInfo.getUserId());
		principal.setUserName(authInfo.getUserName());
		OAuth2Request request = new OAuth2Request(null, client, null, true, null, null, null, null, null);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(JSON.toJSONString(principal), "N/A");
		//getUserPermissions(authInfo.getRoles().stream().map(RoleInfo::getRoleName).collect(Collectors.toList()))
		token.setDetails(authInfo);
		return new OAuth2Authentication(request, token);
	}

	@SuppressWarnings("unused")
	private Collection<? extends GrantedAuthority> getUserPermissions(List<String> permisssions) {
		return permisssions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}
}
