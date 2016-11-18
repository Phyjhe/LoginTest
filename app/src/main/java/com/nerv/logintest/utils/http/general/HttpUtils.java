package com.nerv.logintest.utils.http.general;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/10/20.
 */

public class HttpUtils {
    private ExecutorService executors;
    private int maxThreadCount=5;
    private int connectTimeOut=5000;
    private int readTimeOut=5000;
    private Handler handler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            ResponseCallback r= (ResponseCallback) msg.obj;
            switch (msg.what){
                case 1:
                    r.getCallBack().onResponse(r.getResult());
                    break;
                case 2:
                    r.getCallBack().onError();
            }
        }
    };
    public static HttpUtils instance;
    public HttpUtils() {
        executors = Executors.newFixedThreadPool(maxThreadCount);
    }
    public static HttpUtils getInstance(){
        if (instance==null){
            synchronized (HttpUtils.class){
                if (instance==null){
                    instance=new HttpUtils();
                }
            }
        }
        return instance;
    }
    public void execute(Request request,CallBack callBack){
        HttpRunnable runnable=new HttpRunnable(request,callBack);
        executors.execute(runnable);
    }

    public interface CallBack {
        void onResponse(String response);
        void onError();
    }
    class ResponseCallback{
        private CallBack callBack;
        private String result;
        private Object tag;

        public ResponseCallback(CallBack callBack, String result,  Object tag) {
            this.callBack = callBack;
            this.result = result;
            this.tag = tag;
        }

        public CallBack getCallBack() {
            return callBack;
        }

        public String getResult() {
            return result;
        }

        public Object getTag() {
            return tag;
        }
    }
    private class HttpRunnable implements Runnable{
        private Request request;
        private CallBack callBack;

        public HttpRunnable(Request request, CallBack callBack) {
            this.request = request;
            this.callBack = callBack;
        }

        @Override
        public void run() {
            try {
                URL url=new URL(request.getUrl());
                HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(5000);
                conn.setConnectTimeout(5000);
                conn.setRequestMethod(request.getMethod());
                Headers headers=request.getHeader();
                if (headers!=null){
                    List<String>data=headers.getNamesAndHeaders();
                    for (int i = 0; i < data.size(); i+=2) {
                        conn.addRequestProperty(data.get(i),data.get(i+1));
                    }
                }
                if (request.getBody()!=null){
                    conn.setDoOutput(true);
                    OutputStream ot=conn.getOutputStream();
                    ot.write(request.getBody().getContentByByte());
                }
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                StringBuffer sb=new StringBuffer();
                while((line=br.readLine())!=null){
                    sb.append(line);
                }
                String result= sb.toString();
                if(callBack == null) return;
                ResponseCallback responseCallback=new ResponseCallback(callBack,result,url);
                handler.sendMessage(handler.obtainMessage(1,responseCallback));
                return;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ResponseCallback responseCallback=new ResponseCallback(callBack,null,null);
            handler.sendMessage(handler.obtainMessage(2,responseCallback));
            return;
        }
    }
}
