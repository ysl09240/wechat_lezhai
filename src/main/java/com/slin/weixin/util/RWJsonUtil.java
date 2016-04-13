package com.slin.weixin.util;

import java.io.*;

/**
 * json文件的读写（用于通过json文件创建button）
 * @author SongLin.Yang
 * @data 2016-04-13 16:32
 */
public class RWJsonUtil {

    public static void main(String[] args) {
        String path = String.valueOf(Thread.currentThread().getContextClassLoader().getResource("WechatMenu.json"));
        String tempPath = path.substring(path.indexOf("/"));
        System.out.println(ReadFile(tempPath).replaceAll("\\s*",""));
    }

    public static void writeFile(String filePath, String sets)
            throws IOException {
        FileWriter fw = new FileWriter(filePath);
        PrintWriter out = new PrintWriter(fw);
        out.write(sets);
        out.println();
        fw.close();
        out.close();
    }

    public static String ReadFile(String path) {
        File file = new File(path);
        BufferedReader reader = null;
        String laststr = "";
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr = laststr + tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return laststr;
    }
}
