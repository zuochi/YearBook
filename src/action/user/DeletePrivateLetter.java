package action.user;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.PrivateLetter;

@Controller
@Scope("prototype")
public class DeletePrivateLetter extends UserAction {
	private PrivateLetter privateLetter;
	
	@Override
	public String execute() {
		try {
			out = response.getWriter();
			privateLetter = (PrivateLetter) service.getObjectByHql("from PrivateLetter where isDelete=0 and id='" + privateLetter.getId()+"'");
			if(privateLetter!=null){
				privateLetter.setIsDelete(1);
				if (service.updateObject(privateLetter)) {
					out.print("success");
				}else {
					out.print("fail");
				}
			}else {
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

	@JSON(serialize=false)
	public PrivateLetter getPrivateLetter() {
		return privateLetter;
	}

	public void setPrivateLetter(PrivateLetter privateLetter) {
		this.privateLetter = privateLetter;
	}

}
