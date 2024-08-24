package mg.lahatra3;

import lombok.Getter;
import mg.lahatra3.beans.DatatypeConfiguration;
import mg.lahatra3.beans.FileDataSourceConfiguration;
import mg.lahatra3.beans.JdbcDataSinkConfiguration;
import mg.lahatra3.beans.SparkConfiguration;

import java.util.Map;
import java.util.stream.Collectors;

import static mg.lahatra3.utils.ConfigurationUtils.*;

@Getter
public class FileToDbConfiguration {
    private SparkConfiguration sparkConfiguration;
    private FileDataSourceConfiguration fileDataSourceConfiguration;
    private JdbcDataSinkConfiguration jdbcDataSinkConfiguration;
    private Map<String, String> columnsMapping;
    private Map<String, DatatypeConfiguration> dataConversion;

    public FileToDbConfiguration() {
        setSparkConfiguration();
        setFileDataSourceConfiguration();
        setJdbcDataSinkConfiguration();
        setColumnsMapping();
        setDataConversions();
    }

    private void setSparkConfiguration() {
        this.sparkConfiguration = new SparkConfiguration(SPARK_APP_NAME, SPARK_MASTER_URL,
           SPARK_DRIVER_MEMORY, SPARK_EXECUTOR_MEMORY, SPARK_EXTRA_JAVA_OPTIONS);
    }

    private void setFileDataSourceConfiguration() {
        this.fileDataSourceConfiguration = new FileDataSourceConfiguration(DATA_SOURCE_FILENAME_PATH,
           DATA_SOURCE_ENCODING, Integer.parseInt(DATA_SOURCE_NUM_PARTITIONS));
    }

    private void setJdbcDataSinkConfiguration() {
        this.jdbcDataSinkConfiguration = new JdbcDataSinkConfiguration(DATA_SINK_URL, DATA_SINK_USER,
           DATA_SINK_PASSWORD, DATA_SINK_TABLE, DATA_SINK_NUM_PARTITIONS, DATA_SINK_BATCH_SIZE);
    }

    private void setColumnsMapping() {
        this.columnsMapping = DATA_TRANSFORMATIONS.get("columns_mapping");
    }

    private void setDataConversions() {
        this.dataConversion = DATA_TRANSFORMATIONS.get("data_conversion")
           .entrySet().parallelStream()
           .collect(
              Collectors.toMap(
                 Map.Entry::getKey,
                 entry -> DatatypeConfiguration.valueOf(entry.getValue())
              )
           );
    }

}
