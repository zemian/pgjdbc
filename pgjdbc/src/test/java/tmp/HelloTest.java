package tmp;

import org.junit.Test;

import static org.junit.Assert.assertThat;

/**
 * Created by xbbj5x6 on 8/4/2017.
 */
public class HelloTest {
    @Test
    public void test() {
        System.out.println(Integer.toString(-7));
        System.out.println((long)3.14f);
        System.out.println((long)3.55f);
        System.out.println((long)3.00001f);
        System.out.println((long)3.9f);

        System.out.println((long)2.5f * 1000);
        System.out.println((long)(2.5f * 1000));
    }
}
