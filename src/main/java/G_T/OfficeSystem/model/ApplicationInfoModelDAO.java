package G_T.OfficeSystem.model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ApplicationInfoModelDAO extends JdbcDaoSupport{

	@Autowired
	public ApplicationInfoModelDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public Integer CheckUser(String userId, String pass) {
		String sql = "select count(*) from user_master where 1 = 1";

		if(!userId.equals("")) {
			sql += " and USER_ID = '" + userId + "'";
		}

		if(!pass.equals("")) {
			sql += " and PASSWORD = '" + pass + "'";
		}

		try {
			return getJdbcTemplate().queryForObject(sql, new Object[] { }, Integer.class);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}


	public List<ApplicationInfoModel> ApplyUser(ApplicationConditionModel condition) {
		String sql = UserInfoModelMapper.BASE_SQL + " and u.TYPE = 2 ";

		if (condition != null) {

			if (condition.getApplyId() != null && !condition.getApplyId().equals("")) {
				sql += " and u.USER_ID like '%" + condition.getApplyId() + "%'";
			}

			if (condition.getApplyStatus() != null && !condition.getApplyStatus().equals("")) {
				sql += " and u.EMAIL like '%" + condition.getApplyStatus() + "%'";
			}

			if (condition.getNickName() != null && !condition.getNickName().equals("")) {
				sql += " and p.NICK_NAME like '%" + condition.getNickName() + "%'";
			}

			if (condition.getUserName() != null && !condition.getUserName().equals("")) {
				sql += " and p.USER_NAME like '%" + condition.getUserName() + "%'";
			}


			if (condition.getSex() != null && !condition.getSex().equals("")) {
				sql += " and p.SEX like '%" + condition.getSex() + "%'";
			}

			if (condition.getBirthday() != null && !condition.getBirthday().equals("")) {
				//     0123456789
				//s = "@@@@,@@,@@"
				//             12345678
				//p.BIRTHDAY = yyyymmdd

				//     0123456789
				//s = ""
				//             12345678
				//p.BIRTHDAY = yyyymmdd
				String year = condition.getBirthday1();
				String month = condition.getBirthday2();
				String day = condition.getBirthday3();

				if(year != null && year.equals("")) {
					sql += " and  SUBSTRING( p.BIRTHDAY, 1, 4 ) = '" + year +"'";
				}
				if(month != null & month.equals("")) {
					sql += " and  SUBSTRING( p.BIRTHDAY, 5, 2 ) = '" + month +"'";
				}
				if(day != null & day.equals("")) {
					sql += " and  SUBSTRING( p.BIRTHDAY, 7, 2 ) = '" + day +"'";
				}


/*				if(year != null && year.equals("")) {
					if(month != null & month.equals("")) {
						if(day != null & day.equals("")) {
							sql += " and p.BIRTHDAY = '" + condition.getBirthday() +"'";
						} else {
							sql += " and  SUBSTRING( p.BIRTHDAY, 1, 4 ) = '" + year +"'";
							sql += " and  SUBSTRING( p.BIRTHDAY, 5, 2 ) = '" + month +"'";
						}
					}else {
						if(day != null & day.equals("")) {

						}else {
							sql += " and  SUBSTRING( p.BIRTHDAY, 1, 4 ) = '" + year +"'";
						}
					}
				}*/
/*
				String s = condition.getBirthday();
				String a;
				if(s.startsWith(",,")) {

				}else if(s.startsWith("@@@@,")) {
					if(s.endsWith(",@@")) {
						//月のみ
						a = s.substring(5,7);
						//SUBSTRING( 文字列, 開始位置, 文字数 )
						sql += " and  SUBSTRING( p.BIRTHDAY, 5, 2 ) = '" + a +"'";
					}else if(s.startsWith("@@@@,@@,")){
						//日のみ
						a = s.substring(8,10);
						sql += " and  SUBSTRING( p.BIRTHDAY, 7, 2 ) = '" + a +"'";
					}else {
						//月日
						a = s.substring(5,10);
						a = a.replaceAll(",", "");
						sql += " and  SUBSTRING( p.BIRTHDAY, 5, 4 ) = '" + a +"'";
					}
				}else if(s.endsWith(",@@")) {
					if(s.endsWith(",@@,@@")) {
						//年のみ
						a = s.substring(0,4);
						sql += " and  SUBSTRING( p.BIRTHDAY, 1, 4 ) = '" + a +"'";
					}else {
						//年月
						a = s.substring(0,7);
						a = a.replaceAll(",", "");
						sql += " and  SUBSTRING( p.BIRTHDAY, 1, 6 ) = '" + a +"'";
					}
				}else if(s.indexOf(",@@,") != -1){
					//部分一致
					//年日
					a = s.substring(0,4);//年
					sql += " and  SUBSTRING( p.BIRTHDAY, 1, 4 ) = '" + a +"'";
					String b = s.substring(8,10);//日
					sql += " and  SUBSTRING( p.BIRTHDAY, 7, 2 ) = '" + b +"'";

				}else {
					//年月日
					a = s.replaceAll(",", "");
					sql += " and p.BIRTHDAY = '" + a +"'";
				}*/
			}

			if (condition.getAge() != null && !condition.getAge().equals("")) {
				sql += " and TIMESTAMPDIFF(YEAR, str_to_date(p.BIRTHDAY,'%Y%m%d'), CURDATE()) = '" + condition.getAge() +"'";
			}

			if (condition.getTel() != null && !condition.getTel().equals("")) {
				sql += " and p.TEL like '%" + condition.getTel() + "%'";
			}

			if (condition.getPostcode() != null && !condition.getPostcode().equals("")) {
				sql += " and p.POSTCODE like '%" + condition.getPostcode() + "%'";
			}

			if (condition.getAddress() != null && !condition.getAddress().equals("")) {
				sql += " and p.ADDRESS like '%" + condition.getAddress() + "%'";
			}

			if (condition.getHireDate() != null && !condition.getHireDate().equals("")) {
				//     0123456789
				//s = "@@@@,@@,@@"
				//              12345678
				//p.HIRE_DATE = yyyymmdd
				String s = condition.getHireDate();
				String a;
				if(s.startsWith("@@@@,@@,@@")) {

				}else if(s.startsWith("@@@@,")) {
					if(s.endsWith(",@@")) {
						//月のみ
						a = s.substring(5,7);
						//SUBSTRING( 文字列, 開始位置, 文字数 )
						sql += " and  SUBSTRING( p.HIRE_DATE, 5, 2 ) = '" + a +"'";
					}else if(s.startsWith("@@@@,@@,")){
						//日のみ
						a = s.substring(8,10);
						sql += " and  SUBSTRING( p.HIRE_DATE, 7, 2 ) = '" + a +"'";
					}else {
						//月日
						a = s.substring(5,10);
						a = a.replaceAll(",", "");
						sql += " and  SUBSTRING( p.HIRE_DATE, 5, 4 ) = '" + a +"'";
					}
				}else if(s.endsWith(",@@")) {
					if(s.endsWith(",@@,@@")) {
						//年のみ
						a = s.substring(0,4);
						sql += " and  SUBSTRING( p.HIRE_DATE, 1, 4 ) = '" + a +"'";
					}else {
						//年月
						a = s.substring(0,7);
						a = a.replaceAll(",", "");
						sql += " and  SUBSTRING( p.HIRE_DATE, 1, 6 ) = '" + a +"'";
					}
				}else if(s.indexOf(",@@,") != -1){
					//部分一致
					//年日
					a = s.substring(0,4);//年
					sql += " and  SUBSTRING( p.HIRE_DATE, 1, 4 ) = '" + a +"'";
					String b = s.substring(8,10);//日
					sql += " and  SUBSTRING( p.HIRE_DATE, 7, 2 ) = '" + b +"'";

				}else {
					//年月日
					a = s.replaceAll(",", "");
					sql += " and p.HIRE_DATE = '" + a +"'";
				}
			}

			if (condition.getAffiliation() != null && !condition.getAffiliation().equals("")) {
				sql += " and p.AFFILIATION like '%" + condition.getAffiliation() + "%'";
			}

			if (condition.getPosition() != null && !condition.getPosition().equals("")) {
				sql += " and p.POSITION like '%" + condition.getPosition() + "%'";
			}

			if (condition.getHobby() != null && !condition.getHobby().equals("")) {
				sql += " and p.HOBBY like '%" + condition.getHobby() + "%'";
			}

			if (condition.getSpecialSkill() != null && !condition.getSpecialSkill().equals("")) {
				sql += " and p.SPECIAL_SKILL like '%" + condition.getSpecialSkill() + "%'";
			}

			if (condition.getComment() != null && !condition.getComment().equals("")) {
				sql += " and p.COMMENT like '%" + condition.getComment() + "%'";
			}
		}

		ApplicationInfoModelMapper mapper = new ApplicationInfoModelMapper();
		try {
			List<ApplicationInfoModel> list = this.getJdbcTemplate().query(sql, mapper);
			return list;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

/*	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private UserBasicInfoModel userBasicInfoModel;

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

		    for (int i=0; i<list.size(); ++i)
		    {
		    	System.out.println(list.get(i).getUserId());
		        System.out.println(list.get(i).getEmail());
		        httpSession.setAttribute("userId", );
		    }

	        return;
	    }
    	catch (Exception ex) {
			ex.printStackTrace();
			return;
		}
    	finally {
    		session.close();
    	}

		String sql = "select count(*) from user_master where 1 = 1";

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
		}
	}*/

}
