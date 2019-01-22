package com.sangame.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Author scq
 * @Date 2017/5/13.
 */
public class ZipUtils {
    private static final Logger logger = LoggerFactory.getLogger(ZipUtils.class);

    /**
     * 递归压缩文件夹
     *
     * @param srcRootDir
     *            压缩文件夹根目录的子路径
     * @param file
     *            当前递归压缩的文件或目录对象
     * @param zos
     *            压缩文件存储对象
     * @throws Exception
     */
    private static void zip(String srcRootDir, File file, ZipOutputStream zos) throws Exception {
        if (file == null) {
            return;
        }
        if (!(file.exists())) {
            throw new FileNotFoundException("Source File not exist");
        }
        logger.info("file: isFile:"+file.isFile());
        if (file.isFile()) {
            int count;
            int bufferLength = 1024;
            byte data[] = new byte[bufferLength];

            // 获取文件相对于压缩文件夹根目录的子路径
            String subPath = file.getAbsolutePath();
            int index = subPath.indexOf(srcRootDir);
            if (index != -1) {
                subPath = subPath.substring(srcRootDir.length()
                        + File.separator.length());
            }
            ZipEntry entry = new ZipEntry(subPath);
            zos.putNextEntry(entry);
            BufferedInputStream bis = new BufferedInputStream(
                    new FileInputStream(file));
            while ((count = bis.read(data, 0, bufferLength)) != -1) {
                zos.write(data, 0, count);
            }
            bis.close();
            zos.closeEntry();
        } else {        // 如果是目录，则压缩整个目录
            File[] childFileList = file.listFiles();        // 压缩目录中的文件或子目录

            for (int n = 0; n < childFileList.length; n++) {
                childFileList[n].getAbsolutePath().indexOf(
                        file.getAbsolutePath());
                zip(srcRootDir, childFileList[n], zos);
            }
        }
    }

    /**
     * 对文件或文件目录进行压缩
     *
     * @param srcPath
     *            要压缩的源文件路径。如果压缩一个文件，则为该文件的全路径；如果压缩一个目录，则为该目录的顶层目录路径
     * @param zipPath
     *            压缩文件保存的路径。注意：zipPath不能是srcPath路径下的子文件夹
     * @param zipFileName
     *            压缩文件名
     * @throws Exception
     */
    public static void zip(String srcPath, String zipPath, String zipFileName)
            throws Exception {

        CheckedOutputStream cos = null;
        ZipOutputStream zos = null;
        try {
            File srcFile = new File(srcPath);
            logger.info("exists: "+srcFile.exists()+"| size:"+srcFile.length());
            if(!srcFile.exists()){
                throw new FileNotFoundException("SrcFile is not exists.");
            }
            // 判断压缩文件保存的路径是否为源文件路径的子文件夹，如果是，则抛出异常（防止无限递归压缩的发生）
            if (srcFile.isDirectory() && zipPath.indexOf(srcPath) != -1) {
                throw new Exception("zipPath must not be the child directory of srcPath.");
            }

            // 判断压缩文件保存的路径是否存在，如果不存在，则创建目录
            File zipDir = new File(zipPath);
            if (!zipDir.exists() || !zipDir.isDirectory()) {
                zipDir.mkdirs();
            }

            // 创建压缩文件保存的文件对象
            String zipFilePath = zipPath + File.separator + zipFileName;
            File zipFile = new File(zipFilePath);
            if (zipFile.exists()) {
                zipFile.delete();
            }
            cos = new CheckedOutputStream(new FileOutputStream(zipFile), new CRC32());
            zos = new ZipOutputStream(cos);
            // 如果只是压缩一个文件，则需要截取该文件的父目录
            String srcRootDir = srcPath;
            if (srcFile.isFile()) {
                int index = srcPath.lastIndexOf(File.separator);
                if (index != -1) {
                    srcRootDir = srcPath.substring(0, index);
                }
            }
            // 调用递归压缩方法进行目录或文件压缩
            zip(srcRootDir, srcFile, zos);
            zos.flush();
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (zos != null) {
                    zos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String zipPath = "/Users/alexzhang/src/tobacco";
        String dir = "/Users/alexzhang/src/tobacco/code/weiop/src";
        String zipFileName = "json.zip";
        try {
            zip(dir, zipPath, zipFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
