package mg.lahatra3;

import mg.lahatra3.utils.Env4jUtils;

public class FileToDbApplication {
    public static void main(String[] args) {
        Env4jUtils.load();
        FileToDbRunner fileToDbRunner = new FileToDbRunner();
        fileToDbRunner.run();
    }
}