package com.esteel.web.pojo.approve.auth;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.esteel.admin.service.request.user.AdminUserLoginRequest;
import org.springframework.beans.BeanUtils;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 登录请求信息
 *
 * @author liujie
 *
 * @since 2020-05-20 12:01:44
 */
@Getter
@Setter
@ToString
@ApiModel("登录请求信息")
public class LoginRequest implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = -1332498754182754402L;

	/**
	 * 登录账号
	 */
	@ApiModelProperty("登录账号")
	@NotBlank(message = "登录账号不能为空")
	private String account;
	
	/**
	 * 密码
	 */
	@ApiModelProperty("密码")
	@NotBlank(message = "密码不能为空")
	@Size(min = 6, max = 20, message = "密码长度为6~20位")
	private String password;
	
	public AdminUserLoginRequest transLoginRequest() {
		AdminUserLoginRequest userLoginRequest = new AdminUserLoginRequest();
		BeanUtils.copyProperties(this, userLoginRequest);
		userLoginRequest.setEmail(this.account.trim());
		
		return userLoginRequest;
	}
}
