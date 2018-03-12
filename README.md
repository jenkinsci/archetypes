# Introduction

Maven archetypes to help you create new components involving Jenkins, such as plugins.

# Usage

To see all currently released archetypes and pick one to instantiate:

```sh
mvn archetype:generate -Dfilter=io.jenkins.archetypes:
```

It is also possible to run this noninteractively:

```sh
mvn archetype:generate -B -DarchetypeGroupId=io.jenkins.archetypes -DarchetypeArtifactId=empty-plugin -DarchetypeVersion=1.1 -DartifactId=somefeature
```

In olden times, people used `mvn hpi:create` or even a web-based tool to create new plugins.
This has been deprecated in favor of the new archetypes, which cover more scenarios and require no special tooling.

The NetBeans IDE also offers a [plugin for Jenkins development](https://github.com/stapler/netbeans-stapler-plugin/blob/master/README.md) which offers a Jenkins plugin archetype via the **File » New Project** wizard.

# Changes

## 1.4 (2018 Mar 12)

* Added `JenkinsPipelineUnit` archetypes.

## 1.3 (2018 Feb 09)

* Changed default package name to use `io.jenkins.` prefix.
* Sundry POM updates.

## 1.2 (2017 Oct 12)

* Added sample of `GlobalConfiguration`.
* Empty plugin archetype includes a test root.
* Sample `.gitignore` files included for the convenience of people using Git.

# Development

To create a new archetype, file a pull request creating a new `module`, following the examples currently here. Important pieces:

* `pom.xml` must have `<packaging>maven-archetype</packaging>`
* should also have a `<description>`
* `src/main/resources/META-INF/maven/archetype-metadata.xml` defines files and directories to be copied & processed
* `src/main/resources/archetype-resources/`: the template for the new component (remember a sample `.gitignore`)
* `src/test/resources/projects/testInstall/{goal.txt,archetype.properties}`: defines an integration test, proving that the archetype can not just be instantiated, but builds successfully thereafter

Archetypes are expected to use technologies hosted in @jenkinsci (or a few other places pulled in by Jenkins core, such as @stapler).
Other archetypes can be developed in a `profile` but will not be published.
Releasing:

```sh
mvn -B release:{prepare,perform}
```

In addition to needing write permission to this repository, you must have been preauthorized to deploy to the `io/jenkins/archetypes/` sector of OSSRH.

Artifacts will [appear on Maven Central](http://repo1.maven.org/maven2/io/jenkins/archetypes/) after a few minutes.
But as noted in OSSRH-34275, the catalog only gets regenerated weekly; to check it:

```sh
curl -s http://repo1.maven.org/maven2/archetype-catalog.xml | xmlstarlet sel -R -I -t -c '//archetype[groupId="io.jenkins.archetypes"]'
```

If testing their appearance, be sure to use a clean environment:

```sh
docker run -ti --rm --name mvn --entrypoint /bin/bash maven:3.5.0-jdk-8
```

# TODO

* `HelloWorldBuilderTest` should use `BuildWatcher`
* convert `ui-samples-plugin` to an archetype
* `.gitignore` files do not get copied to actual archetype
* add `Step` sample as per [this example](https://github.com/jglick/wfdev/blob/pipeline/src/main/java/demo/CountGreetingsStep.java)
