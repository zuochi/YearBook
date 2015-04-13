package util;

import service.Service;

import bean.User;

public class AnalyzeStringUtils {
	public static String analyzeFriendUrl(String reply,Service service){
		//去除前后空格
		reply = reply.trim();
		//先按空格分割
		String[] friendsList = reply.split("\\s+");
		//使用StringBuilder重组字符串
		StringBuilder replyStringBuilder = new StringBuilder("");
		
		//重置reply
		//reply = "";
		
		User friend = null;
		
		for(int i=0 ; i<friendsList.length ; i++){
			try {
				friendsList[i] += " ";
				String[] friendsListMiddle = friendsList[i].split("@");
				for(int j=0 ; j<friendsListMiddle.length ; j++){
					if(j==0){
						replyStringBuilder.append(friendsListMiddle[j]);
						continue;
					}
					if(j==(friendsListMiddle.length-1) && !"".equals(friendsListMiddle[j])){
						friend = getUserByName(friendsListMiddle[j],service);
						if(friend!=null){
							replyStringBuilder.append("<a href='javascript:goSocialIndex("+friend.getId()+")'>"+"@"+friendsListMiddle[j]+"</a> ");
						}else{
							replyStringBuilder.append("@" + friendsListMiddle[j]);
						}
					}else{
						replyStringBuilder.append(("@" + friendsListMiddle[j]));
					}
				}
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return replyStringBuilder.toString();
	}
	
	private boolean noifyFriend(Service service){
		return false;
	}
	
	private static User getUserByName(String name,Service service){
		return (User) service.getObjectByHql("from User where isDelete=0 and name="+name);
	}
	
}
