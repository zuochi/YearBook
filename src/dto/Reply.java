package dto;

import java.util.Date;

import bean.Bbs;
import bean.Photo;
import bean.PrivateLetter;
import bean.User;

public class Reply {
	private Integer id;
	private User userByUserId;
	private Bbs bbs;
	private PrivateLetter privateLetter;
	private User userByUserBid;
	private Photo photo;
	private String context;
	private Date signupDate;
	private Integer status;
	private Integer isDelete;
}
