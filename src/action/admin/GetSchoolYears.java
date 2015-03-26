package action.admin;

import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import util.CHW;
import util.PageController;
import bean.Reply;
import bean.SchoolYear;

@Controller
@Scope("prototype")
public class GetSchoolYears extends AdminAction{
	private SchoolYear schoolYear;
	
	@Override
	public String execute() {
		CHW chw = new CHW();
		
		if(schoolYear!=null){
			chw.setCondition("id", schoolYear.getId(), CHW.opr_equal);
			chw.setCondition("year", schoolYear.getYear(), CHW.opr_equal);
			chw.setCondition("isDelete", schoolYear.getIsDelete(), CHW.opr_equal);
		}else{
			schoolYear = new SchoolYear();
			schoolYear.setIsDelete(0);
			chw.setCondition("isDelete", schoolYear.getIsDelete(), CHW.opr_equal);
		}
		
		Integer schoolYearCount = (Integer) service.getObjectByHql("select count(id) from SchoolYear " + chw.getSqlWhere(), "getInteger");
		PageController pc = new PageController(schoolYearCount, 1,pageSize);
		pc.setCurrentPage(toPage);
		
		List<SchoolYear> schoolYears = service.getObjectsByHql("from SchoolYear " + chw.getSqlWhere() + " order by " + (orderBy!=null?orderBy:"year") + " " + (desc!=null?desc:""),pc);
		
		request.setAttribute("objs", schoolYears);
		request.setAttribute("pc", pc);
		request.setAttribute("params", this.schoolYear);
		request.setAttribute("pageSize", this.pageSize);
		
		return "admin_getSchoolYears";
	}

	@JSON(serialize=false)
	public SchoolYear getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(SchoolYear schoolYear) {
		this.schoolYear = schoolYear;
	}
	
}
