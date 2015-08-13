import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by qqy on 15/8/10.
 */

@Controller
@EnableAutoConfiguration
public class SimpleController {

    @RequestMapping(value = "/weixin/{signature}/{timestamp}/{nonce}/{echostr}",method = RequestMethod.GET)
    @ResponseBody
    public String check(@PathVariable String signature,@PathVariable String timestamp,@PathVariable String nonce,@PathVariable String echostr){

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
