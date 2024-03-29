package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        System.out.println(this.getClass());
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.printf("[%s][%s] %s%n", uuid, requestURL, message);
    }

    @PostConstruct
    public void init() {
        this.uuid = UUID.randomUUID().toString();
        System.out.printf("[%s] request scope bean created : %s%n", uuid, this);
    }

    @PreDestroy
    public void close() {
        System.out.printf("[%s] request scope bean closing : %s%n", uuid, this);
    }
}