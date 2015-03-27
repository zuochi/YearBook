package action.admin;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.FeedBack;

@Controller
@Scope("prototype")
public class RecoveryFeedBackA extends AdminAction {
	private FeedBack feedBack;
	
	@Override
	public String execute() {
		try {
			out = response.getWriter();
			feedBack = (FeedBack) service.getObjectByHql("from FeedBack where isDelete=1 and id='" + feedBack.getId()+"'");
			if(feedBack!=null){
				feedBack.setIsDelete(0);
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
