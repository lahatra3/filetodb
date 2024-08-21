package mg.lahatra3.writers;

import java.util.function.Consumer;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import lombok.RequiredArgsConstructor;
import mg.lahatra3.beans.JdbcDataSinkConfiguration;

@RequiredArgsConstructor
public class JdbcDataWriter implements Consumer<Dataset<Row>> {
    private final JdbcDataSinkConfiguration jdbcDataSinkConfiguration;

    @Override
    public void accept(Dataset<Row> dataset) {
        dataset.write()
            .format("jdbc")
            .option("user", jdbcDataSinkConfiguration.getUser())
            .option("password", jdbcDataSinkConfiguration.getPassword())
            .option("dbtable", jdbcDataSinkConfiguration.getDbtable())
            .option("numPartitions", jdbcDataSinkConfiguration.getNumPartitions())
            .option("batchsize", jdbcDataSinkConfiguration.getBatchSize())
            .save();
    }

}
