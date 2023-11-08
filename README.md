# Frequency Counter

### Стек технологий:
* Spring boot 3
* Gradle 8
* Docker
* 
--------------------------------------
## Запуск сервиса (порт 8080)

### Локально
* git clone https://github.com/ivshapovalov/freq-counter.git
* cd freq-counter
* ./gradlew build -Dorg.gradle.java.home=/path-to-jdks-17+
* java -jar ./build/libs/freq-counter-0.0.1-SNAPSHOT.jar

### Используя docker-compose
* git clone https://github.com/ivshapovalov/freq-counter.git
* cd freq-counter
* bash run.sh


## API
1)  OPEN API http://localhost:8080/v3/api-docs или [локально](https://github.com/ivshapovalov/freq-counter/tree/main/docs/api-docs.json)
2)  SWAGGER UI http://localhost:8080/swagger-ui/index.html

