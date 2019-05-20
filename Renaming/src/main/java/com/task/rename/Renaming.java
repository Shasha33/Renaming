package com.task.rename;

import com.google.common.io.*;

import java.io.*;
import java.util.*;

public class Renaming {

    static boolean correctExt(String name) {
        var extention = Files.getFileExtension(name);
        return "java".equals(extention) || "kt".equals(extention);
    }

    static List<File> rename(File directory) {
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
