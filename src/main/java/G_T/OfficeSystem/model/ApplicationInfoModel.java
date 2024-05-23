package G_T.OfficeSystem.model;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;


@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION,proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ApplicationInfoModel{

	private List<HibProfileInfoModel> profileInfoModelList;
	public String userName;
	public String position;
	public String affiliation;

	public String status;
	public String userId;
	public Boolean userIdIsSet = false;
	public String applyStatus;
	public String applyId;
	public String applyFile;
	public String title;
	public File file;
	private List<HibApplicationInfoModel> allApplicationList;
	private List<HibApplicationInfoModel> showApplicationList;
	private int showNumber;
	private int currentPage;
	private String sortOrder;
	private String sortColumn;
	@Autowired
	private SessionFactory sessionFactory;

	public ApplicationInfoModel(){

		showNumber = 10;
		currentPage = 1;
		sortOrder = "▼";
		sortColumn = "申請ID";

	}

	//状態変更時に呼ばれる　ページをリセット
	public void FindApplication(String status) {
		showNumber = 10;
		sortOrder = "▼";
		sortColumn = "申請ID";
		setAllApplicationList(FindApplicationByCondition(status));
		//System.out.println(currentPage);
		SortAll(sortColumn, sortOrder);
		GetPage(showNumber, 1);
	}


	public List<HibApplicationInfoModel> getAllApplicationList(){
		return allApplicationList;
	}

	public void setAllApplicationList(List<HibApplicationInfoModel> allApplicationList) {
		this.allApplicationList = allApplicationList;
	}

	//Hibernate対応
	//指定した状態でデータベースから取得
	public List<HibApplicationInfoModel> FindApplicationByCondition(String status) {
		Session session = null;
    	try {
    		session = sessionFactory.openSession();
	        Criteria criteria = session.createCriteria(HibApplicationInfoModel.class);

	        Integer i = Integer.parseInt(status);
	        if(i==5) {

	        }else {
	        	criteria.add(Restrictions.eq("applyStatus",i));
	        }
	        //System.out.println(criteria.list());//なんでprintされる
	        if(userIdIsSet) {
	        	criteria.add(Restrictions.eq("userId",userId));
	        }
	        return criteria.list();
	    }
    	catch (Exception ex) {
			//ex.printStackTrace();
			return null;
		}
    	finally {
    		session.close();
    	}
    }

	public List<HibApplicationInfoModel> getShowApplicationList(){
		return showApplicationList;
	}

	public void setShowApplicationList(List<HibApplicationInfoModel> showApplicationList) {
		this.showApplicationList = showApplicationList;
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

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getApplyFile() {
		return applyFile;
	}

	public void setApplyFile(String applyFile) {
		this.applyFile = applyFile;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
		setUserIdIsSet(true);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getUserIdIsSet() {
		return userIdIsSet;
	}

	public void setUserIdIsSet(Boolean userIdIsSet) {
		this.userIdIsSet = userIdIsSet;
	}



	public void GetPage(int showNumber, int currentPage)
	{
		int sNumber = showNumber;
		int cPage = currentPage;
		if (sNumber == 0 || allApplicationList.size() <= sNumber) {
			showApplicationList = allApplicationList;
		}else {
			showApplicationList = IntStream.range(0, allApplicationList.size())
					.filter(index -> index >= (currentPage - 1) * showNumber && index < currentPage * showNumber)
					.mapToObj(allApplicationList::get)
					.collect(Collectors.toList());
			if(showApplicationList.size()==0) {
				cPage = currentPage - 1;
				//sNumber = showNumber - 1;
				showApplicationList = IntStream.range(0, allApplicationList.size())
						.filter(index -> index >= (currentPage -1 - 1) * showNumber && index < (currentPage - 1) * showNumber)
						.mapToObj(allApplicationList::get)
						.collect(Collectors.toList());

			}
		}
		this.showNumber = sNumber;
		this.currentPage = cPage;
	}

	public void SortAll(String sortColumn, String sortOrder){
		Collections.sort(this.allApplicationList, new Comparator<HibApplicationInfoModel>(){
			public int compare(HibApplicationInfoModel u1, HibApplicationInfoModel u2){
				int invertFlag = -1;
				if (sortOrder == null || sortOrder.equals("▲") || sortOrder.equals("")){
					invertFlag = 1;//正負を入れ替える
				}
				try {
					if (("状態").equals(sortColumn)) {
						if(u1.getApplyStatus()!=null && u2.getApplyStatus() !=null && !u1.getApplyStatus().equals("") && !u2.getApplyStatus().equals("")) {
							System.out.println(u1.getApplyId() + "*" + u2.getApplyId());
							System.out.println(u1.getApplyStatus().toString() + "*" +u2.getApplyStatus().toString());
							//if(u1.getApplyStatus() == u2.getApplyStatus()) return -1;
							//return invertFlag * u1.getApplyStatus().compareTo(u2.getApplyStatus());
							return invertFlag * u1.getApplyStatus().compareTo(u2.getApplyStatus()) >= 0 ? 1 : -1 ;
	/*						if (u1.getApplyStatus().equals(u2.getApplyStatus())) {
								return 0;
							}else {
								if (u1.getApplyStatus() < u2.getApplyStatus()) {
									return -invertFlag;
								} else {
									return invertFlag;
								}
							}*/
						}else {
							if((u1.getApplyStatus()==null && u1.getApplyStatus()==null) || u1.getApplyStatus().toString().equals(u2.getApplyStatus())) {
								return 0;
							}
							if(u1.getApplyStatus()==null || u1.getApplyStatus().equals("")) {
								return 1;
							}else {
								return -1;
							}
						}
					}
					else if (("申請ID").equals(sortColumn)) {
						if(u1.getApplyId()!=null && u2.getApplyId() !=null && !u1.getApplyId().equals("") && !u2.getApplyId().equals("")) {
							return invertFlag * (u1.getApplyId().compareTo(u2.getApplyId()));
						}else {
							if((u1.getApplyId()==null && u1.getApplyId()==null) || u1.getApplyId().toString().equals(u2.getApplyId())) {
								return 0;
							}
							if(u1.getApplyId()==null || u1.getApplyId().equals("")) {
								return 1;
							}else {
								return -1;
							}
						}
					}
					else if(("申請書類").equals(sortColumn)) {
						if(u1.getApplyFile()!=null && u2.getApplyFile() !=null && !u1.getApplyFile().equals("") && !u2.getApplyFile().equals("")) {
							return invertFlag * (u1.getApplyFile().compareTo(u2.getApplyFile()));
						}else {
							if((u1.getApplyFile()==null && u1.getApplyFile()==null) || u1.getApplyFile().toString().equals(u2.getApplyFile())) {
								return 0;
							}
							if(u1.getApplyFile()==null || u1.getApplyFile().equals("")) {
								return 1;
							}else {
								return -1;
							}
						}
					}
					else if(("タイトル").equals(sortColumn)) {
						if(u1.getTitle()!=null && u2.getTitle() !=null && !u1.getTitle().equals("") && !u2.getTitle().equals("")) {

							return invertFlag * (u1.getTitle().compareTo(u2.getTitle()));
						}else {
							if((u1.getTitle()==null && u1.getTitle()==null) || u1.getTitle().toString().equals(u2.getTitle())) {
								return 0;
							}
							if(u1.getTitle()==null || u1.getTitle().equals("")) {
								return 1;
							}else {
								return -1;
							}
						}
					}
					else if(("申請日").equals(sortColumn)) {
						if(u1.getApplyTime()!=null && u2.getApplyTime() !=null && !u1.getApplyTime().equals("") && !u2.getApplyTime().equals("")) {
							return invertFlag * (u1.getApplyTime().compareTo(u2.getApplyTime()));
						}else {
							if((u1.getApplyTime()==null && u1.getApplyTime()==null) || u1.getApplyTime().toString().equals(u2.getApplyTime())) {
								return 0;
							}
							if(u1.getApplyTime()==null || u1.getApplyTime().equals("")) {
								return 1;
							}else {
								return -1;
							}
						}
					}
					else if(("承認日").equals(sortColumn)) {
						if(u1.getApproveTime()!=null && u2.getApproveTime() !=null && !u1.getApproveTime().equals("") && !u2.getApproveTime().equals("")) {
							System.out.println(u1.getApplyId() + "*" + u2.getApplyId());
							System.out.println(u1.getApproveTime().toString() + "*" +u2.getApproveTime().toString());
							//if(u1.getApproveTime().toString().equals(u2.getApproveTime().toString())) return -1;
							return invertFlag * (u1.getApproveTime().toString().compareTo(u2.getApproveTime().toString()));
						}else {
							if((u1.getApproveTime()==null && u1.getApproveTime()==null) || u1.getApproveTime().toString().equals(u2.getApproveTime())) {
								return 0;
							}
							if(u1.getApproveTime()==null || u1.getApproveTime().equals("")) {
								return 1;
							}else {
								return -1;
							}
						}
					}
					else if(("連絡事項").equals(sortColumn)) {
						if(u1.getNoticeMatter()!=null && u2.getNoticeMatter() !=null && !u1.getNoticeMatter().equals("") && !u2.getNoticeMatter().equals("")) {
							return invertFlag * (u1.getNoticeMatter().compareTo(u2.getNoticeMatter()));
						}else {
							if((u1.getNoticeMatter()==null && u1.getNoticeMatter()==null) || u1.getNoticeMatter().toString().equals(u2.getNoticeMatter())) {
								return 0;
							}
							if(u1.getNoticeMatter() == null || u1.getNoticeMatter().equals("")) {
								return 1;
							}else {
								return -1;
							}
						}
					}
					else {
						return invertFlag * 1;
					}
				}catch(Exception e) {
					System.out.println(u1.getApplyId() + "*" + e.getMessage());
					//System.out.println(u1.getApplyTime()==null);
					return 1;
				}
			}
		});
		this.sortColumn = sortColumn;
		this.sortOrder = sortOrder;
		//GetPage(showNumber, 1);
	}

/*    @PersistenceContext
    private EntityManager em;*/

    public void DeleteApply(String applyId) {



    	Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String hqlUpdate = "update G_T.OfficeSystem.model.HibApplicationInfoModel set " + "applyStatus = 4 " + "where applyId = " + applyId;
		System.out.println(hqlUpdate);
		int updatedEntities = session.createQuery( hqlUpdate ).executeUpdate();
		//getJdbcTemplate().queryForObject(hqlUpdate, new Object[] { }, Integer.class);
		//getJdbcTemplate().query(hqlUpdate);
		//getJdbcTemplate().queryForList(hqlUpdate);
		tx.commit();
		session.close();


/*		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		// create update
		CriteriaUpdate update = cb.createCriteriaUpdate(HibApplicationInfoModel.class);
		// set the root class
		Root e = update.from(HibApplicationInfoModel.class);
		// set update and where clause
		update.set("APPLY_STATUS", "4");
		update.where(cb.greaterThanOrEqualTo(e.get("APPLY_ID"), applyId));
		// perform update
		this.em.createQuery(update).executeUpdate();
*/


/*		String hqlUpdate = "update application_info set name = :newName where name = :oldName";
		int updatedEntities = session.createQuery( hqlUpdate )
		        .setString( "APPLY_STATUS", "4" )
		        .setString( "APPLY_ID", applyId )
		        .executeUpdate();
		tx.commit();
		session.close();*/

/*		Session session = null;
    	try {
    		session = sessionFactory.openSession();
	        Criteria criteria = session.createCriteria(HibApplicationInfoModel.class);

		       	//criteria.add(Restrictions.like("userId", "%" + condition.getUserId() + "%"));
	        	session.update();

	    }
    	catch (Exception ex) {
			ex.printStackTrace();

		}
    	finally {
    		session.close();
    	}*/
    }


    //データの状態を変更
    public void ChangeApplyStatus(String applyId,String applyStatus) {

    	Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String hqlUpdate = "update G_T.OfficeSystem.model.HibApplicationInfoModel set "
								+ "applyStatus = " + applyStatus
								+ "where applyId = " + applyId;
		System.out.println(hqlUpdate);
		int updatedEntities = session.createQuery( hqlUpdate ).executeUpdate();
		//getJdbcTemplate().queryForObject(hqlUpdate, new Object[] { }, Integer.class);
		//getJdbcTemplate().query(hqlUpdate);
		//getJdbcTemplate().queryForList(hqlUpdate);
		tx.commit();
		session.close();
    }

    //申請一覧の表示を更新
	public void UpdateApplyList() {
		setAllApplicationList(FindApplicationByCondition(this.status));//ここで全ての時でも　applyのstatus画は行ってる
		SortAll(sortColumn, sortOrder);
		GetPage(showNumber, currentPage);
	}


	//Hibernate対応
	//1つのユーザーIDで抽出　かえってくるのはリスト
	public List<HibProfileInfoModel> FindUserDataFromDataBase(String userId) {
		Session session = null;
    	try {
    		session = sessionFactory.openSession();
	        Criteria criteria = session.createCriteria(HibProfileInfoModel.class);

	       	criteria.add(Restrictions.eq("userId",userId));

	        return criteria.list();
	    }
    	catch (Exception ex) {
			//ex.printStackTrace();
			return null;
		}
    	finally {
    		session.close();
    	}
    }

	public List<HibProfileInfoModel> getProfileInfoModelList(){
		return profileInfoModelList;
	}

	public void setProfileInfoModelList(List<HibProfileInfoModel> profileInfoModelList) {
		this.profileInfoModelList = profileInfoModelList;
	}

	public void FindUserData(String userId) {
		setProfileInfoModelList(FindUserDataFromDataBase(userId));
	}
}
