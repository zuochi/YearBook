package action.admin;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class GetPhotos extends AdminAction{

	@Override
	public String execute() throws Exception {
		return super.execute();
	}
}
