package com.aiden.aidenlibrary;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.Objects;

public class FileUtil {


    public static boolean createFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            if (!Objects.requireNonNull(file.getParentFile()).exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                return file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }


    /**
     * 创建文件夹
     */
    public static boolean createDir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return file.mkdirs();
        }
        return true;
    }

    public static boolean deleteFile(String path) {
        File file = new File(path);
        boolean flag = false;
        if (file.exists()) {
            if (file.isDirectory()) {
                //删除文件夹
                flag = delDir(file);
            } else {
                flag = delFile(file);
            }
        }
        return flag;
    }

    /***
     * 删除文件夹
     */
    private static boolean delDir(File fileDir) {
        File[] files = fileDir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    delDir(file);
                } else {
                    delFile(file);
                }
            }
        }
        return fileDir.delete();
    }

    /***
     * 删除文件
     */
    private static boolean delFile(File file) {
        return file.delete();
    }

    /**
     * 文件复制
     */
    public static boolean copyFile(String originPath, String targetPath) {
        FileChannel input = null;
        FileChannel output = null;
        boolean flag;
        try {
            input = new FileInputStream(new File(originPath)).getChannel();
            output = new FileOutputStream(new File(targetPath)).getChannel();
            output.transferFrom(input, 0, input.size());
            flag = true;
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(input).close();
                Objects.requireNonNull(output).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    /**
     * 文件移动
     */
    public static boolean moveFile(String originPath, String targetPath) {
        File file = new File(originPath);
        return file.renameTo(new File(targetPath));
    }

    /**
     * 文件改名
     */
    public static boolean rename(String originPath, String targetPath) {
        return moveFile(originPath, targetPath);
    }


    /**
     * 从文件中读取数据
     */
    public static String readTextByFile(String originPath) {
        StringBuffer stringBuffer = new StringBuffer();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(originPath))) {
            String line;
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                stringBuffer.append(line)
                        .append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    public static void write2File(String targetPath, String content) {
        write2File(targetPath, content, false);
    }

    public static void write2File(String targetPath, String content, boolean isAppend) {
        File file = new File(targetPath);
        if (!file.exists()) {
            if (createFile(file.getAbsolutePath())) {
                System.out.println("create file " + file.getAbsolutePath());
            }
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, isAppend))) {
            bufferedWriter.write(content, 0, content.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}