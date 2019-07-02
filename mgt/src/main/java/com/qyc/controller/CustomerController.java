package com.qyc.controller;

import com.alibaba.fastjson.JSONObject;

import com.qyc.utils.AccessTokenUtil;
import com.qyc.utils.SHA1;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author qianyongchao
 * @date 2019/5/22 15:54
 */
@Controller
@RequestMapping("/*")
@Slf4j
public class CustomerController {

    private String Token = "qianyongchao";

    @RequestMapping(value = "chat", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void liaotian(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("进入chat");

        boolean isGet = request.getMethod().toLowerCase().equals("get");

        System.out.println("请求方式: " + request.getMethod().toLowerCase());

        if (isGet) {
            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String echostr = request.getParameter("echostr");
            System.out.println(signature);
            System.out.println(timestamp);
            System.out.println(nonce);
            System.out.println(echostr);
            access(request, response);
        } else {
            // 进入POST聊天处理
            System.out.println("enter post");
            try {
                // 接收消息并返回消息
                acceptMessage(request, response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 验证URL真实性
     *
     * @return String
     * @author qianyongchao
     * @date 2019-05-23
     */
    private String access(HttpServletRequest request, HttpServletResponse response) {
        // 验证URL真实性
        System.out.println("进入验证access");
        String signature = request.getParameter("signature");// 微信加密签名
        String timestamp = request.getParameter("timestamp");// 时间戳
        String nonce = request.getParameter("nonce");// 随机数
        String echostr = request.getParameter("echostr");// 随机字符串
        List<String> params = new ArrayList<String>();
        params.add(Token);
        params.add(timestamp);
        params.add(nonce);
        // 1. 将token、timestamp、nonce三个参数进行字典序排序
        Collections.sort(params, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        // 2. 将三个参数字符串拼接成一个字符串进行sha1加密
        String temp = SHA1.encode(params.get(0) + params.get(1) + params.get(2));
        if (temp.equals(signature)) {
            try {
                response.getWriter().write(echostr);
                System.out.println("成功返回 echostr：" + echostr);
                return echostr;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("失败 认证");
        return null;
    }


    /**
     * 系统自动回复用户-json好像有bug
     */
    public void acceptMessage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject json = null;
        String openid = request.getParameter("FromUserName");//这是通过通过get方式去url 拼接的键值对，post方式取不到值。
        request.setCharacterEncoding("UTF-8");         //返回页面防止出现中文乱码
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));//post方式传递读取字符流
        String jsonStr = null;
        StringBuilder result = new StringBuilder();
        try {
            while ((jsonStr = reader.readLine()) != null) {
                result.append(jsonStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        reader.close();// 关闭输入流
        json = com.alibaba.fastjson.JSONObject.parseObject(result.toString()); // 取一个json转换为对象
        String jsonStrs = "{\"touser\":\"oNrqO4jET7YbGXdFcGWO-m-94heE\",\"msgtype\":\"text\",\"text\":{ \"content\":\"Hello World\"}}";
        // jsonStrs=new String(jsonStrs.getBytes(),"UTF-8");
        String token = AccessTokenUtil.getWxAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + token;
        JSONObject jsonInfo = null;
        url = url.replaceAll("\"", "\\\"");
        //jsonInfo = CommonUtil.httpsRequest(url, "POST", jsonStrs);
        PrintWriter out = null;
        BufferedReader in = null;
        String result1 = "";
        try {
            URL reponseUrl = new URL("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + token);

            HttpURLConnection connection = (HttpURLConnection) reponseUrl.openConnection();
            connection.setDoOutput(true);

            connection.connect();

            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8);
            writer.write(jsonStrs);
            writer.flush();

            InputStreamReader reader1 = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
            BufferedReader breader = new BufferedReader(reader1);

            StringBuffer strb = new StringBuffer();
            String str = null;

            while (null != (str = breader.readLine())) {
                strb.append(str);
            }

            System.out.println(strb.toString());

            writer.close();

            reader.close();
            breader.close();

            connection.disconnect();

        } catch (Exception e) {
            System.out.println("发送post请求异常");
            e.printStackTrace();
        } finally {
            if (out != null) out.close();
            if (in != null) in.close();
        }

    }
}

