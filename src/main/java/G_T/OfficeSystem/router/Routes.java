package G_T.OfficeSystem.router;

import java.util.HashMap;

public class Routes{

    private static HashMap<String, String> routes;

    public static final String Login = "/Login";
    public static final String Find = "/Find";

    private static void setRoutes()
    {
        if(routes == null)
        {
            routes = new HashMap<String, String>();

            routes.put("Login", Login);
            routes.put("Find", Find);
        }
    }

    public static HashMap<String, String> getRoutes()
    {
        setRoutes();

        return routes;
    }

    public static String getRoute(String destin)
    {
        setRoutes();

        return routes.get(destin);
    }

}