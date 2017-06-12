package Client.view;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import Server.dto.IndicatorDTO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by tearsyu on 30/05/17.
 */
public class IndicatorRapportPDF {
    private String dest;
    private String timeScale;
    private BaseColor colorRed;
    private BaseColor colorGreen;
    public IndicatorRapportPDF(String fileName, String timeScale){
        this.dest = fileName + ".pdf";
        this.timeScale = timeScale;
        colorGreen = new BaseColor(100, 150, 87);//deep green
        colorRed = new BaseColor(224, 61, 72);
    }

    public void createPDF(Map<Date, java.util.List<IndicatorDTO>> data, Map<String, String> idct){
        String titleStr = getTitle(idct);
        Document doc = new Document(PageSize.A4);
        try {
            File file = new File(dest);
            System.out.println("Open file");
            file.getParentFile().mkdirs();
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(dest));
            doc.open();

            //CSC the original font
            Font flogo = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.CYAN);
            Chunk nameCompany = new Chunk("TheOriginal", flogo);
            //Title font
            Font ftitle = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLDITALIC, BaseColor.BLACK);
            Chunk title = new Chunk(titleStr, ftitle);
            //Add title and name Company to the file
            Paragraph pLogo = new Paragraph(nameCompany);
            doc.add(pLogo);
            Paragraph pTitle = new Paragraph(title+"\n\n");
            doc.add(pTitle);

            //Table manage
            PdfPTable table = new PdfPTable(8);
            table.setTotalWidth(doc.getPageSize().getWidth() - 80);
            table.setLockedWidth(true);
            //column generation and decoration

            PdfPCell colTimeScale = new PdfPCell(new Phrase("Date " + timeScale));
            colTimeScale.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell colNum = new PdfPCell(new Phrase("Nombres opé total"));
            colNum.setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfPCell colMaxDate = new PdfPCell(new Phrase("Max jours/opé"));
            colMaxDate.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell colMinDate = new PdfPCell(new Phrase("Min jours/opé"));
            colMinDate.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell colAvgDate = new PdfPCell(new Phrase("Avg jours/opé"));
            colAvgDate.setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfPCell colMaxPiece = new PdfPCell(new Phrase("Max piece conso/opé"));
            colMaxPiece.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell colMinPiece = new PdfPCell(new Phrase("Mon piece conso/opé"));
            colMinPiece.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell colAvgPiece = new PdfPCell(new Phrase("Avg piece conso/opé"));
            colAvgPiece.setHorizontalAlignment(Element.ALIGN_CENTER);

            colMaxDate.setBackgroundColor(colorRed);
            colMaxPiece.setBackgroundColor(colorRed);
            colMinDate.setBackgroundColor(colorGreen);
            colMinPiece.setBackgroundColor(colorGreen);

            //add to table
            table.addCell(colTimeScale);
            table.addCell(colNum);
            table.addCell(colMaxDate);
            table.addCell(colMinDate);
            table.addCell(colAvgDate);
            table.addCell(colMaxPiece);
            table.addCell(colMinPiece);
            table.addCell(colAvgPiece);

            addDataToTable(data, table);
            doc.add(table);
            doc.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addDataToTable(Map<Date, java.util.List<IndicatorDTO>> data, PdfPTable table){
        int numop = 0; double days;
        for(Map.Entry<Date, java.util.List<IndicatorDTO>> entry : data.entrySet()){
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            table.addCell(dateFormat.format(entry.getKey()));
            if(entry.getValue() == null){
                for (int i = 0; i < 7; ++i){
                    table.addCell("0");
                }
            } else {
                double maxDay = 0, minDay = 0, avgDay = 0;
                double maxPiece = 0, minPiece = 0, avgPiece = 0;
                double day = 0, pieceConsos = 0;
                days = 0; numop = 0;
                int size = entry.getValue().size();
                System.out.println("[DEBUG] size : "+size);
                numop += size;
                DecimalFormat df = new DecimalFormat("#.##");
                for (IndicatorDTO ele : entry.getValue()) {
                    java.sql.Date end = java.sql.Date.valueOf(ele.getDateE());
                    java.sql.Date begin = java.sql.Date.valueOf(ele.getDateB());
                    day = (end.getTime() - begin.getTime()) / (24 * 60 * 60 * 1000);
                    pieceConsos += ele.getPieceConso();
                    days += day;
                    if (day > maxDay) {
                        maxDay = day;
                    }
                    if (day < minDay) {
                        minDay = day;
                    }
                    if (ele.getPieceConso() > maxPiece) {
                        maxPiece = ele.getPieceConso();
                    }
                    if (ele.getPieceConso() < minPiece) {
                        minPiece = ele.getPieceConso();
                    }

                }
                avgDay = days/size;
                System.out.println("[DEBUG] avg : " + avgDay + " days and size : " + days +" | " + size);
                avgPiece = pieceConsos/size;
                PdfPCell cellNum = new PdfPCell(new Phrase(String.valueOf(size)));

                PdfPCell cellMaxDay = new PdfPCell(new Phrase(String.valueOf(maxDay)));
                cellMaxDay.setBackgroundColor(colorRed);
                PdfPCell cellMaxPiece = new PdfPCell(new Phrase(String.valueOf(maxPiece)));
                cellMaxPiece.setBackgroundColor(colorRed);
                PdfPCell cellMinDay = new PdfPCell(new Phrase(String.valueOf(minDay)));
                cellMinDay.setBackgroundColor(colorGreen);
                PdfPCell cellMinPiece = new PdfPCell(new Phrase(String.valueOf(minPiece)));
                cellMinPiece.setBackgroundColor(colorGreen);
                PdfPCell cellAvgDay = new PdfPCell(new Phrase(df.format(avgDay)));
                PdfPCell cellAvgPiece = new PdfPCell(new Phrase(df.format(avgPiece)));
                table.addCell(cellNum);
                table.addCell(cellMaxDay);
                table.addCell(cellMinDay);
                table.addCell(cellAvgDay);
                table.addCell(cellMaxPiece);
                table.addCell(cellMinPiece);
                table.addCell(cellAvgPiece);
            }
        }
        table.addCell("Total");
        table.addCell(String.valueOf(numop));
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        System.out.println("[DEBUG] numop:" + numop);
    }

    public String getTitle(Map<String, String> idct){
        String from = idct.get("from");
        String to = idct.get("to");
        String btype = idct.get("btype");
        return "Rapport Indicateur de " + from + " a " + to + " \nType de dommage : " + btype.toUpperCase();
    }
}
