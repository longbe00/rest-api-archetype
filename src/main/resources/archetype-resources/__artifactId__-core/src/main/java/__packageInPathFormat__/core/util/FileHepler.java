package ${package}.core.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * File-Upload Handler
 * 
 * @author kobi, bluedigm
 * @version 1.0
 */
public class FileHepler {
    
    /**
     * 파일 업로드
     * 
     * @param uploadPath 파일 업로드 시스템 절대경로
     * @param file MultipartFile
     * @return 변경된 파일명
     * @throws IOException
     */
    public static String upload(String path, String fileName, MultipartFile file) throws IOException {
        File uploadPath = new File(path);
        if(!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        
        // 한글파일명 오류개선
        String remakeFileName = fileName.replaceAll("[\\u3131-\\u318E\\uAC00-\\uD7A3]+", "");
        
        File targetFile = new File(uploadPath, remakeFileName);
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(targetFile);

            FileCopyUtils.copy(file.getInputStream(), fos);
            
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
        
        return remakeFileName;
    }
    /**
     * 파일 업로드
     * 
     * @param uploadPath 파일 업로드 시스템 절대경로
     * @param file MultipartFile
     * @return 변경된 파일명
     * @throws IOException
     */
    public static  String upload(String path, String fileName, File file) throws IOException {
        File uploadPath = new File(path);
        if(!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        // 한글파일명 오류개선
        String remakeFileName = fileName.replaceAll("[\\u3131-\\u318E\\uAC00-\\uD7A3]+", "");
        
        File targetFile = new File(uploadPath, remakeFileName);
        FileCopyUtils.copy(file, targetFile);
            
        return remakeFileName;
    }
    
    public static  List<File> getFilePathListForFolder(String folderPath) {
        final File folder = new File(folderPath);
        return getFilePathListForFolder(folder);
    }
    public static  List<File> getFilePathListForFolder( final File folder) {
        List<File> fileList = new ArrayList<File>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                fileList.addAll(getFilePathListForFolder(fileEntry));
            } else {
                fileList.add(fileEntry);
            }
        }
        return fileList;
    }
}
