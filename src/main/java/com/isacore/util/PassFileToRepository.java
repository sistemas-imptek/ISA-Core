package com.isacore.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import org.apache.commons.io.FileUtils;

public class PassFileToRepository {

	// Mover archivos al repositorio establecido en el 192.168.4.18 D:/
		public static String base64ToFile(String fileBase64, String nameFile, String extFile) {
			String pathFile="";
			String outputFileName="";
			String tmp[]=fileBase64.split(",");
			
			if(extFile.equals("jpg")|| extFile.equals("png")|| extFile.equals("jpeg")) {
				outputFileName ="D:/ISA/FilesRepository/img/"+ nameFile;
			}else {
				outputFileName ="D:/ISA/FilesRepository/files/"+ nameFile;
			}
			
			File fichero = new File(outputFileName);
			if (fichero.exists()) {
				String id=null;
				LocalDateTime cc = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
				id=cc.format(formatter);
				id= id.replaceAll("[^\\dA-Za-z]", "");
				outputFileName ="D:/ISA/FilesRepository/files/"+ id+nameFile; 
			}else {
				
			}
			  
			byte[] decodedBytes = Base64.getDecoder().decode(tmp[1]);
			
			try {
				FileUtils.writeByteArrayToFile(new File(outputFileName), decodedBytes);
				pathFile=outputFileName;
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return pathFile;
			}		
			return pathFile;
		}
		
		// Transformar archivo a base 64 String File
		public static String fileToBase64(String pathFile, String exteFile) {
			String base64File = "";
			String beginBase64="";
	        File file = new File(pathFile);
	        try (FileInputStream imageInFile = new FileInputStream(file)) {
	            // Reading a file from file system
	            byte fileData[] = new byte[(int) file.length()];
	            imageInFile.read(fileData);
	            base64File = Base64.getEncoder().encodeToString(fileData);
	            if(exteFile.equals("png") || exteFile.equals("jpg") || exteFile.equals("jpeg")) {
	            	beginBase64="data:image/"+exteFile+";base64,";
	            	base64File=beginBase64+base64File;
	            }else if(exteFile.equals("pdf")) {
	            	beginBase64="data:application/"+exteFile+";base64,";
	            	base64File=beginBase64+base64File;
	            }else {
	            	beginBase64="data:application/"+exteFile+";base64,";
	            	base64File=beginBase64+base64File;
	            }
	            
	        } catch (FileNotFoundException e) {
	            System.out.println("File not found" + e);
	        } catch (IOException ioe) {
	            System.out.println("Exception while reading the file " + ioe);
	        }
	        return base64File;
		}
}
