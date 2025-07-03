package com.pranil.blog.app.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pranil.blog.app.services.FileService;
@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file, String recoverImg) throws IOException {
        // Ensure target folder exists
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Try deleting old image first
        if (recoverImg != null && !recoverImg.trim().equals("")) {
            File oldFile = new File(path + File.separator + recoverImg);
            System.out.println("Trying to delete old image: " + oldFile.getAbsolutePath());
            if (oldFile.exists()) {
                oldFile.setWritable(true); // ensure permission
                boolean deleted = oldFile.delete();
                System.out.println("Old image deleted: " + deleted);
            } else {
                System.out.println("Old image does not exist.");
            }
        }

        // Generate new filename
        String originalName = file.getOriginalFilename();
        String extension = originalName.substring(originalName.lastIndexOf("."));
        String randomID = UUID.randomUUID().toString();
        String fileName = randomID + extension;

        // Save new file
        String filePath = path + File.separator + fileName;
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName;
    }


    @Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {

		String fullPath = path+File.separator+fileName;
		InputStream is = new FileInputStream(fullPath);
		
		return is;
	}

}
