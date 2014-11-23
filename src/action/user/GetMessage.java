package action.user;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import util.PageController;

import bean.User;


@Controller
@Scope("prototype")
public class GetMessage extends UserAction{
	private int count;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		user = (User) request.getSession().getAttribute("user");
		
		if(!isLogin(user)){
			return "login";
		}
		
		try {
			//status=0 ,且评论者不为自己的未读的条数
			count = (Integer) service.getObjectByHql("select count(*) from Reply r where r.status=0 and r.isDelete=0 and r.userByUserBid.id="+user.getId()+" and r.userByUserId.id!="+user.getId(), "getInteger");
			
			PageController pc = new PageController(count,1,1);
			pc.setCurrentPage(toPage);
			
			/*if(toPage>pc.getTotalPages()){
				out.print("fail");
			}else{
				List<dto.Reply> replys = service.getDtoObjectsBySql("select r.id,r.user_id,u.name,p.url_m,r.user_bid,r.photo_bid,r.context,r.signup_date from reply r,user u LEFT JOIN head_photo p on p.is_delete=0 and p.id=(select u.head_photo_id from user u where u.is_delete=0 and u.id = r.user_id) where r.is_delete=0 and u.id=r.user_id and r.status=0 and r.user_id!="+user.getId()+" and r.user_bid="+user.getId()+" order by r.signup_date desc", pc,new dto.Reply());
				request.setAttribute("messageReply", replys);
			}*/
			request.setAttribute("messageReplyPc", pc);
			request.setAttribute("messageReplyCount", count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "message";
	}

	public String getPhotoReplysByPerPage(){
		user = (User) request.getSession().getAttribute("user");
		try {
			//status=0 ,且评论者不为自己的未读的条数
			//count = (Integer) service.getObjectByHql("select count(*) from Reply r where r.status=0 and r.isDelete=0 and r.userByUserBid.id!="+user.getId()+" and r.userByUserId.id="+user.getId(), "getInteger");
			
			PageController pc = new PageController(count,1,1);
			pc.setCurrentPage(toPage);
			
			if(toPage>pc.getTotalPages()){
				out.print("fail");
			}else{
				List<dto.Reply> replys = service.getDtoObjectsBySql("select r.id,r.user_id,u.name,p.url_m,r.user_bid,r.photo_bid,r.context,r.signup_date from reply r,user u LEFT JOIN head_photo p on p.is_delete=0 and p.id=(select u.head_photo_id from user u where u.is_delete=0 and u.id = r.user_id) where r.is_delete=0 and u.id=r.user_id and r.status=0 and r.user_id!="+user.getId()+" and r.user_bid="+user.getId()+" order by r.signup_date desc", pc,new dto.Reply());
				
				JSONArray json = JSONArray.fromObject(replys);
				out = response.getWriter();
				out.print(json);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	//获取未读总条数
	public String getUnReadCount(){
		try {
			user = (User) request.getSession().getAttribute("user");
			
			//status=0 ,且评论者不为自己的未读的条数
			count = (Integer) service.getObjectByHql("select count(*) from Reply r where r.status=0 and r.isDelete=0 and r.userByUserBid.id!="+user.getId()+" and r.userByUserId.id="+user.getId(), "getInteger");
			
			out = response.getWriter();
			out.print(count);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			out.print("fail");
			e.printStackTrace();
		} finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	@JSON(serialize=false)
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
