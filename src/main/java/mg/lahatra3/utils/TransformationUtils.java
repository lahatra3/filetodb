package mg.lahatra3.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;

public class TransformationUtils {

   public static Map<String, Map<String, String>> get() {
      ClassLoader classLoader = TransformationUtils.class.getClassLoader();
      String filenameResource = Objects.requireNonNull(
         classLoader.getResource("transformation.json")
      ).getFile();
      Path filenamePath = Paths.get(filenameResource);
      ObjectMapper objectMapper = new ObjectMapper();
      try {
         String fileContentString = Files.readString(filenamePath);
         return objectMapper.readValue(
            fileContentString,
            new TypeReference<>() {
            }
         );
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }
}
