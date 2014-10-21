package action.user;

import java.util.List;
import java.util.Properties;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

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
		User socialUser = (User) service.getObjectByProperties(userIdPro, new User());
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
		
		//获取当前user的好友列表,判断与该用户的关系
		Boolean isfollow = false;
		Properties friendListPro = new Properties();
		user = (User) request.getSession().getAttribute("user");
		friendListPro.setProperty("userByUserId.id", user.getId().toString());
		List<FriendList> userFriendList = service.getObjectsByProperties(friendListPro, new FriendList(), true);
		for(int i=0; i<userFriendList.size(); i++){
			int followUserId = socialUser.getId();
			int userId = userFriendList.get(i).getUserByFriendId().getId();
			if(followUserId==userId){
				isfollow = true;
				break;
			}
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
		request.setAttribute("isfollow", isfollow);
		return "social";
	}
}
