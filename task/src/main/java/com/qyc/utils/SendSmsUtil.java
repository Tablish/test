package com.qyc.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.qyc.model.data.Sys.Log;
import com.qyc.model.data.Sys.SmsRequest;
import com.qyc.model.enums.LogType;
import com.qyc.service.sys.DxService;
import com.qyc.service.sys.LogService;
import lombok.extern.log4j.Log4j;
import org.omg.CORBA.DATA_CONVERSION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author qianyongchao
 * @date 2019/5/27 15:27
 */
@Log4j
@Component
@PropertySource( value = "classpath:task-dev.properties" )
public class SendSmsUtil {
    @Autowired
    private LogService logService;

    @Autowired
    private DxService dxService;

    /**
     * 每周一/五 08:00 执行 异步执行（不影响主线程）
     */
    @Async( "taskExecutor" )
    @Scheduled( cron = "${good_morning}" )
    public void showInfo() throws Exception {
        log.info("发送短信逻辑");
        try {
            //初始化ascClient需要的几个参数
            final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
            final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
            //替换成你的AK
            final String accessKeyId = "LTAIu0Rat9wbssoG";//你的accessKeyId,参考本文档步骤2
            final String accessKeySecret = "83zNKM1lRuOnIoVse1swO9MsGpVQcI";//你的accessKeySecret，参考本文档步骤2
            //初始化ascClient,暂时不支持多region（请勿修改）
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                    accessKeySecret);
            try {
                DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            } catch (ClientException e) {
                e.printStackTrace();
            }
            IAcsClient acsClient = new DefaultAcsClient(profile);
            //组装请求对象
            SendSmsRequest request = new SendSmsRequest();
            //使用post提交
            request.setMethod(MethodType.POST);
            //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
            request.setPhoneNumbers("17621378328");
            //必填:短信签名-可在短信控制台中找到
            request.setSignName("子目");
            //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
            request.setTemplateCode("SMS_166485421");
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
            //request.setTemplateParam("{\"code\":\"123\"}");
            //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");
            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            request.setOutId("yourOutId");

            //请求失败这里会抛ClientException异常
            SendSmsResponse sendSmsResponse = null;

            //根据sendSmsResponse.getCode() 可以获取 发送情况
            try {
                sendSmsResponse = acsClient.getAcsResponse(request);
            } catch (ClientException e) {
                e.printStackTrace();

                //请求失败
                Log log = new Log();
                log.setOperator("钱永超");
                log.setType(LogType.Dx.getVal());
                log.setRemark("短信发送事变，ClientException异常");
                log.setOperator("钱永超");
                logService.insertLog(log);
            }

            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                //请求成功
                Log log = new Log();
                log.setOperator("钱永超");
                log.setType(LogType.Dx.getVal());
                log.setRemark("17621378328 SMS_166485421 发送成功");
                log.setOperator("钱永超");
                logService.insertLog(log);

                //发送成功后添加后表中
                SmsRequest sms = new SmsRequest();
                sms.setMobile("17621378328");
                sms.setSms_template_code("SMS_166485421");
                sms.setSms_free_sign_name("子目");
                sms.setSms_type("normal");
                sms.setMsg(sendSmsResponse.getMessage());
                sms.setCode(sendSmsResponse.getCode());
                sms.setSuccess(true);
                dxService.insertDx(sms);
            }
        } catch (Exception e) {
            log.info("异常");
            e.printStackTrace();
        }
    }
}