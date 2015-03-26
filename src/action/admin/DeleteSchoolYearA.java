package action.admin;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.SchoolYear;

@Controller
@Scope("prototype")
public class DeleteSchoolYearA extends AdminAction {
	private SchoolYear schoolYear;
	
	@Override
	public String execute() {
		try {
			out = response.getWriter();
			schoolYear = (SchoolYear) service.getObjectByHql("from SchoolYear where isDelete=0 and id='" + schoolYear.getId()+"'");
			if(schoolYear!=null){
				schoolYear.setIsDelete(1);
				if (service.updateObject(schoolYear)) {
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
	public SchoolYear getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(SchoolYear schoolYear) {
		this.schoolYear = schoolYear;
	}
	
}
