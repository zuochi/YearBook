package dao;

import java.util.List;
import java.util.Properties;

import bean.PhotoAlbum;
import bean.User;

import util.PageController;

public interface Dao<E> {
	public abstract boolean saveObject(Object object);
	public abstract boolean delObject(Object object);
	public abstract boolean updateObject(Object object);
	public abstract boolean updateUser(User user);
	public abstract boolean updateUserSign(User user);
	public abstract boolean updateUserHeadPhoto(User user);
	public abstract boolean updatePhotoAlbum(PhotoAlbum photoAlbum);
	public abstract void updateUserLoginTime(User user);
	public abstract Object getObjectByProperties(Properties pro,Object object);
	public abstract List<E> getAllObjects(Object object);
	public abstract Object getObjectByHql(String hql, String... values);
	public abstract Object getObjectBySql(String sql,Object object,String... values);
	public abstract List<E> getObjectsByHql(String hql,PageController pc, String... values) ;
	public abstract List<E> getObjectsBySql(String sql,PageController pc,Object object,String... values);
	public abstract List<E> getObjectsByPrepage(PageController pc,Object object);
	public abstract List<E> getObjectsByPrepage(Properties pro, PageController pc,Object object);
	public abstract List<E> getObjectsByProperties(Properties pro, Object object, boolean DESC);
	public abstract List<E> getObjectsByPrepageAndProperties(Properties pro, PageController pc,Object object,boolean DESC);
	public abstract List<E> getObjectsByPrepageAndByPropertiesLike(Properties pro,PageController pc, Object object, boolean DESC);
	public abstract int getTotalRowsByProperties(Properties pro, Object object,boolean DESC);
	public abstract int getTotalRowsByUserId(Properties pro, Object object,int userId);
	public abstract int getTotalRowsLike(Properties pro, Object object,boolean DESC);
	public abstract Object getDelObjectByProperties(Properties pro, Object object);
	
}
