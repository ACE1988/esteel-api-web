package com.esteel.web.pojo.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.esteel.common.dubbo.PageResponse;

/**
 * 分页结果对象转换
 *
 * @author liujie
 *
 * @since 2020-05-20 16:24:37
 */
public class PageResponseTransfer<T> {
	/**
	 * 错误描述
	 */
	private static final String BEAN_TRANSFER_ERROR = "对象转换错误";
	/**
	 * PageResponse对象转换
	 *
	 * @param source 源对象
	 * @return 指定的PageResponse对象
	 */
	public PageResponse<T> transfer(PageResponse<?> source) {
		// 返回数据集合
		List<T> list = new ArrayList<>();
		/*
		 * 空参数检查
		 */
		if (source == null) {
			return new PageResponse<T>();
		}
		if (CollectionUtils.isEmpty(source.getData())) {
			return PageResponse.data(source.getCurrentPage(), source.getPageSize(), source.getTotalCount(), list);
		}
		// 获取转换的指定对象类
		Class<T> clazz = getTClass();
		source.getData().stream().forEach(t -> {
			try {
				// 调用转换类的构造方法生成实例
				list.add(clazz.getConstructor(t.getClass()).newInstance(t));
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				// 对象转换异常
				throw new RuntimeException(BEAN_TRANSFER_ERROR);
			}
		});
		// 返回转换后的对象
		return PageResponse.data(source.getCurrentPage(), source.getPageSize(), source.getTotalCount(), list);
	}

	@SuppressWarnings("unchecked")
	public Class<T> getTClass() {
		// 获取指定泛型类对象
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
