package mg.lahatra3.readers;

import java.util.function.Supplier;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import lombok.RequiredArgsConstructor;
import mg.lahatra3.beans.FileDataSourceConfiguration;

@RequiredArgsConstructor
public class FileDataReader implements Supplier<Dataset<Row>>{
    private final SparkSession sparkSession;
    private final FileDataSourceConfiguration fileDataSourceConfiguration;

    @Override
    public Dataset<Row> get() {
        Dataset<Row> dataset = sparkSession.read()
            .format("csv")
            .option("header", true)
            .option("inferSchema", true)
            .option("encoding", fileDataSourceConfiguration.getEncoding())
            .load(fileDataSourceConfiguration.getFilenamePathString());
        
        return dataset.repartition(fileDataSourceConfiguration.getNumPartitions());
    }

}
