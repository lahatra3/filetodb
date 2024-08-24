package mg.lahatra3.functions;

import lombok.RequiredArgsConstructor;
import mg.lahatra3.beans.DatatypeConfiguration;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;

import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import static org.apache.spark.sql.functions.col;

@RequiredArgsConstructor
public class DataConversionFunc implements UnaryOperator<Dataset<Row>> {
   private final Map<String, DatatypeConfiguration> dataConversion;

   @Override
   public Dataset<Row> apply(Dataset<Row> dataset) {
      if (dataConversion.isEmpty()) {
         return dataset;
      }
      Map<String, Column> columns = dataConversion
         .entrySet()
         .parallelStream()
         .collect(
            Collectors.toMap(
               Map.Entry::getKey,
               entry -> getColumn(entry.getKey(), getDatatype(entry.getValue()))
            )
         );
      return dataset.withColumns(columns);
   }

   private Column getColumn(String columnName, DataType datatype) {
      return col(columnName).cast(datatype);
   }

   private DataType getDatatype(DatatypeConfiguration datatype) {
      return switch (datatype) {
         case BOOLEAN -> DataTypes.BooleanType;
         case STRING -> DataTypes.ShortType;
         case INTEGER -> DataTypes.IntegerType;
         case LONG -> DataTypes.LongType;
         case DOUBLE -> DataTypes.DoubleType;
         case TIMESTAMP -> DataTypes.TimestampType;
         case DATE -> DataTypes.DateType;
      };
   }
}
