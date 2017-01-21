package de.zalando.project.selenium.driver.factory;

import de.zalando.project.selenium.driver.ParameterKey;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DesiredCapabilitiesFactory {

    public DesiredCapabilities createDesiredCapabilities(Map<String, String> driverParameters) {
        final Map<String, String> capabilitiesParameters = driverParameters.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(ParameterKey.DRIVER_DESIRED_CAPABILITIES_PREFIX))
                .collect(Collectors.toMap(entry -> entry.getKey().substring(entry.getKey().lastIndexOf(".") + 1), Map.Entry::getValue));

        final DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        capabilitiesParameters.entrySet().forEach(entry -> desiredCapabilities.setCapability(entry.getKey(), entry.getValue()));
        desiredCapabilities.setCapability("name", driverParameters.get(ParameterKey.TEST_NAME));

        return desiredCapabilities;
    }
}
