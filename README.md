# id-management

## Table of contents
- [About the Project](#about-the-project)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Contact](#contact)

## About the Project

This project provides a user interface to login to an application. It also provides an option to login with Facebook or Google. Once the customer signup with facebook or google the customer details will be stored in the local database.

This project uses
1. Spring security
2. Spring OAuth2 client
3. Spring JPA
4. Oracle driver

It is a rest based application.

## Getting Started

Run the project locally with Oracle database. Have a Oracle database running locally or inside the docker. Create an image using the docker file and then run the container which is on the same network as the Database.

1. Create network
   docker network create -d bridge my-bridge-network

2. Create a docker image
   docker build -t .   

3. Run the created image using the same network
   docker run --name my-application-container-name -p 8080:8080 -d image-name
   

### Prerequisites

Spring security knowledge

### Contact
snarinderpal040@gmail.com
+1 289-952-6243
