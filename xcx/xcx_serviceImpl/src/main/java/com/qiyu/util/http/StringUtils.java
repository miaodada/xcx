package com.qiyu.util.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.qiyu.util.exception.BizException;

import net.sf.json.JSONObject;


/**
 * String工具
 * 主要对 StringUtils 的一些方法进行重写,达到更方便的使用
 * @author zhou-baicheng
 *
 */
public class StringUtils extends org.apache.commons.lang.StringUtils{
	
	/**
	 * 一次性判断多个或单个对象为空。
	 * @param objects
	 * @author zhou-baicheng
	 * @return 只要有一个元素为Blank，则返回true
	 */
	public static boolean isBlank(Object...objects){
		Boolean result = Boolean.FALSE ;
		for (Object object : objects) {
			if(object == null || "".equals(object.toString().trim()) 
					|| "null".equals(object.toString().trim())
					|| "[null]".equals(object.toString().trim())
					|| "[]".equals(object.toString().trim())
					|| "{}".equals(object.toString().trim())
					
				){
				result = Boolean.TRUE ; 
				break ; 
			}
		}
		return result ; 
	}
	/**
	 * 一次性判断多个或单个对象不为空。
	 * @param objects
     * @author zhou-baicheng
	 * @return 只要有一个元素不为Blank，则返回true
	 */
	public static boolean isNotBlank(Object...objects){
		return !isBlank(objects);
	}
	public static boolean isBlank(String...objects){
		Object[] object = objects ;
		return isBlank(object);
	}
	public static boolean isNotBlank(String...objects){
		Object[] object = objects ;
		return !isBlank(object);
	}
	public static boolean isBlank(String str){
		Object object = str ;
		return isBlank(object);
	}
	public static boolean isNotBlank(String str){
		Object object = str ;
		return !isBlank(object);
	}

	public static String checkNullToConvert(Object obj) {
		return StringUtils.isBlank(obj) ? "" : obj.toString();
	}
	/**
	 * 判断一个字符串在数组中存在几个
	 * @param baseStr
	 * @param strings
	 * @return
	 */
	public static int indexOf(String baseStr,String[] strings){
		
		if(null == baseStr || baseStr.length() == 0 || null == strings)
			return 0;
		
		int i = 0;
		for (String string : strings) {
			boolean result = baseStr.equals(string);
			i = result ? ++i : i;
		}
		return i ;
	}
	/**
	 * 判断一个字符串是否为JSONObject,是返回JSONObject,不是返回null
	 * @param args
	 * @return
	 */
	public static net.sf.json.JSONObject isJSONObject(Object args) {
		net.sf.json.JSONObject result = null ;
		
		if(args instanceof net.sf.json.JSONObject){
			return (net.sf.json.JSONObject)args;
		}
		
		if(isBlank(args)){
			return result ;
		}
		try {
			return net.sf.json.JSONObject.fromObject(args);
		} catch (Exception e) {
			return result ;
		}
	}
	
	
	
	
	/**
	 * 判断一个字符串是否为JSONArray,是返回JSONArray,不是返回null
	 * @param args
	 * @return
	 */
	public static net.sf.json.JSONArray isJSONArray(Object args) {
		
		if(args instanceof net.sf.json.JSONArray){
			return (net.sf.json.JSONArray)args;
		}
		
		net.sf.json.JSONArray result = null ;
		if(isBlank(args)){
			return result ;
		}
		try {
			return net.sf.json.JSONArray.fromObject(args);
		} catch (Exception e) {
			return result ;
		}
	}
	public static String trimToEmpty(Object str){
	  return (isBlank(str) ? "" : str.toString().trim());
	}
	

    /**
     * 把Map转换成get请求参数类型,如 {"name"=20,"age"=30} 转换后变成 name=20&age=30
     * @param map
     * @return
     */
    public static String mapToGet(Map<? extends Object,? extends Object> map){
    	String result = "" ;
    	if(map == null || map.size() ==0){
    		return result ;
    	}
    	Set<? extends Object> keys = map.keySet();
    	for (Object key : keys ) {
    		result += ((String)key + "=" + (String)map.get(key) + "&");
		}
    	
    	return isBlank(result) ? result : result.substring(0,result.length() - 1);
    }
    /**
     * 把一串参数字符串,转换成Map 如"?a=3&b=4" 转换为Map{a=3,b=4}
     * @param args
     * @return
     */
    public static Map<String, String> getToMap(String args){
    	if(isBlank(args)){
    		return new HashMap<String, String>() ;
    	}
    	args = args.trim();
    	//如果是?开头,把?去掉
    	if(args.startsWith("?")){
    		args = args.substring(1,args.length());
    	}
    	String[] argsArray = args.split("&");
    	
    	Map<String,String> result = new HashMap<String,String>();
    	for (String ag : argsArray) {
			if(!isBlank(ag) && ag.indexOf("=")>0){
				
				String[] keyValue = ag.split("=");
				//如果value或者key值里包含 "="号,以第一个"="号为主 ,如  name=0=3  转换后,{"name":"0=3"}, 如果不满足需求,请勿修改,自行解决.
					
				String key = keyValue[0];
				String value = "" ;
				for (int i = 1; i < keyValue.length; i++) {
					value += keyValue[i]  + "=";
				}
				value = value.length() > 0 ? value.substring(0,value.length()-1) : value ;
				result.put(key,value);
				
			}
		}
    	
    	return result ;
    }
    
