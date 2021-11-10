package com.hanker.hankercrawling.setting;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@Data
@PropertySource("classpath:path.properties")
public class PathVO {

    @Value("${CRAWLING_URL}")
    private String CRAWLING_URL;

    @Value("${CRAWLING_IMG_DOWNLOAD_DIR}")
    private String CRAWLING_IMG_DOWNLOAD_DIR;
}
