package action.user;

import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.User;

@Controller
@Scope("prototype")
public class NearBy extends UserAction {
	@Override
	public String execute() {
		try {
			user = (User) session.get("user");
			
			if(user==null){
				return "login";
			}
			
			user = (User) service.getObjectByHql("from User where isDelete=0 and id="+user.getId(),  "getHeadPhoto", "getProfession","getSchoolYear");
			
			out = response.getWriter();
			List<dto.SearchUserInfo> users = service.getDtoObjectsBySql(
					" select u.id,u.name,"+
					 "u.sex," +
					 "u.sign," +
					 "u.home_town," +
					 "y.year," +
					 "p.name as professionName," +
					 "h.url_m," +
					 "h.url_l,"+
						" u.id as post,"+
						" u.id as following,"+
						" u.id as follower"+
					" from user u "+
						" LEFT JOIN school_year y on u.school_year_id=y.id "+ 
						" LEFT JOIN profession p on p.id=u.profession_id "+
						" LEFT JOIN head_photo h on h.id=u.head_photo_id "+
						" where "
						+ "u.is_delete=0 and u.id!="+user.getId()+" and "
						+ "(y.id="+user.getSchoolYear().getId()+" "
								+ " or p.id="+user.getProfession().getId()
								+ " or home_town like '%"+user.getHomeTown()+"%')"
								+ " and u.id not in (select f.friend_id from friend_list f where f.is_delete=0 and f.user_id="+user.getId()+") limit 12", null, new dto.SearchUserInfo());
			//如果推荐不为空 写出到前台
			if(users!=null){
				out = response.getWriter();
				JSONArray json = JSONArray.fromObject(users);
				out.print(json);
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
