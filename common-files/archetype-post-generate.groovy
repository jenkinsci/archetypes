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

if (properties.get('theme') != null) {
    String theme = properties.get('theme')
    def parts = theme.split("-").collect {it.capitalize()}
    String themeClassName = parts.join('')
    String themeTitleName = parts.join(' ')

    replaceInFile('__themeClassNameRootAction.java', (themeClassName + 'RootAction.java') as String, themeClassName, themeTitleName, projectPath)
    replaceInFile('__themeClassNameTheme.java',( themeClassName + 'Theme.java') as String, themeClassName, themeTitleName, projectPath)
    replaceInFile('index.jelly', null, themeClassName, themeTitleName, projectPath)
    replaceInFile('pom.xml', null, themeClassName, themeTitleName, projectPath)
    replaceInFile('README.md', null, themeClassName, themeTitleName, projectPath)
}

// Needs to be defined outside the if block to avoid compilation error
static def replaceInFile(String old, String replacement, String themeClassName, String themeTitleName, Path projectPath) {
    try (Stream<Path> pathStream = Files.find(
            projectPath,
            Integer.MAX_VALUE,
            (path, attr) -> path.getFileName().toString() == old
    )) {
        pathStream.findFirst()
                .ifPresent {
                    def file = it.toFile()
                    String content = file.text
                    content = content.replace('$THEME_CLASS_NAME', themeClassName)
                    content = content.replace('$THEME_TITLE_NAME', themeTitleName)
                    file.text = content
                    if (replacement != null) {
                        Files.move(it, it.parent.resolve(replacement))
                    }
                }
    }
}

// Adding gitignore file via maven-resources-plugin is broken
// see https://issues.apache.org/jira/browse/ARCHETYPE-505?focusedCommentId=17277974&page=com.atlassian.jira.plugin.system.issuetabpanels%3Acomment-tabpanel#comment-17277974
Path gitIgnorePath = projectPath.resolve("gitignore");
Path targetPath = gitIgnorePath.getParent().resolve(".gitignore");
Files.move(gitIgnorePath, targetPath)

println properties
