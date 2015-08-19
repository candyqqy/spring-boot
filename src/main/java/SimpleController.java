import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by qqy on 15/8/10.
 */

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "weixin")
public class SimpleController {

    @RequestMapping(value = "check", method = RequestMethod.GET, params = {"signature", "timestamp", "nonce", "echostr"})
    public String check(String signature, String timestamp, String nonce, String echostr) {

        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        }
        return "check";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST)
    public static String process(HttpServletRequest request,HttpServletResponse response) throws Exception {
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
            respContent = "text";
            return respContent;
        }
        //图片消息
        else if (msgType.equals("image")) {
            respContent = "image";
            return respContent;
        }
        //声音消息
        else if (msgType.equals("voice")) {
            respContent = "voice";
            return respContent;
        }
        //视频消息
        else if (msgType.equals("video")) {
            respContent = "video";
            return respContent;
        }
        //地理位置
        else if (msgType.equals("location")) {
            respContent = "location";
            return respContent;
        }
        //事件类型
        else if (msgType.equals("event")) {
            String eventType = map.get("Event");
            //订阅
            if (eventType.equals("subscribe")) {
                respContent = "欢迎订阅我的公众号！";
//                TextMessage textMessage = Map2Bean.parseText(map,respContent);
//                result = Bean2ResponseXML.textMessageToXml(textMessage);
                return respContent;
            }
            //取消订阅
            else if (eventType.equals("unsubscribe")) {
                respContent = "unsubscribe";
                return respContent;
            }
            //点击菜单
            else if (eventType.equals("CLICK")) {
                respContent = "CLICK";
                return respContent;
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
