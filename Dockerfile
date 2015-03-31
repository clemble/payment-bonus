FROM java:8-jre
MAINTAINER antono@clemble.com

EXPOSE 8080

ADD target/payment-bonus-0.17.0-SNAPSHOT.jar /data/payment-bonus.jar

CMD java -jar -Dspring.profiles.active=cloud /data/payment-bonus.jar
