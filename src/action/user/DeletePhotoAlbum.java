package action.user;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class DeletePhotoAlbum extends UserAction {
	@Override
	public String execute() {
		try {
			photoAlbum.setId(Integer.parseInt(request
					.getParameter("photoAlbum.id")));
			photoAlbum.setName(request.getParameter("photoAlbum.name"));
			out = response.getWriter();
			if (service.delObject(photoAlbum)) {
				out.print("success");
			} else {
				out.print("fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}
}
