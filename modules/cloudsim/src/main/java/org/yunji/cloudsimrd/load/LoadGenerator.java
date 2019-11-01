package org.yunji.cloudsimrd.load;

import org.cloudbus.cloudsim.Cloudlet;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;
import org.cloudbus.cloudsim.UtilizationModelNull;
import org.cloudbus.cloudsim.container.core.ContainerCloudlet;
import org.cloudbus.cloudsim.container.utils.IDs;
import org.cloudbus.cloudsim.util.WorkloadFileReader;
import org.yunji.cloudsimrd.Constants;
import org.yunji.cloudsimrd.load.ConcurrencyLoads;
import org.yunji.cloudsimrd.load.Load;

/**
 * @author weirenjie
 * @date 2019/10/22
 */

/**
 * 负载生成器，仿真负载。
 */
public class LoadGenerator {
    private int rating = 1;

    private String defaultUrl = "www.baidu.com";

    /**
     * 从文件中读取任务
     *
     * @param fileName
     * @return
     */
    public List<Load> generateLoadsFromFile(String fileName) {
        List<Load> loads = new ArrayList<>();
        try {
            WorkloadFileReader workloadFileReader = new WorkloadFileReader(fileName, this.rating);
            ArrayList<Cloudlet> cloudlets = workloadFileReader.generateWorkload();


            for (Cloudlet cloudlet : cloudlets) {
                Load load = new Load(cloudlet, defaultUrl);
                loads.add(load);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            return loads;
        }
    }

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

    /**
     * 生成并发任务
     *
     * @param loads             普通任务
     * @param concurrencyNumber 并发数
     * @return
     */
    public ConcurrencyLoads getConcurrencyLoads(List<Load> loads, Integer concurrencyNumber) {

        if (null == concurrencyNumber) {
            concurrencyNumber = 1;
        }
        ConcurrencyLoads concurrencyLoads = new ConcurrencyLoads(loads, concurrencyNumber);
        return concurrencyLoads;
    }

    /**
     * Creating the cloudlet list that are going to run on containers
     *
     * @param brokerId
     * @param numberOfCloudlets
     * @return
     * @throws FileNotFoundException
     */
    public static List<ContainerCloudlet> createContainerCloudletList(int brokerId, int numberOfCloudlets)
            throws FileNotFoundException {
        String inputFolderName = LoadGenerator.class.getClassLoader().getResource("workload/planetlab").getPath();
        ArrayList<ContainerCloudlet> cloudletList = new ArrayList<ContainerCloudlet>();
        long fileSize = 300L;
        long outputSize = 300L;
        UtilizationModelNull utilizationModelNull = new UtilizationModelNull();
        java.io.File inputFolder1 = new java.io.File(inputFolderName);
        java.io.File[] files1 = inputFolder1.listFiles();
        int createdCloudlets = 0;
        for (java.io.File aFiles1 : files1) {
            java.io.File inputFolder = new java.io.File(aFiles1.toString());
            java.io.File[] files = inputFolder.listFiles();
            for (int i = 0; i < files.length; ++i) {
                if (createdCloudlets < numberOfCloudlets) {
                    ContainerCloudlet cloudlet = null;

                    try {
                        cloudlet = new ContainerCloudlet(IDs.pollId(ContainerCloudlet.class), Constants.CLOUDLET_LENGTH, 1,
                                fileSize, outputSize,
                                new UtilizationModelPlanetLabInMemoryExtended(files[i].getAbsolutePath(), 300.0D),
                                utilizationModelNull, utilizationModelNull);
                    } catch (Exception var13) {
                        var13.printStackTrace();
                        System.exit(0);
                    }

                    cloudlet.setUserId(brokerId);
                    cloudletList.add(cloudlet);
                    createdCloudlets += 1;
                } else {

                    return cloudletList;
                }
            }

        }
        return cloudletList;
    }

    public static List<Load> createLoadList(int brokeId, List<String> Urls, UtilizationModel utilizationModelRam, UtilizationModel utilizationModelBw) throws FileNotFoundException {

        long fileSize = 300L;
        long outputSize = 300L;
        String inputFolderName = LoadGenerator.class.getClassLoader().getResource("workload/planetlab").getPath();
        ArrayList<Load> loadList = new ArrayList<Load>();
        java.io.File inputFolder1 = new java.io.File(inputFolderName);
        java.io.File[] files1 = inputFolder1.listFiles();
        int createdCloudlets = 0;

        for (java.io.File aFiles1 : files1) {
            java.io.File inputFolder = new java.io.File(aFiles1.toString());
            java.io.File[] files = inputFolder.listFiles();
            for (int i = 0; i < files.length; ++i) {
                if (createdCloudlets < Urls.size()) {
                    ContainerCloudlet cloudlet = null;
                    try {
                        cloudlet = new ContainerCloudlet(IDs.pollId(ContainerCloudlet.class), Constants.CLOUDLET_LENGTH, 1,
                                fileSize, outputSize,
                                new UtilizationModelPlanetLabInMemoryExtended(files[i].getAbsolutePath(), 300.0D),
                                utilizationModelRam, utilizationModelBw);
                    } catch (Exception var13) {
                        var13.printStackTrace();
                        System.exit(0);
                    }

                    cloudlet.setUserId(brokeId);
                    Load load = new Load(cloudlet, Urls.get(i));
                    loadList.add(load);
                    createdCloudlets += 1;
                } else {
                    return loadList;
                }
            }
        }
        return loadList;
    }

}
