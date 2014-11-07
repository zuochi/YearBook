package action.user;

import java.util.Properties;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.IWantTop;
import bean.Photo;

@Controller
@Scope("prototype")
public class DeletePhoto extends UserAction {
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try {
			out = response.getWriter();
			photo = (Photo) service.getObjectByHql("from Photo where isDelete=0 and id=" + photo.getId());
			if(photo!=null){
				photo.setIsDelete(1);
				if (service.updateObject(photo)) {
					out.print("success");
				}else {
					out.print("fail");
				}
			}else {
				out.print("fail");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return "ajax";
	}
}
