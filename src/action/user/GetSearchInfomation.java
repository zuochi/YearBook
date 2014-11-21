package action.user;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import util.PageController;

import bean.FriendList;
import bean.User;
import dto.FriendInfomation;

@Controller
@Scope("prototype")
public class GetSearchInfomation extends UserAction{
	private String type;
	private FriendList friendList;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try {
			user = (User) request.getSession().getAttribute("user");
			out = response.getWriter();
			if(type.equals("searchFriendName") && user!=null){
				PageController pc = new PageController(5, 1,5);
				
				List<FriendInfomation> friendNameList = service.getDtoObjectsBySql("select u.name,h.url_s from user u LEFT JOIN head_photo h on u.head_photo_id=h.id where u.is_delete=0 and u.id in (select f.friend_id from friend_list f where f.user_id=" + user.getId() + ") and u.name like '" + friendList.getUserByFriendId().getName() + "%'", pc, new FriendInfomation());
				
				/*List<FriendList> friendLists = service.getObjectsByHql("from FriendList where isDelete=0 and userByUserId.id="+user.getId() + " and userByFriendId.name like '"+friendList.getUserByFriendId().getName()+"%'", pc);
				
				List<FriendInfomation> friendNameList = new ArrayList<FriendInfomation>();
				for(FriendList f : friendLists){
					User friend = (User) service.getObjectByHql("from User where id="+f.getUserByFriendId().getId(), "getHeadPhoto");
					if(friend!=null){
						friendNameList.add(new FriendInfomation(friend.getHeadPhoto().getUrlS(),friend.getName()));
					}
				}*/
				JSONArray json = JSONArray.fromObject(friendNameList);
				out.print(json);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public FriendList getFriendList() {
		return friendList;
	}
	
	public void setFriendList(FriendList friendList) {
		this.friendList = friendList;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}