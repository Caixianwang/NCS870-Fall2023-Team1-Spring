This project upgraded on the basis of Prof. [Zhida Li](https://zhidali.me/)'s [CyberDefense](https://github.com/zhida-li/cyberDefense) project. The original project included technologies such as real-time analytics, prediction and machine learning on the Python side, while the Web layer used Websocket to display real-time charts and data. However, the front and back ends of the project were not separated, which led to limited scalability and difficulties in maintenance.

Under the guidance of Professor Li, the team reorganized the business and technology. A current, more stable and reliable technical architecture was adopted, and a three-tier technical architecture was designed. This new design helps to attract more people with different technical backgrounds to participate, providing greater flexibility and scalability for system development

The NCS870-Fall2023-Team1 project consists of three sub-projects, which are: 1. The front-end framework is based on VUE, 2. The application server is based on Spring Boot, 3. Background analysis is based on Python.

[NCS870-Fall2023-Team1-Vue](https://github.com/Caixianwang/NCS870-Fall2023-Team1-Vue)

[NCS870-Fall2023-Team1-Spring](https://github.com/Caixianwang/NCS870-Fall2023-Team1-Spring)

[NCS870-Fall2023-Team1-Python](https://github.com/Caixianwang/NCS870-Fall2023-Team1-Python)

# Incs870-Spring
Spring Boot is the back-end framework that handles front-end requests and major business logic. With WebSocket technology, a persistent bidirectional connection is established to achieve real-time communication, ensuring that the system can transmit data in real time, which greatly improves the user experience, especially in situations requiring real-time updates and interactivity.
# Launching Incs870-Spring
# First Install Maven3.9+ and JDK17+

mvn clean package -DskipTests

Generate NCS870-Fall2023-Team1-Spring-0.0.1-SNAPSHOT.jar in the target directory

# Run project command

java -jar NCS870-Fall2023-Team1-Spring-0.0.1-SNAPSHOT-0.0.1-SNAPSHOT.jar

http://locahost:8080
