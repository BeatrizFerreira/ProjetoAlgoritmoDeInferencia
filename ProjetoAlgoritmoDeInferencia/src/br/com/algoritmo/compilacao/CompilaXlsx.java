package br.com.algoritmo.compilacao;

/* ====================================================================
Licensed to the Apache Software Foundation (ASF) under one or more
contributor license agreements.  See the NOTICE file distributed with
this work for additional information regarding copyright ownership.
The ASF licenses this file to You under the Apache License, Version 2.0
(the "License"); you may not use this file except in compliance with
the License.  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==================================================================== */
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.Map;
import java.util.HashMap;
import java.io.FileOutputStream;

/**
* A weekly timesheet created using Apache POI.
* Usage:
*  TimesheetDemo -xls|xlsx
*
* @author Yegor Kozlov
*/
public class CompilaXlsx {
 private static final String[] titles = {
 	"Indivíduos a serem comparados", "Indivíduos", "Id Lattes",	"Área de Concentração",	"Qtd de Produções",	
 	"Qtd Termos Iguais", "% de Similaridade"

 };
 
 private static final String[] base = {
 	"Indivíduo Base Original", "Indivíduo Base", null, null, null, null, null
 };
 
 private static final String[] destino = {
 	"Indivíduos Destinos Originais", "Indivíduos Destinos", null, null, null, null, null
 };

 private static Object[][] sample_data = {
         {"Luiz Carlos Miyadaira Ribeiro Junior", "Base", "0468265522433921", "SOFTWARE", 20.0, null, null},
//         {"Gisella Bronzetti", "GB", 4.0, 3.0, 1.0, 3.5, null},
 };

 public static void main(String[] args) throws Exception {
     Workbook wb;

     if(args.length > 0 && args[0].equals("-xls")) wb = new HSSFWorkbook();
     else wb = new XSSFWorkbook();

     Map<String, CellStyle> styles = createStyles(wb);

     Sheet sheet = wb.createSheet("Percentual de similaridade");
     PrintSetup printSetup = sheet.getPrintSetup();
     printSetup.setLandscape(true);
     sheet.setFitToPage(true);
     sheet.setHorizontallyCenter(true);

     //title row
     Row titleRow = sheet.createRow(0);
     titleRow.setHeightInPoints(15);
     Cell titleCell = titleRow.createCell(0);
     titleCell.setCellValue("Resultado da aplicação do algoritmo de cálculo do percentual de similaridade entre os indivíduos");
     titleCell.setCellStyle(styles.get("title"));
     sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$G$1"));

     //header row
     Row headerRow = sheet.createRow(1);
     headerRow.setHeightInPoints(15);
     Cell headerCell;
     for (int i = 0; i < titles.length; i++) {
         headerCell = headerRow.createCell(i);
         headerCell.setCellValue(titles[i]);
         headerCell.setCellStyle(styles.get("header"));
     }
     
     Row headerBase = sheet.createRow(2);
     headerBase.setHeightInPoints(15);
     Cell headerCellBase;
     for (int i = 0; i < base.length; i++) {
     	headerCellBase = headerBase.createCell(i);
     	headerCellBase.setCellValue(base[i]);
     	headerCellBase.setCellStyle(styles.get("header1"));
     }
     
     Row headerDestino = sheet.createRow(4);
     headerDestino.setHeightInPoints(15);
     Cell headerCellDestino;
     for (int i = 0; i < destino.length; i++) {
     	headerCellDestino = headerDestino.createCell(i);
     	headerCellDestino.setCellValue(destino[i]);
     	headerCellDestino.setCellStyle(styles.get("header1"));
     }
     


     /*int rownum = 2;
     for (int i = 0; i < 10; i++) {
         Row row = sheet.createRow(rownum++);
         for (int j = 0; j < titles.length; j++) {
             Cell cell = row.createCell(j);
             if(j == 9){
                 //the 10th cell contains sum over week days, e.g. SUM(C3:I3)
                 String ref = "C" +rownum+ ":I" + rownum;
                 cell.setCellFormula("SUM("+ref+")");
                 cell.setCellStyle(styles.get("formula"));
             } else if (j == 11){
                 cell.setCellFormula("J" +rownum+ "-K" + rownum);
                 cell.setCellStyle(styles.get("formula"));
             } else {
                 cell.setCellStyle(styles.get("cell"));
             }
         }
     }
     
     rownum = 3;
     for (int i = 0; i < 10; i++) {
         Row row = sheet.createRow(rownum++);
         for (int j = 0; j < titles1.length; j++) {
             Cell cell = row.createCell(j);
             if(j == 9){
                 //the 10th cell contains sum over week days, e.g. SUM(C3:I3)
                 String ref = "C" +rownum+ ":I" + rownum;
                 cell.setCellFormula("SUM("+ref+")");
                 cell.setCellStyle(styles.get("formula"));
             } else if (j == 11){
                 cell.setCellFormula("J" +rownum+ "-K" + rownum);
                 cell.setCellStyle(styles.get("formula"));
             } else {
                 cell.setCellStyle(styles.get("cell"));
             }
         }
     }
*/
     //set sample data
     for (int i = 0; i < sample_data.length; i++) {
         Row row = sheet.getRow(2 + i);
         for (int j = 0; j < sample_data[i].length; j++) {
             if(sample_data[i][j] == null) continue;

             if(sample_data[i][j] instanceof String) {
                 row.getCell(j).setCellValue((String)sample_data[i][j]);
             } else {
                 row.getCell(j).setCellValue((Double)sample_data[i][j]);
             }
         }
     }

     //finally set column widths, the width is measured in units of 1/256th of a character width
     sheet.setColumnWidth(0, 30*256); //30 characters wide
     for (int i = 1; i < 9; i++) {
         sheet.setColumnWidth(i, 20*256);  //6 characters wide
     }

     // Write the output to a file
     String file = "Saída/Percentual de similaridade.xls";
     if(wb instanceof XSSFWorkbook) file += "x";
     FileOutputStream out = new FileOutputStream(file);
     wb.write(out);
     out.close();
 }

