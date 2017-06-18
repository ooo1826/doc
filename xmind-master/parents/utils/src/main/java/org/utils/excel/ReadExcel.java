package org.utils.excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel{
	
	public static final String OFFICE_EXCEL_2003_POSTFIX="xls";
	public static final String OFFICE_EXCEL_2010_POSTFIX="xlsx";
	
	public static final String EMPTY					="";
	public static final String POINT					=".";
	public static final String NOT_EXCEL_FILE			=" : Not the Excel file!";
	
	private static Logger	   log						=Logger.getLogger(ReadExcel.class);
	
	public static Object readXlsx(InputStream is,String path) throws IOException{
		XSSFWorkbook xssfWorkbook=new XSSFWorkbook(is);
		
		Map<String,Object> xlsx=new HashMap<String,Object>();
		for(int i=0;i<xssfWorkbook.getNumberOfSheets();i++){
			XSSFSheet xssfSheet=xssfWorkbook.getSheetAt(i);
			if(xssfSheet==null){
				continue;
			}
			String xssfSheetName=xssfSheet.getSheetName();
			if(xssfSheet.getLastRowNum()>1){
				List<String[]> lists=new ArrayList<String[]>();
				for(int j=1;j<xssfSheet.getLastRowNum();j++){
					XSSFRow xssfRow=xssfSheet.getRow(j);
					if(xssfRow!=null){
						String[] strings=new String[xssfRow.getLastCellNum()];
						for(int k=0;k<xssfRow.getLastCellNum();k++){
							XSSFCell xssfCell=xssfRow.getCell(k);
							strings[k]=getCellValue(xssfCell);
						}
						lists.add(strings);
					}else{
						log.info("文件："+path+","+xssfSheetName+"第"+(j+1)+"行没有数据");
					}
				}
				xlsx.put(xssfSheetName,lists);
			}else{
				log.info("文件："+path+","+xssfSheetName+"没有数据");
			}
		}
		return xlsx;
	}
	
	private static String getCellValue(Cell cell){
		String cellValue="";
		DataFormatter formatter=new DataFormatter();
		if(cell!=null){
			switch(cell.getCellType()){
				case Cell.CELL_TYPE_NUMERIC:
					if(DateUtil.isCellDateFormatted(cell)){
						cellValue=formatter.formatCellValue(cell);
					}else{
						double value=cell.getNumericCellValue();
						int intValue=(int)value;
						cellValue=value-intValue==0?String.valueOf(intValue):String.valueOf(value);
					}
					break;
				case Cell.CELL_TYPE_STRING:
					cellValue=cell.getStringCellValue();
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					cellValue=String.valueOf(cell.getBooleanCellValue());
					break;
				case Cell.CELL_TYPE_FORMULA:
					cellValue=String.valueOf(cell.getCellFormula());
					break;
				case Cell.CELL_TYPE_BLANK:
					cellValue="";
					break;
				case Cell.CELL_TYPE_ERROR:
					cellValue="";
					break;
				default:
					cellValue=cell.toString().trim();
					break;
			}
		}
		return cellValue.trim();
	}
	
	public static Object readXls(InputStream is,String path) throws IOException{
		HSSFWorkbook hssfWorkbook=new HSSFWorkbook(is);
		
		Map<String,Object> xlsx=new HashMap<String,Object>();
		for(int i=0;i<hssfWorkbook.getNumberOfSheets();i++){
			HSSFSheet hssfSheet=hssfWorkbook.getSheetAt(i);
			if(hssfSheet==null){
				continue;
			}
			String hssfSheetName=hssfSheet.getSheetName();
			if(hssfSheet.getLastRowNum()>1){
				List<String[]> lists=new ArrayList<String[]>();
				for(int j=1;j<hssfSheet.getLastRowNum();j++){
					HSSFRow hssfRow=hssfSheet.getRow(j);
					if(hssfRow!=null){
						String[] strings=new String[hssfRow.getLastCellNum()];
						for(int k=0;k<hssfRow.getLastCellNum();k++){
							HSSFCell hssfCell=hssfRow.getCell(k);
							strings[k]=getCellValue(hssfCell);
						}
						lists.add(strings);
					}else{
						log.info("文件："+path+","+hssfSheetName+"第"+(j+1)+"行没有数据");
					}
				}
				xlsx.put(hssfSheetName,lists);
			}else{
				log.info("文件："+path+","+hssfSheetName+"没有数据");
			}
		}
		return xlsx;
	}
	
	public static Object readExcel(InputStream is,String path) throws IOException{
		if(path==null||ReadExcel.EMPTY.equals(path)){
			return null;
		}else{
			String postfix=ReadExcel.getPostfix(path);
			if(!ReadExcel.EMPTY.equals(postfix)){
				if(ReadExcel.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)){
					return readXls(is,path);
				}else if(ReadExcel.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)){ return readXlsx(is,path); }
			}
			log.info(ReadExcel.NOT_EXCEL_FILE+"path:"+path);
			return null;
		}
	}
	
	public static String getPostfix(String path){
		if(path==null||ReadExcel.EMPTY.equals(path.trim())){ return ReadExcel.EMPTY; }
		if(path.contains(ReadExcel.POINT)){ return path.substring(path.lastIndexOf(ReadExcel.POINT)+1,path.length()); }
		return ReadExcel.EMPTY;
	}
	
}
