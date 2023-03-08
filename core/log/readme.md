<style>
r { color: Red }
</style>
## Config slf4j Facade 
### Dependency
The interface dependency is
```xml
<dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-api</artifactId>
  <version>1.7.36</version>
</dependency>
```
Implement dependency
```xml
<dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-log4j12</artifactId>
  <version>1.7.36</version>
</dependency>
```
###
Trouble shoot
> <r> Failed to load class "org.slf4j.impl.StaticLoggerBinder </r>

Thisome happens when you only put interface dependency in pom file

> <r> No appenders could be found for logger </r>

Need to config log appender, information put in log4j.properties file and stay in resources folder
