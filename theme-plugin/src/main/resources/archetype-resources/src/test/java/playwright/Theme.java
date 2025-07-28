package ${package}.playwright;

import static ${package}.playwright.Theme.CssVariable.background;

import ${package}.$THEME_CLASS_NAMETheme;
import io.jenkins.plugins.thememanager.ThemeManagerFactoryDescriptor;

public record Theme(String name, String id, CssVariable variableToCheck) {

    public static Theme $THEME_CONSTANT_NAME = new Theme(new $THEME_CLASS_NAMETheme.DescriptorImpl(), background("white"));

    public Theme {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Theme name cannot be null or empty");
        }
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Theme id cannot be null or empty");
        }
        if (variableToCheck == null) {
            throw new IllegalArgumentException("Variable to check cannot be null");
        }
    }

    public Theme(ThemeManagerFactoryDescriptor theme, CssVariable variableToCheck) {
        this(theme.getDisplayName(), theme.getThemeKey(), variableToCheck);
    }

    public record CssVariable(String name, String expected) {
        public CssVariable {
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("CSS variable name cannot be null or empty");
            }
            if (!name.startsWith("--")) {
                throw new IllegalArgumentException("CSS variable name must start with '--'");
            }
            if (expected == null || expected.isEmpty()) {
                throw new IllegalArgumentException("Expected value cannot be null or empty");
            }
        }

        public static CssVariable background(String expectedValue) {
            return new CssVariable("--background", expectedValue);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
