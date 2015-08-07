## Project Description (copyright: Munish Gogna) ##
This project will serve as a ready made template for all who want to use EJB3, JPA, Hibernate and JSF (Richfaces) in their project deployed over JBoss AS 5+ version.

##### Who Can use this template? #####
  * All who want to use above technology stack for the very first time in their new Project.
  * All who want to migrate to JBoss AS from glassfish or tomcat or any other server.
  * All new comers to Java for learning purpose so that they can make maximum out of it.


---


For demo purpose the Project defines two domain entitities User and Preference (related to each other by 1:1 relation mapped by a foreign key)
```
User (id, name, email)
Preference (id, color, user_id)
```

---

The project has three modules:

| **Artifact** | **Summary** |
|:-------------|:------------|
| Template-ejb | Takes care of JPA and Hibernate mappings using annotations, Provides  services via stateless EJBs. |
| Template-war | The UI part of the project as shown in the screenshot below rendered by JBoss Richfaces. |
| Template-ear | Final artifact to be deployed over JBoss. |


### UI Design ###
![http://3.bp.blogspot.com/-8GBAtI94xL0/TaqJVyVzFMI/AAAAAAAAAU8/HQSSQhKXdR8/s1600/template.png](http://3.bp.blogspot.com/-8GBAtI94xL0/TaqJVyVzFMI/AAAAAAAAAU8/HQSSQhKXdR8/s1600/template.png)



---


#### Motivation behind this project ####

Following exceptions really pushed me towards this Project

1. I ran into the this problem trying to use mysql with Hibernate and JPA:
```
Caused by: java.lang.IllegalArgumentException: null source
at java.util.EventObject.<init>(EventObject.java:38)
at javax.sql.StatementEvent.<init>(StatementEvent.java:39)
at
com.mysql.jdbc.jdbc2.optional.JDBC4PreparedStatementWrapper.close(JDBC4PreparedStatementWrapper.java:70)
at com.caucho.sql.UserStatement.close(UserStatement.java:127)
at com.caucho.sql.UserPreparedStatement.close(UserPreparedStatement.java:450)
at org.hibernate.jdbc.AbstractBatcher.closePreparedStatement(AbstractBatcher.java:534)

Solution was to get mysql-connector-java(5.1.15) version placed in server/default/lib directory.
```

2. I had really difficult time trying to deploy a simple web application based on
JSF with Facelets and Rich Faces implementation under JBoss application server.
```
The problems lies in libs incompatibility/duplication.

Solution I found was:
1. Excluding jsf-api.jar, jsf-impl.jar and jstl.jar from war
2. Replacing jsf-facelets.jar v1.1.14 with v1.1.15B1
```

3. Incompatibility in Persistence jars. The library which uses hibernate, contains a package(ejb3-persistence.jar) which also uses the same class as in javaee-api-5.0-1.jar package.
```
java.lang.ClassCastException org.hibernate.ejb.HibernatePersistence cannot be cast to javax.persistence.spi.PersistenceProvider

Solution:
Exclude peristence-api from the generated artifacts, as JBoss is already shipped with the same.

<dependency>
<groupId>org.hibernate</groupId>
<artifactId>hibernate-entitymanager</artifactId>
<version>3.3.1.ga</version>
<exclusions>
	<exclusion>
	        <groupId>javax.persistence</groupId>
		<artifactId>persistence-api</artifactId>
	</exclusion>
	<exclusion>
		<groupId>jboss</groupId>
		<artifactId>jboss-common-core</artifactId>
	</exclusion>
	<exclusion>
		<groupId>jboss</groupId>
		<artifactId>javassist</artifactId>
	</exclusion>
</exclusions>
</dependency>

```

4. Class loading issues with Jboss
```

* Check Template-war/WEB-INF/jboss-classloading.xml 

*Following section in Template-ear/pom.xml

<jboss>
	<version>5</version>
	<loader-repository>com.jboss.jpa.poc:loader=Template-ear.ear</loader-repository>
	<loader-repository-config>java2ParentDelegation=true</loader-repository-config>
</jboss>

```

5. There were other small issues as well which I don't remember anymore. One I remember is getting exceptions related JTA (com.arjuna.ats.internal.jta related )
```
Solution I found is to exclude jta artifact from the final ear (check Template-ear/pom.xml)
```

## Running The Project ##

  * Check out the Project.
  * Build the Project (mvn clean install).
  * Rename datasource.xml to datasource-ds.xml and place the file in JBOSS-HOME/server/default/deploy folder.
  * Place the generated ear artifact in JBOSS-HOME/server/default/deploy folder.
  * Finally point your browser to http://localhost:8080/template



cheers,
Munish Gogna