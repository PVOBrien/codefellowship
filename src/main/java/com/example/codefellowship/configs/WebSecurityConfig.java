package com.example.codefellowship.configs;
// import com.example.codefellowship.configs.UserDetailsServiceImpl; TODO: what is this for?
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    // all ^^^ is necessary boilerplate wiring

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .and()
                .logout(); // this then creates a built-in default logout route at /logout.
    }
}

//                .antMatchers("/gotouserinfo").permitAll() add to .antMatchers for possible testing.
//                .anyRequest().authenticated() READS: any (OTHER) request must be authenticated.

//========= If necessary, swap in below code accordingly.

//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        http
//                .cors().disable()// whitelist pages
//                .csrf().disable()// cross site resource forgery
//                .authorizeRequests() // all lines until AND are connected
//                .antMatchers("/").permitAll() // permit home route
//                .antMatchers("/newuser", "/login").permitAll() // permit them
//                .anyRequest().authenticated() // force login
//                .and()
//                .formLogin() // settings about login
//                .loginPage("/login") // login will live on /login // the form must have an action of /login too
//                // this gets rid of the pretty login
//                .defaultSuccessUrl("/")
//                .and()
//                .logout(); // this creates a get route of /logout
//    }