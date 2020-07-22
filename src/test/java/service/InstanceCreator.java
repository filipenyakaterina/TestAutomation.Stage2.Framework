package service;

import model.Instance;

public class InstanceCreator {
    public static final String NUMBER_OF_INSTANCES = "testdata.instance.number";
    public static final String PURPOSE_OF_USE = "testdata.instance.purpose";
    public static final String OPERATING_SYSTEM = "testdata.instance.os";
    public static final String MACHINE_CLASS = "testdata.instance.machine-class";
    public static final String MACHINE_TYPE = "testdata.instance.machine-type";
    public static final String NUMBER_OF_GPUS = "testdata.instance.number-of-gpus";
    public static final String TYPE_OF_GPUS = "testdata.instance.type-of-gpus";
    public static final String LOCAL_SSD = "testdata.instance.local-ssd";
    public static final String DATACENTER_LOCATION = "testdata.instance.datacenter";
    public static final String USECOMMITTED_USAGE = "testdata.instance.usecommitted-usage";
    public static final String ESTIMATE_COST = "testdata.instance.estimate-cost";

    public static Instance withCredentialsFromProperty() {
        return new Instance(TestDataReader.getTestData(NUMBER_OF_INSTANCES),
                TestDataReader.getTestData(PURPOSE_OF_USE), TestDataReader.getTestData(OPERATING_SYSTEM),
                TestDataReader.getTestData(MACHINE_CLASS), TestDataReader.getTestData(MACHINE_TYPE),
                TestDataReader.getTestData(NUMBER_OF_GPUS), TestDataReader.getTestData(TYPE_OF_GPUS),
                TestDataReader.getTestData(LOCAL_SSD), TestDataReader.getTestData(DATACENTER_LOCATION),
                TestDataReader.getTestData(USECOMMITTED_USAGE), TestDataReader.getTestData(ESTIMATE_COST));
    }

    public static Instance withoutEstimateCost() {
        return new Instance(TestDataReader.getTestData(NUMBER_OF_INSTANCES),
                TestDataReader.getTestData(PURPOSE_OF_USE), TestDataReader.getTestData(OPERATING_SYSTEM),
                TestDataReader.getTestData(MACHINE_CLASS), TestDataReader.getTestData(MACHINE_TYPE),
                TestDataReader.getTestData(NUMBER_OF_GPUS), TestDataReader.getTestData(TYPE_OF_GPUS),
                TestDataReader.getTestData(LOCAL_SSD), TestDataReader.getTestData(DATACENTER_LOCATION),
                TestDataReader.getTestData(USECOMMITTED_USAGE), "");
    }
}
