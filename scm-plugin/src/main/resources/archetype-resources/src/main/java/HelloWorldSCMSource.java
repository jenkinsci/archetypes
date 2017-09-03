package $package;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import hudson.model.TaskListener;
import hudson.scm.SCM;
import jenkins.scm.api.SCMHead;
import jenkins.scm.api.SCMHeadEvent;
import jenkins.scm.api.SCMHeadObserver;
import jenkins.scm.api.SCMRevision;
import jenkins.scm.api.SCMSource;
import jenkins.scm.api.SCMSourceCriteria;
import jenkins.scm.api.SCMSourceDescriptor;
import org.jenkinsci.Symbol;

import java.io.IOException;
import java.util.Set;

public class HelloWorldSCMSource extends SCMSource {
    @Override
    protected void retrieve(SCMSourceCriteria scmSourceCriteria,
                            @NonNull SCMHeadObserver scmHeadObserver,
                            SCMHeadEvent<?> scmHeadEvent,
                            @NonNull TaskListener taskListener) throws IOException, InterruptedException {
        // TODO
    }

    @Override
    public SCMRevision getTrustedRevision(SCMRevision revision,
                                          TaskListener listener) throws IOException,
            InterruptedException {
        return super.getTrustedRevision(revision, listener); // TODO
    }


    @Override
    protected Set<String> retrieveRevisions(TaskListener listener) throws IOException, InterruptedException {
        return super.retrieveRevisions(listener); // TODO
    }

    @Override
    protected SCMRevision retrieve(String thingName,
                                   TaskListener listener) throws IOException, InterruptedException {
        return super.retrieve(thingName, listener); // TODO
    }


    @NonNull
    @Override
    public SCM build(@NonNull SCMHead scmHead, SCMRevision scmRevision) {
        return new HelloWorldSCM(scmHead, scmRevision);
    }

    @Symbol("helloWorldSCM")
    @Extension
    public static class DescriptorImpl extends SCMSourceDescriptor {

        @Override
        public String getDisplayName() {
            return "Hello World SCM";
        }

    }
}
