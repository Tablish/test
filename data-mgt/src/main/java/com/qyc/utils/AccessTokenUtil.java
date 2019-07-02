package com.qyc.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 后台获取access_token
 *
 * @author qianyongchao
 * @date 2019/5/23 17:14
 */

@Slf4j
public class AccessTokenUtil {
    public static String getWxAccessToken() throws Exception {
        //https://developers.weixin.qq.com/miniprogram/dev/api-backend/auth.getAccessToken.html

        /**
         * grant_type
         * appid
         * secret
         */
        //access_token :21_dctUy3mkK3Ag5w9gGO_9tj2tQsN6HT1xG8xfFS2zZoiu3F_QkHsvT1sVGhxYZS5sKWqcnkMbeiu1ZuY5gsQFu3uT2_9txsZ3fbU7jGNo7q8H3zRruC5xLqm1tRfJ-HKDl7hAHWKpDTXD1CXXPETdAHAWLA
        String strURL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx680106bf040743d2&secret=749b40a45ee9dac0e8a1f9fd4c108eb4";
        URL url = new URL(strURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("GET");
        httpConn.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
        String line;
        StringBuffer buffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
        httpConn.disconnect();

        System.out.println(buffer.toString());
        //return buffer.toString();
        return "";
    }
}
