package com.task.rename;

import org.apache.commons.io.*;
import org.jetbrains.annotations.*;

import java.io.*;
import java.util.*;

/**
 * Class consists of a single static method that renames files in a directory.
 */
public class Renaming {
    
    private static final List<String> extensions = List.of("java", "kt");

    private static String newName(@NotNull File file) {
        return file.getPath() + ".2019";
    }

    private static boolean correctExtension(@NotNull String name) {
        var extension = FilenameUtils.getExtension(name);
        return extensions.contains(extension);
    }

    /**
     * Recursively renames files in given directory with extensions java or kt
     * to filename.java.2019 or filename.kt.2019 respectively.
     * If given argument isnt a directory does nothing.
     * @return list of renamed files
     */
    public static List<File> rename(@NotNull File directory) {
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
