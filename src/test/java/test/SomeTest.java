package test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author justZero
 * @since 2019/2/14
 */
public class SomeTest {

    @Test
    public void testFuncParam() {
        Integer res = 233;
        assertEquals(233, res.intValue());
        func(res);
        assertEquals(233, res.intValue());
    }
    private void func(Integer num) {
        num = 10;
    }

}
