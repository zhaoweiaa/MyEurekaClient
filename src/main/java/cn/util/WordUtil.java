package cn.util;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.*;
import com.deepoove.poi.data.style.Style;
import com.deepoove.poi.data.style.TableStyle;
import com.deepoove.poi.util.BytePictureUtils;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * poi-tl 插件练习
 */
public class WordUtil {

    /**
     * 根据模板生成word文件
     */
    public void createWord() throws IOException, URISyntaxException {

//        String path = Thread.currentThread().getClass().getClassLoader().getResource("template.docx").toString();
        String path = this.getClass().getClassLoader().getResource("template.docx").getPath();

        XWPFTemplate template = XWPFTemplate.compile(path);

//        template.render(new HashMap<String,Object>(){{put("title","Poi-tl 模板引擎");}});

        template.render(dataMap());

        String pattern = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhh24mmss"));

        template.write(Files.newOutputStream(Paths.get(pattern + "temp.docx")));

        template.close();
    }

    private Map<String, Object> dataMap() throws IOException, URISyntaxException {
        return new HashMap<String, Object>(){{
           put("author", new TextRenderData("000000","Sayi卅一"));
           put("introduce","http://www.deepoove.com");
           put("link",new HyperLinkTextRenderData("website","http://www.deepoove.com"));

           Style customStyle = new Style();
            customStyle.setBold(true);
            customStyle.setColor("00FF00");
            customStyle.setFontFamily("微软雅黑");
            customStyle.setFontSize(14);
            customStyle.setItalic(true);
            customStyle.setStrike(true);
            customStyle.setUnderLine(true);
            put("customStyle", new TextRenderData("hello 赵伟,自定义文字样式，我要换行我要换行我要换行我要换行我\n要换行我要换行我要换行我要换行我要换行",customStyle));

            //插入本地图片
            put("localPicture", new PictureRenderData(350,240,this.getClass().getResource("./bd.jpg").getPath()));
            //网络图片
            put("uriPicture", new PictureRenderData(350,240,".jpg", BytePictureUtils.getUrlByteArray("https://www.baidu.com/img/bd_logo1.png")));

            RowRenderData rowRenderData = new RowRenderData();
            //设置表头
            rowRenderData.setRowData(Arrays.asList(new TextRenderData("FFFFFF","姓名"),new TextRenderData("FFFFFF","学历")));
            TableStyle tableStyle = new TableStyle();
            tableStyle.setBackgroundColor("0CFF19");
            tableStyle.setAlign(STJc.Enum.forString("center"));
            rowRenderData.setStyle(tableStyle);

            List<RowRenderData> listTable = Arrays.asList(
                    new RowRenderData(Arrays.asList(new TextRenderData("00ffcc", "张三"), new TextRenderData("00ffaa", "研究生"))),
                    new RowRenderData(Arrays.asList(new TextRenderData("00ffcc", "李四"), new TextRenderData("00ffaa", "博士"))),
                    new RowRenderData(Arrays.asList(new TextRenderData("00ffcc", "王五"), new TextRenderData("00ffaa", "博士后")))
            );
            put("table",new MiniTableRenderData(rowRenderData,listTable,"no data",MiniTableRenderData.WIDTH_A4_EXTEND_FULL));

            //列表样式示例
            List<TextRenderData> numList = Arrays.asList(
                    new TextRenderData("00ffdd", "Plug-in grammar"),
                    new TextRenderData("00ffdd", "Supports word text, header..."),
                    new TextRenderData("00ffdd", "Not just templates, but also style templates")
            );
            put("FMT_BULLET",new NumbericRenderData(NumbericRenderData.FMT_BULLET, numList));
            put("FMT_DECIMAL",new NumbericRenderData(NumbericRenderData.FMT_DECIMAL, numList));
            put("FMT_DECIMAL_PARENTHESES",new NumbericRenderData(NumbericRenderData.FMT_DECIMAL_PARENTHESES, numList));
            put("FMT_LOWER_LETTER",new NumbericRenderData(NumbericRenderData.FMT_LOWER_LETTER, numList));
            put("FMT_UPPER_LETTER",new NumbericRenderData(NumbericRenderData.FMT_UPPER_LETTER, numList));
            put("FMT_LOWER_ROMAN",new NumbericRenderData(NumbericRenderData.FMT_LOWER_ROMAN, numList));
            put("FMT_UPPER_ROMAN",new NumbericRenderData(NumbericRenderData.FMT_UPPER_ROMAN, numList));

            //渲染另一个word文档数据

//            Paths.get(String.valueOf(this.getClass().getResource("./segmentTemplate.docx")));
//            Path file = Files.createFile(Paths.get(this.getClass().getResource("./segmentTemplate.docx").toURI()));
//            DocxRenderData docxRenderData = new DocxRenderData(new File("~/segmentTemplage.docx"), new HashMap<String,String>(){{put("segmentContent","我是来自另一个文档的内容");}});
//            put("docx_world",docxRenderData);
        }};
    }
    //sample data
    private Map<String,Object> datas(){

        HashMap<String, Object> datas = new HashMap<>();

        datas.put("header","Deeply love what you love.");
        datas.put("name","Poi-tl");
        datas.put("time","2018-12-21");
        datas.put("what","Java Word 模板引擎：Minimal Microsoft word(docx) templating with {{template}} in Java." +
                "It works by expanding tags in a template using values provided in a JavaMap or JavaObject.");
        datas.put("author", new TextRenderData("000000", "赵伟"));
        datas.put("introduce","http://www.deepoove.com");

        datas.put("portrait", new PictureRenderData(60, 60, "src/main/resources/sayi.png"));

        RowRenderData header = new RowRenderData();
        List<TextRenderData> list = new ArrayList<>();
        list.add(new TextRenderData("FFFFFF", "Word处理解决方案"));
        list.add(new TextRenderData("FFFFFF", "是否跨平台"));
        list.add(new TextRenderData("FFFFFF", "易用性"));
        TableStyle tableStyle = new TableStyle();
        tableStyle.setBackgroundColor("ff9800");
        header.setRowData(list);header.setStyle(tableStyle);

        RowRenderData row0 = RowRenderData.build("Poi-tl", "纯Java组件，跨平台", "简单：模板引擎功能，并对POI进行了一些封装");
        RowRenderData row1 = RowRenderData.build("Apache Poi", "纯Java组件，跨平台", "简单，缺少一些功能的封装");
        RowRenderData row2 = RowRenderData.build("Freemarker", "XML操作，跨平台", "复杂，需要理解XML结构");
        RowRenderData row3 = RowRenderData.build("OpenOffice", "需要安装OpenOffice软件", "复杂，需要了解OpenOffice的API");
        RowRenderData row4 = RowRenderData.build("Jacob、winlib", "Windows平台", "复杂，不推荐使用");

        datas.put("solution_compare", new MiniTableRenderData(header, Arrays.asList(row0,row1,row2,row3,row4), MiniTableRenderData.WIDTH_A4_FULL));

        datas.put("feature", new NumbericRenderData(new ArrayList<TextRenderData>() {
            {
                add(new TextRenderData("Plug-in grammar, add new grammar by yourself"));
                add(new TextRenderData(
                        "Supports word text, local pictures, web pictures, table, list, header, footer..."));
                add(new TextRenderData("Templates, not just templates, but also style templates"));
            }
        }));
        return datas;
    }

    //简单示例
    public void simpleDocx() throws IOException {

        XWPFTemplate template = XWPFTemplate.compile("src/main/resources/simple.docx").render(datas());

        template.write(Files.newOutputStream(Paths.get("simple.docx"), StandardOpenOption.CREATE_NEW));

        template.close();
    }

    public static void main(String[] args) {
        try {
//            new WordUtil().createWord();
            new WordUtil().simpleDocx();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
