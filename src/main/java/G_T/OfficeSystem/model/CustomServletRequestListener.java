package G_T.OfficeSystem.model;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

//使ってない
public class CustomServletRequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
    	System.out.println("a");



    }
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        // リクエスト終了時の処理を行う。
        // (実装は省略)
    	System.out.println("a");
    }

}