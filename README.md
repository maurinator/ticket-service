# ticket-service
Java Tomcat 8 back-end for a stage ticket service

# Setup
Download the latest Apache Maven from https://maven.apache.org/

# Run Tests
``` shell
mvn test
```

# Build Project
``` shell
mvn package
```

# Run Project's Front End (Work-in-progress)
Navigate to the project's root, build, then run in Terminal / CMD

Windows,
``` shell
target\bin\webapp.bat
```

Unix,
``` shell
target\bin\webapp.sh
```

Then go to 'localhost:8080' on your favorite Internet Browser.

#To-Do's
1. Polish front-end routes to enable users to reserve seats from webapp served from Tomcat
2. Cleanup CSS, Mobile Device Responsiveness
