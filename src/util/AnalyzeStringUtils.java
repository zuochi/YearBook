package util;

import java.util.Properties;

import service.Service;

import bean.User;

public class AnalyzeStringUtils {
	public static String analyzeFriendUrl(String reply,Service service){
		reply = reply.trim();
		String s = reply;
		String[] friendsList = reply.split("\\s+");
		
		//重置reply
		reply = "";
		User friend = null;
		
		for(int i=0 ; i<friendsList.length ; i++){
			try {
				String[] friendsListMiddle = friendsList[i].split("@");
				for(int j=0 ; j<friendsListMiddle.length ; j++){
					if(j==0){
						reply += friendsListMiddle[j];
						continue;
					}
					if(j==(friendsListMiddle.length-1) && !"".equals(friendsListMiddle[j])){
						friend = getUserByName(friendsListMiddle[j],service);
						if(friend!=null){
							reply += "<a href='YearBook/user/getSocial?userId="+friend.getId()+"'>"+"@"+friendsListMiddle[j]+"<a> ";
							System.out.println(i + " " +friend.getUserName());
						}else{
							reply += "@" + friendsListMiddle[j] + " " ;
						}
					}else{
						reply += ("@" + friendsListMiddle[j]);
					}
				}
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return reply;
	}
	
	private static User getUserByName(String name,Service service){
		Properties pro = new Properties();
		pro.setProperty("name", name);
		return (User) service.getObjectByProperties(pro, new User());
	}
	
}
