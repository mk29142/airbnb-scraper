import airbnb.AirbnbScraper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

public class Main {

    public static void main(String[] args) throws MalformedURLException, JsonProcessingException {

        // Airbnb has dynamically loaded content, which means simple curl
        // request to the site won't render all the information in the elements.
        // To overcome this we need an actual browser engine to make sure all consecutive
        // AJAX calls and scripts are run before trying to parse the page.
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        var webDriver = new ChromeDriver(options);

        var scraper = new AirbnbScraper(webDriver);

        var urls = List.of(new URL("https://www.airbnb.co.uk/rooms/33571268"),
                new URL("https://www.airbnb.co.uk/rooms/20669368"),
                new URL("https://www.airbnb.co.uk/rooms/50633275"));

        var res = urls.stream().map(scraper::scrape)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(res));
    }
}
