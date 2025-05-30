FROM gradle:8.5-jdk21 as build

WORKDIR /app

COPY . .

RUN chmod +x wait-for-selenium.sh

COPY src/test/resources /app/src/test/resources
# Run the wait script and then tests at runtime
ENTRYPOINT ["/bin/bash", "-c", "./wait-for-selenium.sh && gradle clean test --no-daemon"]


#CMD ./wait-for-selenium.sh && gradle clean test --no-daemon
