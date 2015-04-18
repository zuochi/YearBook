package action.admin;

import java.sql.Timestamp;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.IWantTop;

@Controller
@Scope("prototype")
public class ReviewIWantTops extends AdminAction{
	private IWantTop i_want_top;

	@Override
	public String execute() {
		try {
			out = response.getWriter();
			
			IWantTop iWantTopOld = (IWantTop) service.getObjectByHql("from IWantTop where isDelete=0 and id=" + i_want_top.getId());
			if(iWantTopOld!=null){
				//修改审核结果
				iWantTopOld.setStatus(i_want_top.getStatus());
				//添加审核时间
				iWantTopOld.setReviewDate(new Timestamp(System.currentTimeMillis()));
				//添加点赞时间，让图片顶置
				iWantTopOld.setPraiseDate(new Timestamp(System.currentTimeMillis()));
				if (service.updateObject(iWantTopOld)) {
					out.print("success");
				}else {
					out.print("fail");
				}
			}else {
				out.print("fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	//上首页状态操作
	public String updateStatusCover() {
		try {
			out = response.getWriter();
			//status=1确保上架图片通过审核
			IWantTop iWantTopOld = (IWantTop) service.getObjectByHql("from IWantTop where status=1 and isDelete=0 and id=" + i_want_top.getId());
			if(iWantTopOld!=null){
				//修改审核结果
				iWantTopOld.setStatusCover(i_want_top.getStatusCover());
				//添加图片的顶置时间，让图片顶置
				iWantTopOld.setTopDate(new Timestamp(System.currentTimeMillis()));
				if (service.updateObject(iWantTopOld)) {
					out.print("success");
				}else {
					out.print("fail");
				}
			}else {
				out.print("fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}
	
	//上首页状态操作
	public String overhead() {
		try {
			out = response.getWriter();
			//status=1 and statusCover=1确保图片通过审核，并已经上架到首页
			IWantTop iWantTopOld = (IWantTop) service.getObjectByHql("from IWantTop where status=1 and statusCover=1 and isDelete=0 and id=" + i_want_top.getId());
			if(iWantTopOld!=null){
				//更新图片的顶置时间，让图片顶置
				iWantTopOld.setTopDate(new Timestamp(System.currentTimeMillis()));
				if (service.updateObject(iWantTopOld)) {
					out.print("success");
				}else {
					out.print("fail");
				}
			}else {
				out.print("fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}
	
	@JSON(serialize=false)
	public void setI_want_top(IWantTop i_want_top) {
		this.i_want_top = i_want_top;
	}
	
	public IWantTop getI_want_top() {
		return i_want_top;
	}
}
