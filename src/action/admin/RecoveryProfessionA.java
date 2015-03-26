package action.admin;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.Profession;

@Controller
@Scope("prototype")
public class RecoveryProfessionA extends AdminAction {
	private Profession profession;
	
	@Override
	public String execute() {
		try {
			out = response.getWriter();
			profession = (Profession) service.getObjectByHql("from Profession where isDelete=1 and id='" + profession.getId()+"'");
			if(profession!=null){
				profession.setIsDelete(0);
				if (service.updateObject(profession)) {
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
	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}
}
