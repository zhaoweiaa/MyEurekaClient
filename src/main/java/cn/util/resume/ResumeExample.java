package cn.util.resume;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.DocxRenderData;
import com.deepoove.poi.data.NumbericRenderData;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.data.TextRenderData;
import com.deepoove.poi.data.style.Style;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResumeExample {

    private ResumeData resumeData;

    public ResumeExample() {

        this.resumeData = new ResumeData();

        resumeData.setPortrait(new PictureRenderData(100,100,"src/main/resources/resume/sayi.png"));
        resumeData.setName("zhaowei");
        resumeData.setJob("Java研发工程师");
        resumeData.setPhone("10010");
        resumeData.setSex("男");
        resumeData.setProvince("浙江宁波");
        resumeData.setBirthday("2018.08.19");
        resumeData.setEmail("adasai90@gmail.com");
        resumeData.setAddress("浙江省杭州市西湖区古荡1号");
        resumeData.setEnglish("CET6 620");
        resumeData.setUniversity("美国斯坦福大学");
        resumeData.setProfession("生命工程");
        resumeData.setEducation("学士");
        resumeData.setRank("班级排名 1/36");
        resumeData.setHobbies("音乐、画画、乒乓球、旅游、读书\nhttps://github.com/Sayi");

        TextRenderData textRenderData = new TextRenderData("SpringBoot、SprigCloud、Mybatis");
        Style style = new Style();
        style.setFontSize(10);
        style.setColor("7F7F7F");
        style.setFontFamily("微软雅黑");
        textRenderData.setStyle(style);

        resumeData.setStack(new NumbericRenderData(Arrays.asList(textRenderData,textRenderData,textRenderData)));

        List<ExperienceData> experiences = new ArrayList<>();

        ExperienceData data1 = new ExperienceData();
        data1.setCompany("香港微软");
        data1.setDepartment("Office 事业部");
        data1.setTime("2001.07-2020.06");
        data1.setPosition("BUG工程师");
        textRenderData = new TextRenderData("负责生产BUG，然后修复BUG，同时有效实施招聘行为");
        textRenderData.setStyle(style);
        data1.setResponsibility(new NumbericRenderData(Arrays.asList(textRenderData,textRenderData)));
        experiences.add(data1);

        ExperienceData data2 = new ExperienceData();

        data2.setCompany("自由职业");
        data2.setDepartment("OpenSource 项目组");
        data2.setTime("2015.07-2020.06");
        data2.setPosition("研发工程师");
        textRenderData = new TextRenderData("开源项目的迭代和维护工作");
        textRenderData.setStyle(style);
        TextRenderData textRenderData1 = new TextRenderData("持续集成、Swagger文档等工具调研");
        textRenderData1.setStyle(style);
        data2.setResponsibility(new NumbericRenderData(Arrays.asList(textRenderData,textRenderData1)));

        experiences.add(data2);
        experiences.add(data1);
        experiences.add(data2);

        resumeData.setExperience(new DocxRenderData(new File("src/main/resources/resume/segment.docx"),experiences));
    }

    public void createResumeDocx() throws IOException {
        XWPFTemplate template = XWPFTemplate.compile("src/main/resources/resume/简历.docx").render(resumeData);
        template.write(Files.newOutputStream(Paths.get("我的简历.docx"), StandardOpenOption.CREATE_NEW));
        template.close();
    }

    public static void main(String[] args) {
        try {
            new ResumeExample().createResumeDocx();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
