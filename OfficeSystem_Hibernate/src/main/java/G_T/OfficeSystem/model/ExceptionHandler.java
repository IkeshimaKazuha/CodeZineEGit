package G_T.OfficeSystem.model;

import org.springframework.web.bind.annotation.ControllerAdvice;

//エラー
@ControllerAdvice
public class ExceptionHandler {

/*    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @org.springframework.web.bind.annotation.ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    @ResponseBody
    public Map<String, Object> handleError() {
        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("message", "許可されていないメソッド");
        errorMap.put("status", HttpStatus.METHOD_NOT_ALLOWED);
       return errorMap;
    }*/

}