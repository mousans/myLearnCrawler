package com.example.mylearncrawler.urlsearchandvisite;

import com.example.mylearncrawler.dto.PageRelationShipDTO;
import org.apache.http.Header;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author musan
 * @date 2021/07/08 22:58
 **/
public class UrlSearch {

    /**
     * 查询的网页个数
     */
    private static Integer urlNum = new Integer(10000);

    private static CloseableHttpClient httpClient = HttpClients.createDefault();
    /**
     * 对保存网站的队列和集合做初始化
     * @param pageRelationShipDTO 初始的url
     */
    public static void initUrl(PageRelationShipDTO pageRelationShipDTO){
        UrlSaveUtil.addUnVisitUrl(pageRelationShipDTO);
    }
    public static void searchUrl(){
        PageRelationShipDTO curUrl = new PageRelationShipDTO();
        while(!StringUtils.isEmpty(curUrl)&&UrlSaveUtil.getVisitedUrlNum()<urlNum){
            curUrl = UrlSaveUtil.nextVisitUrl();
            System.out.println("当前访问： "+curUrl.getUrl());
            if(UrlSaveUtil.urlIsVisited(curUrl.getUrl())){
                continue;
            }
            getUrlContent(curUrl);
            UrlSaveUtil.addUrlToVisited(curUrl);
        }
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据url和对应服务器建立连接
     * @param pageRelationShipDTO 当前爬取网页的url
     */
    private static void getUrlContent(PageRelationShipDTO pageRelationShipDTO){
        if(StringUtils.isEmpty(pageRelationShipDTO.getUrl())){
            return ;
        }
        HttpGet httpGet = new HttpGet(pageRelationShipDTO.getUrl());
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if(response == null){

            }else{
                int statusCode = response.getStatusLine().getStatusCode();
                if(statusCode == HttpStatus.SC_OK){
                    String html = EntityUtils.toString(response.getEntity());
                    analysisPage(html,pageRelationShipDTO);
                }else if(statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY
                || statusCode == HttpStatus.SC_SEE_OTHER || statusCode == HttpStatus.SC_TEMPORARY_REDIRECT){
                    List<Header> headers = Arrays.stream(response.getAllHeaders()).filter(header->header.getName().equals("location")).collect(Collectors.toList());
                    String newLink = headers.get(0).getValue();
                    UrlSaveUtil.addUnVisitUrl(new PageRelationShipDTO(newLink,pageRelationShipDTO.getUrl()));
                }else{
                    System.err.println("状态码："+statusCode+ " msg: "+ response.getStatusLine().getReasonPhrase());
                }
            }
        } catch (IOException e) {
            System.err.println("当前错误url:"+pageRelationShipDTO.getUrl() + " 其父节点Url: "+ pageRelationShipDTO.getParentUrl());
        }finally {
            try {
                if(response != null){
                    response.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    private static void analysisPage(String html,PageRelationShipDTO pageRelationShipDTO){
        Document document = Jsoup.parse(html,pageRelationShipDTO.getUrl());
        Element body = document.body();
        Elements links = body.select("a[href]");
        /**
         * 将网页钟所有<a><a/>标签的链接取出存入
         */
        for(Element link:links){
            String  newUrl = link.attr("href");
            UrlSaveUtil.addUnVisitUrl(new PageRelationShipDTO(newUrl,pageRelationShipDTO.getUrl()));
        }
    }

}
