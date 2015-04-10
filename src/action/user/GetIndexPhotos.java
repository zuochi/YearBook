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
import bean.IWantTop;
import bean.Praise;
import bean.Profession;
import bean.User;


@Controller
@Scope("prototype")
public class GetIndexPhotos extends UserAction{
	private IWantTop i_want_top;
	
	@Override
	public String execute() {
		//如果没有选择专业，则随机帮用户选取一个
		/*if(i_want_top.getUser().getProfession().getId()==null || i_want_top.getUser().getProfession().getId()==0){
			i_want_top.getUser().getProfession().setId(getRamdomProfessionId());
		}*/
		
		int photosCount = (Integer) service.getObjectByHql("select count(id) from IWantTop where isDelete=0 and status=1 and user.profession.id="+i_want_top.getUser().getProfession().getId(),"getInteger");
		PageController pc = new PageController(photosCount, 1,5);
		pc.setCurrentPage(toPage);
		
		List<dto.IWantTop> iWantTops = service.getDtoObjectsBySql("select i.id,i.photo_id,ph.url_thumb,ph.url,i.signup_date,i.review_date,i.status,i.is_delete,i.user_id,u.name,h.url_m,(select count(id) from praise p where p.is_delete=1 and p.photo_id=i.photo_id) as praise_count from i_want_top i LEFT JOIN user u  on i.user_id=u.id LEFT JOIN head_photo h on u.head_photo_id=h.id LEFT JOIN photo ph on ph.id=i.photo_id where i.status=1 and i.is_delete=0 and u.profession_id="+i_want_top.getUser().getProfession().getId()+" ORDER BY i.review_date desc ", pc,new dto.IWantTop());
		//List<dto.IWantTop> iWantTops = service.getDtoObjectsBySql("select i.id,i.photo_id,ph.url_thumb,ph.url,i.signup_date,i.review_date,i.status,i.is_delete,i.user_id,u.name,h.url_m,(select count(id) from praise p where p.is_delete=1 and p.photo_id=i.photo_id) as praise_count from i_want_top i LEFT JOIN user u  on i.user_id=u.id LEFT JOIN head_photo h on u.head_photo_id=h.id LEFT JOIN photo ph on ph.id=i.photo_id where i.status=1 and i.is_delete=0 ORDER BY i.review_date desc ", pc,new dto.IWantTop());
		
		user = (User) session.get("user");
		//如果有登陆，则会判断登陆者是否已经点赞过图片？
		if(user!=null){
			for(dto.IWantTop i_want_top_in : iWantTops){
				i_want_top_in = isPraise(i_want_top_in);
			}
		}
		
		Map<String, Object> datas = new HashMap<String, Object>();
		datas.put("objs", iWantTops);
		datas.put("pc", pc);
		
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
	
	/**
	 * 获取随机的专业id
	 */
	private Integer getRamdomProfessionId(){
		List<Profession> professions = (List<Profession>) service.getObjectsByHql("from Profession",null);
		
		if(professions!=null){
			System.out.println(professions.get(new Random().nextInt(professions.size()-1)).getId());
			return professions.get(new Random().nextInt(professions.size()-1)).getId();
		}
		return null;
	}
	
	/**
	 * 判断是否点赞，并重新设置点赞的结果
	 */
	private dto.IWantTop isPraise(dto.IWantTop i_want_top){
		Praise praise = (Praise) service.getObjectByHql("from Praise where user.id="+user.getId() + " and photo.id=" + i_want_top.getPhoto_id());
		
		if(praise!=null){
			i_want_top.setIs_praise(praise.getIsDelete());
		}else{
			i_want_top.setIs_praise(0);
		}
		
		return i_want_top;
	}
	
	public IWantTop getI_want_top() {
		return i_want_top;
	}

	public void setI_want_top(IWantTop i_want_top) {
		this.i_want_top = i_want_top;
	}
	
}
