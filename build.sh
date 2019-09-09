mvn clean package -Dmaven.test.skip=true
cd target && java -jar -Dspring.profiles.active=composer -Dserver.port=5000 core-0.0.1.jar
