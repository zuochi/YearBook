package action.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.hibernate.Hibernate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dto.FriendInfomation;

import util.PageController;

import bean.FriendList;
import bean.HeadPhoto;
import bean.Photo;
import bean.Profession;
import bean.SchoolYear;
import bean.User;

@Controller
@Scope("prototype")
public class GetSocial extends UserAction{
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		Properties pro = new Properties();
		pro.setProperty("user.id", request.getParameter("userId"));
		
		int socialPhotosCount = service.getTotalRowsByProperties(pro, new Photo(),true);
		
		Properties userIdPro = new Properties();
		userIdPro.setProperty("id", request.getParameter("userId"));
		//获取社交用户
		User socialUser = (User) service.getObjectByProperties(userIdPro, new User());
		//获取当前user
		user = (User) request.getSession().getAttribute("user");
		//屏蔽相关属性
		socialUser.setUserName("");
		socialUser.setPassword("");
		
		Properties headPhotoIdPro = new Properties();
		headPhotoIdPro.setProperty("id", socialUser.getHeadPhoto().getId().toString());
		HeadPhoto socialHeadPhoto = (HeadPhoto) service.getObjectByProperties(headPhotoIdPro,new HeadPhoto());
		
		if(socialUser.getProfession()!=null){
			Properties professionIdPro = new Properties();
			professionIdPro.setProperty("id", socialUser.getProfession().getId().toString());
			Profession socialProfession = (Profession) service.getObjectByProperties(professionIdPro,new Profession());
			request.setAttribute("socialProfession", socialProfession);
		}
		
		if(socialUser.getSchoolYear()!=null){
			Properties schoolYearIdPro = new Properties();
			schoolYearIdPro.setProperty("id", socialUser.getSchoolYear().getId().toString());
			SchoolYear socialSchoolYear = (SchoolYear) service.getObjectByProperties(schoolYearIdPro,new SchoolYear());
			request.setAttribute("socialSchoolYear", socialSchoolYear);
		}
		
		//获取following数量
		Properties followingPro = new Properties();
		followingPro.setProperty("userByUserId.id", request.getParameter("userId"));
		int socialFollowingCount = service.getTotalRowsByProperties(followingPro, new FriendList(),true);
				
		//获取followers数量
		Properties followersPro = new Properties();
		followersPro.setProperty("userByFriendId.id", request.getParameter("userId"));
		int socialFollowersCount = service.getTotalRowsByProperties(followersPro, new FriendList(),true);
		
		request.setAttribute("socialPhotosCount", socialPhotosCount);
		request.setAttribute("socialUser", socialUser);
		request.setAttribute("socialHeadPhoto", socialHeadPhoto);
		request.setAttribute("socialFollowingCount", socialFollowingCount);
		request.setAttribute("socialFollowersCount", socialFollowersCount);
		request.setAttribute("relationShip", getRelationship(user.getId(),socialUser.getId()));
		return "social";
	}
	
	public String getFriendByPerPage(){
		List<FriendInfomation> friendInfomations = null;
		
		Integer friendAmmount = (Integer) service.getObjectBySql("select count(*) from friend_list f left join user u on f.user_id = u.id where f.user_id  in (select f.friend_id from friend_list f where f.user_id = "+user.getId()+") and f.friend_id = "+user.getId(),new Integer(0));
		
		System.out.println(friendAmmount);
		
		//PageController pc = new PageController(1,1,true);
		//pc.setCurrentPage(toPage);
		
		//获取当前用户的好友信息
		//List<User> userFriendList = service.getObjectsBySql("select u.* from friend_list f left join user u on f.user_id = u.id where f.user_id  in (select f.friend_id from friend_list f where f.user_id = "+user.getId()+") and f.friend_id = "+user.getId(),pc,user,"getHeadPhoto");
		
		//for(User friend : userFriendList){
			//System.out.println(friend.getName());
		//}
		
		return "ajax";
	}
	
	//判断是否关注
	private boolean isFollowed(int userByUserId,int userByFriendId){
		FriendList isFriendList = (FriendList) service.getObjectByHql("from FriendList where isDelete=0 and userByUserId.id="+userByUserId+" and userByFriendId.id=" + userByFriendId,null);
		if(isFriendList!=null){
			return true;
		}
		return false;
	}
	
	//获取当前user的好友列表,判断与该用户的关系,0:未关注,1:已关注,2:朋友,3:被关注
	private int getRelationship(int userByUserId,int userByFriendId){
		//判断是否关注
		if(isFollowed(userByUserId,userByFriendId)){
			//判断是否朋友
			if(isFollowed(userByFriendId,userByUserId)){
				return 2;
			}else{
				return 1;
			}
		}else{
			//判断是否被关注
			if(isFollowed(userByFriendId,userByUserId)){
				return 3;
			}else{
				return 0;
			}
		}
	}
}
