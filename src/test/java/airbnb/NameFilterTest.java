package airbnb;

import model.Attribute;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NameFilterTest {

    private Filter filter;

    @BeforeEach
    public void setUp() {
        filter = new NameFilter();
    }

    @Test
    public void shouldGetCorrectTextFromElement() {
        var html = """
                <div data-plugin-in-point-id="TITLE_DEFAULT" data-section-id="TITLE_DEFAULT" style="padding-top:24px">
                <section><div class="_b8stb0"><span class="_1n81at5"><h1 tabindex="-1" class="_fecoyn4" elementtiming="LCP-target">Test property</h1></span></div>
                </div>
                """;
        var doc = Jsoup.parse(html);
        var got = filter.filter(doc);

        var expected = new Attribute("title", "Test property");
        assertEquals(expected, got.get());
    }

    @Test
    public void shouldErrorIfTextNotFound() {
        var html = """
                <div data-plugin-in-point-id="should-break" data-section-id="should-break"">
                </div>
                """;
        var doc = Jsoup.parse(html);
        var res = filter.filter(doc);

        assertTrue(res.isEmpty());
    }
}