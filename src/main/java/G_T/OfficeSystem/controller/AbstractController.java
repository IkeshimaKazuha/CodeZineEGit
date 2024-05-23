package G_T.OfficeSystem.controller;

import org.springframework.ui.ModelMap;

import G_T.OfficeSystem.router.Routes;

public abstract class AbstractController {

    protected static void preRender(ModelMap model) {
        model.addAttribute("routes", Routes.getRoutes());
    }

}