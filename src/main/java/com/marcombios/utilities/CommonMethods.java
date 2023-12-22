package com.marcombios.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonMethods {

// ---------------------------------- Reusable Methods --------------------------------------

  
    public static boolean chkApplication(WebElement loanSearch) {
        boolean visible = false;
        int loopCounter = 0;
        int countToExit = 100;

        while (!visible) {
            try {
                visible=loanSearch.isDisplayed();

            } catch (Exception e) {
                visible=false;
                loopCounter++;
                if (loopCounter > countToExit) {
                    System.out.println("******* Environment Issue ***********");
                    visible= false;
                    break;
                }
            }

        }
        return visible;
    }

    /*
     * Capture Screenshot
     */
    public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
    
    public int generateRandomNumber(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Max must be greater than min");
        }

        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
  
    public static String readExcel(int rownum , int columnnum) throws IOException{
    	System.out.println("Reading data from Excel file");
    	FileInputStream fis = new FileInputStream("./TestData\\LoginData.xlsx");
    	XSSFWorkbook srcBook = new XSSFWorkbook(fis);     
        XSSFSheet sourceSheet = srcBook.getSheetAt(0);
        XSSFRow sourceRow = sourceSheet.getRow(rownum);
        XSSFCell cell=sourceRow.getCell(columnnum);
       return cell.getStringCellValue();
}
    
    public static void writeDataInSheet(File myFile, Map<String, Object[]> data) throws Exception {
        System.out.println("Writing data to the Excel file");
        FileInputStream fis = new FileInputStream("./TestData/testdata.properties");

        // Finds the workbook instance for XLSX file
        XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

        // Return first sheet from the XLSX workbook
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);
        for (int i = mySheet.getLastRowNum(); i >= 0; i--) {
            mySheet.removeRow(mySheet.getRow(i));
        }

        // Set to Iterate and add rows into XLS file
        Set<String> newRows = data.keySet();

        // get the last row number to append new data
        int rownum = mySheet.getLastRowNum();

        for (String key : newRows) {

            // Creating a new Row in existing XLSX sheet
            Row row = mySheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Boolean) {
                    cell.setCellValue((Boolean) obj);
                } else if (obj instanceof Date) {
                    cell.setCellValue((Date) obj);
                } else if (obj instanceof Double) {
                    cell.setCellValue((Double) obj);
                }
            }
        }

        // open an OutputStream to save written data into XLSX file
        FileOutputStream os = new FileOutputStream(myFile);
        myWorkBook.write(os);
        System.out.println("Writing on XLSX file Finished ...");
    }
    public String randomestring(int n)
   	{
   		String generatedstring=RandomStringUtils.randomAlphabetic(n);
   		return(generatedstring);
   	}
   	
   	public static String randomeNum(int n) {
   		String generatedString2 = RandomStringUtils.randomNumeric(n);
   		return (generatedString2);
   	}
   	
   	public static String randomeAlphaNum(int n) {
   		String generatedString3 = RandomStringUtils.randomAlphanumeric(n);
   		return (generatedString3);
   	}
   	
         
      

    /*
     * getCurrentDate --> Fetch Current Date [yyyy-MM-dd]
     */
    public static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        //System.out.println("Current Date is: " + dateFormat.format(date));
        return dateFormat.format(date);
    }

    public static String getCurrentDatePST() {
    	TimeZone tz = TimeZone.getTimeZone("PST");
    	DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	sdf.setTimeZone(tz);
    	return sdf.format(new Date());

    }

    /*
     * formatAmount --> Method will remove braces, Dollar, Comma symbols and convert to return double value
     */
    public static double formatAmount(String strAmount) {
        double dblFormatAmount = 0.0;
        if (strAmount != null) {
            //Remove $ and ,
            if (strAmount.contains("(")) {
                dblFormatAmount = Double.parseDouble(strAmount.replaceAll("[$,()]", ""));
                dblFormatAmount = dblFormatAmount * -1;
                return dblFormatAmount;

            } else {
                dblFormatAmount = Double.parseDouble(strAmount.replaceAll("[$,]", ""));
                return dblFormatAmount;
            }
        } else
            return dblFormatAmount;

    }

    /*
     * compareDates --> Method compares two dates in [Month,YYYY] format and returns true only if date1 < date2
     */
    public static boolean compareDates(String date1, String date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMMM,yyyy");
        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = sdf.parse(date1);
            dt2 = sdf.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (dt1.compareTo(dt2) > 0) {
            return false;
        } else if (dt1.compareTo(dt2) < 0) {
            return true;
        } else {
            return false;
        }

    }


    /*
     *convertJSONDate --> Used to parse JSON Date format from API response to YYYY-MM-DD format
     */

    public static String convertJSONDate(String longDate) {
        String dateFormatted = "";
        int start = longDate.indexOf('(');
        int end = longDate.indexOf(')');

        try {
        	longDate = longDate.substring(start+1, end);
            long myDate = Long.parseLong(longDate);
            Date getDate = new Date(myDate);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormatted = dateFormat.format(getDate).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dateFormatted;
    }

    /*
     *convertJSONDate --> Used to parse JSON Date format from API response to MMMDD,YYY format
     */

    public static String convertJSONDate1(String longDate) {
        String dateFormatted = "";

        try {
            longDate = longDate.substring(6, 19);
            long myDate = Long.parseLong(longDate);
            Date getDate = new Date(myDate);

            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
            dateFormatted = dateFormat.format(getDate).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dateFormatted;
    }


    /*
     *getDate_YYYYMMDD --> Convert Date [MMM dd, yyyy] to Date [yyyy-MM-dd]
     */
    public static String getDate_YYYYMMDD(String Date_MMMDDYYYY) {
        String dateFormatted = "";

        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");

        try {

            Date date = formatter.parse(Date_MMMDDYYYY);
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            dateFormatted = formatter.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateFormatted;
    }

    /*
     *getDate_MMMddyyyy --> Convert Date [yyyy-MM-dd] to Date [MMM dd, yyyy]
     */
    public static String getDate_MMMddyyyy(String Date_YYYYMMDD) {
        String dateFormatted = "";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {

            Date date = formatter.parse(Date_YYYYMMDD);
            formatter = new SimpleDateFormat("MMM dd, yyyy");
            dateFormatted = formatter.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateFormatted;
    }

 /***
  * Convert Input Date from Current Format to New Format format
  * call formatDateYYYYMMDD(date, currFormat, newFormat)
  * returns "date" in newFormat
 */
    public static String formatDate(String inputDate, String currFormat, String reqdFormat) {
        String dateFormatted = "";

        SimpleDateFormat formatter = new SimpleDateFormat(currFormat);

        try {

            Date date = formatter.parse(inputDate);
            formatter = new SimpleDateFormat(reqdFormat);
            dateFormatted = formatter.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateFormatted;
    }

    public static String addMonth_To_Date(String dateYYYYMMDD, int addMonths){
        String dateFormatted = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try{

            Date date = formatter.parse(dateYYYYMMDD);

            Calendar newDate = Calendar.getInstance();
            newDate.setTime(date);
            newDate.add(Calendar.MONTH,addMonths);
            date = newDate.getTime();
            dateFormatted=formatter.format(date);

        }catch(ParseException e){
            e.printStackTrace();
        }

        return dateFormatted;

    }

    /*
     * getDate_MMM --> Convert Date [MMM dd, yyyy] to Month[MMM]
     */
    public static String getDate_MMM(String Date_MMMDDYYYY) {
        String dateFormatted = "";

        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");

        try {

            Date date = formatter.parse(Date_MMMDDYYYY);
            formatter = new SimpleDateFormat("MMM");
            dateFormatted = formatter.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateFormatted;
    }


    /*
     *  getDate_YYYYMM01 --> Convert Date [MMM, yyyy] to Date [yyyy-MM-01]
     */
    public static String getDate_YYYYMM01(String Date_MMMYYYY) {
        String dateFormatted = "";

        SimpleDateFormat formatter = new SimpleDateFormat("MMM, yyyy");

        try {

            Date date = formatter.parse(Date_MMMYYYY);
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            dateFormatted = formatter.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateFormatted;
    }


    /*
     * compareDouble --> Compare two Double values and return boolean [True: equal or within Tolerance]
     */
    public static boolean compareDouble(double dblFirstNum, double dblSecondNum, double dblTolerance) {

        double d1 = dblFirstNum;
        double d2 = dblSecondNum;
        double diff = 0;
        double dblVariance = dblTolerance;

        diff = Math.abs(d1 - d2);

        if (diff <= dblVariance) {
            return true;
        } else {
            return false;
        }

    }

    /*
     * compareDates_PTD --> Method used to compare two dates and return boolean
     * True --> Date 1 LESS or EQUAL to Date 2
     */
    public static boolean compareDates_PTD(String date1, String date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = sdf.parse(date1);
            dt2 = sdf.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (dt1.compareTo(dt2) > 0) {
            //Date 1 GREATER THAN Date 2
            return false;
        } else if (dt1.compareTo(dt2) < 0) {
            //Date 1 LESS THAN Date 2
            return true;
        } else {
            //Date 1 EQUAL TO Date 2
            return true;
        }

    }

/***
 *  CompareDates returns INT
 *  0 --> date1 and date2 equal ;
 *  1 --> date1 greater than date2 ;
 *  -1 --> date1 less than date2
 *
 */
    public static int compareDatesNew(String date1, String date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = sdf.parse(date1);
            dt2 = sdf.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dt1.compareTo(dt2);

    }


/***
* 	diffInMonths --> Method used to fetch difference in number of months between two Dates of any format.
* 	If date1 greater than date2, difference is positive number;
*   If date1 less than date2, difference is negative number;
*   If date1 equals date2, returns 0
*/
    public static int diffInMonths(String date1, String date2, String dtFormat) {
//	        SimpleDateFormat sdf = new SimpleDateFormat("MMMMM,yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat(dtFormat);
        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = sdf.parse(date1);
            dt2 = sdf.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LocalDate dt3 = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(dt1));
        LocalDate dt4 = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(dt2));

        int noOfMonths = (int) ChronoUnit.MONTHS.between(dt3, dt4);
        return noOfMonths;

    }


    public static void deleteDownloadedFiles() throws Exception {
        System.out.println("Deleting existing files from exportedFiles folder.");
        String filePath = System.getProperty("user.dir") + "\\exportedFiles";
        if (!(new File(filePath).exists())) {
            new File(filePath).mkdir();
        } else {
            FileUtils.deleteDirectory(new File(filePath));
            Thread.sleep(1000);
            new File(filePath).mkdir();
        }
    }

    /****
     *
     * Get difference in DAYS between two Dates
     *
     */
    public static int getDiffInDays(String date1, String date2, String dtFormat) {
	    SimpleDateFormat sdf = new SimpleDateFormat(dtFormat);
	    Date dt1 = null;
	    Date dt2 = null;
	    try {
	        dt1 = sdf.parse(date1);
	        dt2 = sdf.parse(date2);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    LocalDate dt3 = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(dt1));
	    LocalDate dt4 = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(dt2));

	    int noOfMonths = (int) ChronoUnit.DAYS.between(dt3, dt4);
	    return noOfMonths;

    }

    /****
     *
     * Get Next/Prev Date after adding X DAYS to a Date
     *
     */
    public static String addDays_To_Date(String dateYYYYMMDD, int addDays){
        String dateFormatted = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try{

            Date date = formatter.parse(dateYYYYMMDD);

            Calendar newDate = Calendar.getInstance();
            newDate.setTime(date);
            newDate.add(Calendar.DATE,addDays);
            date = newDate.getTime();
            dateFormatted=formatter.format(date);

        }catch(ParseException e){
            e.printStackTrace();
        }

        return dateFormatted;

    }

    public static String getLastDayOfPrevYear() {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	// Get Last Day of previous year
		String strLastDayOfPrevYear = "";
		Calendar cal=Calendar.getInstance();
		int year=cal.get(Calendar.YEAR)-1;

		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH,11);
		cal.set(Calendar.DAY_OF_MONTH, 31);

		Date endDate = cal.getTime();
		strLastDayOfPrevYear = sdf.format(endDate);

		return strLastDayOfPrevYear;
    }


   
}