package test.miniHr.data;

import java.io.FileInputStream;
import java.io.FileWriter;

import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadFileTest {
    public static void main(String[] args) {
        try {
            jobData("/app/company.xlsx");
            //companyData("/app/company.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void companyData(String fileName) throws Exception {
        FileInputStream bean = new FileInputStream(fileName);
        Workbook workbook = WorkbookFactory.create(bean);
        StringBuilder sb = new StringBuilder();
        Sheet sheet = workbook.getSheetAt(1);
        Row row = null;
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            StringBuilder sb2 = new StringBuilder(
                "insert into COMPANY_INFO(id,booth_id,COMPANY_NAME,information,SCALE,ADDRESS,WELFARE,NAME,PHONE,IMAGE) values(");
            if ((row = sheet.getRow(i)) != null) {
                for (int j = 0; j < 10; j++) {
                    String value = null;
                    Cell cell = row.getCell(j);
                    if (cell == null || Cell.CELL_TYPE_BLANK == cell.getCellType()) {
                        value = "";
                    } else {
                        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                            HSSFDataFormatter dataFormatter = new HSSFDataFormatter();
                            value = dataFormatter.formatCellValue(cell);
                        } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                            value = String.valueOf(cell.getStringCellValue());
                        }
                    }
                    value = value.replace("\n", "");
                    value = value.replaceAll("\\s*", "");
                    if (j == 0) {
                        sb2.append(value).append(",");
                    } else if (j == 1) {
                        sb2.append(value).append(",");
                    } else {
                        sb2.append("'").append(value).append("'").append(",");
                    }
                }
                sb2.setLength(sb2.length() - 1);
                sb2.append(");").append(System.getProperty("line.separator"));
                sb.append(sb2);
            }
        }
        FileWriter writer = new FileWriter("/app/company_info.sql");
        writer.write(sb.toString());
        writer.close();
        bean.close();
    }

    public static void jobData(String fileName) throws Exception {
        FileInputStream bean = new FileInputStream(fileName);
        Workbook workbook = WorkbookFactory.create(bean);
        StringBuilder sb = new StringBuilder();
        Sheet sheet = workbook.getSheetAt(0);
        Row row = null;
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            StringBuilder sb2 = new StringBuilder(
                "insert into JOB(company_id,JOB_NAME,INDUSTRY,JOB_SALARY,JOB_DETAIL) values(");
            if ((row = sheet.getRow(i)) != null) {
                for (int j = 0; j < 5; j++) {
                    String value = null;
                    Cell cell = row.getCell(j);
                    if (cell == null || Cell.CELL_TYPE_BLANK == cell.getCellType()) {
                        value = "";
                    } else {
                        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                            HSSFDataFormatter dataFormatter = new HSSFDataFormatter();
                            value = dataFormatter.formatCellValue(cell);
                        } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                            value = String.valueOf(cell.getStringCellValue());
                        }
                    }
                    if (j == 4) {
                        value = value.replace("\n", "");
                        value = value.replaceAll("\\s*", "");
                    }
                    if (j == 0) {
                        sb2.append(value).append(",");
                    } else {
                        sb2.append("'").append(value).append("'").append(",");
                    }
                }
                sb2.setLength(sb2.length() - 1);
                sb2.append(");").append(System.getProperty("line.separator"));
                sb.append(sb2);
            }
        }
        FileWriter writer = new FileWriter("/app/job.sql");
        writer.write(sb.toString());
        writer.close();
        bean.close();
    }
}
