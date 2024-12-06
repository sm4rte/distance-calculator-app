/*
package kz.qaj.distance_calculator_app.pdfgen;

import net.sf.dynamicreports.report.builder.ReportTemplateBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizerBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.constant.WhenNoDataType;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.template;

public class TemplatesStyle {

    private final int fontSize;
    private final int columnTitleFontSize;
    private final int reportTitleFontSize;
    private final int topPadding;
    private final int bottomPadding;
    private final int leftPadding;
    private final int rightPadding;
    //private final StyleBuilder rootStyle;

    public TemplatesStyle(int fontSize, int columnTitleFontSize, int reportTitleFontSize, int topPadding, int bottomPadding, int leftPadding, int rightPadding) {
        this.fontSize = fontSize;
        this.columnTitleFontSize = columnTitleFontSize;
        this.reportTitleFontSize = reportTitleFontSize;
        this.topPadding = topPadding;
        this.bottomPadding = bottomPadding;
        this.leftPadding = leftPadding;
        this.rightPadding = rightPadding;
        //this.rootStyle = rootStyle();
    }

    private StyleBuilder rootStyle() {
        return stl.style()
                .setFontSize(this.fontSize)
                .setTopPadding(this.topPadding)
                .setBottomPadding(this.bottomPadding)
                .setLeftPadding(this.leftPadding)
                .setRightPadding(this.rightPadding);
    }

    public StyleBuilder fontSize(Integer fontSize) {
        return stl.style(rootStyle())
                .setFontSize(fontSize);
    }

    public StyleBuilder leftStyle() {
        return stl.style(rootStyle())
                .setTextAlignment(HorizontalTextAlignment.LEFT, VerticalTextAlignment.MIDDLE);
    }

    public StyleBuilder rightStyle() {
        return stl.style(rootStyle())
                .setTextAlignment(HorizontalTextAlignment.RIGHT, VerticalTextAlignment.MIDDLE);
    }

    public StyleBuilder centeredStyle() {
        return stl.style(rootStyle())
                .setTextAlignment(HorizontalTextAlignment.CENTER, VerticalTextAlignment.MIDDLE);
    }

    public StyleBuilder boldStyle() {
        return stl.style(rootStyle())
                .bold();
    }

    public StyleBuilder italicStyle() {
        return stl.style(rootStyle())
                .italic();
    }

    public StyleBuilder leftBoldStyle() {
        return stl.style(leftStyle())
                .bold();
    }

    public StyleBuilder rightBoldStyle() {
        return stl.style(rightStyle())
                .bold();
    }

    public StyleBuilder centeredBoldStyle() {
        return stl.style(centeredStyle())
                .bold();
    }

    private StyleBuilder columnStyle() {
        return stl.style(rootStyle()).setVerticalTextAlignment(VerticalTextAlignment.MIDDLE);
    }

    private StyleBuilder columnTitleStyle() {
        return stl.style(columnStyle())
                .setFontSize(this.columnTitleFontSize)
                .setBorder(stl.pen1Point())
                .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
                .setBackgroundColor(Color.LIGHT_GRAY)
                .bold();
    }

    private StyleBuilder reportTitleFontSize() {
        return stl.style(centeredBoldStyle())
                .setFontSize(this.reportTitleFontSize);
    }

    private StyleBuilder groupStyle() {
        return stl.style(boldStyle())
                .setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);
    }

    private StyleBuilder subtotalStyle() {
        return stl.style(boldStyle())
                .setTopBorder(stl.pen1Point());
    }

    @Deprecated
    public DateTimeFormatter defaultFormatter() {
        return DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    }

    public StyleBuilder crosstabGroupStyle() {
        return stl.style(columnTitleStyle());
    }

    public StyleBuilder crosstabGroupTotalStyle() {
        return stl.style(columnTitleStyle())
                .setBackgroundColor(new Color(170, 170, 170));
    }

    public StyleBuilder crosstabGrandTotalStyle() {
        return stl.style(columnTitleStyle())
                .setBackgroundColor(new Color(140, 140, 140));
    }

    public StyleBuilder crosstabCellStyle() {
        return stl.style(columnStyle())
                .setBorder(stl.pen1Point());
    }

    public TableOfContentsCustomizerBuilder tableOfContentsCustomizer() {
        return tableOfContentsCustomizer()
                .setHeadingStyle(0, stl.style(rootStyle()).bold());
    }

    public ReportTemplateBuilder reportTemplate() {
        return template()
                .setLocale(new Locale("ru", "KZ"))
                .setColumnStyle(columnStyle())
                .setColumnTitleStyle(columnTitleStyle())
                .setGroupStyle(groupStyle())
                .setGroupTitleStyle(groupStyle())
                .setSubtotalStyle(subtotalStyle())
                .highlightDetailEvenRows()
                .crosstabHighlightEvenRows()
                .setCrosstabGroupStyle(crosstabGroupStyle())
                .setCrosstabGroupTotalStyle(crosstabGroupTotalStyle())
                .setCrosstabGrandTotalStyle(crosstabGrandTotalStyle())
                .setCrosstabCellStyle(crosstabCellStyle())
                .setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL);
                //.setTableOfContentsCustomizer(tableOfContentsCustomizer());
    }

    private ComponentBuilder<?, ?> dynamicReportsComponent() {
        return cmp.horizontalList(
                cmp.image(TemplatesStyle.class.getResource("/static/img/bitumen_logo.png")).setFixedWidth(120).setStyle(stl.style().setLeftPadding(5)));
    }

    public ComponentBuilder<?, ?> createTitleComponent(String label) {

        HorizontalListBuilder hList =
                cmp.horizontalList()
                        .add(cmp.text(label)
                                .setStyle(reportTitleFontSize())
                                .setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT)
                        )
                        .add(cmp.verticalGap(10));

        return cmp.horizontalList().add(
                        dynamicReportsComponent(),
                        hList
                );
    }
}


*/
