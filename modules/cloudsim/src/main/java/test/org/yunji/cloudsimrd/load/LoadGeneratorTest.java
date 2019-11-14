package test.org.yunji.cloudsimrd.load;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.yunji.cloudsimrd.Constants;
import org.yunji.cloudsimrd.PropertiesUtil;
import org.yunji.cloudsimrd.load.Load;

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


    /**
     * 负载生成器，仿真负载。
     */

    private int rating = 1;

    private String defaultUrl = "www.baidu.com";

    private int brokeId = 1;
    private double ramp_up = 10000;
    private double ramp_down = 10000;
    private int totalNumberOfRequest = 20000;
    private int maxConcurrent = 1000;
    private int vmId = 0;

    private static final int INTERVAL = 100;

    // /**
    //  * 从文件中读取任务
    //  *
    //  * @param fileName
    //  * @return
    //  */
    // public List<Load> generateLoadsFromFile(String fileName) {
    //     List<Load> loads = new ArrayList<>();
    //     try {
    //         WorkloadFileReader workloadFileReader = new WorkloadFileReader(fileName, this.rating);
    //         ArrayList<Cloudlet> cloudlets = workloadFileReader.generateWorkload();
    //
    //
    //         for (Cloudlet cloudlet : cloudlets) {
    //             Load load = new Load(cloudlet, defaultUrl);
    //             loads.add(load);
    //         }
    //     } catch (Exception e) {
    //         System.out.println(e.getMessage());
    //     } finally {
    //         return loads;
    //     }
    // }

    /**
     * 根据默认参数生成Load
     *
     * @param brokerId
     * @param vmid
     * @return
     */
    public Load generateLoad(int brokerId, int vmid) {
        int id = 0;
        long length = 400000;
        long fileSize = 300;
        long outputSize = 300;
        // number of cpus
        int pesNumber = 1;
        UtilizationModel utilizationModel = new UtilizationModelFull();

        // 用刚才定义的参数创建云任务
        Cloudlet cloudlet =
                new Cloudlet(id, length, pesNumber, fileSize,
                        outputSize, utilizationModel, utilizationModel,
                        utilizationModel);
        cloudlet.setUserId(brokerId);
        cloudlet.setVmId(vmid);
        Load load = new Load(cloudlet, this.defaultUrl);
        return load;
    }

    // /**
    //  * 生成并发任务
    //  *
    //  * @param loads             普通任务
    //  * @param concurrencyNumber 并发数
    //  * @return
    //  */
    // public ConcurrencyLoads getConcurrencyLoads(List<Load> loads, Integer concurrencyNumber) {
    //
    //     if (null == concurrencyNumber) {
    //         concurrencyNumber = 1;
    //     }
    //     ConcurrencyLoads concurrencyLoads = new ConcurrencyLoads(loads, concurrencyNumber);
    //     return concurrencyLoads;
    // }
    //
    // /**
    //  * Creating the cloudlet list that are going to run on containers
    //  *
    //  * @param brokerId
    //  * @param numberOfCloudlets
    //  * @return
    //  * @throws FileNotFoundException
    //  */
    // public static List<ContainerCloudlet> createContainerCloudletList(int brokerId, int numberOfCloudlets)
    //         throws FileNotFoundException {
    //     String inputFolderName = LoadGeneratorTest.class.getClassLoader().getResource("workload/planetlab").getPath();
    //     ArrayList<ContainerCloudlet> cloudletList = new ArrayList<ContainerCloudlet>();
    //     long fileSize = 300L;
    //     long outputSize = 300L;
    //     UtilizationModelNull utilizationModelNull = new UtilizationModelNull();
    //     java.io.File inputFolder1 = new java.io.File(inputFolderName);
    //     java.io.File[] files1 = inputFolder1.listFiles();
    //     int createdCloudlets = 0;
    //     for (java.io.File aFiles1 : files1) {
    //         java.io.File inputFolder = new java.io.File(aFiles1.toString());
    //         java.io.File[] files = inputFolder.listFiles();
    //         for (int i = 0; i < files.length; ++i) {
    //             if (createdCloudlets < numberOfCloudlets) {
    //                 ContainerCloudlet cloudlet = null;
    //
    //                 try {
    //                     cloudlet = new ContainerCloudlet(IDs.pollId(ContainerCloudlet.class), Constants.CLOUDLET_LENGTH, 1,
    //                             fileSize, outputSize,
    //                             new UtilizationModelPlanetLabInMemoryExtended(files[i].getAbsolutePath(), 300.0D),
    //                             utilizationModelNull, utilizationModelNull);
    //                 } catch (Exception var13) {
    //                     var13.printStackTrace();
    //                     System.exit(0);
    //                 }
    //
    //                 cloudlet.setUserId(brokerId);
    //                 cloudletList.add(cloudlet);
    //                 createdCloudlets += 1;
    //             } else {
    //
    //                 return cloudletList;
    //             }
    //         }
    //
    //     }
    //     return cloudletList;
    // }

    // /**
    //  * 通过参数生成任务列表生成任务列表
    //  *
    //  * @param brokeId
    //  * @param Urls
    //  * @param utilizationModelCpu
    //  * @param utilizationModelRam
    //  * @param utilizationModelBw
    //  * @return
    //  * @throws FileNotFoundException
    //  */

    // public static List<Load> createLoadList(int brokeId, List<String> Urls, UtilizationModel utilizationModelCpu, UtilizationModel utilizationModelRam, UtilizationModel utilizationModelBw) throws FileNotFoundException {
    //
    //     long fileSize = 300L;
    //     long outputSize = 300L;
    //     String inputFolderName = LoadGenerator.class.getClassLoader().getResource("workload/planetlab").getPath();
    //     ArrayList<Load> loadList = new ArrayList<Load>();
    //     java.io.File inputFolder1 = new java.io.File(inputFolderName);
    //     java.io.File[] files1 = inputFolder1.listFiles();
    //     int createdCloudlets = 0;
    //
    //     for (java.io.File aFiles1 : files1) {
    //         java.io.File inputFolder = new java.io.File(aFiles1.toString());
    //         java.io.File[] files = inputFolder.listFiles();
    //         for (int i = 0; i < files.length; ++i) {
    //             if (createdCloudlets < Urls.size()) {
    //                 ContainerCloudlet cloudlet = null;
    //                 try {
    //                     cloudlet = new ContainerCloudlet(IDs.pollId(ContainerCloudlet.class), Constants.CLOUDLET_LENGTH, 1,
    //                             fileSize, outputSize,
    //                             new UtilizationModelPlanetLabInMemoryExtended(files[i].getAbsolutePath(), 300.0D),
    //                             utilizationModelRam, utilizationModelBw);
    //                 } catch (Exception var13) {
    //                     var13.printStackTrace();
    //                     System.exit(0);
    //                 }
    //
    //                 cloudlet.setUserId(brokeId);
    //                 Load load = new Load(cloudlet, Urls.get(i));
    //                 loadList.add(load);
    //                 createdCloudlets += 1;
    //             } else {
    //                 return loadList;
    //             }
    //         }
    //     }
    //     return loadList;
    // }

    public double currentTime() {
        return System.currentTimeMillis();
    }

    public void printSimpleTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm:ss a");
        System.out.println("现在时间：" + simpleDateFormat.format(date)); // 输出已经格式化的现在时间（24小时制）
    }

    public void convertToSimpleTime(double date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm:ss a");
        // System.out.println("现在时间：" + simpleDateFormat.format(date)); // 输出已经格式化的现在时间（24小时制）
    }

    public Map<Double, List<Load>> createLoadsWithTime(int brokeId, double ramp_up, double ramp_down, int total_number, int maxConcurrent) {
        Map<Double, List<Load>> resultMap = new HashMap<>();
        int numOfCloudlet = 0;

        Integer up_acceleration = new Double(maxConcurrent / (ramp_up / 1000)).intValue();
        Integer up_number = 0;
        for (Integer speed = up_acceleration; speed <= maxConcurrent; speed += up_acceleration) {
            up_number = up_number + speed;
        }

        Integer down_acceleration = new Double(maxConcurrent / ramp_down * 1000).intValue();
        Integer down_number = 0;
        for (Integer speed = maxConcurrent; speed > 0; speed -= down_acceleration) {
            down_number = down_number + speed;
        }

        Integer stable_number = total_number - up_number - down_number;
        Double stable_time = new Double(stable_number / (maxConcurrent / Constants.INTERVAL));

        System.out.println("总时长：" + (ramp_up + stable_time + ramp_down) / 1000 + "s");
        System.out.println("\n启动|持续时间：" + ramp_up / 1000 + "s");
        System.out.println("启动|加速度：" + up_acceleration + "load/s^2");
        System.out.println("启动|需要生成的任务数量：" + up_number);

        System.out.println("\n稳定|持续时间：" + stable_time / 1000 + "s");
        System.out.println("稳定|需要生成的任务总量：" + stable_number);

        System.out.println("\n停止|持续时间：" + ramp_down / 1000 + "s");
        System.out.println("停止|加速度：" + down_acceleration + "load/s^2");
        System.out.println("停止|需要生成的任务数量：" + down_number + "\n");

        Integer currentSpeed;
        for (currentSpeed = up_acceleration; currentSpeed <= maxConcurrent; currentSpeed += up_acceleration) {

            Double changeTime = currentTime();

            List<Load> loads = new ArrayList<>();
            for (int i = 0; i < currentSpeed; i++) {
                loads.add(generateLoad(brokeId, vmId));
            }
            resultMap.put(currentTime(), loads);
            numOfCloudlet = numOfCloudlet + currentSpeed;

            System.out.println("启动阶段，当前生成速度" + currentSpeed + "  当前已生成任务数量" + numOfCloudlet);

            changeTime = changeTime + INTERVAL;
            while (currentTime() < changeTime) {
            }
        }

        if (stable_time > 0) {
            do {
                Double changeTime = currentTime();

                List<Load> loads = new ArrayList<>();
                for (int i = 0; i < maxConcurrent; i++) {
                    loads.add(generateLoad(brokeId, vmId));
                }
                resultMap.put(currentTime(), loads);
                numOfCloudlet = numOfCloudlet + maxConcurrent;

                System.out.println("稳定阶段，当前生成速度" + maxConcurrent + "  当前已生成任务数量" + numOfCloudlet);

                changeTime = changeTime + INTERVAL;
                while (currentTime() < changeTime) {
                }
            } while (numOfCloudlet < up_number + stable_number);
        }

        for (currentSpeed = maxConcurrent; currentSpeed > 0; currentSpeed -= down_acceleration) {
            Double changeTime = currentTime();

            List<Load> loads = new ArrayList<>();
            for (int i = 0; i < currentSpeed; i++) {
                loads.add(generateLoad(brokeId, vmId));
            }
            resultMap.put(currentTime(), loads);
            numOfCloudlet = numOfCloudlet + currentSpeed;

            System.out.println("终止阶段，当前生成任务速度" + currentSpeed + "  当前已生成任务数量" + numOfCloudlet);

            changeTime = changeTime + INTERVAL;
            while (currentTime() < changeTime) {
            }
        }
        return resultMap;
    }

    public List<Cloudlet> getCloudlistFromLoadList(List<Load> loads) {
        List<Cloudlet> cloudlets = new ArrayList<>(loads.size());
        for (Load load : loads) {
            cloudlets.add(load.getSingleTask());
        }
        return cloudlets;
    }

    @Test
    public void creatLoadsWithTimeTest() {
        Map<Double, List<Load>> map = createLoadsWithTime(brokeId, ramp_up, ramp_down, totalNumberOfRequest, maxConcurrent);
        PropertiesUtil propertiesUtil = new PropertiesUtil();

        propertiesUtil.writePropertiesFromMap("ListLoads", map);
    }

}

