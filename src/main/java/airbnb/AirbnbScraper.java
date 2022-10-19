package airbnb;

import model.Attribute;
import model.Property;
import model.PropertyScraper;
import org.jsoup.Jsoup;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.net.URL;
import java.util.List;
import java.util.Optional;

public class AirbnbScraper implements PropertyScraper {

    private List<Filter> filters;
    private WebDriver driver;

    public AirbnbScraper(WebDriver driver) {
        this.driver = driver;
        filters = List.of(new NameFilter(),
                new BedroomFilter(),
                new BathroomFilter(),
                new PropertyTypeFilter(),
                new AmenitiesFilter());
    }

    @Override
    public Optional<Property> scrape(URL url) {
        try {
            driver.get(url.toString());

            // The content in the amenities section is not loaded until we are actively
            // over the block. Therefore, we need to scroll down to it can render the elements.
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,10000)", "");

            List<Attribute> attributes = filters.stream()
                    .map(filter -> filter.filter(Jsoup.parse(driver.getPageSource())))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();

            return Optional.of(new Property(url, attributes));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
