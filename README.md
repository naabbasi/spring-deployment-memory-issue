# spring-deployment-memory-issue

#### version 0.0.2
<ul>
    <li>Excluded spring default logging</li>
    <li>Destroy context by calling </li>
</ul>
<code>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
        <exclusions>
            <exclusion>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-logging</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
</code>