package Helper;

import java.io.File;

public enum CurrentSession {
    SESSION;

    private String bank;
    private String environment;
    private String api;
    private String module;
    private boolean hasTestDataFile;
    private boolean hasTestCaseFile;
    private File testDataFile;
    private File testCaseFile;
    private boolean pause;
    private boolean execute;

    public void set(String bank, String environment, String api, String module, boolean hasTestDataFile, boolean hasTestCaseFile, File testDataFile, File testCaseFile) {
        this.bank = bank;
        this.environment = environment;
        this.api = api;
        this.module = module;
        this.hasTestDataFile = hasTestDataFile;
        this.hasTestCaseFile = hasTestCaseFile;
        this.testDataFile = testDataFile;
        this.testCaseFile = testCaseFile;
    }
    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public String getBank() { return bank; }
    public String getEnvironment() { return environment; }
    public String getApi() { return api; }
    public String getModule() { return module; }
    public boolean hasTestDataFile() { return hasTestDataFile; }
    public boolean hasTestCaseFile() { return hasTestCaseFile; }
    public File getTestDataFile() { return testDataFile; }
    public File getTestCaseFile() { return testCaseFile; }
    public boolean isPause() { return pause; }
}