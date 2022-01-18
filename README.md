# Mettle project task

This microservice is for creating simple features, which can be turned on or off, by default they are off.
 By default, this offers Admin user and other user. With Admin user you can create Feature, view all features and turn them on or off. With user, you can only view all Features, which are turned on.
This service uses simple H2 in memory database for data storage. It has basic HTML web interface and also built in H2 console.

This service is built like little web application, however it is easily convertible to backend REST service by changing controller methods. Currently, all functions you can do using provided web interface.

Run application using command:

```
./gradlew bootRun
```

Application should be available at:

```
http://localhost:8080/
```

Login using one of the following credentials:

```
Username: user
Password: user
```

or

```
Username: admin
Password: admin
```

When logged with admin username you have admin rights. You can add several features, then click on show. Select values
from drop down boxes and click on Save button to enable or disable them.

When logged with user, you can only view enabled values created by admin.

For any authenticated user there are link to DB console available at the top of the page.