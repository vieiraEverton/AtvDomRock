package com.domRock.atv;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ReadWriteXlsx {

    private static final String SALDO_ITEM = "./files/SaldoITEM.xlsx";

    private static final String MOV_TO_ITEM = "./files/MovtoITEM.xlsx";

    private static final String MOV_DAY_ITEM = "./files/MovDayITEM.xlsx";


    public static ArrayList<SaldoItem> lerSaldoItem(){
        ArrayList<SaldoItem> saldoItens = new ArrayList<SaldoItem>( );
        try {
            Workbook workbook = WorkbookFactory.create(new File(SALDO_ITEM));

            // Obtendo a planilha no índice zero
            Sheet sheet = workbook.getSheetAt(0);

            // Crie um DataFormatter para formatar e obter o valor de cada célula como String
            DataFormatter dataFormatter = new DataFormatter();

            Iterator<Row> rowIterator = sheet.rowIterator();

            //descartar título
            rowIterator.next();
            while (rowIterator.hasNext()) {
                String aux[] = new String[7];
                int i = 0;
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    String cellValue = dataFormatter.formatCellValue(cell);
                    aux[i] = cellValue;
                    i++;
                }
                SaldoItem saldoItem = new SaldoItem(aux[0], Util.convertStringForDate(aux[1]), Util.convertStringForDouble(aux[2]),
                        Util.convertStringForDouble(aux[3]), Util.convertStringForDate(aux[4]), Util.convertStringForDouble(aux[5]),
                        Util.convertStringForDouble(aux[6]));
                saldoItens.add(saldoItem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
            return null;
        }
        return saldoItens;
    }

    public static ArrayList<MovtoItem> lerMovtoItem(){
        ArrayList<MovtoItem> movtoItens = new ArrayList<MovtoItem>( );
        try {
            Workbook workbook = WorkbookFactory.create(new File(MOV_TO_ITEM));

            // Obtendo a planilha no índice zero
            Sheet sheet = workbook.getSheetAt(0);

            // Crie um DataFormatter para formatar e obter o valor de cada célula como String
            DataFormatter dataFormatter = new DataFormatter();

            Iterator<Row> rowIterator = sheet.rowIterator();

            //descartar título
            rowIterator.next();
            while (rowIterator.hasNext()) {
                String aux[] = new String[5];
                int i = 0;
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    String cellValue = dataFormatter.formatCellValue(cell);
                    aux[i] = cellValue;
                    i++;
                }
                MovtoItem movtoIten = new MovtoItem(aux[0], TipoMovimento.valueOf(aux[1]), Util.convertStringForDate(aux[2]),
                        Util.convertStringForDouble(aux[3]), Util.convertStringForDouble(aux[4]));
                movtoItens.add(movtoIten);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
            return null;
        }
        return movtoItens;
    }

    public static boolean escreverMovDiarioITEM(ArrayList<Item> itens){
        String[] columns = {"Item", "data_lancamento", "qtd_ent", "valor_ent",
                "qtd_sai", "valor_sai", "qtd_inicial", "valor_inicial",
                "qtd_final", "valor_final"};
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Itens");

        Font headerFont = workbook.createFont();

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Criado Linha
        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Criando as outras linhas
        int rowNum = 1;

        for (Item item : itens) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.getItem());
            row.createCell(1).setCellValue(Util.convertDateForString(item.getDataLancamento()));
            row.createCell(2).setCellValue(Util.decimalFormat(item.getQuantidadeEntrada(), 4));
            row.createCell(3).setCellValue(Util.decimalFormat(item.getValorEntrada(), 2));
            row.createCell(4).setCellValue(Util.decimalFormat(item.getQuantidadeSaida(), 4 ));
            row.createCell(5).setCellValue(Util.decimalFormat(item.getValorSaida(), 2));
            row.createCell(6).setCellValue(Util.decimalFormat(item.getQuantidadeInicial(), 4));
            row.createCell(7).setCellValue(Util.decimalFormat(item.getValorInicial(), 2));
            row.createCell(8).setCellValue(Util.decimalFormat(item.getQuantidadeFinal(), 4));
            row.createCell(9).setCellValue(Util.decimalFormat(item.getValorFinal(), 2));
        }

        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Escrevendo no arquivo
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(MOV_DAY_ITEM);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        try {
            workbook.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
