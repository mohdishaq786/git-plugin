package hudson.plugins.git.opt;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.eclipse.jgit.transport.RemoteConfig;
import org.jenkinsci.plugins.gitclient.MergeCommand;
import org.kohsuke.stapler.export.Exported;
import org.kohsuke.stapler.export.ExportedBean;

import java.io.Serial;
import java.io.Serializable;

/**
 * Git SCM can optionally perform a merge with another branch (possibly another repository.)
 *
 * This object specifies that configuration.
 */
@ExportedBean(defaultVisibility = 999)
public class PreBuildMergeOptions implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;

    /**
     * Remote repository that contains the {@linkplain #mergeTarget ref}.
     */
    @SuppressFBWarnings(value = "PA_PUBLIC_PRIMITIVE_ATTRIBUTE",
                        justification = "Preserve API compatibility")
    public RemoteConfig mergeRemote = null;

    /**
     * Remote ref to merge.
     */
    @SuppressFBWarnings(value = "PA_PUBLIC_PRIMITIVE_ATTRIBUTE",
                        justification = "Preserve API compatibility")
    public String mergeTarget = null;

    /**
     * Merge strategy.
     */
    @SuppressFBWarnings(value = "PA_PUBLIC_PRIMITIVE_ATTRIBUTE",
                        justification = "Preserve API compatibility")
    public String mergeStrategy = MergeCommand.Strategy.DEFAULT.toString();

    @SuppressFBWarnings(value = "PA_PUBLIC_PRIMITIVE_ATTRIBUTE",
                        justification = "Preserve API compatibility")
    public MergeCommand.GitPluginFastForwardMode fastForwardMode = MergeCommand.GitPluginFastForwardMode.FF;

    public RemoteConfig getMergeRemote() {
        return mergeRemote;
    }

    public void setMergeRemote(RemoteConfig mergeRemote) {
        this.mergeRemote = mergeRemote;
    }

    @Exported
    public String getMergeTarget() {
        return mergeTarget;
    }

    public void setMergeTarget(String mergeTarget) {
        this.mergeTarget = mergeTarget;
    }

    @Exported
    public MergeCommand.Strategy getMergeStrategy() {
        for (MergeCommand.Strategy strategy: MergeCommand.Strategy.values())
            if (strategy.toString().equals(mergeStrategy))
                return strategy;
        return MergeCommand.Strategy.DEFAULT;
    }

    public void setMergeStrategy(MergeCommand.Strategy mergeStrategy) {
        this.mergeStrategy = mergeStrategy.toString();
    }

    @Exported
    public MergeCommand.GitPluginFastForwardMode getFastForwardMode() {
        for (MergeCommand.GitPluginFastForwardMode ffMode : MergeCommand.GitPluginFastForwardMode.values())
            if (ffMode == fastForwardMode)
                return ffMode;
        return MergeCommand.GitPluginFastForwardMode.FF;
    }

    public void setFastForwardMode(MergeCommand.GitPluginFastForwardMode fastForwardMode) {
      this.fastForwardMode = fastForwardMode;
    }

    @Exported
    public String getRemoteBranchName() {
        return (mergeRemote == null) ? null : mergeRemote.getName() + "/" + mergeTarget;
    }

    public boolean doMerge() {
        return mergeTarget != null;
    }
}
