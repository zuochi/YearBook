package action.admin;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.Photo;
import bean.Reply;
import util.CHW;
import util.PageController;

@Controller
@Scope("prototype")
public class GetPhotos extends AdminAction{
	private Photo photo;

	@Override
	public String execute() {
		CHW chw = new CHW();
		
		if(photo!=null){
			chw.setCondition("id", photo.getId(), CHW.opr_equal);
			chw.setCondition("user.id", photo.getUser().getId(), CHW.opr_equal);
			chw.setCondition("user.name", photo.getUser().getName(), CHW.opr_like);
			chw.setCondition("isDelete", photo.getIsDelete(), CHW.opr_equal);
			chw.setCondition("isAccusation", photo.getIsAccusation(), CHW.opr_equal);
		}else{
			photo = new Photo();
			photo.setIsDelete(0);
			photo.setIsAccusation(1);
			chw.setCondition("isDelete", photo.getIsDelete(), CHW.opr_equal);
			chw.setCondition("isAccusation", photo.getIsAccusation(), CHW.opr_equal);
		}

		int photosCount = (Integer) service.getObjectByHql("select count(id) from Photo " + chw.getSqlWhere(),"getInteger");
		PageController pc = new PageController(photosCount, 1,this.pageSize);
		pc.setCurrentPage(toPage);

		List<Photo> photos = service.getObjectsByHql("from Photo " + chw.getSqlWhere() + " order by " + (orderBy!=null?orderBy:"uploadDate") + " " + (desc!=null?desc:""), pc, "getUser");
		
		request.setAttribute("objs", photos);
		request.setAttribute("pc", pc);
		request.setAttribute("pageSize", this.pageSize);
		request.setAttribute("params", this.photo);
		
		return "admin_getPhotos";
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	
}
