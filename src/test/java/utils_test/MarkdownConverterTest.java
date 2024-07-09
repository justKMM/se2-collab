package utils_test;

import hbrs.se2.collhbrs.util.MarkdownConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MarkdownConverterTest {

    private MarkdownConverter markdownConverter;

    @BeforeEach
    public void setUp() {
        markdownConverter = new MarkdownConverter();
    }

    @Test
    void testConvertToHtmlSingleString() {
        String markdown = "# Hello World";
        String expectedHtml = "<h1>Hello World</h1>\n";
        String actualHtml = markdownConverter.convertToHtml(markdown);

        assertEquals(expectedHtml, actualHtml);
    }

    @Test
    void testConvertToHtmlEmptyString() {
        String markdown = "";
        String expectedHtml = "";
        String actualHtml = markdownConverter.convertToHtml(markdown);

        assertEquals(expectedHtml, actualHtml);
    }

    @Test
    void testConvertToHtmlEmptyList() {
        List<String> markdownList = List.of();
        List<String> expectedHtmlList = List.of();
        List<String> actualHtmlList = markdownConverter.convertToHtml(markdownList);

        assertEquals(expectedHtmlList, actualHtmlList);
    }
}