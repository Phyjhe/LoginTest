package com.nerv.logintest.utils.http.forqingdan;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.nerv.logintest.utils.UnicodeParser;
import com.nerv.logintest.utils.http.general.Body;
import com.nerv.logintest.utils.http.general.HttpUtils;
import com.nerv.logintest.utils.http.general.JsonBody;
import com.nerv.logintest.utils.http.general.Request;


/**
 * Created by Administrator on 2016/10/20.
 */

public class HttpClient {
    private static String authorization;
    private static long validTime;
    public static void execute(final Request.Builder requset, final HttpUtils.CallBack callBack){
        if (!authorizationChecker()){
            Log.d("HttpClient", "token为空，去申请token咯");
            String json = "{\"client_id\":\"herEv4O9tg4PuviM\",\"client_secret\":\"v20ulmkZP5ykQMn9uUwNbyEiuTkzFfPn\",\"grant_type\":\"client_credentials\"}";
            Body body=new JsonBody(Body.jsonContentType,json);
            final Request requestForToken=new Request.Builder()
                    .setURL("http://api.eqingdan.com/v1/oauth2/access-token")
                    .post(body)
                    .build();
            HttpUtils.getInstance().execute(requestForToken, new HttpUtils.CallBack() {
                @Override
                public void onResponse(String response) {
                    Log.d("HttpClient", UnicodeParser.decodeUnicode(response));
                    AuthoriaztionObj authoriaztionObj= JSON.parseObject(response,AuthoriaztionObj.class);
                    AuthoriaztionObj.DataBean data = authoriaztionObj.getData();
                    authorization=data.getToken_type()+" "+data.getAccess_token();
                    validTime=System.currentTimeMillis()+data.getExpires_in()*1000;
                    requset.addHeader("authorization",authorization);
                    HttpUtils.getInstance().execute(requset.build(),callBack);

                }

                @Override
                public void onError() {

                }
            });
        }else{
            requset.addHeader("authorization",authorization);
            HttpUtils.getInstance().execute(requset.build(),callBack);
        }
    }

    private static boolean authorizationChecker() {
        if (TextUtils.isEmpty(authorization)||isTokenInvalid()){
            return false;
        }
        return true;
    }

    private static boolean isTokenInvalid() {
        return System.currentTimeMillis()>validTime;
    }


}
