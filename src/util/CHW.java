package util;
/**
 * CHM 全称 Construt Hql Where
 * @author Yodomi Jinta
 *
 */
public class CHW{
	public final static String opr_equal = "=";
	public final static String opr_like = "like";
	public final static String opr_equal_rt = ">=";
	public final static String opr_equal_lt = "<=";
	public final static String opr_rt = ">";
	public final static String opr_lt = "<";
	
	private StringBuilder sb;
	
	public void setCondition(String key,Object value,String opr){
		if(sb==null){
			sb = new StringBuilder();
			sb.append(" where 1=1");
		}
		
		if(value!=null){
			if(opr.equals(opr_like)){
				String s = (String) value;
				if(!s.isEmpty()){
					sb.append(" and " + key + " " + opr +" '%" + value + "%'");
				}
			}else if(value instanceof String){
				String s = (String) value;
				if(!s.isEmpty()){
					sb.append(" and " + key + " " + opr + " '" + value + "'");
				}
			}else if(value instanceof Integer){
				Integer i = (Integer) value;
				sb.append(" and " + key + " " + opr + " " + i.intValue());
			}else if(value instanceof Double){
				Double d = (Double) value;
				sb.append(" and " + key + " " + opr + " " + d.doubleValue());
			}
		}
	}
	
	public void setSearchCondition(String key,String value){
		if(sb==null){
			sb = new StringBuilder();
		}
		
		if(!CommonUtils.isBlank(value)){
			if("".equals(sb.toString())){
				sb.append(" where ");
			}
			sb.append(" " + key + " " + opr_like +" '%" + value + "%'" + " or ");
		}
	}
	
	public String getSqlWhere(){
		if(sb != null){
			System.out.println("【SqlWhere】:"+sb.toString());
			return sb.toString();
		}
		return "";
	}
	
	public String getSearchSqlWhere(){
		if(sb != null){
			String result = sb.substring(0, sb.lastIndexOf("or"));
			System.out.println("【SearchSqlWhere】:"+result);
			return result;
		}
		return "";
	}
}