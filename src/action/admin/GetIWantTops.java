package action.admin;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import util.PageController;

@Controller
@Scope("prototype")
public class GetIWantTops extends AdminAction{

	@Override
	public String execute() {
		/*if(!isLogin(user)){
			return "login";
		}*/
		
		int photosCount = (Integer) service.getObjectByHql("select count(p.id) from Photo p where isDelete=0","getInteger");
		PageController pc = new PageController(photosCount, 1,20);
		pc.setCurrentPage(toPage);
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(toPage>pc.getTotalPages()){
			out.print("false");
		}else{
			List<dto.Photo> photos = service.getDtoObjectsBySql("select * from photo where is_delete=0 order by upload_date desc", pc,new dto.Photo());
			request.setAttribute("objs", photos);
			request.setAttribute("pc", pc);
		}
		
		return "report_getIWantTops";
	}
}
