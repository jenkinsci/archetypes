package $package;

import static org.junit.jupiter.api.Assertions.*;

import org.htmlunit.html.HtmlForm;
import org.htmlunit.html.HtmlTextInput;
import org.junit.jupiter.api.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.junit.jupiter.WithJenkins;

@WithJenkins
class SampleConfigurationTest {

    /**
     * Tries to exercise enough code paths to catch common mistakes:
     * <ul>
     * <li>missing {@code load}
     * <li>missing {@code save}
     * <li>misnamed or absent getter/setter
     * <li>misnamed {@code textbox}
     * </ul>
     */
    @Test
    void uiAndStorage(JenkinsRule jenkins) throws Throwable {
        assertNull(SampleConfiguration.get().getLabel(), "not set initially");
        try (JenkinsRule.WebClient client = jenkins.createWebClient()) {
            HtmlForm config = client.goTo("configure").getFormByName("config");
            HtmlTextInput textbox = config.getInputByName("_.label");
            textbox.setText("hello");
            jenkins.submit(config);
            assertEquals("hello", SampleConfiguration.get().getLabel(), "global config page let us edit it");
        }

        jenkins.restart();

        assertEquals("hello", SampleConfiguration.get().getLabel(), "still there after restart of Jenkins");
    }
}
