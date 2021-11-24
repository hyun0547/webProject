package com.sbs.exam.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenFile {
	private int id;
	private String regDate;
	private String updateDate;
	private int delDate;
	private String delstatus;
	private String relTypeCode;
	private int relId;
	private String originFileName;
	private String fileExt;
	private String typeCode;
	private String type2Code;
	private long fileSize;
	private String fileExtTypeCode;
	private String fileExt2TypeCode;
	private int fileNo;
	private String fileDir;
	
	//excluded auto query value
	public GenFile(String relTypeCode, int relId, String originFileName, String fileExt, String typeCode,
			String type2Code, long fileSize, String fileExtTypeCode, String fileExtType2Code, int fileNo,
			String fileDir) {
		this.relTypeCode = relTypeCode;
		this.relId = relId;
		this.originFileName = originFileName;
		this.fileExt = fileExt;
		this.typeCode = typeCode;
		this.typeCode = typeCode;
		this.fileSize = fileSize;
		this.fileExtTypeCode = fileExtTypeCode;
		this.fileExtTypeCode = fileExtTypeCode;
		this.fileNo = fileNo;
		this.fileDir = fileDir; 
	}
}
