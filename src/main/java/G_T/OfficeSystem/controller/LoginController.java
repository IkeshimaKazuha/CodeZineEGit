package G_T.OfficeSystem.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import G_T.OfficeSystem.model.UserBasicInfoModelDAO;
import G_T.OfficeSystem.model.UserInfoModelDAO;


@Controller
public class LoginController {

	@Autowired
	private UserInfoModelDAO userInfoModelDAO;

	@Autowired
	private UserBasicInfoModelDAO userBasicInfoModelDAO;

	@RequestMapping(value="/error404", method = RequestMethod.GET)
	public String error404(HttpSession session) {
		return "error404";
	}
	@RequestMapping(value="/error403", method = RequestMethod.GET)
	public String error403(HttpSession session) {
		return "error403";
	}

/*	@RequestMapping(value="/InternalError", method = RequestMethod.GET)
	public String InternalError(HttpSession session) {

		return "InternalError";
	}*/

	@RequestMapping(value="/Login", method = RequestMethod.GET)
	public String GetLogin(Model model, HttpSession session) {
/*		HttpSession session = new HttpSession();*/

		//int a = 1 / 0;
		//System.out.println(session.getAttribute("session1"));
		model.addAttribute("iserror",false);

		return "Login";
	}

	@RequestMapping(value = "/Login-error", method = RequestMethod.GET)
	public String loginError(Model model) {
		 model.addAttribute("iserror",true);
		 return "Login";
	}

	@RequestMapping(value = "/LoginTest", method = RequestMethod.GET)
	public String loginTest(Model model) {
		 return "LoginTest";
	}

	@RequestMapping(value = "/my-login", method = RequestMethod.GET)
	public String myLogin(Model model) {
		 return "my-login";
	}





	@RequestMapping(value="/Login", params = {"userId", "password"}, method = RequestMethod.POST, produces="text/plain;charset=utf-8")
	public @ResponseBody String Login(Model model,HttpSession session,
		@RequestParam(value = "userId") String userId,
		@RequestParam(value = "password") String password
	) {
		//int a = 1 / 0;

/*		if (userId == "" || password == "") {
			model.addAttribute("error", "ユーザーIDまたはパスワードが未入力です。");
			return "Login";
		}*/


		//	ユーザーIDとパスワードがデータベースに存在するかどうかチェック
		Integer count = userInfoModelDAO.CheckUser(userId, password);

		//	存在しない場合、ログイン画面に「ユーザーIDまたはパスワードが存在しません」のエラーメッセージを表示する
		if (count == 0) {
			//session.setAttribute("session1",null);
			return "";
		}

		//List<UserInfoModel> list = userInfoModelDAO.SaveUserDateInSession(userId, password, session);
		userBasicInfoModelDAO.SaveUserDateInSession(userId, password, session);

		session.setAttribute("session1","session1を有効にしました");
		System.out.println(session.getAttribute("session1"));

		return "Find";
	}


}