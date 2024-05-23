package G_T.OfficeSystem.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import G_T.OfficeSystem.model.UserBasicInfoModel;
import G_T.OfficeSystem.model.UserBasicInfoModelDAO;


@Controller
public class UserBasicInfoController {

	@Autowired
	private UserBasicInfoModel userBasicInfoModel;


	@Autowired
	private UserBasicInfoModelDAO userBasicInfoModelDAO;

	@RequestMapping(value="/UserBasicInfo", method = RequestMethod.GET)
	public String UserBasicInfoGet(Model model, HttpSession session) {
		userBasicInfoModel.setUserId(session.getAttribute("userId").toString());
		userBasicInfoModel.setEmail(session.getAttribute("email").toString());
		model.addAttribute(userBasicInfoModel);

		return "UserBasicInfo";
	}

	@RequestMapping(value="/UserBasicInfoChange", method = RequestMethod.GET)
	public String UserBasicInfoChangeGet(Model model, HttpSession session) {
		model.addAttribute(userBasicInfoModel);
		return "UserBasicInfoChange";
	}

	@RequestMapping(value="/UserBasicInfoChangeCompletion", method = RequestMethod.GET)
	public String UserBasicInfoChangeCompletionGet(Model model, HttpSession session) {
		model.addAttribute(userBasicInfoModel);
		return "UserBasicInfoChangeCompletion";
	}

	@RequestMapping(value="/UserBasicInfoChangeConfirmation", method = RequestMethod.GET)
	public String UserBasicInfoChangeConfirmationGet(Model model, HttpSession session) {

		model.addAttribute(userBasicInfoModel);
		return "UserBasicInfoChangeConfirmation";
	}

	@RequestMapping(value="/CheckRegistration", method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.OK)
	@ResponseBody
	public String CheckRegistration(Model model, HttpSession session
			,@RequestParam(value = "userId") String userId
			,@RequestParam(value = "oldPassword") String oldPassword
			,@RequestParam(value = "email") String email
			,@RequestParam(value = "newPassword") String newPassword
			) {

		//存在するかじゃなくてログインユーザーと一致するかだからだめ
/*		Integer count = userBasicInfoModelDAO.checkTable(userId,oldPassword);
		if (count == 0) {
			return "";
		}*/
		if(!session.getAttribute("userId").toString().equals(userId)||!session.getAttribute("password").toString().equals(oldPassword)) {
			return "ng";
		}
		System.out.println(session.getAttribute("userId").toString());
		System.out.println(session.getAttribute("password").toString());

		session.setAttribute("newEmail", email);//ここで確定じゃない型別名で保存する
		session.setAttribute("newPassword", newPassword);
		userBasicInfoModel.setNewEmail(session.getAttribute("newEmail").toString());
		userBasicInfoModel.setNewPassword(session.getAttribute("newPassword").toString());

		session.setAttribute("oldPassword", oldPassword);
		userBasicInfoModel.setOldPassword(session.getAttribute("oldPassword").toString());

		model.addAttribute(userBasicInfoModel);
		return "";
	}

	@RequestMapping(value="/UpdatePassword", method = RequestMethod.GET)
	public String UpdatePassword(Model model, HttpSession session) {
		model.addAttribute(userBasicInfoModel);
		userBasicInfoModelDAO.updatePassword(session.getAttribute("userId").toString(), session.getAttribute("newPassword").toString());
        session.setAttribute("userId", null);
        session.setAttribute("email", null);
        session.setAttribute("newPassword", null);
        session.setAttribute("oldPassword", null);
        session.setAttribute("password", null);

		return "UserBasicInfoChangeConfirmation";
	}



}