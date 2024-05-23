package G_T.OfficeSystem.model;



public class ApplicationConditionModel {
		private String applyId;
		private Integer applyStatus;
		private String nickName;
		private String userName;
		private Integer sex;
		private String birthday1;
		private String birthday2;
		private String birthday3;
		private String birthday;
		private Integer age;
		private String tel;
		private String postcode;
		private String address;
		private String hireDate1;
		private String hireDate2;
		private String hireDate3;
		private String hireDate;
		private String affiliation;
		private String position;
		private String hobby;
		private String specialSkill;
		private String comment;


		public ApplicationConditionModel() {

		}

		public ApplicationConditionModel (
			String userId,
			String email,
			String nickName,
			String userName,
			Integer sex,
			String birthday1,
			String birthday2,
			String birthday3,
			String birthday,
			Integer age,
			String tel,
			String postcode,
			String address,
			String hireDate1,
			String hireDate2,
			String hireDate3,
			String hireDate,
			String affiliation,
			String position,
			String hobby,
			String specialSkill,
			String comment
		) {
			this.nickName =  nickName;
			this.userName = userName;
			this.sex = sex;
			this.birthday1 = birthday1;
			this.birthday2 = birthday2;
			this.birthday3 = birthday3;
			this.birthday = birthday;
			this.age = age;
			this.tel = tel;
			this.postcode = postcode;
			this.address = address;
			this.hireDate1 = hireDate1;
			this.hireDate2 = hireDate2;
			this.hireDate3 = hireDate3;
			this.hireDate = hireDate;
			this.affiliation = affiliation;
			this.position = position;
			this.hobby = hobby;
			this.specialSkill = specialSkill;
			this.comment = comment;
		}
}
