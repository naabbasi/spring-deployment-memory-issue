# spring-deployment-memory-issue

#### version 0.0.1
<ul>
    <li>Spring boot with starter and web dependencies</li>
</ul>

#### version 0.0.2
<ul>
    <li>Excluded spring default logging</li>
    <li>Destroy context by calling </li>
    <pre>
        try {
            ContextLoader contextLoader = new ContextLoader();
            contextLoader.closeWebApplicationContext(Objects.requireNonNull(this.webApplicationContext.getServletContext()));
        } catch (Exception e) {
            this.logUtils.log("Exception occurred: {}", e.getMessage());
        }
    </pre>
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

<p>Application objects are being released either explicitly destroy context via application event lister or not</p>

#### version 0.0.3
<ul>
    <li>Added springboot starter data jpa and postgresql dependencies</li>
    <li>Releasing all resources with/without entities and repository</li>
</ul>
