package action.user;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import util.PageController;

@Controller
@Scope("prototype")
public class GetPhotosByPerPage extends UserAction{
	@Override
	public String execute() {
		if(!isLogin(user)){
			return "login";
		}
		
		int photosCount = (Integer) service.getObjectByHql("select count(p) from Photo p where isDelete=0 and user.id="+user.getId(),"getInteger");
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
			List<dto.Photo> photos = service.getDtoObjectsBySql("select * from photo where is_delete=0 and user_id="+user.getId()+" order by upload_date desc", pc,new dto.Photo());
			net.sf.json.JSONArray json = net.sf.json.JSONArray.fromObject(photos);
			out.print(json);
		}
		out.flush();
		out.close();
		return null;
	}
}
