package com.hanker.hankercrawling.service;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MainService {


    public String webCrawling() {

        try{
            // 크롤링할 URL 주소
            String URL = "URL주소";

            Connection conn = Jsoup.connect(URL);

            Document html = conn.get();
            // 수집할 Class 명
            Elements imageUrlElements = html.getElementsByClass("thumbnail");



            return imageUrlElements.toString();
        } catch(IOException ie){
            ie.printStackTrace();

            return "error";
        }
    }
}
