package com.example.myapplication18.dj;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * 常用工具类
 * 
 * @auther chenlf3
 * @date 2015年8月6日-上午9:47:33
 * Copyright (c) 2015点聚信息技术有限公司-版权所有
 */

public class ClfUtil{
	
	private static String TAG = "ClfUtil";
	private static Map<String,String> emailMap = new HashMap<String,String>();
	private boolean flag = false;
	private static String [][]  MIME_MapTable={   
            //{后缀名，MIME类型}   
            {".3gp",    "video/3gpp"},
            {".apk",    "application/vnd.android.package-archive"},   
            {".asf",    "video/x-ms-asf"}, 
            {".avi",    "video/x-msvideo"},   
            {".bin",    "application/octet-stream"},   
            {".bmp",    "image/bmp"},   
            {".c",  "text/plain"},   
            {".class",  "application/octet-stream"},
            {".conf",   "text/plain"},   
            {".cpp",    "text/plain"},   
            {".doc",    "application/msword"},   
            {".docx",   "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},   
            {".xls",    "application/vnd.ms-excel"},    
            {".xlsx",   "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},   
            {".exe",    "application/octet-stream"},   
            {".gif",    "image/gif"},   
            {".gtar",   "application/x-gtar"},   
            {".gz", "application/x-gzip"},   
            {".h",  "text/plain"},   
            {".htm",    "text/html"},   
            {".html",   "text/html"},   
            {".jar",    "application/java-archive"},   
            {".java",   "text/plain"},   
            {".jpeg",   "image/jpeg"},   
            {".jpg",    "image/jpeg"},   
            {".js", "application/x-javascript"},   
            {".log",    "text/plain"},   
            {".m3u",    "audio/x-mpegurl"},   
            {".m4a",    "audio/mp4a-latm"},   
            {".m4b",    "audio/mp4a-latm"},   
            {".m4p",    "audio/mp4a-latm"},   
            {".m4u",    "video/vnd.mpegurl"},   
            {".m4v",    "video/x-m4v"},    
            {".mov",    "video/quicktime"},   
            {".mp2",    "audio/x-mpeg"},   
            {".mp3",    "audio/x-mpeg"},   
            {".mp4",    "video/mp4"},   
            {".mpc",    "application/vnd.mpohun.certificate"},          
            {".mpe",    "video/mpeg"},     
            {".mpeg",   "video/mpeg"},     
            {".mpg",    "video/mpeg"},     
            {".mpg4",   "video/mp4"},      
            {".mpga",   "audio/mpeg"},   
            {".msg",    "application/vnd.ms-outlook"},   
            {".ogg",    "audio/ogg"},   
            {".pdf",    "application/pdf"},   
            {".png",    "image/png"},   
            {".pps",    "application/vnd.ms-powerpoint"},   
            {".ppt",    "application/vnd.ms-powerpoint"},   
            {".pptx",   "application/vnd.openxmlformats-officedocument.presentationml.presentation"},   
            {".prop",   "text/plain"},   
            {".rc", "text/plain"},   
            {".rmvb",   "audio/x-pn-realaudio"},   
            {".rtf",    "application/rtf"},   
            {".sh", "text/plain"},   
            {".tar",    "application/x-tar"},      
            {".tgz",    "application/x-compressed"},    
            {".txt",    "text/plain"},   
            {".wav",    "audio/x-wav"},   
            {".wma",    "audio/x-ms-wma"},   
            {".wmv",    "audio/x-ms-wmv"},   
            {".wps",    "application/vnd.ms-works"},   
            {".xml",    "text/plain"},   
            {".z",  "application/x-compress"},   
            {".zip",    "application/x-zip-compressed"},   
            {"",        "*/*"}     
        };   
  
	
	static {
		emailMap.put("qq.com", "http://mail.qq.com");
		emailMap.put("gmail.com", "http://mail.google.com");
		emailMap.put("sina.com", "http://mail.sina.com.cn");
		emailMap.put("163.com", "http://mail.163.com");
		emailMap.put("126.com", "http://mail.126.com");
		emailMap.put("yeah.net", "http://www.yeah.net/");
		emailMap.put("sohu.com", "http://mail.sohu.com/");
		emailMap.put("tom.com", "http://mail.tom.com/");
		emailMap.put("sogou.com", "http://mail.sogou.com/");
		emailMap.put("139.com", "http://mail.10086.cn/");
		emailMap.put("hotmail.com", "http://www.hotmail.com");
		emailMap.put("live.com", "http://login.live.com/");
		emailMap.put("live.cn", "http://login.live.cn/");
		emailMap.put("live.com.cn", "http://login.live.com.cn");
		emailMap.put("189.com", "http://webmail16.189.cn/webmail/");
		emailMap.put("yahoo.com.cn", "http://mail.cn.yahoo.com/");
		emailMap.put("yahoo.cn", "http://mail.cn.yahoo.com/");
		emailMap.put("eyou.com", "http://www.eyou.com/");
		emailMap.put("21cn.com", "http://mail.21cn.com/");
		emailMap.put("188.com", "http://www.188.com/");
		emailMap.put("foxmail.coom", "http://www.foxmail.com");
	}
	
	/**
	 * 获取所需so(目前支持的so型号有)
	 * @auther chenlf3
	 * @date 2018年12月18日 上午11:39:33
	 * @param context
	 * @param ndkSetList 根据androidstudio开发工具上设置的ndk列表设置,无可设置为null
	 * @param jniLibsList 根据libs文件夹下创建的so类型文件夹名称设置(如果文件夹下没有任何so文件，该文件夹不加入jniLibList)
	 * @return
	 */
	public static String getAppAbi(Context context, List<String> ndkSetList, List<String> jniLibsList) {
		//File jniDir = context.getDir("jniLibs", Activity.MODE_PRIVATE);
		//取交集
		List<String> commonList = null;// = new ArrayList<String>();
		if(ndkSetList == null || ndkSetList.size()<=0) {
			if(jniLibsList == null || jniLibsList.size()<=0) {
				//不做任何事情
			} else {
				commonList = jniLibsList;
			}
		} else {
			if(jniLibsList == null || jniLibsList.size()<=0) {
				commonList = ndkSetList;
			} else {
				commonList = new ArrayList<String>();
				for(String ndkSet:ndkSetList) {
					for(String jniLib:jniLibsList) {
						if(ndkSet.equals(jniLib)) {
							commonList.add(ndkSet);
							break;
						}
					}
				}
			}
		}
		if((commonList==null || commonList.size()<=0) && ndkSetList != null && ndkSetList.size()>=0) {
			commonList = ndkSetList;
		}
		//获取设备支持的so
		String[] SUPPORTED_ABIS = android.os.Build.SUPPORTED_ABIS;//4.4.2是不支持该方法的
		if(SUPPORTED_ABIS == null || SUPPORTED_ABIS.length<=0) {
			return "error-设备支持的ABIS列表空";
		}
		if(commonList==null) {
			return SUPPORTED_ABIS[0];
		}
		for(String bean:SUPPORTED_ABIS) {
			if(commonList.contains(bean)) {
				return bean;
			}
		}
		return "error-未查询到合适的ABIS值";
	}
	
	private int loadSo(Context context, String filePath, String filename) {
		File file = new File(filePath, filename);
		if(!file.exists()) {
			return -1;
		}
		File jnidir = context.getDir("jniLibs", Activity.MODE_PRIVATE);
		String soPath = jnidir.getAbsolutePath()+File.separator+filename;
		File soFile = new File(soPath);
		if(soFile.exists()) soFile.delete();
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(file);
			os = new FileOutputStream(soPath);
			byte[] buffer = new byte[2048];
			int len = -1;
			while((len=is.read(buffer))!=-1) {
				os.write(buffer, 0, len);
			}
			os.close();
			is.close();
			return 1;
		} catch(IOException e) {
			e.printStackTrace();
			try {
				if(os != null) os.close();
				if(is != null) is.close();
			} catch(IOException e1) {
				
			}
			return -2;
		}
	}
	
	/**
	 * 获取文本类型的图片，该图片可放进文本框
	 * @auther chenlf3
	 * @date 2015年12月1日 下午2:18:03
	 * @param drawable
	 * @return
	 */
	public static SpannableString getSpannableString(Context context, Bitmap bitmap) {
		BitmapDrawable drawable= new BitmapDrawable(context.getResources(), bitmap);
		SpannableString spannable = new SpannableString("c");
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
		//要让图片替代指定的文字就要用ImageSpan
		ImageSpan span = new ImageSpan(drawable,ImageSpan.ALIGN_BOTTOM);
		spannable.setSpan(span, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		return spannable;
	}
	
	/**
	 * 安装apk
	 * @auther chenlf3
	 * @date 2015年12月1日 下午2:11:22
	 * @param context
	 * @param filename
	 */
	public static void installApk(Context context, String filename) {
        File file = new File(filename);
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        String type = "application/vnd.android.package-archive";
        intent.setDataAndType(Uri.fromFile(file), type);
        context.startActivity(intent);
    }
	
	/**
	 * 静默安装apk(需要手机有root权限)
	 * @auther chenlf3
	 * @date 2015年12月1日 下午2:11:22
	 * @param context
	 * @param filename
	 */
	public static boolean clientInstallApk(Context context, String filename) {
		PrintWriter PrintWriter = null;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("su");
            PrintWriter = new PrintWriter(process.getOutputStream());
            PrintWriter.println("chmod 777 "+filename);
            PrintWriter.println("export LD_LIBRARY_PATH=/vendor/lib:/system/lib");
            PrintWriter.println("pm install -r "+filename);
//          PrintWriter.println("exit");
            PrintWriter.flush();
            PrintWriter.close();
            int value = process.waitFor();
            if(value == 0) {//成功
            	return true;
            } else if(value == 1) {//失败
            	return false;
            } else {//位置情况
            	return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(process!=null){
                process.destroy();
            }
        }
        return false;
    }
	
	/**
	 * 判断是否有root权限,如果没有则获取root权限
	 * @auther chenlf3
	 * @date 2016年5月26日 下午9:12:32
	 * @return
	 */
	public static boolean hasRootPerssion(){
        PrintWriter PrintWriter = null;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("su");
            PrintWriter = new PrintWriter(process.getOutputStream());
            PrintWriter.flush();
            PrintWriter.close();
            int value = process.waitFor();
            if(value == 0) {//成功
            	return true;
            } else if(value == 1) {//失败
            	return false;
            } else {//位置情况
            	return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(process!=null){
                process.destroy();
            }
        }
        return false;
    }
	
	/**
	 * 获取app版本号
	 * @auther chenlf3
	 * @date 2015年9月21日 上午11:02:05
	 * @param context
	 * @return
	 * @throws NameNotFoundException 
	 */
	public static int getVersionCode(Context context) {
		try {
			return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 获取app版本名称
	 * @auther chenlf3
	 * @date 2016年2月1日 上午10:11:13
	 * @param context
	 * @return
	 */
	public static String getVersionName(Context context) {
		try {
			return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 判断网络是否可用
	 * @auther chenlf3
	 * @date 2015年9月18日 下午4:53:45
	 * @param context
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if(cm != null) {
			//如果仅仅判断网络连接可以使用cm.getActiveNetworkInfo().isAvailable();
			NetworkInfo[] info = cm.getAllNetworkInfo();
			for(int i=0;i<info.length;i++) {
				if(info[i].getState() == NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 判断wifi是否打开
	 * @auther chenlf3
	 * @date 2015年9月18日 下午5:02:36
	 * @param context
	 * @return
	 */
	public static boolean isWifiEnabled(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if(networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
			return true;
		}
		return false;
/*		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return ((cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);*/
	}
	
	/**
	 * 判断是否3G网络
	 * @auther chenlf3
	 * @date 2015年9月18日 下午5:12:11
	 * @param context
	 * @return
	 */
	public static boolean is3rd(Context context) {   
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);   
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();   
        if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {   
            return true;   
        }
        return false;   
    }
	
	/**
	 * 判断当前网络是否是wifi
	 * @auther chenlf3
	 * @date 2015年9月18日 下午5:13:47
	 * @param context
	 * @return
	 */
	public static boolean isWifi(Context context) {   
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);   
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();   
        if (networkINfo != null && networkINfo.getType() == ConnectivityManager.TYPE_WIFI) {   
            return true;   
        }   
        return false;   
    }
	
	/**
	 * 判断GPS是否打开
	 * @auther chenlf3
	 * @date 2015年9月18日 下午4:59:31
	 * @param context
	 * @return
	 */
	public static boolean isGpsEnabled(Context context) {
		LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		List<String> accessibleProviders = lm.getProviders(true);
		return (accessibleProviders!=null && accessibleProviders.size()>0);
	}
	
	/**
	 * 根据服务器端错误状态码，返回错误信息
	 * @auther chenlf3
	 * @date 2015年9月11日 上午10:54:26
	 * @param code
	 * @return
	 */
	public static String getClientErrorInfoByCode(int code) {
		String errorMsg = null;
		switch(code) {
		case 400:
			errorMsg = "请求错误！";
			break;
		case 401:
			errorMsg = "未授权！";
			break;
		case 402:
			break;
		case 403:
			errorMsg = "禁止访问！";
			break;
		case 404:
			errorMsg = "请求资源错误，请检查URL！";
			break;
		case 405:
			errorMsg = "对于请求所标识的资源，不允许使用请求行中所指定的方法！";
			break;
		case 406:
			errorMsg = "根据此请求中所发送的“接受”标题，此请求所标识的资源只能生成内容特征为“不可接受”的响应实体！";
			break;
		case 407:
			errorMsg = "需要代理身份验证！";
			break;
		case 412:
			errorMsg = "在服务器上测试前提条件时，部分请求标题字段中所给定的前提条件估计为FALSE！";
			break;
		case 414:
			errorMsg = "Request-URI太长！";
			break;
		case 500:
			errorMsg = "服务器的内部错误！";
			break;
		case 501:
			errorMsg = "服务器不支持实现此请求所需的功能！";
			break;
		case 502:
			errorMsg = "网关出错！";
			break;
		default:
			break;
		}
		return errorMsg;
	}
	
	/**
	 * 根据邮箱获取邮箱登录地址
	 * @auther chenlf3
	 * @date 2015年9月7日 下午12:33:56
	 * @param email
	 * @return
	 */
	public static String getEmailLoginAddress(String email) {
		if(email == null || "".equals(email.trim())) return null;
		Set<String> sets = emailMap.keySet();
		for(String key:sets) {
			if(email.endsWith(key)) {
				return emailMap.get(key);
			}
		}
		return null;
	}
	
	/**
	 * 获取code
	 * @auther chenlf3
	 * @date 2015年9月7日 上午10:06:28
	 * @param context
	 * @return
	 * @throws NameNotFoundException
	 */
	public static String getCode(Context context) throws NameNotFoundException {
		StringBuilder res = new StringBuilder("android*");//客户端类型
		res.append(android.os.Build.MODEL).append("*");//终端型号
		res.append(android.os.Build.VERSION.RELEASE).append("*");//操作系统版本
		res.append(context.getPackageManager().getPackageInfo(context.getPackageName(), 0)).append("*");//客户端版本号
		res.append(Secure.getString(context.getContentResolver(), Secure.ANDROID_ID));//硬件序列号
		return res.toString();
	}
	
	/**
	 * 检查参数是否合法
	 * @auther chenlf3
	 * @date 2015年9月6日 下午12:23:17
	 * @param params
	 * @return
	 */
	public static boolean isLegalParams(String...params) {
		if(params == null || params.length==0) return false;
		for(String param:params) {
			if(param == null || "".equals(param.trim())) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 将map拼接为一个字符串,用于获取get请求的参数
	 * @auther chenlf3
	 * @date 2015年9月1日 下午6:09:47
	 * @param map
	 * @return
	 */
	public static String getParams(Map<String,String> map) {
		if(map == null) return null;
		Set<String> keys = map.keySet();
		if(keys.size() == 0) return null;
		StringBuilder temp = new StringBuilder("?");
		for(String key:keys) {
			try {
				temp.append(key).append("=").append(map.get(key)==null?"":URLEncoder.encode(map.get(key),"utf-8")).append("&");
				//temp.append(key).append("=").append(map.get(key)).append("&");
			} catch (UnsupportedEncodingException e) {
				 //TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String res = temp.toString();
		if(res.endsWith("&")) {
			return res.substring(0, res.length()-1);
		} else {
			return res;
		}
	}
	
	public static String getParams(JSONObject params) throws JSONException {
		if(params==null || params.length()<=0) return "";
		Iterator<String> it = params.keys();
		StringBuilder temp = new StringBuilder("?");
		while(it.hasNext()) {
			String key = (String) it.next();  
            String value = params.getString(key);
			try {
				temp.append(key).append("=").append(value==null?"":URLEncoder.encode(value,"UTF-8")).append("&");
			} catch (UnsupportedEncodingException e) {
				 //TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String res = temp.toString();
		if(res.endsWith("&")) {
			return res.substring(0, res.length()-1);
		} else {
			return res;
		}
	}
	
	/**
	 * 获取存储文件中的值
	 * @auther chenlf3
	 * @date 2015年8月31日 下午3:27:37
	 * @param context
	 * @param key
	 * @return
	 */
	public static String getSPString(Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		return sp.getString(key, null);
	}
	
	/**
	 * 获取存储文件中的值
	 * @auther chenlf3
	 * @date 2015年8月31日 下午3:27:37
	 * @param context
	 * @param key
	 * @return
	 */
	public static int getSPInt(Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		return sp.getInt(key, 0);
	}
	
	/**
	 * 获取存储文件中的值
	 * @auther chenlf3
	 * @date 2015年8月31日 下午3:27:50
	 * @param context
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getSPString(Context context, String key, String defaultValue) {
		SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		return sp.getString(key, defaultValue);
	}
	
	/**
	 * 获取存储文件中的值
	 * @auther chenlf3
	 * @date 2015年8月31日 下午3:27:50
	 * @param context
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static int getSPInt(Context context, String key, int defaultValue) {
		SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		return sp.getInt(key, defaultValue);
	}
	
	/**
	 * 添加存储文件中的值
	 * @auther chenlf3
	 * @date 2015年8月31日 下午3:29:57
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void addSP(Context context, String key, String value) {
		SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	/**
	 * 添加存储文件中的值
	 * @auther chenlf3
	 * @date 2015年8月31日 下午3:29:57
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void addSP(Context context, String key, int value) {
		SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putInt(key, value);
		editor.commit();
	}
	
	/**
	 * 添加存储文件中的值
	 * @auther chenlf3
	 * @date 2015年8月31日 下午3:33:58
	 * @param context
	 * @param map
	 */
	public static void addSPSString(Context context, Map<String,String> map) {
		SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		Editor editor = sp.edit();
		Set<String> keys = map.keySet();
		for(String key:keys) {
			editor.putString(key, map.get(key));
		}
		editor.commit();
	}
	
	/**
	 * 添加存储文件中的值
	 * @auther chenlf3
	 * @date 2015年8月31日 下午3:33:58
	 * @param context
	 * @param map
	 */
	public static void addSPSInt(Context context, Map<String,Integer> map) {
		SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		Editor editor = sp.edit();
		Set<String> keys = map.keySet();
		for(String key:keys) {
			editor.putInt(key, map.get(key));
		}
		editor.commit();
	}

	/**
	 * 把输入流内容转化为字符串
	 * @auther chenlf3
	 * @date 2015年8月6日 上午9:51:43
	 * @param is
	 * @return
	 */
	public static String readInputStream(InputStream is) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			int len = 0;
			byte[] buffer = new byte[1024];
			while((len = is.read(buffer)) != -1) {
				os.write(buffer,0,len);
			}
			is.close();
			os.close();
			byte[] result = os.toByteArray();
			return new String(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String readInputStream(InputStream is,String encode) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			int len = 0;
			byte[] buffer = new byte[1024];
			while((len = is.read(buffer)) != -1) {
				os.write(buffer,0,len);
			}
			is.close();
			os.close();
			byte[] result = os.toByteArray();
			return new String(result,encode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 将文件转化为字节数组
	 * @auther chenlf3
	 * @date 2015年11月10日 下午12:08:34
	 * @param file
	 * @return
	 */
	public static byte[] getBytes(File file) {
		if(file == null || !file.exists()) {
			return null;
		}
		try {
			InputStream is = new FileInputStream(file);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buffer = new byte[2048];
			int i = -1;
			while((i=is.read(buffer))!=-1) {
				out.write(buffer,0,i);
			}
			is.close();
			out.close();
			return out.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 将输入流存储为本地文件,读取pdf流，存储为pdf文件
	 * @auther chenlf3
	 * @date 2015年8月6日 上午9:51:58
	 * @param in
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static boolean saveTo(InputStream in,String path) throws IOException {
		File file = new File(path);
		OutputStream os = new FileOutputStream(file);
		byte[] buffer = new byte[1024];
		int i = -1;
		while((i=in.read(buffer)) != -1) {
			os.write(buffer,0,i);
		}
		os.flush();
		in.close();
		os.close();
		return true;
	}
	
	/**
	 * 将内容追加到指定文件
	 * @auther chenlf3
	 * @date 2016年5月7日 下午5:28:18
	 * @param content
	 * @param filePath
	 * @return
	 */
	public static boolean saveAppendTo(String content, String dir, String fileName) {
		/** 目录若不存在，创建目录 */
		File dirFile = new File(dir);
		if(!dirFile.exists() || !dirFile.isDirectory()) {
			dirFile.mkdirs();
		}
		/** 文件若不存在创建文件 */
		File file = new File(dir+File.separator+fileName);
		if(!file.exists() || !file.isFile()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		/** 将字符串写入文件 */
		try {
			FileOutputStream fos = new FileOutputStream(file,true);
			fos.write(content.getBytes());
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 追加
	 * @auther chenlf3
	 * @date 2016年6月2日 上午10:29:10
	 * @param data
	 * @param dir
	 * @param fileName
	 * @return
	 */
	public static boolean saveAppendTo(byte[] data, String dir, String fileName) {
		/** 目录若不存在，创建目录 */
		File dirFile = new File(dir);
		if(!dirFile.exists() || !dirFile.isDirectory()) {
			dirFile.mkdirs();
		}
		/** 文件若不存在创建文件 */
		File file = new File(dir+File.separator+fileName);
		if(!file.exists() || !file.isFile()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		try {
			FileOutputStream out = new FileOutputStream(file,true);
			out.write(data);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 将byte数组保存到文件
	 * @auther chenlf3
	 * @date 2015年8月21日 下午5:39:25
	 * @param data
	 * @param path
	 * @return
	 */
	public static boolean saveTo(byte[] data, String path) {
		try {
			File file = new File(path);
			FileOutputStream out = new FileOutputStream(file);
			out.write(data);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 拷贝文件
	 * @auther chenlf3
	 * @date 2016年4月1日 下午12:14:21
	 * @param file1
	 * @param file2
	 * @return
	 */
	public static boolean copyFile(String filePath1, String filePath2) {
		File file1 = new File(filePath1);
		if(file1.exists() && file1.isFile()) {
			try {
				InputStream in = new FileInputStream(file1);;
				boolean res = saveTo(in, filePath2);
				return res;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	/**
	 * 检查手机是否存在照相机
	 * @auther chenlf3
	 * @date 2015年8月6日 上午9:52:13
	 * @param context
	 * @return
	 */
	public static boolean checkCameraHardware(Context context) {
		if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			//this device has a camera
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * amr音频文件拼接(list里面为暂停录音所产生的几段录音文件的名字，中间几段文件的减去前面的6个字节头文件)
	 * @auther chenlf3
	 * @date 2015年8月11日 下午5:29:45
	 * @param filePaths
	 * @param finalFilePath
	 * @return
	 * @throws IOException 
	 */
	public static boolean mergeMusicFile(List<String> filePaths, String finalFilePath) throws IOException {
		if(filePaths == null || filePaths.size() <= 0) {
			System.out.println("filePaths为空！");
			return false;
		}
		/** 检验文件是否全都存在 */
		for(int i=0;i<filePaths.size();i++) {
			File file = new File(filePaths.get(i));
			if(!file.exists()) {
				System.out.println(filePaths.get(i)+"文件不存在！");
				return false;
			}
		}
		OutputStream os = new FileOutputStream(finalFilePath);
		for(int i=0;i<filePaths.size();i++) {
			InputStream is = new FileInputStream(filePaths.get(i));
			byte[] buffer = new byte[is.available()];
			int length = buffer.length;
			if(i==0) {
				while((is.read(buffer)) != -1) {
					os.write(buffer,0,length);
				}
			} else {
				while((is.read(buffer)) != -1) {
					os.write(buffer,6,length-6);
				}
			}
			os.flush();
			is.close();
		}
		os.close();
		return true;
	}
	
	/**
	 * 获取文件夹下的子文件列表
	 * @auther chenlf3
	 * @date 2015年8月21日 下午3:08:36
	 * @param absolutePath
	 * @return
	 */
	public static List<Map<String,String>> getFileDir(List<Map<String,String>> list, String absolutePath) {
		if(list == null) {
			list = new ArrayList<Map<String,String>>();
		}
		File file = new File(absolutePath);
		if(!file.exists() || file.isFile()) return list;
		File[] files = file.listFiles();
		if(files == null || files.length<=0) return list;
		for(File file1:files) {
			if(file1.getName().endsWith(".pdf") || file1.isDirectory()) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("fileName", file1.getName());
				map.put("absolutePath", file1.getAbsolutePath());
				list.add(map);
			}
		}
		return list;
	}
	
	/**
	 * 获取文件夹下文件列表
	 * @auther chenlf3
	 * @date 2017年2月13日 上午10:44:57
	 * @param absolutePath
	 * @return
	 */
	public static List<Map<String,String>> getFileList(String absolutePath) {
		List list = new ArrayList<Map<String,String>>();
		File file = new File(absolutePath);
		if(!file.exists() || file.isFile()) return list;
		File[] files = file.listFiles();
		if(files == null || files.length<=0) return list;
		for(File file1:files) {
			if(file1.isFile() && (file1.getName().endsWith(".pdf") || file1.getName().endsWith(".aip"))) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("fileName", file1.getName());
				map.put("absolutePath", file1.getAbsolutePath());
				list.add(map);
			}
		}
		return list;
	}

	/**
	 * 将assets下面的某个文件夹的内容(只包含文件不包含文件夹内容)
	 * @auther chenlf3
	 * @date 2015年10月26日 下午5:04:12
	 * @param originPath 如"dianju\camera"
	 * @param terminiPath 如Environment.getExternalStorageDirectory()+File.separator+"dianju"+File.separator+sqs.aip
	 * @throws IOException 
	 */
	public static void copyDJFileFromAssets(Context context,String originPath, String terminiPath) throws IOException {
		File dir = new File(terminiPath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		String names[] = context.getAssets().list(originPath);
		if(names != null && names.length>0) {
			for(String name:names) {
				if(name != null && !"".equals(name)) {
					if(name.contains(".")) {//文件
						InputStream in = context.getAssets().open(originPath+File.separator+name);
						/** 拷贝文件 */
						saveTo(in, terminiPath+File.separator+name);
					}
				}
			}
		}
	}
	
	/**
	 * 将assets下面的某个文件夹的内容(包含文件和文件夹内容)
	 * @auther chenlf3
	 * @date 2016年1月19日 下午6:07:33
	 * @param context
	 * @param originPath
	 * @param terminiPath
	 * @throws IOException
	 */
	public static void copyDJDirAndFileAfterAssets(Context context,String originPath, String terminiPath) throws IOException {
		File dir = new File(terminiPath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		String names[] = context.getAssets().list(originPath);
		if(names != null && names.length>0) {
			for(String name:names) {
				if(name != null && !"".equals(name)) {
					if(name.contains(".")) {//文件
						InputStream in = context.getAssets().open(originPath+File.separator+name);
						/** 拷贝文件 */
						saveTo(in, terminiPath+File.separator+name);
					} else {//目录,进行遍历复制
						copyDJDirAndFileAfterAssets(context,originPath+File.separator+name,terminiPath+File.separator+name);
					}
				}
			}
		}
	}
	
	/**
	 * 获取时间
	 * @auther chenlf3
	 * @date 2016年1月19日 下午6:08:16
	 * @param timeType
	 * @return
	 */
	public static String getTime(String timeType) {
		SimpleDateFormat df = new SimpleDateFormat(timeType);//设置日期格式
		return df.format(new Date());
	}
	
	/**
	 * 复制map中的内容
	 * @auther chenlf3
	 * @date 2016年1月19日 下午6:13:59
	 * @param map
	 * @return
	 */
	public static Map getSameContent(Map map) {
		if(map == null) return null;
		Set keys = map.keySet();
		if(keys == null || keys.size()==0) return null;
		Map res = new HashMap();
		for(Object key:keys) {
			res.put(key, map.get(key));
		}
		return res;
	}
	
	/**
	 * 获取MD5签名信息
	 * @auther chenlf3
	 * @date 2016年1月26日 上午11:36:31
	 * @param content
	 * @return
	 */
	public static String getMD5String(String content) {
		if(content == null || "".equals(content.trim())) return "";
		try {
			/** 获取MD5摘要算法的MessageDigest */
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			/** 更新摘要 */
			messageDigest.update(content.getBytes());
			/** 获得密文 */
			byte[] cipherData = messageDigest.digest();
			/** 将密文转为十六进制返回 */
			String res = switch2to16s(cipherData);
		    return res;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 删除某个文件
	 * @auther chenlf3
	 * @date 2016年2月1日 下午12:03:48
	 * @param filePath
	 */
	public static boolean delFile(String filePath) {
		File file = new File(filePath);
		if(file.exists() && file.isFile()) {
			file.delete();
			return true;
		}
		return true;
	}
	
	/**
	 * 删除目录
	 * @auther chenlf3
	 * @date 2016年4月1日 上午10:55:12
	 * @param dirPath
	 */
	public static boolean delDir(File dirFile) {
		if(!dirFile.exists() || !dirFile.isDirectory()) return true;
		File[] lists = dirFile.listFiles();
		if(lists == null || lists.length==0) {//目录无数据，删除目录
			if(!dirFile.delete()) {//删除失败，停止删除任务
				return false;
			}
		} else {//目录有数据，先删除数据再删除目录
			for(File file1:lists) {
				if(file1.exists() && file1.isFile()) {//删除其中文件
					if(!file1.delete()) {
						return false;
					}
				} else if(file1.exists() && file1.isDirectory()) {//删除其中文件夹
					if(!delDir(file1)) {
						return false;
					}
				} else {
					return false;
				}
			}
			/** 再删除完文件夹内部数据之后，删除该文件夹 */
			if(!dirFile.delete()) {//删除失败，停止删除任务
				return false;
			}
		}
		return true;
	}
	
	public static void cleanDir(String dirPath) {
		File dirFile = new File(dirPath);
		if(!dirFile.exists() || !dirFile.isDirectory()) return;
		File[] lists = dirFile.listFiles();
		if(lists == null || lists.length==0) {//目录无数据，删除目录
			return;
		} else {//目录有数据，先删除数据再删除目录
			for(File file1:lists) {
				if(file1.exists() && file1.isFile()) {//删除其中文件
					file1.delete();
				} else if(file1.exists() && file1.isDirectory()) {//删除其中文件夹
					delDir(file1);
				} else {}
			}
		}
	}
	
	/**
	 * 复制目录
	 * @auther chenlf3
	 * @date 2016年4月1日 上午11:45:45
	 * @param originPath 源目录
	 * @param terminiPath 目标目录
	 * @throws IOException
	 */
	public static boolean copyDir(String originPath, String terminiPath) {
		File originFile = new File(originPath);
		if(!originFile.exists() || !originFile.isDirectory()) return false;
		File dir = new File(terminiPath+File.separator+originFile.getName());
		if(!dir.exists()) {
			dir.mkdirs();
		}
		File[] list = originFile.listFiles();
		for(File file1:list) {
			if(file1.isDirectory()) {//目录
				if(!copyDir(file1.getAbsolutePath(),terminiPath+File.separator+originFile.getName())) {
					return false;
				}
			} else {//文件
				if(!copyFile(file1.getAbsolutePath(), terminiPath+File.separator+originFile.getName()+File.separator+file1.getName())) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 *  方法一：
	 *	setCanceledOnTouchOutside(false);调用这个方法时，按对话框以外的地方不起作用。按返回键还起作用
	 *	方法二：
	 *	setCancelable(false);调用这个方法时，按对话框以外的地方不起作用。按返回键也不起作用
	 * 弹出提示框
	 * @auther chenlf3
	 * @date 2016年3月7日 上午11:15:59
	 * @param context
	 * @param title
	 * @param content
	 * @param positiveListener
	 * @param negativeListener
	 * @return
	 */
	public static AlertDialog popDialog(Context context, String title, String content, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
		Builder builder = new Builder(context);
		if(!TextUtils.isEmpty(title)) {
			builder.setTitle(title);//设置提示框的标题
		}
		if(!TextUtils.isEmpty(content)) {
			builder.setMessage(content);//提示内容
		}
		if(positiveListener!=null) {
			builder.setPositiveButton("确定", positiveListener);
		}
		if(negativeListener!=null) {
			builder.setNegativeButton("取消", negativeListener);
		}
		AlertDialog dialog = builder.show();
		return dialog;
	}
	
	/**
	 * 
	 * @auther chenlf3
	 * @date 2016年5月30日 上午10:50:32
	 * @param context
	 * @param title
	 * @param content
	 * @param ok
	 * @param cancel
	 * @param positiveListener
	 * @param negativeListener
	 * @return
	 */
	public static AlertDialog popDialog(Context context, String title, String content, String ok, String cancel, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
		Builder builder = new Builder(context);
		if(!TextUtils.isEmpty(title)) {
			builder.setTitle(title);//设置提示框的标题
		}
		if(!TextUtils.isEmpty(content)) {
			builder.setMessage(content);//提示内容
		}
		if(!TextUtils.isEmpty(ok)) {
			builder.setPositiveButton(ok, positiveListener);
		}
		if(!TextUtils.isEmpty(cancel)) {
			builder.setNegativeButton(cancel, negativeListener);
		}
		AlertDialog dialog = builder.show();
		return dialog;
	}
	
	/*
	 * 弹出带布局的弹出框
	 */
	public static AlertDialog popDialog(Context context, String title, String ok, String cancel, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener, View view) {
		Builder builder = new Builder(context);
		if(!TextUtils.isEmpty(title)) {
			builder.setTitle(title);//设置提示框的标题
		}
		builder.setView(view);
		if(!TextUtils.isEmpty(ok)) {
			builder.setPositiveButton(ok, positiveListener);
		}
		if(!TextUtils.isEmpty(cancel)) {
			builder.setNegativeButton(cancel, negativeListener);
		}
		AlertDialog dialog = builder.show();
		return dialog;
	}
	
	public static AlertDialog popDialog(Context context, String title, String content, String ok, String middle, String cancel, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener middleListener, DialogInterface.OnClickListener negativeListener) {
		Builder builder = new Builder(context);
		if(!TextUtils.isEmpty(title)) {
			builder.setTitle(title);//设置提示框的标题
		}
		if(!TextUtils.isEmpty(content)) {
			builder.setMessage(content);//提示内容
		}
		if(!TextUtils.isEmpty(ok)) {
			builder.setPositiveButton(ok, positiveListener);
		}
		if(!TextUtils.isEmpty(middle)) {
			builder.setNeutralButton(middle, middleListener);
		}
		if(!TextUtils.isEmpty(cancel)) {
			builder.setNegativeButton(cancel, negativeListener);
		}
		AlertDialog dialog = builder.show();
		return dialog;
	}
	
	/**
	 * 弹出吐司
	 * @auther chenlf3
	 * @date 2016年3月7日 上午11:52:18
	 * @param context
	 * @param content
	 */
	public static void popToast(Context context,String content) {
		Toast.makeText(context, content, Toast.LENGTH_LONG).show();
	}
	
	/**
	 * 清空文件夹下所有文件
	 * @auther chenlf3
	 * @date 2016年3月11日 下午4:23:55
	 * @param dirPath
	 */
	public static void cleanDirFile(String dirPath) {
		File file = new File(dirPath);
		if(file.exists() && file.isDirectory()) {
			File[] files = file.listFiles();
			for(File file1:files) {
				if(file1.isFile()) {
					file1.delete();
				}
			}
		}
	}
	
	/**
	 * 将byte[]转化为字符窜
	 * @auther chenlf3
	 * @date 2016年3月23日 下午2:42:50
	 * @param b
	 * @param leng
	 * @return
	 */
	public static String bytesToHex(byte[] data) {
		if(data == null || data.length<=0) return "";
		StringBuffer strbuf = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			strbuf.append("0123456789abcdef".charAt(((byte) ((data[i] & 0xf0) >> 4))));
			strbuf.append("0123456789abcdef".charAt((byte) (data[i] & 0x0f)));
		}
		return strbuf.toString();
	}
	
	/**
	 * 16进制转化为byte
	 * @auther chenlf3
	 * @date 2016年3月23日 下午5:30:07
	 * @param data 必须为0x开头的16进制
	 * @return
	 */
	public static Byte switch16to2(String data) {
		String range = "0123456789abcdef";
		if(data.length() == 4 && data.startsWith("0x")) {
			String mainData = data.substring(2);
			try {
				return Integer.valueOf(mainData,16).byteValue();
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}
	
	/**
	 * 根据16进制字符串，获取16进制数组
	 * @auther chenlf3
	 * @date 2016年8月6日 下午3:19:51
	 * @param data
	 * @return
	 */
	public static byte[] switch16to2s(String data) {
		String range = "0123456789abcdef";
		if(data != null && (!"".equals(data)) && data.length()%2 == 0) {
			int count = data.length()/2;
			byte[] res = new byte[count];
			for(int i=0;i<count;i++) {
				String ceilData = data.substring(2*i, 2*i+2);
				res[i] = Integer.valueOf(ceilData,16).byteValue();
			}
			return res;
		}
		return null;
	}
	
	public static String switch2to16s(byte[] data) {
		StringBuffer strbuf = new StringBuffer("");
		if(data == null && data.length<=0) return null;
		for (int i = 0; i < data.length; i++) {
			strbuf.append("0123456789abcdef".charAt(((byte) ((data[i] & 0xf0) >> 4))));
			strbuf.append("0123456789abcdef".charAt((byte) (data[i] & 0x0f)));
		}
		return strbuf.toString();
	}
	
	public static String switch2to16s(byte data) {
		StringBuffer strbuf = new StringBuffer("");
		strbuf.append("0123456789abcdef".charAt(((byte) ((data & 0xf0) >> 4))));
		strbuf.append("0123456789abcdef".charAt((byte) (data & 0x0f)));
		return strbuf.toString();
	}
	
	/**
	 * 调整声音大小
	 * @auther chenlf3
	 * @date 2016年3月24日 上午11:55:23
	 * @param context
	 * @param value
	 */
	public static void adjustVolume(Context context, int value) {
		AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);//音量
		int setValue = 0;
		/** 获取声音最大值 */
		int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		if(value<=0) {
			setValue = 0;
		} else if(value >0 && value<maxVolume) {
			setValue = value;
		} else {
			setValue = maxVolume;
		}
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, setValue, audioManager.FLAG_PLAY_SOUND);
	}
	
	/**
	 * 
	 * @auther chenlf3
	 * @date 2016年3月12日 下午5:54:02
	 * @param context
	 * @param range 总档位
	 * @param value 设定档位，从1档位开始
	 */
	public static void adjustVolume(Context context, int range, int value) {
		AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);//音量
		int setValue = 0;
		/** 获取声音最大值 */
		int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		if(value<=0) {
			setValue = 0;
		} else if(value >0 && value<maxVolume) {
			int cell = maxVolume/range;//每个档位大小
			int tempValue = 1;
			if(value <1) {
				tempValue = 1;
			} else if(value>range) {
				tempValue = range;
			} else {
				tempValue = value;
			}
			setValue = tempValue * cell;
		} else {
			setValue = maxVolume;
		}
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, setValue, audioManager.FLAG_PLAY_SOUND);
	}
	
	/**
	 * 获取可用空间，单位M
	 * @auther chenlf3
	 * @date 2016年3月24日 下午3:15:44
	 * @return
	 */
	public static String getAvailableSpace(Context context) {
		/** 获取sd卡目录文件 */
		File path = Environment.getExternalStorageDirectory();
		//构造sd卡文件系统
		StatFs stat = new StatFs(path.getPath());
		//获取sd卡存储块的大小
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();//获取sd卡可用存储块数目
		long availableSize = availableBlocks * blockSize;//可用空间大小
		long res = availableSize/(1024*1024);
		//String strAvailableSize = Formatter.formatFileSize(context, availableSize);
		return ""+res;
	}
	
	/**
	 * 获取手机屏幕宽高
	 * @auther chenlf3
	 * @date 2016年4月14日 下午4:45:14
	 * @return
	 */
	public static Point getScreenSize(Activity context) {
		Point point = new Point();
		WindowManager manager = context.getWindowManager();
		int width = manager.getDefaultDisplay().getWidth();
		int height = manager.getDefaultDisplay().getHeight();
		point.set(width, height);
		return point;
	}
	
	/**
	 * 获取蓝牙状态(1-打开；0-关闭；-1-没有蓝牙模块)
	 * @auther chenlf3
	 * @date 2016年6月3日 下午1:59:45
	 * @return
	 */
	public static String getBlueToothState() {
		BluetoothAdapter mBTAdapter = BluetoothAdapter.getDefaultAdapter();
		if(mBTAdapter == null) return "-1";
		if(mBTAdapter.isEnabled()) {
			return "1";
		} else {
			return "0";
		}
	}
	
	/**
	 * 打开设备蓝牙
	 * @auther chenlf3
	 * @date 2016年6月3日 下午2:06:23
	 */
	public static void openBlueTooth(Activity context) {
		if("0".equals(getBlueToothState())) {
			Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			context.startActivityForResult(enableIntent, 3);
		}
	}
	
	/**
	 * 获取汉字的个数
	 * @auther chenlf3
	 * @date 2016年5月26日 下午5:49:02
	 * @param content
	 * @return
	 */
	public static int getChinCount(String content) {
		if(content == null || content.length()<=0) return 0;
		int count = 0;
		String regEx = "[\\u4e00-\\u9fa5]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(content);
		while (m.find()) {
			for (int i = 0; i <= m.groupCount(); i++) {
				count = count + 1;
			}
		}
		return count;
	}
	
	/**
	 * 获取android设备唯一标示
	 * @auther chenlf3
	 * @date 2016年7月26日 下午4:46:54
	 * @param context
	 * @return
	 */
	public static String getUUID(Context context){
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);   
        String tmDevice, tmSerial, tmPhone, androidId;   
        /*tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();   */
		tmDevice = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
		tmSerial = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
        androidId = "" + Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String uniqueId = deviceUuid.toString();
        return uniqueId;
	}
	
	/**
	 * 获取透明色
	 * @auther chenlf3
	 * @date 2017年1月3日 下午6:14:41
	 * @param color
	 * @param alpha
	 * @return
	 */
	public static int getTransparentColor(String color, int alpha) {
		if(TextUtils.isEmpty(color)) {
			return -1;
		}
		String temp = "";
		if(color.startsWith("#") && color.length() == 7) {
			temp = color.substring(1);
		} else if((!color.startsWith("#")) && color.length() == 6) {
			temp = color;
		} else {
			return -1;
		}
		String clfAlpha = Integer.toHexString(alpha);
		if(clfAlpha.length()==1) clfAlpha = "0"+clfAlpha;
		int clfColor = Color.parseColor("#"+clfAlpha+temp);
		return clfColor;
	}
	
	public static long getRandom() {
		long time = System.currentTimeMillis();
		int randNum = (int) (Math.random() * 1000);
		long res = Long.parseLong(""+time+randNum);
		return res;
	}
	
	public static String upload(String host,File file,Map<String,String> params){
		String TAG = "uploadFile";
	    int TIME_OUT = 15*1000; //超时时间
	    String CHARSET = "utf-8"; //设置编码
	    String PREFIX = "--";
	    String LINE_END = "\r\n";
		
		String BOUNDARY = UUID.randomUUID().toString(); //边界标识 随机生成 String PREFIX = "--" , LINE_END = "\r\n";
        String CONTENT_TYPE = "multipart/form-data"; //内容类型
        try {
        	if(file==null || (!file.exists()) || file.length()<=0) return "fail-无效文件";
            URL url = new URL(host);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置每次传输的流大小，可以有效防止手机因为内存不足崩溃
            // 此方法用于在预先不知道内容长度时启用没有进行内部缓冲的 HTTP请求正文的流。
            conn.setChunkedStreamingMode(51200);
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setRequestMethod("POST"); //请求方式
            conn.setRequestProperty("Charset", CHARSET);//设置编码
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);
            conn.setDoInput(true); //允许输入流
            conn.setDoOutput(true); //允许输出流
            conn.setUseCaches(false); //不允许使用缓存
            /** * 当文件不为空，把文件包装并且上传 */
            OutputStream outputSteam=conn.getOutputStream();
            DataOutputStream dos = new DataOutputStream(outputSteam);
            if(params!=null){//根据格式，开始拼接文本参数
            	// 首先组拼文本类型的参数
            	StringBuffer sb = new StringBuffer();
                sb.append(LINE_END);
                for(Map.Entry<String,String> entry:params.entrySet()){
                    sb.append(PREFIX).append(BOUNDARY).append(LINE_END);//分界符
                    sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + LINE_END);
                    sb.append("Content-Type: text/plain; charset=" + CHARSET + LINE_END);
                    sb.append("Content-Transfer-Encoding: 8bit" + LINE_END);
                    sb.append(LINE_END);
                    sb.append(entry.getValue());
                    sb.append(LINE_END);//换行！
                }
                dos.write(sb.toString().getBytes(CHARSET));
            }
            
            
            // 构建发送字符串数据
            StringBuffer filesb = new StringBuffer();
            filesb.append(PREFIX);//开始拼接文件参数
            filesb.append(BOUNDARY);
            filesb.append(LINE_END);
            /**
             * 这里重点注意：
             * name里面的值为服务器端需要key 只有这个key 才可以得到对应的文件
             * filename是文件的名字，包含后缀名的 比如:abc.png
             */
            filesb.append("Content-Disposition: form-data; name=\"upoafile\"; filename=\""+file.getName()+"\""+LINE_END);
            filesb.append("Content-Type: application/octet-stream; charset="+CHARSET+LINE_END);
            filesb.append(LINE_END);
            // 写入到输出流中
            dos.write(filesb.toString().getBytes());
            // 将文件读入输入流中
            InputStream is = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int len = 0;
            while((len=is.read(bytes))!=-1){
                dos.write(bytes, 0, len);
            }
            is.close();
            dos.write(LINE_END.getBytes());//一定还有换行
            byte[] end_data = (PREFIX+BOUNDARY+PREFIX+LINE_END).getBytes();
            dos.write(end_data);
            dos.flush();
            /**
             * 获取响应码 200=成功
             * 当响应成功，获取响应的流
             */
            int code = conn.getResponseCode();
            if(code != 200) {
            	return "fail-服务器返回失败,code="+code;
            }
            InputStream bis = conn.getInputStream();
    		ByteArrayOutputStream bos = new ByteArrayOutputStream();
    		len = 0;
    		byte[] buffer = new byte[1024];
    		while((len = bis.read(buffer)) != -1) {
    			bos.write(buffer,0,len);
    		}
    		bis.close();
    		bos.close();
    		byte[] result = bos.toByteArray();
    		String res = new String(result);
            return res;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "fail-exception="+e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return "fail-exception="+e.getMessage();
        }
    }
	
	public static void dlog(String...params) {
		if(params == null || params.length==0) {
			Log.d("chenlongfei", "clfutil_log_null");
			return;
		}
		StringBuilder res = new StringBuilder();
		for(int i=0;i<params.length;i++) {
			if(params[i] == null) {
				res.append("params[").append(i).append("]=null;");
			}else if("".equals(params[i].trim())) {
				res.append("params[").append(i).append("]=;");
			} else {
				res.append("params[").append(i).append("]=").append(params[i]).append(";");
			}
		}
		Log.d("chenlongfei", "clfutil_log:"+res.toString());
	}
	
	/**
	 * 使用手机应用打开文件
	 * @auther chenlf3
	 * @date 2016年5月30日 上午10:50:32
	 * @param context
	 * @param file
	 * @return
	 */
	public static int openFile(Context context,File file) {
		try {
			Intent intent = new Intent();   
	        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   
	        //设置intent的Action属性   
	        intent.setAction(Intent.ACTION_VIEW);   
	        //获取文件file的MIME类型   
	        String type = getMIMEType(file);   
	        //设置intent的data和Type属性。   
	        intent.setDataAndType(Uri.fromFile(file), type);   
	        //跳转   
	        context.startActivity(intent);
	        return 1;
	        //Intent.createChooser(intent, "请选择对应的软件打开该附件！");
		} catch(ActivityNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	private static String getMIMEType(File file) {
		String type="*/*";   
        String fName = file.getName();   
        //获取后缀名前的分隔符"."在fName中的位置。   
        int dotIndex = fName.lastIndexOf(".");   
        if(dotIndex < 0){   
            return type;   
        }   
        /* 获取文件的后缀名*/   
        String end=fName.substring(dotIndex,fName.length()).toLowerCase();   
        if(end=="")return type;   
        //在MIME和文件类型的匹配表中找到对应的MIME类型。   
        for(int i=0;i<MIME_MapTable.length;i++){
        	if(end.equals(MIME_MapTable[i][0])) type = MIME_MapTable[i][1];
        }
        return type; 
	}
	
	
	public static void shareFile(Context context, File file) {
    	Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("application/octet-stream");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));  
        context.startActivity(Intent.createChooser(intent, "Mail Chooser"));
    }
	
	// 調用系統方法分享文件
    public static void shareFileToMail(Context context, File file) {
    	Intent intent = new Intent(Intent.ACTION_SEND);
        //收件人  
        intent.putExtra(Intent.EXTRA_EMAIL,new String[] {"pop1030123@163.com"});
        // 主题  
        intent.putExtra(Intent.EXTRA_SUBJECT, "note.xml");
        // 正文  
        intent.putExtra(Intent.EXTRA_TEXT,"this is test extra.");
        intent.setType("application/octet-stream");
        //当无法确认发送类型的时候使用如下语句  
        //intent.setType("*/*");  
        //当没有附件,纯文本发送时使用如下语句  
        //intent.setType(“plain/text”);
        //i.setType("text/plain"); //模拟器请使用这行  
        //i.setType("message/rfc822"); // 真机上使用这行
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));  
        context.startActivity(Intent.createChooser(intent, "Mail Chooser"));  
    }
    
    public static void shareFileToQQ(Context context, File file) {
    	Intent share = new Intent(Intent.ACTION_SEND);
    	ComponentName comp = new ComponentName("com.tencent.mobileqq","com.tencent.mobileqq.activity.JumpActivity");
    	share.setComponent(comp);
        share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        share.setType("*/*");//此处可发送多种文件
        share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.startActivity(Intent.createChooser(share, "分享文件"));
    }
    
    public static void shareFileToWeChat(Context context, File file) {
    	Intent share = new Intent(Intent.ACTION_SEND);
    	ComponentName comp = new ComponentName("com.tencent.mm","com.tencent.mm.ui.tools.ShareImgUI");
    	share.setComponent(comp);
        share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        share.setType("*/*");//此处可发送多种文件
        share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.startActivity(Intent.createChooser(share, "分享文件"));
    }
	
	/**
	 * 获取不重复的文件名字
	 * @auther chenlf3
	 * @date 2018年5月29日 下午2:27:42
	 * @param dir
	 * @param fileName
	 * @return
	 */
	public static String getNoRepeatName(String dir, String fileName) {
		if(fileName.contains(".")) {
			int i = fileName.lastIndexOf(".");
			String fileHead = fileName.substring(0,i);
			String fileTail = fileName.substring(i);
			int count = 0;
			File file = new File(dir, fileName);
			while(file.exists()) {
				count++;
				String mFileName = fileHead+count+fileTail;
				file = new File(dir, mFileName);
			}
			String newFileName = fileHead+count+fileTail;
			if(count==0) {
				newFileName = fileHead+fileTail;
			}
			return newFileName;
		} else {
			int count = 0;
			File file = new File(dir, fileName);
			while(file.exists()) {
				count++;
				String mFileName = fileName+count;
				file = new File(dir, mFileName);
			}
			String newFileName = fileName+count;
			if(count==0) {
				newFileName = fileName;
			}
			return newFileName;
		}
	}
	
	/**
	 * 压缩文件
	 * 
	 * @param srcfile
	 */
	public static void zipFiles(File srcfile, File desFile) {
 
		ZipOutputStream out = null;
		try {
			out = new ZipOutputStream(new FileOutputStream(desFile));
			if(srcfile.isFile()){
				zipFile(srcfile, out, "");
			} else{
				File[] list = srcfile.listFiles();
				for (int i = 0; i < list.length; i++) {
					compress(list[i], out, "");
				}
			}
			System.out.println("压缩完毕");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	/**
	 * 压缩文件夹里的文件
	 * 起初不知道是文件还是文件夹--- 统一调用该方法
	 * @param file
	 * @param out
	 * @param basedir
	 */
	private static void compress(File file, ZipOutputStream out, String basedir) {
		/* 判断是目录还是文件 */
		if (file.isDirectory()) {
			zipDirectory(file, out, basedir);
		} else {
			zipFile(file, out, basedir);
		}
	}
	
	/**
	 * 压缩单个文件
	 * 
	 * @param srcfile
	 */
	public static void zipFile(File srcfile, ZipOutputStream out, String basedir) {
		if (!srcfile.exists())
			return;
 
		byte[] buf = new byte[1024];
		FileInputStream in = null;
 
		try {
			int len;
			in = new FileInputStream(srcfile);
			out.putNextEntry(new ZipEntry(basedir + srcfile.getName()));
 
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.closeEntry();
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 压缩文件夹
	 * @param dir
	 * @param out
	 * @param basedir
	 */
	public static void zipDirectory(File dir, ZipOutputStream out, String basedir) {
		if (!dir.exists())
			return;
 
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			/* 递归 */
			compress(files[i], out, basedir + dir.getName() + "/");
		}
	}


	
	//解压文件
	public void unzipFile(String targetPath, String zipFilePath) {
		int i=0;
		try {
			File zipFile = new File(zipFilePath);
			InputStream is = new FileInputStream(zipFile);
			ZipInputStream zis = new ZipInputStream(is);
			ZipEntry entry = null;
			while ((entry = zis.getNextEntry()) != null) {
				i++;
				String zipPath = entry.getName();
				try {

					if (entry.isDirectory()) {
						File zipFolder = new File(targetPath + File.separator
								+ zipPath);
						if (!zipFolder.exists()) {
							zipFolder.mkdirs();
						}
					} else {
						File file = new File(targetPath + File.separator
								+ zipPath);
						if (!file.exists()) {
							File pathDir = file.getParentFile();
							pathDir.mkdirs();
							file.createNewFile();
						}

						FileOutputStream fos = new FileOutputStream(file);
						int bread;
						while ((bread = zis.read()) != -1) {
							fos.write(bread);
						}
						fos.close();
					}

				} catch (Exception e) {
					continue;
				}
			}
			zis.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ProgressDialog getDialog(Context context, String title, String msg) {
		ProgressDialog dialog = new ProgressDialog(context);
		dialog.setTitle(title);
		dialog.setMessage(msg);
		dialog.setCanceledOnTouchOutside(false);
		return dialog;
	}
	
}
