package action.user;

import java.io.IOException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class UpdatePhoto extends UserAction{
	public String updateDescription(){
		try {
			out = response.getWriter();
			photo.setIsDelete(0);
			if(service.updateObject(photo)){
				out.print("success");
			}else{
				out.print("fail");
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
