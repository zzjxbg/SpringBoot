package org.example.boot;

import ch.qos.logback.core.db.DBHelper;
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
/**
 * EnableAutoConfiguration中两个关键注解:
 * 1.@AutoConfigurationPackage:添加该注解的类所在的package作为自动配置package进行管理
 * 利用Registrar給容器中导入一系列组件
 * 将指定的一个包下的所有组件导入(MainApplication所在的包下)
 * 2.@Import({AutoConfigurationImportSelector.class}):
 * 使用Import自动导入所有符合自动配置条件的Bean定义并加载到IOC容器
 * a.利用getAutoConfigurationEntry(AnnotationMetadata annotationMetadata)给容器中批量导入组件
 * b.List<String> configurations = this.getCandidateConfigurations(annotationMetadata, attributes)
 * 获取到所有需要导入到容器中的配置类(127个)
 * c.利用工厂加载Map<String, List<String>> loadSpringFactories(@Nullable ClassLoader classLoader)
 * 得到所有的组件
 * d.从META-INF/spring.factories位置来加载一个文件:默认扫描当前系统里所有
 * META-INF/spring.factories位置的文件
 * spring-boot-autoconfigure-2.3.5.RELEASE.jar包里也有META-INF/spring.factories
 * e.文件里面写死了springboot一启动就要给容器中加载的所有配置类(21行-148行)
 * f.127个场景的所有自动配置启动时默认全部加载,按照条件装配(@Conditional)规则,最终实际按需配置
 */
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
//        Pet tom01 = run.getBean("tom",Pet.class);
//        Pet tom02 = run.getBean("tom",Pet.class);
//        System.out.println("组件:"+(tom01 == tom02));
//
//        //4.org.example.boot.config.MyConfig$$EnhancerBySpringCGLIB$$1cdc2e2c@1e7f2e0f
//        MyConfig bean = run.getBean(MyConfig.class);
//        System.out.println(bean);
//
//        //如果@Configuration(proxyBeanMethods = true) 代理对象调用方法
//        //springboot总会检查这个组件是否在容器中(从容器中找组件)
//        //即保持组件单实例
//        User user = bean.user01();
//        User user1 = bean.user01();
//        System.out.println(user == user1);
//
//        User user01 = run.getBean("user01",User.class);
//        Pet tom = run.getBean("tom",Pet.class);
//        System.out.println("用户的宠物:"+(user01.getPet()==tom));
//
//        //5.获取组件
//        String[] beanNamesForType = run.getBeanNamesForType(User.class);
//        System.out.println("=======");
//        for(String s : beanNamesForType) {
//            System.out.println(s);
//        }
//
//        DBHelper bean1 = run.getBean(DBHelper.class);
//        System.out.println(bean1);

        boolean tom = run.containsBean("tom");
        System.out.println("容器中Tom组件:" + tom);

        boolean user01 = run.containsBean("user01");
        System.out.println("容器中user01组件:" + user01);

        boolean tom22 = run.containsBean("tom22");
        System.out.println("容器中tom22组件:" + tom22);

        boolean haha = run.containsBean("haha");
        boolean hehe = run.containsBean("hehe");
        System.out.println("haha: "+haha);
        System.out.println("hehe: "+hehe);
    }
}
