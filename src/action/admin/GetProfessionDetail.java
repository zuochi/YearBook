package action.admin;

import java.io.IOException;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.Profession;
import bean.SchoolYear;
import bean.User;

@Controller
@Scope("prototype")
public class GetProfessionDetail extends AdminAction{
	private Profession profession;
	
	@Override
	public String execute() {
		profession = (Profession) service.getObjectByHql("from Profession where id="+profession.getId());

		try {
			out = response.getWriter();
		
			if(profession!=null){
				//ProfessionDTO dto = new ProfessionDTO(profession.getId(),profession.getName(),profession.getContext(),profession.getIsDelete());
				JsonConfig config = new JsonConfig();
				config.setExcludes( new String[]{ "users"} );
				
				JSONObject json = JSONObject.fromObject(profession,config);
				
				out.print(json);
			}else{
				out.print("error");
			}
			
			out.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		
		return null;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

}