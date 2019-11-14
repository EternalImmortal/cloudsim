package test.org.yunji.cloudsimrd;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.yunji.cloudsimrd.PropertiesUtil;

import java.io.File;

/**
 * PropertiesUtil Tester.
 *
 * @author <é­ä»æ°>
 * @version 1.0
 * @since <pre>11月 14, 2019</pre>
 */
public class PropertiesUtilTest {

    PropertiesUtil propertiesUtil = new PropertiesUtil();

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: writePropertiesDemo(String fileName)
     */
    @Test
    public void testWritePropertiesDemo() throws Exception {

    }


    /**
     * Method: writePropertiesFromMap(String fileName, Map<String, Object> propertiesMap)
     */
    @Test
    public void testWritePropertiesFromMap() throws Exception {
//TODO: Test goes here...
    }

    /**
     * Method: loadProperties(String fileName)
     */
    @Test
    public void testLoadProperties() throws Exception {
        System.out.println(propertiesUtil.loadProperties());
    }


}
