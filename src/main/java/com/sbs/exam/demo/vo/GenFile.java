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
	
	public String getForPrintDir() {
		return "/gen/" + relTypeCode + "/" + fileDir + "/" + relId + "__" + fileNo + "." + fileExt;
	}

	public String getFilePath() {
		return "/" + relTypeCode + "/" + fileDir + "/" + relId + "__" + fileNo + "." + fileExt;
	}
}
