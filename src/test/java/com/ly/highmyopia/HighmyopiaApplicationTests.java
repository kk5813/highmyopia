package com.ly.highmyopia;

import com.ly.highmyopia.entity.User;
import com.ly.highmyopia.mapper.UserMapper;
import com.ly.highmyopia.util.ConnectOCT;
import com.ly.highmyopia.util.ConnectPacs;
import com.ly.highmyopia.util.JwtUtils;
import com.ly.highmyopia.util.lianjie;
import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.PrintStream;
import java.sql.SQLException;
import java.time.chrono.JapaneseDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class HighmyopiaApplicationTests {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Test
    public void list() {
        List<User> userList = userMapper.list();
        userList.forEach(user -> {
            System.out.println(user);
        });
    }

    private long time = 1000 * 60 * 60 * 24;
    private String signature = "admin";

    @Test
    //JWT加密
    public void jwt() {
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwttoken = jwtBuilder
                //header
                .setHeaderParam("typ", "jwt")
                .setHeaderParam("alg", "HS256")
                //payload
                .claim("username", "tom")
                .claim("role", "admin")
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(SignatureAlgorithm.HS256, signature)
                .compact();      //用点拼接
        System.out.println(jwttoken);
    }
    @Test
    //jwt解密
    public void Parse(){
        String token = "eyJ0eXAiOiJqd3QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InRvbSIsInJvbGUiOiJhZG1pbiIsInN1YiI6ImFkbWluLXRlc3QiLCJleHAiOjE2OTkxNjcxNTQsImp0aSI6Ijg2YzYyYjQ4LTQ4MTUtNDFlZC05NDI2LWJlYWUxYTdiNzU1YSJ9.UxSvnZjmKNOIakO8C2ETCyqmLKedV9_rI6LNi_3HAvk";
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(signature).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        System.out.println(claims.get("username"));
        System.out.println(claims.get("role"));
        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(claims.getExpiration());
    }

    //JwtUtils测试
    @Autowired
    private JwtUtils jwtUtils;
    @Test
    public void jwtUtils(){
        String jwtToken = jwtUtils.generateToken(1232153123L);
        Claims claims = jwtUtils.getClaimByToken(jwtToken);
        System.out.println(claims.getExpiration());
    }

    @Autowired
    private ConnectPacs connectPacs;
    private ConnectOCT connectOCT;
    private lianjie lianjie;
    @Test
    public  void LianJieTest() throws SQLException {
        System.out.println("开始测试能否连接");
        //connectOCT.connnectOct();
        lianjie.lianjiePatient();
    }

}
