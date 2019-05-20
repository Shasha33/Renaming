package com.task.rename;

import java.io.*;

public class Ranaming {
    static void rename(File directory) {
        if (!directory.isDirectory()) {
            return;
        }

        for (var file : directory.listFiles()) {
            if (file.isDirectory()) {
                rename(file);
            } else {
                File renamed = new File(file.getPath() + );
                file.renameTo();
            }
        }
    }
}
