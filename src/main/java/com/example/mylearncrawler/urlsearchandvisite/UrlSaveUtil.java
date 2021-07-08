package com.example.mylearncrawler.urlsearchandvisite;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author musan
 * @date 2021/07/08 22:43
 **/
public class UrlSaveUtil {
    private static HashSet<String> visitedUrl = new HashSet<>();
    private static Queue<String> unVisitUrl = new LinkedList<>();

    public static String nextVisitUrl(){
        if(unVisitUrl.isEmpty()){
            return null;
        }
        String nextUrl = unVisitUrl.peek();
        unVisitUrl.remove();
        return nextUrl;
    }

    public static void addUnVisitUrl(String url){
        unVisitUrl.add(url);
    }
    public static boolean urlIsVisited(String url){
            if(visitedUrl.contains(url)){
                return true;
            }
            return false;
    }
    public static void addUrlToVisited(String url){
        visitedUrl.add(url);
    }
    public static Integer getVisitedUrlNum(){
        return visitedUrl.size();
    }
}
