package org.example.boot.config;

import ch.qos.logback.core.db.DBHelper;
import org.example.boot.bean.Car;
import org.example.boot.bean.Pet;
import org.example.boot.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

/**
 * 1.配置类里使用@Bean标注在方法上给容器注册组件,默认也是单实例的
 * 2.配置类本身也是组件
 * 3.proxyBeanMethods: 代理bean的方法
 *      Full(proxyBeanMethods = true)   全模式
 *      Lite(proxyBeanMethods = false)  轻量级模式
 *      组件依赖
 * 配置类组件之间无依赖关系用Lite模式加速容器启动过程,减少判断
 * 配置类组件之间有依赖关系,方法会被调用得到之前单实例组件,用Full模式
 * 4.@Import({User.class,DBHelper.class})
 *   给容器中自动创建出这两个类型的组件,默认组件的名字就是全类名(Source Root)
 *
 * 5.@ImportResource("classpath:beans.xml")导入spring的配置文件,使其生效
 */
@Import({User.class, DBHelper.class})
@Configuration(proxyBeanMethods = true) //告诉springboot这是一个配置类==配置文件
//@ConditionalOnBean(name = "tom")
@ConditionalOnMissingBean(name = "tom")
@ImportResource("classpath:beans.xml")
@EnableConfigurationProperties(Car.class)
//1.开启Car的配置绑定功能
//2.把这个Car组件自动注册到容器中
public class MyConfig {

    /**
     * 外部无论对配置类中的这个组件注册方法调用多少次获取的都是之前注册容器中的单实例对象
     * @return
     */
    //给容器中添加组件,以方法名作为组件的id.返回类型就是组件类型
    // 返回的值,就是组件在容器中的实例

    @Bean
    public User user01() {
        User zhangsan = new User("zhangsan",18);
        //User组件依赖了Pet组件
        zhangsan.setPet(tomcatPet());
        return zhangsan;
//        return new User("zhangsan", 18);
    }

    @Bean("tom22")
    public Pet tomcatPet() {
        return new Pet("tomcat");
    }
}
