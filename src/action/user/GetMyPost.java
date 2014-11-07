package action.user;

import java.util.Properties;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.FriendList;
import bean.Photo;

@Controller
@Scope("prototype")
public class GetMyPost extends UserAction{
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		//获取相片数量
		/*Properties pro = new Properties();
		pro.setProperty("user.id", user.getId().toString());
		//pro.setProperty("photoAlbum.id", request.getParameter("photoAlbumId"));
		int photosCount = service.getTotalRowsByProperties(pro, new Photo(),true);*/
		int photosCount = (Integer) service.getObjectByHql("select count(*) from Photo where isDelete=0 and user.id="+user.getId(), "getInteger");
		
		//获取following数量
		/*Properties followingPro = new Properties();
		followingPro.setProperty("userByUserId.id", user.getId().toString());
		int followingCount = service.getTotalRowsByProperties(followingPro, new FriendList(),true);*/
		int followingCount = (Integer) service.getObjectByHql("select count(*) from FriendList where isDelete=0 and userByUserId.id="+user.getId(), "getInteger");
		
		//获取followers数量
		/*Properties followersPro = new Properties();
		followersPro.setProperty("userByFriendId.id", user.getId().toString());
		int followersCount = service.getTotalRowsByProperties(followersPro, new FriendList(),true);*/
		int followersCount = (Integer) service.getObjectByHql("select count(*) from FriendList where isDelete=0 and userByFriendId.id="+user.getId(), "getInteger");
		
		request.setAttribute("photosCount", photosCount);
		request.setAttribute("followingCount", followingCount);
		request.setAttribute("followersCount", followersCount);
		return "mypost";
	}
	
}
