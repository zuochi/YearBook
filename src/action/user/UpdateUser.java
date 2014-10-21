package action.user;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.Profession;
import bean.SchoolYear;

@Controller
@Scope("prototype")
public class UpdateUser extends UserAction {
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try {
			user.setId(Integer.parseInt(request.getParameter("user.id")));
			//user.setName(new String(request.getParameter("user.name").getBytes("ISO-8859-1"),"UTF-8"));// 转码了
			user.setWeChat(null);
			if (request.getParameter("user.password") != "") {
				user.setPassword(request.getParameter("user.password"));
			} else {
				user.setPassword(request.getParameter("user.passwordOld"));
			}
			user.setSchoolYear(new SchoolYear());
			user.getSchoolYear()
					.setId(Integer.parseInt(request
							.getParameter("user.schoolYear.id")));
			user.setProfession(new Profession());
			user.getProfession().setId(Integer.parseInt(request.getParameter("user.profession.id")));
			user.setSex(Integer.parseInt(request.getParameter("user.sex")));
			if (request.getParameter("user.qq") != "") {
				user.setQq(request.getParameter("user.qq"));
			}
			if (request.getParameter("user.weChat") != "") {
				//user.setWeChat(new String(request.getParameter("user.weChat").getBytes("ISO-8859-1"),"UTF-8"));// 转码了
				user.setWeChat(request.getParameter("user.weChat"));
			}
			if (request.getParameter("user.email") != "") {
				user.setEmail(request.getParameter("user.email"));
			}
			out = response.getWriter();
			if (service.updateUser(user)) {
				out.print("success");
				session.put("user", user);
			} else {
				out.print("fail");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.print("fail");
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return "ajax";
	}
}
