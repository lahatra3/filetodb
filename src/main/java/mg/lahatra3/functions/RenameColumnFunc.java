package mg.lahatra3.functions;

import lombok.RequiredArgsConstructor;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import java.util.Map;
import java.util.function.UnaryOperator;

@RequiredArgsConstructor
public class RenameColumnFunc implements UnaryOperator<Dataset<Row>> {
   private final Map<String, String> columnsMapping;

   @Override
   public Dataset<Row> apply(Dataset<Row> dataset) {
      if (columnsMapping.isEmpty()) {
         return dataset;
      }
      return dataset.withColumnsRenamed(columnsMapping);
   }
}
