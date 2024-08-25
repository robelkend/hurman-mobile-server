package com.rsoft.hurmanmobileapp;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;

@EnableFeignClients({"com.rsoft.hurmanmobileapp"})
//@ComponentScan(basePackages = {"com.rsoft.hurmanmobileapp.component"})
//@ComponentScan("com.rsoft.hurmanmobileapp.component")
@SpringBootApplication
@EnableConfigurationProperties
@EnableDiscoveryClient
@EnableAsync
@EnableCaching
@EnableScheduling
public class HurmanMobileAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(HurmanMobileAppApplication.class, args);
    }

    @PostConstruct
    public void init() {
        /*
         * Permet de resoudre le probleme de date par rapport a la base de donnees, par exemple le 29 juillet qui s'affiche 29 juillet
         * dans la base s'affiche 28 juillet ce qui pose une probleme majeur lors des recherche et finalisation des operation.
         * L'instruction suivante resoud le probleme
         */
//		TimeZone.setDefault(TimeZone.getTimeZone(config.getGlobalUtcTimezone() != null ? config.getGlobalUtcTimezone() : "US/Eastern"));   // It will set UTC timezone
        TimeZone.setDefault(TimeZone.getTimeZone("US/Eastern"));   // It will set UTC time
//        try {
//            System.out.println("==========================================================");
//            GetMessageTypeResponse response = new GetMessageTypeResponse();
//            List<String> messagesType = new ArrayList<>();
//            messagesType.add("CONGE ANNUEL");
//            messagesType.add("CONGE MALADIE");
//            messagesType.add("RETARD");
//            messagesType.add("ABSENCE");
//            messagesType.add("AUTRE");
//            response.setMessageTypeList(messagesType);
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            String r1 = objectMapper.writeValueAsString(response);
//            System.out.println(r1);
//            System.out.println("==========================================================");
//
//        } catch (Exception e) {
//            e.printStackTrace(System.out);
//        }
    }
}
