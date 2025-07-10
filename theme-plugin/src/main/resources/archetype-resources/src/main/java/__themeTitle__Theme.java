package ${package};

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import io.jenkins.plugins.thememanager.Theme;
import io.jenkins.plugins.thememanager.ThemeManagerFactory;
import io.jenkins.plugins.thememanager.ThemeManagerFactoryDescriptor;
import java.util.List;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;

public class ${themeTitle}Theme extends ThemeManagerFactory {

    static final String CSS = "${theme}.css";
    static final String ID = "${theme}";

    @DataBoundConstructor
    public ${themeTitle}Theme() {
        // Stapler
    }

    @Override
    public Theme getTheme() {
        return Theme.builder()
            .withCssUrls(List.of(getCssUrl()))
            .build();
    }

    @Extension
    @Symbol("${theme}")
    public static class DescriptorImpl extends ThemeManagerFactoryDescriptor {

        @Override
        public String getThemeKey() {
            return ID;
        }

        @Override
        public ThemeManagerFactory getInstance() {
            return new ${themeTitle}Theme();
        }

        @Override
        public String getThemeCssSuffix() {
            return CSS;
        }

        @NonNull
        @Override
        public String getDisplayName() {
            return "${themeTitle}";
        }

        @Override
        public String getIconClassName() {
            return "symbol-${theme} plugin-${theme}-theme";
        }

        @Override
        public String getThemeId() {
            return ID;
        }

        @Override
        public boolean isNamespaced() {
            return true;
        }
    }
}
