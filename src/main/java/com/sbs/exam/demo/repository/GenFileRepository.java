package com.sbs.exam.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GenFileRepository {

	void save(@Param("relTypeCode") String relTypeCode, @Param("relId") int relId, @Param("originFileName") String originFileName, @Param("fileExt") String fileExt, @Param("typeCode") String typeCode, @Param("type2Code") String type2Code,
			@Param("fileSize") long fileSize, @Param("fileExtTypeCode") String fileExtTypeCode, @Param("fileExtType2Code") String fileExtType2Code, @Param("fileNo") int fileNo, @Param("fileDir") String fileDir);
}