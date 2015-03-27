package action.admin;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.FeedBack;
import bean.Reply;

@Controller
@Scope("prototype")
public class DeleteFeedBackA extends AdminAction {
	private FeedBack feedBack;
	
	@Override
	public String execute() {
		try {
			out = response.getWriter();
			feedBack = (FeedBack) service.getObjectByHql("from FeedBack where isDelete=0 and id='" + feedBack.getId()+"'");
			if(feedBack!=null){
				feedBack.setIsDelete(1);
				if (service.updateObject(feedBack)) {
					out.print("success");
				}else {
					out.print("fail");
				}
			}else {
				out.print("fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	@JSON(serialize=false)
	public FeedBack getFeedBack() {
		return feedBack;
	}

	public void setFeedBack(FeedBack feedBack) {
		this.feedBack = feedBack;
	}

}
