package com.sbs.exam.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.sbs.exam.demo.repository.GenFileRepository;
import com.sbs.exam.demo.util.Utility;
import com.sbs.exam.demo.vo.GenFile;
import com.sbs.exam.demo.vo.ResultData;

@Service
public class GenFileService {
	@Value("${custom.genFileDirPath}")
	String genFileDirPath;
	
	GenFileRepository genFileRepository;
	
	public GenFileService(GenFileRepository genFileRepository) {
		this.genFileRepository = genFileRepository;
	}

	public ResultData<ArrayList<String>> save(Map<String, MultipartFile> fileMap, int articleId) {
		ArrayList<String> fileNames = new ArrayList<>();
		for(String fileInputName : fileMap.keySet()) {
			MultipartFile mf = fileMap.get(fileInputName);
			String fileInputNameBits[] = mf.getName().split("__");
			
			if(fileInputNameBits[0].equals("file") == false) {
				continue;
			}
			
			long fileSize = mf.getSize();
			
			if(fileSize <= 0) {
				continue;
			}
			
			String relTypeCode = fileInputNameBits[1];
			int relId = articleId;
			String originFileName = mf.getOriginalFilename();
			String fileExt = Utility.getFileExtFromFileName(originFileName.toLowerCase()).toLowerCase();
			String typeCode = fileInputNameBits[3]; 
			String type2Code = fileInputNameBits[4]; 
			String fileExtTypeCode = Utility.getFileExtTypeCodeFromFileName(originFileName.toLowerCase()).toLowerCase();
			String fileExtType2Code = Utility.getFileExtType2CodeFromFileName(originFileName.toLowerCase()).toLowerCase();
			int fileNo = Integer.parseInt(fileInputNameBits[2]);
			String fileDir = Utility.getNowYearMonthDateStr();
			
			genFileRepository.save(relTypeCode, relId, originFileName, fileExt, typeCode, type2Code, fileSize, fileExtTypeCode, fileExtType2Code, fileNo, fileDir);
			
			if(fileExt.equals("jpeg")) {
				fileExt = "jpg";
			}
			else if(fileExt.equals("htm")) {
				fileExt = "html";
			}
			
			//저장할 경로 지정
			String targetDirPath = genFileDirPath + "/" + relTypeCode + "/" + fileDir;
			File targetDir = new File(targetDirPath);
			
			//경로가 존재하지 않으면 생성
			if(targetDir.exists() == false) {
				targetDir.mkdirs();
			}
			
			String targetFileName = relId + "__" + fileNo + "." + fileExt;
			String targetFilePath = targetDir + "/" + targetFileName;
			
			try {
				mf.transferTo(new File(targetFilePath));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				return ResultData.from("F-1", "파일 저장 실패");
			}
			
			fileNames.add(targetFileName);
		}
		return ResultData.from("S-1", "업로드된 파일들", fileNames.getClass().getSimpleName(), fileNames);
		
	}

	public GenFile getFileForRel(int relId, String relTypeCode) {
		return genFileRepository.getFileForRel(relId, relTypeCode);
	}

}
