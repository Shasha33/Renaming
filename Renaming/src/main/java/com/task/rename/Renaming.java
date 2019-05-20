package com.task.rename;

import org.apache.commons.io.*;

import java.io.*;
import java.util.*;

public class Renaming {

    private static boolean correctExt(String name) {
        var extention = FilenameUtils.getExtension(name);
        return "java".equals(extention) || "kt".equals(extention);
    }

    public static List<File> rename(File directory) {
        if (!directory.isDirectory()) {
            return new ArrayList<>();
        }

        var result = new ArrayList<File>();
        for (var file : directory.listFiles()) {
            if (file.isDirectory()) {
                result.addAll(rename(file));
            } else if (correctExt(file.getName())) {
                File renamed = new File(file.getPath() + ".2019");

                if (file.renameTo(renamed)) {
                    result.add(file);
                }
            }
        }
        return result;
    }
}
