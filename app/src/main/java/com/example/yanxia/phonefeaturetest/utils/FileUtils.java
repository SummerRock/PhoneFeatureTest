package com.example.yanxia.phonefeaturetest.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {


    public static String copyAssetAndWrite(Context context, String fileName) {
        try {
            File cacheDir = context.getCacheDir();
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            File outFile = new File(cacheDir, fileName);
            if (!outFile.exists()) {
                boolean res = outFile.createNewFile();
                if (res) {
                    InputStream is = context.getAssets().open(fileName);
                    FileOutputStream fos = new FileOutputStream(outFile);
                    byte[] buffer = new byte[is.available()];
                    int byteCount;
                    while ((byteCount = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, byteCount);
                    }
                    fos.flush();
                    is.close();
                    fos.close();
                    Toast.makeText(context, "下载成功", Toast.LENGTH_SHORT).show();
                    return outFile.getAbsolutePath();
                }
            } else {
                Toast.makeText(context, "文件已存在", Toast.LENGTH_SHORT).show();
                return outFile.getAbsolutePath();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean copyFile(final String srcPath, final String destPath) {
        try {
            int byteRead;
            File destFile = new File(destPath);
            if (destFile.exists()) {
                destFile.delete();
            }
            if (!destFile.exists()) {
                destFile.getParentFile().mkdirs();
                InputStream in = new FileInputStream(srcPath); //读入原文件
                FileOutputStream out = new FileOutputStream(destPath);
                byte[] buffer = new byte[1024];
                //int length;
                while ((byteRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, byteRead);
                }

                in.close();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void copyDirectory(File sourceLocation, File targetLocation)
            throws IOException {
        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists() && !targetLocation.mkdirs()) {
                throw new IOException("Cannot create dir " + targetLocation.getAbsolutePath());
            }

            String[] children = sourceLocation.list();
            if (children != null) {
                for (int i = 0; i < children.length; i++) {
                    copyDirectory(new File(sourceLocation, children[i]),
                            new File(targetLocation, children[i]));
                }
            }
        } else {
            // make sure the directory we plan to store the recording in exists
            File directory = targetLocation.getParentFile();
            if (directory != null && !directory.exists() && !directory.mkdirs()) {
                throw new IOException("Cannot create dir " + directory.getAbsolutePath());
            }

            InputStream in = new FileInputStream(sourceLocation);
            OutputStream out = new FileOutputStream(targetLocation);

            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        }
    }

    public static boolean cloneAssetFile(Context context, String sourceFilePath, String targetFilePath) {
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = context.getAssets().open(sourceFilePath);
            outputStream = new FileOutputStream(targetFilePath);

            byte[] buf = new byte[1024];
            int len;

            while ((len = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, len);
            }

            inputStream.close();
            outputStream.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 在dir目录下查找以prefix开头,以suffix结尾的文件
     *
     * @param dir
     * @param prefix
     * @param suffix
     * @return
     */
    public static File[] listFile(final String dir, final String prefix, final String suffix) {
        File targetDir = new File(dir);
        if (targetDir.exists() && targetDir.isDirectory()) {
            return targetDir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String filename) {
                    return filename.startsWith(prefix) && filename.endsWith(suffix);
                }
            });
        }
        return null;
    }

    /**
     * 创建新文件
     *
     * @param absolutePath
     * @return
     */
    public static File createNewFile(String absolutePath) {
        File file = new File(absolutePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * get file name from uri
     *
     * @param uri
     * @return
     */
    public static String getFileName(Uri uri) {
        File file = new File(uri.getPath());
        if (file.isFile()) {
            String fileName = file.getName();
            return fileName.contains(".") ? fileName.substring(0, fileName.lastIndexOf(".")) : fileName;
        }
        return null;
    }

    /**
     * 拷贝文件
     *
     * @param srcFile 源文件
     * @param dstFile 目标文件
     * @return
     */
    public static void copyFile(final File srcFile, final File dstFile) {
        try {
            int byteRead;
            InputStream in = new FileInputStream(srcFile);
            FileOutputStream out = new FileOutputStream(dstFile);
            byte[] buffer = new byte[1024];
            while ((byteRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, byteRead);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyFile(final InputStream in, final File dstFile) {
        try {
            int byteRead;
            FileOutputStream out = new FileOutputStream(dstFile);
            byte[] buffer = new byte[1024];
            while ((byteRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, byteRead);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyAssets2Sd(Context context, String assetDirectoryName, String outputDirectory) {
        AssetManager assets = context.getAssets();
        String[] assetDirectoryContents;
        try {
            assetDirectoryContents = assets.list(assetDirectoryName);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        for (String assetFilename : assetDirectoryContents) {
            if (TextUtils.isEmpty(assetFilename)) {
                continue;
            }

            String assetFilePath;
            if (TextUtils.isEmpty(assetDirectoryName)) {
                assetFilePath = assetFilename;
            } else {
                assetFilePath = assetDirectoryName + File.separator + assetFilename;
            }

            String outputFilePath = outputDirectory + File.separator + assetFilename;

            new File(outputDirectory).mkdirs();

            try {
                InputStream inputStream = assets.open(assetFilePath);
                copyAndClose(inputStream, new FileOutputStream(outputFilePath));
            } catch (IOException e) {
                e.printStackTrace();
                copyAssets2Sd(context, assetFilePath, outputFilePath);
            }
        }
    }

    public static boolean copyAssetsToLocal(Context context, String assetName, String outputFilePath) {
        AssetManager assets = context.getAssets();
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = assets.open(assetName);
            outputStream = new FileOutputStream(outputFilePath);
            copy(inputStream, outputStream);
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            closeQuietly(inputStream);
            closeQuietly(outputStream);
        }
    }

    private static void closeQuietly(OutputStream output) {
        try {
            if (output != null) {
                output.close();
            }
        } catch (IOException ioe) {
            //skip
        }
    }

    private static void closeQuietly(InputStream input) {
        try {
            if (input != null) {
                input.close();
            }
        } catch (IOException ioe) {
            //skip
        }
    }

    private static void copyAndClose(InputStream input, OutputStream output) throws IOException {
        copy(input, output);
        closeQuietly(input);
        closeQuietly(output);
    }

    private static void copy(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[1024];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
    }

    public static boolean isAssetFileExists(@NonNull Context context, @NonNull String fileName) {
        AssetManager assetManager = context.getResources().getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open(fileName);
            if (null != inputStream) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Moves a file or directory to the destination directory.
     * <p>
     * When the destination is on another file system, do a "copy and delete".
     *
     * @param src           the file or directory to be moved
     * @param destDir       the destination directory
     * @param createDestDir If {@code true} create the destination directory,
     *                      otherwise if {@code false} throw an IOException
     * @throws NullPointerException if source or destination is {@code null}
     * @throws IOException          if source or destination is invalid
     * @throws IOException          if an IO error occurs moving the file
     */
    public static void moveToDirectory(final File src, final File destDir, final boolean createDestDir)
            throws IOException {
        if (src == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (destDir == null) {
            throw new NullPointerException("Destination must not be null");
        }
        if (!src.exists()) {
            throw new FileNotFoundException("Source '" + src + "' does not exist");
        }
        if (src.isDirectory()) {
            moveDirectoryToDirectory(src, destDir, createDestDir);
        } else {
            moveFileToDirectory(src, destDir, createDestDir);
        }
    }

    /**
     * Moves a directory to another directory.
     *
     * @param src           the file to be moved
     * @param destDir       the destination file
     * @param createDestDir If {@code true} create the destination directory,
     *                      otherwise if {@code false} throw an IOException
     * @throws NullPointerException if source or destination is {@code null}
     * @throws IOException          if source or destination is invalid
     * @throws IOException          if an IO error occurs moving the file
     */
    public static void moveDirectoryToDirectory(final File src, final File destDir, final boolean createDestDir)
            throws IOException {
        if (src == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (destDir == null) {
            throw new NullPointerException("Destination directory must not be null");
        }
        if (!destDir.exists() && createDestDir) {
            destDir.mkdirs();
        }
        if (!destDir.exists()) {
            throw new FileNotFoundException("Destination directory '" + destDir +
                    "' does not exist [createDestDir=" + createDestDir + "]");
        }
        if (!destDir.isDirectory()) {
            throw new IOException("Destination '" + destDir + "' is not a directory");
        }
        moveDirectory(src, new File(destDir, src.getName()));
    }

    /**
     * Moves a directory.
     * <p>
     * When the destination directory is on another file system, do a "copy and delete".
     *
     * @param srcDir  the directory to be moved
     * @param destDir the destination directory
     * @throws NullPointerException if source or destination is {@code null}
     * @throws IOException          if source or destination is invalid
     * @throws IOException          if an IO error occurs moving the file
     */
    public static void moveDirectory(final File srcDir, final File destDir) throws IOException {
        if (srcDir == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (destDir == null) {
            throw new NullPointerException("Destination must not be null");
        }
        if (!srcDir.exists()) {
            throw new FileNotFoundException("Source '" + srcDir + "' does not exist");
        }
        if (!srcDir.isDirectory()) {
            throw new IOException("Source '" + srcDir + "' is not a directory");
        }
        if (destDir.exists()) {
            throw new IOException("Destination '" + destDir + "' already exists");
        }

        if (!srcDir.renameTo(destDir)) {
            throw new IOException("Cannot move directory: " + srcDir + " to a directory: " + destDir);
        }
    }

    /**
     * Moves a file to a directory.
     *
     * @param srcFile       the file to be moved
     * @param destDir       the destination file
     * @param createDestDir If {@code true} create the destination directory,
     *                      otherwise if {@code false} throw an IOException
     * @throws NullPointerException if source or destination is {@code null}
     * @throws IOException          if source or destination is invalid
     * @throws IOException          if an IO error occurs moving the file
     * @since 1.4
     */
    public static void moveFileToDirectory(final File srcFile, final File destDir, final boolean createDestDir)
            throws IOException {
        if (srcFile == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (destDir == null) {
            throw new NullPointerException("Destination directory must not be null");
        }
        if (!destDir.exists() && createDestDir) {
            destDir.mkdirs();
        }
        if (!destDir.exists()) {
            throw new FileNotFoundException("Destination directory '" + destDir +
                    "' does not exist [createDestDir=" + createDestDir + "]");
        }
        if (!destDir.isDirectory()) {
            throw new IOException("Destination '" + destDir + "' is not a directory");
        }
        moveFile(srcFile, new File(destDir, srcFile.getName()));
    }

    /**
     * Moves a file.
     * <p>
     * When the destination file is on another file system, do a "copy and delete".
     *
     * @param srcFile  the file to be moved
     * @param destFile the destination file
     * @throws NullPointerException if source or destination is {@code null}
     * @throws IOException          if the destination file exists
     * @throws IOException          if source or destination is invalid
     * @throws IOException          if an IO error occurs moving the file
     * @since 1.4
     */
    public static void moveFile(final File srcFile, final File destFile) throws IOException {
        if (srcFile == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (destFile == null) {
            throw new NullPointerException("Destination must not be null");
        }
        if (!srcFile.exists()) {
            throw new FileNotFoundException("Source '" + srcFile + "' does not exist");
        }
        if (srcFile.isDirectory()) {
            throw new IOException("Source '" + srcFile + "' is a directory");
        }
        if (destFile.exists()) {
            throw new IOException("Destination '" + destFile + "' already exists");
        }
        if (destFile.isDirectory()) {
            throw new IOException("Destination '" + destFile + "' is a directory");
        }

        if (!srcFile.renameTo(destFile)) {
            throw new IOException("Failed to move original file '" + srcFile +
                    "' to destination file '" + destFile + "'");
        }
    }

}
