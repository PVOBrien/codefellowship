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

## Feature Task (Comprehensive

- [x] The site should have a splash page at the root route (/) that contains basic information about the site, as well as a link to the “sign up” page.
- [x] An ApplicationUser should have a username, password (will be hashed using BCrypt), firstName, lastName, dateOfBirth, bio, and any other fields you think are useful.
    - [x] dateOfBirth now implemented.
- [x] The site should allow users to create an ApplicationUser on the “sign up” page.
- [x] Your Controller should have an @Autowired private PasswordEncoder passwordEncoder; and use that to run passwordEncoder.encode(password) before saving the password into the new user.
- [x] The site should have a page which allows viewing the data about a single ApplicationUser, at a route like /users/{id}.
- [ ] This should include a default profile picture, which is the same for every user, and their basic information.
- [x] Using the above cheat sheet, add the ability for users to log in to your app.
- [ ] When a user is logged in, the app should display the user’s username on every page (probably in the heading).
- [x] Ensure that your homepage, login, and registration routes are accessible to non-logged in users. All other routes should be limited to logged-in users.
- [ ] The site should be well-styled and attractive.
[-] The site should use reusable templates for its information. (At a minimum, it should have one Thymeleaf fragment that is used on multiple pages.)
   - [ ] ^^^ Fragment created, but now functioning.
- [ ] The site should have a non-whitelabel error handling page that lets the user know, at minimum, the error code and a brief message about what went wrong.
- [x] Ensure that user registration also logs users into your app automatically.
- [x] Add a Post entity to your app.
- [x] A Post has a body and a createdAt timestamp.
- [x] A logged-in user should be able to create a Post, and a post should belong to the user that created it.
hint: this is a relationship between two pieces of data
- [x] A user’s posts should be visible on their profile page.