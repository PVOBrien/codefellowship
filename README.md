# Creating a Spring Boot app with login functionality

- Uses JPA, Postgres, and Security (all Spring) to wire up login functionality. (Postgres is connected via heroku.)
- App currently starts at a homepage
    - you can go click to create a login
    - you can click to login.
    > NOTE: you are logged in, but app only build to this point.
- Password is hashed.

Routes

"/" - homepage.
"/signup" - goes to a page to signup

"/newuser" - POST map to create a new user with a hashed password, then to be redirected to a user detail page (not yet in place).