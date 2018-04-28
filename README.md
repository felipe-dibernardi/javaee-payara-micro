#JavaEE7 and Payara Micro RESTFul example with Basic Authentication and Arquillian Test Suite

##Index
[1. **Prerequisites**](#1.prequisites)
[2. **Running instructions**](#2.running-instructions)
3. **API Documentation**

##1.Prerequisites

* Java 7+ JDK
* MySQL

##2.Running Instructions

1. Clone project https://github.com/felipe-dibernardi/javaee-payara-micro.git

2. Run **mvn clean install** at pom.xml level folder

3. Create project and test database structure using **init.sql** script located at {{PROJECT\_PATH}}/src/main/sql/init.sql

4. Create an UberJar using ** java -jar {{MVN\_.M2\_PATH}}/repository/fish/payara/extras/payara-micro/4.1.2.181/payara-micro-4.1.2.181.jar --deploy {{PROJECT\_PATH}}/target/condominio-1.0.0-SNAPSHOT.war --domainConfig {{PROJECT\_PATH}}/src/main/config/domain.xml --outputUberJar {{APPLICATION\_NAME}}.jar**

5. Run **java -jar {{APPLICATION\_NAME}}.jar**

##3. API Documentation

###3.1. Users

####3.1.1. List All Users

Get a list of all Users.

* **URL**

  /users/

* **Method:**

  GET

* **Success Response:**

  * **Code:** 200 OK
  * **Content:** [{ id : [numeric], username: [alphanumeric], password : [alphanumeric], type : [string] }]

* **Error Response:**

   * **Code:** 401 UNAUTHORIZED

####3.1.2. Find User by Id

Find a User by Id.

* **URL**

  /users/{id}

* **Method:**

  GET

*  **URL Params**

   **Required:**

   `id=[integer]`

* **Success Response:**

  * **Code:** 200 OK
  * **Content:** [{ id : [numeric], username: [alphanumeric], password : [alphanumeric], type : [string] }]

* **Error Response:**

   * **Code:** 401 UNAUTHORIZED

####3.1.3. Find User by Username

Find a User by Username.

* **URL**

  /users/find?username={username}

* **Method:**

  GET

*  **URL Params**

   **Required:**

   `username=[alphanumeric]`

* **Success Response:**

  * **Code:** 200 OK
  * **Content:** [{ id : [numeric], username: [alphanumeric], password : [alphanumeric], type : [string] }]

* **Error Response:**

   * **Code:** 401 UNAUTHORIZED

####3.1.4. Post User

   Insert a new User.

   * **URL**

     /users/

   * **Method:**

     POST

  * **Data Params**

    { username: [alphanumeric], password : [alphanumeric], type : [string] }

   * **Success Response:**

     * **Code:** 201 CREATED
     * **Content:** [{ id : [integer], username: [alphanumeric], password : [alphanumeric], type : [string] }]

   * **Error Response:**

      * **Code:** 401 UNAUTHORIZED

      * **Code:** 500 INTERNAL SERVER ERROR

####3.1.5. Put User

   Update a User.

   * **URL**

     /users/

   * **Method:**

     PUT

  * **Data Params**

    {id: [integer], username: [alphanumeric], password : [alphanumeric], type : [string] }

   * **Success Response:**

     * **Code:** 200 OK
     * **Content:** [{ id : [integer], username: [alphanumeric], password : [alphanumeric], type : [string] }]

   * **Error Response:**

      * **Code:** 401 UNAUTHORIZED

      * **Code:** 500 INTERNAL SERVER ERROR

####3.1.6. Remove User

   Delete a User.

   * **URL**

     /users/remove/{id}

   * **Method:**

     DELETE

   *  **URL Params**

   **Required:**

   `id=[integer]`

   * **Success Response:**

     * **Code:** 204 No Content

   * **Error Response:**

      * **Code:** 401 UNAUTHORIZED

      * **Code:** 500 INTERNAL SERVER ERROR
