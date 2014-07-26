package smscode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.cloopen.rest.sdk.CCPRestSDK.BodyType;

public class SendTemplateSMS {
	
	public static void main(String[] args) {
		sendSMSCode("18615599163", "555555", "2");
	}

	public static void sendSMSCode(String phonenum, String code, String time) {
		HashMap<String, Object> result = null;

		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init("app.cloopen.com", "8883"); //初始化服务器地址和端口
		restAPI.setAccount("8a48b5514739760101473d0f7baf0073", "d553e3e49c4246fca86da46e5c1f23ac");//初始化主帐号名称和主帐号令牌
		restAPI.setAppId("aaf98f8947473c1301474a226f8b010b");// 初始化应用ID
		
		
		result = restAPI.sendTemplateSMS(phonenum,"2619",new String[]{code, time}); //短信内容，验证码时间

		System.out.println("SDKTestGetSubAccounts result=" + result);
		if("000000".equals(result.get("statusCode"))){
			//正常返回输出data包体信息（map）
			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for(String key:keySet){
				Object object = data.get(key);
				System.out.println(key +" = "+object);
			}
		}else{
			////异常返回输出错误码和错误信息
			System.out.println("ErrorCode=" + result.get("statusCode") +"ErrorMsg= "+result.get("statusMsg"));
		}
	}
	
	public static Boolean  resendSMSCode(String phonenum, String code, String time) {
		Map result = new HashMap();

		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init("app.cloopen.com", "8883"); //初始化服务器地址和端口
		restAPI.setAccount("8a48b5514739760101473d0f7baf0073", "d553e3e49c4246fca86da46e5c1f23ac");//初始化主帐号名称和主帐号令牌
		restAPI.setAppId("aaf98f8947473c1301474a226f8b010b");// 初始化应用ID
		
		//result = restAPI.sendTemplateSMS(phonenum,"2619",new String[]{code, time}); //短信内容，验证码时间

		System.out.println("SDKTestGetSubAccounts result=" + result);
		if("000000".equals(result.get("statusCode"))){
			//正常返回输出data包体信息（map）
			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for(String key:keySet){
				Object object = data.get(key);
				System.out.println(key +" = "+object);
			}
			
			return true;
		}else{
			////异常返回输出错误码和错误信息
			System.out.println("ErrorCode=" + result.get("statusCode") +"ErrorMsg= "+result.get("statusMsg"));
			return false;
		}
	}

}
