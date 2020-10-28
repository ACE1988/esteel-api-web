package com.esteel.web.pojo.approve.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @version 1.0.0
 * @ClassName RoleInfo.java
 * @author: liu Jie
 * @Description: TODO
 * @createTime: 2020年-10月-19日  15:35
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class RoleInfo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long roleId ;

    private String roleName ;
}
