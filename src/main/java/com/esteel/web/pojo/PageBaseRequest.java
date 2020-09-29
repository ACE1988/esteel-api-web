package com.esteel.web.pojo;

import io.swagger.annotations.ApiParam;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 分页查询基础参数
 *
 * @author liujie
 *
 * @since 2020-01-20 14:39:51
 */
@Data
@ApiModel("分页查询基础参数")
public class PageBaseRequest implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = -485977464590264623L;

	/**
	 * 当前页数
	 */
	@ApiParam(value = "当前页数：默认1",required = true)
	@ApiModelProperty("当前页数：默认1")
	private Integer currentPage = 1;
	
	/**
	 * 每页记录数
	 */
	@ApiParam(value = "每页记录数：默认10",required = true)
	@ApiModelProperty("每页记录数：默认10")
	private Integer pageSize = 10;

}
