package fi.motetool.motetool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * StreamGobbler reads inputstream to "gobble" it. This is used by Executor
 * class when running a commandline applications. Gobblers must read/purge INSTR
 * and ERRSTR process streams.
 * http://www.javaworld.com/javaworld/jw-12-2000/jw-1229-traps.html?page=4
 */
public class StreamGobbler extends Thread {
    protected static final Logger log = LoggerFactory.getLogger(StreamGobbler.class);
    
    private InputStream is;
    private StringBuffer output;
    private volatile boolean completed; // mark volatile to guarantee a thread safety

    public StreamGobbler(InputStream is, boolean readStream) {
        this.is = is;
        this.output = (readStream ? new StringBuffer(256) : null);
    }
    
    public StreamGobbler(InputStream is, StringBuffer buff) {
        this.is = is;
        this.output = buff;
    }

    @Override
    public void run() {
        completed = false;
        try {
            String NL = System.getProperty("line.separator", "\r\n");

            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                if (output != null) {
                    // Can be done in 2 appends but due to thread safety.
                    output.append(line+NL);
                }
            }
        } catch (IOException ex) {
            log.error("Exception in reading stream", ex);
        }
        completed = true;
    }

    /**
     * Get inputstream buffer or null if stream was not consumed.
     *
     * @return
     */
    public String getOutput() {
        return (output != null ? output.toString() : null);
    }

    /**
     * Is input stream completed.
     *
     * @return
     */
    public boolean isCompleted() {
        return completed;
    }
}
