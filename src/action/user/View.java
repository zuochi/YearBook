package action.user;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.Photo;

@Controller
@Scope("prototype")
public class View extends UserAction {
	@Override
	public String execute() {
		photo = (Photo) service.getObjectByHql("from Photo where isDelete=0 and id=" + photo.getId(),"getUser");
		
		request.setAttribute("obj", photo);
		
		return "view";
	}
}
