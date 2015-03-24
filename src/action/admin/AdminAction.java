package action.admin;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import service.Service;
import util.CommonUtils;
import bean.User;

@Controller
@Scope("prototype")
public class AdminAction extends ActionSupport implements SessionAware {
	@Autowired
	protected Service service;
	protected int toPage;
	protected String orderBy;
	protected String desc;
	protected HttpServletRequest request = ServletActionContext.getRequest();
	protected HttpServletResponse response = ServletActionContext.getResponse();
	protected PrintWriter out;
	protected Map<String, Object> session;
	
	public void setService(Service service) {
		this.service = service;
	}

	public void setToPage(int toPage) {
		this.toPage = toPage;
	}
	
	public void setOrderBy(String orderBy) {
		if(!CommonUtils.isBlank(orderBy)){
			this.orderBy = orderBy;
		}else{
			this.orderBy = null;
		}
		request.setAttribute("orderBy", orderBy);
	}

	public void setDesc(String desc) {
		if(!CommonUtils.isBlank(desc)){
			this.desc = desc;
		}else{
			this.desc = "";
		}
		request.setAttribute("desc", desc);
	}

	//判断是否登录了
	protected boolean isLogin(User user){
		if(user==null){
			return false;
		}else{
			return true;
		}
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		response.setCharacterEncoding("utf-8");
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		this.session = session;
	}

}
