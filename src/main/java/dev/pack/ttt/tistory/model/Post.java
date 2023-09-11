package dev.pack.ttt.tistory.model;

import dev.pack.ttt.notion.model.Block;
import dev.pack.ttt.notion.model.Block.Annotations;
import dev.pack.ttt.notion.model.Block.RichText;
import dev.pack.ttt.notion.model.Block.RichTextWrapper;
import dev.pack.ttt.notion.model.Content;
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
            String type = block.getType();
            RichTextWrapper richTextWrapper = getNonNullRichTextWrapper(block);
            if (richTextWrapper != null) {
                addFrontTag(type, htmlBuilder);
                for (RichText richText : richTextWrapper.getRichText()) {
                    convertRichTextToHTML(richText, type, htmlBuilder);
                }
                addBackTag(type, htmlBuilder);
            }
        }
        return htmlBuilder;
    }

    private RichTextWrapper getNonNullRichTextWrapper(Block block) {
        if (block.getHeading1() != null) {
            return block.getHeading1();
        } else if (block.getHeading2() != null) {
            return block.getHeading2();
        } else if (block.getHeading3() != null) {
            return block.getHeading3();
        } else if (block.getBulletedListItem() != null) {
            return block.getBulletedListItem();
        } else if (block.getNumberedListItem() != null) {
            return block.getNumberedListItem();
        } else if (block.getToDo() != null) {
            return block.getToDo();
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

    private void convertRichTextToHTML(RichText richText, String type, StringBuilder htmlBuilder) {
        if (richText != null) {
            String content = richText.getText().getContent();
            Annotations annotations = richText.getAnnotations();

            if (annotations != null) {
                if (annotations.getBold()) {
                    content = "<strong>" + content + "</strong>";
                }
                if (annotations.getItalic()) {
                    content = "<em>" + content + "</em>";
                }
                if (annotations.getStrikethrough()) {
                    content = "<del>" + content + "</del>";
                }
                if (annotations.getUnderline()) {
                    content = "<u>" + content + "</u>";
                }
                if (annotations.getCode()) {
                    content = "<code>" + content + "</code>";
                }
            }


            htmlBuilder.append(content+" ");

        }
    }

    public void addFrontTag(String type, StringBuilder htmlBuilder) {
        switch (type) {
            case "heading_1":
                htmlBuilder.append("<h1>");
            case "heading_2":
                htmlBuilder.append("<h2>");
                break;
            case "heading_3":
                htmlBuilder.append("<h3>");
                break;
            case "bulleted_list_item":
                htmlBuilder.append("<ul><li>");
                break;
            case "numbered_list_item":
                htmlBuilder.append("<ol><li>");
                break;
            case "paragraph":
                htmlBuilder.append("<p>");
                break;
            case "to_do":
                htmlBuilder.append("<input type='checkbox'>");
                break;
            case "toggle":
                htmlBuilder.append("<details><summary>");
                break;
            case "quote":
                htmlBuilder.append("<blockquote>");
                break;
            case "callout":
                htmlBuilder.append("<div class='callout'>");
                break;
            case "code":
                htmlBuilder.append("<pre><code>");
                break;
            default:
                htmlBuilder.append("");
                break;
        }
    }

    public void addBackTag(String type, StringBuilder htmlBuilder) {
        switch (type) {
            case "heading_1":
                htmlBuilder.append("</h1>");
                break;
            case "heading_2":
                htmlBuilder.append("</h2>");
                break;
            case "heading_3":
                htmlBuilder.append("</h3>");
                break;
            case "bulleted_list_item":
                htmlBuilder.append("</li></ul>");
                break;
            case "numbered_list_item":
                htmlBuilder.append("</li></ol>");
                break;
            case "paragraph":
                htmlBuilder.append("</p>");
                break;
            case "to_do":
                htmlBuilder.append("<br>");
                break;
            case "toggle":
                htmlBuilder.append("</summary></details>");
                break;
            case "quote":
                htmlBuilder.append("</blockquote>");
                break;
            case "callout":
                htmlBuilder.append("</div>");
                break;
            case "code":
                htmlBuilder.append("</code></pre>");
                break;
            default:
                htmlBuilder.append("");
                break;
        }
    }
}
