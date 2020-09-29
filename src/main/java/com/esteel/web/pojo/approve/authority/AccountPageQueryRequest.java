package com.esteel.web.pojo.approve.authority;

import java.io.Serializable;

import com.esteel.user.service.request.admin.AdminUserRequest;
import org.springframework.beans.BeanUtils;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 账号信息分页查询参数
 *
 * @author liujie
 *
 * @since 2020-05-20 11:42:33
 */
@Getter
@Setter
@ToString(callSuper = true)
@ApiModel("账号信息分页查询参数")
public class AccountPageQueryRequest implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = -3721218414953955878L;

	/**
	 * 账号名
	 */
	@ApiModelProperty("账号名")
	private String userName;
	
	/**
	 * 邮箱
	 */
	@ApiModelProperty("邮箱")
	private String email;
	
	/**
	 * 当前页数
	 */
	@ApiModelProperty("当前页数：默认1")
	private Integer currentPage = 1;
	
	/**
	 * 每页记录数
	 */
	@ApiModelProperty("每页记录数：默认10")
	private Integer pageSize = 10;
	
	/**
	 * 获取查询请求参数
	 * 
	 * @return 查询用户账号参数
	 */
	public AdminUserRequest transAdminUserRequest() {
		AdminUserRequest request = new AdminUserRequest();
		BeanUtils.copyProperties(this, request);
		
		return request;
	}
}
