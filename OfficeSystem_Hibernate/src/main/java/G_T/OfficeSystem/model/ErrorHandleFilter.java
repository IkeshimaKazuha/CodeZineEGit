package G_T.OfficeSystem.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


//フィルター　ほぼ最初に
public class ErrorHandleFilter implements Filter {

	@Override
	public void destroy() {
    	System.out.println("destroy");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
    	System.out.println("init");
	}

/*public static <T> T uncheckCall(Callable<T> callable) {
	    try {
	        return callable.call();
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}*/

	@Override
	public void doFilter(ServletRequest request,
               ServletResponse response, FilterChain chain)
		throws IOException, ServletException {

		HttpServletResponse res = ((HttpServletResponse) response);
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		String requestedWithHeader = req.getHeader("X-Requested-With");

		//Routes.getRoutes();

		//RequestMappingの一覧を取得
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);

        scanner.addIncludeFilter(new AnnotationTypeFilter(Controller.class));

        List<String> list = new ArrayList<String>();

        Set<BeanDefinition> beanSet = scanner.findCandidateComponents("G_T.OfficeSystem.controller");

        for (BeanDefinition def : beanSet) {
            Class<?> clazz = null;
			try {
				clazz = Class.forName(def.getBeanClassName());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
            Arrays.stream(clazz.getDeclaredMethods()).map(m -> m.getAnnotation(RequestMapping.class)).filter(
                    a -> a != null && a.value().length > 0).forEach(a -> Arrays.stream(a.value()).forEach(p ->{
                        //System.out.println(p);
                        list.add(p);
                    }));
        }



/*		for (int i=0; i<list.size(); ++i)
		{
		    //System.out.println(list.get(i));
		}*/

/*		if(list.contains(req.getServletPath())) {
			System.out.println("存在している");
		}else {
			System.out.println(req.getServletPath()+"は存在しない");
		}*/






/*		//コントローラにある全てのクラスの全てのメソッド名を取得
		Stream<Object> allClasses = provider.findCandidateComponents("G_T.OfficeSystem.controller").stream()
		        .map(bean -> uncheckCall(() -> Class.forName(bean.getBeanClassName())));
		//System.out.println(allClasses.toString());
	    allClasses.forEach(s -> {
	    	String s1 = s.toString().replaceFirst("class ","");
	    	List<String> methodList = new ArrayList<>();
			try {
				Class<?> clazz = Class.forName(s1);

				//String className = clazz.getName();
				//Method[] methods = clazz.getMethods();
				Method[] methods = clazz.getDeclaredMethods();

				StringBuilder builder = new StringBuilder();
				for (Method method : methods) {
					String methodName = method.getName();
					//builder.append(methodName);
					methodList.add(methodName);
				}
				//System.out.println(builder.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}

			for (int i=0; i<methodList.size(); ++i)
			{
			    System.out.println(methodList.get(i));
			}
	    });



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




/*		StringBuilder sb1 = new StringBuilder();
		BufferedReader reader = req.getReader();
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				sb1.append(line).append('\n');
			}
		} finally {
			reader.close();
		}
		System.out.println(sb1.toString());
		System.out.println();*/

/*		System.out.println(req.getHeaderNames());
 * 		System.out.println();


		Enumeration<String> values = req.getHeaderNames();
		while(values.hasMoreElements()) {
		    System.out.println(values.nextElement());
		}
		req.getHeader("refere");
	    System.out.println();*/


/*		System.out.println(req.getRequestURI());
		//System.out.println(req.getRequestURL());
*/

/*		System.out.println(req.getServletPath()+" req.getServletPath()");
		System.out.println(req.getPathInfo());*/

		try {
			//URL直打ちか
			if(req.getHeader("referer") == null&&!req.getRequestURI().equals("/OfficeSystem_Hibernate/Login")){
				//URLのパスがRequestMappingに存在しているか
				if(list.contains(req.getServletPath())) {
					throw new Exception("BACKLOGIN");
				}else {
					//System.out.println(req.getServletPath()+"は存在しないからエラーページを表示");
					throw new Exception("NotFoundError");
				}
			}

/*			System.out.println(requestedWithHeader);
			System.out.println(req.getRequestURI());
			System.out.println(res.getStatus());*/
/*	        if(req.getRequestURI().equals("/OfficeSystem_Hibernate/Login")&&"XMLHttpRequest".equals(requestedWithHeader)) {
	        	//session.setAttribute("session1","session1を有効にしました");//ログイン失敗しても呼ばれる　変数に保存されてる？コントローラーで消去する
	        }*/

	        if(!req.getRequestURI().equals("/OfficeSystem_Hibernate/Login")&&!req.getRequestURI().equals("/OfficeSystem_Hibernate/SessionTimeout")) {
		        if(session.getAttribute("session1")==null) {
			        if("XMLHttpRequest".equals(requestedWithHeader)) {
			        	//throw new Exception("ajax");
			        }else {
			        	//throw new Exception("タイムアウトページ");//ここではコントローラーに存在するか分からない？エラーもない？一番最初
			        }
		        }
	        }

/*			if(res.getStatus()==404) {
				req.getRequestDispatcher("/WEB-INF/error/NotFoundError.jsp")
                .forward(req, res);
			}*/


		    //コントローラーに入る
			chain.doFilter(req, res);

/*        	try {
				res.sendRedirect("/OfficeSystem_Hibernate/error404");
			} catch (IOException e) {
				e.printStackTrace();
			}*/

/*			if(res.getStatus() == 404) {
				//res.sendError(404,"Error Message");
				//res.sendRedirect("/OfficeSystem_Hibernate/NotFoundError");

				req.getRequestDispatcher("/WEB-INF/error/NotFoundError.jsp")
                .forward(req, res);
				//throw new Exception("NotFoundError");
				try {
					throw new NotFoundException("a");
				} catch (NotFoundException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}*/
		} catch (Exception ex) {
			if(res.getStatus() == 404){
				//res.sendError(404,"Error Message");
				//res.sendRedirect("/OfficeSystem_Hibernate/NotFoundError");
				req.getRequestDispatcher("/WEB-INF/error/NotFoundError.jsp")
                .forward(req, res);
			}

			if(ex.getMessage().equals("ajax")) {
				res.setStatus(500);
	            PrintWriter out = null;
				try {
					//message = new String(message.getBytes("UTF-8"), "UTF-8");
					res.setContentType("text/html; charset=UTF-8");
					//res.setContentType("text/html");
					//res.setCharacterEncoding("UTF-8");
					out = res.getWriter();
		            out.println("タイムアウトしました");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else if(ex.getMessage().equals("タイムアウトページ")) {
				//request.setAttribute("errorMessage", ex);
				req.getRequestDispatcher("/WEB-INF/error/SessionTimeout.jsp")
	                               .forward(req, res);//requestをrequに
			}else if(ex.getMessage().equals("NotFoundError")) {
				req.getRequestDispatcher("/WEB-INF/error/NotFoundError.jsp")
                .forward(req, res);
			}else if(ex.getMessage().equals("BACKLOGIN")) {
				req.getRequestDispatcher("/WEB-INF/pages/Login.jsp")
                .forward(req, res);
			}
		}

	}

}