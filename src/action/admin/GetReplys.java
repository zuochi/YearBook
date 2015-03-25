package action.admin;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.Reply;
import util.CHW;
import util.PageController;

@Controller
@Scope("prototype")
public class GetReplys extends AdminAction{
	private Reply reply;

	@Override
	public String execute() {
		CHW chw = new CHW();
		
		if(reply!=null){
			chw.setCondition("userByUserId.id", reply.getUserByUserId().getId(), CHW.opr_equal);
			chw.setCondition("userByUserId.name", reply.getUserByUserId().getName(), CHW.opr_like);
			chw.setCondition("isDelete", reply.getIsDelete(), CHW.opr_equal);
			chw.setCondition("isAccusation", reply.getIsAccusation(), CHW.opr_equal);
		}else{
			reply = new Reply();
			reply.setIsDelete(0);
			reply.setIsAccusation(1);
			chw.setCondition("isDelete", reply.getIsDelete(), CHW.opr_equal);
			chw.setCondition("isAccusation", reply.getIsAccusation(), CHW.opr_equal);
		}
		
		Integer replysCount = (Integer) service.getObjectByHql("select count(id) from Reply " + chw.getSqlWhere(), "getInteger");
		PageController pc = new PageController(replysCount, 1,pageSize);
		pc.setCurrentPage(toPage);
		
		//List<dto.Reply> replys = service.getDtoObjectsBySql("select r.id,r.user_id,u.name,p.url_m,r.user_bid,r.photo_bid,r.context,r.signup_date from user u,reply r LEFT JOIN head_photo p on p.is_delete=0 and p.id=(select u.head_photo_id from user u where u.is_delete=0 and u.id = r.user_id group by u.head_photo_id ) where r.is_delete=0 and u.id=r.user_id and r.is_accusation=1 order by r.signup_date desc", pc,new dto.Reply());
		
		List<Reply> replys = service.getObjectsByHql("from Reply " + chw.getSqlWhere() + " order by " + (orderBy!=null?orderBy:"signupDate") + " " + (desc!=null?desc:""), pc,"getUserByUserId");
		
		request.setAttribute("objs", replys);
		request.setAttribute("pc", pc);
		request.setAttribute("pageSize", this.pageSize);
		request.setAttribute("params", this.reply);
		
		return "report_getReplys";
	}

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}
	
}
