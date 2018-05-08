package com.bnq.common.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;

/**
 * Created by liqiang on 2018/4/24.
 */
public class CodeParseUtil {

    public static final String PACKAGE = "package";
    public static final String basePath = "D:\\workspace\\webServiceTest\\src\\main\\java\\com\\bnq\\code\\";
    public static void main(String[] args) throws IOException {
        readFile("D:\\workspace\\webServiceTest\\src\\main\\java\\com\\bnq\\ao");
    }

    public static void readFile(String path) throws IOException {
        File file = new File(path);
        if(file.isDirectory()){
            File[] files = file.listFiles(pathname -> {
                if (pathname != null) {
                    boolean b = pathname.getName().endsWith(".java");
                    return b;
                }
                return false;
            });
            if(files != null) {
                for (File javaFile : files) {
                    makeFileDir(javaFile);
                }
            }
        }else {
            makeFileDir(file);
        }
    }

    private static void makeFileDir(File file) throws IOException {
        BufferedReader bufferedReader = null;
        PrintWriter out = null;
        String line = null;
        String filePath = null;
        try {
            /*FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);*/
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.defaultCharset()));
            while ((line = bufferedReader.readLine()) != null){
                if(filePath == null && line.trim().startsWith(PACKAGE)){
                    String packagePath = line.substring(line.indexOf(PACKAGE) + PACKAGE.length(), line.indexOf(";"));
                    filePath = createFilePath(basePath, packagePath, file);
                }
                if(filePath != null) {
                    if(out == null) {
                        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath + "\\" + file.getName()), Charset.defaultCharset())),true);
                    }
                    out.println(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bufferedReader != null){
                bufferedReader.close();
            }
            if(out != null){
                out.close();
            }
        }
    }

    private static String createFilePath(String basePath, String packagePath, File javaFile) throws IOException {
        String javaFileAbsPath = basePath + packagePath.trim().replace('.','\\');
        File file = new File(javaFileAbsPath);
        boolean isMk;
        if (!file.exists()) {
            isMk = file.mkdirs();
        }else {
            isMk = true;
        }
        if(isMk){
            return javaFileAbsPath;
        }
        return null;
    }
}
