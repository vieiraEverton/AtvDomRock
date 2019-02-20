package com.domRock.atv;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ReadXlsx {

    private static final String SALDO_ITEM = "../../../files/SaldoITEM.xlsx";

    private static final String MOV_TO_ITEM = "../../../files/MovtoITEM.xlsx";


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
}
