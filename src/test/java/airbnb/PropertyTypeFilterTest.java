package airbnb;

import model.Attribute;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PropertyTypeFilterTest {

    private Filter filter;

    @BeforeEach
    void setUp() {
        filter = new PropertyTypeFilter();
    }

    @Test
    public void shouldGetCorrectType() {
        var html = """
                <div data-plugin-in-point-id="OVERVIEW_DEFAULT" data-section-id="OVERVIEW_DEFAULT" style="padding-top: 48px; padding-bottom: 24px;">
                	<section>
                		<div>
                			<div class="_88xxct">
                				<div class="_jro6t0">
                					<div class="_tqmy57">
                						<div class="_cv5qq4">
                							<h2 tabindex="-1" class="_14i3z6h" elementtiming="LCP-target">House hosted by&nbsp; mridul</h2>
                						</div>
                					</div>
                					</div>
                				</div>
                			</div>
                		</section>
                	</div>
                """;
        var doc = Jsoup.parse(html);
        var got = filter.filter(doc);

        var expected = new Attribute("type", "House");
        assertEquals(expected, got.get());
    }

    @Test
    public void shouldReturnEmptyOptionalIfElementNotFound() {
        var html = """
                <div data-plugin-in-point-id="should-fail" data-section-id="should-fail" style="padding-top: 48px; padding-bottom: 24px;">
                	</div>
                """;
        var doc = Jsoup.parse(html);
        var got = filter.filter(doc);

        assertTrue(got.isEmpty());
    }

    @Test
    public void shouldReturnEmptyOptionalIfElementDoesNotContainType() {
        var html = """
                <div data-plugin-in-point-id="OVERVIEW_DEFAULT" data-section-id="OVERVIEW_DEFAULT" style="padding-top: 48px; padding-bottom: 24px;">
                	<section>
                		<div>
                			<div class="_88xxct">
                				<div class="_jro6t0">
                					<div class="_tqmy57">
                					</div>
                					</div>
                				</div>
                			</div>
                		</section>
                	</div>
                """;
        var doc = Jsoup.parse(html);
        var got = filter.filter(doc);

        assertTrue(got.isEmpty() );
    }
}