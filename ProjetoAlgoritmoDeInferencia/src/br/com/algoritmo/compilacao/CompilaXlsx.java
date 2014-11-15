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
import java.util.Set;
import java.util.TreeMap;
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

 


 public static void main(String[] args) throws Exception {
     Workbook wb;
     Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
     data.put(0, new Object[] {0, "Luiz Carlos Miyadaira Ribeiro Junior", "Base", "0468265522433921", "SOFTWARE", null, null, null});
     data.put(1, new Object[] {1, "Sergio Antônio Andrade de Freitas", "Destino 1", "0395549254894676", "SOFTWARE", null, null, null});
     data.put(2, new Object[] {2, "Andre Luiz Aquere de Cerqueira e Souza", "Destino 2", "8424412648258970", "CIVIL", null, null, null});
     data.put(3, new Object[] {3, "Edson Mintsu Hung Destino", "Destino 3", "6753551743147880", "ELETRÔNICA", null, null, null});
     data.put(4, new Object[] {4, "Edgard Costa Oliveira", "Destino 4", "1196380808351110", "SOFTWARE", null, null, null});
     data.put(5, new Object[] {5, "Edson Alves da Costa Júnior", "Destino 5", "2105379147123450", "SOFTWARE", null, null, null});
     data.put(6, new Object[] {6, "André Barros de Sales", "Destino 6", "7610669796869660", "SOFTWARE", null, null, null});
     data.put(7, new Object[] {7, "Giovanni Almeida dos Santos", "Destino 7", "0580891429319047", "SOFTWARE", null, null, null});
     data.put(8, new Object[] {8, "Cristiane Soares Ramos", "Destino 8", "9950213660160160", "SOFTWARE", null, null, null});
     data.put(9, new Object[] {9, "Fabricio Ataides Braz", "Destino 9", "1700216932505000", "SOFTWARE", null, null, null});
     data.put(10, new Object[] {10, "Alexandre Sérgio de Araújo Bezerra", "Destino 10", "0255998976169051", "MEDICINA", null, null, null});
     data.put(11, new Object[] {11, "Eduardo Stockler Tognetti", "Destino 11", "2443108673822680", "ELÉTRICA", null, null, null});
     data.put(12, new Object[] {12, "Jan Mendonça Correa", "Destino 12", "7844006017790570", "CIÊNCIA DA COMPUTAÇÃO", null, null, null});
     data.put(13, new Object[] {13, "Rejane Maria da Costa Figueiredo", "Destino 13", "2187680174312042", "SOFTWARE", null, null, null});
     data.put(14, new Object[] {14, "Augusto César de Mendonça Brasil", "Destino 14", "0571960641751286", "ENERGIA", null, null, null});
     data.put(15, new Object[] {15, "Fábio Macêdo Mendes", "Destino 15", "8075435338067780", "FÍSICA", null, null, null});
     

     if(args.length > 0 && args[0].equals("-xls")) wb = new HSSFWorkbook();
     else wb = new XSSFWorkbook();

     Map<String, CellStyle> styles = createStyles(wb);

     Sheet aba1 = wb.createSheet("Percentual de similaridade 1");
     PrintSetup printSetup = aba1.getPrintSetup();
     printSetup.setLandscape(true);
     aba1.setFitToPage(true);
     aba1.setHorizontallyCenter(true);
     
     Sheet aba2 = wb.createSheet("Percentual de similaridade 2");
     PrintSetup printSetup2 = aba2.getPrintSetup();
     printSetup2.setLandscape(true);
     aba1.setFitToPage(true);
     aba1.setHorizontallyCenter(true);

     //title row
     Row titleRow = aba1.createRow(0);
     titleRow.setHeightInPoints(15);
     Cell titleCell = titleRow.createCell(0);
     titleCell.setCellValue("Resultado da aplicação do algoritmo de cálculo do percentual de similaridade entre os indivíduos");
     titleCell.setCellStyle(styles.get("title"));
     aba1.addMergedRegion(CellRangeAddress.valueOf("$A$1:$H$1"));

     //header row
     Row headerRow = aba1.createRow(1);
     headerRow.setHeightInPoints(15);
     Cell headerCell;
     for (int i = 1; i <= titles.length; i++) {
         headerCell = headerRow.createCell(i);
         headerCell.setCellValue(titles[i-1]);
         headerCell.setCellStyle(styles.get("header"));
     }
     
     Row headerBase = aba1.createRow(2);
     headerBase.setHeightInPoints(15);
     Cell headerCellBase;
     for (int i = 1; i <= base.length; i++) {
     	headerCellBase = headerBase.createCell(i);
     	headerCellBase.setCellValue(base[i-1]);
     	headerCellBase.setCellStyle(styles.get("header1"));
     }
     
     Row headerDestino = aba1.createRow(4);
     headerDestino.setHeightInPoints(15);
     Cell headerCellDestino;
     for (int i = 1; i <= destino.length; i++) {
     	headerCellDestino = headerDestino.createCell(i);
     	headerCellDestino.setCellValue(destino[i-1]);
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
   //Iterate over data and write to sheet
     Set<Integer> keyset = data.keySet();
     int rownum = 0;
     for (Integer key : keyset)
     {
         Row row = aba1.createRow(3+rownum++);
         Object [] objArr = data.get(key);
         int cellnum = 0;
         for (Object obj : objArr)
         {
            Cell cell = row.createCell(cellnum++);
            if(obj instanceof String)
                 cell.setCellValue((String)obj);
             else if(obj instanceof Integer)
                 cell.setCellValue((Integer)obj);
         }
         if (row.getRowNum() == 3){ 
        	 rownum ++;
         }
     }
     //finally set column widths, the width is measured in units of 1/256th of a character width
     aba1.setColumnWidth(0, 2*256); //2 characters wide
     aba1.setColumnWidth(1, 26*256); //26 characters wide
     aba1.setColumnWidth(2, 20*256); //20 characters wide
     aba1.setColumnWidth(3, 18*256); //18 characters wide
     aba1.setColumnWidth(4, 20*256); //20 characters wide
     for (int i = 5; i < 9; i++) {
    	 aba1.setColumnWidth(i, 15*256);  //6 characters wide
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
     monthFont.setFontHeightInPoints((short)10);
     monthFont.setColor(IndexedColors.WHITE.getIndex());
     monthFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
     style = wb.createCellStyle();
     style.setAlignment(CellStyle.ALIGN_LEFT);
     style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
     style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
     style.setFillPattern(CellStyle.SOLID_FOREGROUND);
     style.setFont(monthFont);
     style.setWrapText(true);
     styles.put("header", style);
     
     Font monthFont1 = wb.createFont();
     monthFont1.setFontHeightInPoints((short)10);
     monthFont1.setColor(IndexedColors.WHITE.getIndex());
     monthFont1.setBoldweight(Font.BOLDWEIGHT_BOLD);
     style = wb.createCellStyle();
     style.setAlignment(CellStyle.ALIGN_LEFT);
     style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
     style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
     style.setFillPattern(CellStyle.SOLID_FOREGROUND);
     style.setFont(monthFont1);
     style.setWrapText(true);
     styles.put("header1", style);

     style = wb.createCellStyle();
     style.setAlignment(CellStyle.ALIGN_LEFT);
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
     style.setAlignment(CellStyle.ALIGN_LEFT);
     style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
     style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
     style.setFillPattern(CellStyle.SOLID_FOREGROUND);
     style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
     styles.put("formula_2", style);

     return styles;
 }
}
