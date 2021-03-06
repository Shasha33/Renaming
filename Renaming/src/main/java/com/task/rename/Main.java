package com.task.rename;

import java.io.*;

public class Main {
    public static void main(String... args) {

        if (args.length == 0) {
            System.out.println("Missing argument");
            return;
        }

        for (var file : Renaming.rename(new File(args[0]))) {
            System.out.println(file.getName());
        }
    }
}
