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
	public String uploadImage(String path, MultipartFile file,String recoverImg) throws IOException {
		
		
		// file name 
		String name = file.getOriginalFilename();
		
		
		//random name generate File
		
		String randomID = UUID.randomUUID().toString();
		String fileName = randomID.concat(name.substring(name.lastIndexOf(".")));
		
		
		//fullpath
		String filePath = path+File.separator+fileName;
		
		//create folder if not created
		
		File f = new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		//file copy
		
		Files.copy(file.getInputStream(), Paths.get(filePath));
		System.out.println(Paths.get(filePath));
		System.out.println(path+recoverImg);
		
		
		File file2 = new File(path+File.separator+recoverImg);
		System.out.println(path+File.separator+recoverImg);
		if(file2.exists()) {
			System.out.println("File is :");
			boolean delete = file2.delete();
			System.out.println("File is :"+delete);
		}
		return fileName;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {

		String fullPath = path+File.separator+fileName;
		InputStream is = new FileInputStream(fullPath);
		
		return is;
	}

}
