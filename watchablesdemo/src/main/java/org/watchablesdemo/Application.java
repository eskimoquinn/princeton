package org.watchablesdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by tgreen on 7/27/16.
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"org.watchablesdemo", "org.cirrostratus.sequoia"})
@ImportResource({
        "classpath:/META-INF/spring/watchable-beans.xml",
        "classpath:/META-INF/spring/dipswitch-beans.xml",
        "classpath*:META-INF/spring/persistentvariable-beans.xml",
    })
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }



    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}
