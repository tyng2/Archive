package com.main.comm;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.main.vo.FileVo;

@Component
public class cmmFile {
	
	private static Logger log = LoggerFactory.getLogger(cmmFile.class);
	
	private static String filePath;
	
	@Value("${file.path}")
	public void setFile_Path(String path) {
		filePath = path;
	}
	
	// 실제 파일 생성 : 원래 파일 이름, 저장된 파일 이름을 리턴
	public static FileVo fileUpload(MultipartFile file) {
		log.info("fileUpload!");
		
		String fileOlnm	= file.getOriginalFilename();
		String extra		= "__";
		
		File f = new File(filePath);
		if (f.exists()) {
			log.info("경로 존재");
		}else {
			log.info("경로가 존재하지 않음");
			
			if(f.mkdirs()) {
				log.info("경로 생성 완료 :: {}", filePath);
			}else {
				log.info("경로 생성 실패 :: {}", filePath);
				return null;
			}
		}
		
		DateFormat df 		= new SimpleDateFormat("yyyyMMddHmsS");
		String dateStr		= df.format(new Date());
		int lastDotIndex	= fileOlnm.lastIndexOf(".");
//		String[] split_name	= fileOlnm.split("\\.");
//		String fileName 	= split_name[0] + extra + dateStr + "." +split_name[1];
//		svnm : olnm[0] + "__yyyyMMddHmsS" + "." + olnm[1]
		String prevName		= fileOlnm.substring(0, lastDotIndex) + extra + dateStr;
		String extName		= fileOlnm.substring(lastDotIndex);
		String fileName		= prevName + extName;

		int i = 0;
		while (true) {
			f = new File(filePath + fileName);
			
			if (f.exists()) {
				log.info("파일이 이미 존재함 :: {}", fileName);
				fileName = prevName + "_" + ++i + extName;
				
			} else {
				log.info("해당 이름 파일 존재하지 않음");
				break;
			}
		}
		
		Path copyPath = Paths.get(filePath + StringUtils.cleanPath(fileName));
		try {
			Files.copy(file.getInputStream(), copyPath, StandardCopyOption.REPLACE_EXISTING);
			log.info("파일 생성 완료! :: {}", fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FileVo fileVo = new FileVo();
		fileVo.setFileOlnm(fileOlnm);
		fileVo.setFileSvnm(fileName);
//		return file_name;
		return fileVo;
	}
	
	
	public static byte[] fileDownload(String fileSvnm) throws Exception {
		File file		= new File(filePath + fileSvnm);
		byte fileByte[] = FileUtils.readFileToByteArray(file);
		
		return fileByte;
	}
	
	public static boolean deleteFile(FileVo file) {
		boolean result = false;
		File delFile = new File(filePath + file.getFileSvnm());
		if (delFile.exists()) {
			delFile.delete();
			result = true;
		}
		return result;
	}

}
