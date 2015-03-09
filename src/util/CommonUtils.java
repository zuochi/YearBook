package util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CommonUtils {

	
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str){
		
		if(str==null){
			return true;
		}
		if("".equals(str.trim())){
			return true;
		}
		if("null".equals(str)){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断字符串非空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		
		if(str==null) return false;
		
		if("".equals(str.trim())) return false;
		
		if("null".equals(str)) return false;
		
		return true;
	}
	
	
	
	
	public static String createUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	
	/**
	 * 将double类型根据数字格式转换字符串
	 * @param num
	 * @param pattern
	 * @return
	 */
	public static String randDoubleToString(Double num,String pattern){
		
		DecimalFormat format = new DecimalFormat(pattern);
		return format.format(num.doubleValue());
	}
	
	/**
	 * 校验手机号码是否正确
	 * @author 周瑞振
	 * @param phone -手机号码
	 * @return boolean -正确与否,true为正确,false为不正确
	 */
	public static boolean checkPhone(String phone) {
		return phone.matches("^1[3|4|5|8][0-9]\\d{4,8}$");
	}
	
	/**
	 * 校验字符串是否为纯数字
	 * @author 周瑞振
	 * @param checked -需要检验的字符串
	 * @return boolean -true为纯字符串,false为不是纯字符串
	 */
	public static boolean isNumber(String checked) {
		return checked.matches("[0-9]+");
	}
	
	/**
	 * 校验邮箱格式是否正确
	 * @author 周瑞振
	 * @param email -邮箱
	 * @return boolean -正确与否,true为正确,false为不正确
	 */
	public static boolean checkEmail(String email) {
		return email.matches("^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$");
	}
	
	/**
	 * 判断字符串数组的第一个字符串是否为空
	 * @author 周瑞振
	 * @param array -数组
	 * @return boolean -true为空,false为非空
	 */
	public static boolean isEmptyForArrayFirstChild(String[] array) {
		if(array==null) {
			return true;
		} else if(array.length<=0) {
			return true;
		} else if(isBlank(array[0])) {
			return true;
		} else {
			return false;
		}
	}
	
	/** 根据时间生成订单号
		 * @return
		 */
	public static String createOrderId(){
		SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmssSSS");
		String dateStr = format.format(new Date(System.currentTimeMillis()));
		return dateStr;
 	}
}
