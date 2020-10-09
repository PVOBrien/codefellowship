# Creating a Spring Boot app with login functionality

- Uses JPA, Postgres, and Security (all Spring) to wire up login functionality. (Postgres is connected via heroku.)
- App currently starts at a homepage
    - you can go click to create a login
    - you can click to login.
    > NOTE: you are logged in, but app only build to this point.
- Password is hashed.

# How to Run
## ./gradlew bootRun
^^^ do so within the folder that has the gradlew file. It seems to take some time to spool up (it connects to heroku postgresql, probably impacts boot time).

Can also be run within intelliJ.

## Application Features

- A home page upon arrival at "/" where a user can sign up or login.
- The "/signup" route creates a database entry (with a hashed password via Bcrypt hashing and a unique ID) and then sends them to the "/login" route so that...
- On the "/login" route a user can login, at which point the following routes are available:
    - "/user" Where they go to upon successful connection. Here a user can see their user account details
    - "/post" The post button allows them to type in the above box to leave a message.
    - "/follow" a user can select a fellow user from the dropdown and choose another user to follow.
    - "/feed" The "See Feed" button goes to a feed of all of their followed user's posts.
    - "/seeotheruser" Allows a user to choose another user and go to their user details. On this route, the page also has a dedicated "/follow" route specific to that user's page.