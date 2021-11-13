package com.sbs.exam.demo.util;

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
}
