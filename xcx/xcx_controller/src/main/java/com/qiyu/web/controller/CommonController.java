package com.qiyu.web.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.qiyu.web.vo.Result;

import net.coobird.thumbnailator.Thumbnails;

import com.qiyu.bean.Admin;
import com.qiyu.bean.FileInfo;
import com.qiyu.bean.User;
import com.qiyu.dao.IAdminDao;
import com.qiyu.dao.IUserDao;
import com.qiyu.util.common.DateUtil;
import com.qiyu.util.common.ParameterUtil;
import com.qiyu.util.common.PropertiesUtil;
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
	
	@RequestMapping("/adminLogin")
	@SuppressWarnings("unchecked")
	public void adminLogin(HttpServletRequest request, String method, String param, HttpServletResponse response) {
		logger.info("----------------------------login.do---START----------------------------------------");
		logger.info("----------------------------method:" + method + "----"+request.getSession().getAttribute("user")+"------------------------------------");
		logger.info("----------------------------param:" + param + "----------------------------------------");
		// 参数转成Map
		Map<String, Object> params = (Map<String, Object>) ParameterUtil.parseObj(param, Map.class);
		//flag为1为登出
		Object flagObject=params.get("flag");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html;charset=UTF-8");
		
		if(flagObject!=null&&flagObject.toString().equals("1")){
			logger.info("----------------------------登出清除session中的user----------------------------------");
			request.getSession().setAttribute("admin",null);
			Result result = new Result();
			result.setCode(Response.LOGINOUT_SUCESS.getErrorCode());
			result.setMessage(Response.LOGINOUT_SUCESS.getMsg());
			String reslutJSON = JSONObject.toJSONString(result);
			try {
				response.getWriter().write(reslutJSON);
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}else{
			//登录
			try {
				String[] methods = method.split("/");
				String serviceName = methods[0] + "Service";
				String methodName = methods[1];
				// 获取service对象
				WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
				Object service = webApplicationContext.getBean(serviceName);

				Result result = this.getResult(service.getClass().getInterfaces()[0], service, methodName, params);
				
				Admin admin = null;

					if (result != null && result.getData() != null) {
						admin = (Admin)result.getData();
						// 存储登录的员工信息
						request.getSession().setAttribute("admin", admin);
						request.getSession().setMaxInactiveInterval(30*60);
						
						result.setCode(Response.LOGIN_SUCESS.getErrorCode());
						result.setMessage(Response.LOGIN_SUCESS.getMsg());
						
						
						String reslutJSON = JSONObject.toJSONString(result);
						response.getWriter().write(reslutJSON);
					}
					else{
						result.setCode(Response.LOGIN_FAILURE.getErrorCode());
						result.setMessage(Response.LOGIN_FAILURE.getMsg());
						
						
						String reslutJSON = JSONObject.toJSONString(result);
						response.getWriter().write(reslutJSON);
					}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}
		logger.info("----------------------------api.do---END----------------------------------------");
	}
	
	@RequestMapping("/userLogin")
	@SuppressWarnings("unchecked")
	public void userLogin(HttpServletRequest request, String method, String param, HttpServletResponse response) {
		logger.info("----------------------------login.do---START----------------------------------------");
		logger.info("----------------------------method:" + method + "----"+request.getSession().getAttribute("user")+"------------------------------------");
		logger.info("----------------------------param:" + param + "----------------------------------------");
		// 参数转成Map
		Map<String, Object> params = (Map<String, Object>) ParameterUtil.parseObj(param, Map.class);
		//flag为1为登出
		Object flagObject=params.get("flag");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html;charset=UTF-8");
		
		if(flagObject!=null&&flagObject.toString().equals("1")){
			logger.info("----------------------------登出清除session中的user----------------------------------");
			request.getSession().setAttribute("user",null);
			Result result = new Result();
			result.setCode(Response.LOGINOUT_SUCESS.getErrorCode());
			result.setMessage(Response.LOGINOUT_SUCESS.getMsg());
			String reslutJSON = JSONObject.toJSONString(result);
			try {
				response.getWriter().write(reslutJSON);
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}else{
			//登录
			try {
				String[] methods = method.split("/");
				String serviceName = methods[0] + "Service";
				String methodName = methods[1];
				// 获取service对象
				WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
				Object service = webApplicationContext.getBean(serviceName);

				Result result = this.getResult(service.getClass().getInterfaces()[0], service, methodName, params);
				
				User user = null;

					if (result != null && result.getData() != null) {
						user = new User();
						user.setId( Long.valueOf(((Map<String, Object>) result.getData()).get("id").toString()));
						user.setName( ((Map<String, Object>) result.getData()).get("name").toString());
						user.setHeadImg( ((Map<String, Object>) result.getData()).get("headImg").toString());
						// 存储登录的员工信息
						request.getSession().setAttribute("user", user);
						request.getSession().setMaxInactiveInterval(30*60);
						result.setCode(Response.LOGIN_SUCESS.getErrorCode());
						result.setMessage(Response.LOGIN_SUCESS.getMsg());
						String reslutJSON = JSONObject.toJSONString(result);
						response.getWriter().write(reslutJSON);
					}else{
						result.setCode(Response.LOGIN_FAILURE.getErrorCode());
						result.setMessage(Response.LOGIN_FAILURE.getMsg());
						
						
						String reslutJSON = JSONObject.toJSONString(result);
						response.getWriter().write(reslutJSON);
					}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}
		logger.info("----------------------------api.do---END----------------------------------------");
	}
	
	
	@RequestMapping("/userApi")
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
			if (sessionJudge(request, response, params,1)) {
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
	
	
	
	@RequestMapping("/adminApi")
	@SuppressWarnings("unchecked")
	public void find2(HttpServletRequest request, String method, String param, HttpServletResponse response) {
		logger.info("----------------------------api.do---START----------------------------------------");
		logger.info("----------------------------method:" + method + "----"+request.getSession().getAttribute("user")+"------------------------------------");
		logger.info("----------------------------param:" + param + "----------------------------------------");
		// 参数转成Map
		Map<String, Object> params = (Map<String, Object>) ParameterUtil.parseObj(param, Map.class);
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html;charset=UTF-8");
		try {
			if (sessionJudge(request, response, params,2)) {
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

				//此参数即原始的请求参数
				result.setParam(param);
				String reslutJSON = JSONObject.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteMapNullValue);
				response.getWriter().write(reslutJSON);
			
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
	private boolean sessionJudge(HttpServletRequest request, HttpServletResponse response, Map<String, Object> params,int i ){
		logger.info("===================================SESSION验证START===========================================");
		
		if(i==1){
			User currUser = null;
			Object o = request.getSession().getAttribute("user");
			if(o!=null){
				currUser=(User)o;
			}
			if(currUser ==null){
				currUser=new User();
				currUser.setId(1l);
			}
			try {
				
				if(currUser != null){
					params.put("userId", currUser.getId());
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
		} 
		if(i==2){
			Admin currAdmin = null;
			Object o = request.getSession().getAttribute("admin");
			if(o!=null){
				currAdmin=(Admin)o;
			}
			if(currAdmin ==null){
				currAdmin=new Admin();
				currAdmin.setId(4l);
				currAdmin.setLevel("2");
				currAdmin.setBuildingId(1l);
				currAdmin.setStoreId(0l);
				currAdmin.setInitUpdate("1");
			}
			try {
				if(currAdmin != null){
					params.put("adminId", currAdmin.getId());
					params.put("level", currAdmin.getLevel());
					params.put("buildingId", currAdmin.getBuildingId());
					params.put("storeId", currAdmin.getStoreId());
					params.put("initUpdate", currAdmin.getInitUpdate());
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
		}
		return true;
	}
	
	@Autowired
	private IAdminDao adminDao;
	
	@RequestMapping("/uploadFileForApp")
	@SuppressWarnings("unchecked")
	public void uploadFileForApp(HttpServletRequest request, String method, String param, HttpServletResponse response) {
		logger.info("----------------------------app调用上传接口uploadFileForApp:START----------------------------------------");
		logger.info("----------------------------method:" + method + "----------------------------------------");
		logger.info("----------------------------param:" + param + "----------------------------------------");
		// 参数转成Map
		Object admin = request.getSession().getAttribute("admin");
		if(admin==null){
			throw new BizException("430", "无权限");
		}
		try {
			JSONObject resultJson = new JSONObject();
			String fileIds =null;
				CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
				// 过滤掉不允许的文件类型
				String[] errorType = { ".jpg", ".png", ".gif"};
				if (multipartResolver.isMultipart(request)) {
					// 转换成多部分request
					MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
					// 取得request中的所有文件名
					Iterator<String> iter = multiRequest.getFileNames();
					
					while (iter.hasNext()) {
						FileInfo fileInfo = new FileInfo();
						// 取得上传文件
						MultipartFile multipartFile = multiRequest.getFile(iter.next());
						if (multipartFile != null) {
							// 取得当前上传文件的文件名称
							String originalFilename = multipartFile.getOriginalFilename();
							//String fileType = originalFilename.split("\\.", 2)[1];
							// 获取图片扩展名
			       	    	String fileType = originalFilename.substring(originalFilename.lastIndexOf(".")+1,originalFilename.length());
							if (originalFilename.trim() != "") {
								for (int temp = 0; temp < errorType.length; temp++) {
									// 文件名的第一个.后面的为文件后缀
									if (!fileType.endsWith(errorType[temp])) {
										throw new IOException("只能上传jpg/png/gif文件");
									}
								}
							String curDate = DateUtil.getCurDate("yyyymmdd");
							String file_location = PropertiesUtil.getPropertyByKey("file_location_app")+curDate+"/";
							File dir = new File(file_location);
							if (!dir.exists() && !dir.isDirectory()) {
								//目录不存在则创建一个
								dir.mkdir();
							}
							
							String uuid = UUID.randomUUID().toString().replaceAll("-", "");
							String fileName = "File" + uuid +"."+ fileType;
							
							File localFile = new File(file_location+fileName); //保存路径
							//1.先将原文件上传至服务器(目前文件与项目在同一台服务器上)
							multipartFile.transferTo(localFile);
							
							BufferedImage bufImage = ImageIO.read(new FileInputStream(localFile));
							
							//3.准备将文件信息保存到数据库表中
							String httpFilePath = PropertiesUtil.getPropertyByKey("file_url");
							
							
							//生成压缩图
							
					        String newFileName ="YS_"+fileName;
					        //拼接后台文件名称
					        if(fileName.contains(".png")){
					        	newFileName = fileName.replace(".png", ".jpg");
					        }
					        long size = multipartFile.getSize();
					        double scale = 1.0d ;
					        if(size >= 200*1024){
					            if(size > 0){
					                scale = (200*1024f) / size  ;
					            }
					        }
					        
					        
					        //拼接文件路劲
					        try {
					            //added by chenshun 2016-3-22 注释掉之前长宽的方式，改用大小
//					            Thumbnails.of(filePathName).size(width, height).toFile(thumbnailFilePathName);
					            if(size < 200*1024){
					                Thumbnails.of(file_location+fileName).scale(1f).outputFormat("jpg").toFile(file_location+newFileName);
					            }else{
					                Thumbnails.of(file_location+fileName).scale(1f).outputQuality(scale).outputFormat("jpg").toFile(file_location+newFileName);
					            }
					            
					        } catch (Exception e1) {
					           throw new BizException("430", "图片压缩失败");
					        }
			                
					      //4.拼接成全路径
							String userFileUrl = httpFilePath+curDate+newFileName;
							fileInfo.setUrl(userFileUrl); //访问路径
		                	//文件信息保存到库中
		                	String fileId = adminDao.saveFileInfo(fileInfo);
		                	if(fileIds==null){
		                		fileIds=fileId;
		                	}else{
		                		fileIds=fileIds+","+fileId;
		                	}
		                	resultJson.put("code", 0);
		                	resultJson.put("data", fileIds);
		                    resultJson.put("message", "上传图片成功！");
		        			}
						}
					}
					try {
		            	response.setContentType("text/json;charset=utf-8");
		    			response.getWriter().print(resultJson.toString());
		    		} catch (IOException e) {
		    			resultJson.put("code", 4300);
		    			resultJson.put("message", "上传图片失败！");
		    			response.getWriter().write(resultJson.toString());
		    			e.printStackTrace();
		    		}
				}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		logger.info("----------------------------app端调用上传接口uploadFileForApp:END----------------------------------------");
	}
	
	public static void main(String[] args) {
		
	}
}
