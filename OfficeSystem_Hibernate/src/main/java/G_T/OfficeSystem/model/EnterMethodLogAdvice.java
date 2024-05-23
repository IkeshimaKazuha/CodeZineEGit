package G_T.OfficeSystem.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//AOP
@Aspect
@Component
public class EnterMethodLogAdvice {


	@After("execution(* G_T.OfficeSystem.controller.*.*(..))")
    public void after(JoinPoint jp) {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)attributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpSession session = request.getSession();
        HttpServletResponse res = servletRequestAttributes.getResponse();

/*        System.out.println(res.getStatus());*/


		return;
    }

	//引数は使えないか
	//within クラス だからログインだけ別クラスとか　複数なら||
	//存在してる時だけ来るつまり　デフォルトは存在してない時の機能？でそれをキャンセルまたは　どっちが先かで
	@Before("execution(* G_T.OfficeSystem.controller.*.*(..))")//ここでメソッドみてる？存在してるかチェックは？存在してる場合だけ入るなら存在してない場合違うと子とか
    public void before(JoinPoint jp) {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)attributes;
        HttpServletRequest req = servletRequestAttributes.getRequest();
        HttpSession session = req.getSession();
        HttpServletResponse res = servletRequestAttributes.getResponse();

/*		StringBuilder sb = new StringBuilder();
		// 全リクエストヘッダ名を取得
		Enumeration<?> headerNames = req.getHeaderNames();
		while (headerNames.hasMoreElements()) {

		    // ヘッダ名と値を取得
		    String headerName = (String)headerNames.nextElement();
		    String headerValue = req.getHeader(headerName);

		    sb.append(headerName);
		    sb.append("=");
		    sb.append(headerValue);
		    sb.append("\n");
		}
		System.out.print(sb);
		System.out.println();*/
/*		System.out.println(req.getRequestURL());
        System.out.println(res.getStatus());
*/

    }


/*
    //@Before("execution(* G_T.OfficeSystem.controller.LoginController.*(..))")
	@Before("execution(* G_T.OfficeSystem.controller.LoginController.Login(..))")
    public void beforeLogin() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)attributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("session1","session1が有効です");
		System.out.println(session.getAttribute("session1"));
    }

	@Before("execution(* G_T.OfficeSystem.controller.*.*(..))")
    public void beforeView(JoinPoint jp) {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)attributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpSession session = request.getSession();

        HttpServletResponse res = servletRequestAttributes.getResponse();

//        System.out.println(res.toString());
//        System.out.println(jp.getSignature().getName());
        if(jp.getSignature().getName().equals("Login")) {
        	session.setAttribute("session1","session1が有効です");//ログイン失敗しても呼ばれる　変数に保存されてる？コントローラーで消去する
        }
        if(!jp.getSignature().getName().equals("OpenLogin")&&!jp.getSignature().getName().equals("SessionTimeout")) {
			if(session.getAttribute("session1")==null) {
		        String requestedWithHeader = request.getHeader("X-Requested-With");
		        if("XMLHttpRequest".equals(requestedWithHeader)) {
					try {
						request.getRequestDispatcher("/WEB-INF/error/SessionTimeout.jsp")
						.forward(request, res);
					} catch (ServletException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					} catch (IOException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
		            try {
						res.sendError(404,"タイムアウトが発生しました");
					} catch (IOException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}

		        	//res.setStatus(500);

			        try {
						res.sendRedirect("/OfficeSystem_Hibernate/SessionTimeout");
					} catch (IOException e) {
						e.printStackTrace();
					}

		    		try {
		    			   throw new Exception("ajax");
		    		} catch (Exception ex) {
		    			if(ex.getMessage().equals("ajax")) {
		    				res.setStatus(500);
		    			}else if(ex.getMessage().equals("ぺージ")) {

		    			}
		    		}




			        ModelAndView mav = new ModelAndView();
			        mav.addObject("message", "予期せぬエラーが発生しました。");
			        return mav;
		        }else {
			        ModelAndView mav = new ModelAndView();
			        mav.setViewName("SessionTimeout");

		        	//res.setStatus(500);

		        	//View view = new View("/WEB-INF/error/SessionTimeout.jsp");
			        //return "/WEB-INF/error/SessionTimeout.jsp";
			        //return "SessionTimeout";

		            try {
		            	res.sendError(404,"タイムアウトが発生しました");
		            } catch (IOException e) {
					// TODO 自動生成された catch ブロック
		            	e.printStackTrace();
		            }



		        	try {
						res.sendRedirect("/OfficeSystem_Hibernate/SessionTimeout");
					} catch (IOException e) {
						e.printStackTrace();
					}



		        	ServletContext context = request.getServletContext();
		        	RequestDispatcher rd = context.getRequestDispatcher("/SessionTimeout");
		        	try {
						rd.forward(request, res);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

			        //return new ModelAndView("/WEB-INF/error/SessionTimeout");
		        }
			}
        }

        //res.setStatus(200);
		return;


    }

	@Before("execution(* G_T.OfficeSystem.controller.*.*Operate(..))")
    public ModelAndView beforeOperate() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)attributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpSession session = request.getSession();


        String requestedWithHeader = request.getHeader("X-Requested-With");
        System.out.println(requestedWithHeader);
        //return "XMLHttpRequest".equals(requestedWithHeader);

		if(session.getAttribute("session1")==null) {
			System.out.println(session.getAttribute("session1"));
			//メッセージ
	        ModelAndView mav = new ModelAndView();
	        // JSPに表示するメッセージをセットします。
	        mav.addObject("message", "予期せぬエラーが発生しました。" +
	                        " 詳細：【"  + "】");
	        mav.setViewName("SessionTimeout");
	        return mav;

		}
		return null;

    }*/
}


/*@Aspect
@Component
public class EnterMethodLogAdvice implements MethodBeforeAdvice {

    //@Before("execution(* G_T.OfficeSystem.controller.*.*(..))")
    @Before("execution(* *..*Controller.*(..))")
    public void before() {
        System.out.println("before !!");
    }

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println("beforemethod !!");
	}

}*/