This project was upgraded under the direction of Professor [Zhida Li](https://zhidali.me/), based on Professor Li's [CyberDefense](https://github.com/zhida-li/cyberDefense) project. Professor Li's project includes complete real-time analysis, prediction, machine learning and other technologies on the Python side. In addition, the Web layer uses Websocket for real-time chart and data presentation.
Under the guidance of Professor Li, the front and back ends of the project are not separated, and there are problems such as limited scalability and difficult maintenance. The team reorganized the business and technology, adopted the current stable and reliable technical architecture, and designed a three-layer technical architecture. Such a design helps to attract more people from different technical backgrounds and provides flexibility and scalability for the development of the system.

The NCS870-Fall2023-Team1 project consists of three sub-projects, which are: 1. The front-end framework is based on VUE, 2. The application server is based on Spring Boot, 3. Background analysis is based on Python.

[NCS870-Fall2023-Team1-Vue](https://github.com/Caixianwang/NCS870-Fall2023-Team1-Vue)

[NCS870-Fall2023-Team1-Spring](https://github.com/Caixianwang/NCS870-Fall2023-Team1-Spring)

[NCS870-Fall2023-Team1-Python](https://github.com/Caixianwang/NCS870-Fall2023-Team1-Python)

# Incs870-Spring
Spring Boot is the back-end framework that handles front-end requests and major business logic. With WebSocket technology, a persistent bidirectional connection is established to achieve real-time communication, ensuring that the system can transmit data in real time, which greatly improves the user experience, especially in situations requiring real-time updates and interactivity.
# Launching Incs870-Spring
# First Install Maven3.9+ and JDK17+

mvn clean package -DskipTests

# Generate NCS870-Fall2023-Team1-Spring-0.0.1-SNAPSHOT.jar in the target directory

# Start project command
java -jar NCS870-Fall2023-Team1-Spring-0.0.1-SNAPSHOT-0.0.1-SNAPSHOT.jar

Running on:

http://locahost:8080
