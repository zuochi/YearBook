package action.admin;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import util.PageController;

@Controller
@Scope("prototype")
public class GetReplys extends AdminAction{

	@Override
	public String execute() {
		/*if(!isLogin(user)){
			return "login";
		}*/
		Integer replysCount = (Integer) service.getObjectByHql("select count(r.id) from Reply r where r.isDelete=0 and r.isAccusation=1", "getInteger");
		PageController pc = new PageController(replysCount, 1,20);
		pc.setCurrentPage(toPage);
		
		List<dto.Reply> replys = service.getDtoObjectsBySql("select r.id,r.user_id,u.name,p.url_m,r.user_bid,r.photo_bid,r.context,r.signup_date from user u,reply r LEFT JOIN head_photo p on p.is_delete=0 and p.id=(select u.head_photo_id from user u where u.is_delete=0 and u.id = r.user_id group by u.head_photo_id ) where r.is_delete=0 and u.id=r.user_id and r.is_accusation=1 order by r.signup_date desc", pc,new dto.Reply());
		request.setAttribute("objs", replys);
		request.setAttribute("pc", pc);
		
		return "report_getReplys";
	}
}
