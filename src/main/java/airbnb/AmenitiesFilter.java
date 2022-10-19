package airbnb;

import model.Attribute;
import org.jsoup.nodes.Document;

import java.util.Optional;

class AmenitiesFilter implements Filter {

    private final String BLOCK_ID = "AMENITIES_DEFAULT";

    @Override
    public Optional<Attribute> filter(Document doc) {
        var block = doc.select("div[data-section-id=" + BLOCK_ID + "]");
        var amenities = block.select("div").stream().filter(elem -> elem.attributes().isEmpty()).map(elem -> elem.text()).toList();

        if(amenities.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(new Attribute("amenities", amenities.toString()));
    }
}
