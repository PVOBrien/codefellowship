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

## Feature Task (Comprehensive

- [x] The site should have a splash page at the root route (/) that contains basic information about the site, as well as a link to the “sign up” page.
- [x] An ApplicationUser should have a username, password (will be hashed using BCrypt), firstName, lastName, dateOfBirth, bio, and any other fields you think are useful.
    - [x] dateOfBirth now implemented.
- [x] The site should allow users to create an ApplicationUser on the “sign up” page.
- [x] Your Controller should have an @Autowired private PasswordEncoder passwordEncoder; and use that to run passwordEncoder.encode(password) before saving the password into the new user.
- [x] The site should have a page which allows viewing the data about a single ApplicationUser, at a route like /users/{id}.
- [x] This should include a default profile picture, which is the same for every user, and their basic information.
- [x] Using the above cheat sheet, add the ability for users to log in to your app.
- [x] When a user is logged in, the app should display the user’s username on every page (probably in the heading).
- [x] Ensure that your homepage, login, and registration routes are accessible to non-logged in users. All other routes should be limited to logged-in users.
- [ ] The site should be well-styled and attractive.
- [x] The site should use reusable templates for its information. (At a minimum, it should have one Thymeleaf fragment that is used on multiple pages.)
   - [x] Not thoroughly, but is implemented.
- [x] The site should have a non-whitelabel error handling page that lets the user know, at minimum, the error code and a brief message about what went wrong.
- [x] Ensure that user registration also logs users into your app automatically.
- [x] Add a Post entity to your app.
- [x] A Post has a body and a createdAt timestamp.
- [x] A logged-in user should be able to create a Post, and a post should belong to the user that created it.
hint: this is a relationship between two pieces of data
- [x] A user’s posts should be visible on their profile page.

==== Day Three Tasks

- [x] Users can’t perform SQL injection or HTML injection with their posts
- [x] Allow users to follow others.
    - [ ] Implemented, but not visually reinforced atm.
- [x] Ensure there is some way (like a user's index page) that a user can discover other users on the service.
    - [x] Via a dropdown method atm.
    - [x] Button to be implemented. Already using some same skill by not rendering the post block if a logged in user is visiting another user's page.    
- [x] On a user profile page that does NOT belong to the currently logged-in user, display a “Follow” button. When a user clicks that follow button, the logged-in user is now following the viewed-profile-page user.
- [x] A user can visit an url (like /feed) to view all of the posts from the users that they follow.