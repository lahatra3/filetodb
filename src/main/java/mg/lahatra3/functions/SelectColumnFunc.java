package mg.lahatra3.functions;

import lombok.RequiredArgsConstructor;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import java.util.Map;
import java.util.function.UnaryOperator;

@RequiredArgsConstructor
public class SelectColumnFunc implements UnaryOperator<Dataset<Row>> {
   private final Map<String, String> columnsMapping;

   @Override
   public Dataset<Row> apply(Dataset<Row> dataset) {
      if (columnsMapping.isEmpty()) {
         return dataset;
      }
      String[] columns = columnsMapping
         .entrySet()
         .parallelStream()
         .map(Map.Entry::getValue)
         .toArray(String[]::new);
      return dataset.selectExpr(columns);
   }
}
