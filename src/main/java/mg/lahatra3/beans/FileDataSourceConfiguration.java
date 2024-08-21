package mg.lahatra3.beans;

import lombok.Getter;

@Getter
public class FileDataSourceConfiguration {
    private String filenamePathString;
    private String encoding;
    private int numPartitions;
}
