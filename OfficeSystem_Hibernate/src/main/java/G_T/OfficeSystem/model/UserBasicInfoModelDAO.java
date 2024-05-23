package G_T.OfficeSystem.model;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserBasicInfoModelDAO extends JdbcDaoSupport{

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private UserBasicInfoModel userBasicInfoModel;

	@Autowired
	public UserBasicInfoModelDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public void SaveUserDateInSession(String userId, String pass,HttpSession httpSession) {
		Session session = null;
    	try {
    		session = sessionFactory.openSession();
	        Criteria criteria = session.createCriteria(HibUserMasterModel.class)
	        		.createAlias("hibProfileInfoModel","p", JoinType.INNER_JOIN);
	        criteria.add(Restrictions.eqProperty("p.userId", "userId"));

		    criteria.add(Restrictions.eq("userId", userId));
		    List<HibUserMasterModel> list =criteria.list();
	        httpSession.setAttribute("userId",list.get(0).getUserId() );
	        httpSession.setAttribute("email",list.get(0).getEmail() );
	        httpSession.setAttribute("password",list.get(0).getPassword() );

/*		    for (int i=0; i<list.size(); ++i)
		    {
		    	System.out.println(list.get(i).getUserId());
		        System.out.println(list.get(i).getEmail());
		        httpSession.setAttribute("userId", );
		    }*/

	        return;
	    }
    	catch (Exception ex) {
			ex.printStackTrace();
			return;
		}
    	finally {
    		session.close();
    	}

/*		String sql = "select count(*) from user_master where 1 = 1";

		if(!userId.equals("")) {
			sql += " and USER_ID = '" + userId + "'";
		}

		if(!pass.equals("")) {
			sql += " and PASSWORD = '" + pass + "'";
		}
		UserInfoModelMapper mapper = new UserInfoModelMapper();
		try {

			List<Map<String, Object>> ret = getJdbcTemplate().queryForList(sql);
			for (Map<String, Object> map : ret) {
				System.out.println(map.get("USER_ID").toString() + "-" + map.get("PASSWORD").toString());
			}
			//getJdbcTemplate().queryForObject(sql, new Object[] { }, Integer.class);
	        List<UserInfoModel> dtoList =
	                this.getJdbcTemplate().query(sql, mapper);
	        for (UserInfoModel dto : dtoList) {
	            System.out.println(dto.getEmail());
	            System.out.println(dto.getNIckName());
	        }
			return;
		} catch(EmptyResultDataAccessException e) {
			return;
		}*/
	}

	public Integer checkTable(String userId,String password){
		String sql = "select count(*) from user_master where 1 = 1";

		if(!userId.equals("")) {
			sql += " and USER_ID = '" + userId + "'";
		}

		if(!password.equals("")) {
			sql += " and PASSWORD = '" + password + "'";
		}

		try {
			return getJdbcTemplate().queryForObject(sql, new Object[] { }, Integer.class);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void updatePassword(String userId,String password) {
    	Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String hqlUpdate = "update G_T.OfficeSystem.model.HibUserMasterModel set "
								+ "password = " + password
								+ " where userId = " + userId +";";
		System.out.println(hqlUpdate);
		int updatedEntities = session.createQuery( hqlUpdate ).executeUpdate();
		tx.commit();
		session.close();
	}
}
