package action.admin;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.Profession;

@Controller
@Scope("prototype")
public class EditProfession extends AdminAction {
	private Profession profession;
	
	@Override
	public String execute() {
		try {
			//判断是否使用旧密码
			out = response.getWriter();
			
			Profession professionOld = (Profession) service.getObjectByHql("from Profession where name='" + profession.getName()+"'");
			
			//如果有重名
			if((professionOld!=null && profession.getId().intValue()==professionOld.getId().intValue()) || professionOld==null){
				if (service.updateObject(profession)) {
					out.print("success");
				} else {
					out.print("fail");
				}
			} else {
				out.print("fail");
			}
		} catch (Exception e) {
			out.print("fail");
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

}
