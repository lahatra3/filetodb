package mg.lahatra3.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FileDataSourceConfiguration {
    private String filenamePathString;
    private String encoding;
    private int numPartitions;
}
