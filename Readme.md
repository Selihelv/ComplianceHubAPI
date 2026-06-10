<!-- TOC -->
  * [Description of the project](#description-of-the-project)
  * [Class Diagram](#class-diagram)
  * [Setup](#setup)
    * [Prerequisites](#prerequisites)
  * [Technologies used](#technologies-used)
  * [Controllers and Route structures](#controllers-and-route-structures)
    * [Authentication:](#authentication)
    * [User:](#user)
    * [UserProfile:](#userprofile)
    * [Role:](#role)
    * [Regulation:](#regulation)
    * [ComplianceDocument:](#compliancedocument)
    * [AI Chat:](#ai-chat)
    * [Greet:](#greet)
    * [Any other requests: authenticated users](#any-other-requests-authenticated-users)
  * [Extra links](#extra-links)
  * [Future work](#future-work)
  * [Resources](#resources)
  * [Team Members](#team-members)
<!-- TOC -->





## Description of the project
ComplianceHub API is a RESTful backend application built with Spring Boot, designed to streamline compliance document management for marketplace sellers operating across European markets.

The platform allows sellers to submit and track compliance documents required by each marketplace's local regulations, while internal agents review submissions and regulation managers maintain the regulatory framework per country.

The system supports four roles — Seller, Agent, Regulation Manager, and Admin — each with their own scoped permissions enforced through stateless JWT-based authentication and Spring Security.

An integrated AI chat feature powered by Spring AI and OpenAI allows users to interact with the platform through natural language, with access to real-time tools such as date/time lookup and user data retrieval.



## Class Diagram




## Setup
### Prerequisites
- Java 25+
- Maven 3.8+
- MySQL 8+
- An OpenAI API key (for the Spring AI chat feature)

1. Clone the repository
2. Configure the Database
3. Set the application properties
4. Configure the OpenAI API key
5. Run the application


## Technologies used
- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security (JWT-based authentication)
- Spring Validation
- Spring Boot DevTools
- Spring AI
- MySQL Database
- Maven
- Lombok


## Controllers and Route structures
### Authentication:
| Method | **Endpoint** | Access |
|--------|--------------|--------|
| POST   | /api/login   | Public |

### User:
| Method | **Endpoint**                                    | Access         |
|--------|-------------------------------------------------|----------------|
| GET    | /api/users                                      | Agent          |
| POST   | /api/users                                      | Public         |
| GET    | /api/users/compliance-status/{complianceStatus} | Agent          |
| GET    | /api/users/{id}                                 | Seller + Agent |
| PUT    | /api/users/{id}                                 | Seller + Agent |
| DELETE | /api/users/{id}                                 | Admin          |

### UserProfile:
| Method | **Endpoint**                       | Access         |
|--------|------------------------------------|----------------|
| GET    | /api/user-profile                  | Agent          |
| POST   | /api/user-profile                  | Public         |
| GET    | /api/user-profile/by-user/{userId} | Seller + Agent |
| GET    | /api/user-profile/{id}             | Seller + Agent |
| PUT    | /api/user-profile/{userId}         | Seller         |

### Role:
| Method | **Endpoint**               | Access |
|--------|----------------------------|--------|
| POST   | /api/roles                 | Admin  |
| POST   | /api/roles/add-to-user     | Admin  |
| DELETE | /api/roles/delete/roleName | Admin  |

### Regulation:
| Method | **Endpoint**                              | Access                     |
|--------|-------------------------------------------|----------------------------|
| GET    | /api/regulation                           | Agent + Regulation Manager |
| POST   | /api/regulation                           | Regulation Manager         |
| GET    | /api/regulation/marketplace/{marketplace} | Regulation Manager         |
| GET    | /api/regulation/{id}                      | Regulation Manager         |
| PUT    | /api/regulation/{id}                      | Regulation Manager         |
| DELETE | /api/regulation/{id}                      | Admin + Regulation Manager |

### ComplianceDocument:
| Method | **Endpoint**                                      | Access        |
|--------|---------------------------------------------------|---------------|
| GET    | /api/compliance-document                          | Agent         |
| POST   | /api/compliance-document                          | Seller        |
| GET    | /api/compliance-document/document-status/{status} | Agent         |
| GET    | /api/compliance-document/document-type/{type}     | Agent         |
| GET    | /api/compliance-document/user/{userId}            | Agent         |
| GET    | /api/compliance-document/{id}                     | Agent         |
| PUT    | /api/compliance-document/{id}                     | Agent         |
| DELETE | /api/compliance-document/{id}                     | Agent + Admin |


### AI Chat:
| Method | **Endpoint**                               | Access              |
|--------|--------------------------------------------|---------------------|
| GET    | /orion/hello                               | Public              |
| POST   | /orion/compliance_copilot/{conversationId} | Authenticated users |


### Greet:
| Method | **Endpoint**        | Access              |
|--------|---------------------|---------------------|
| GET    | /api/greet          |  Public             |
| GET    | /api/greet/personal | Authenticated users |

### Any other requests: authenticated users


## Extra links
- Project Management Board:
https://trello.com/invite/b/6a117d920f331643e15e992a/ATTIc50f4bad75791aed6092ce5c068f62342F0A984D/proyecto-final-backend

- Presentation Slides:


## Future work
1. **Global exception handler** — add a global exception handler; structured error responses across all endpoints instead of relying on default Spring error pages.
2. **Automatic compliance status recalculation** — when an Agent reviews a compliance document, the owning seller's complianceStatus should be recalculated automatically based on the status of all their documents, rather than being set manually.
3. **Document expiry and renewal** — add expiry dates to compliance documents and automatically flag or downgrade sellers whose documents have outdated.
4. **Role-scoped AI chatbot** — extend the chatbot so that its available tools and the data it can access are scoped to the authenticated user's role (e.g. agents see all users, sellers see only themselves).
5. **Frontend client** — build a web or mobile interface that consumes the API, providing a visual compliance dashboard for sellers and a document review queue for agents.


## Resources


## Team Members
1. Developer: Selim Helvacı
2. Mentor: Salvatore Corsaro

