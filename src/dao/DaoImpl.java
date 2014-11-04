package dao;

import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
		// TODO Auto-generated method stub
		try {
			session = sessionFactory.getCurrentSession();
			session.save(object);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			sessionFactory.close();
		}
	}

	@Override
	public boolean delObject(Object object) {
		// TODO Auto-generated method stub
		try {
			session = sessionFactory.getCurrentSession();
			session.delete(object);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			sessionFactory.close();
		}
	}

	@Override
	public boolean updateObject(Object object) {
		// TODO Auto-generated method stub
		try {
			session = sessionFactory.getCurrentSession();
			session.update(object);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			sessionFactory.close();
		}
	}

	@Override
	public Object getObjectByProperties(Properties pro, Object object) {
		// TODO Auto-generated method stub
		Object objuectGetForSql = null;
		StringBuilder sb = new StringBuilder("from "
				+ object.getClass().getName() + " ");
		String hql = getHQLByPropertiesDESC(sb, pro, false);
		int count = getTotalRowsByProperties(pro, object,false);
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
		// TODO Auto-generated method stub
		Object objuectGetForSql = null;
		StringBuilder sb = new StringBuilder("from "
				+ object.getClass().getName() + " ");
		String hql = getHQLByPropertiesDESCandDEL(sb, pro, false);
		int count = getTotalRowsByProperties(pro, object,false);
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			// Query可以完成查询,删除,更新操作
			Query query = session.createQuery(hql);
			// List<Emp> allEmps = query.list();
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
	public List<E> getObjectsByPrepage(PageController pc, Object object) {
		// TODO Auto-generated method stub
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
	public List<E> getObjectsByPrepage(Properties pro, PageController pc,
			Object object) {
		// TODO Auto-generated method stub
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
	public List<E> getObjectsByPrepageAndProperties(Properties pro,
			PageController pc, Object object, boolean DESC) {
		// TODO Auto-generated method stub
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
	public List getObjectsByProperties(Properties pro, Object object,
			boolean DESC) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder("from "
				+ object.getClass().getName() + " ");
		String hql = getHQLByPropertiesDESC(sb, pro, DESC);
		int count = getTotalRowsByProperties(pro, object,DESC);
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
	public List getObjectsByPrepageAndByPropertiesLike(Properties pro,PageController pc, Object object,
			boolean DESC) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		if (pro != null && pro.size() > 0) {
			hql.append(" where isDelete=0 and ");
			Enumeration enu = pro.propertyNames();
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
		// TODO Auto-generated method stub
		if (pro != null && pro.size() > 0) {
			hql.append(" where isDelete=1 and ");
			Enumeration enu = pro.propertyNames();
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
		// TODO Auto-generated method stub
		if (pro != null && pro.size() > 0) {
			hql.append(" where isDelete=0 and ");
			Enumeration enu = pro.propertyNames();
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
	public List getAllObjects(Object object) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			sessionFactory.close();
		}
	}
	
	@Override
	public boolean updateUserSign(User user) {
		// TODO Auto-generated method stub
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("update User u set u.sign=? where u.id=?");
			query.setParameter(0, user.getSign());
			query.setParameter(1, user.getId());
			query.executeUpdate();
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			sessionFactory.close();
		}
	}

	@Override
	public void updateUserLoginTime(User user) {
		// TODO Auto-generated method stub
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session
					.createQuery("update User u set u.lastLogintime=? where u.id = ?");
			query.setParameter(0, new Timestamp(System.currentTimeMillis()));
			query.setParameter(1, user.getId());
			query.executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sessionFactory.close();
		}
	}

	@Override
	public boolean updateUserHeadPhoto(User user) {
		// TODO Auto-generated method stub
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			sessionFactory.close();
		}
	}

	@Override
	public int getTotalRowsByProperties(Properties pro, Object object,boolean DESC) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			sessionFactory.close();
		}
	}

}
