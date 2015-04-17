package action.user;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import util.PageController;

import action.user.UserAction;

@Controller
@Scope("prototype")
public class GetReply extends UserAction{
	private int replyCountN;
	private int pageSizeN;
	
	@Override
	public String execute() {
		try {
			//int replyCount = (Integer) service.getObjectByHql("select count(*) from Reply r where r.isDelete=0 and userByUserId.id="+user.getId()+" and r.photo.id="+photo.getId(), "getInteger");
			PageController pc = new PageController(replyCountN,1,pageSizeN);
			pc.setCurrentPage(toPage);

			if(toPage>pc.getTotalPages()){
				out.print("fail");
			}else{
				List<dto.Reply> replys = service.getDtoObjectsBySql("select r.id,r.user_id,u.name,p.url_m,r.user_bid,r.photo_bid,r.context,r.signup_date from user u,reply r LEFT JOIN head_photo p on p.is_delete=0 and p.id=(select u.head_photo_id from user u where u.is_delete=0 and u.id = r.user_id group by u.head_photo_id ) where r.is_delete=0 and u.id=r.user_id and r.photo_bid="+photo.getId() + " order by r.signup_date desc", pc,new dto.Reply());
				
				JSONArray json = JSONArray.fromObject(replys);
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
	
	//获取评论总条数
	public String getReplyCount(){
		try {
			int replyCount = (Integer) service.getObjectByHql("select count(*) from Reply r where r.isDelete=0 and r.photo.id="+photo.getId(), "getInteger");
			
			out = response.getWriter();
			out.print(replyCount);
		} catch (IOException e) {
			out.print("fail");
			e.printStackTrace();
		} finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	@JSON(serialize=false)
	public int getReplyCountN() {
		return replyCountN;
	}

	public void setReplyCountN(int replyCountN) {
		this.replyCountN = replyCountN;
	}

	@JSON(serialize=false)
	public int getPageSizeN() {
		return pageSizeN;
	}

	public void setPageSizeN(int pageSizeN) {
		this.pageSizeN = pageSizeN;
	}
}
