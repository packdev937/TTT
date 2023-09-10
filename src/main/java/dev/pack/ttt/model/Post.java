package dev.pack.ttt.model;

import dev.pack.ttt.notion.model.Block;
import dev.pack.ttt.notion.model.Block.Annotations;
import dev.pack.ttt.notion.model.Block.RichText;
import dev.pack.ttt.notion.model.Block.RichTextWrapper;
import java.time.LocalDate;
import java.util.List;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@ToString
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    private Content content;
    private List<Block> blocks;
    private int visibility;
    private int category;
    private String slogan;
    private String tag;
    private LocalDate publishedDate;
    private StringBuilder html;

    public Post(Content content, List<Block> blocks) {
        this.content = content;
        this.blocks = blocks;
        this.html = convertToHTML();
    }


    public StringBuilder convertToHTML() {
        log.info("Start convertToHTML");
        StringBuilder htmlBuilder = new StringBuilder();

        for (Block block : blocks) {
            RichTextWrapper richTextWrapper = getNonNullRichTextWrapper(block);
            if (richTextWrapper != null) {
                for (RichText richText : richTextWrapper.getRichText()) {
                    convertRichTextToHTML(richText, htmlBuilder);
                }
            }
        }
        return htmlBuilder;
    }

    private RichTextWrapper getNonNullRichTextWrapper(Block block) {
        if (block.getToDo() != null) {
            return block.getToDo();
        } else if (block.getNumberedListItem() != null) {
            return block.getNumberedListItem();
        } else if (block.getParagraph() != null) {
            return block.getParagraph();
        } else if (block.getToggle() != null) {
            return block.getToggle();
        } else if (block.getQuote() != null) {
            return block.getQuote();
        } else if (block.getCallout() != null) {
            return block.getCallout();
        }
        return null;
    }

    private void convertRichTextToHTML(RichText richText, StringBuilder htmlBuilder) {
        if (richText != null) {
            String content = richText.getText().getContent();
            Annotations annotations = richText.getAnnotations();

            if (annotations != null) {
                if (annotations.getBold()) {
                    content = "**" + content + "**";
                }
                if (annotations.getItalic()) {
                    content = "*" + content + "*";
                }
                if (annotations.getStrikethrough()) {
                    content = "~~" + content + "~~";
                }
                if (annotations.getUnderline()) {
                    content = "<u>" + content + "</u>";
                }
                if (annotations.getCode()) {
                    content = "`" + content + "`";
                }
            }

            switch (richText.getType()) {
                case "heading1":
                    htmlBuilder.append("<h1>").append(content).append("</h1>");
                    break;
                case "heading2":
                    htmlBuilder.append("<h2>").append(content).append("</h2>");
                    break;
                // 다른 유형의 머리글에 대한 케이스도 여기에 추가할 수 있습니다.
                default:
                    htmlBuilder.append("");
                    break;
            }
        }
    }
}
