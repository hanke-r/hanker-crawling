package com.hanker.hankercrawling.service;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class MainService {

    // 크롤링할 URL 주소
    private static final String CRAWLING_URL = "";
    // 다운로드 받을 Dir 주소
    private static final String DIR = "D:\\1.download";
    // 파일명 갱신
    private static int FILE_NUM = 0;


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


    public void imgDownload() {

        try{
            Connection conn = Jsoup.connect(CRAWLING_URL);

            Document html = conn.get();

            Elements imageUrlElements = html.getElementsByTag("img");

            for(Element image : imageUrlElements) {
                String imgURL = image.getElementsByAttribute("src").attr("src");

                if (!"".equals(imgURL) && (imgURL.startsWith("http://") || imgURL.startsWith("https://"))) {
                    System.out.println("The address of the picture downloaded: " + imgURL);
                    downImages(DIR, imgURL);

                }
            }

        } catch(Exception e){
            e.printStackTrace();
        }


    }

    private static void downImages(String dir, String imgURL) {
        String[] fileName = imgURL.substring(imgURL.lastIndexOf("/")).split("/");

        File files = new File(dir);

        if(!files.exists()){
            files.mkdir();
        }

        try{
            URL url = new URL(imgURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();

            System.out.println("fileName = " + fileName);

            File file = new File(dir + "/" + FILE_NUM + "_"+ fileName[1]);
            FILE_NUM++;
            FileOutputStream out = new FileOutputStream(file);
            int i = 0;

            while((i=is.read()) != -1){
                out.write(i);
            }

            is.close();
            out.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}
