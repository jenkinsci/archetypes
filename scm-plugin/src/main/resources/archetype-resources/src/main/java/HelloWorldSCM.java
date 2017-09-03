package $package;

import java.io.File;
import java.io.IOException;

import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.Job;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.scm.ChangeLogParser;
import hudson.scm.PollingResult;
import hudson.scm.SCM;
import hudson.scm.SCMDescriptor;
import hudson.scm.SCMRevisionState;
import jenkins.scm.api.SCMHead;
import jenkins.scm.api.SCMRevision;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class HelloWorldSCM extends SCM {
    HelloWorldSCM(SCMHead head, SCMRevision revision) {

    }

    @Override
    public ChangeLogParser createChangeLogParser() {
        return new HelloWorldSCMChangeLogParser();
    }

    @Override
    public PollingResult compareRemoteRevisionWith(@Nonnull Job<?, ?> project, @Nullable Launcher launcher, @Nullable FilePath workspace, @Nonnull TaskListener listener, @Nonnull SCMRevisionState baseline) throws IOException, InterruptedException {
        return null;
    }

    @Override
    public void checkout(@Nonnull Run<?, ?> build, @Nonnull Launcher launcher, @Nonnull FilePath workspace, @Nonnull TaskListener listener, File changelogFile, SCMRevisionState baseline) throws IOException, InterruptedException {
        // Perform the actual checkout operation (download data to workspace).
    }


    @Override
    public SCMRevisionState calcRevisionsFromBuild(@Nonnull Run<?, ?> build, @Nullable FilePath workspace, @Nullable Launcher launcher, @Nonnull TaskListener listener) throws IOException, InterruptedException {
        return null;
    }

    @Extension
    public static class DescriptorImpl extends SCMDescriptor<HelloWorldSCM> {

        public DescriptorImpl() {
            super(null);
        }

        @Override
        public String getDisplayName() {
            return "Hello World SCM";
        }
    }
}
