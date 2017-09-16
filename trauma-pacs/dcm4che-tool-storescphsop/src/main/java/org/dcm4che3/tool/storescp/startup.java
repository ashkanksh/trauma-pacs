package org.dcm4che3.tool.storescp;

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
    private StoreSCP storeSCP;


    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(startup.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.setHeadless(false);
        app.run(args);

    }

    @Override
    public void run(String... args) throws Exception {

//        if (args.length > 0 ) {
//            System.out.println(helloService.getMessage(args[0].toString()));
//        }else{
//            System.out.println(helloService.getMessage());
//        }
        storeSCP.runService();

    }
}
