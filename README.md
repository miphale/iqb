# Online Registration System

**Project list**
1. MySQL on docker
ora-docker
Contains docker-compose file and the init sql file
2. Rest API
ora-api
3. Form and Report
ora-angular-client

## Running the project

1. **Auto Run** <br/><br/>
On a Linux machine (An assumption is made that the linux machine has docker installed)
In project ora-run, there is a .sh script you can utilise to start and stop all services

**Start services** <br/>
Run command './ora-startup-projects start'
The .sh script command starts the projects in the following order:
* MySQL Docker (ora-docker): runs a 'docker-compose' commandto setup and start mysql on docker. The docker-compose file is setup to run the init sql script to create the 'ora' database
* Rest API (ora-api): runs a 'mvn spring-boot:run' command to startup the springboot project
* Form and Report (ora-angular-client): runs a 'ng serve' command to compile and start up the ui project

Once all the projects are running, access the web UI using the url: localhost:4200
<br/><br/>
**Stop services**<br/>
Run command './ora-startup-projects stop'

2. **Manual Run** <br/>
* MySQL Docker
In a docker machine, setup the mysql container. Run docker-compose using the docker-compose file in ora-docker
* Rest API 
From the directory: ora-api, run command 'mvn spring-boot'
* Form and Report 
From the directory: ora-angular-client, run command 'ng serve'
* Access the web UI using the url: localhost:4200 Use the nav menu to create new person or view list of people already created


## Notes:
* Tests: <br/>
The test case 'testAddPerson' requires a valid SA ID number to run. For obvious reasons I didn't provide that in this repo. You can provide a valid ID, comment out the specified assertion line to accurately run the test
* MySQL docker: <br/> 
If you change any of the server properties: (usernames, container name, password or port), note you must update the values in application.properties file in ora-api<br/>
* On Windows: <br/>
For property: spring.datasource.url Networking on windows gets a bit iffy so you can use the docker container ip address instead of the container name (*ora-mysql*) or you can map your container IP address to the container name in your host file so the property value becomes either: jdbc:mysql://${containername}:3308/ora?useSSL=false or jdbc:mysql://${ipaddress}:3308/ora?useSSL=false

