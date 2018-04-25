package com.bnq.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Created by liqiang on 2018/4/24.
 */
public class CodeParseUtil {

    public static final String PACKAGE = "package";
    public static final String basePath = "D:\\workspace\\webServiceTest\\src\\main\\java\\com\\bnq\\code\\";
    public static void main(String[] args) {
        readFile("D:\\workspace\\webServiceTest\\src\\main\\java\\com\\bnq\\ao");
    }

    public static void readFile(String path){
        File file = new File(path);
        if(file.isDirectory()){
            File[] files = file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    if (pathname != null) {
                        boolean b = pathname.getName().endsWith(".java");
                        return b;
                    }
                    return false;
                }
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

    private static void makeFileDir(File file) {
        try {
            /*FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);*/
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.defaultCharset()));
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                if(line.trim().startsWith(PACKAGE)){
                    String packagePath = line.substring(line.indexOf(PACKAGE) + PACKAGE.length(), line.indexOf(";"));
                    createFilePath(basePath,packagePath,file);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createFilePath(String basePath, String packagePath, File javaFile) throws IOException {
        String javaFileAbsPath = basePath + packagePath.trim().replace('.','\\');
        File file = new File(javaFileAbsPath);
        boolean isMk;
        if (!file.exists()) {
            isMk = file.mkdirs();
        }else {
            isMk = true;
        }
        if(isMk) {
            FileInputStream fileInputStream = null;
            FileOutputStream fileOutputStream = null;
            try {
                fileInputStream = new FileInputStream(javaFile);
                fileOutputStream = new FileOutputStream(javaFileAbsPath + "\\" + javaFile.getName());
                byte[] bytes = new byte[1024];
                int n = 0;
                while ((n = fileInputStream.read(bytes)) != -1) {
                    fileOutputStream.write(bytes, 0, n);
                    fileOutputStream.flush();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
            }
        }
    }
}
