package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features", // Ruta a los .feature
        glue = {"steps"}, // Paquete donde están los Step Definitions
        plugin = {
                "pretty", // Formato legible en consola
                "html:target/cucumber-reports.html", // Reporte HTML
                "json:target/cucumber.json", // Reporte JSON
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        },

        monochrome = true, // Limpia el output en consola
        tags = "@TradeMe" // Ejecutar solo escenarios con este tag
)
public class TestRunner {
}
