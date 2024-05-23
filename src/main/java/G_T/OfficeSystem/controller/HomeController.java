package G_T.OfficeSystem.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import G_T.OfficeSystem.router.Routes;

@Controller
public class HomeController extends AbstractController {

    @RequestMapping(value =Routes.Login, params = {"userId","password"}, method = RequestMethod.POST, produces="text/plain;charset=utf-8")
    public String home(ModelMap model)
    {
    	G_T.OfficeSystem.controller.AbstractController.preRender(model);
        return Routes.Login;
    }

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return null;
	}

}