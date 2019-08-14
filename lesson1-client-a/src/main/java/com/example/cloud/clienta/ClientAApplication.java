package com.example.cloud.clienta;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
@EnableRedisHttpSession
@EnableEurekaClient
@RestController
@Slf4j
public class ClientAApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientAApplication.class, args);
    }

    ConcurrentHashMap<String, AtomicInteger> map = new ConcurrentHashMap<>();


    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    public String home(@RequestParam String name) {
        return "hi "+name+",i am from port:" +port;
    }



    @PostMapping("/exception")
    public String testException( ) {
        TestO o = new TestO();
        o.setId("1");
        AtomicInteger num = map.computeIfAbsent(o.getId(), s -> new AtomicInteger());
        int i = num.incrementAndGet();
        log.warn("Retry count: "+ ToStringBuilder.reflectionToString(o));
        return String.valueOf(port);
    }


    @GetMapping("/login")
    public String login(@RequestParam("username")String username, @RequestParam(name ="pwd")String pwd, HttpServletRequest request, HttpServletResponse response  ) {
        String msg = "登陆失败";
        if ( !username.equals("123") || !pwd.equals("123") ){
            return msg;
        }
        Cookie cookie = new Cookie("token", UUID.randomUUID().toString());
        cookie.setMaxAge(3600);
        cookie.setPath("/");
        response.addCookie(cookie);
        msg = "登陆成功";
        return msg;
    }

    @GetMapping("/set")
    public Map<String,Object> test( HttpServletRequest request){
        System.out.println("端口端口端口端口端口端口端口端口"+port);
        System.out.println(request.getSession().getId());
        HttpSession session = request.getSession();
        session.setAttribute("token",port);
        System.out.println(port);
        Map<String,Object> map = new HashMap<>();
        System.out.println("eq".equals("111"));
        map.put("sessionId",request.getSession().getId());
        return map;
    }

    @GetMapping("/get")
    public Map<String,Object> two(HttpServletRequest request){
        System.out.println("端口端口端口端口端口端口端口端口"+port);
        Map<String,Object> map = new HashMap<>();
        HttpSession session = request.getSession();
        Object token = session.getAttribute("token");
        System.out.println(request.getSession().getId());
        System.out.println(token);
        map.put("sessionId",request.getSession().getId());
        map.put("message",request.getSession().getAttribute("message"));
        return map;
    }


    }
