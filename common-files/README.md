#set( $H = '#' )
$H ${artifactId}

#if( $hostOnJenkinsGitHub == "true" )
[![Build Status](https://ci.jenkins.io/job/Plugins/job/${artifactId}-plugin/job/master/badge/icon)](https://ci.jenkins.io/job/Plugins/job/${artifactId}-plugin/job/master/)
[![Contributors](https://img.shields.io/github/contributors/jenkinsci/${artifactId}-plugin.svg)](https://github.com/jenkinsci/${artifactId}-plugin/graphs/contributors)
[![Jenkins Plugin](https://img.shields.io/jenkins/plugin/v/${artifactId}.svg)](https://plugins.jenkins.io/${artifactId})
[![GitHub release](https://img.shields.io/github/release/jenkinsci/${artifactId}-plugin.svg?label=changelog)](https://github.com/jenkinsci/${artifactId}-plugin/releases/latest)
[![Jenkins Plugin Installs](https://img.shields.io/jenkins/plugin/i/${artifactId}.svg?color=blue)](https://plugins.jenkins.io/${artifactId})

#end
$H$H Introduction

TODO Describe what your plugin does here

$H$H Getting started

TODO Tell users how to configure your plugin here, include screenshots, pipeline examples and 
configuration-as-code examples.

#if( $hostOnJenkinsGitHub == "true" )
$H$H Issues

TODO Decide where you're going to host your issues, the default is Jenkins JIRA, but you can also enable GitHub issues,
If you use GitHub issues there's no need for this section; else add the following line:

Report issues and enhancements in the [Jenkins issue tracker](https://issues.jenkins-ci.org/).
#end

$H$H Contributing

TODO review the default [CONTRIBUTING](https://github.com/jenkinsci/.github/blob/master/CONTRIBUTING.md) file and make sure it is appropriate for your plugin, if not then add your own one adapted from the base file

Refer to our [contribution guidelines](https://github.com/jenkinsci/.github/blob/master/CONTRIBUTING.md)

#if( $hostOnJenkinsGitHub == "true" )
$H$H LICENSE

Licensed under MIT, see [LICENSE](LICENSE.md)

#end
