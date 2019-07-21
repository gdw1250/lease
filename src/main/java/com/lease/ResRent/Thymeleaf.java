package com.lease.ResRent;

import java.nio.charset.Charset;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.MimeType;

//@ConfigurationProperties("spring.thyleaf")
public class Thymeleaf {
	 private static final Charset DEFAULT_ENCODING = Charset.forName("UTF-8");
	    private static final MimeType DEFAULT_CONTENT_TYPE = MimeType.valueOf("text/html");
	    public static final String DEFAULT_PREFIX = "classpath:/templates/";
	    public static final String DEFAULT_SUFFIX = ".html";
	    private boolean checkTemplate = true;
	    private boolean checkTemplateLocation = true;
	    private String prefix = "classpath:/templates/";
	    private String suffix = ".html";
	    private String mode = "HTML5";
}
