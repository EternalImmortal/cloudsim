package test.org.yunji.cloudsimrd.load;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.yunji.cloudsimrd.Constants;
import org.yunji.cloudsimrd.LoadProperties;
import org.yunji.cloudsimrd.PropertiesUtil;
import org.yunji.cloudsimrd.load.Load;
import org.yunji.cloudsimrd.load.LoadGenerator;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * LoadGenerator Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>11月 14, 2019</pre>
 */
public class LoadGeneratorTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    //1.从load.properties文件中获取属性
    LoadProperties loadProperties = new LoadProperties();

    //2.初始化任务生成器
    LoadGenerator loadGenerator = new LoadGenerator();

    @Test
    public void Example() {
        //3.输入参数，将文件保存到指定路径
        loadGenerator.saveLoadConfig("loadTrace", "rateTrace", loadProperties.ramp_up, loadProperties.ramp_down, loadProperties.totalNumberOfRequest, loadProperties.maxConcurrent, loadProperties.CPUMax);

        //4.读取trace文件中的参数，生成Cloudlet的List

        List<Cloudlet> cloudlets =loadGenerator.generateCloudletsFromList(loadGenerator.readListFromFile("/Users/WeiJoseph/Documents/GitHub/cloudsim/modules/cloudsim/loadTrace,29", 29));
    }


}

