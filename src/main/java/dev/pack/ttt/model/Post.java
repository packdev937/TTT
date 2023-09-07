package dev.pack.ttt.model;

import dev.pack.ttt.notion.model.Block;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class Post {

    // Tistory에 Post할 때 필요한 정보들로 대체
    private Content content;
    private List<Block> blocks;
    private int visibility;
    private int category;
    private String slogan;
    private String tag;
    private LocalDate publishedDate;
    private StringBuilder html;

    public StringBuilder convertToHtml(){
        html = new StringBuilder();
        for(Block block : blocks){
            html.append(block.convertToHTML());
        }
        return html;
    }
}
