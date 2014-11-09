package action.user;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.HeadPhoto;
import bean.Photo;
import bean.User;

import com.alibaba.fastjson.JSON;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class PhotoUpload extends UserAction{
	private File file; // 上传的文件
	private File __source;//原头像
	private File __avatar1;//头像大
	private File __avatar2;//头像中
	private File __avatar3;//头像小
	private String basePath;
	private FileOutputStream fos;
	private FileInputStream fis;
	
	public String execute() throws Exception {
		//basePath = ServletActionContext.getServletContext().getRealPath("\\");
		basePath = ServletActionContext.getServletContext().getRealPath("/") + "/";
		user = (User) request.getSession().getAttribute("user");
		try {
			if(file != null){
				photo = new Photo();
				createThumbnail();
				savePhoto();
			}else{
				Result result = new Result();
				result.success = false;
				result.msg = "Failure!";
				//保存头像
				headPhoto = (HeadPhoto) request.getSession().getAttribute("headPhoto");
				saveHeadPhoto(__avatar1,1);
				saveHeadPhoto(__avatar2,2);
				saveHeadPhoto(__avatar3,3);		
				if(__source != null){
					saveHeadPhoto(__source,0);
				}
				//保存到数据库
				headPhoto.setIsDelete(0);
				service.updateObject(headPhoto);
				//放进回话里面
				session.put("headPhoto", headPhoto);
				result.success = true;
				result.msg = "Success!";
				//返回图片的保存结果（返回内容为json字符串，可自行构造，该处使用fastjson构造）
				out=response.getWriter();
				out.print(JSON.toJSONString(result));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(out!=null){
				out.flush();
				out.close();
			}
			if(fos!=null){
				fos.flush();
				fos.close();
			}
			if(fis!=null){
				fis.close();
			}
		}
		return null;
	}

	public void createThumbnail() throws Exception{
		String savePath = basePath+"uploadImgs";
		//读入文件
		Image src = javax.imageio.ImageIO.read(file);
		//构造Image对象
		int wideth=src.getWidth(null);
		//得到源图
		int height=src.getHeight(null);
		//得到比例
		double rate = (double)wideth/200d;
		//得到源图长
		BufferedImage tag;
		if(wideth<=200){
			tag = new BufferedImage(wideth,height,BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(src,0,0,wideth,height,null);
		}else{
			height = (int) ((double)height/rate);
			tag = new BufferedImage(200,height,BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(src,0,0,200,height,null);
		}
		//绘制缩小后的图
		//FileOutputStream fos = new FileOutputStream(savePath + "\\" +"thumb_"+file.getName());
		FileOutputStream fos = new FileOutputStream(savePath + "/" +"thumb_"+file.getName());
		//输出到文件流
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
		//近JPEG编码
		encoder.encode(tag);
		//保存缩略图地址
		//photo.setUrlThumb("uploadImgs\\"+"thumb_"+file.getName());
		photo.setUrlThumb("uploadImgs/"+"thumb_"+file.getName());
		fos.flush();
		fos.close();
    }
	
	private void savePhoto() throws Exception{
		//先保存到数据库
		photo.setName("No description yet.");
		//photo.setUrl("uploadImgs\\"+file.getName());
		photo.setUrl("uploadImgs/"+file.getName());
		photo.setUploadDate(new Timestamp(System.currentTimeMillis()));
		photo.setUser(user);
		photo.setIsDelete(0);
		//photo.setPhotoAlbum(photoAlbum);
		service.saveObject(photo);
		
		//保存到服务器的路径
		String savePath = basePath+"uploadImgs";
		// 保存相片到服务器，以服务器的文件保存地址和源文件名建立上传文件输出流
		//fos = new FileOutputStream(savePath + "\\"+ file.getName());
		fos = new FileOutputStream(savePath + "/"+ file.getName());
		// 以上传文件建立一个文件上传流
		fis = new FileInputStream(file);
		// 讲上传的内容写入服务器
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fis.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		}
	}
	
	private void saveHeadPhoto(File headPhotoFile,int i) throws Exception{
		//保存到服务器
		//String savePathHeadPhoto = basePath+"uploadImgs\\headPhoto";
		//fos = new FileOutputStream(savePathHeadPhoto + "\\"+ headPhotoFile.getName());
		String savePathHeadPhoto = basePath+"uploadImgs/headPhoto";
		fos = new FileOutputStream(savePathHeadPhoto + "/"+ headPhotoFile.getName());
		fis = new FileInputStream(headPhotoFile);
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fis.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		}
		fos.flush();
		fos.close();
		//组成HeadPhoto并删除旧文件
		//File oldFile;
		if(i==1){
			/*oldFile = new File(basePath+(headPhoto.getUrlL()!=null?headPhoto.getUrlL():""));
			if(oldFile.exists()){
				oldFile.delete();
			}*/
			headPhoto.setUrlL("uploadImgs/headPhoto/"+headPhotoFile.getName());
			//headPhoto.setUrlL("uploadImgs/headPhoto/"+headPhotoFile.getName());
		}else if(i==2){
			/*oldFile = new File(basePath+(headPhoto.getUrlM()!=null?headPhoto.getUrlM():""));
			if(oldFile.exists()){
				oldFile.delete();
			}*/
			headPhoto.setUrlM("uploadImgs/headPhoto/"+headPhotoFile.getName());
			//headPhoto.setUrlM("uploadImgs\\headPhoto\\"+headPhotoFile.getName());
		}else if(i==3){
			/*oldFile = new File(basePath+(headPhoto.getUrlS()!=null?headPhoto.getUrlS():""));
			if(oldFile.exists()){
				oldFile.delete();
			}*/
			headPhoto.setUrlS("uploadImgs/headPhoto/"+headPhotoFile.getName());
			//headPhoto.setUrlS("uploadImgs\\headPhoto\\"+headPhotoFile.getName());
		}else{
			/*oldFile = new File(basePath+(headPhoto.getUrlO()!=null?headPhoto.getUrlO():""));
			if(oldFile.exists()){
				oldFile.delete();
			}*/
			//headPhoto.setUrlO("uploadImgs\\headPhoto\\"+headPhotoFile.getName());
			headPhoto.setUrlO("uploadImgs/headPhoto/"+headPhotoFile.getName());
		}
	}
	
	
	private class Result
	{
		/**
		* 表示图片是否已上传成功。
		*/
		public Boolean success;
		/**
		* 自定义的附加消息。
		*/
		public String msg;
		/**
		* 表示原始图片的保存地址。
		*/
		public String sourceUrl;
		/**
		* 表示所有头像图片的保存地址，该变量为一个数组。
		*/
		public ArrayList avatarUrls;
	}
	
	public void setFile(File file) {
		this.file = file;
	}

	public void set__source(File __source) {
		this.__source = __source;
	}
	
	public void set__avatar1(File __avatar1) {
		this.__avatar1 = __avatar1;
	}

	public void set__avatar2(File __avatar2) {
		this.__avatar2 = __avatar2;
	}

	public void set__avatar3(File __avatar3) {
		this.__avatar3 = __avatar3;
	}
}