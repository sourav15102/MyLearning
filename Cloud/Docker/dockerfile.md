sample
```bash
FROM maven:3.8.7-eclipse-temurin-19  
  
WORKDIR /myapp/GatekeeperService/  
  
COPY ./target/Gatekeeper-0.0.1-SNAPSHOT.jar .  
  
CMD ["java", "-jar", "Gatekeeper-0.0.1-SNAPSHOT.jar"]
```

