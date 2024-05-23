/**
 *
 */
package G_T.OfficeSystem.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author user
 *
 */

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION,proxyMode = ScopedProxyMode.TARGET_CLASS)
public class FindModel {
	private List<HibUserMasterModel> allUserList;

	private List<HibUserMasterModel> showUserList;
	private int showNumber;
	private int currentPage;
	private String sortOrder;
	private String sortColumn;

	@Autowired
	private UserInfoModelDAO userInfoModelDAO;

	@Autowired
	private SessionFactory sessionFactory;

	public FindModel(){

		showNumber = 10;
		currentPage = 1;
		sortOrder = "▲";
		sortColumn = "ユーザーID";

	}

	public List<HibUserMasterModel> getAllUserList(){
		return allUserList;
	}

	public void setAllUserList(List<HibUserMasterModel> allUserList) {
		this.allUserList = allUserList;
	}

	public void FindUser(FindConditionModel condition) {
		setAllUserList(FindUserByCondition(condition));
		SortAll(sortColumn, sortOrder);
		GetPage(showNumber, 1);
	}

	//Hibernate対応
		public List<HibUserMasterModel> FindUserByCondition(FindConditionModel condition) {
			Session session = null;
	    	try {
	    		session = sessionFactory.openSession();
		        Criteria criteria = session.createCriteria(HibUserMasterModel.class)
		        		.createAlias("hibProfileInfoModel","p", JoinType.INNER_JOIN);
		        criteria.add(Restrictions.eqProperty("p.userId", "userId"));

		        if (condition != null) {
			        if (condition.getUserId() != null && !condition.getUserId().equals("")) {
			        	criteria.add(Restrictions.like("userId", "%" + condition.getUserId() + "%"));
			        }
			        if (condition.getEmail() != null && !condition.getEmail().equals("")) {
			        	criteria.add(Restrictions.like("email", "%" + condition.getEmail() + "%"));
			        }

			        if (condition.getNickName() != null && !condition.getNickName().equals("")) {
			        	criteria.add(Restrictions.like("p.nickName", "%" + condition.getNickName() + "%"));
			        }

			        if (condition.getUserName() != null && !condition.getUserName().equals("")) {
			        	criteria.add(Restrictions.like("p.userName", "%" + condition.getUserName() + "%"));
			        }

			        if (condition.getSex() != null) {
			        	criteria.add(Restrictions.eq("p.sex", condition.getSex()));
			        }

			        if (condition.getBirthday() != null && !condition.getBirthday().equals("")) {
			        	//criteria.add(Restrictions.like("p.birthday", "%" + condition.getBirthday() + "%"));
			        	if(condition.getBirthday1() != null && !condition.getBirthday1().equals("")) {
			        		criteria.add(Restrictions.like("p.birthday", condition.getBirthday1() + "%"));
			        	}
			        	if(condition.getBirthday2() != null && !condition.getBirthday2().equals("")) {
			        		criteria.add(Restrictions.like("p.birthday", "____" + condition.getBirthday2() + "%"));
			        	}
			        	if(condition.getBirthday3() != null && !condition.getBirthday3().equals("")) {
			        		criteria.add(Restrictions.like("p.birthday", "%" + condition.getBirthday3()));
			        	}
			        }

			        if (condition.getTel() != null && !condition.getTel().equals("")) {
			        	criteria.add(Restrictions.like("p.tel", "%" + condition.getTel() + "%"));
			        }

			        if (condition.getPostcode() != null && !condition.getPostcode().equals("")) {
			        	criteria.add(Restrictions.like("p.postcode", "%" + condition.getPostcode() + "%"));
			        }

			        if (condition.getAddress() != null && !condition.getAddress().equals("")) {
			        	criteria.add(Restrictions.like("p.address", "%" + condition.getAddress() + "%"));
			        }

			        if (condition.getHireDate() != null && !condition.getHireDate().equals("")) {
			        	criteria.add(Restrictions.like("p.hireDate", "%" + condition.getHireDate() + "%"));
			        }

			        if (condition.getAffiliation() != null && !condition.getAffiliation().equals("")) {
			        	criteria.add(Restrictions.like("p.affiliation", "%" + condition.getAffiliation() + "%"));
			        }

			        if (condition.getPosition() != null && !condition.getPosition().equals("")) {
			        	criteria.add(Restrictions.like("p.position", "%" + condition.getPosition() + "%"));
			        }

			        if (condition.getSpecialSkill() != null && !condition.getSpecialSkill().equals("")) {
			        	criteria.add(Restrictions.like("p.specialSkill", "%" + condition.getSpecialSkill() + "%"));
			        }

			        if (condition.getComment() != null && !condition.getComment().equals("")) {
			        	criteria.add(Restrictions.like("p.comment", "%" + condition.getComment() + "%"));
			        }
		        }

		        return criteria.list();
		    }
	    	catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
	    	finally {
	    		session.close();
	    	}
	    }


