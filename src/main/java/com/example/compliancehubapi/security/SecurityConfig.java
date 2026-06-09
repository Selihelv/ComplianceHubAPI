package com.example.compliancehubapi.security;

import com.example.compliancehubapi.security.filters.CustomAuthenticationFilter;
import com.example.compliancehubapi.security.filters.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * This is the main configuration class for security in the application. It enables web security,
 * sets up the password encoder, and sets up the security filter chain.
 */
@Configuration
@EnableWebSecurity // indicates it is a security config class using spring web security
@RequiredArgsConstructor
public class SecurityConfig {

    // UserDetailsService is an interface provided by Spring Security that defines a way to retrieve user information
    private final UserDetailsService userDetailsService;

    // Autowired instance of the AuthenticationManagerBuilder (provided by Spring Security)
    private final AuthenticationManagerBuilder authManagerBuilder;


    /**
     * Bean definition for AuthenticationManager
     *
     * @param authenticationConfiguration the instance of AuthenticationConfiguration
     * @return an instance of the AuthenticationManager
     * @throws Exception if there is an issue getting the instance of the AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Bean definition for SecurityFilterChain
     *
     * @param http the instance of HttpSecurity
     * @return an instance of the SecurityFilterChain
     * @throws Exception if there is an issue building the SecurityFilterChain
     */
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CustomAuthenticationFilter instance created
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authManagerBuilder.getOrBuild());

        // set the URL that the filter should process
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");


        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(STATELESS))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/login/**").permitAll()// public endpoint to login

                         //User
                        .requestMatchers(GET, "/api/users").hasAnyAuthority("Agent") // only agents can get all users
                        .requestMatchers(POST, "/api/users").permitAll() // public endpoint to create a new user.
                        .requestMatchers(GET, "/api/users/compliance-status/{complianceStatus}").hasAnyAuthority("Agent")
                        .requestMatchers(GET, "/api/users/{id}").hasAnyAuthority("Seller","Agent")
                        .requestMatchers(PUT, "/api/users/{id}").hasAnyAuthority("Seller","Agent")
                        .requestMatchers(DELETE, "/api/users/{id}").hasAnyAuthority("Agent")

                        // UserProfile
                        .requestMatchers(GET,"/api/user-profile").hasAnyAuthority("Agent")
                        .requestMatchers(POST,"/api/user-profile").permitAll()
                        .requestMatchers(GET,"/api/user-profile/by-user/{userId}").hasAnyAuthority("Seller","Agent")
                        .requestMatchers(GET,"/api/user-profile/{id}").hasAnyAuthority("Seller", "Agent")
                        .requestMatchers(PUT,"/api/user-profile/{userId}").hasAnyAuthority("Seller")
                        .requestMatchers(DELETE, "/api/user-profile/{userId}").hasAnyAuthority( "Agent") //if Sellers delete their User, this should be deleted also

                        // Role
                        .requestMatchers(POST, "/api/roles").hasAnyAuthority("ADMIN")
                        .requestMatchers(POST, "/api/roles/add-to-user").hasAnyAuthority("ADMIN")

                        //Regulation
                        .requestMatchers(GET,"/api/regulation").hasAnyAuthority("Regulation Manager")
                        .requestMatchers(POST, "/api/regulation").hasAnyAuthority("Regulation Manager")
                        .requestMatchers(GET, "/api/regulation/marketplace/{marketplace}").hasAnyAuthority("Regulation Manager")
                        .requestMatchers(GET, "/api/regulation/{id}").hasAnyAuthority("Regulation Manager")
                        .requestMatchers(PUT, "/api/regulation/{id}").hasAnyAuthority("Regulation Manager")
                        .requestMatchers(DELETE, "/api/regulation/{id}").hasAnyAuthority("Regulation Manager")

                        //ComplianceDocument
                        .requestMatchers(GET,"/api/compliance-document").hasAnyAuthority("Agent")
                        .requestMatchers(POST, "/api/compliance-document").hasAnyAuthority("Seller")
                        .requestMatchers(GET, "/api/compliance-document/document-status/{status}").hasAnyAuthority("Agent")
                        .requestMatchers(GET, "/api/compliance-document/document-type/{type}").hasAnyAuthority("Agent")
                        .requestMatchers(GET,"/api/compliance-document/user/{userId}").hasAnyAuthority("Agent")
                        .requestMatchers(GET, "/api/compliance-document/{id}").hasAnyAuthority("Agent")
                        .requestMatchers(PUT, "/api/compliance-document/{id}").hasAnyAuthority("Agent")
                        .requestMatchers(DELETE,"/api/compliance-document/{id}").hasAnyAuthority("Agent")

                        //CHAT
                        .requestMatchers(GET, "/chat/**").permitAll()
                        .requestMatchers(POST, "/chat/chatbot/{conversationId}").authenticated()

                        //GREET
                        .requestMatchers("/api/greet").permitAll()
                        .requestMatchers("/api/greet/personal").authenticated()

                       // .anyRequest().permitAll());
                        .anyRequest().authenticated()); // any other endpoints require authentication

        // add the custom authentication filter to the http security object
        http.addFilter(customAuthenticationFilter);

        // Add the custom authorization filter before the standard authentication filter.
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        // Build the security filter chain to be returned.
        return http.build();
    }
}
