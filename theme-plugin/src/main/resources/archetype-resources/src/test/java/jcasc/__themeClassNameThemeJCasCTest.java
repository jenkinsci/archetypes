package ${package}.jcasc;

import io.jenkins.plugins.casc.misc.junit.jupiter.WithJenkinsConfiguredWithCode;
import ${package}.$THEME_CLASS_NAMETheme;
import io.jenkins.plugins.thememanager.ThemeManagerFactory;

@WithJenkinsConfiguredWithCode
class $THEME_CLASS_NAMEThemeJCasCTest extends AbstractThemeJCasCTest {

    @Override
    protected Class<? extends ThemeManagerFactory> getThemeManagerFactory() {
        return $THEME_CLASS_NAMETheme.class;
    }
}
