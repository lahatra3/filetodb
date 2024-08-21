package mg.lahatra3.beans;

import lombok.Getter;

@Getter
public class SparkConfiguration {
    private String appName;
    private String masterUrl;
    private String driverMemory;
    private String executorMemory;
    private String extraJavaOptions;
}
