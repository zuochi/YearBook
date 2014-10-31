package action.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import util.PageController;
import bean.Photo;

@Controller
@Scope("prototype")
public class GetPhotosByPerPage extends UserAction{
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Properties pro = new Properties();
		pro.setProperty("user.id", request.getParameter("userId"));
		//pro.setProperty("photoAlbum.id", request.getParameter("photoAlbumId"));
		int photosCount = service.getTotalRowsByProperties(pro, new Photo(),true);
		PageController pc = new PageController(photosCount, 1,20);
		pc.setCurrentPage(toPage);
		List<Photo> photos = service.getObjectsByPrepageAndProperties(pro, pc,new Photo(), true);
		try {
			out = response.getWriter();
			if(toPage>pc.getTotalPages()){
				out.print("false");
			}else{
				// 让model层的photo作为json的媒介
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
		}
		out.flush();
		out.close();
		if(photos.size()!=0){
			request.setAttribute("photos", photos);
			request.setAttribute("photosPc", pc);
		}
		return "ajax";
	}
}
