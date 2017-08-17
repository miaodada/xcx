package com.qiyu.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.qiyu.web.vo.Result;
import com.qiyu.bean.User;
import com.qiyu.dao.IUserDao;
import com.qiyu.util.common.ParameterUtil;
import com.qiyu.util.enums.Response;
import com.qiyu.util.exception.BizException;
import com.qiyu.util.exception.DAOException;


/**
 * @ClassName: CommonController
 * @Description: 公共Controller
 * @author 小飞
 * @date 2016年6月13日 下午4:18:30
 *
 */

@Controller
public class CommonController { 
	
	

	private static Logger logger = Logger.getLogger(CommonController.class);
	@RequestMapping("/api")
	@SuppressWarnings("unchecked")
	public void find(HttpServletRequest request, String method, String param, HttpServletResponse response) {
		logger.info("----------------------------api.do---START----------------------------------------");
		logger.info("----------------------------method:" + method + "----"+request.getSession().getAttribute("user")+"------------------------------------");
		logger.info("----------------------------param:" + param + "----------------------------------------");
		// 参数转成Map
		Map<String, Object> params = (Map<String, Object>) ParameterUtil.parseObj(param, Map.class);
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html;charset=UTF-8");
		try {
			if (sessionJudge(request, response, params)) {
				String[] methods = method.split("/");
				String serviceName = methods[0] + "Service";
				String methodName = methods[1];
				responseData(serviceName,methodName,params,response,param);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		logger.info("----------------------------api.do---END----------------------------------------");
	}
	/**
	 * 对请求接口做出响应
	 * @param serviceName
	 * @param methodName
	 * @param params
	 * @param response
	 */
	public void responseData(String serviceName,String methodName,Map<String, Object> params,HttpServletResponse response,String param){
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		Object service = webApplicationContext.getBean(serviceName);
		Result result = this.getResult(service.getClass().getInterfaces()[0], service, methodName, params);
		try {
			if (result.getData() instanceof HSSFWorkbook) {
				HSSFWorkbook excel = (HSSFWorkbook) result.getData();
				response.setHeader("content-disposition", "attachment;filename="+ new String((excel.getSheetName(0)).getBytes("gbk"), "iso8859-1") + ".xls");
				response.setContentType("multipart/form-data");
				OutputStream ouputStream;
				ouputStream = response.getOutputStream();
				excel.write(ouputStream);
				ouputStream.flush();
				ouputStream.close();
			} else {
				//此参数即原始的请求参数
				result.setParam(param);
				String reslutJSON = JSONObject.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteMapNullValue);
				response.getWriter().write(reslutJSON);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	



	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Result getResult(Class clazz, Object obj, String methodName, Map<String, Object> params) {
		Result result = new Result();
		try {
			// 执行方法，返回结果
			Method serviceMethod = clazz.getMethod(methodName, Map.class);
			Object object = serviceMethod.invoke(obj, params);
			result.setData(object);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			Throwable cause = e.getCause();
			if (cause instanceof DAOException) {
				result.setCode(((DAOException) cause).getErrorCode());
				result.setMessage(cause.getMessage());
			} else if (cause instanceof BizException) {
				result.setCode(((BizException) cause).getErrorCode());
				result.setMessage(cause.getMessage());
			} else {
				result.setCode("1");
				result.setMessage("执行失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			result.setCode("1");
			result.setMessage("执行失败!");
		}
		return result;
	}
	@Autowired
	private IUserDao dao;
	
	/**
	 * session校验,提取出来,本地测试时注释部分代码
	 * @param request
	 * @param params
	 * @return
	 */
	private boolean sessionJudge(HttpServletRequest request, HttpServletResponse response, Map<String, Object> params){
		User currUser = null;
		logger.info("===================================SESSION验证START===========================================");
			Object o = request.getSession().getAttribute("user");
			if(o!=null){
				currUser=(User)o;
			}
			
//			if(currUser ==null){
//				currUser = new User();
//				//本地测试只保留一个账号(需要切换账号自行手动修改)
//				currUser.setId(new Long(9094718));
//				currUser.setPhone("14088889999");
//				currUser.setCompanyId(new Long(9169549));
//				currUser.setName("景麒测试账号");
//				currUser.setLoginUserId(new Long(9094718));
//				
//				currUser.setId(new Long(94463));
//				currUser.setPhone("14033338888");
//				currUser.setCompanyId(new Long(169359));
//				currUser.setName("xxxxx");
//				currUser.setLoginUserId(new Long(94463));
//			}
		try {
			
			if(currUser != null){
				params.put("userId", currUser.getId());
//				params.put("phone", currUser.getPhone()); 
//				params.put("companyId", currUser.getCompanyId()); 
//				params.put("userName", currUser.getName());
//				params.put("userHeadImg", currUser.getUserHeadImg());
			}else{
				Result result = new Result();
				result.setCode(Response.LOGIN_FAILURE.getErrorCode());
				result.setMessage("登录超时!请重新登录");
				String reslutJSON = JSONObject.toJSONString(result);
				try {
					response.getWriter().write(reslutJSON);
					return false;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
    
	
}
