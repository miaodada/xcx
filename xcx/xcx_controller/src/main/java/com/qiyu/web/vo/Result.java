package com.qiyu.web.vo;

/**
 * 
 * @ClassName: Result
 * @Description: 返回消息
 * @author 小飞
 * @date 2016年6月20日 上午11:06:23
 *
 */
public class Result {
	/**
	 * 错误码
	 */
	private String code = "0";
	/**
	 * 错误信息
	 */
	private String message = "操作成功";
	/**
	 * 返回数据：对象/数组，当有错误时，返回null
	 */
	private Object data;
	
	/**
	 * 请求参数
	 */
	private String param;

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
