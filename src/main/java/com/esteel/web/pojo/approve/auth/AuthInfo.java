package com.esteel.web.pojo.approve.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 登录认证信息
 *
 * @author liujie
 *
 * @since 2020-05-20 22:38:23
 */
@Data
@ApiModel("登录认证信息")
public class AuthInfo implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = -6535178660678151389L;
	
	/**
	 * Token
	 */
	@ApiModelProperty("token")
	private String token;
	
	/**
	 * 用户ID
	 */
	@ApiModelProperty("用户ID")
	private Long userId;
	
	/**
	 * 用户名
	 */
	@ApiModelProperty("用户名")
	private String userName;
	
	/**
	 * 邮箱
	 */
	@ApiModelProperty("邮箱")
	private String email;
	
//	/**
//	 * 角色列表
//	 */
//	@ApiModelProperty("角色列表")
//	private List<RoleInfo> roles;
//
//	/**
//	 * 权限列表
//	 */
//	@ApiModelProperty("权限列表")
//	private List<PermissionInfo> permissions;
}
