package cn.itcast.zt;

/**
 * Created by zhangtian on 2017/5/12.
 */
public class CommonResult {
    private Integer code ;
    private String message ;

    public CommonResult(){

    }

    public CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static CommonResult ok(){
        return new CommonResult(200, "注册成功") ;
    }

    public static CommonResult build(Integer code, String message) {
        return new CommonResult(code, message) ;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
