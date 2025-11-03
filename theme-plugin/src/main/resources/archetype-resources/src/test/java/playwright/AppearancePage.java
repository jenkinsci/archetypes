package ${package}.playwright;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.GetByRoleOptions;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.AriaRole;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppearancePage extends JenkinsPage<AppearancePage> {

    private static final Logger log = LoggerFactory.getLogger(AppearancePage.class);

    public AppearancePage(Page page, String rootUrl) {
        super(page, rootUrl + "/manage/appearance/");
    }

    public AppearancePage themeIsPresent(Theme theme) {
        log.info("Checking if theme '{}' is present", theme);
        assertThat(getThemeCard(theme)).isVisible();
        checkAppearance(".app-theme-picker__picker[data-theme='" + theme.id() + "']", theme.variableToCheck());
        return this;
    }

    public AppearancePage selectTheme(Theme theme) {
        log.info("Selecting theme '{}'", theme.name());
        getThemeCard(theme).click();
        return this;
    }

    public AppearancePage themeIsApplied(Theme theme) {
        log.info("Checking if theme '{}' is applied", theme);
        assertThat(getThemeCard(theme).getByRole(AriaRole.RADIO)).isChecked();
        assertThat(page.locator("html")).hasAttribute("data-theme", theme.id());
        checkAppearance("body", theme.variableToCheck());
        return this;
    }

    private Locator getThemeCard(Theme theme) {
        log.debug("Locating theme box for '{}'", theme);
        return page.getByRole(AriaRole.RADIO, new GetByRoleOptions().setName(theme.name()))
                .locator("..");
    }

    /**
     * Checks the appearance of an element based on a CSS variable.
     *
     * @param selector the CSS selector of the element to check
     * @param variable the CSS variable to check against
     */
    private void checkAppearance(String selector, Theme.CssVariable variable) {
        log.debug("Checking appearance for selector '{}' with CSS variable '{}'", selector, variable);
        try {
            page.waitForFunction("""
        ([selector, cssVar, expected]) => {
          const el = document.querySelector(selector);
          if (!el) return false;
          return getComputedStyle(el).getPropertyValue(cssVar).trim() === expected;
        }""", List.of(selector, variable.name(), variable.expected()));
        } catch (TimeoutError e) {
            Object value = page.evaluate("""
                ([selector, cssVar]) => {
                  const el = document.querySelector(selector);
                  if (!el) return null;
                  return getComputedStyle(el).getPropertyValue(cssVar).trim();
                }""", List.of(selector, variable.name()));
            throw new AssertionError(
                    "Could not verify that '%s' was equal to '%s', found '%s'"
                            .formatted(variable.name(), variable.expected(), value),
                    e);
        }
    }
}
