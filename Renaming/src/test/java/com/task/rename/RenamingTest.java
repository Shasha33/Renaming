package com.task.rename;

import org.apache.commons.io.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RenamingTest {

    private File directory;
    private static final int N = 10;

    @BeforeEach
    void init() {
        directory = new File("directory/");
        directory.mkdir();
    }

    @AfterEach
    void delete() throws IOException {
        FileUtils.deleteDirectory(directory);
    }

    @Test
    void emptyRenamingTest() {
        var list = Renaming.rename(directory);
        assertEquals(0, list.size());
    }

    @Test
    void singleFileEmptyRenamingTest() throws IOException {
        new File("directory/file.c").createNewFile();
        var list = Renaming.rename(directory);
        assertEquals(0, list.size());
    }

    @Test
    void singleFileRenamingTest() throws IOException {
        var file = new File("directory/file.java");
        file.createNewFile();
        var list = Renaming.rename(directory);
        assertEquals(1, list.size());
        assertEquals(file, list.get(0));
    }

    @Test
    void multipleFilesRenamingTest() throws IOException {
        var files = new ArrayList<File>();
        for (int i = 0; i < N; i++) {
            files.add(new File("directory/file" + i + ".java"));
            files.get(i).createNewFile();
        }

        var list = Renaming.rename(directory);
        assertEquals(files.size(), list.size());
        list.sort(Comparator.comparing(File::getName));
        assertEquals(files, list);
    }

    @Test
    void someOfFilesRenamingTest() throws IOException {
        var files = new ArrayList<File>();
        for (int i = 0; i < N; i++) {
            files.add(new File("directory/file" + i + ".java"));
            files.get(i).createNewFile();
        }

        for (int i = 0; i < N; i++) {
            new File("directory/file" + i + ".cpp").createNewFile();
        }

        var list = Renaming.rename(directory);
        assertEquals(files.size(), list.size());
        list.sort(Comparator.comparing(File::getName));
        assertEquals(files, list);
    }

    @Test
    void differentExtentionsRenamingTest() throws IOException {
        var files = new ArrayList<File>();
        for (int i = 0; i < N; i++) {
            files.add(new File("directory/file" + i + ".java"));
            files.get(i).createNewFile();
        }

        for (int i = 0; i < N; i++) {
            new File("directory/file" + i + ".cpp").createNewFile();
        }

        for (int i = 0; i < N; i++) {
            files.add(new File("directory/file" + i + ".kt"));
            files.get(N + i).createNewFile();
        }

        var list = Renaming.rename(directory);

        assertEquals(files.size(), list.size());

        list.sort(Comparator.comparing(File::getName));
        files.sort(Comparator.comparing(File::getName));
        assertEquals(files, list);
    }

    @Test
    void innerDirectoryRenamingTest() throws IOException {
        var innerDirectory = new File("directory/innerDirectory/");
        innerDirectory.mkdir();
        var file = new File("directory/innerDirectory/a.java");
        file.createNewFile();
        new File("directory/innerDirectory/b.sh").createNewFile();
        var list = Renaming.rename(directory);
        assertEquals(1, list.size());
        assertEquals(file, list.get(0));
    }

}