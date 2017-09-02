package $package;

import java.io.File;
import java.io.IOException;

import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.scm.ChangeLogParser;
import hudson.scm.SCM;
import hudson.scm.SCMDescriptor;
import hudson.scm.SCMRevisionState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;

public class HelloWorldSCM extends SCM {
    @Override
    public ChangeLogParser createChangeLogParser() {
        return new HelloWorldSCMChangeLogParser();
    }

    @Override
    public void checkout(@Nonnull Run<?, ?> build, @Nonnull Launcher launcher, @Nonnull FilePath workspace, @Nonnull TaskListener listener, File changelogFile, SCMRevisionState baseline) throws IOException, InterruptedException {
    }


    @Override
    public SCMRevisionState calcRevisionsFromBuild(@Nonnull Run<?, ?> build, @Nullable FilePath workspace, @Nullable Launcher launcher, @Nonnull TaskListener listener) throws IOException, InterruptedException {
        return null;
    }

    @Extension
    public static class DescriptorImpl extends SCMDescriptor<HelloWorldSCM> {

        public DescriptorImpl() {
            super(HelloWorldSCM.class, null);
            load();
        }

        @Override
        public String getDisplayName() {
            return "Hello World SCM";
        }
    }
}
