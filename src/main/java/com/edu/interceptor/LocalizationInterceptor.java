package com.edu.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class LocalizationInterceptor {
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("language");
        return lci;
    }
}
