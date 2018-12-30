package cn.util.payment;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.TextRenderData;
import com.deepoove.poi.data.style.Style;
import com.deepoove.poi.data.style.TableStyle;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class PaymentExample {

    PaymentData paymentData = new PaymentData();

    Style headTextStyle = new Style();

    TableStyle headStyle = new TableStyle();

    TableStyle rowStyle = new TableStyle();

    public PaymentExample() {

        headTextStyle.setFontFamily("Hei");
        headTextStyle.setFontSize(9);
        headTextStyle.setColor("7F7F7F");

        headStyle.setBackgroundColor("F2F2F2");
        headStyle.setAlign(STJc.CENTER);

        rowStyle.setBackgroundColor("00ddee");
        rowStyle.setAlign(STJc.CENTER);

        paymentData.setNO("KB.6890451");
        paymentData.setID("ZHANG_SAN_091");
        paymentData.setTaitou("深圳XX家装有限公司");
        paymentData.setConsignee("丙丁");
        paymentData.setSubtotal("8000");
        paymentData.setTax("600");
        paymentData.setTransform("120");
        paymentData.setOther("250");
        paymentData.setUnpay("6600");
        paymentData.setTotal("共：7200");

        RowRenderData header = RowRenderData.build(
                new TextRenderData("日期",headTextStyle),
                new TextRenderData("订单编号",headTextStyle),
                new TextRenderData("销售代表",headTextStyle),
                new TextRenderData("离岸价",headTextStyle),
                new TextRenderData("发货方式",headTextStyle),
                new TextRenderData("条款",headTextStyle),
                new TextRenderData("税号",headTextStyle)
        );

        header.setStyle(headStyle);

        RowRenderData row = RowRenderData.build("2018-06-12","SN18090","李四", "5000元", "快递", "附录A", "T11090");
        row.setStyle(rowStyle);

        MiniTableRenderData miniTableRenderData = new MiniTableRenderData(header, Arrays.asList(row), MiniTableRenderData.WIDTH_A4_NARROW_FULL);
        miniTableRenderData.setStyle(headStyle);

        paymentData.setOrder(miniTableRenderData);

        DetailData detailData = new DetailData();

        RowRenderData row1 = RowRenderData.build("4", "墙纸", "书房+卧室", "1500", "/", "400", "1600");
        row1.setStyle(rowStyle);
        detailData.setGoods(Arrays.asList(row1,row1,row1));

        RowRenderData row2 = RowRenderData.build("油漆工", "2", "200", "400");
        row2.setStyle(rowStyle);
        detailData.setLabors(Arrays.asList(row2,row2,row2,row2));

        paymentData.setDetailTable(detailData);

    }

    public void generatePaymentDocx() throws IOException {

        Configure configure = Configure.newBuilder().customPolicy("detail_table", new DetailTablePolicy()).build();

        XWPFTemplate temp = XWPFTemplate.compile("src/main/resources/paymentTemplate.docx",configure).render(paymentData);

        temp.write(Files.newOutputStream(Paths.get("付款通知书.docx"), StandardOpenOption.CREATE_NEW));

        temp.close();
    }
    public static void main(String[] args) {
        try {
            new PaymentExample().generatePaymentDocx();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
