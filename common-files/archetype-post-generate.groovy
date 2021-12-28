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
        new File(it, projectPath.toFile()).delete()
    }
    directoriesToRemove.each {
        new File(it, projectPath.toFile()).deleteDir()
    }
}

// Adding gitignore file via maven-resources-plugin is broken
// see https://issues.apache.org/jira/browse/ARCHETYPE-505?focusedCommentId=17277974&page=com.atlassian.jira.plugin.system.issuetabpanels%3Acomment-tabpanel#comment-17277974
Path gitIgnorePath = projectPath.resolve("gitignore");
Path targetPath = gitIgnorePath.getParent().resolve(".gitignore");
Files.move(gitIgnorePath, targetPath)

println properties
