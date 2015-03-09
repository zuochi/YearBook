package action.user;

import java.util.List;
import java.util.Properties;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import util.PageController;
import bean.PhotoAlbum;

@Controller
@Scope("prototype")
public class GetPhotoAlbumsByPerPage extends UserAction{
	@Override
	public String execute() {
		int photoAlbumscount = service.getTotalRowsByUserId(new Properties(),new PhotoAlbum(),Integer.parseInt(request.getParameter("userId")));
		Properties pro = new Properties();
		if (request.getParameter("userId") != null) {
			pro.setProperty("user.id", request.getParameter("userId"));
		}
		PageController pc = new PageController(photoAlbumscount, 1,6);//6个相册一排
		pc.setCurrentPage(toPage);
		List<PhotoAlbum> photoAlbums = service.getObjectsByPrepageAndProperties(pro, pc, new PhotoAlbum(),true);
		List<PhotoAlbum> photoAlbumsAll = service.getObjectsByProperties(pro,new PhotoAlbum(), true);
		if(photoAlbums.size()!=0 && photoAlbumsAll.size()!=0){
			session.put("photoAlbums", photoAlbums);
			session.put("photoAlbumsAll", photoAlbumsAll);
			session.put("photoAlbumPc", pc);
			session.put("photoAlbumscount", photoAlbumscount);
		}
		/*if("ajax".equals(request.getParameter("ajax"))){
			List<dto.PhotoAlbum> modelPhotoAlbums = new ArrayList<dto.PhotoAlbum>();
			for(int i=0 ; i<photoAlbums.size() ; i++){
				modelPhotoAlbums.add(new dto.PhotoAlbum(photoAlbums.get(i).getId(),photoAlbums.get(i).getUser().getId(),photoAlbums.get(i).getName(),photoAlbums.get(i).getPhotoUrl(),photoAlbums.get(i).getPermission(),photoAlbums.get(i).getPassword()));
			}
			out = response.getWriter();
			JSONArray json = JSONArray.fromObject(modelPhotoAlbums);
			out.print(json);
			out.flush();
			out.close();
			return null;
		}*/
		return "photo";
	}
}
