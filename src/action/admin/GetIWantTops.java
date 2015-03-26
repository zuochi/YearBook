package action.admin;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.IWantTop;
import util.CHW;
import util.PageController;

@Controller
@Scope("prototype")
public class GetIWantTops extends AdminAction{
	private IWantTop iwant;

	@Override
	public String execute() {
		CHW chw = new CHW();
		
		if(iwant!=null){
			chw.setCondition("id", iwant.getId(), CHW.opr_equal);
			chw.setCondition("user.id", iwant.getUser().getId(), CHW.opr_equal);
			chw.setCondition("user.name", iwant.getUser().getName(), CHW.opr_like);
			chw.setCondition("status", iwant.getStatus(), CHW.opr_equal);
		}
		
		int photosCount = (Integer) service.getObjectByHql("select count(id) from IWantTop where isDelete=0","getInteger");
		PageController pc = new PageController(photosCount, 1,pageSize);
		pc.setCurrentPage(toPage);
		
		List<IWantTop> iWantTops = service.getObjectsByHql("from IWantTop " + chw.getSqlWhere() + " order by " + (orderBy!=null?orderBy:"signupDate") + " " + (desc!=null?desc:""), pc, "getUser","getPhoto");

		request.setAttribute("objs", iWantTops);
		request.setAttribute("pc", pc);
		request.setAttribute("params", iwant);
		request.setAttribute("pageSize", pageSize);
		
		return "admin_getIWantTops";
	}

	public IWantTop getIwant() {
		return iwant;
	}

	public void setIwant(IWantTop iwant) {
		this.iwant = iwant;
	}
}
