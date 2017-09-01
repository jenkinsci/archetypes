package ${package}

import static org.junit.Assert.assertEquals

import org.junit.Before
import org.junit.Test

import com.lesfurets.jenkins.unit.BasePipelineTest

class TestJenkinsfile extends BasePipelineTest {

    @Override
    @Before
    void setUp() throws Exception {
        super.setUp()
    }

    @Test
    void should_execute_on_master() throws Exception {
        binding.setVariable('env', [BRANCH_NAME: 'master'])
        runScript('Jenkinsfile')
        assertEquals('I only execute on the master branch', helper.callStack.last().argsToString())
    }

    @Test
    void should_execute_elsewhere() throws Exception {
        binding.setVariable('env', [BRANCH_NAME: 'develop'])
        runScript('Jenkinsfile')
        assertEquals('I execute elsewhere', helper.callStack.last().argsToString())
    }

}
