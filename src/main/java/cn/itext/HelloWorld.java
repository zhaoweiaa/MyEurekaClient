package cn.itext;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import javax.print.Doc;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * itext hello world
 */
public class HelloWorld {

//   static {
//        try {
//            BaseFont baseFont = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    //Java生成pdf文件
    public static void helloWorld() throws Exception{
        Document document = new Document();


        PdfWriter pdfWriter = PdfWriter.getInstance(document, Files.newOutputStream(Paths.get("pdfdemo/hello.pdf"), StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING));


        document.open();

        document.add(new Paragraph("hello world , welcome zhaowei"));
        document.add(new Paragraph("hoahoodsssssssssss"));

        //使用Windows系统字体(TrueType)(mysh.ttf为微软雅黑常规字体)
        BaseFont winFont = BaseFont.createFont("C:/Windows/Fonts/msyh.ttf",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);

        //使用com-itextpdf-itext-asian jar
        BaseFont baseFont = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont, 12, Font.BOLD);
        //指定资源位置
        BaseFont resFont = BaseFont.createFont("src/main/resources/font/msyhbd.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

        document.add(new Paragraph("我是赵伟！itext-asian解决中文显示问题",font));

        document.add(new Paragraph("我是赵伟！Windows自带字体解决中文显示问题",new Font(winFont)));

        document.add(new Paragraph("我是赵伟！下载字体文件解决中文显示问题",new Font(resFont)));

        document.close();

        pdfWriter.close();

        document = null;
    }

    /**
     * 设置pdf文档对象属性
     */
    public static void setAttributePDF() throws Exception {

        Document document = new Document();

        PdfWriter.getInstance(document,Files.newOutputStream(Paths.get("pdfdemo/setAttr.pdf"),StandardOpenOption.CREATE_NEW,StandardOpenOption.TRUNCATE_EXISTING));
        document.open();
        document.add(new Paragraph("我是赵伟!", new Font(FontClass.getBastFont())));

        document.addAuthor("zhaowei");
        document.addCreationDate();
        document.addCreator("cn.jxzu.zw");
        document.addTitle("Set Attribute Example");
        document.addSubject("An example to show how attributes can be added to pdf files.");
        document.close();
        document= null;
    }

    /**
     * 插入图片
     */
    public static void insertPicPDF() throws Exception{
        Document document = new Document();
        PdfWriter.getInstance(document,Files.newOutputStream(Paths.get("pdfdemo/insertPic.pdf"),StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING));
        document.open();

        Image img2 = Image.getInstance(new URL("http://www.eclipse.org/xtend/images/java8_logo.png"));
        document.add(img2);
        document.add(new Paragraph("网络图片：http://www.eclipse.org/xtend/images/java8_logo.png",new Font(FontClass.getBastFont())));

        //另起一页
        document.newPage();
        for (int i = 1; i < 34; i++) {

            document.top(20f);

            Paragraph title = new Paragraph("卡哇伊 莱昂纳德 " + i, new Font(FontClass.getBastFont()));
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);

            Image img = Image.getInstance("src/main/resources/pdf/" + i + ".jpg");
//            img.setAbsolutePosition(100f,550f);
            img.scaleAbsolute(450,420);
            img.setAlignment(Image.ALIGN_CENTER);
//            img.setAlignment(Image.ALIGN_JUSTIFIED_ALL);
            img.setScaleToFitHeight(true);
            img.setScaleToFitLineWhenOverflow(true);
            img.setSmask(true);
            document.add(img);

            document.newPage();
        }



