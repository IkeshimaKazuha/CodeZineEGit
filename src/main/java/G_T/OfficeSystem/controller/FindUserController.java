package G_T.OfficeSystem.controller;


import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import G_T.OfficeSystem.model.FindConditionModel;
import G_T.OfficeSystem.model.FindModel;

import G_T.OfficeSystem.model.ApplicationConditionModel;
import G_T.OfficeSystem.model.ApplicationInfoModel;

@Controller
public class FindUserController {
	@Autowired
	FindModel findModel;


	@RequestMapping(value="/CheckSession", method = RequestMethod.POST)
	@ResponseBody
	public String CheckSession(HttpSession session) {

		if(session.getAttribute("session1")==null) {
			return "SessionTimeout";
		}

		return ("");
	}


	//一番最初に検索画面を表示するためのメソッド
	@RequestMapping(value="/Find", method = RequestMethod.GET)
	public String FindView(HttpSession session) {

		//System.out.println(session.getAttribute("session1"));
/*		if(session.getAttribute("session1")==null) {
			return "SessionTimeout";
		}*/

		return "Find";
	}

	@RequestMapping(value="/Find", method = RequestMethod.POST)
	public String FindOperate(HttpSession session, FindConditionModel condition, Model model) {
		findModel.FindUser(condition);

		model.addAttribute("findModel",findModel);
		//int a = 1 / 0;
/*		if(session.getAttribute("session1")==null) {
			model.addAttribute("sessionIsTimeout","no");
		}*/

		return ("_FindResult");
	}

	@RequestMapping(value="/GetPage", method = RequestMethod.POST)
	public String GetPage(HttpSession session,  Model model
			, @RequestParam int showNumber
			, @RequestParam int currentPage){

		findModel.GetPage(showNumber, currentPage);
		model.addAttribute("findModel", findModel);

		return ("_FindResult");
	}

	@RequestMapping(value="/Sort", method = RequestMethod.POST)
	public String Sort(HttpSession session,  Model model
			, @RequestParam String sortColumn
			, @RequestParam String sortOrder){

		findModel.SortAll(sortColumn, sortOrder);//sort
		model.addAttribute("findModel", findModel);

		return ("_FindResult");
	}


//ユーザー検索画面のメール送信ボタンが押された時に飛ばされる
	@RequestMapping(value="/SendMail", method = RequestMethod.GET)
	public String SendMail(HttpSession session) {
		if(session.getAttribute("session1")==null) {
			return "SessionTimeout";
		}

        folderPath="";
    	path1 = "";
    	path2 = "";

    	return "SendMail";
    	//return "SendMail"+ "?destination=" +address;
	}

