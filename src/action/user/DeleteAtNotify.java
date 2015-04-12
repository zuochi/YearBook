package action.user;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.AtNotify;
import bean.Reply;

@Controller
@Scope("prototype")
public class DeleteAtNotify extends UserAction {
	private AtNotify atNotify;
	
	@Override
	public String execute() {
		try {
			out = response.getWriter();
			atNotify = (AtNotify) service.getObjectByHql("from AtNotify where isDelete=0 and id='" + atNotify.getId()+"'");
			if(atNotify!=null){
				atNotify.setIsDelete(1);
				if (service.updateObject(atNotify)) {
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
	public AtNotify getAtNotify() {
		return atNotify;
	}

	public void setAtNotify(AtNotify atNotify) {
		this.atNotify = atNotify;
	}
}
