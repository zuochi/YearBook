package action.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import util.PageController;
import bean.FriendList;
import bean.Photo;
import bean.PhotoAlbum;
import bean.User;

@Controller
@Scope("prototype")
public class GetMyPost extends UserAction{
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		//获取相片数量
		Properties pro = new Properties();
		pro.setProperty("user.id", request.getParameter("userId"));
		//pro.setProperty("photoAlbum.id", request.getParameter("photoAlbumId"));
		int photosCount = service.getTotalRowsByProperties(pro, new Photo(),true);
		
		//获取following数量
		Properties followingPro = new Properties();
		followingPro.setProperty("userByUserId.id", request.getParameter("userId"));
		int followingCount = service.getTotalRowsByProperties(followingPro, new FriendList(),true);
		
		//获取followers数量
		Properties followersPro = new Properties();
		followersPro.setProperty("userByFriendId.id", request.getParameter("userId"));
		int followersCount = service.getTotalRowsByProperties(followersPro, new FriendList(),true);
		
		request.setAttribute("photosCount", photosCount);
		request.setAttribute("followingCount", followingCount);
		request.setAttribute("followersCount", followersCount);
		return "mypost";
	}
	
}
