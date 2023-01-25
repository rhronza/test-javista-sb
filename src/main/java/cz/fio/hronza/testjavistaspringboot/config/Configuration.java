package cz.fio.hronza.testjavistaspringboot.config;

import cz.fio.hronza.testjavistaspringboot.TestJavista;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = TestJavista.class)
class TestJavista2Configuration {
}
