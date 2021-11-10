package com.hanker.hankercrawling.controller;

import com.hanker.hankercrawling.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("/")
    public String main(){

        return "index";
    }

    @GetMapping("/crawling")
    public String ajaxCrawling(Model model){
        String image = mainService.webCrawling();

        model.addAttribute("images", image.toString());

        return "jsonView";
    }

    @PostMapping("/getCrawlingImageDownload")
    public String getCrawlingImageDownload(){

        mainService.imgDownload();

        return "jsonView";
    }
}
