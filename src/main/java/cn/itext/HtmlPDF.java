package cn.itext;

import com.itextpdf.text.Document;
import com.itextpdf.text.FontProvider;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class HtmlPDF {

    private static final String HTML = "src/main/resources/template/pdf.html";

    private static final String FREEMARKER_HTML = "src\\main\\resources\\template\\";

    private static Configuration freemarkerConfig = null;
    static {
        freemarkerConfig = new Configuration();
        try {
//            freemarkerConfig.getTemplate(FREEMARKER_HTML, "UTF-8");
            freemarkerConfig.setDirectoryForTemplateLoading(Files.createFile(Paths.get(FREEMARKER_HTML)).toFile());
            freemarkerConfig.setDefaultEncoding("UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createHtmlPDF() throws Exception{
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, Files.newOutputStream(Paths.get("pdfdemo/hello-html.pdf"), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING));
        document.open();

        XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        fontProvider.registerDirectory("src/main/resources/font",true);
//        fontProvider.register("src/main/resources/font/msyh.ttf");

        XMLWorkerHelper.getInstance().parseXHtml(writer,document,Files.newInputStream(Paths.get(HTML)), Charset.forName("UTF-8"),fontProvider);
        document.close();
        document = null;
    }

    /**
     *
     * 使用freemarker 渲染pdf
     * @throws Exception
     */
    public static void createFreeMarkerHtmlPDF() throws Exception{

        //freemarker 渲染html 模板到输出流
        Template template = freemarkerConfig.getTemplate(FREEMARKER_HTML + "\\freemarker_template.html");
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("name","赵伟，会吼吼吼吼的猪, 大忽悠");
        StringWriter stringWriter = new StringWriter();
        template.process(dataMap,stringWriter);
        stringWriter.flush();

        template = null;
        dataMap = null;

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, Files.newOutputStream(Paths.get("pdfdemo/freemarker.pdf"), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING));
        document.open();
        XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        fontProvider.registerDirectory("src/main/resources/font",true);
        XMLWorkerHelper.getInstance().parseXHtml(
                writer,
                document,
                new ByteArrayInputStream(stringWriter.toString().getBytes()),
                null,
                Charset.forName("UTF-8"),
                fontProvider);

        document.close();
        writer.close();
        stringWriter.close();
        document = null;

    }

    /**
     * Flying Saucer-CSS高级特性支持
     */
    public static void createImgFreemarkerPDF() throws Exception{
        //输入模板数据到输出流
        Template template = freemarkerConfig.getTemplate(FREEMARKER_HTML + "\\freemarker_template.html");
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("name","终于显示中文了");
        StringWriter out = new StringWriter();
        template.process(dataMap,out);
        out.flush();

        template = null;
        dataMap = null;

        ITextRenderer iTextRenderer = new ITextRenderer();
        //设置字体目录
        ITextFontResolver fontResolver = iTextRenderer.getFontResolver();
        fontResolver.addFontDirectory("src/main/resources/font/",false);
        fontResolver.addFont("C:/Windows/Fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        fontResolver.addFont("C:/Windows/Fonts/simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        fontResolver.addFont("C:/Windows/Fonts/simkai.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        fontResolver.addFont("src/main/resources/font/simhei.ttf",BaseFont.NOT_EMBEDDED);

        iTextRenderer.setDocumentFromString(out.toString());
        iTextRenderer.layout();
        iTextRenderer.createPDF(Files.newOutputStream(Paths.get("pdfdemo/高级特性支持.pdf"),StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING));

        iTextRenderer = null;
        out.close();
    }

    public static void createFreeImg() throws  Exception{
        //输入模板数据到输出流
        Template template = freemarkerConfig.getTemplate(FREEMARKER_HTML + "\\freemarker_template_fs.html");
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("name","终于显示中文了");
        StringWriter out = new StringWriter();
        template.process(dataMap,out);
        out.flush();

        template = null;
        dataMap = null;

        ITextRenderer iTextRenderer = new ITextRenderer();
        //设置字体目录
        ITextFontResolver fontResolver = iTextRenderer.getFontResolver();
        fontResolver.addFontDirectory("src/main/resources/font/",false);
        fontResolver.addFont("C:/Windows/Fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        fontResolver.addFont("C:/Windows/Fonts/simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        fontResolver.addFont("C:/Windows/Fonts/simkai.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        fontResolver.addFont("src/main/resources/font/simhei.ttf",BaseFont.NOT_EMBEDDED);

        iTextRenderer.setDocumentFromString(out.toString());
        String path = HtmlPDF.class.getClassLoader().getResource("sayi.png").getPath();
        iTextRenderer.getSharedContext().setBaseURL("file://" + path);
        iTextRenderer.layout();
        iTextRenderer.createPDF(Files.newOutputStream(Paths.get("pdfdemo/高级特性支持22.pdf"),StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        iTextRenderer.createPDF(byteArrayOutputStream,true);

        ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(Files.newOutputStream(Paths.get("pdfdemo/freemarker.png"),StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING));


        iTextRenderer = null;
        out.close();
        imageOutputStream.close();
        imageOutputStream = null;
    }

    public static void main(String[] args) {
        try {
//            HtmlPDF.createHtmlPDF();
//            HtmlPDF.createFreeMarkerHtmlPDF();
//            HtmlPDF.createImgFreemarkerPDF();

            HtmlPDF.createFreeImg();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
