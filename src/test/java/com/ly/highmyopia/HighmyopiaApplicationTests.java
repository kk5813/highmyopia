package com.ly.highmyopia;

import cn.hutool.crypto.SecureUtil;
import com.ly.highmyopia.common.lang.Result;
import com.ly.highmyopia.entity.User;
import com.ly.highmyopia.mapper.UserMapper;
import com.ly.highmyopia.service.UserService;
import com.ly.highmyopia.util.*;
import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

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
    public void Parse() {
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
    public void jwtUtils() {
        String jwtToken = jwtUtils.generateToken(1232153123L);
        Claims claims = jwtUtils.getClaimByToken(jwtToken);
        System.out.println(claims.getExpiration());
    }

    @Autowired
    private ConnectPacs connectPacs;
    private ConnectOCT connectOCT;
    private lianjie lianjie;

    @Test
    public void LianJieTest() throws SQLException {
        System.out.println("开始测试能否连接");
        //connectOCT.connnectOct();
        lianjie.lianjiePatient();
    }


    //测试jedis
    @Test
    public void JedisTest() {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.auth("2287996531");
        jedis.set("key1", "jedis test");
        String string = jedis.get("key1");
        System.out.println(string);
        jedis.close();
    }


    @Autowired
    UserService userService;

    @Test
    public void testSaltMD5() {
        String salt = "bEZ5mLq6SxkSkZHBnZxRfd8dyXGq7vfi";
        String userPassword = "2287996531";
        String loginDto = SecureUtil.md5(userPassword);
        for (int i = 0; i < 10; i++)
            System.out.println(SecureUtil.md5(salt + loginDto));
    }

}
