package com.w3cjava.common.utils;
import java.util.UUID;
/**
 * 
 * @author	w3cjava
 * @date	2018年8月29日
 * @desc	封装各种生成唯一性ID算法的工具类.
 */
public class IdGen{
	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
