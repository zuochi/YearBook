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
	private int pageSize=10;
	private String type;
	
	@Override
	public String execute() {
		user = (User) request.getSession().getAttribute("user");
		
		if(!isLogin(user)){
			return "login";
		}
		
		try {
			if("photo".equals(type)){
				count = (Integer) service.getObjectByHql("select count(r.id) from Reply r where r.isDelete=0 and r.photo.id is not null and (r.userByUserBid.id="+user.getId()+" or r.photo.id in (select p.id from Photo p where p.user.id="+user.getId()+")) and r.userByUserId.id!="+user.getId(), "getInteger");
			}else if("mentionMe".equals(type)){
				count = (Integer) service.getObjectByHql("select count(a.id) from AtNotify a where a.isDelete=0 and a.userByUserBid.id="+user.getId(), "getInteger");
			}
			
			
			PageController pc = new PageController(count,1,pageSize);
			pc.setCurrentPage(toPage);

			//消息的类型type，消息的页码以及消息的总数
			request.setAttribute("type", type);
			request.setAttribute("messageReplyPc", pc);
			request.setAttribute("messageReplyCount", count);
			
			//获取各种消息未读总条数
			request.setAttribute("messageReplyUnReadCount", doGetUnReadCount());
			request.setAttribute("mentionMeUnReadCount", doGetUnReadMentionMeCount());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "message";
	}

	/**
	 * 获取MentionMe的相关信息
	 * @return String
	 */
	public String getMentionMeByPerPage(){
		user = (User) request.getSession().getAttribute("user");
		
		try {
			//status=0 ,且评论者不为自己的未读的条数
			//count = (Integer) service.getObjectByHql("select count(*) from Reply r where r.status=0 and r.isDelete=0 and r.userByUserBid.id!="+user.getId()+" and r.userByUserId.id="+user.getId(), "getInteger");
			//TODO
			PageController pc = new PageController(count,1,pageSize);
			pc.setCurrentPage(toPage);
			
			if(toPage>pc.getTotalPages()){
				out.print("fail");
			}else{
				//使用数据转换类
				List<dto.Message> messages = service.getDtoObjectsBySql(
					"select a.id,a.at_date as signup_date,a.user_id,a.user_bid,a.photo_bid,u.name,a.status,r.context,a.status as is_accusation,"+
						"(select ph.user_id from photo ph where ph.id=a.photo_bid) as photoOwnerId, "+
						"(select h.url_m from user u LEFT JOIN head_photo h on u.head_photo_id=h.id where u.id=a.user_id) as url_m "+
					"from at_notify a "+
						"LEFT JOIN user u on u.id=a.user_id "+
						"LEFT JOIN reply r on r.id=a.reply_bid "+
						"where a.user_bid="+user.getId() + " order by a.at_date desc ", pc,new dto.Message());
				JSONArray json = JSONArray.fromObject(messages);
				out = response.getWriter();
				out.print(json);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	/**
	 * 获取图片评论
	 * @return String
	 */
	public String getPhotoReplysByPerPage(){
		user = (User) request.getSession().getAttribute("user");
		
		if(user==null){
			return null;
		}
		
		try {
			//status=0 ,且评论者不为自己的未读的条数
			//count = (Integer) service.getObjectByHql("select count(*) from Reply r where r.status=0 and r.isDelete=0 and r.userByUserBid.id!="+user.getId()+" and r.userByUserId.id="+user.getId(), "getInteger");
			
			PageController pc = new PageController(count,1,pageSize);
			pc.setCurrentPage(toPage);
			
			if(toPage>pc.getTotalPages()){
				out.print("fail");
			}else{
				//使用数据转换类
				List<dto.Message> messages = service.getDtoObjectsBySql("select r.id,r.user_id,r.is_accusation,ph.user_id as photoOwnerId,u.name,p.url_m,r.user_bid,r.photo_bid,r.context,r.signup_date,r.status from reply r,photo ph,user u LEFT JOIN head_photo p on p.is_delete=0 and p.id=u.head_photo_id where r.photo_bid=ph.id and r.is_delete=0 and u.id=r.user_id and "+ ("photo".equals(type)?"r.photo_bid is not null":"") +" and r.user_id!="+user.getId()+" and (r.user_bid="+user.getId()+" or r.photo_bid in (select c.id from photo c where c.user_id="+user.getId()+")) group by r.id order by r.signup_date desc", pc,new dto.Message());
				JSONArray json = JSONArray.fromObject(messages);
				out = response.getWriter();
				out.print(json);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	//获取未读总条数
	public void getNewMessageCount(){
		try {
			user = (User) request.getSession().getAttribute("user");
			
			if(user==null){
				return;
			}
			
			out = response.getWriter();
			
			//status=0 ,且评论者不为自己的未读的条数
			Integer newMessageCount = doGetUnReadCount() + doGetUnReadMentionMeCount();
			
			out.print(newMessageCount);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
	
	//获取Photo's replys未读总条数
	private int doGetUnReadCount(){
		try {
			user = (User) request.getSession().getAttribute("user");
			
			if(user==null){
				return 0;
			}
			
			//status=0 ,且评论者不为自己的未读的条数
			return (Integer) service.getObjectByHql(
					"select count(r.id) "
					+ "from Reply r "
					+ "where r.status=0 and "
					+ "r.isDelete=0 and "
					+ "r.photo.id is not null and "
					+ "(r.userByUserBid.id="+user.getId()+" or "
							+ "r.photo.id in (select p.id from Photo p where p.user.id="+user.getId()+")) and "
									+ "r.userByUserId.id!="+user.getId(), "getInteger");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	//获取Metion Me未读总条数
	private int doGetUnReadMentionMeCount(){
		try {
			user = (User) request.getSession().getAttribute("user");
			
			if(user==null){
				return 0;
			}
			
			//status=0 is_delete=0,且被艾特到的user为自己的未读的条数
			return (Integer) service.getObjectByHql(
					"select count(a.id) "
					+ "from AtNotify a "
					+ "where a.isDelete=0 and "
					+ "a.status=0 and "
					+ "a.userByUserBid.id="+user.getId(), "getInteger");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@JSON(serialize=false)
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@JSON(serialize=false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
