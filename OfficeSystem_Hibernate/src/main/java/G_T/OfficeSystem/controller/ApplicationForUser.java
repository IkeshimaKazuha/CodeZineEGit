package G_T.OfficeSystem.controller;

import java.awt.Dimension;
import java.awt.Font;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import G_T.OfficeSystem.model.ApplicationInfoModel;

@Controller
public class ApplicationForUser {
	@Autowired
	ApplicationInfoModel applicationInfoModel;


	//申請内容画面
	@RequestMapping(value="/ApplicationInfo", method = RequestMethod.GET)
	public String ApplicationInfo(HttpSession session) {
/*		if(session.getAttribute("session1")==null) {
			return "SessionTimeout";
		}*/

		return "ApplicationInfo";
	}

	//書類申請画面
	@RequestMapping(value="/ApplicationManage", method = RequestMethod.GET)
	public String ApplicationManage(HttpSession session) {
/*		if(session.getAttribute("session1")==null) {
			return "SessionTimeout";
		}*/

		//applicationInfoModel.userIdIsSet=false;
		applicationInfoModel.setUserIdIsSet(false);
		return "ApplicationManage";
	}

	//書類申請画面　状態変更時
	@RequestMapping(value="/ApplicationManage", method = RequestMethod.POST)
	public String GetApplicationManage(HttpSession session, Model model
		,@RequestParam String status){
		applicationInfoModel.FindApplication(status);
		model.addAttribute("applicationInfoModel",applicationInfoModel);
		return ("_ApplicationList");

	}

	//書類申請画面　ソート
	@RequestMapping(value="/SortApplication", method = RequestMethod.POST)
	public String Sort(HttpSession session,  Model model
			, @RequestParam String sortColumn
			, @RequestParam String sortOrder){

		applicationInfoModel.SortAll(sortColumn, sortOrder);//sort
		applicationInfoModel.GetPage(10, 1);
		model.addAttribute("applicationInfoModel", applicationInfoModel);
		return ("_ApplicationList");
	}

	//書類申請画面　ページ取得
	@RequestMapping(value="/GetPageApplication", method = RequestMethod.POST)
	public String GetPage(HttpSession session,  Model model
			, @RequestParam int showNumber
			, @RequestParam int currentPage){

		applicationInfoModel.GetPage(showNumber, currentPage);
		model.addAttribute("applicationInfoModel", applicationInfoModel);

		return ("_ApplicationList");
	}


	//データ１つをクリック　申請内容画面を開く
	@RequestMapping(value="/ApplicationInsertDataGet", method = RequestMethod.GET)
	public String ApplicationInsertDataGet(HttpSession session,  Model model
			, @RequestParam(required = false) String applyStatus
			, @RequestParam(required = false) String applyId
			, @RequestParam(required = false) String applyFile
			, @RequestParam(required = false) String title
			, @RequestParam(required = false) String status){
/*		if(session.getAttribute("session1")==null) {
			return "SessionTimeout";
		}*/


		String title1 = "";
		String applyFile1 = "";
		try {
			 title1 = new String(title.getBytes("ISO-8859-1"), "UTF-8");
			 applyFile1 = new String(applyFile.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

/*		String decoded;
		 try {
			decoded = URLDecoder.decode(title, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}*/
/*		System.out.println(applyStatus + "*" + applyId + "*" + applyFile + "*" + title);*/
		applicationInfoModel.setApplyStatus(applyStatus);
		applicationInfoModel.setApplyId(applyId);
		applicationInfoModel.setApplyFile(applyFile1);
		applicationInfoModel.setTitle(title1);
		applicationInfoModel.setStatus(status);

/*		System.out.println(applicationInfoModel.applyStatus);*/
		model.addAttribute("applicationInfoModel", applicationInfoModel);

		return "ApplicationInfo";
	}


	//申請書類をダウンロード
	@RequestMapping(value="/Download", method = RequestMethod.GET)
	public void Download(HttpServletResponse response
			, @RequestParam(required = false) String link) throws Exception {
	  Resource file = new FileSystemResource(link);
	  response.setContentType("text/plain");
	  response.setContentLength((int)FileUtils.sizeOf(file.getFile()));
	  response.setHeader("Content-Disposition","attachment; filename=\"" + file.getFile().getName() +"\"");
	  FileCopyUtils.copy(file.getInputStream(), response.getOutputStream());
	}


	//データの申請状態を変更
	@RequestMapping(value="/ChangeApplyStatus", method = RequestMethod.POST)
	public String ChangeApplyStatus(HttpSession session,  Model model
			, @RequestParam String applyId
			, @RequestParam String applyStatus){

		applicationInfoModel.ChangeApplyStatus(applyId,applyStatus);

		return ("ApplicationInfo");
	}


	//通知メールの送信
	@RequestMapping(value="/NoticeMail", method = RequestMethod.POST)
	public String SendMail(HttpSession session,  Model model
			, @RequestParam(required = false) String sendEmailAddress
			, @RequestParam(required = false) String applyId
			, @RequestParam(required = false) String emailContent) {
		System.out.println(sendEmailAddress);

        try {
            MultiPartEmail mail = new MultiPartEmail();
            mail.setHostName("smtp.mail.yahoo.co.jp");
            mail.setSmtpPort(587);
            mail.setStartTLSEnabled(true);
            mail.setAuthentication("gtyoshiaki", "GTTANAKA");
            mail.setFrom("gtyoshiaki@yahoo.co.jp");
            mail.setSubject("申請ID：" + applyId + "を差し戻しました");
            mail.setMsg("・差し戻し理由\n\n" + emailContent);
            mail.setCharset("UTF-8");
            mail.addTo(sendEmailAddress);
            mail.send();

		} catch (EmailException e) {
			e.printStackTrace();
			JFrame frame = new JFrame("タイトル");

            frame.setBounds(100, 100, 300, 250);
            frame.setLocationRelativeTo(null);
            //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setAlwaysOnTop(true);

            JPanel p = new JPanel();
            p.setSize(200,150);

            JLabel label = new JLabel();
            label.setPreferredSize(new Dimension(200,150));
            label.setText("EmailExceptionが発生しました");

            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            label.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 24));

            JButton button = new JButton();
            button.setBounds(50, 50, 50, 30);
            button.setText("OK");

            frame.add(p);
            p.add(label);
            //p.add(button);

            frame.setVisible(true);
            return "";
		}


        return "SendMailFinish";
	}

	//申請内容画面から状態を変更した後に申請一覧の表示を更新
	@RequestMapping(value="/UpdateApplyList", method = RequestMethod.POST)
	public String UpdateApplyList(HttpSession session,  Model model){

		applicationInfoModel.UpdateApplyList();
		model.addAttribute("applicationInfoModel",applicationInfoModel);

		return ("_ApplicationList");
	}

	//検索画面の申請確認ボタン
	@RequestMapping(value="/ApplicationManageWithUserId", method = RequestMethod.GET)
	public String ApplicationManageWithUserId(HttpSession session,  Model model
			,@RequestParam String userId){

		String userId2 = "";
		try {
			userId2 = new String(userId.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
/*		if(session.getAttribute("session1")==null) {
			return "SessionTimeout";
		}*/

		applicationInfoModel.FindUserData(userId2);



		String userId1 = "";
		try {
			 userId1 = new String(userId.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

		applicationInfoModel.setUserId(userId);

		model.addAttribute("applicationInfoModel", applicationInfoModel);

		return ("ApplicationManage");
	}

}
