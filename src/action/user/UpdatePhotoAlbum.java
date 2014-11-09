package action.user;

import java.io.IOException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class UpdatePhotoAlbum extends UserAction{
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try {
			photoAlbum.setId(Integer.parseInt(request
					.getParameter("photoAlbum.id")));
			photoAlbum.setName(request.getParameter("photoAlbum.name"));
			photoAlbum.setPermission(Integer.parseInt(request
					.getParameter("photoAlbum.permission")));
			photoAlbum.setPassword(request.getParameter("photoAlbum.password"));
			out = response.getWriter();
			if (service.updatePhotoAlbum(photoAlbum)) {
				out.print("success");
			} else {
				out.print("fail");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}
}
