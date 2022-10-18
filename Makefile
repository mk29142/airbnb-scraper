
.PHONY: build
build:
	./gradlew build

.PHONY: start
start:
	java -jar "build/libs/airbnb-scraper.jar"

start-docker:
	docker build -t scraper .
	docker run --network host scraper