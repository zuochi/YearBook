package dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.transaction.annotation.Transactional;

import bean.PhotoAlbum;
import bean.User;

import util.PageController;

@Transactional
public class DaoImpl<E> implements Dao {
	private SessionFactory sessionFactory;
	private Session session;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean saveObject(Object object) {
		try {
			session = sessionFactory.getCurrentSession();
			session.save(object);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} finally {
			sessionFactory.close();
		}
	}

	@Override
	public boolean delObject(Object object) {
		try {
			session = sessionFactory.getCurrentSession();
			session.delete(object);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} finally {
			sessionFactory.close();
		}
	}

	@Override
	public boolean updateObject(Object object) {
		try {
			session = sessionFactory.getCurrentSession();
			session.update(object);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} finally {
			sessionFactory.close();
		}
	}

	@Override
	public Object getObjectByProperties(Properties pro, Object object) {
		Object objuectGetForSql = null;
		StringBuilder sb = new StringBuilder("from "
				+ object.getClass().getName() + " ");
		String hql = getHQLByPropertiesDESC(sb, pro, false);
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			// Query可以完成查询,删除,更新操作
			Query query = session.createQuery(hql);
			List<Object> objects = query.list();
			if (objects.size() != 0) {
				objuectGetForSql = objects.get(0);
			}
			return objuectGetForSql;
		} catch (HibernateException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			sessionFactory.close();
		}
	}
	
	@Override
	public Object getDelObjectByProperties(Properties pro, Object object) {
		Object objuectGetForSql = null;
		StringBuilder sb = new StringBuilder("from "
				+ object.getClass().getName() + " ");
		String hql = getHQLByPropertiesDESCandDEL(sb, pro, false);
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			// Query可以完成查询,删除,更新操作
			Query query = session.createQuery(hql);
			List<Object> objects = query.list();
			if (objects.size() != 0) {
				objuectGetForSql = objects.get(0);
			}
			return objuectGetForSql;
		} catch (HibernateException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			sessionFactory.close();
		}
	}

	@Override
	public List<?> getObjectsByPrepage(PageController pc, Object object) {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "select o from " + object.getClass().getName() + " o";
			Query query = session.createQuery(hql);
			query.setFirstResult(pc.getPageStartRow());
			// query.setMaxResults(pc.getPageEndRow());
			query.setMaxResults(pc.getPageSize());// 这个是拿到结果的条数
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sessionFactory.close();
		}
	}

	@Override
	public List<?> getObjectsByPrepage(Properties pro, PageController pc,
			Object object) {
		StringBuilder sb = new StringBuilder("from "
				+ object.getClass().getName() + " ");
		String hql = getHQLByPropertiesDESC(sb, pro,false);
		int count = getTotalRowsByProperties(pro, object,true);
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			// Query可以完成查询,删除,更新操作
			Query query = session.createQuery(hql);
			// List<Emp> allEmps = query.list();
			pc.setPageController(count, pc.getCurrentPage());
			// 跟分页相关的两个方法
			query.setFirstResult(pc.getPageStartRow());
			query.setMaxResults(pc.getPageSize());
			return query.list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			sessionFactory.close();
		}
	}

	@Override
	public List<?> getObjectsByPrepageAndProperties(Properties pro,
			PageController pc, Object object, boolean DESC) {
		StringBuilder sb = new StringBuilder("from "
				+ object.getClass().getName() + " ");
		String hql = getHQLByPropertiesDESC(sb, pro, DESC);
		int count = getTotalRowsByProperties(pro, object,DESC);
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			// Query可以完成查询,删除,更新操作
			Query query = session.createQuery(hql);
			// List<Emp> allEmps = query.list();
			pc.setPageController(count, pc.getCurrentPage());
			// 跟分页相关的两个方法
			query.setFirstResult(pc.getPageStartRow());
			query.setMaxResults(pc.getPageSize());
			return query.list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			sessionFactory.close();
		}
	}

	@Override
	public List<?> getObjectsByProperties(Properties pro, Object object,
			boolean DESC) {
		StringBuilder sb = new StringBuilder("from "
				+ object.getClass().getName() + " ");
		String hql = getHQLByPropertiesDESC(sb, pro, DESC);
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			// Query可以完成查询,删除,更新操作
			Query query = session.createQuery(hql);
			return query.list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			sessionFactory.close();
		}
	}
	
	@Override
	public List<?> getObjectsByPrepageAndByPropertiesLike(Properties pro,PageController pc, Object object,
			boolean DESC) {
		StringBuilder sb = new StringBuilder("from "
				+ object.getClass().getName() + " ");
		String hql = getHQLgetHQLByPropertiesDESCLike(sb, pro, DESC);
		int count = getTotalRowsLike(pro, object,DESC);
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			// Query可以完成查询,删除,更新操作
			Query query = session.createQuery(hql);
			// List<Emp> allEmps = query.list();
			pc.setPageController(count, pc.getCurrentPage());
			// 跟分页相关的两个方法
			query.setFirstResult(pc.getPageStartRow());
			query.setMaxResults(pc.getPageSize());
			return query.list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			sessionFactory.close();
		}
	}

	private String getHQLByPropertiesDESC(StringBuilder hql, Properties pro,
			boolean DESC) {
		if (pro != null && pro.size() > 0) {
			hql.append(" where isDelete=0 and ");
			Enumeration<?> enu = pro.propertyNames();
			while (enu.hasMoreElements()) {
				String key = (String) enu.nextElement();
				if ("id".equals(key) || "user.id".equals(key)
						|| "photoAlbum.id".equals(key)
						|| "userByUserId.id".equals(key)
						|| "userByFriendId.id".equals(key)
						|| "bbs.id".equals(key)
						|| "privateLetter.id".equals(key)
						|| "profession.id".equals(key)
						|| "schoolYear.id".equals(key)
						|| "headPhoto.id".equals(key)) {
					if (!"".equals(pro.getProperty(key))) {
						hql.append(key + " = " + pro.getProperty(key) + " and ");
					}
				} else {
					if (!"".equals(pro.getProperty(key))) {
						hql.append(key + " = '" + pro.getProperty(key)
								+ "' and ");
					}
				}
			}
			int startIndex = hql.lastIndexOf("and");
			hql.delete(startIndex, hql.length() - 1);
			if (DESC) {
				hql.append(" order by id desc ");
			}
		}
		/**
		 * 显示拼出来的HQL
		 */
		System.out.println("hql==>" + hql.toString());
		return hql.toString();
	}

	private String getHQLByPropertiesDESCandDEL(StringBuilder hql, Properties pro,
			boolean DESC) {
		if (pro != null && pro.size() > 0) {
			hql.append(" where isDelete=1 and ");
			Enumeration<?> enu = pro.propertyNames();
			while (enu.hasMoreElements()) {
				String key = (String) enu.nextElement();
				if ("id".equals(key) || "user.id".equals(key)
						|| "photoAlbum.id".equals(key)
						|| "userByUserId.id".equals(key)
						|| "userByFriendId.id".equals(key)
						|| "bbs.id".equals(key)
						|| "privateLetter.id".equals(key)
						|| "profession.id".equals(key)
						|| "schoolYear.id".equals(key)
						|| "headPhoto.id".equals(key)) {
					if (!"".equals(pro.getProperty(key))) {
						hql.append(key + " = " + pro.getProperty(key) + " and ");
					}
				} else {
					if (!"".equals(pro.getProperty(key))) {
						hql.append(key + " = '" + pro.getProperty(key)
								+ "' and ");
					}
				}
			}
			int startIndex = hql.lastIndexOf("and");
			hql.delete(startIndex, hql.length() - 1);
			if (DESC) {
				hql.append(" order by id desc ");
			}
		}
		/**
		 * 显示拼出来的HQL
		 */
		System.out.println("hql==>" + hql.toString());
		return hql.toString();
	}
	
	private String getHQLgetHQLByPropertiesDESCLike(StringBuilder hql, Properties pro,boolean DESC) {
		if (pro != null && pro.size() > 0) {
			hql.append(" where isDelete=0 and ");
			Enumeration<?> enu = pro.propertyNames();
			while (enu.hasMoreElements()) {
				String key = (String) enu.nextElement();
				if(!"".equals(pro.getProperty(key)) && pro.getProperty(key).charAt(0)=='='){
					hql.append(key + pro.getProperty(key)+ "' and ");
				}else if (!"".equals(pro.getProperty(key))) {
					hql.append(key + " like  '" + pro.getProperty(key)
							+ "%' and ");
				}
			}
			int startIndex = hql.lastIndexOf("and");
			hql.delete(startIndex, hql.length() - 1);
			if (DESC) {
				hql.append(" order by id desc ");
			}
		}
		/**
		 * 显示拼出来的HQL
		 */
		// System.out.println("hql==>" + hql.toString());
		return hql.toString();
	}

	@Override
	public int getTotalRowsLike(Properties pro, Object object,boolean DESC) {
		// select count(e) from Emp e where deptno=30
		StringBuilder sb = new StringBuilder("select count(t) from "
				+ object.getClass().getName() + " t");
		String hql = this.getHQLgetHQLByPropertiesDESCLike(sb, pro,DESC);
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			// Query可以完成查询,删除,更新操作
			Query query = session.createQuery(hql);
			Object obj = query.uniqueResult();
			if (obj instanceof Long) {
				Long count = (Long) obj;
				return count.intValue();
			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			sessionFactory.close();
		}
		return 0;
	}

	@Override
	public List<?> getAllObjects(Object object) {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "select o from " + object.getClass().getName() + " o";
			Query query = session.createQuery(hql);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sessionFactory.close();
		}
	}

	@Override
	public boolean updateUser(User user) {
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("update User u set u.name=?,"
					+ "u.password=?," + "u.profession.id=?," + "u.qq=?,"
					+ "u.weChat=?," + "u.email=?," + "u.sex=?," + "u.schoolYear.id=?,u.isDelete=?,u.sign=?,u.telePhone=?,u.homeTown=? where u.id = ?");
			query.setParameter(0, user.getName());
			query.setParameter(1, user.getPassword());
			query.setParameter(2, user.getProfession().getId());
			query.setParameter(3, user.getQq() == null ? null : user.getQq());
			query.setParameter(4,
					user.getWeChat() == null ? null : user.getWeChat());
			query.setParameter(5,
					user.getEmail() == null ? null : user.getEmail());
			query.setParameter(6, user.getSex());
			query.setParameter(7, user.getSchoolYear().getId());
			query.setParameter(8, user.getIsDelete());
			query.setParameter(9, user.getSign());
			query.setParameter(10, user.getTelePhone());
			query.setParameter(11, user.getHomeTown());
			query.setParameter(12, user.getId());
			query.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} finally {
			sessionFactory.close();
		}
	}
	
	@Override
	public boolean updateUserSign(User user) {
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("update User u set u.sign=? where u.id=?");
			query.setParameter(0, user.getSign());
			query.setParameter(1, user.getId());
			query.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} finally {
			sessionFactory.close();
		}
	}

	@Override
	public void updateUserLoginTime(User user) {
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session
					.createQuery("update User u set u.lastLogintime=? where u.id = ?");
			query.setParameter(0, new Timestamp(System.currentTimeMillis()));
			query.setParameter(1, user.getId());
			query.executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sessionFactory.close();
		}
	}

	@Override
	public boolean updateUserHeadPhoto(User user) {
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("update User u set u.name=?,"
					+ "u.password=?," + "u.profession.id=?," + "u.qq=?,"
					+ "u.weChat=?," + "u.email=?," + "u.sex=? where u.id = ?");
			query.setParameter(0, user.getName());
			query.setParameter(1, user.getPassword());
			query.setParameter(2, user.getProfession().getId());
			query.setParameter(3, user.getQq() == null ? null : user.getQq());
			query.setParameter(4,
					user.getWeChat() == null ? null : user.getWeChat());
			query.setParameter(5,
					user.getEmail() == null ? null : user.getEmail());
			query.setParameter(6, user.getSex());
			query.setParameter(7, user.getId());
			query.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} finally {
			sessionFactory.close();
		}
	}

	@Override
	public int getTotalRowsByProperties(Properties pro, Object object,boolean DESC) {
		// select count(e) from Emp e where deptno=30
		StringBuilder sb = new StringBuilder("select count(t) from " + object.getClass().getName() + " t");
		String hql = this.getHQLByPropertiesDESC(sb, pro,DESC);
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			Object obj = query.uniqueResult();
			if (obj instanceof Long) {
				Long count = (Long) obj;
				return count.intValue();
			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			sessionFactory.close();
		}
		return 0;
	}

	@Override
	public int getTotalRowsByUserId(Properties pro, Object object, int userId) {
		StringBuilder sb = new StringBuilder("select count(t) from "
				+ object.getClass().getName() + " t where t.user.id=" + userId);
		String hql = this.getHQLByPropertiesDESC(sb, pro,false);
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			// Query可以完成查询,删除,更新操作
			Query query = session.createQuery(hql);
			Object obj = query.uniqueResult();
			if (obj instanceof Long) {
				Long count = (Long) obj;
				return count.intValue();
			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			sessionFactory.close();
		}
		return 0;
	}

	@Override
	public boolean updatePhotoAlbum(PhotoAlbum photoAlbum) {
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session
					.createQuery("update PhotoAlbum u set u.name=?,"
							+ "u.permission=?," + "u.password=? where u.id = ?");
			query.setParameter(0, photoAlbum.getName());
			query.setParameter(1, photoAlbum.getPermission());
			query.setParameter(2, photoAlbum.getPassword());
			query.setParameter(3, photoAlbum.getId());
			query.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} finally {
			sessionFactory.close();
		}
	}
	
	@Override
	public List<?> getObjectsBySql(String sql,PageController pc,Object object,String... values) {
		List<?> list = null;
		try {
			session = sessionFactory.getCurrentSession();
			if(pc!=null){
				if(object instanceof dto.Reply || object instanceof dto.Photo){
					list =  session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(object.getClass())).setFirstResult(pc.getPageStartRow()).setMaxResults(pc.getPageSize()).list();
				}else{
					list =  session.createSQLQuery(sql).addEntity(object.getClass()).setFirstResult(pc.getPageStartRow()).setMaxResults(pc.getPageSize()).list();
				}
			}else{
				if(object instanceof dto.Reply){
					list = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(object.getClass())).list();
				}else{
					list = session.createSQLQuery(sql).addEntity(object.getClass()).list();
				}
			}
			for(Object o:list){
				if(values!=null && values.length>0){
					for(String methodName : values){
						Method method = o.getClass().getMethod(methodName, new Class[0]);
						Hibernate.initialize(method.invoke(o,new Object[0]));
					}
				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} finally {
			sessionFactory.close();
		}
		return list;
	}
	
	@Override
	public List<?> getDtoObjectsBySql(String sql,PageController pc,Object object) {
		List<?> list = null;
		try {
			session = sessionFactory.getCurrentSession();
			if(pc!=null){
				SQLQuery sqlQuery = session.createSQLQuery(sql);
				if(object instanceof dto.Message || object instanceof dto.SearchUserInfo){
					for(Field field:object.getClass().getDeclaredFields()){
						sqlQuery.addScalar(field.getName());
					}
				}
				list = sqlQuery.setResultTransformer(Transformers.aliasToBean(object.getClass())).setFirstResult(pc.getPageStartRow()).setMaxResults(pc.getPageSize()).list();
			}else{
				list = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(object.getClass())).list();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} finally {
			sessionFactory.close();
		}
		return list;
	}
	
	@Override
	public Object getObjectBySql(String sql,Object object,String... values) {
		List<?> list = null;
		try {
			session = sessionFactory.getCurrentSession();
			if(object instanceof Integer){
				Object obj = session.createSQLQuery(sql).uniqueResult();
				if (obj instanceof BigInteger) {
					return ((BigInteger) obj).intValue();
				}
			}
			list =  session.createSQLQuery(sql).addEntity(object.getClass()).list();
			for(Object o:list){
				if(values!=null && values.length>0){
					for(String methodName : values){
						Method method = o.getClass().getMethod(methodName, new Class[0]);
						Hibernate.initialize(method.invoke(o,new Object[0]));
					}
				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} finally {
			sessionFactory.close();
		}
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 强制加载某些属性
	 * "String... values" 为方法的名字，去括号
	 */
	@Override
	public List<?> getObjectsByHql(String hql,PageController pc, String... values) {
		List<?> list = null;
		try {
			session = sessionFactory.getCurrentSession();
			if(pc!=null){
				Query query = session.createQuery(hql);
				query.setFirstResult(pc.getPageStartRow());
				query.setMaxResults(pc.getPageSize());
				//list = session.createQuery(hql).setFirstResult(pc.getPageStartRow()).setMaxResults(pc.getPageSize()).list();
				list = query.list();
			}else{
				list = session.createQuery(hql).list();
			}
			for(Object o:list){
				if(values!=null && values.length>0){
					for(String methodName : values){
						Method method = o.getClass().getMethod(methodName, new Class[0]);
						Hibernate.initialize(method.invoke(o,new Object[0]));
					}
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} finally {
			sessionFactory.close();
		}
		return list;
	}

	@Override
	public Object getObjectByHql(String hql, String... values) {
		List<?> list = null;
		try {
			session = sessionFactory.getCurrentSession();
			if(values!=null && values.length>0 && "getInteger".equals(values[0])){
				Object obj = session.createQuery(hql).uniqueResult();
				if (obj instanceof Long) {
					return ((Long) obj).intValue();
				}
			}
			list = session.createQuery(hql).list();
			for(Object o:list){
				if(values!=null && values.length>0){
					for(String methodName : values){
						Method method = o.getClass().getMethod(methodName, new Class[0]);
						Hibernate.initialize(method.invoke(o,new Object[0]));
					}
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} finally {
			sessionFactory.close();
		}
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
