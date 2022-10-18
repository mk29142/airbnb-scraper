
.PHONY: build
build:
	./gradlew build

.PHONY: run
run:
	java -jar "build/libs/airbnb-scraper.jar"

start-docker:
	docker build -t scraper .
	docker run --network host scraper