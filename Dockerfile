FROM java:8-jre
MAINTAINER antono@clemble.com

EXPOSE 10008

ADD target/payment-bonus-*-SNAPSHOT.jar /data/payment-bonus.jar

CMD java -jar -Dspring.profiles.active=cloud  -Dserver.port=10008 /data/payment-bonus.jar