        document.close();
        document = null;

    }


    public static void createTablePDF() throws Exception{
        Document document = new Document();
        PdfWriter.getInstance(document,Files.newOutputStream(Paths.get("pdfdemo/createTable.pdf"),StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING));
        document.open();

        PdfPTable pdfPTable = new PdfPTable(3);

        pdfPTable.setWidthPercentage(100);
        pdfPTable.setSummary("my table");
        pdfPTable.setSpacingBefore(10);
        pdfPTable.setSpacingAfter(10);

        float[] columnsWiths = {1f,1f,1f};
        pdfPTable.setWidths(columnsWiths);

        PdfPCell pdfPCell1 = new PdfPCell(new Paragraph("cell 1"));
        pdfPCell1.setBorderColor(BaseColor.BLUE);
        pdfPCell1.setPadding(10f);
        pdfPCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPCell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell pdfPCell2 = new PdfPCell(new Paragraph("cell 2"));
        pdfPCell2.setBorderColor(BaseColor.GREEN);
        pdfPCell2.setPadding(10f);
        pdfPCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPCell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell pdfPCell3 = new PdfPCell(new Paragraph("cell 3"));
        pdfPCell3.setBorderColor(BaseColor.PINK);
        pdfPCell3.setPadding(10f);
        pdfPCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPCell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

        pdfPCell1.setBackgroundColor(BaseColor.MAGENTA);
        pdfPCell2.setBackgroundColor(BaseColor.MAGENTA);
        pdfPCell3.setBackgroundColor(BaseColor.MAGENTA);

        pdfPTable.addCell(pdfPCell1);
        pdfPTable.addCell(pdfPCell2);
        pdfPTable.addCell(pdfPCell3);

        for (int i = 0; i < 17 ; i++) {
            pdfPTable.addCell(new Paragraph("赵伟"+i,new Font(FontClass.getBastFont())));
        }
//        pdfPTable.setExtendLastRow(false,false);
        pdfPTable.setHorizontalAlignment(PdfPTable.ALIGN_CENTER);

        document.add(pdfPTable);


        document.close();
        document = null;
    }

    public static void createListPDF() throws Exception{
        Document document = new Document();
        PdfWriter.getInstance(document,Files.newOutputStream(Paths.get("pdfdemo/createList.pdf"),StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING));
        document.open();

        List list = new List(List.ORDERED);

        list.add(new ListItem("item 1"));
        list.add(new ListItem("item 2"));
        list.add(new ListItem("item 3"));
        list.add(new ListItem("item 4"));
        document.add(list);

        List unorderedList = new List(List.UNORDERED);
        unorderedList.add(new ListItem("Item 1"));
        unorderedList.add(new ListItem("Item 2"));
        unorderedList.add(new ListItem("Item 3"));
        document.add(unorderedList);

        RomanList romanList = new RomanList();
        romanList.add(new ListItem("Item 1"));
        romanList.add(new ListItem("Item 2"));
        romanList.add(new ListItem("Item 3"));
        document.add(romanList);

        GreekList greekList = new GreekList();
        greekList.add(new ListItem("Item 1"));
        greekList.add(new ListItem("Item 2"));
        greekList.add(new ListItem("Item 3"));
        document.add(greekList);

        ZapfDingbatsList zapfDingbatsList = new ZapfDingbatsList(43, 30);
        zapfDingbatsList.add(new ListItem("Item 1"));
        zapfDingbatsList.add(new ListItem("Item 2"));
        zapfDingbatsList.add(new ListItem("Item 3"));
        document.add(zapfDingbatsList);

        List list1 = new List(List.UNORDERED);
        list1.add(new ListItem("item 1"));

        List subList = new List(List.ORDERED, List.UPPERCASE, 30);

        subList.setListSymbol(new Chunk("",FontFactory.getFont(FontFactory.HELVETICA,6)));
        subList.add("A");
        subList.add("B");
        list1.add(subList);

        list1.add(new ListItem("item 2"));

        subList = new List(List.ORDERED, List.UPPERCASE, 30);
        subList.setListSymbol(new Chunk("",FontFactory.getFont(FontFactory.HELVETICA, 6)));
        subList.add("C");
        subList.add("D");
        list1.add(subList);

        document.add(list1);


        document.close();
        document = null;
    }

    public static void setFontStylePDF() throws Exception{

        Font blueFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new CMYKColor(255, 0, 0, 0));
        Font redFont = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, new CMYKColor(0, 255, 0, 0));
        Font yellowFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD, new CMYKColor(0, 0, 255, 0));

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, Files.newOutputStream(Paths.get("pdfdemo/font.pdf"), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING));
        //文档加密
        writer.setEncryption("password".getBytes(),"zhaowei".getBytes(), PdfWriter.ALLOW_PRINTING,PdfWriter.ENCRYPTION_AES_128);



        document.open();

        document.add(new Paragraph("some colored paragraph",blueFont));

        Chapter chapter = new Chapter(new Paragraph("Chapter 1", yellowFont), 0);
        chapter.setNumberDepth(0);

        Section section_title = chapter.addSection(new Paragraph("Section Title", redFont));
        section_title.add(new Paragraph("section content", blueFont));
        document.add(chapter);


        document.close();
        writer.close();
        document = null;
        writer = null;

    }



    public static void main(String[] args) {

        try {
//            HelloWorld.helloWorld();
//            HelloWorld.setAttributePDF();
//            HelloWorld.insertPicPDF();
//            HelloWorld.createListPDF();
//            HelloWorld.createTablePDF();
            HelloWorld.setFontStylePDF();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 提供基础字体
     */
    private static class FontClass{
        public FontClass(){}

        public static BaseFont getBastFont(){
            try {
                return BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


}
