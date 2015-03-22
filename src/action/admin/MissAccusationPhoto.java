package action.admin;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.Photo;

@Controller
@Scope("prototype")
public class MissAccusationPhoto extends AdminAction {
	private Photo photo;
	
	@Override
	public String execute() {
		try {
			out = response.getWriter();
			photo = (Photo) service.getObjectByHql("from Photo where isDelete=0 and id=" + photo.getId());
			if(photo!=null){
				photo.setIsAccusation(0);
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

	@JSON(serialize=false)
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
}
