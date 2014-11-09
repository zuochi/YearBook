package action.user;

import java.util.List;
import java.util.Properties;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.Reply;

import util.PageController;


@Controller
@Scope("prototype")
public class GetMessage extends UserAction{
	private String type ;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		if("getPhotoComments".equals(type)){
			
		}
		
		return null;
	}

	private boolean getPhotoComment(){
		try {
			//获取相片数量
			Properties pro = new Properties();
			pro.setProperty("photo.id", photo.getId().toString());
			int photosCommentCount = service.getTotalRowsByProperties(pro, new Reply(),true);
					
			//获取相片评论
			PageController photoReplyPc = new PageController(photosCommentCount, 1,10);
			photoReplyPc.setCurrentPage(toPage);
			List<Reply> photoReplys = service.getObjectsByPrepageAndProperties(pro, photoReplyPc,new Reply(), true);
							
			if(photoReplys.size()!=0){
				request.setAttribute("photoReplys", photoReplys);
				request.setAttribute("photoReplysPc", photoReplyPc);
				request.setAttribute("photosCount", photosCommentCount);
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void setType(String type) {
		this.type = type;
	}
}
