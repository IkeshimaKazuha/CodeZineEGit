package G_T.OfficeSystem.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;


public class ApplicationInfoModelMapper implements RowMapper<ApplicationInfoModel>{
	public static final String BASE_SQL = "Select * " +
			"from  application_info"  +
			"where 1 = 1";



	@Override
	public ApplicationInfoModel mapRow(ResultSet rs,int rowNum)throws SQLException{
		Integer applyId = rs.getInteger("APPLY_ID");
		Integer applyStatus = rs.getInteger("APPLY_STATUS");
		 String applyFile = rs.getString("APPLY_FILE");
		 String title = rs.getString("TITLE");
		 Timestamp applyTime = rs.getTimestamp("APPLY_TIME");
		 Timestamp approveTime = rs.getTimestamp("APPROVE_TIME");
		 String noticeMatter = rs.getString("NOTICEMATTER");
		 String userId = rs.getString("USER_Id");


		return new ApplicationInfoModel(
			applyId,
			applyStatus,
			applyFile,
			title,
			applyTime,
			approveTime,
			noticeMatter,
			userId
		);

	}
}
