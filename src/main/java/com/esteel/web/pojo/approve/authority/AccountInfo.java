package com.esteel.web.pojo.approve.authority;

import com.esteel.user.service.response.admin.AdminUserResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 账户信息
 *
 * @author liujie
 *
 * @since 2020-05-20 11:24:29
 */
@Data
@NoArgsConstructor
@ApiModel("账号信息")
public class AccountInfo implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 3488186338928620511L;

	/**
	 * 记录ID，主键
	 */
	@ApiModelProperty("账号记录ID")
	private Long accountId;
	
	/**
	 * 账号名
	 */
	@ApiModelProperty("账号名")
	private String userName;
	
	/**
	 * 邮箱，唯一性
	 */
	@ApiModelProperty("邮箱")
	private String email;
	
	/**
	 * 账号类型：支持多个类型
	 */
	@ApiModelProperty("账号类型（多个类型）：0-初审员；1-终审员；2-主管")
	private List<Integer> types;
	
	/**
	 * 每日分配数，自动分配机制下的每日上限值
	 */
	@ApiModelProperty("每日分配数")
	private Integer assignNums;
	
	/**
	 * 自动分配标识
	 */
	@ApiModelProperty("是否开启自动分配:0-否；1-是")
	private Integer assignFlag;
	
	/**
	 * 账号状态
	 */
	@ApiModelProperty("账号状态:0-禁用；1-启用")
	private Integer status;
	
//	/**
//	 * 账号角色列表
//	 */
//	@ApiModelProperty("账号角色列表")
//	private List<RoleInfo> roleList;
	
	/**
	 * 头像
	 */
	@ApiModelProperty("头像")
	private String profilePicture;
	
	public AccountInfo(AdminUserResponse userResponse) {
		if (userResponse == null) {
			return;
		}
		BeanUtils.copyProperties(userResponse, this);
		this.accountId = userResponse.getId();
		this.assignFlag = userResponse.getAutoStatus();
		this.assignNums = userResponse.getNum();
//		if (CollectionUtils.isNotEmpty(userResponse.getRoleList())) {
//			this.roleList = userResponse.getRoleList().stream().map(RoleInfo::new).collect(Collectors.toList());
//		}
//		this.profilePicture = userResponse.getImageUrl();
	}
}
