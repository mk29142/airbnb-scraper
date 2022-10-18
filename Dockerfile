FROM selenium/standalone-chrome

EXPOSE 4444 4444

RUN \
  sudo apt-get update && \
  DEBIAN_FRONTEND=noninteractive \
    sudo apt-get -y install \
      default-jre-headless

RUN sudo apt-get install -y software-properties-common

RUN sudo apt-get install -y openjdk-17-jdk

COPY build/libs/airbnb-scraper.jar /usr/lib/airbnb-scraper.jar

CMD ["java","-jar","/usr/lib/airbnb-scraper.jar"]