package action.admin;

import java.sql.Timestamp;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.FeedBack;
import bean.User;

@Controller
@Scope("prototype")
public class ReviewFeedBacks extends AdminAction{
	private FeedBack feedBack;

	@Override
	public String execute() {
		try {
			out = response.getWriter();
			
			User user = (User) session.get("user");
			
			//TODO 过滤器
			if(user==null){
				return "login";
			}
			
			FeedBack feedBackOld = (FeedBack) service.getObjectByHql("from FeedBack where isDelete=0 and id=" + feedBack.getId());
			if(feedBackOld!=null){
				//修改审核结果
				feedBackOld.setStatus(feedBack.getStatus());
				feedBackOld.setUserDeal(user);
				//添加审核时间
				if(feedBack.getStatus()==0){
					feedBackOld.setDealDate(null);
				}else if(feedBack.getStatus()==1){
					feedBackOld.setDealDate(new Timestamp(System.currentTimeMillis()));
				}
				//更新到数据库
				if (service.updateObject(feedBackOld)) {
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
