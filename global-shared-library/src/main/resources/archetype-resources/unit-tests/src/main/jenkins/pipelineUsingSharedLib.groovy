package $package

@Library('shared-library')
import ${package}.Utils

sh acme.name
acme.name = 'something'
sh acme.name

acme.caution('world')

sayHello 'World'
sayHello()

parallel(
        action1: {
            node() {
                def utils = new Utils()
                sh "${utils.gitTools()}"
                sh 'sleep 3'
                String json = libraryResource '${packageInPathFormat}/request.json'
                println json
            }
        },
        action2: {
            node() {
                sh 'sleep 4'
                error 'message'
            }
        }
)