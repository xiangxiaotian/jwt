package com.xxt.token;

import com.xxt.token.util.TokenUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenApplicationTests {

	@Test
	public void contextLoads() {
	}
	@Test
	public void text(){
		System.out.println(TokenUtil.getToken(1l));
	}

}
