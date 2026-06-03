# Uniall

This project is a simple fullstack application with a frontend in React, a backend in Spring Boot and a MySQL database.

## Scope: 

- The main idea is to provide a source of information about universities and courses over the world.
<img src="uniall/public/app-screenshot.png" alt="Alt text" width="800" height="600">

- Use this prototype to understand how to build a fullstack application with React, Spring Boot and MySQL.

## Frontend - React

1. Project folder is .\uniall:

- In the project directory, you can run:

### `npm install & npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

The page will reload when you make changes.\
You may also see any lint errors in the console.

## Backend - Spring Boot 

Run Backend in your local machine:

1. Backend requires MySQL database in order to run locally execute the docker-compose commands on displayed on the Docker configuration section to start 
the MySQL container.
2. Run the following commands in the root directory of the project.

### `mvn clean install`
then
### `mvn spring-boot:run`

Open [http://localhost:8083](http://localhost:8083) to view it in your browser.

## Database - MySQL

- The database is running in a Docker container.
- The database is accessible on port 3306.
- During the container creation, the database is created and the tables are created and populated.
- Inside `sql-script` folder you will find all the scripts executed during the startup.

## Docker configuration

* In order to deploy the services in Docker edit or keep the standard configuration on `docker-compose.yml` file.

- To run the services in Docker, you need to have Docker and docker-compose installed on your machine.
- Run the following commands in the root directory of the project.


- To start the services run the following command:
### `docker-compose up`
- To stop the services run the following command:

### `docker-compose down`

## License

This project is licensed under the MIT License.
