package $package;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import org.apache.commons.io.FileUtils;
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

        jenkins = restartJenkins(jenkins);

        assertEquals("hello", SampleConfiguration.get().getLabel(), "still there after restart of Jenkins");

        FileUtils.deleteQuietly(jenkins.getInstance().getRootDir());
    }

    private static JenkinsRule restartJenkins(JenkinsRule currentJenkinsRule) throws Throwable {
        // backup current instance
        File backup = new File(currentJenkinsRule.getInstance().getRootDir() + "_" + System.currentTimeMillis());
        FileUtils.copyDirectory(currentJenkinsRule.getInstance().getRootDir(), backup);

        // shutdown and cleanup current instance
        currentJenkinsRule.after();

        // init new instance with previous port and home
        JenkinsRule newJenkinsRule = new JenkinsRule() {
            @Override
            public void before() throws Throwable {
                this.localPort = currentJenkinsRule.getURL().getPort();
                this.testDescription = currentJenkinsRule.getTestDescription();
                super.before();
            }
        }.withExistingHome(backup);

        newJenkinsRule.before();

        // delete backup
        FileUtils.deleteQuietly(backup);

        return newJenkinsRule;
    }
}
