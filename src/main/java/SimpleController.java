import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by qqy on 15/8/10.
 */

@Controller
@EnableAutoConfiguration
public class SimpleController {

    @RequestMapping(value = "/weixin",method = RequestMethod.GET,params = {"signature","timestamp","nonce","echostr"})
    @ResponseBody
    public String check(String signature,String timestamp,String nonce,String echostr){

        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        }
        return "false";
    }

//    @RequestMapping(value ="/hello", method = RequestMethod.GET)
//    @ResponseBody
//    public String hello(){
//        return "hello world";
//    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleController.class, args);
    }
}
