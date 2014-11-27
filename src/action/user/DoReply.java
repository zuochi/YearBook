package action.user;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.Hibernate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import service.Service;

import bean.AtNotify;
import bean.Reply;
import bean.User;

@Controller
@Scope("prototype")
public class DoReply extends UserAction{
	private Reply reply;
	private TreeSet<User> followSet = null;
	
	@SuppressWarnings("deprecation")
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		//当前用户
		User currentUser = (User) request.getSession().getAttribute("user");
		
		reply.setUserByUserId(currentUser);
		
		//先解评论好友，并为好友附上链接
		reply.setContext(analyzeFriendUrl(reply.getContext(),currentUser,service));
		
		//保存评论时间，用于获取用户评论的ID
		reply.setSignupDate(new Timestamp(System.currentTimeMillis()));
		reply.setStatus(0);
		reply.setIsDelete(0);
		
		out = response.getWriter();
		
		if(service.saveObject(reply)){
			//返回成功并获取当前评论
			reply = getCurrentReply(currentUser,reply.getSignupDate());
			
			//那么我们就艾特这些用户吧
			if(followSet!=null){
				Iterator<User> it = followSet.iterator();
				while(it.hasNext()){
					if(atNoifyFriend(currentUser,it.next())){
						System.out.println(new Timestamp(System.currentTimeMillis()) + " : at friend success.");
					};
				}
			}
			out.print(reply.getContext());
		}else{
			out.print("fail");
		}
		out.flush();
		out.close();
		return null;
	}
	
	//解析at到的用户
	public String analyzeFriendUrl(String replyContext,User currentUser,Service service){
		//去除前后空格
		replyContext = replyContext.trim();
		
		//先按空格分割
		String[] friendsList = replyContext.split("\\s+");

		//使用StringBuilder重组字符串
		StringBuilder replyStringBuilder = new StringBuilder("");
				
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
						friend = getUserByName(friendsListMiddle[j].trim(),service);
						if(friend!=null){
							replyStringBuilder.append("<a href='javascript:goSocialIndex("+friend.getId()+")'>"+"@"+friendsListMiddle[j].trim()+"</a> ");
							//那么我们就艾特这个用户吧，先把该用户添加进Set里面，保证每个用户一条评论只@一次
							if(followSet==null){
								followSet = new TreeSet<User>(new UserComparetor());
							}
							//@自己是没有提示的，或者别人在自己的图片的评论@自己也是没有提示的，只有评论
							if(friend.getId().intValue()!=currentUser.getId().intValue() && reply.getUserByUserBid().getId().intValue()!=friend.getId().intValue()){
								//判断第一个@到的用户是否reply对象
								if(i==0){
									if(replyContext.startsWith("reply")){
										reply.getUserByUserBid().setId(friend.getId().intValue());
										continue;
									}
								}
								if(photo.getUser().getId()!=friend.getId().intValue()){
									followSet.add(friend);
								}
							}
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
	
	//at提醒
	private boolean atNoifyFriend(User currentUser,User friend){
		return service.saveObject(new AtNotify(null,currentUser,null,reply,friend,null,null,new Timestamp(System.currentTimeMillis()),0,0));
	}
	
	private User getUserByName(String name,Service service){
		return (User) service.getObjectByHql("from User where isDelete=0 and name='"+name+"'");
	}

	private Reply getCurrentReply(User currentUser,Timestamp signupDate){
		return (Reply) service.getObjectByHql("from Reply where isDelete=0 and signupDate='"+signupDate+"' and userByUserId.id="+currentUser.getId());
	}
	
	@JSON(serialize=false)
	public Reply getReply() {
		return reply;
	}
	
	@JSON(serialize=false)
	public TreeSet<User> getFollowSet() {
		return followSet;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}
	
}

class UserComparetor implements Comparator<User>{
	@Override
	public int compare(User user1, User user2) {
		// TODO Auto-generated method stub
		if(user1.getId()>user2.getId()){
			return 1;
		}else if(user1.getId()<user2.getId()){
			return -1;
		}
		return 0;
	}
}
