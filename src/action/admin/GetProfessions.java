package action.admin;

import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import util.CHW;
import util.PageController;
import bean.Profession;

@Controller
@Scope("prototype")
public class GetProfessions extends AdminAction{
	private Profession profession;
	
	@Override
	public String execute() {
		CHW chw = new CHW();
		
		if(this.profession!=null){
			chw.setCondition("id", this.profession.getId(), CHW.opr_like);
			chw.setCondition("name", this.profession.getName(), CHW.opr_like);
			chw.setCondition("context", this.profession.getContext(), CHW.opr_like);
			chw.setCondition("isDelete", this.profession.getIsDelete(), CHW.opr_equal);
		}else{
			this.profession = new Profession();
			this.profession.setIsDelete(0);
			chw.setCondition("isDelete", this.profession.getIsDelete(), CHW.opr_equal);
		}
		
		Integer professionCount = (Integer) service.getObjectByHql("select count(id) from Profession " + chw.getSqlWhere(), "getInteger");
		PageController pc = new PageController(professionCount, 1, pageSize);
		pc.setCurrentPage(toPage);
		
		List<Profession> professions = service.getObjectsByHql("from Profession " + chw.getSqlWhere() + " order by " + (orderBy!=null?orderBy:"id") + " " + (desc!=null?desc:""),pc);
		
		request.setAttribute("objs", professions);
		request.setAttribute("params", this.profession);
		request.setAttribute("pc", pc);
		request.setAttribute("pageSize", this.pageSize);
		
		return "admin_getProfessions";
	}

	@JSON(serialize=false)
	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}
	
}
