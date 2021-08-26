# Pay My Buddy
<p><b>PayMyBuddy</b> We make moving your money easy!</p>


![UML_Diagram](assets/paymybuddy_banner_image1.png "Pay My Buddy Banner")


<p> The purpose of the project is to build an App that would allow customers to transfer money, to manage their finances or pay their friends with atmost ease. </p>



   * [Introduction and Quick Overview](#introduction)
   * [Technical Specifications](#technical-specifications)
   * [Project Milestones](#project-milestones)
   * [Preliminary Design Sketches](#preliminary-design-sketches)
   * [Project Milestones](#project-milestones)
   * [Preliminary Design Sketches](#preliminary-design-sketches)
   * [Functional Scope of the Requirements](#functional-scope-of-the-requirements)
   * [Activity Diagram](#activity-diagram)
   * [User Case Diagram](#user-case-diagram)
   * [Activity Diagram Elaborated Sketching](#activity-diagram-elaborated-sketching)
   * [Login View Template Mockup Sketch API Endpoints and Development Features Analysis](#login-view-template-mockup-sketch-api-endpoints-and-development-features-analysis)
   * [SignUp View Template Mockup Sketch API Endpoints and Development Features Analysis](#signup-view-template-mockup-sketch-api-endpoints-and-development-features-analysis)
   * [Detailed features requirement Analysis on API endpoints and validation rules](#detailed-features-requirement-analysis-on-api-endpoints-and-validation-rules)
   * [Schema MVC Software Architecture Design](#schema-mvc-software-architecture-design)
   * [UML class diagram MCD](#uml-class-diagram-mcd)
   * [UML physical Data Model MPD Entity RelationShip Diagram ER](#uml-physical-data-model-mpd-entity-relationship-diagram-er)
   * [SQL Scripts](#sql-scripts)
   * [UI visuals](#ui-visuals)
   * [Endpoints Information](#endpoints-information)
   * [Actuator Info](#actuator-info)
   * [API Documentation Swagger](#api-documentation-swagger)
   * [Reference Documentation](#reference-documentation)
   * [Reference Guides](#reference-guides)
   

Introduction
===

The main goal is to commit on participating in the different phases of the software development cycle that involves:<br />
	![📝 PLANNING](https://img.shields.io/badge/%F0%9F%93%9D-PLANNING-blueviolet)(requirements definition - Functional & Technical requirements),<br />
	![🔨 ANALYSIS](https://img.shields.io/badge/%F0%9F%94%A8-%20ANALYSIS-blue) (objectives, opportunities, constraints, resources, requirements, milestones ...),<br />
	![✒️ DESIGN](https://img.shields.io/badge/%E2%9C%92%EF%B8%8F-%20DESIGN-critical) (WireFraming, User Stories, Data flow diagram - DFD, Technical Design - Software blueprint, UI prototyping, DDD principle ...),<br />
	![🔨 CODING IMPLEMENTATION](https://img.shields.io/badge/%F0%9F%94%A8-%20CODING%20IMPLEMENTATION-success) (Spring boot framework - backend API development),<br />
	![❓ ❕  FUNCTIONAL TESTING](https://img.shields.io/badge/%E2%9D%93%20%E2%9D%95-FUNCTIONAL%20TESTING-informational) (Unit, Integration Testing... Code quality review, report generation, Test summary..),<br />
	![🔑 DEPLOYMENT](https://img.shields.io/badge/%F0%9F%94%91-DEPLOYMENT-red) - pre-release evaluation with domain experts on its quality standards to deliver as a functionally strong backend Application API.<br />


Technical Specifications
===
Keeping in mind the obligations of the production environment, the following technologies are used:<br />
![✒️ DESIGN & ANALYSIS:](https://img.shields.io/badge/%E2%9C%92%EF%B8%8F-DESIGN%20%26%20ANALYSIS%3A-orange)<br />
![ Drawoio](https://img.shields.io/badge/%E2%9C%92%EF%B8%8F-Drawio-blue) ![Lucidchart](https://img.shields.io/badge/%E2%9C%8F%EF%B8%8F-LucidChart-yellow) ![Trello](https://img.shields.io/badge/%F0%9F%93%8D-Tello-yellowgreen)<br /><br />

![🔨 CODING IMPLEMENTATION & DEVELOPMENT:](https://img.shields.io/badge/%F0%9F%94%A8-CODING%20IMPLEMENTATION%20%26%20DEVELOPMENT-red)<br />
![Java Version](https://img.shields.io/badge/Java-1.8-blue)
![Maven Version](https://img.shields.io/badge/Maven-2.7.7-yellow)
![SpringBoot Version](https://img.shields.io/badge/Spring%20Boot-2.4.3-red)
![MySQL](https://img.shields.io/badge/MySQL--cyan)
![H2 DB](https://img.shields.io/badge/H2-Database-important)
![TomCat](https://img.shields.io/badge/TomCat%3A-9090-brightgreen)
![Spring Security](https://img.shields.io/badge/%F0%9F%94%91-Spring%20Security-critical)
![Spring DATA JPA](https://img.shields.io/badge/%E2%9B%88%EF%B8%8F-Spring%20DATA%20JPA-blue)
![Thymeleaf](https://img.shields.io/badge/%F0%9F%8C%B1-Thymeleaf-brightgreen)
![HTML](https://img.shields.io/badge/%F0%9F%92%BB-HTML-success)
![CSS](https://img.shields.io/badge/%F0%9F%91%91-CSS-orange)<br /><br />

![❕ FUNCTIONAL TESTING](https://img.shields.io/badge/%E2%9C%82%EF%B8%8F-%E2%9D%95%20FUNCTIONAL%20TESTING%20-critical)<br />
![SureFire](https://img.shields.io/badge/%F0%9F%9B%A0%EF%B8%8F-SureFire-informational)
![Jacoco](https://img.shields.io/badge/%F0%9F%94%A7-Jacoco-blueviolet)
![SonarLint](https://img.shields.io/badge/%F0%9F%8F%B9-SonarLint-ff69b4t)
![CheckStyle](https://img.shields.io/badge/%F0%9F%92%8A-CheckStyle-ff69b4t)
![SpotBugs / FindBugs](https://img.shields.io/badge/%F0%9F%90%9B-SpotBugs%20%2F%20FindBugs-important)
![Swagger API](https://img.shields.io/badge/%F0%9F%93%8A-Swagger%20API-red)
![Actuator](https://img.shields.io/badge/%F0%9F%93%89-Actuator-red)
![BCrypt Encryption](https://img.shields.io/badge/%F0%9F%94%8F-BCrypt%20Encryption-yellowgreen)


Project Milestones
===
![Milestones](assets/requirements_milestones_planning.PNG "Project Milestones")<br />

Preliminary Design Sketches
===
Functional Scope of the Requirements
===
![Functional Scope](assets/functional_scope.PNG "Functional Scope")<br />


Activity Diagram
===

![Activity Diagram](assets/activity_diagram.PNG "Activity Diagram")<br />


User Case Diagram
===

![User Case Diagram](assets/user_case_diagram.PNG "User Case Diagram")<br />


Activity Diagram Elaborated Sketching
===

![Activity Diagram Elaborated Sketching](assets/activity_diagram_detailed.PNG "Activity Diagram Elaborated Sketching")<br />


Login View Template Mockup Sketch API Endpoints and Development Features Analysis
===

![Login Endpoint](assets/endpoints_login.PNG "Login Endpoint")<br />


SignUp View Template Mockup Sketch API Endpoints and Development Features Analysis
===

![SignUp Endpoint](assets/enpoints_signup.PNG "SignUp Endpoint")<br />


Detailed features requirement Analysis on API endpoints and validation rules
===

![API endpoints and validation rules](assets/enpoints_validation_detailed.PNG "API endpoints and validation rules")<br />


Schema MVC Software Architecture Design
===

![Schema MVC Software Architecture Design](assets/schema_mvc.PNG "Schema MVC Software Architecture Design")<br />


UML class diagram MCD
===

![UML class diagram MCD](assets/mcd_class_diagram.PNG "UML class diagram MCD")<br />


UML physical Data Model MPD Entity RelationShip Diagram ER
===

![UML physical Data Model MPD Entity RelationShip Diagram ER](assets/mpd.PNG "MPD Entity RelationShip Diagram")<br />

SQL Scripts
===


// TODO

UI visuals
===
// TODO<br />

Endpoints Information
===
// TODO


Actuator Info
===
// TODO

API Documentation Swagger
===
// TODO

### Authors
// TODO

### versions
// TODO

### License
// TODO



Reference Documentation
===
For further reference, consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.4/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.5.4/reference/htmlsingle/#using-boot-devtools)
* [Validation](https://docs.spring.io/spring-boot/docs/2.5.4/reference/htmlsingle/#boot-features-validation)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.5.4/reference/htmlsingle/#production-ready)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.5.4/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.5.4/reference/htmlsingle/#boot-features-security)
* [Thymeleaf](https://docs.spring.io/spring-boot/docs/2.5.4/reference/htmlsingle/#boot-features-spring-mvc-template-engines)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.4/reference/htmlsingle/#boot-features-developing-web-applications)

Reference Guides
===
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

