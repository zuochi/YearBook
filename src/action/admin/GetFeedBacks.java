package action.admin;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.FeedBack;
import bean.Reply;
import util.CHW;
import util.PageController;

@Controller
@Scope("prototype")
public class GetFeedBacks extends AdminAction{
	private FeedBack feedBack;

	@Override
	public String execute() {
		CHW chw = new CHW();
		
		if(feedBack!=null){
			chw.setCondition("user.id", feedBack.getUser().getId(), CHW.opr_equal);
			chw.setCondition("user.name", feedBack.getUser().getName(), CHW.opr_like);
			chw.setCondition("userDeal.id", feedBack.getUserDeal().getId(), CHW.opr_equal);
			chw.setCondition("userDeal.name", feedBack.getUserDeal().getName(), CHW.opr_like);
			chw.setCondition("context", feedBack.getContext(), CHW.opr_like);
			chw.setCondition("isDelete", feedBack.getIsDelete(), CHW.opr_equal);
			chw.setCondition("status", feedBack.getStatus(), CHW.opr_equal);
		}else{
			feedBack = new FeedBack();
			feedBack.setIsDelete(0);
			feedBack.setStatus(0);
			chw.setCondition("isDelete", feedBack.getIsDelete(), CHW.opr_equal);
			chw.setCondition("status", feedBack.getStatus(), CHW.opr_equal);
		}
		
		Integer feedBacksCount = (Integer) service.getObjectByHql("select count(id) from FeedBack " + chw.getSqlWhere(), "getInteger");
		PageController pc = new PageController(feedBacksCount, 1,pageSize);
		pc.setCurrentPage(toPage);
		
		List<Reply> replys = service.getObjectsByHql("from FeedBack " + chw.getSqlWhere() + " order by " + (orderBy!=null?orderBy:"signupDate") + " " + (desc!=null?desc:""), pc,"getUser","getUserDeal");
		
		request.setAttribute("objs", replys);
		request.setAttribute("pc", pc);
		request.setAttribute("pageSize", this.pageSize);
		request.setAttribute("params", this.feedBack);
		
		return "admin_getFeedBacks";
	}

	public FeedBack getFeedBack() {
		return feedBack;
	}

	public void setFeedBack(FeedBack feedBack) {
		this.feedBack = feedBack;
	}

}
