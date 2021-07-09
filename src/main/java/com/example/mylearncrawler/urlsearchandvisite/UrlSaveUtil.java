package com.example.mylearncrawler.urlsearchandvisite;

import com.example.mylearncrawler.dto.PageRelationShipDTO;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

/**
 * @author musan
 * @date 2021/07/08 22:43
 **/
public class UrlSaveUtil {
    private static HashSet<PageRelationShipDTO> visitedUrl = new HashSet<>();
    private static Queue<PageRelationShipDTO> unVisitUrl = new LinkedList<>();

    public static PageRelationShipDTO nextVisitUrl(){
        if(unVisitUrl.isEmpty()){
            return null;
        }
        PageRelationShipDTO nextUrl = unVisitUrl.peek();
        unVisitUrl.remove();
        return nextUrl;
    }

    public static void addUnVisitUrl(PageRelationShipDTO pageRelationShipDTO){
        unVisitUrl.add(pageRelationShipDTO);
    }
    public static boolean urlIsVisited(String url){
            List<PageRelationShipDTO> urls =  visitedUrl.stream().filter(pgDTO-> pgDTO.getUrl().equals(url)).collect(Collectors.toList());
            return !urls.isEmpty();
    }
    public static void addUrlToVisited(PageRelationShipDTO pageRelationShipDTO){
        visitedUrl.add(pageRelationShipDTO);
    }
    public static Integer getVisitedUrlNum(){
        return visitedUrl.size();
    }
}
