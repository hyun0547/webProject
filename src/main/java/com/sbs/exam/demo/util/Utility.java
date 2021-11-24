package com.sbs.exam.demo.util;

import java.text.SimpleDateFormat;

public class Utility {

	public static boolean checkNull(String str) {
		
		return str == null || str.trim().isEmpty();
	}
	
	public static String f(String format, Object... args) {
		
		return String.format(format, args);
	}
	
	public static String jsHistoryBack(String msg) {
		
		if(msg == null) {
			msg = "";
		}
		
		String script = """
				<script>
					const msg = '%s'.trim();
					
					if(msg.length > 0){
						alert(msg);
					}
					
					history.back();
				</script>
				""";
		
		return Utility.f(script, msg);
	}
	
	public static String jsReplace(String msg, String uri) {
			
			if(msg == null) {
				msg = "";
			}
			
			String script = """
					<script>
						const msg = '%s'.trim();
						
						if(msg.length > 0){
							alert(msg);
						}
						
						location.replace('%s');
					</script>
					""";
			
			return Utility.f(script, msg, uri);
		}

	public static String getFileExtFromFileName(String originFileName) {
		String splitName[] = originFileName.split("\\.");
		return splitName[splitName.length - 1];
	}
	
	public static String getFileExtTypeCodeFromFileName(String fileName) {
		String ext = getFileExtFromFileName(fileName).toLowerCase();

		switch (ext) {
		case "jpeg":
		case "jpg":
		case "gif":
		case "png":
			return "img";
		case "mp4":
		case "avi":
		case "mov":
			return "video";
		case "mp3":
			return "audio";
		}

		return "etc";
	}

	public static String getFileExtType2CodeFromFileName(String fileName) {
		String ext = getFileExtFromFileName(fileName).toLowerCase();

		switch (ext) {
		case "jpeg":
		case "jpg":
			return "jpg";
		case "gif":
			return ext;
		case "png":
			return ext;
		case "mp4":
			return ext;
		case "mov":
			return ext;
		case "avi":
			return ext;
		case "mp3":
			return ext;
		}

		return "etc";
	}
	

	public static String getNowYearMonthDateStr() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy_MM");

		String dateStr = format1.format(System.currentTimeMillis());

		return dateStr;
	}
}
