package action.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import util.PageController;
import bean.FriendList;


@Controller
@Scope("prototype")
public class GetMySocial extends UserAction{
	private int followingToPage;
	private int followersToPage;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		//获取following数量
		Properties followingPro = new Properties();
		followingPro.setProperty("userByUserId.id", request.getParameter("userId"));
		int followingCount = service.getTotalRowsByProperties(followingPro, new FriendList(),true);
		
		//获取followers数量
		Properties followersPro = new Properties();
		followersPro.setProperty("userByFriendId.id", request.getParameter("userId"));
		int followersCount = service.getTotalRowsByProperties(followersPro, new FriendList(),true);
		
		//获取关注列表
		PageController followingListPc = new PageController(followingCount, 1,20);
		followingListPc.setCurrentPage(followingToPage);
		List<FriendList> followingList = service.getObjectsByPrepageAndProperties(followingPro,followingListPc, new FriendList(), true);
		
		
		//获取粉丝列表
		PageController followersListPc = new PageController(followersCount, 1,20);
		followersListPc.setCurrentPage(followersToPage);
		List<FriendList> followersList = service.getObjectsByPrepageAndProperties(followersPro,followersListPc, new FriendList(), true);
		
		request.setAttribute("followingList", followingList);
		request.setAttribute("followersList", followersList);
		request.setAttribute("followingCount", followingCount);
		request.setAttribute("followersCount", followersCount);
		return null;
	}

	public void setFollowingToPage(int followingToPage) {
		this.followingToPage = followingToPage;
	}
	public void setFollowersToPage(int followersToPage) {
		this.followersToPage = followersToPage;
	}
	
	
}
