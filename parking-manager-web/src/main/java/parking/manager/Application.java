package parking.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author huang
 * @date 2020/1/7 17:44
 * @Disc
 **/
@SpringBootApplication
@MapperScan(basePackages = "parking.manager.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

}
