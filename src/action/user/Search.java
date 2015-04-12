package action.user;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import util.CHW;
import util.CommonUtils;
import util.PageController;

@Controller
@Scope("prototype")
public class Search extends UserAction {
	private String keyword;
	private int pageSize = 10;
	
	@Override
	public String execute() {
		CHW chw = new CHW();
		
		if(!CommonUtils.isBlank(keyword)){
			chw.setSearchCondition("name", keyword);
			chw.setSearchCondition("sign", keyword);
			chw.setSearchCondition("sex", keyword);
			chw.setSearchCondition("homeTown", keyword);
			chw.setSearchCondition("schoolYear.year", keyword);
			chw.setSearchCondition("profession.name", keyword);
		}
		
		Integer count = (Integer) service.getObjectByHql("select count(id) from User " + chw.getSearchSqlWhere(), "getInteger");
		 
		PageController pc = new PageController(count, 1, pageSize);
		pc.setCurrentPage(toPage);
		
		if(!CommonUtils.isBlank(keyword)){
			chw = new CHW();
			chw.setSearchCondition("u.name", keyword);
			chw.setSearchCondition("u.sign", keyword);
			chw.setSearchCondition("u.sex", keyword);
			chw.setSearchCondition("u.home_town", keyword);
			chw.setSearchCondition("y.year", keyword);
			chw.setSearchCondition("p.name", keyword);
		}
		
		//List<User> users = service.getObjectsByHql("from User " + chw.getSearchSqlWhere(), pc,"getHeadPhoto","getProfession","getSchoolYear");
		List<dto.SearchUserInfo> users = service.getDtoObjectsBySql(
				" select u.id,u.name,u.sex,u.sign,u.home_town,y.year,p.name as professionName,h.url_m,h.url_l,"+
					" (select count(id) from photo ph where u.id=ph.user_id and ph.is_delete=0) as post,"+
					" (select count(id) from friend_list f where f.user_id=u.id and f.is_delete=0) as following,"+
					" (select count(id) from friend_list fe where fe.friend_id=u.id and fe.is_delete=0) as follower"+
				" from user u "+
					" LEFT JOIN school_year y on u.school_year_id=y.id "+ 
					" LEFT JOIN profession p on p.id=u.profession_id "+
					" LEFT JOIN head_photo h on h.id=u.head_photo_id " + chw.getSearchSqlWhere() , pc, new dto.SearchUserInfo());
							
		request.setAttribute("objs", users);
		request.setAttribute("pc", pc);
		request.setAttribute("keyword", keyword);
		request.setAttribute("pageSize", pageSize);
		
		return "search";
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
