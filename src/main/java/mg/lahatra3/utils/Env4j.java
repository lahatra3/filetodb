package mg.lahatra3.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Env4j {

   public void load() {
      load(".env");
   }

   public void load(String filename) {
      Path envfilePath = Paths.get(filename);
      try(Stream<String> fileContentStream = Files.lines(envfilePath)) {
         Map<String, String> enMap = fileContentStream
            .parallel()
            .filter(line -> !line.trim().startsWith("#") && !line.trim().isBlank())
            .map(line -> line.split("=", 2))
            .collect(
               Collectors.toMap(t -> t[0], t -> t[1])
            );
         setProperties(enMap);
      } catch(IOException e) {
         e.printStackTrace();
      }
   }

   public String get(String key) {
      return Optional.ofNullable(System.getenv(key))
         .or(() -> Optional.ofNullable(System.getProperty(key)))
         .orElseThrow(() -> new IllegalArgumentException("Missing required env: " + key));
   }

   public String get(String key, String defaultValue) {
      return Optional.ofNullable(System.getenv(key))
         .or(() -> Optional.ofNullable(System.getProperty(key)))
         .orElse(defaultValue);
   }

   private void setProperties(Map<String, String> envMap) {
      Properties properties = new Properties();
      properties.putAll(envMap);
      System.setProperties(properties);
   }
}
