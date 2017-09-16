package org.dcm4che3.tool.storescu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Ashkan on 9/11/2017.
 */
@SpringBootApplication
public class startup implements CommandLineRunner {
    @Autowired
    private sender sender;


    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(startup.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.setHeadless(false);
        app.run(args);

    }

    @Override
    public void run(String... args) throws Exception {

        sender.runService();

    }
}
