FROM gradle:8.5-jdk21 as build

WORKDIR /app

COPY . .

RUN chmod +x wait-for-selenium.sh

# Run the wait script and then tests at runtime
ENTRYPOINT ["/bin/bash", "-c", "./wait-for-selenium.sh && gradle clean test --no-daemon"]

#CMD ./wait-for-selenium.sh && gradle clean test --no-daemon
