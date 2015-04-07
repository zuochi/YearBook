package action.user;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import util.PageController;
import bean.IWantTop;


@Controller
@Scope("prototype")
public class GetIndexPhotos extends UserAction{
	private IWantTop i_want_top;
	
	@Override
	public String execute() {
		int photosCount = (Integer) service.getObjectByHql("select count(id) from IWantTop where isDelete=0 and status=1 and user.profession.id="+i_want_top.getUser().getProfession().getId(),"getInteger");
		PageController pc = new PageController(photosCount, 1,20);
		pc.setCurrentPage(toPage);
		//TODO 暂时取消专业限制
		//List<dto.IWantTop> iWantTops = service.getDtoObjectsBySql("select i.id,i.photo_id,ph.url_thumb,ph.url,i.signup_date,i.status,i.is_delete,i.user_id,u.name,h.url_m,(select count(id) from praise p where p.is_delete=0 and p.photo_id=i.photo_id) as praise_count from i_want_top i LEFT JOIN user u  on i.user_id=u.id LEFT JOIN head_photo h on u.head_photo_id=h.id LEFT JOIN photo ph on ph.id=i.photo_id where i.status=1 and i.is_delete=0 and u.profession_id="+i_want_top.getUser().getProfession().getId()+" ORDER BY i.review_date desc ", pc,new dto.IWantTop());
		List<dto.IWantTop> iWantTops = service.getDtoObjectsBySql("select i.id,i.photo_id,ph.url_thumb,ph.url,i.signup_date,i.status,i.is_delete,i.user_id,u.name,h.url_m,(select count(id) from praise p where p.is_delete=0 and p.photo_id=i.photo_id) as praise_count from i_want_top i LEFT JOIN user u  on i.user_id=u.id LEFT JOIN head_photo h on u.head_photo_id=h.id LEFT JOIN photo ph on ph.id=i.photo_id where i.status=1 and i.is_delete=0 ORDER BY i.review_date desc ", pc,new dto.IWantTop());
		
		JSONArray json = JSONArray.fromObject(iWantTops);
		
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

	public IWantTop getI_want_top() {
		return i_want_top;
	}

	public void setI_want_top(IWantTop i_want_top) {
		this.i_want_top = i_want_top;
	}
	
}