	public List<HibUserMasterModel> getShowUserList(){
		return showUserList;
	}

	public void setShowUserList(List<HibUserMasterModel> showUserList) {
		this.showUserList = showUserList;
	}

	public int getShowNumber() {
		return showNumber;
	}

	public void setShowNumber(int showNumber) {
		this.showNumber = showNumber;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public void GetPage(int showNumber, int currentPage)
	{
		if (showNumber == 0 || allUserList.size() <= showNumber) {
			showUserList = allUserList;
		}else {
			showUserList = IntStream.range(0, allUserList.size())
					.filter(index -> index >= (currentPage - 1) * showNumber && index < currentPage * showNumber)
					.mapToObj(allUserList::get)
					.collect(Collectors.toList());
		}
		this.showNumber = showNumber;
		this.currentPage = currentPage;
	}


	public void SortAll(String sortColumn, String sortOrder){
		Collections.sort(this.allUserList, new Comparator<HibUserMasterModel>(){
			public int compare(HibUserMasterModel u1, HibUserMasterModel u2){
				int invertFlag = -1;
				if (sortOrder == null || sortOrder.equals("▲") || sortOrder.equals("")){
					invertFlag = 1;//正負を入れ替える
				}
				if (("ユーザーID").equals(sortColumn)) {
					return invertFlag * (u1.getUserId().compareTo(u2.getUserId()) >= 0 ? 1 : -1);
				}
				else if (("氏名").equals(sortColumn)) {
					return invertFlag * (u1.getHibProfileInfoModel().getUserName().compareTo(u2.getHibProfileInfoModel().getUserName()) >= 0 ? 1 : -1);
				}
				else if(("性別").equals(sortColumn)) {
					//return 1;
					int a = u1.getHibProfileInfoModel().getSex().compareTo(u2.getHibProfileInfoModel().getSex());
					int b = a >= 0 ? 1 : -1 ;
					int c = invertFlag * b;
					System.out.println(a + "*" + b + "*" + c + "*" + u1.getUserId() + "*" + u2.getUserId());
					return c;
					//return invertFlag * (u1.getSex().compareTo(u2.getSex()) > 0 ? 1 : -1 );
				}
				else if(("電話番号").equals(sortColumn)) {
					return invertFlag * (u1.getHibProfileInfoModel().getTel().compareTo(u2.getHibProfileInfoModel().getTel()) >= 0 ? 1 : -1 );
				}
				else if(("郵便番号").equals(sortColumn)) {
					return invertFlag * (u1.getHibProfileInfoModel().getPostcode().compareTo(u2.getHibProfileInfoModel().getPostcode()) >= 0 ? 1 : -1 );
				}
				else if(("住所").equals(sortColumn)) {
					return invertFlag * (u1.getHibProfileInfoModel().getAddress().compareTo(u2.getHibProfileInfoModel().getAddress()) >= 0 ? 1 : -1 );
				}
				else {
					return invertFlag * 1;
				}
			}
		});
		this.sortColumn = sortColumn;
		this.sortOrder = sortOrder;
		GetPage(showNumber, 1);
	}

}
