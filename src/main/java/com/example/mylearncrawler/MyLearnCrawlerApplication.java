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
        UrlSearch.initUrl(new PageRelationShipDTO("https://space.bilibili.com/3/fans/fans",""));
        UrlSearch.searchUrl();
        SpringApplication.run(MyLearnCrawlerApplication.class, args);
    }

}
