package com.task.rename;

import org.apache.commons.io.*;

import java.io.*;
import java.util.*;

public class Renaming {
    
    private static final List<String> extensions = List.of("java", "kt");

    private static String newName(File file) {
        return file.getPath() + ".2019";
    }

    private static boolean correctExtension(String name) {
        var extension = FilenameUtils.getExtension(name);
        return extensions.contains(extension);
    }

    public static List<File> rename(File directory) {
        if (!directory.isDirectory()) {
            return new ArrayList<>();
        }

        var result = new ArrayList<File>();
        for (var file : directory.listFiles()) {
            if (file.isDirectory()) {
                result.addAll(rename(file));
            } else if (correctExtension(file.getName())) {
                File renamed = new File(newName(file));

                if (file.renameTo(renamed)) {
                    result.add(file);
                }
            }
        }
        return result;
    }
}
