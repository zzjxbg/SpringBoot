package org.example.boot;

import org.example.boot.bean.Pet;
import org.example.boot.bean.User;
import org.example.boot.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @SpringBootApplication 声明该类为主配置类
 */
//@SpringBootApplication
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan("org.example.boot")
public class MainApplication {

    public static void main(String[] args) {
        // 1.返回IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class,args);

        // 2.查看容器里面的组件
        String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }

        // 3.从容器中获取组件
        Pet tom01 = run.getBean("tom",Pet.class);
        Pet tom02 = run.getBean("tom",Pet.class);
        System.out.println("组件:"+(tom01 == tom02));

        //4.org.example.boot.config.MyConfig$$EnhancerBySpringCGLIB$$1cdc2e2c@1e7f2e0f
        MyConfig bean = run.getBean(MyConfig.class);
        System.out.println(bean);

        //5.如果@Configuration(proxyBeanMethods = true) 代理对象调用方法
        //springboot总会检查这个组件是否在容器中(从容器中找组件)
        //即保持组件单实例
        User user = bean.user01();
        User user1 = bean.user01();
        System.out.println(user == user1);

        User user01 = run.getBean("user01",User.class);
        Pet tom = run.getBean("tom",Pet.class);
        System.out.println("用户的宠物:"+(user01.getPet()==tom));
    }
}
