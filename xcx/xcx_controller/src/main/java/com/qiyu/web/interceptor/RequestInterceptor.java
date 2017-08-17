package com.qiyu.web.interceptor;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qiyu.util.common.ParameterUtil;
import com.qiyu.util.enums.Response;
import com.qiyu.web.vo.RequestParam;
import com.qiyu.web.vo.Result;

/**
 * 
 * @ClassName: RequestInterceptor 拦截器
 * @Description: 校验参数合法性
 * @author 小飞
 * @date 2016年6月13日 上午11:42:13
 *
 */
public class RequestInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String method = request.getParameter("method");
		String args = request.getParameter("param");
		
		Result result = new Result();
		// 4.判断args合法性
		Object bean = null;
		if(method!=null){
			String[] methods = method.split("/");
			String request_type = methods[0];
			WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
			bean = webApplicationContext.getBean(request_type + "Service");
		}

		if (bean != null) {
			boolean flag = ParameterUtil.check(args, RequestParam.class);
			if (!flag) {
				result.setCode(Response.PROP_ERROR.getErrorCode());
				result.setMessage(Response.PROP_ERROR.getMsg());
				this.writeResult(result, response);
				return false;
			}
		} else {
			result.setCode(Response.LOGIC_ERROR.getErrorCode());
			result.setMessage(Response.LOGIC_ERROR.getMsg());
			this.writeResult(result, response);
			return false;
		}
		return true;
	}

	/**
	 * 数据写出
	 * 
	 * @param result
	 * @param response
	 */
	private void writeResult(Result result, HttpServletResponse response) {
		try {
			String reslutJSON = JSONObject.toJSONString(result);
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(reslutJSON);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

}
