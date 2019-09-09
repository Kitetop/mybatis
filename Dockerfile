FROM maven:3.6-ibmjava-8
WORKDIR /usr/src
COPY . .
RUN chmod 777 ./build.sh
VOLUME ["~/.m2/repository"]
EXPOSE 5000
CMD ["./build.sh"]