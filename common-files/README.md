#set( $H = '#' )
$H ${artifactId}

$H$H Introduction

TODO Describe what your plugin does here

$H$H Getting started

TODO Tell users how to configure your plugin here, include screenshots, pipeline examples and 
configuration-as-code examples.

#if( $hostOnJenkinsGitHub == "true" )
$H$H Issues

TODO Decide where you're going to host your issues, the default is Jenkins JIRA, but you can also enable GitHub issues,
If you use GitHub issues there's no need for this section; else add the following line:

Report issues and enhancements in the [Jenkins issue tracker](https://issues.jenkins.io/).

$H$H Contributing

TODO review the default [CONTRIBUTING](https://github.com/jenkinsci/.github/blob/master/CONTRIBUTING.md) file and make sure it is appropriate for your plugin, if not then add your own one adapted from the base file

Refer to our [contribution guidelines](https://github.com/jenkinsci/.github/blob/master/CONTRIBUTING.md)

$H$H LICENSE

Licensed under MIT, see [LICENSE](LICENSE.md)

#end