	//メール送信画面の送信ボタンを押すと飛ぶ
	@RequestMapping(value="/SendMail", method = RequestMethod.POST)
	public String SendMail(HttpSession session,  Model model
			, @RequestBody @RequestParam(required = false) String destination
			, @RequestBody @RequestParam(required = false) String subject
			, @RequestParam(required = false) String mailContent
			, @RequestParam(required = false) String address) {
		System.out.println(address);


        try {
        	//データがカラかどうか確認
			if(!destination.isEmpty() && !subject.isEmpty() && !mailContent.isEmpty()) {
				//;で区切られたメールアドレスを;区切りで配列に保存する
            	String[] dest = destination.split(";",0);

            	//サーバーの保存フォルダ
                StringBuffer filePath = new StringBuffer("C:\\Users\\USER\\Desktop\\a\\");
            	 if(folderPath.equals("")) {
         	        folderPath = mkdirs(filePath);
        	        //System.out.println(folderPath);
            	 }

				File fileDestination = new File(folderPath +"\\"+ "宛先.txt");
				FileWriter filewriter1 = new FileWriter(fileDestination);
	            //filewriter1.write(destination);
	            for (int i = 0; i < dest.length; i++) {
	            	//宛先１：～＠yahoo.co.jp\n宛先２：～＠yahoo.co.jpのような形式で保存
	                filewriter1.write("宛先" + (i+1) + ":" +dest[i] + "\r\n");
	            }
	            filewriter1.close();

				File fileSubject = new File(folderPath +"\\" + "件名.txt");
				FileWriter filewriter2 = new FileWriter(fileSubject);
	            filewriter2.write(subject);
	            filewriter2.close();
			}
			//メールサーバー情報の設定
            MultiPartEmail mail = new MultiPartEmail();
            //ここでGmailを利用しているが、他のメールを利用しても問題ない↓
            mail.setHostName("smtp.gmail.com");
            //暗号化されたものをSMTPで受け取る際の受け取りポートを指定
            mail.setSmtpPort(587);
            // 暗号化するか否かを設定します。trueで暗号化するに設定
            mail.setStartTLSEnabled(true);
            //アカウント情報の設定
            mail.setAuthentication("toyamakunn0930", "cfsd jfqk vlqc psgi");
            //メール情報の設定
            mail.setFrom("toyamakunn0930@gmail.com");
            mail.setSubject(subject);
            mail.setMsg(mailContent);
            mail.setCharset("UTF-8");

        	String[] dest = destination.split(";",0);
            for (int i = 0; i < dest.length; i++) {
                mail.addTo(dest[i]);
            }

            File file1 = new File(path1);
            File file2 = new File(path2);
            //アップロードした「添付ファイル3」のファイルパスを取得
            File file3 = new File(path3);

			EmailAttachment attach1 = new EmailAttachment();
			//viewでファイル選択時サーバーにアップロードされてるので、
			//フォルダが存在しているかの確認
            if(file1.exists()){
	            attach1.setPath(path1);
	            attach1.setDisposition(EmailAttachment.ATTACHMENT);

	            //ファイルを添付
	            mail.attach(attach1);
            }

			EmailAttachment attach2 = new EmailAttachment();
            if(file2.exists()){
	            attach2.setPath(path2);
	            attach2.setDisposition(EmailAttachment.ATTACHMENT);

	            //ファイルを添付
	            mail.attach(attach2);
            }
            //「添付ファイル3」のファイルをメールに添付
            EmailAttachment attach3 = new EmailAttachment();
            if(file3.exists()) {
            	attach3.setPath(path3);
            	attach3.setDisposition(EmailAttachment.ATTACHMENT);
            	
            	//ファイルを添付
            	mail.attach(attach3);
            }
            
            //pr++;
            folderPath="";
        	path1 = "";
        	path2 = "";
        	//「添付ファイル3」のファイルパスを保持する変数の値をリセット
        	path3 = "";

            //mail.setDebug(true);  //必要に応じて
        	//メールサーバーにリクエストを送る
            mail.send();

            //メールサーバーから届いたエラー結果を処理して、クライアントにエラーメッセージを表示させるようにする
 		} catch (IOException e) {
		} catch (EmailException e) {
			// TODO 自動生成された catch ブロック
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
        //メールサーバーの処理が成功した場合、クライアントにメール送信完了画面を返す
        return "SendMailFinish";
	}

    private String mkdirs(StringBuffer filePath){
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        File uploadDir = new File(filePath.toString(), sdf.format(now));
        // 既に存在する場合はプレフィックスをつける
        int prefix = 0;
        while(uploadDir.exists()){
            prefix++;
            uploadDir =
                    new File(filePath.toString() + sdf.format(now) + "-" + String.valueOf(prefix));
        }

        // フォルダ作成
        uploadDir.mkdirs();

        return uploadDir.toString();
    }


	@RequestMapping(value="/SendMailFinish", method = RequestMethod.GET)
	public String SendMailFinish(HttpSession session) {
		if(session.getAttribute("session1")==null) {
			return "SessionTimeout";
		}
		return "SendMailFinish";
	}

	String folderPath ="";
	String path1 = "";
	String path2 = "";
	String path3 = "";
	File uploadDir;
	int pr = 0;


	private static void recursiveDeleteFile(final File file) throws Exception {
	    // 存在しない場合は処理終了
	    if (!file.exists()) {
	        return;
	    }
	    // 対象がディレクトリの場合は再帰処理
	    if (file.isDirectory()) {
	        for (File child : file.listFiles()) {
	            recursiveDeleteFile(child);
	        }
	    }
	    // 対象がファイルもしくは配下が空のディレクトリの場合は削除する
	    file.delete();
	}

	//サーバーにあるファイルの削除
	@RequestMapping(value="/SendMailDeleteFile", method = RequestMethod.POST)
	public String SendMailDeleteFile(HttpSession session,  Model model
			, @RequestParam String id) {
        File fileFolder = new File(folderPath+"\\"+id);
        try {
			recursiveDeleteFile(fileFolder);
			fileFolder = new File(folderPath+"\\"+id);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return "SendMail";
	}


	@RequestMapping(value="/SendMailFileUpload", method = RequestMethod.POST)
	public String SendMailFileUpload(HttpServletRequest request, HttpSession session,  Model model
			, @RequestParam(required = false) MultipartFile file
			, @RequestParam(required = false) String number) {
	     	//System.out.println(System.getProperty("file.encoding"));

		//Webアプリフォルダーを取得
		ServletContext context = request.getServletContext();
		String appPath =context.getRealPath("");
		
        // ファイル種類から決まる値をセットする
        StringBuffer filePath = new StringBuffer(appPath + "\\upload");
        // アップロードファイルを格納するディレクトリを作成する
        if(folderPath.equals("")) {
	        folderPath = mkdirs(filePath);
	        //System.out.println(folderPath);
        }

        File fileFolder = new File(folderPath+"\\"+number);
        try {
			recursiveDeleteFile(fileFolder);
			fileFolder = new File(folderPath+"\\"+number);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        fileFolder.mkdirs();

        System.out.println(fileFolder.getPath());

        if(!file.isEmpty()){
        	File uploadFile = new File(fileFolder.getPath() +"\\"+ file.getOriginalFilename());
        	switch(number) {
        		case("file1"):
        			path1 = fileFolder.getPath() +"\\"+ file.getOriginalFilename();
        			break;
        		case("file2"):
        			path2 = fileFolder.getPath() +"\\"+ file.getOriginalFilename();
        			break;
        		case("file3"):
        			path3 = fileFolder.getPath() +"\\"+ file.getOriginalFilename();
        			break;
        	}

        	byte[] bytes;
			try {
				bytes = file.getBytes();
            	BufferedOutputStream uploadFileStream = new BufferedOutputStream(new FileOutputStream(uploadFile));
				uploadFileStream.write(bytes);
				uploadFileStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }

		System.out.println(file.toString());
		return "SendMail";
	}

	@RequestMapping(value="/DownloadFile", method = RequestMethod.GET)   
	 public void DownloadFile(
			 HttpServletRequest request
			 ,HttpServletResponse response
			 ,@RequestParam(required = false) String id
			 ,@RequestParam(required = false) String name) throws Exception{

		File file = null;
		switch(id) {
		case("file1"):
			file = new File(path1);
	    break;
	   case("file2"):   
		   file = new File(path2);
	    break;   
	   case("file3"):
		   file = new File(path3);
	    break;   
	  }   
	   
	  //ファイル名をUTF-8の文字コードに変換する   
	  String fileName = URLEncoder.encode(file.getName(),"UTF-8");
	  //ファイルのタイプを取得 
	  String mimeType = URLConnection.guessContentTypeFromName(fileName);
	  //サーバーからダウンロードされるファイルのタイプをクライアントに教える
	  response.setContentType(mimeType);
	  //クライアントでダウンロードされるファイル名を指定する 
	  response.setHeader("Content-Disposition","attachment;filename*=utf-8''" + fileName);
	  response.setContentLength((int)FileUtils.sizeOf(file));
	  
	  InputStream inputStream = new FileInputStream(file);
	  //ファイルの部分ごとをクライアントに送信する
	  FileCopyUtils.copy(inputStream,response.getOutputStream());
	  //ダウンロード処理を完了させる   
	  response.flushBuffer();
	  inputStream.close();
	}
	 @Autowired
	 ApplicationInfoModel applicationInfoModel;
	@RequestMapping(value="/ApplicationManage", method = RequestMethod.GET)
	 public String ApplicationManage() {
		  return "ApplicationManage";
		}
	@RequestMapping(value = "/ApplicationManage", method = RequestMethod.POST)
	 public String ApplicationManage(HttpSession session, ApplicationConditionModel condition, Model model) {
	  applicationInfoModel.(condition);
	  model.addAttribute(" applicationInfoModel",  applicationInfoModel);
	  return ("_ApplicationList");
	}
}

/*	@RequestMapping(value="/SendMailInsertAddress", method = RequestMethod.POST)
	public String SendMailInsertAddress(HttpSession session,  Model model
			, @RequestParam String address) {


		return "SendMail";
	}*/
