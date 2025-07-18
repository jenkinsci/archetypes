package ${package};

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import ${package}.playwright.AppearancePage;
import ${package}.playwright.PlaywrightConfig;
import ${package}.playwright.Theme;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.junit.jupiter.WithJenkins;

@WithJenkins
@UsePlaywright(PlaywrightConfig.class)
public class $THEME_CLASS_NAMEThemeTest {

  @Test
  void themeLoads(JenkinsRule j, Page p) {
    Theme theme = Theme.of(new $THEME_CLASS_NAMETheme.DescriptorImpl(), Theme.CssVariable.background("white"));
    new AppearancePage(p, j.jenkins.getRootUrl())
        .goTo()
        .themeIsPresent(theme)
        .selectTheme(theme)
        .themeIsApplied(theme);
  }
}
