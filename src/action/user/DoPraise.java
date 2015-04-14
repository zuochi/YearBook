package action.user;

import java.io.IOException;
import java.sql.Timestamp;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.IWantTop;
import bean.Praise;
import bean.User;

@Controller
@Scope("prototype")
public class DoPraise extends UserAction{
	private Praise praise;
	private IWantTop i_want_top;

	@Override
	public String execute() {
		
		user = (User) session.get("user");
		
		try {
			out=response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if(user==null){
			out.print("please login.");
		}else{
			Praise praise_from_DB = (Praise) service.getObjectByHql("from Praise where user.id="+user.getId() + " and photo.id=" + praise.getPhoto().getId());
			
			//1.如果已经点赞，但是点赞是激活的，就取消点赞
			if(praise_from_DB!=null && praise_from_DB.getIsDelete()==0){
				praise_from_DB.setIsDelete(1);
				if(service.updateObject(praise_from_DB)){
					out.print("success");
				}else{
					out.print("fail");
				}
			//2.如果已经点赞，但是点赞是取消的，就激活点赞
			}else if(praise_from_DB!=null && praise_from_DB.getIsDelete()==1){
				praise_from_DB.setIsDelete(0);
				if(service.updateObject(praise_from_DB)){
					out.print("success");
				}else{
					out.print("fail");
				}
			//3.最后一种情况就是第一次点赞,则保存为一条新的点赞记录
			}else{
				praise.setUser(user);
				praise.setSignupDate(new Timestamp(System.currentTimeMillis()));
				praise.setIsDelete(1);
				//并更新该首页图片的首页点赞时间
				i_want_top = (IWantTop) service.getObjectByHql("from IWantTop where id="+i_want_top.getId());
				if(i_want_top!=null){
					i_want_top.setPraiseDate(new Timestamp(System.currentTimeMillis()));
					if(service.updateObject(i_want_top)){
						//最后保存点赞记录
						if(service.saveObject(praise)){
							out.print("success");
						}else{
							out.print("fail");
						}
					}
				}
			}
		}
		
		out.flush();
		out.close();
		
		return null;
	}

	public Praise getPraise() {
		return praise;
	}

	public void setPraise(Praise praise) {
		this.praise = praise;
	}

	public IWantTop getI_want_top() {
		return i_want_top;
	}

	public void setI_want_top(IWantTop i_want_top) {
		this.i_want_top = i_want_top;
	}
}
