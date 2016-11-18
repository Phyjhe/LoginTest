package com.nerv.logintest.utils;

/**
 * Created by LG on 2016/10/27.
 * Tips:
 */

public interface Apis {
    /**主页 口碑对应的接口**/
    String URL_REPUTATION = "http://api.eqingdan.com/v1/rankings/front";
    /**文章详情相关接口**/
    String URL_ALLTOPIC ="http://api.eqingdan.com/v1/rankings?page={0}&per=10";
    //文章标题区域对应接口
    String URL_ARTICLE_TITLE = "http://api.eqingdan.com/v1/articles/{0}";
    //文章详情对应的webview地址
    String URL_ARTICLE_DETAIL = "http://www.eqingdan.com/app/articles/{0}";
    //评论区
    String URL_ARTICLE_COMMENTS = "http://api.eqingdan.com/v1/comments/hot?target_type=article&target_id={0}&page=1&per_page=4";
    //相关文章接口
    String URL_RELATED_ARTICLES = "http://api.eqingdan.com/v1/articles/{0}/related-articles";

    /**专题接口**/
    String URL_SEMINAR ="http://api.eqingdan.com/v1/batching";

    String URL_REVIEW = "http://api.eqingdan.com/v1/rankings/{0}/things?keyword={1}&sort=review-count-desc&page={2}&per=10";
    String URL_RATING = "http://api.eqingdan.com/v1/rankings/{0}/things?keyword={1}&sort=rating-score-desc&page={2}&per=10";
    String URL_BRAND = "http://api.eqingdan.com/v1/rankings/{0}/things?keyword={1}&sort=brand-name-asc&page={2}&per=10";

    String URL_THINGSARTICLE = "http://api.eqingdan.com/v1/reviews?target_type=thing&target_id={0}&has_body=1&sort=hottest&page=1&per=3&include=thing";
    String URL_THINGSCOMMENTS = "http://api.eqingdan.com/v1/things/{0}/articles  ";
    String URL_CATEGORYTOP ="http://api.eqingdan.com/v1/categories?depth=3";
}
