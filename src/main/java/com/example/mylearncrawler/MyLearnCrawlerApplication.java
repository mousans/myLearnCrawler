package com.example.mylearncrawler;

import com.example.mylearncrawler.dto.PageRelationShipDTO;
import com.example.mylearncrawler.urlsearchandvisite.UrlSearch;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author musan
 */
@SpringBootApplication
public class MyLearnCrawlerApplication {

    public static void main(String[] args) {
        UrlSearch.initUrl(new PageRelationShipDTO("https://blog.csdn.net/weixin_33788244/article/details/93079933?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_title-0&spm=1001.2101.3001.4242",""));
        UrlSearch.searchUrl();
        SpringApplication.run(MyLearnCrawlerApplication.class, args);
    }

}
