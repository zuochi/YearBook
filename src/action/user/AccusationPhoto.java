package action.user;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.Photo;

@Controller
@Scope("prototype")
public class AccusationPhoto extends UserAction {
	@Override
	public String execute() {
		try {
			out = response.getWriter();
			photo = (Photo) service.getObjectByHql("from Photo where isDelete=0 and id=" + photo.getId());
			if(photo!=null){
				photo.setIsAccusation(1);
				if (service.updateObject(photo)) {
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
}
