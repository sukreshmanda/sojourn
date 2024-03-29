# sojourn

project to store key-value data for temporary purpose

## Pre-requisite

Install mongoDB in your local machine

## Front-end

Install front-end with npm & start

```bash
  cd sojourn-ui
  npm install
  npm start
```

Start from the docker

```bash
docker run -d -p 80:80 sukreshmanda/sojourn-ui:latest
```

## Backend

Install backend with maven and start

```bash
  cd sojourn
  mvn clean install
  mvn spring-boot:run
```

Start from the docker

```bash
docker run -d -p 1234:1234 sukreshmanda/sojourn:latest
```

## Technical Tools

- Spring boot
- ReactJs
- MongoDB

## Features

- Temporary storage of Json data in server
- Access through UI
- Programatical access (Basic & JWT)
- Stateless

## 🚀 About Me

I'm a software engineering with skills including backend, frontend, mobile, devops and many more.

## License

[MIT](https://choosealicense.com/licenses/mit/)