 /**
  * Create a library of cell styles
  */
 private static Map<String, CellStyle> createStyles(Workbook wb){
     Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
     CellStyle style;
     Font titleFont = wb.createFont();
     titleFont.setFontHeightInPoints((short)12);
     titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
     style = wb.createCellStyle();
     style.setAlignment(CellStyle.ALIGN_CENTER);
     style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
     style.setFont(titleFont);
     styles.put("title", style);

     Font monthFont = wb.createFont();
     monthFont.setFontHeightInPoints((short)11);
     monthFont.setColor(IndexedColors.WHITE.getIndex());
     monthFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
     style = wb.createCellStyle();
     style.setAlignment(CellStyle.ALIGN_CENTER);
     style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
     style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
     style.setFillPattern(CellStyle.SOLID_FOREGROUND);
     style.setFont(monthFont);
     style.setWrapText(true);
     styles.put("header", style);
     
     Font monthFont1 = wb.createFont();
     monthFont1.setFontHeightInPoints((short)11);
     monthFont1.setColor(IndexedColors.WHITE.getIndex());
     monthFont1.setBoldweight(Font.BOLDWEIGHT_BOLD);
     style = wb.createCellStyle();
     style.setAlignment(CellStyle.ALIGN_CENTER);
     style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
     style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
     style.setFillPattern(CellStyle.SOLID_FOREGROUND);
     style.setFont(monthFont1);
     style.setWrapText(true);
     styles.put("header1", style);

     style = wb.createCellStyle();
     style.setAlignment(CellStyle.ALIGN_CENTER);
     style.setWrapText(true);
     style.setBorderRight(CellStyle.BORDER_THIN);
     style.setRightBorderColor(IndexedColors.BLACK.getIndex());
     style.setBorderLeft(CellStyle.BORDER_THIN);
     style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
     style.setBorderTop(CellStyle.BORDER_THIN);
     style.setTopBorderColor(IndexedColors.BLACK.getIndex());
     style.setBorderBottom(CellStyle.BORDER_THIN);
     style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
     styles.put("cell", style);

     style = wb.createCellStyle();
     style.setAlignment(CellStyle.ALIGN_CENTER);
     style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
     style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
     style.setFillPattern(CellStyle.SOLID_FOREGROUND);
     style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
     styles.put("formula", style);

     style = wb.createCellStyle();
     style.setAlignment(CellStyle.ALIGN_CENTER);
     style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
     style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
     style.setFillPattern(CellStyle.SOLID_FOREGROUND);
     style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
     styles.put("formula_2", style);

     return styles;
 }
}
