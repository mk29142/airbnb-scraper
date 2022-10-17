package airbnb;

import model.Attribute;
import org.jsoup.nodes.Document;

import java.util.Arrays;
import java.util.Optional;

class PropertyTypeFilter implements Filter {

    private final String BLOCK_ID = "OVERVIEW_DEFAULT";

    @Override
    public Optional<Attribute> filter(Document doc) {
        var block = doc.select("div[data-section-id=" + BLOCK_ID + "]");
        var text = block.select("h2").text();

        if(text.isBlank()) {
            return Optional.empty();
        }

        var type = Arrays.stream(text.split("hosted by")).findFirst();
        return type.map(t -> new Attribute("type", t.trim()));
    }
}
