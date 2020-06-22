Online Registration System

MySQL on docker
ora-docker
Contains docker-compose file and the init sql file
springboot api
ora-api
angular frontend
ora-angular-client

Running the project

Auto Run
Linux (An assumption is made that the linux machine has docker installed)
In directory ora-run there is a .sh script you can utilise to start and stop the projects

Start services and frontend
Run command ./ora-startup-projects start
The .sh script command starts the projects in the following order:
ora-docker: runs a 'docker-compose' commandto setup and start mysql on docker. The docker-compose file is setup to run the init sql script to create the 'ora' database
ora-api: runs a 'mvn spring-boot:run' command to startup the springboot project
ora-angular-client: runs a 'ng serve' command to compile and start up the ui project

Once all the projects are running, access the web UI using the url: localhost:4200

Stop services and front end
./ora-startup-projects stop

Manual Run
MySQL Docker
In a docker machine, setup the mysql container. Run docker-compose using the docker-compose file in ora-docker
ora-api
From this directory, run command 'mvn spring-boot'
ora-angular-client
From this directory, run command 'ng serve'


Notes:
Tests
The test case 'testAddPerson' requires a valid SA ID number to run. For obvious reasons I didn't provide that in this repo. You can provide a valid ID, comment out the specified assertion line to accurately run the test
