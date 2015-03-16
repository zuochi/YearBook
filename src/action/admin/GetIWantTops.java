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
		
		int photosCount = (Integer) service.getObjectByHql("select count(i.id) from IWantTop i where isDelete=0","getInteger");
		PageController pc = new PageController(photosCount, 1,20);
		pc.setCurrentPage(toPage);
		
		List<dto.Photo> photos = service.getDtoObjectsBySql("select * from photo where is_delete=0 and id in (select photo_id from i_want_top) order by upload_date desc", pc,new dto.Photo());
		request.setAttribute("objs", photos);
		request.setAttribute("pc", pc);
		
		return "report_getIWantTops";
	}
}
