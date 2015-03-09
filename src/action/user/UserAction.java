﻿package action.user;

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
import bean.HeadPhoto;
import bean.Photo;
import bean.PhotoAlbum;
import bean.User;
@Controller
@Scope("prototype")
public class UserAction extends ActionSupport implements SessionAware {
	@Autowired
	protected Service service;
	protected User user;
	protected Photo photo;
	protected PhotoAlbum photoAlbum;
	protected HeadPhoto headPhoto;
	protected String userName;
	protected String password;
	protected String auto_login;
	protected int toPage;
	protected HttpServletRequest request = ServletActionContext.getRequest();
	protected HttpServletResponse response = ServletActionContext.getResponse();
	protected PrintWriter out;
	protected Map<String, Object> session;
	
	public void setService(Service service) {
		this.service = service;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	
	public HeadPhoto getHeadPhoto() {
		return headPhoto;
	}

	public void setHeadPhoto(HeadPhoto headPhoto) {
		this.headPhoto = headPhoto;
	}

	public User getUser() {
		return user;
	}

	public Photo getPhoto() {
		return photo;
	}

	public PhotoAlbum getPhotoAlbum() {
		return photoAlbum;
	}

	public void setPhotoAlbum(PhotoAlbum photoAlbum) {
		this.photoAlbum = photoAlbum;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuto_login(String auto_login) {
		this.auto_login = auto_login;
	}

	public void setToPage(int toPage) {
		this.toPage = toPage;
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
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.session = session;
	}

}
