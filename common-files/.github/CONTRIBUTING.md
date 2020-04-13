# Contribution guide

**Never report security issues on GitHub or other public channels (Gitter/Twitter/etc.), 
follow the instruction from [Jenkins Security](https://jenkins.io/security/) to 
report it on [Jenkins Jira](https://issues.jenkins-ci.org)**

## Why should you contribute

You can contribute in many ways, and whatever you choose we're grateful!
Source code contribution is the obvious one but we also need your feedback and if you don't really want to participate 
in the implementation directly you may still have great ideas about features we need (or should get rid of).

We have our vision for the plugin and we have an experience with maintaining Jenkins instances, 
but the plugin is not supposed to solve only our problems. Surely we haven't experienced all of them... 
That's why we want to hear from you.

Whenever you report a problem please provide information about:

- Plugin version
- Jenkins version
- Operating system
- Description!

### Regarding source code contribution ways of working

- For larger contributions create an issue for any required discussion
- Implement solution on a branch in your fork
- Make sure to include issue number (if created) in commit message, and make the message speak for itself
- Once you're done create a pull request and ask at least one of the maintainers for review
  - Remember to title your pull request properly as it is used for release notes

Never push directly to this repository!

## Run Locally

Prerequisites: _Java_, _Maven_ & _IntelliJ IDEA_

- Ensure Java 8 or 11 is available.

  ```shell
  java -version
  ```
  - Use the alternate Java 8.

  ```shell
  export JAVA_HOME=`/usr/libexec/java_home -v 1.8`
  echo $JAVA_HOME
  ```

  ```text
  /Library/Java/JavaVirtualMachines/jdk1.8.0_192.jdk/Contents/Home
  ```

- Ensure Maven > 3.6.0 is installed and included in the PATH environment variable.

  ```
  mvn --version
  ```
  
### IDE configuration

See [IDE configuration](https://jenkins.io/doc/developer/development-environment/ide-configuration/)

### CLI

- Go into the `plugin` child directory under the root directory of this project.
- Use the below commands.

```shell
mvn hpi:run
```

```text
...
INFO: Jenkins is fully up and running
```

- Open <http://localhost:8080/jenkins/> to test the plugin locally.
