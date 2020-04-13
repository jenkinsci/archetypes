import java.nio.file.Path
import java.nio.file.Paths

Path projectPath = Paths.get(request.outputDirectory, request.artifactId)
Properties properties = request.properties

if (properties.get("hostOnJenkinsGitHub") == "false") {
    println "Not hosting on Jenkins Github organisation, removing files specific to jenkinsci"

    def filesToRemove = [
            'Jenkinsfile',
    ]
    def directoriesToRemove = [
            '.github',
            '.dependabot',
            '.mvn'
    ]
    filesToRemove.each {
        new File(it, projectPath.toFile()).delete()
    }
    directoriesToRemove.each {
        new File(it, projectPath.toFile()).deleteDir()
    }
}

println properties