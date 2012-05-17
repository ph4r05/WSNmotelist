package fi.motetool.motetool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.ExampleMode;
import org.kohsuke.args4j.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App {
    // main logger instance, configured in log4j.properties in resources
    private static final Logger log = LoggerFactory.getLogger(App.class);
    
    // receives other command line parameters than options
    @Argument
    private List<String> arguments = new ArrayList<String>(8);
    
    @Option(name = "--debug", aliases = {"-d"}, usage = "enables debug output")
    private boolean debug;
    
    @Option(name = "--detect-nodes", usage = "performs node detection, read-only operation")
    private boolean detectNodes;
    
    @Option(name = "--show-binding", usage = "returns database binding for connected nodes")
    private boolean showBinding=true;
    
    @Option(name = "--use-motes", aliases={"-m"}, usage = "comma separated list of motes serial numbers to use in experiment. If ALL present, all defined nodes will be used")
    private String useMotesString = null;
    
    @Option(name = "--ignore-motes", aliases={"-i"}, usage = "comma separated list of motes serial numbers to ignore in experiment.")
    private String ignoreMotesString = null;
    
    @Option(name = "--motelist", usage = "sets path to motelist command")
    private String motelistCommand = null;
    
    @Option(name = "--reprogram-nodes-with", aliases={"-f"}, usage = "path to node software directory to reprogram nodes with. Must contain tinyos makefile")
    private String reprogramNodesWith=null;
    
    @Option(name="--reset", aliases={"-r"}, usage = "reset nodes")
    private boolean reset=false;
    
    @Option(name="--threads", aliases={"-t"}, usage="thread count to use during reprogramming")
    private int threadCount=1;
    
    // Running instance of application.
    // To be reachable from another modules for configuration purposes
    private static App runningInstance = null;
    
     // main properties object
    Properties props = null;
    
    private USBarbitratorImpl usbArbitrator = null;
    
    static App getRunningInstance() {
        return runningInstance;
    }
    
    public static void main(String[] args) {
        log.info("Starting application");
        try {
            // some inits in static scope
            // ...

            // do main on instance
            App.runningInstance = new App();  
            
            // do the main
            App.runningInstance.doMain(args);
            
            // ending application
            // ... 

        } catch (IOException ex) {
            log.error("Exception thrown: ", ex);
        } catch (CmdLineException ex) {
            log.error("Error in processing command line arguments", ex);
        } catch (RuntimeException ex) {
            log.error("Runtime exception occurred", ex);
        } catch (Exception ex) {
            log.error("Generic exception occurred", ex);
        }
        
        System.out.println("Exiting...");
        log.info("Everything OK, exiting");
        try {
            Thread.yield();
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            log.warn("Cannot sleep", ex);
        }
        
        // exit
        System.exit(0);
    }
    
    /**
     * Main entry method for running instance of App
     * After start, execution is passed here
     * 
     * @param args          Startup arguments
     * @throws IOException
     * @throws CmdLineException 
     */
    public void doMain(String[] args) throws IOException, CmdLineException {
        this.usbArbitrator = new USBarbitratorImpl();
        
        // load default properties
        props = new Properties();
        props.load(getClass().getResourceAsStream("/application.properties"));
        // init default properties
        this.motelistCommand = props.getProperty("motelistCmd", "motelist");
        
        // command line argument parser
        CmdLineParser parser = new CmdLineParser(this);

        // if you have a wider console, you could increase the value;
        // here 80 is also the default
        parser.setUsageWidth(80);

        try {
            // parse the arguments.
            parser.parseArgument(args);

            // you can parse additional arguments if you want.
            //parser.parseArgument("include","mote");

            // after parsing arguments, you should check
            // if enough arguments are given.
//            if (arguments.isEmpty()) {
//                throw new CmdLineException("No argument is given");
//            }

        } catch (CmdLineException e) {
            // if there's a problem in the command line,
            // you'll get this exception. this will report
            // an error message.
            System.err.println(e.getMessage());
            System.err.println("java SampleMain [options...] arguments...");
            // print the list of available options
            parser.printUsage(System.err);
            System.err.println();

            // print option sample. This is useful some time
            System.err.println(" Example: java SampleMain" + parser.printExample(ExampleMode.ALL));

            return;
        }
//        
//        if (configFile != null && (configFile instanceof File)) {
//            System.out.println("Config file set: " + configFile.getName());
//        }
        
        // init dependencies here - arguments and properties loaded
        // application context loading
        log.info("Initializing depencencies");
        this.initDependencies();
        
        // main logic starting
        log.info("Arguments parsed, can start logic");
        
        // experiment starting, detect nodes, check if same by default
        log.info("Detecting new nodes");
        this.usbArbitrator.setThreadCount(threadCount);
        this.usbArbitrator.detectConnectedNodes();
        
        // reprogram nodes?
        if (reprogramNodesWith!=null && reprogramNodesWith.isEmpty()==false){
            this.reprogramConnectedNodes(this.reprogramNodesWith);
        }     
    }

    private void initDependencies() {
        
    }

    public String getMotelistCommand() {
        return this.motelistCommand;
    }

    public boolean isShowBinding() {
        return this.showBinding;
    }

    boolean isDebug() {
        return this.debug;
    }
    
    /**
     * Helper method - re-programs connected nodes specified by parameters/config file.
     * Only path to directory with makefile is required. Then is executed
     * make telosb install,X bsl,/dev/mote_telosX
     * 
     * @extension: add multithreading to save time required for reprogramming
     * 
     * @param makeDir  absolute path to makefile directory with mote program
     */
    public void reprogramConnectedNodes(String makefileDir){
        List<NodeConfigRecord> nodes2connect = this.getNodes2connect();
        this.usbArbitrator.reprogramNodes(nodes2connect, makefileDir);
    }
    
   public List<NodeConfigRecord> getNodes2connect(){
        // default include/exclude motelist from parameters
        // init file has precedense
        String moteInclude = App.getRunningInstance().getUseMotesString();
        String moteExclude = App.getRunningInstance().getIgnoreMotesString();
        
        // config file/arguments parsing, node selectors, get final set of nodes to connect to
        return this.usbArbitrator.getNodes2connect(moteInclude, moteExclude);
    }

    public String getIgnoreMotesString() {
        return ignoreMotesString;
    }

    public String getUseMotesString() {
        return useMotesString;
    }

    public Properties getProps() {
        return props;
    }
}
