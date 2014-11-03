package action.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONArray;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import util.PageController;

import bean.FriendList;
import bean.HeadPhoto;
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
		user = (User) request.getSession().getAttribute("user");
		out = response.getWriter();
		if(type.equals("searchFriendName") && user!=null){
			PageController pc = new PageController(5, 1,5);
			Properties pro = new Properties();
			pro.setProperty("userByUserId.id", "='"+user.getId().toString());
			pro.setProperty("userByFriendId.name", friendList.getUserByFriendId().getName());
			List<FriendList> friendLists = service.getObjectsByPrepageAndByPropertiesLike(pro,pc, new FriendList(), false);
			List<FriendInfomation> friendNameList = new ArrayList<FriendInfomation>();
			for(FriendList f : friendLists){
				Properties prof = new Properties();
				prof.setProperty("id", f.getUserByFriendId().getId().toString());
				User friend = (User) service.getObjectByProperties(prof, new User());
				if(friend!=null){
					if (friend.getHeadPhoto().getId() != null) {
						pro = new Properties();
						pro.setProperty("id", friend.getHeadPhoto().getId().toString());
					}
					HeadPhoto headPhoto = (HeadPhoto) service.getObjectByProperties(pro, new HeadPhoto());
					friendNameList.add(new FriendInfomation(headPhoto.getUrlS(),friend.getName()));
				}
			}
			JSONArray json = null;
			try {
				json = JSONArray.fromObject(friendNameList);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print(json);
		}
		out.close();
		out.flush();
		return "ajax";
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