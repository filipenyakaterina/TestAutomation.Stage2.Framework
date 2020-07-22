package model;

public class Instance {
    private String numberOfInstances;
    private String purposeOfUse;
    private String operatingSystem;
    private String machineClass;
    private String machineType;
    private String numberOfGPUs;
    private String typeOfGPUs;
    private String localSSD;
    private String datacenterLocation;
    private String committedUsage;
    private String estimateCost;

    public Instance(String numberOfInstances, String purposeOfUse, String operatingSystem, String machineClass,
                    String machineType, String numberOfGPUs, String typeOfGPUs, String localSSD,
                    String datacenterLocation, String committedUsage, String estimateCost) {
        this.numberOfInstances = numberOfInstances;
        this.purposeOfUse = purposeOfUse;
        this.operatingSystem = operatingSystem;
        this.machineClass = machineClass;
        this.machineType = machineType;
        this.numberOfGPUs = numberOfGPUs;
        this.typeOfGPUs = typeOfGPUs;
        this.localSSD = localSSD;
        this.datacenterLocation = datacenterLocation;
        this.committedUsage = committedUsage;
        this.estimateCost = estimateCost;
    }

    public String getNumberOfInstances() {
        return numberOfInstances;
    }

    public String getPurposeOfUse() {
        return purposeOfUse;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getMachineClass() {
        return machineClass;
    }

    public String getMachineType() {
        return machineType;
    }

    public String getNumberOfGPUs() {
        return numberOfGPUs;
    }

    public String getTypeOfGPUs() {
        return typeOfGPUs;
    }

    public String getLocalSSD() {
        return localSSD;
    }

    public String getDatacenterLocation() {
        return datacenterLocation;
    }

    public String getCommittedUsage() {
        return committedUsage;
    }

    public String getEstimateCost() {
        return estimateCost;
    }
}
