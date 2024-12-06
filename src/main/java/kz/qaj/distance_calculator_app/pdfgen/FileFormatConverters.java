/*
package kz.qaj.distance_calculator_app.pdfgen;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.builder.export.Exporters;
import net.sf.dynamicreports.jasper.builder.export.JasperXlsxExporterBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.exception.DRException;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class FileFormatConverters {


    public static byte[] byteArray(String format, JasperReportBuilder report) throws DRException {
        return switch (format) {
            case ("pdf") -> pdfResponseEntity(report);
            case ("xlsx") -> xlsxResponseEntity(report);
            default -> htmlResponseEntity(report);
        };
    }

    public static byte[] byteArray(
            ByteArrayOutputStream bas
    ) throws DRException {

        return xlsxResponseEntity(bas);
    }

    private static byte[] pdfResponseEntity(JasperReportBuilder report) throws DRException {
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        bas.reset();
        report.toPdf(bas);

        return bas.toByteArray();
    }

    private static byte[] xlsxResponseEntity(JasperReportBuilder report) throws DRException {
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        JasperXlsxExporterBuilder jrb = Exporters.xlsxExporter(bas)
                .setDetectCellType(true)
                .setIgnorePageMargins(true)
                .setWhitePageBackground(true)
                .setRemoveEmptySpaceBetweenRows(true)
                .setOnePagePerSheet(true);
                //.setMaxRowsPerSheet(100000);

        report.ignorePagination()
                .setColumnHeaderPrintWhenExpression(DynamicReports.exp.printInFirstPage())
                .toXlsx(jrb);

        return bas.toByteArray();
    }

    private static byte[] xlsxResponseEntity(ByteArrayOutputStream bas) {

        return bas.toByteArray();
    }

    private static byte[] htmlResponseEntity(JasperReportBuilder report) throws DRException {
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        report.setIgnorePagination(true).toHtml(bas);

        return bas.toByteArray();
    }

    public static ResponseEntity responseEntity(String name, String format, byte[] byteArray) throws UnsupportedEncodingException {
        String fileName = URLEncoder.encode(name, StandardCharsets.UTF_8);
        if (StringUtils.isEmpty(format)) {
            format = "html";
        }
        fileName = fileName + "." + format;

        HttpHeaders headers = new HttpHeaders();
        String value = MediaType.TEXT_HTML_VALUE;
        headers.add("content-disposition", "inline; filename*=UTF-8''" + fileName);

        switch (format) {
            case ("pdf") -> {
                value = MediaType.APPLICATION_PDF_VALUE;
                headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            }
            case ("xlsx") -> value = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        }

        headers.setContentType(MediaType.parseMediaType(value));

        System.gc();

        return new ResponseEntity(byteArray, headers, HttpStatus.OK);
    }

    */
/*public ResponseEntity downloadZipFile(HttpServletResponse response, MutableMap<String, byte[]> listOfFileNames) {
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=download.zip");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/zip");
        headers.add("Content-Disposition", "attachment; filename=download.zip");

        try {
            new ZipOutputStream(response.getOutputStream()).use {zipOutputStream ->
                for (fileName in listOfFileNames) {
                    //val fileSystemResource = FileSystemResource(fileName.key)
                    val zipEntry = ZipEntry(fileName.key)
                    zipEntry.size = fileName.value.size.toLong()
                    zipEntry.time = System.currentTimeMillis()
                    zipOutputStream.putNextEntry(zipEntry)
                    StreamUtils.copy(fileName.value.inputStream(), zipOutputStream)
                    zipOutputStream.closeEntry()
                }
                zipOutputStream.finish()
                response.status = 200
            }

            return new ResponseEntity(byteArray, headers, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*//*

}
*/
