import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

Path projectPath = Paths.get(request.outputDirectory, request.artifactId)
Properties properties = request.properties

if (properties.get("hostOnJenkinsGitHub") == "false") {
    println "Not hosting on Jenkins Github organisation, removing files specific to jenkinsci"

    def filesToRemove = [
            'Jenkinsfile',
            'LICENSE.md'
    ]
    def directoriesToRemove = [
            '.github',
    ]
    filesToRemove.each {
        projectPath.resolve(it).toFile().delete()
    }
    directoriesToRemove.each {
        projectPath.resolve(it).toFile().deleteDir()
    }
}

properties.get('theme')?.with { theme ->
    def packagePath = (properties.get('package') as String).replace('.', File.separator)
    def main = projectPath.resolve('src').resolve('main')
    def mainJava = main.resolve('java').resolve(packagePath)
    def testJava = projectPath.resolve('src').resolve('test').resolve('java').resolve(packagePath)

    def parts = (theme as String).split('-').collect { it.capitalize() }
    def themeClassName = parts.join('')
    def replacements = [
            new TextReplacement('$THEME_CLASS_NAME', themeClassName),
            new TextReplacement('$THEME_TITLE_NAME', parts.join(' ')),
            new TextReplacement('$THEME_CONSTANT_NAME', parts.collect { it.toUpperCase() }.join('_'))
    ]

    replace(mainJava.resolve('__themeClassNameRootAction.java'), replacements, "${themeClassName}RootAction.java")
    replace(mainJava.resolve('__themeClassNameTheme.java'), replacements, "${themeClassName}Theme.java")
    replace(main.resolve('resources').resolve('index.jelly'), replacements)
    replace(testJava.resolve('playwright').resolve('__themeClassNameThemeTest.java'), replacements, "${themeClassName}ThemeTest.java")
    replace(testJava.resolve('playwright').resolve('Theme.java'), replacements)
    replace(projectPath.resolve('pom.xml'), replacements)
    replace(projectPath.resolve('README.md'), replacements)
}

// Adding gitignore file via maven-resources-plugin is broken
// see https://issues.apache.org/jira/browse/ARCHETYPE-505?focusedCommentId=17277974&page=com.atlassian.jira.plugin.system.issuetabpanels%3Acomment-tabpanel#comment-17277974
Path gitIgnorePath = projectPath.resolve("gitignore");
Path targetPath = gitIgnorePath.getParent().resolve(".gitignore");
Files.move(gitIgnorePath, targetPath)

println properties

// Needs to be defined outside the if block to avoid compilation error
record TextReplacement(String target, String replacement) {
    String replace(String content) { content.replace(target, replacement) }
}

private static void replace(Path target, List<TextReplacement> replacements, String newName = null) {
    def file = target.toFile()
    file.text = replacements.inject(file.text as String) { acc, r -> r.replace(acc) }
    newName?.with { Files.move(target, target.parent.resolve(it)) }
}