    /**
	 * 转换成Unicode
	 * @param str
	 * @return
	 */
    public static String toUnicode(String str) {
        String as[] = new String[str.length()];
        String s1 = "";
        for (int i = 0; i < str.length(); i++) {
            as[i] = Integer.toHexString(str.charAt(i) & 0xffff);
            s1 = s1 + "\\u" + as[i];
        }
        return s1;
     }
    
    public static String getDoubleTOString(Double str){
		String money = str.toString();
		try {   
			Double.parseDouble(money);
		} catch (Exception e) {
			BigDecimal bDecimal = new BigDecimal(str);
			money = bDecimal.toPlainString();
		} 
		return money;
    	
    }
    /**
     * 处理9588json
     * getJsonString
     * @param resultString
     * @return  JSONObject
     * @author JIANG FEI
     * Jul 25, 2014 6:10:54 PM
     */
    public static JSONObject getJsonString(String resultString){
    	resultString = resultString.replace("\\","");
		resultString = resultString.substring(1, resultString.length()-1);
		return StringUtils.isJSONObject(resultString);
    	
    }
    /**
     * 处理9588json
     * getJsonString
     * @param resultString
     * @return  JSONObject
     * @author JIANG FEI
     * Jul 25, 2014 6:10:54 PM
     */
    public static String  getJson(String resultString){
    	resultString = resultString.replace("\\","");
		resultString = resultString.substring(1, resultString.length()-1);
		return resultString;
    	
    }
    /**
     * 把数组转换成Set 方便判断
     * @param objs
     * @return
     */
    public static TreeSet<String> arrayToSet(String[] objs){
    	TreeSet<String> result = new TreeSet<String>();
    	if(null ==objs){
    		return result ;
    	}
    	for (String obj:objs) {
    		result.add(obj);
    	}
    	return result;
    }
    
    public static boolean isNumber(String str){ 
       
       if(isBlank(str)){
    	   return false;
       }
	   Pattern pattern = Pattern.compile("[0-9]*"); 
	   Matcher isNum = pattern.matcher(str);
	   if( !isNum.matches() ){
	       return false; 
	   } 
	   return true; 
    }
    
    
	// 逗号分隔字符串按要求匹配字符串操作 ，type 1.移除 0，增
	public static  String  updateCommaString(String type,String content,String  matching){
		if(content==null){
			content="";
		}
		List<String> list = new ArrayList<>(); 
		Collections.addAll(list, content.split(","));
		if(type.equals("1")&&!list.contains(matching)){
			list.add(matching);
		}
		if(type.equals("0")&&list.contains(matching)){
			list.remove(matching);
		}
		if(list.size()<=0){
			return "";
		}
		
		return list.toString().substring(1,list.toString().length()-1 ).replace(" ", "");
		
	}
	// 集合变字符串
	public static  String  ListChangeString(Collection list){
		if(list==null||list.size()<=0){
			return "";
		}
		String str = list.toString().substring(1,list.toString().length()-1 ).replace(" ", "");
		return str;
		
	}
    
    public static String conversionCharacter(String str){
    	return str.replace("\"", "&quot;").replace("<", "&lt;").replace(">", "&gt;").replace("'", "&#39;");
    }
    
    /**
     * 
     * @Title: doGet 
     * @Description: GET请求，获得返回数据
     * @param urlStr 请求路径
     * @return String
     * @throws
     */
    public static String doGet(String urlStr) {
        URL url = null;
        HttpURLConnection conn = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(20000);
            conn.setConnectTimeout(20000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            if (conn.getResponseCode() == 200) {
                is = conn.getInputStream();
                baos = new ByteArrayOutputStream();
                int len = -1; 
                byte[] buf = new byte[128];

                while ((len = is.read(buf)) != -1) {
                    baos.write(buf, 0, len);
                }
                baos.flush();
                return baos.toString();
            } else {
                throw new RuntimeException(" responseCode is not 200 ... ");
            }
        } catch (Exception e) {
        	throw new BizException("430","凭证获取信息用户openId和sessionKey失败");
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
            }
            try {
                if (baos != null)
                    baos.close();
            } catch (IOException e) {
            }
            conn.disconnect();
        }

    }
}




