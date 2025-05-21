FROM gradle:8.5-jdk21 as build

WORKDIR /app

COPY . .

RUN chmod +x wait-for-selenium.sh

CMD ./wait-for-selenium.sh && gradle clean test --no-daemon
