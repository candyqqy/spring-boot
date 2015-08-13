import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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
        return "check";
    }

    @RequestMapping(value = "/weixin",method = RequestMethod.GET,params = {"signature","timestamp","nonce","token"})
    @ResponseBody
    public String getMessage(String signature,String timestamp,String nonce,String token){

        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            return token;
        }
        return "getMessage";
    }

    @RequestMapping(value = "/weixin",method = RequestMethod.GET)
    @ResponseBody
    public static String process(HttpServletRequest request,HttpServletResponse response) throws Exception{
        @SuppressWarnings("unchecked")
        Map<String, String> map = RequestXML2Map.parseXml(request);
        String result = "";
        String msgType = map.get("MsgType");
        String respContent = "";
        //文本消息
        if (msgType.equals("text")) {
//            respContent = TulingRobot.robot(map.get("Content"));
//            TextMessage textMessage = Map2Bean.parseText(map,respContent);
//            result = Bean2ResponseXML.textMessageToXml(textMessage);
        }
        //图片消息
        else if (msgType.equals("image")) {
            respContent = "";
            return null;
        }
        //声音消息
        else if (msgType.equals("voice")) {
            respContent = "";
            return null;
        }
        //视频消息
        else if (msgType.equals("video")) {
            respContent = "";
            return null;
        }
        //地理位置
        else if (msgType.equals("location")) {
            respContent = "";
            return null;
        }
        //事件类型
        else if (msgType.equals("event")) {
            String eventType = map.get("Event");
            //订阅
            if (eventType.equals("subscribe")) {
                respContent = "欢迎订阅我的公众号！";
//                TextMessage textMessage = Map2Bean.parseText(map,respContent);
//                result = Bean2ResponseXML.textMessageToXml(textMessage);
            }
            //取消订阅
            else if (eventType.equals("unsubscribe")) {
                // TODO
                return null;
            }
            //点击菜单
            else if (eventType.equals("CLICK")) {
                // TODO
                return null;
            }
        }
        return result;
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
