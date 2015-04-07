package action.user;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.Profession;

@Controller
@Scope("prototype")
public class GetIndexProfessions extends UserAction{
	@Override
	public String execute() {
		List<Profession> professions = service.getObjectsByHql("from Profession where isDelete=0",null);
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] {"users"});
		
		JSONArray json = JSONArray.fromObject(professions,jsonConfig);
		
		try {
			out = response.getWriter();
			out.print(json);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			out.flush();
			out.close();
		}
		
		return null;
	}
}
