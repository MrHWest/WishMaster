# Database information
## Environment variables
The following environment variables are needed to connect to the database:
### DB_URL
The URL to the database. Example: *localhost:3306*
### DB_UID
The user ID needed to connect to database
### DB_PWD
The password associated with the user ID.

## Initial database setup
Prior to running the application, the database needs to be set up.
This can be achieved by running the SQL query in *src/main/resources/mySQL/create-db.sql*


After the database has been set up, you can add dummy data by running the SQL query in *src/main/resources/mySQL/insert-dummy-data.sql* 