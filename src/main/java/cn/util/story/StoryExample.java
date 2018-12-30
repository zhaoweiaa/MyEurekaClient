package cn.util.story;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.DocxRenderData;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.util.BytePictureUtils;
import com.sun.nio.file.ExtendedCopyOption;
import com.sun.nio.file.ExtendedOpenOption;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class StoryExample {

    private StoryDate storyDate;

    public StoryExample() {

        storyDate = new StoryDate();

        storyDate.setStoryName("今天开始，和过去的自己说再见");
        storyDate.setStoryAuthor("逸遥");
        storyDate.setStorySource("阳光灿烂的日子");
        storyDate.setSummary("你现在每一次的后悔遗憾，都有一个过去讨厌的自己。如果你对现在的自己不满意，那就勇敢地对过去种种讨厌的行为say-goodbye吧。很快，你就会发现，一个人见人爱的自己！");

        List<SegmentData> segments = new ArrayList<>();

        SegmentData segment1 = new SegmentData();
        segment1.setTitle("经常抱怨的自己");
        segment1.setContent("每个人生活得都不容易，经常向别人抱怨的人，说白了就是把对方当做“垃圾场”，你一股脑地将自己的埋怨与不满倒给别人，自己倒是爽了，你有考虑过对方的感受吗？对方的脸上可能一笑了之，但是心里可能有一万只草泥马奔腾而过。");
        segment1.setPicture(new PictureRenderData(510,402,"src/main/resources/story/image1.jpg"));
        segments.add(segment1);

        SegmentData segment2 = new SegmentData();
        segment2.setTitle("拖拖拉拉的自己");
        segment2.setContent("能够今天做完的事情，不要拖到明天，你的事情没有任何人有义务去帮你做；不要做“宅男”、不要当“宅女”，放假的日子约上三五好友出去转转；经常动手做家务，既能分担伴侣的负担，又有一个干净舒适的环境何乐而不为呢？");
        segment2.setPicture(new PictureRenderData(510,510,"src/main/resources/story/image2.jpeg"));
        segments.add(segment2);

        SegmentData s3 = new SegmentData();
        s3.setTitle("斤斤计较的自己");
        s3.setContent("人生在世，真的没必要斤斤计较：和亲人计较，即使赢了也会伤了感情；和朋友计较，可能你们的友谊到此就结束了；和陌生人计较，别人会记着你一辈子。少了斤斤计较，你的生活也会豁然开朗。");
        s3.setPicture(new PictureRenderData(510, 384, "src/main/resources/story/image3.jpeg"));
        segments.add(s3);

        SegmentData s4 = new SegmentData();
        s4.setTitle("有公主病的自己");
        s4.setContent("生来就是公主命的人真的很少，但是有公主病的人却越来越多。不要幻想爱情和面包兼得，别人给了你爱情，你也应该自己去挣买面包的钱，你以为人人都能嫁给“富二代”么？你以为你不开心，就要有人陪着你难过么？你爱自己没错，可是你不能奢求别人来替你爱自己。");
        s4.setPicture(new PictureRenderData(510, 510, "src/main/resources/story/image4.jpeg"));
        segments.add(s4);

        SegmentData s5 = new SegmentData();
        s5.setTitle("视钱如命的自己");
        s5.setContent("我承认，钱确实能够给我带来快乐的感觉，钱也能让我有高人一等的优越感。但是，物质的充裕填补不了精神的空缺、再多的金钱也买不来健康的身体、再高的价格也有交换不了的感情。爱钱没有错，可是这绝不是你不择手段、牺牲健康的借口。");
        s5.setPicture(new PictureRenderData(510, 412, "src/main/resources/story/image5.jpeg"));
        segments.add(s5);

        SegmentData s6 = new SegmentData();
        s6.setTitle("毒舌的自己");
        s6.setContent("良言一句暖三冬，恶语伤人六月寒，不要以为你的毒舌是幽默，不分场合和对象的毒舌，不仅伤害了他人，也暴露了自己的无知。你有你的犀利，别人也有不容侵犯的骄傲，毒舌的人碰到谁，谁都想给他一巴掌。");
        segments.add(s6);

        DocxRenderData docxRenderData = new DocxRenderData(new File("src/main/resources/story/segment.docx"), segments);

        storyDate.setSegment(docxRenderData);

    }

    public void createStoryDcox() throws IOException {
        XWPFTemplate template = XWPFTemplate.compile("src/main/resources/story/story.docx").render(storyDate);
        template.write(Files.newOutputStream(Paths.get("out_story.docx"), StandardOpenOption.CREATE_NEW));
        template.close();
    }

    public static void main(String[] args) {
        try {
//            new StoryExample().createStoryDcox();
//            InputStream in = BytePictureUtils.getUrlPictureStream("src/main/resources/story/image1.jpg");
//            BytePictureUtils.toByteArray()
            Files.copy(Paths.get("src/main/resources/story/image1.jpg"),Paths.get("out_img1.jpg"), StandardCopyOption.COPY_ATTRIBUTES,StandardCopyOption.REPLACE_EXISTING);
            Files.copy(Files.newInputStream(Paths.get("src/main/resources/story/image2.jpeg"),StandardOpenOption.READ), Paths.get("out_image1.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
