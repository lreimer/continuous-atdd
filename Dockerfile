FROM ghcr.io/graalvm/graalvm-ce:java8-20.3.0

RUN mkdir /continuous-atdd
WORKDIR /continuous-atdd
COPY . .
RUN ./gradlew testClasses configureChromeDriverBinary configureGeckoDriverBinary

ENV SELENIUM_HUB_HOST=selenium-hub
ENV SELENIUM_HUB_PORT=4444

CMD [ "./gradlew" ]