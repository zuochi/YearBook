package action.user;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import net.sf.json.JSONArray;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dto.FriendInfomation;

import util.PageController;

import bean.FriendList;
import bean.Photo;
import bean.User;

@Controller
@Scope("prototype")
public class GetMyPost extends UserAction{
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		//获取相片数量
		int photosCount = (Integer) service.getObjectByHql("select count(*) from Photo where isDelete=0 and user.id="+user.getId(), "getInteger");
		
		//获取following数量
		int followingCount = (Integer) service.getObjectByHql("select count(*) from FriendList where isDelete=0 and userByUserId.id="+user.getId(), "getInteger");
		
		//获取followers数量
		int followersCount = (Integer) service.getObjectByHql("select count(*) from FriendList where isDelete=0 and userByFriendId.id="+user.getId(), "getInteger");
		
		request.setAttribute("photosCount", photosCount);
		request.setAttribute("followingCount", followingCount);
		request.setAttribute("followersCount", followersCount);
		return "mypost";
	}
}
