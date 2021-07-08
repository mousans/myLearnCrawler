package com.example.mylearncrawler.urlsearchandvisite;

import com.example.mylearncrawler.staticDate.HttpStatus;
import org.springframework.util.StringUtils;
import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author musan
 * @date 2021/07/08 22:58
 **/
public class UrlSearch {

    /**
     * 查询的网页个数
     */
    private static Integer urlNum = new Integer(10000);

    /**
     * 网页中查询url的正则表达式
     */
    private static Pattern pattern = Pattern.compile("<a.*?href=[\"']?((https?://)?/?[^\"']+)[\"']?.*?>(.+)</a>");
    /**
     * 对保存网站的队列和集合做初始化
     * @param url 初始的url
     */
    public static void initUrl(String url){
        UrlSaveUtil.addUnVisitUrl(url);
    }
    public static void searchUrl(){
        String curUrl = "init";
        while(!StringUtils.isEmpty(curUrl)&&UrlSaveUtil.getVisitedUrlNum()<urlNum){
            curUrl = UrlSaveUtil.nextVisitUrl();
            System.out.println("当前访问： "+curUrl);
            if(UrlSaveUtil.urlIsVisited(curUrl)){
                continue;
            }
            getUrlContent(curUrl);
            UrlSaveUtil.addUrlToVisited(curUrl);
        }
    }

    /**
     * 根据url和对应服务器建立连接
     * @param curUrl 当前爬取网页的url
     */
    private static void getUrlContent(String curUrl){
        if(StringUtils.isEmpty(curUrl)){
            return ;
        }
        try {
            URL url = new URL(curUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(2000);
            connection.setReadTimeout(2000);
            operateConnection(connection);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据当前网页的连接，得到下级URL
     * @param connection 当前爬取网页对应的连接
     */
    private static void operateConnection(HttpURLConnection connection) throws IOException {
        Integer responseCode = connection.getResponseCode();
        if(responseCode.equals(HttpStatus.OK.getCode())){
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            String line = "";
            while((line = reader.readLine()) != null){
                findUrl(line);
            }
        }else if(responseCode.equals(HttpStatus.Found.getCode()) || responseCode.equals(HttpStatus.MovedPermanently.getCode()) || responseCode.equals(HttpStatus.SeeOther.getCode()) || responseCode.equals(HttpStatus.TemporaryRedirect.getCode())){
            String newLink = connection.getHeaderField("location");
            UrlSaveUtil.addUnVisitUrl(newLink);
        }else{
            System.err.println("url: "+connection.getURL());
            System.err.println("status code : " + responseCode);
        }
    }


    private static void findUrl(String line){
        Matcher matcher = pattern.matcher(line);
        if(matcher.find()){
            String newLink = matcher.group(1).trim();
            UrlSaveUtil.addUnVisitUrl(newLink);
        }

    }
}
