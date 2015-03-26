package action.admin;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.SchoolYear;

@Controller
@Scope("prototype")
public class AddSchoolYearA extends AdminAction {
	private SchoolYear schoolYear;
	
	@Override
	public String execute() {
		try {
			out = response.getWriter();
			
			SchoolYear schoolYearOld = (SchoolYear) service.getObjectByHql("from SchoolYear where year=" + schoolYear.getYear());
			
			if(schoolYear!=null && schoolYearOld==null){
				if (service.saveObject(schoolYear)) {
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
