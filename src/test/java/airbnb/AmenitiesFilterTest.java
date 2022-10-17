package airbnb;

import model.Attribute;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AmenitiesFilterTest {

    private Filter filter;

    @BeforeEach
    public void setUp() {
        filter = new AmenitiesFilter();
    }

    @Test
    public void shouldGetAllAmenities() throws IOException {
        var doc = Jsoup.parse(getHTML());
        var got = filter.filter(doc);

        var expectedValue = "[Kitchen, Free parking on premises, Hot tub, Patio or balcony, Indoor fireplace, Unavailable: Carbon monoxide alarm Carbon monoxide alarm]";
        var expected = Optional.of(new Attribute("amenities", expectedValue));

        assertEquals(expected, got);
    }

    @Test
    public void shouldReturnEmptyListIfNoAmenitiesFound() throws IOException {
        var html = """
                      <div data-plugin-in-point-id="should-fail" data-section-id="should-fail" style="padding-top: 48px; padding-bottom: 24px;">
                	</div>
                """;
        var doc = Jsoup.parse(html);
        var got = filter.filter(doc);

        assertEquals(Optional.empty(), got);
    }

    private String getHTML() throws IOException {
        return """
                <div data-plugin-in-point-id="AMENITIES_DEFAULT" data-section-id="AMENITIES_DEFAULT" style="padding-top: 48px; padding-bottom: 48px;">
                	<section>
                		<div id="DEBUG_STYLE_LOGGER" class="d1aa9g70 dir dir-ltr"></div>
                		<div class="sewcpu6 dir dir-ltr" style="--spacingBottom:3;">
                			<div class="t5p7tdn dir dir-ltr">
                				<h2 tabindex="-1" class="hnwb2pb dir dir-ltr" elementtiming="LCP-target">What this place offers</h2>
                			</div>
                		</div>
                		<div class="_1byskwn">
                			<div class="_19xnuo97">
                				<div class="iikjzje i10xc1ab dir dir-ltr">
                					<div>Kitchen</div>
                					<div class="i4wvyiy i1fpqhzs dir dir-ltr">
                						<svg viewBox="0 0 32 32"
                							xmlns="http://www.w3.org/2000/svg" aria-hidden="true" role="presentation" focusable="false" style="display: block; height: 24px; width: 24px; fill: currentcolor;">
                							<path d="M26 1a5 5 0 0 1 5 5c0 6.389-1.592 13.187-4 14.693V31h-2V20.694c-2.364-1.478-3.942-8.062-3.998-14.349L21 6l.005-.217A5 5 0 0 1 26 1zm-9 0v18.118c2.317.557 4 3.01 4 5.882 0 3.27-2.183 6-5 6s-5-2.73-5-6c0-2.872 1.683-5.326 4-5.882V1zM2 1h1c4.47 0 6.934 6.365 6.999 18.505L10 21H3.999L4 31H2zm14 20c-1.602 0-3 1.748-3 4s1.398 4 3 4 3-1.748 3-4-1.398-4-3-4zM4 3.239V19h3.995l-.017-.964-.027-.949C7.673 9.157 6.235 4.623 4.224 3.364l-.12-.07zm19.005 2.585L23 6l.002.31c.045 4.321 1.031 9.133 1.999 11.39V3.17a3.002 3.002 0 0 0-1.996 2.654zm3.996-2.653v14.526C27.99 15.387 29 10.4 29 6a3.001 3.001 0 0 0-2-2.829z"></path>
                						</svg>
                					</div>
                				</div>
                			</div>
                			<div class="_19xnuo97">
                				<div class="iikjzje i10xc1ab dir dir-ltr">
                					<div>Free parking on premises</div>
                					<div class="i4wvyiy i1fpqhzs dir dir-ltr">
                						<svg
                							xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" aria-hidden="true" role="presentation" focusable="false" style="display: block; height: 24px; width: 24px; fill: currentcolor;">
                							<path d="M26 19a1 1 0 1 1-2 0 1 1 0 0 1 2 0zM7 18a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm20.693-5l.42 1.119C29.253 15.036 30 16.426 30 18v9c0 1.103-.897 2-2 2h-2c-1.103 0-2-.897-2-2v-2H8v2c0 1.103-.897 2-2 2H4c-1.103 0-2-.897-2-2v-9c0-1.575.746-2.965 1.888-3.882L4.308 13H2v-2h3v.152l1.82-4.854A2.009 2.009 0 0 1 8.693 5h14.614c.829 0 1.58.521 1.873 1.297L27 11.151V11h3v2h-2.307zM6 25H4v2h2v-2zm22 0h-2v2h2v-2zm0-2v-5c0-1.654-1.346-3-3-3H7c-1.654 0-3 1.346-3 3v5h24zm-3-10h.557l-2.25-6H8.693l-2.25 6H25zm-15 7h12v-2H10v2z"></path>
                						</svg>
                					</div>
                				</div>
                			</div>
                			<div class="_19xnuo97">
                				<div class="iikjzje i10xc1ab dir dir-ltr">
                					<div>Hot tub</div>
                					<div class="i4wvyiy i1fpqhzs dir dir-ltr">
                						<svg viewBox="0 0 32 32"
                							xmlns="http://www.w3.org/2000/svg" aria-hidden="true" role="presentation" focusable="false" style="display: block; height: 24px; width: 24px; fill: currentcolor;">
                							<path d="M9.5 2a4.5 4.5 0 0 1 3.527 7.295c.609.215 1.173.55 1.66.988l.191.182L17.414 13H31v2h-2v14a2 2 0 0 1-1.85 1.995L27 31H5a2 2 0 0 1-1.995-1.85L3 29V15H1v-2h2.1a5.009 5.009 0 0 1 2.955-3.608A4.5 4.5 0 0 1 9.5 2zm7.085 13H5v14h22V15h-7.586l3.293 3.294-1.414 1.414zM9.5 4a2.5 2.5 0 0 0-1 4.792V11H8a3.001 3.001 0 0 0-2.83 2h9.415l-1.121-1.121a3 3 0 0 0-1.923-.872L11.343 11H10.5V8.792A2.5 2.5 0 0 0 9.5 4zm15.486-3a6.957 6.957 0 0 1-1.803 4.07l-.445.463A8.971 8.971 0 0 0 20.354 11H18.35a10.975 10.975 0 0 1 3.202-7.118A4.961 4.961 0 0 0 22.974 1zm2.007 0h2.004a10.96 10.96 0 0 1-3.202 7.124A4.974 4.974 0 0 0 24.373 11h-2.012a6.97 6.97 0 0 1 1.804-4.064l.444-.462A8.958 8.958 0 0 0 26.993.999z"></path>
                						</svg>
                					</div>
                				</div>
                			</div>
                			<div class="_19xnuo97">
                				<div class="iikjzje i10xc1ab dir dir-ltr">
                					<div>Patio or balcony</div>
                					<div class="i4wvyiy i1fpqhzs dir dir-ltr">
                						<svg viewBox="0 0 32 32"
                							xmlns="http://www.w3.org/2000/svg" aria-hidden="true" role="presentation" focusable="false" style="display: block; height: 24px; width: 24px; fill: currentcolor;">
                							<path d="M23 1a2 2 0 0 1 1.995 1.85L25 3v16h4v2h-2v8h2v2H3v-2h2v-8H3v-2h4V3a2 2 0 0 1 1.85-1.995L9 1zM9 21H7v8h2zm4 0h-2v8h2zm4 0h-2v8h2zm4 0h-2v8h2zm4 0h-2v8h2zm-10-8H9v6h6zm8 0h-6v6h6zM15 3H9v8h6zm8 0h-6v8h6z"></path>
                						</svg>
                					</div>
                				</div>
                			</div>
                			<div class="_19xnuo97">
                				<div class="iikjzje i10xc1ab dir dir-ltr">
                					<div>Indoor fireplace</div>
                					<div class="i4wvyiy i1fpqhzs dir dir-ltr">
                						<svg viewBox="0 0 32 32"
                							xmlns="http://www.w3.org/2000/svg" aria-hidden="true" role="presentation" focusable="false" style="display: block; height: 24px; width: 24px; fill: currentcolor;">
                							<path d="m31 6v2h-1v23h-6v-18h-16v18h-6v-23h-1v-2zm-15.368 8.991.959.702c3.317 2.43 5.141 5.07 5.382 7.934l.02.287.005.207.002.138c0 3.183-2.698 5.741-6 5.741-3.168 0-5.789-2.358-5.988-5.387l-.01-.218-.002-.147c.004-1.629.557-3.29 1.64-4.985l.224-.34.677-.98 1.238.783zm12.368-6.991h-24v21h2v-16a2 2 0 0 1 1.697-1.977l.154-.018.149-.005h16a2 2 0 0 1 1.995 1.85l.005.15v16h2zm-12 17.355-.092.093c-.62.655-.908 1.233-.908 1.719 0 .428.413.833 1 .833s1-.405 1-.833c0-.445-.242-.968-.76-1.556l-.148-.163zm.351-7.315-1.766 3.562-1.466-.927-.152.265c-.534.96-.844 1.878-.937 2.749l-.023.289-.007.26.001.118c.025.92.408 1.761 1.024 2.403.14-1.137.86-2.237 2.097-3.324l.238-.203.64-.534.64.534c1.384 1.153 2.188 2.32 2.335 3.528a3.593 3.593 0 0 0 1.018-2.27l.007-.218-.006-.28c-.088-1.865-1.113-3.702-3.129-5.51l-.268-.236zm14.649-16.04v2h-30v-2z"></path>
                						</svg>
                					</div>
                				</div>
                			</div>
                			<div class="_19xnuo97">
                				<div class="iikjzje i10xc1ab dir dir-ltr">
                					<div>
                						<span class="a8jt5op dir dir-ltr">Unavailable: Carbon monoxide alarm</span>
                						<del aria-hidden="true">Carbon monoxide alarm</del>
                					</div>
                					<div class="i4wvyiy i1fpqhzs dir dir-ltr">
                						<svg viewBox="0 0 32 32"
                							xmlns="http://www.w3.org/2000/svg" aria-hidden="true" role="presentation" focusable="false" style="display: block; height: 24px; width: 24px; fill: currentcolor;">
                							<path d="M2.05 6.292L4 8.242V25a3 3 0 0 0 2.824 2.995L7 28h16.757l1.95 1.95c-.161.023-.325.038-.49.045L25 30H7a5 5 0 0 1-4.995-4.783L2 25V7c0-.24.017-.477.05-.708zm1.657-4l26 26-1.414 1.415-26-26 1.414-1.414zM25 2a5 5 0 0 1 4.995 4.783L30 7v18c0 .24-.017.476-.05.707L28 23.757V7a3 3 0 0 0-2.824-2.995L25 4H8.242l-1.95-1.95c.162-.023.325-.038.491-.045L7 2h18zM11.1 17a5.006 5.006 0 0 0 3.9 3.9v2.03A7.005 7.005 0 0 1 9.071 17h2.03zm5.9 4.243l1.352 1.352a6.954 6.954 0 0 1-1.351.334L17 21.243zM21.243 17l1.686.001c-.067.467-.18.919-.334 1.351L21.243 17zm-4.242-7.929A7.005 7.005 0 0 1 22.929 15H20.9A5.006 5.006 0 0 0 17 11.1l.001-2.029zm-7.596 4.577L10.757 15 9.071 15c.067-.467.18-.92.334-1.352zM15 9.071l-.001 1.686-1.35-1.352A6.954 6.954 0 0 1 15 9.07zM23 8a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"></path>
                						</svg>
                					</div>
                				</div>
                			</div>
                		</div>
                		<div class="b6xigss dir dir-ltr">
                			<button type="button" class="b65jmrv v7aged4 dir dir-ltr">Show all 15 amenities</button>
                		</div>
                	</section>
                </div>
                """;
    }
}