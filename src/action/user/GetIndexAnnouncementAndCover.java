package action.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import util.PageController;
import bean.Announcement;
import bean.IWantTop;
import bean.Praise;
import bean.Profession;
import bean.User;


@Controller
@Scope("prototype")
public class GetIndexAnnouncementAndCover extends UserAction{
	@Override
	public String execute() {
		List<dto.IWantTop> iWantTops = service.getDtoObjectsBySql(
				"select i.id,i.photo_id,ph.url_thumb,ph.url,i.signup_date,i.review_date,i.status,i.is_delete,i.user_id,u.name,h.url_m,"
				+ "(select count(id) from praise p where p.is_delete=1 and p.photo_id=i.photo_id) as praise_count "
				+ "from i_want_top i LEFT JOIN user u  on i.user_id=u.id "
				+ "LEFT JOIN head_photo h on u.head_photo_id=h.id LEFT JOIN photo ph on ph.id=i.photo_id "
				+ "where i.status=1 and i.is_delete=0 and status_cover=1 "
						+ "ORDER BY i.top_date desc limit 5", null,new dto.IWantTop());
		
		List<dto.AnnouncementAndCover> announcementAndCovers = service.getDtoObjectsBySql(
				"select a.title,a.context,a.user_id, u.name "
				+ "from announcement a "
				+ "LEFT JOIN user u on u.id=a.user_id "
				+ "where a.is_delete=0 order by a.top_date desc limit 5", null, new dto.AnnouncementAndCover());
		
		//放置
		if(announcementAndCovers.size()<iWantTops.size()){
			for(int i=0 ; i<announcementAndCovers.size() ; i++){
				announcementAndCovers.get(i).setI_want_top(iWantTops.get(i));
			}
			for(int i=(announcementAndCovers.size()) ; i<(iWantTops.size()); i++){
				dto.AnnouncementAndCover announcementAndCover = new dto.AnnouncementAndCover();
				announcementAndCover.setI_want_top(iWantTops.get(i));
				announcementAndCovers.add(announcementAndCover);
			}
		}else{
			for(int i=0 ; i<iWantTops.size() ; i++){
				announcementAndCovers.get(i).setI_want_top(iWantTops.get(i));
			}
		}
		
		Map<String, Object> datas = new HashMap<String, Object>();
		datas.put("objs", announcementAndCovers);
		
		JSONArray json = JSONArray.fromObject(datas);
		
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
