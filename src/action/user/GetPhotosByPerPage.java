package action.user;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import util.PageController;

@Controller
@Scope("prototype")
public class GetPhotosByPerPage extends UserAction{
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(!isLogin(user)){
			return "login";
		}
		
		int photosCount = (Integer) service.getObjectByHql("select count(p) from Photo p where isDelete=0 and user.id="+user.getId(),"getInteger");
		PageController pc = new PageController(photosCount, 1,20);
		pc.setCurrentPage(toPage);
		out = response.getWriter();
		if(toPage>pc.getTotalPages()){
			out.print("false");
		}else{
			List<dto.Photo> photos = service.getDtoObjectsBySql("select * from photo where is_delete=0 and user_id="+user.getId()+" order by upload_date desc", pc,new dto.Photo());
			net.sf.json.JSONArray json = net.sf.json.JSONArray.fromObject(photos);
			out.print(json);
		}
		/*List<Photo> photos = service.getObjectsByHql("from Photo where isDelete=0 and user.id="+user.getId()+" order by uploadDate desc", pc);
		try {
			out = response.getWriter();
			if(toPage>pc.getTotalPages()){
				out.print("false");
			}else{
				//让model层的photo作为json的媒介
				List<dto.Photo> modelPhotos = new ArrayList<dto.Photo>();
				for (int i = 0; i < photos.size(); i++) {
					dto.Photo modelPhoto = new dto.Photo(photos.get(i).getId(), photos.get(i).getUser().getId(),0, 
							photos.get(i).getName(), photos.get(i).getUrl(), photos.get(i).getUrlThumb(),
							photos.get(i).getUploadDate(),0);
					modelPhotos.add(modelPhoto);
				}
				net.sf.json.JSONArray json = net.sf.json.JSONArray.fromObject(modelPhotos);
				out.print(json);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		out.flush();
		out.close();
		return null;
	}
}
