FROM java:8-jre
MAINTAINER antono@clemble.com

EXPOSE 10008

ADD ./buildoutput/payment-bonus.jar /data/payment-bonus.jar

CMD java -jar -Dspring.profiles.active=cloud -Dlogging.config=classpath:logback.cloud.xml -Dserver.port=10008 /data/payment-bonus.jar
