package com.sbs.exam.demo.service;

import org.springframework.stereotype.Service;
import com.sbs.exam.demo.repository.GenFileRepository;
import com.sbs.exam.demo.vo.GenFile;

@Service
public class GenFileService {
	GenFileRepository genFileRepository;
	
	public GenFileService(com.sbs.exam.demo.repository.GenFileRepository genFileRepository) {
		this.genFileRepository = genFileRepository;
	}

	public void save(String relTypeCode, int relId, String originFileName, String fileExt, String typeCode,
			String type2Code, long fileSize, String fileExtTypeCode, String fileExtType2Code, int fileNo,
			String fileDir) {
		genFileRepository.save(relTypeCode, relId, originFileName, fileExt, typeCode, type2Code, fileSize, fileExtTypeCode, fileExtType2Code, fileNo, fileDir);
	}

}
