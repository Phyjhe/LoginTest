package com.nerv.logintest.utils;

/**
 * Created by Administrator on 2016/10/27.
 */

public class UrlHandler {
    /**
     * http://api.eqingdan.com/v1/articles/{0}?page={1}
     * 28 1
     * http://api.eqingdan.com/v1/articles/28?page={1}
     * http://api.eqingdan.com/v1/articles/28?page=1
     * @param url
     * @param params
     * @return
     */
    public  static final String handlUrl(String url,Object... params){
        for (int i = 0; i < params.length; i++) {
            url = url.replace("{"+i+"}", params[i]+"");
        }
        return url;
    }
//    public  static final String handleUrlInRanking(String url,int i,String type){
//            url = url.replace("{0}", i+"").replace("{s}",type);
//        return url;
//    }
}
