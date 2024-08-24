package mg.lahatra3;

import mg.lahatra3.beans.DatatypeConfiguration;
import mg.lahatra3.beans.FileDataSourceConfiguration;
import mg.lahatra3.beans.JdbcDataSinkConfiguration;
import mg.lahatra3.beans.SparkConfiguration;
import mg.lahatra3.functions.DataConversionFunc;
import mg.lahatra3.functions.RenameColumnFunc;
import mg.lahatra3.functions.SelectColumnFunc;
import mg.lahatra3.readers.FileDataReader;
import mg.lahatra3.writers.JdbcDataWriter;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.Map;

public class FileToDbRunner implements Runnable {

    @Override
    public void run() {
        FileToDbConfiguration fileToDbConfiguration = new FileToDbConfiguration();
        SparkConfiguration sparkConfiguration = fileToDbConfiguration.getSparkConfiguration();
        FileDataSourceConfiguration fileDataSourceConfiguration = fileToDbConfiguration.getFileDataSourceConfiguration();
        JdbcDataSinkConfiguration jdbcDataSinkConfiguration = fileToDbConfiguration.getJdbcDataSinkConfiguration();
        Map<String, String> columnsMapping = fileToDbConfiguration.getColumnsMapping();
        Map<String, DatatypeConfiguration> dataConversion = fileToDbConfiguration.getDataConversion();

        SparkSession sparkSession = SparkSession.builder()
           .appName(sparkConfiguration.getAppName())
           .master(sparkConfiguration.getMasterUrl())
           .config("spark.driver.memory", sparkConfiguration.getDriverMemory())
           .config("spark.executor.memory", sparkConfiguration.getExecutorMemory())
           .config("spark.driver.extraJavaOptions", sparkConfiguration.getExtraJavaOptions())
           .config("spark.executor.extraJavaOptions", sparkConfiguration.getExtraJavaOptions())
           .getOrCreate();

        FileDataReader fileDataReader = new FileDataReader(sparkSession, fileDataSourceConfiguration);
        Dataset<Row> originalDataset = fileDataReader.get().cache();

        RenameColumnFunc renameColumnFunc = new RenameColumnFunc(columnsMapping);
        Dataset<Row> transformedDatasetRenameColumn = renameColumnFunc.apply(originalDataset);

        SelectColumnFunc selectColumnFunc = new SelectColumnFunc(columnsMapping);
        Dataset<Row> transformedDatasetSelectColumn = selectColumnFunc.apply(transformedDatasetRenameColumn);

        DataConversionFunc dataConversionFunc = new DataConversionFunc(dataConversion);
        Dataset<Row> transformedDatasetDataConversion = dataConversionFunc.apply(transformedDatasetSelectColumn);

        JdbcDataWriter jdbcDataWriter = new JdbcDataWriter(jdbcDataSinkConfiguration);
        jdbcDataWriter.accept(transformedDatasetDataConversion);
    }

}
