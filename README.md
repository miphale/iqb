**Online Registration System**

#Project list#
1. MySQL on docker
ora-docker
Contains docker-compose file and the init sql file
2. Rest API
ora-api
3. Form and Report
ora-angular-client

**Running the project**

1. **Auto Run**
On a Linux machine (An assumption is made that the linux machine has docker installed)
In project ora-run, there is a .sh script you can utilise to start and stop the services

* Start services
Run command './ora-startup-projects start'
The .sh script command starts the projects in the following order:
1. MySQL Docker (ora-docker): runs a 'docker-compose' commandto setup and start mysql on docker. The docker-compose file is setup to run the init sql script to create the 'ora' database
2. Rest API (ora-api): runs a 'mvn spring-boot:run' command to startup the springboot project
3. Form and Report (ora-angular-client): runs a 'ng serve' command to compile and start up the ui project

Once all the projects are running, access the web UI using the url: localhost:4200

* Stop services
Run command './ora-startup-projects stop'

2. **Manual Run**
1. MySQL Docker
In a docker machine, setup the mysql container. Run docker-compose using the docker-compose file in ora-docker
2. Rest API 
From the directory: ora-api, run command 'mvn spring-boot'
3. Form and Report 
From the directory: ora-angular-client, run command 'ng serve'
4. Access the web UI using the url: localhost:4200 Use the nav menu to create new person or view list of people already created


**Notes:**
Tests
The test case 'testAddPerson' requires a valid SA ID number to run. For obvious reasons I didn't provide that in this repo. You can provide a valid ID, comment out the specified assertion line to accurately run the test
MySQL docker: if you change usernames, container name, password, ports to the sql server, note you must update the values in application.properties file in ora-api
