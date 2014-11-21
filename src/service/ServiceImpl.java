package service;

import java.util.List;
import java.util.Properties;

import bean.PhotoAlbum;
import bean.User;

import util.PageController;

import dao.Dao;

public class ServiceImpl<E> implements Service{
	private Dao dao;

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	@Override
	public boolean saveObject(Object object) {
		// TODO Auto-generated method stub
		return dao.saveObject(object);
	}

	@Override
	public boolean delObject(Object object) {
		// TODO Auto-generated method stub
		return dao.delObject(object);
	}

	@Override
	public boolean updateObject(Object object) {
		// TODO Auto-generated method stub
		return dao.updateObject(object);
	}

	@Override
	public List<E> getObjectsByPrepage(PageController pc, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> getObjectsByPrepage(Properties pro, PageController pc,
			Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getAllObjects(Object object) {
		// TODO Auto-generated method stub
		return dao.getAllObjects(object);
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return dao.updateUser(user);
	}

	@Override
	public void updateUserLoginTime(User user) {
		// TODO Auto-generated method stub
		dao.updateUserLoginTime(user);
	}

	@Override
	public boolean isDuplicationOfProperties(Properties pro,Object object) {
		// TODO Auto-generated method stub
		Object o = dao.getObjectByProperties(pro, object);
		if(o==null){
			return false; 
		}
		return true;
	}

	@Override
	public int getTotalRowsByUserId(Properties pro, Object object, int userId) {
		// TODO Auto-generated method stub
		return dao.getTotalRowsByUserId(pro, object, userId);
	}

	@Override
	public boolean updatePhotoAlbum(PhotoAlbum photoAlbum) {
		// TODO Auto-generated method stub
		return dao.updatePhotoAlbum(photoAlbum);
	}


	@Override
	public List getObjectsByPrepageByUserId(PageController pc, Object object,
			int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getObjectsByPrepageAndProperties(Properties pro, PageController pc,Object object,boolean DESC) {
		// TODO Auto-generated method stub
		return dao.getObjectsByPrepageAndProperties(pro, pc, object, DESC);
	}
	
	@Override
	public List getObjectsByProperties(Properties pro, Object object,
			boolean DESC) {
		// TODO Auto-generated method stub
		return dao.getObjectsByProperties(pro, object, DESC);
	}

	@Override
	public Object getObjectByProperties(Properties pro, Object object) {
		// TODO Auto-generated method stub
		return dao.getObjectByProperties(pro, object);
	}

	@Override
	public int getTotalRowsByProperties(Properties pro, Object object,boolean DESC) {
		// TODO Auto-generated method stub
		return dao.getTotalRowsByProperties(pro, object, DESC);
	}

	@Override
	public Object getDelObjectByProperties(Properties pro, Object object) {
		// TODO Auto-generated method stub
		return dao.getDelObjectByProperties(pro, object);
	}

	@Override
	public List getObjectsByPrepageAndByPropertiesLike(Properties pro,PageController pc, Object object,
			boolean DESC) {
		// TODO Auto-generated method stub
		return dao.getObjectsByPrepageAndByPropertiesLike(pro,pc, object, DESC);
	}

	@Override
	public int getTotalRowsLike(Properties pro,PageController pc, Object object, boolean DESC) {
		// TODO Auto-generated method stub
		return getTotalRowsLike(pro,pc,object,DESC);
	}

	@Override
	public boolean updateUserSign(User user) {
		// TODO Auto-generated method stub
		return dao.updateUserSign(user);
	}

	@Override
	public Object getObjectByHql(String hql, String... values) {
		// TODO Auto-generated method stub
		return dao.getObjectByHql(hql, values);
	}

	@Override
	public Object getObjectBySql(String sql, Object object, String... values) {
		// TODO Auto-generated method stub
		return dao.getObjectBySql(sql, object, values);
	}

	@Override
	public List getObjectsByHql(String hql,PageController pc, String... values) {
		// TODO Auto-generated method stub
		return dao.getObjectsByHql(hql,pc, values);
	}
	
	@Override
	public List getObjectsBySql(String sql,PageController pc, Object object, String... values) {
		// TODO Auto-generated method stub
		return dao.getObjectsBySql(sql,pc, object, values);
	}

	@Override
	public List getDtoObjectsBySql(String sql, PageController pc, Object object) {
		// TODO Auto-generated method stub
		return dao.getDtoObjectsBySql(sql, pc, object);
	}
	
}
