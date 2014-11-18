package action.user;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import net.sf.json.JSONArray;

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
	
	public String getPostReply(){
		try {
			user = (User) request.getSession().getAttribute("user");
			
			int replyCount = (Integer) service.getObjectByHql("select count(*) from Reply r where r.isDelete=0 and userByUserId.id="+user.getId()+" and r.photo.id="+photo.getId(), "getInteger");
			
			PageController pc = new PageController(replyCount,1,5);
			pc.setCurrentPage(toPage);
			
			List<dto.Reply> replys = service.getObjectsBySql("select r.id,r.user_id,u.name,p.url_m,r.user_bid,r.photo_bid,r.context,r.signup_date from reply r,user u LEFT JOIN head_photo p on p.is_delete=0 and p.id=(select u.head_photo_id from user u where u.is_delete=0 and u.id = r.user_id) where r.is_delete=0 and u.id=r.user_id and r.user_bid="+user.getId()+" and r.photo_bid="+photo.getId() + " order by r.signup_date desc", pc,new dto.Reply());
			
			JSONArray json = JSONArray.fromObject(replys);
			out = response.getWriter();
			out.print(json);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			out.close();
		}
		return null;
	}
}
