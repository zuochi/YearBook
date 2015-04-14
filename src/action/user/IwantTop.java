package action.user;

import java.sql.Timestamp;
import java.util.Properties;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.IWantTop;

@Controller
@Scope("prototype")
public class IwantTop extends UserAction {

	@Override
	public String execute() {
		try {
			out = response.getWriter();
			Properties pro = new Properties();
			pro.setProperty("photo.id", photo.getId().toString());
			if (service.isDuplicationOfProperties(pro, new IWantTop()) == false) {
				IWantTop iWantTop = new IWantTop(user, photo, new Timestamp(System.currentTimeMillis()), 0,0);
				if (service.saveObject(iWantTop)) {
					out.print("success");
				} else {
					out.print("fail");
				}
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
