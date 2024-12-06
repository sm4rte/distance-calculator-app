/*
package kz.qaj.distance_calculator_app.controller;

import com.google.zxing.WriterException;
import kz.qaj.distance_calculator_app.pdfgen.FileFormatConverters;
import lombok.extern.slf4j.Slf4j;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Objects;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;

@Slf4j
@RestController
public class FileGeneratorController {

    @PostMapping("/generate-file")
    public ResponseEntity<?> generateFile(
            @RequestParam(required = false, defaultValue = "kz") String lang,
            @RequestParam String startCityName,
            @RequestParam String endCityName,
            @RequestParam BigDecimal calculatedDistance) {
        try {
            log.info("Request lang={}, startCityName={}, endCityName={}, calculatedDistance={}", lang, startCityName, endCityName, calculatedDistance);
            String resultCode = "kz";
            if (StringUtils.hasText(lang) &&
                    (lang.equalsIgnoreCase("kz") || lang.equalsIgnoreCase("ru"))
            ) {
                resultCode = lang;
            }
            byte[] byteArray = generateReport(transferDto, resultCode);
            return FileFormatConverters.responseEntity("report-transfer", "pdf", byteArray);
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    private byte[] generateReport(
            TransferDto transferDto,
            String languageCode
    ) throws DRException, IOException, WriterException {

        JasperReportBuilder report = reportBuilder(transferDto, languageCode);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList(transferDto));
        report.setDataSource(dataSource);

        return FileFormatConverters.byteArray("pdf", report);
    }

    private JasperReportBuilder reportBuilder(
            TransferDto transferDto,
            String languageCode
    ) throws IOException, WriterException {
        TemplatesStyle templateStyle = new TemplatesStyleBuilder()
                .setFontSize(14)
                .setColumnTitleFontSize(14)
                .setSymmetricalPadding(2)
                .setLeftPadding(30)
                .setRightPadding(30)
                .build();

        String strRequestId;
        if (Objects.nonNull(transferDto.getRequestId())) {
            if (transferDto.getRequestId() < 1000) {
                String literalZero = "0000" + transferDto.getRequestId();
                strRequestId = literalZero.substring(literalZero.length() - 4);
            } else {
                strRequestId = String.valueOf(transferDto.getRequestId());
            }
        } else {
            strRequestId = "-";
        }

        String headText = "Өтініш № ".concat(strRequestId);
        String text = String.format("\tМен, %s, %s МТНБ-мен көлік құралының шотындағы %s сомадағы " +
                        "ақшалай қаражатты %s МТНБ-не ауыстыруды сұраймын.",
                transferDto.getOwnerName(),
                transferDto.getFromLicencePlate(),
                transferDto.getBalance(),
                transferDto.getToLicencePlate()
        );
        String dateString = "Күні: ";
        String timeText = "\nУақыты: ";
        String signText = "Қолы: ";

        if (languageCode.equalsIgnoreCase("ru")) {
            headText = "Заявление № ".concat(strRequestId);
            text = String.format("\tЯ, %s, прошу перенести на ГРНЗ %s денежные средства в сумме %s," +
                            " находящиеся на счету транспортного средства с ГРНЗ %s.",
                    transferDto.getOwnerName(),
                    transferDto.getToLicencePlate(),
                    transferDto.getBalance(),
                    transferDto.getFromLicencePlate()
            );
            dateString = "Дата: ";
            timeText = "\nВремя: ";
            signText = "Подпись: ";
        }

        InputStream png = new ByteArrayInputStream(qrCodeGeneratorService.generateQRCode(
                "https://kaztoll.kz/documents/transfer/".concat(
                        String.valueOf(transferDto.getBalanceId())
                )
        ));

        return report()
                .setTemplate(templateStyle.reportTemplate())
                .setPageFormat(PageType.A4, PageOrientation.PORTRAIT)
                .title(
                        cmp.verticalGap(50)
                )
                .detail(
                        cmp.verticalGap(50),
                        cmp.text(headText).setStyle(templateStyle.centeredBoldStyle()),
                        cmp.verticalGap(25),
                        cmp.text(text).setStyle(templateStyle.justifiedStyle()),
                        cmp.verticalGap(50),
                        cmp.horizontalList()
                                .add(
                                        cmp.verticalList()
                                                .add(
                                                        cmp.text(dateString
                                                                .concat(transferDto.getCreatedDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                                                                .concat(timeText.concat(transferDto.getCreatedDate().format(DateTimeFormatter.ofPattern("HH:mm"))))
                                                        ).setStyle(templateStyle.fontSize())
                                                ),
                                        cmp.verticalList()
                                                .add(
                                                        cmp.text(signText).setStyle(templateStyle.fontSize()),
                                                        cmp.image(png).setStyle(templateStyle.fontSize())
                                                )
                                )
                );
    }


}
*/
