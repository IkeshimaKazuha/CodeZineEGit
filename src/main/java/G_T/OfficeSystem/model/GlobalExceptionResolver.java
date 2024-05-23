
package G_T.OfficeSystem.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

//エラーが起きたとき
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    public ModelAndView resolveException(
                        HttpServletRequest request,
                        HttpServletResponse response,
                        Object object,
                        Exception ex) {
    	//System.out.println(response.getStatus());
    	//HTTPException he = (HTTPException) ex;
    	//System.out.println(he.getStatusCode());

//    	ex.printStackTrace();

        StringBuffer filePath = new StringBuffer("C:\\Users\\USER\\Desktop\\a\\error\\");
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        File uploadDir = new File(filePath.toString(), sdf.format(now));
        int prefix = 0;
        while(uploadDir.exists()){
            prefix++;
            uploadDir = new File(filePath.toString() + sdf.format(now) + "-" + String.valueOf(prefix));
        }
        uploadDir.mkdirs();
        String folderPath = uploadDir.toString();
		File fileDestination = new File(folderPath +"\\"+ "エラーログ.txt");
		FileWriter filewriter1 = null;
		try {
			filewriter1 = new FileWriter(fileDestination);
        	for (int i = 0; i < ex.getStackTrace().length; i++) {
				filewriter1.write(ex.getStackTrace()[i] + "\r\n");
        	}
			filewriter1.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}




		///HttpSession session = request.getSession();
		String requestedWithHeader = request.getHeader("X-Requested-With");
        if("XMLHttpRequest".equals(requestedWithHeader)) {
        	//ajax
/*	        try {
				response.sendRedirect("/OfficeSystem_Hibernate/InternalError");
			} catch (IOException e) {
				e.printStackTrace();
			}*/
/*	        try {
				request.getRequestDispatcher("/WEB-INF/error/InternalError.jsp")
				.forward(request, response);
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}*/
/*            try {
				response.sendError(404,"タイムアウトが発生しました");
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}*/
        	//System.out.println(ex.toString());
        	response.setStatus(500);//エラーコード設定？？
            PrintWriter out = null;
			try {
				out = response.getWriter();
	            out.println(ex.toString()+"が発生しました。");
			} catch (IOException e) {
				e.printStackTrace();
			}
        	return null;
        }else {
            ModelAndView mav = new ModelAndView();
            mav.addObject("errmessage", "予期せぬエラーが発生しました。" + " 詳細：【" + ex.getMessage() + "】");
            mav.setViewName("InternalError");
            return mav;
        }


/*        //logger.log_error(ex, "HandlerExceptionResolver拡張クラス で例外をハンドリングしました");

        ModelAndView mav = new ModelAndView();
        // Viewに表示するメッセージをセットします。
        mav.addObject("errmessage", "予期せぬエラーが発生しました。" + " 詳細：【" + ex.getMessage() + "】");


        // 遷移先のJSPを指定します。(error.jspに遷移します。)
        mav.setViewName("InternalError");
        //エラーでここにくるがajaxの場合　ページを返すつまりエラーを返さないつまり成功するだけ
        return mav;
*/
    }

}