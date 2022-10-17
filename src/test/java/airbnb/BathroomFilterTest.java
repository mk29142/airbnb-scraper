package airbnb;

import model.Attribute;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BathroomFilterTest {

    private Filter filter;

    @BeforeEach
    void setUp() {
        filter = new BathroomFilter();
    }

    @Test
    public void shouldGetCorrectNumberOfBathrooms() {
        var html = """
<div data-plugin-in-point-id="OVERVIEW_DEFAULT" data-section-id="OVERVIEW_DEFAULT" style="padding-top: 48px; padding-bottom: 24px;">
	<section>
		<div>
			<div class="_88xxct">
				<div class="_jro6t0">
					<div class="_tqmy57">
						<div class="_cv5qq4">
							<h2 tabindex="-1" class="_14i3z6h" elementtiming="LCP-target">Tiny home hosted by&nbsp;Tanya</h2>
						</div>
						<ol class="lgx66tx dir dir-ltr">
							<li class="l7n4lsf dir dir-ltr">
								<span>4 guests</span>
								<span class="axjq0r dir dir-ltr">
									<span class="s1b4clln dir dir-ltr" aria-hidden="true"> · </span>
								</span>
							</li>
							<li class="l7n4lsf dir dir-ltr">
								<span class="pen26si dir dir-ltr">
									<span class="s1b4clln dir dir-ltr" aria-hidden="true"> · </span>
								</span>
								<span>1 bedroom</span>
								<span class="axjq0r dir dir-ltr">
									<span class="s1b4clln dir dir-ltr" aria-hidden="true"> · </span>
								</span>
							</li>
							<li class="l7n4lsf dir dir-ltr">
								<span class="pen26si dir dir-ltr">
									<span class="s1b4clln dir dir-ltr" aria-hidden="true"> · </span>
								</span>
								<span>1 bed</span>
								<span class="axjq0r dir dir-ltr">
									<span class="s1b4clln dir dir-ltr" aria-hidden="true"> · </span>
								</span>
							</li>
							<li class="l7n4lsf dir dir-ltr">
								<span class="pen26si dir dir-ltr">
									<span class="s1b4clln dir dir-ltr" aria-hidden="true"> · </span>
								</span>
								<span>3 bathroom</span>
							</li>
						</ol>
					</div>
				</div>
			</div>
		</section>
	</div>
                """;
        var doc = Jsoup.parse(html);
        var got = filter.filter(doc);

        var expected = new Attribute("bathrooms", "3 bathroom");
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
    public void shouldReturnEmptyOptionalIfListDoesNotContainBathrooms() {
        var html = """
                <div data-plugin-in-point-id="OVERVIEW_DEFAULT" data-section-id="OVERVIEW_DEFAULT" style="padding-top: 48px; padding-bottom: 24px;">
                	<section>
                		<div>
                			<div class="_88xxct">
                				<div class="_jro6t0">
                					<div class="_tqmy57">
                						<ol class="lgx66tx dir dir-ltr">
                							<li class="l7n4lsf dir dir-ltr">
                								<span>4 guests</span>
                							</li>
                							<li class="l7n4lsf dir dir-ltr">
                								<span>1 bed</span>
                							</li>
                						</ol>
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