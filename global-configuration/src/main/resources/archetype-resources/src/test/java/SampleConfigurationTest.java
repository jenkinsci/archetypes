package $package;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.jvnet.hudson.test.RestartableJenkinsRule;

public class SampleConfigurationTest {

    @Rule
    public RestartableJenkinsRule rr = new RestartableJenkinsRule();

    @Test
    public void uiAndStorage() {
        rr.then(r -> {
            assertNull("not set initially", SampleConfiguration.get().getLabel());
            HtmlForm config = r.createWebClient().goTo("configure").getFormByName("config");
            HtmlTextInput textbox = config.getInputByName("_.label");
            textbox.setText("hello");
            r.submit(config);
            assertEquals("global config page let us edit it", "hello", SampleConfiguration.get().getLabel());
        });
        rr.then(r -> {
            assertEquals("still there after restart of Jenkins", "hello", SampleConfiguration.get().getLabel());
        });
    }

}
