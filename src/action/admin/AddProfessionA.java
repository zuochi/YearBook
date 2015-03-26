package action.admin;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.Profession;

@Controller
@Scope("prototype")
public class AddProfessionA extends AdminAction {
	private Profession profession;
	
	@Override
	public String execute() {
		try {
			out = response.getWriter();
			
			Profession professionOld = (Profession) service.getObjectByHql("from Profession where name='" + profession.getName() + "'");
			
			if(profession!=null && professionOld==null){
				if (service.saveObject(profession)) {
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
