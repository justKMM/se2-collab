package hbrs.se2.collhbrs.util;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MarkdownConverter {
    private final Parser parser;
    private final HtmlRenderer renderer;

    public MarkdownConverter() {
        this.parser = Parser.builder().build();
        this.renderer = HtmlRenderer.builder().build();
    }

    public String convertToHtml(String markdown) {
        return renderer.render(parser.parse(markdown));
    }

    public List<String> convertToHtml(List<String> markdownList) {
        List<String> htmlList = new ArrayList<>();
        for (String markdown : markdownList) {
            String html = renderer.render(parser.parse(markdown));
            htmlList.add(html);
        }
        return htmlList;
    }
}
