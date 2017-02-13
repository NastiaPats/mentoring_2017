import org.testng.Assert;
import org.testng.annotations.Test;

public class HelloWorld {

    @Test
    public void test1() {
        System.out.println("Hello, world");
        Assert.assertTrue(false, "Error! What are you doing?");
    }

    @Test
    public void test2() throws Exception {
        System.out.println("Hello, world");
    }
}
