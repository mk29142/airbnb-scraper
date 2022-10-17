package airbnb;

import model.Attribute;
import org.jsoup.nodes.Document;

import java.util.Optional;

class NameFilter implements Filter {

    private final String BLOCK_ID = "TITLE_DEFAULT";

    @Override
    public Optional<Attribute> filter(Document doc) {
        var block = doc.select("div[data-section-id=" + BLOCK_ID + "]");
        var name = block.select("h1").text();

        if(name.isBlank()) {
            return Optional.empty();
        }

        return Optional.of(new Attribute("title", name));
    }
}
