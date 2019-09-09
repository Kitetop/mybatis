FROM maven:3.6-ibmjava-8
WORKDIR /usr/src
COPY . /usr/src
RUN mvn clean package -Dmaven.test.skip=true
RUN cd target && copy core-0.0.1.jar /usr/src
EXPOSE 5000
CMD ["java", "-jar", "--spring.profiles.active=test", "core-0.0.1.jar"]