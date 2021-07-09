package com.example.mylearncrawler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author musan
 * @date 2021/07/09 11:19
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRelationShipDTO {
    String url;
    String parentUrl;

}
