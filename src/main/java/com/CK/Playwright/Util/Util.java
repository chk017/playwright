package com.CK.Playwright.Util;



import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.CK.Playwright.BaseTest.BaseTest;
import com.CK.Playwright.Reporting.Reporting;

import de.taimos.totp.TOTP;

public class Util extends BaseTest{

	public static String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	public static String formatTime = new SimpleDateFormat("hh.mm.ss").format(new Date());
	
	public static void verifyText(String expect, String actual) {
//		actual = BaseTest.page.title();
//		expect = sElement;
		if(expect.contains(actual)) {
			Reporting.pass("System successfully matches the Expected text : <b>"+expect+"</b> and Actual text : "+actual);
		}else {
			Reporting.fail("System failed to match the Expected text : <b>"+expect+"</b> and Actual text : "+actual);	
		}
		
	}

	/**
	 * This method is used to sleep for number of seconds mentioned in parameter
	 * @param n - number of seconds
	 * @author  kaja ChennnakesavaRao Bachu
	 */
	public static void sleepforseconds(int n) {

		try {
			Thread.sleep(n*1000);
			logger.info("Waiting for "+n+" seconds");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

/**
 * This method is used to click Escape button
 */
	public static void pressEscape() {
		Robot rb;
		try {
			rb = new Robot();
			rb.keyPress(java.awt.event.KeyEvent.VK_ESCAPE);
			rb.keyRelease(java.awt.event.KeyEvent.VK_ESCAPE);
			logger.info("Pressing the Escape button");
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method used to click tab button number of times mentioned in parameter
	 * @param tabscount - number of times to click tab button
	 * @throws AWTException
	 * @author  kaja ChennnakesavaRao Bachu
	 */
	public static void numberoftabs(int tabscount) {
		Robot preskey;
		try {
			preskey = new Robot();

		for (int i = 0; i < tabscount; i++) {
			preskey.keyPress(KeyEvent.VK_TAB);
			preskey.keyRelease(KeyEvent.VK_TAB);
		}

		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		}
	
	/**
	 * This method to press the keyboard keys. For ex pressKeys(keyEvent.VK_1)
	 * @param KeyEvent - keyEvent.VK_A
	 * @author  kaja ChennnakesavaRao Bachu
	 */
	public static void pressKeys(int KeyEvent) {
		Robot presskey;
		try {
			presskey = new Robot();
			presskey.keyPress(KeyEvent);
			presskey.keyRelease(KeyEvent);

			logger.info("Pressing the keys "+ KeyEvent);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	/**
	 * This method will click the Enter button
	 * @throws AWTException
	 * @author  kaja ChennnakesavaRao Bachu
	 */
	public static void hitenter() {
		Robot preskey;
		try {
			preskey = new Robot();
		
		preskey.keyPress(KeyEvent.VK_ENTER);
		preskey.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
		
		

	/**
	 * This method is used to execute the Autoit script.
	 * @param autoitexepath - Path of compiled Autoit script file with extension .exe
	 * @author  kaja ChennnakesavaRao Bachu
	 */
	public static void  runAutoit(String autoitexepath) {
		/*
		 * try { Runtime.getRuntime().exec(autoitexepath); } catch (IOException e) { //
		 * TODO Auto-generated catch block System.out.println("cause : " +
		 * e.getCause()); System.out.println("Message : " + e.getMessage()); }
		 */
		
			try {
				ProcessBuilder builder = new ProcessBuilder(autoitexepath);
				builder.inheritIO(); // Optional: to show output in console
				Process process = builder.start();
				
				System.out.println("Process exited with code: " + process.waitFor());
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("cause : " + e.getCause());
				System.out.println("Message : " + e.getMessage());
			}
		


	}


	/**
	 * This method is used to execute the Autoit script.
	 * @param autoitexepath - Path of compiled Autoitscript file with extension .exe
	 * @param param1	-	This is the value of parameter that was declared in the Autoit script
	 * @author  kaja ChennnakesavaRao Bachu
	 */
	public static void  runAutoit(String autoitexepath, String param1) {
		runAutoit(autoitexepath +" "+ param1);
	}


	/**
	 * This method will return the current date and time in the format specified in parameter.
	 * @param format - Format can be retrieved from method getformat() Or can be written the required format 
	 * @return - returns the date and time in the format specified
	 * @author  kaja ChennnakesavaRao Bachu
	 */
	public static String getCurrentDatenTime(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	}

	
	/**
	 * This method will return the tomorrow date and time in the format specified in parameter.
	 * @param format - Format can be retrieved from method getformat() Or can be written the required format 
	 * @return - returns the date and time in the format specified
	 * @author  kaja ChennnakesavaRao Bachu
	 */
	public static String getTomorrowDatenTime(String format) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	}
	
	/**
	 * This method will return the yesterday date and time in the format specified in parameter.
	 * @param format - Format can be retrieved from method getformat() Or can be written the required format 
	 * @return - returns the date and time in the format specified
	 * @author  kaja ChennnakesavaRao Bachu
	 */
	public static String getYesterdayDatenTime(String format) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	}
	
	/**
	 * This method just returns the format predefined in this class
	 * @return - returns the format "dd-MM-yyyy hh.mm.ss"
	 * @author  kaja ChennnakesavaRao Bachu
	 */
	public static String getformat() {
		return (formatDate +"_"+formatTime);

	}


	/**
	 * this method look for the folder and creates if not found in the specified path.
	 * @param sPath - Folder name
	 */
	public static void createFolder(String sPath) {

		if(!(new File(sPath)).exists()) {
			(new File(sPath)).mkdir();
		}else {
		}
	}

	/**
	 * This method will rename the file/folder by providing both old and new name parameters. Please note that, If you want to rename the file, it should have extensions as well. 
	 * @param CurrentName - this is the Old name with complete Path
	 * @param NewName     - this is the New name with complete Path
	 */
	public static void renameFolder(String CurrentName, String NewName) {

		try {
			if(new File(CurrentName).exists()) {
				new File(CurrentName).renameTo(new File(NewName));
				System.out.println("File Or Folder name is updated from " + CurrentName + " to "+ NewName);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * This method will delete the file/folder specified as parameter. Here we have to pass the complete path of the file or folder. 
	 * @param Path
	 */
	public static void deleteFolder(String Path) {
		try {
			FileUtils.forceDelete(new File(Path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("INFO: Looks like file or folder does not exist : " + e.getMessage());
		}
	}

	public static JSONObject readJson(String sPath) {

		JSONParser jSONParserObj = new JSONParser();
		JSONObject jSONObject = null;
		try {
			jSONObject = (JSONObject) jSONParserObj.parse(new FileReader(sPath));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error in Read json :" + e.getMessage());
		}
		return jSONObject;
	}

	@SuppressWarnings("unchecked")
	public static  Map<String, String> getJSONtoMap(String sPath, String key) {

		JSONParser jSONParserObj = new JSONParser();
		JSONObject jSONObject = null;
		try {
			jSONObject = (JSONObject) jSONParserObj.parse(new FileReader(sPath));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error in Read json :" + e.getMessage());
		}
		return (Map<String, String>) jSONObject.get(key);
	}



	public static String encodeFileToBase64Binary(File file){
		String encodedfile = null;
		try {
			FileInputStream fileInputStreamReader = new FileInputStream(file);
			byte[] bytes = new byte[(int)file.length()];
			fileInputStreamReader.read(bytes);
			encodedfile = Base64.getEncoder().encodeToString(bytes);

			fileInputStreamReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return encodedfile;
	}



	@SuppressWarnings("rawtypes")
	public static Set getBrowsersFromJson(String sPath) {

		JSONParser jSONParserObj = new JSONParser();
		JSONObject jSONObject = null;
		try {
			jSONObject = (JSONObject) jSONParserObj.parse(new FileReader(sPath));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error in Read json :" + e.getMessage());
		}

		System.out.println("Json key size : " + jSONObject.keySet().size());
		System.out.println("Json key set : " + jSONObject.keySet());

		return jSONObject.keySet();

	}
	
	/**
	 * This method is used to get the One time pass code of Microsoft authenticator app by passing the secrete key as parameter. 
	 * @param secretKey
	 * @return
	 * @author  kaja ChennnakesavaRao Bachu
	 */
	public static String getTOTPCode(String secretKey) {
        Base32 base32 = new Base32();
        byte[] bytes = base32.decode(secretKey);
        String hexKey = Hex.encodeHexString(bytes);
        return TOTP.getOTP(hexKey);
    }
	
	
}
