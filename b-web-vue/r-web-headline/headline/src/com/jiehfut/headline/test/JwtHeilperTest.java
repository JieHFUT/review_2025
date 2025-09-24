package com.jiehfut.headline.test;

import com.jiehfut.headline.util.JwtHelper;
import org.junit.Test;

/**
 * ClassName: JwtHeilperTest
 * Package: com.jiehfut.headline.test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/12/22 21:34
 * @Version 1.0
 */
public class JwtHeilperTest {


    @Test
    public void testAllMethod() throws InterruptedException {
        String token = JwtHelper.createToken(1L);
        System.out.println("token = " + token);

        Long userId = JwtHelper.getUserId(token);
        System.out.println("userId = " + userId);

        System.out.println("token isExpiration = " + JwtHelper.isExpiration(token));
        Thread.sleep(5000);
        System.out.println("token isExpiration = " + JwtHelper.isExpiration(token));

        // token = eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAA_6tWKi5NUrJSiox099ANDXYNUtJRSq0oULIyNDc2sTA3NbCw1FEqLU4t8kwBitUCAFzP_UIvAAAA.-aKLHDu22TWoi8ysi9va0_LLMDPicg-QO6m-JGARYeJw8uIdf79cjK4Z5KiaTUwZYzkCwS8vCNnznnQDifRqow
        // userId = 1
        // token isExpiration = false
        // token isExpiration = true
    }


}
