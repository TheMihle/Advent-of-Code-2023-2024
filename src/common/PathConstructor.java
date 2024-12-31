package common;

import java.nio.file.Paths;

public  class PathConstructor {

//    Constructs path for the input file
    public static <T> String getInputPath(Class<T> selfClass, String fileName) {
        String path = selfClass.getPackageName().replace(".", "/");
        return Paths.get("src", path, fileName + ".txt").toString();
    }

    public static <T> String getInputPath(Class<T> selfClass) {
        return getInputPath(selfClass, "input");
    }
}
