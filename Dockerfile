FROM maven:3.8.6-amazoncorretto-11

COPY . .
COPY commands.sh /commands.sh

RUN ["chmod", "+x", "/commands.sh"]

ENTRYPOINT ["/commands.sh"]