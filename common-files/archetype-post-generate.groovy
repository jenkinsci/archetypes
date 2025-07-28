import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Stream

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
    def parts = (theme as String).split('-').collect { it.capitalize() }
    def themeClassName = parts.join('')
    def themeTitleName = parts.join(' ')
    def themeConstantName = parts.collect {it.toUpperCase()}.join('_')

    def replacements = [
            new TextReplacement('$THEME_CLASS_NAME', themeClassName),
            new TextReplacement('$THEME_TITLE_NAME', themeTitleName),
            new TextReplacement('$THEME_CONSTANT_NAME', themeConstantName)
    ]

    replaceInFile(projectPath, [
            new FileReplacement('__themeClassNameRootAction.java', "${themeClassName}RootAction.java", replacements),
            new FileReplacement('__themeClassNameTheme.java', "${themeClassName}Theme.java", replacements),
            new FileReplacement('__themeClassNameThemeTest.java', "${themeClassName}ThemeTest.java", replacements),
            new FileReplacement('index.jelly', null, replacements),
            new FileReplacement('Theme.java', null, replacements),
            new FileReplacement('pom.xml', null, replacements),
            new FileReplacement('README.md', null, replacements)
    ])
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

record FileReplacement(String oldName, String newName, List<TextReplacement> replacements) {
    boolean matches(Path path) { oldName == path.getFileName().toString() }

    void replace(Path path) {
        def file = path.toFile()
        file.text = replacements.inject(file.text as String) { acc, r -> r.replace(acc) }
        newName?.with { Files.move(path, path.parent.resolve(it)) }
    }
}

private static void replaceInFile(Path projectPath, List<FileReplacement> files) {
    try (Stream<Path> pathStream = Files.find(projectPath, Integer.MAX_VALUE, (path, _) -> files.any { it.matches(path) })) {
        pathStream.forEach { it -> files.find {r -> r.matches(it) }.replace(it) }
    }
}
