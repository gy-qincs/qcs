package test;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.spring.MemcachedClientFactoryBean;

public class MemcachedTest {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
            "META-INF/applicationContext-memcached.xml");
        MemcachedClient client = (MemcachedClient) applicationContext.getBean("memcachedClient");

        client.set("abc", 60000, "qwertyuiop");
        System.out.println(client.get("abc"));
    }

}
