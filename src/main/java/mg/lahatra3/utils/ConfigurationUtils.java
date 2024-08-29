package mg.lahatra3.utils;

import java.util.Map;

import static mg.lahatra3.utils.ConstantUtils.*;

public class ConfigurationUtils {
    public static final String SPARK_APP_NAME = Env4jUtils.get("SPARK_APP_NAME", DEFAULT_SPARK_APP_NAME);
    public static final String SPARK_MASTER_URL = Env4jUtils.get("SPARK_MASTER_URL", DEFAULT_SPARK_MASTER_URL);
    public static final String SPARK_DRIVER_MEMORY = Env4jUtils.get("SPARK_DRIVER_MEMORY", DEFAULT_SPARK_DRIVER_MEMORY);
    public static final String SPARK_EXECUTOR_MEMORY = Env4jUtils.get("SPARK_EXECUTOR_MEMORY", DEFAULT_SPARK_EXECUTOR_MEMORY);
    public static final String SPARK_EXTRA_JAVA_OPTIONS = Env4jUtils.get("SPARK_EXTRA_JAVA_OPTIONS", DEFAULT_SPARK_EXTRA_JAVA_OPTIONS);
    public static final String DATA_SINK_URL = Env4jUtils.get("DATA_SINK_URL");
    public static final String DATA_SINK_USER = Env4jUtils.get("DATA_SINK_USER");
    public static final String DATA_SINK_PASSWORD = Env4jUtils.get("DATA_SINK_PASSWORD");
    public static final String DATA_SINK_TABLE = Env4jUtils.get("DATA_SINK_TABLE");
    public static final String DATA_SINK_NUM_PARTITIONS = Env4jUtils.get("DATA_SINK_NUM_PARTITIONS", DEFAULT_NUM_PARTITIONS);
    public static final String DATA_SINK_BATCH_SIZE = Env4jUtils.get("DATA_SINK_BATCH_SIZE", DEFAULT_BATCH_SIZE);
    public static final String DATA_SOURCE_DATE = Env4jUtils.get("DATE_SOURCE_DATE", DEFAULT_DATE);
    public static final String DATA_SOURCE_FILENAME_PATH = Env4jUtils.get("DATA_SOURCE_FILENAME_PATH")
       .replace("<date>", DATA_SOURCE_DATE);
    public static final String DATA_SOURCE_ENCODING = Env4jUtils.get("DATA_SOURCE_ENCODING", DEFAULT_ENCODING);
    public static final String DATA_SOURCE_NUM_PARTITIONS = Env4jUtils.get("DATA_SOURCE_NUM_PARTITIONS", DEFAULT_NUM_PARTITIONS);
    public static final Map<String, Map<String, String>> DATA_TRANSFORMATIONS = TransformationUtils.get();

    private ConfigurationUtils() {}
}
