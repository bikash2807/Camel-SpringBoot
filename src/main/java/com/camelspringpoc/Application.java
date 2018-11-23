package com.camelspringpoc;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;

@SpringBootApplication
// load regular Spring XML file from the classpath
// Be careful with using "classpath:" or classpath*:" and fixed name or ant-style pattern. See PathMatchingResourcePatternResolver for details
// classpath:my.file returns the first file from classpath
// classpath*:my.file finds files from all classpath roots (/classes, /test-classes and jars)
// classpath:**/my*.file returns multiple files under the first classpath root (/classes OR /test-classes)
// classpath*:**/my*file returns matching files from all classpath roots
@ImportResource({"classpath:spring/application-context.xml"}) 
public class Application {
	private final static Logger log = LoggerFactory.getLogger(Application.class);

    /**
     * A main method to start this application.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
	
    /**
    * This is only a simple redirect to access swagger UI easier
    * from "/swagger-ui" to "/swagger-ui/index.html?url=/api/swagger&validatorUrl="
    */
    @Controller
    class SwaggerWelcome {
        @RequestMapping(
            "/swagger-ui"
        )
        public String redirectToUi() {
            return "redirect:/webjars/swagger-ui/index.html?url=/api/swagger&validatorUrl=";
        }
    }
    
    @RestController
    class SpringRestService {
    	@RequestMapping("/version")
    	public String versionInformation() {
    		return readGitProperties();
    	}
    }
    private String readGitProperties() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("git.properties");
        try {
            return readFromInputStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return "Version information could not be retrieved";
        }
    }
    private String readFromInputStream(InputStream inputStream)
    throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
	
}