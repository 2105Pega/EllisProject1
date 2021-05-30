FROM tomcat:9.0.46-jdk8
ADD ./target/JDBCBank-1.0.war /usr/local/tomcat/webapps/JDBCBank.war
CMD ["catalina.sh", "run"]
