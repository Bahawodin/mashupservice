# mashupservice
Prerequisites: maven
The default addr is http://127.0.0.1 and server port is 8081, it can be changet in the properties file

1. Open up terminal

2. clone mashupservice repository to a local directory
	- git clone https://github.com/Bahawodin/mashupservice.git

3. cd mashupservice/
  
4. ./mvnw clean package

5. java -jar target/mashup-api-0.0.1-SNAPSHOT.jar

6. Open webbrowser or postman and use http://127.0.0.1:8081/mashup/MBiD
  -example: http://127.0.0.1:8081/mashup/5b11f4ce-a62d-471e-81fc-a69a8278c7da
