package dev.pack.ttt.notion.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Block {

    private String type;

    @JsonProperty("heading_1")
    private RichTextWrapper heading1;

    @JsonProperty("heading_2")
    private RichTextWrapper heading2;

    @JsonProperty("heading_3")
    private RichTextWrapper heading3;

    @JsonProperty("bulleted_list_item")
    private RichTextWrapper bulletedListItem;

    @JsonProperty("to_do")
    private RichTextWrapper toDo;

    @JsonProperty("numbered_list_item")
    private RichTextWrapper numberedListItem;

    @JsonProperty("paragraph")
    private RichTextWrapper paragraph;

    @JsonProperty("toggle")
    private RichTextWrapper toggle;

    @JsonProperty("quote")
    private RichTextWrapper quote;

    @JsonProperty("callout")
    private RichTextWrapper callout;

    @JsonProperty("code")
    private CodeWrapper code;

    @Data
    @ToString
    public static class RichTextWrapper {

        @JsonProperty("rich_text")
        private List<RichText> richText;
    }

    @Data
    @ToString
    public static class CodeWrapper {

        @JsonProperty("rich_text")
        private List<RichText> richText;

        @JsonProperty("language")
        private String language;
    }

    @Data
    @ToString
    public static class RichText {

        private String type;
        private Text text;
        private Annotations annotations;
        @JsonProperty("plain_text")
        private String plainText;
        private String href;
    }

    @Data
    @ToString
    public static class Text {

        private String content;
        private Object link;
    }

    // properties are consist of dynamic information
    private JsonNode properties;
    private JsonNode parent;
}
