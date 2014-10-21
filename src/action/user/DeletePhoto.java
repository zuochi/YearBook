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
			Properties pro = new Properties();
			pro.setProperty("id", photo.getId().toString());
			Properties pro2 = new Properties();
			pro2.setProperty("photo.id", photo.getId().toString());
			photo = (Photo) service.getObjectByProperties(pro, new Photo());
			//IWantTop iWantTop = (IWantTop) service.getObjectByProperties(pro2, new IWantTop());
			//if(iWantTop!=null){
				//service.delObject(iWantTop);
			//}
			//if (service.delObject(photo)) {
			photo.setIsDelete(1);
			if (service.updateObject(photo)) {
				out.print("success");
			} else {
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
