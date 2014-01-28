/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.motetool.motetool;

import static fi.motetool.motetool.StreamGobbler.log;
import java.io.InputStream;

/**
 * Merges two String input streams to one.
 * @author ph4r05
 */
public class StreamMerger {
    private final InputStream is1;
    private final InputStream is2;
    private final StreamGobbler g1;
    private final StreamGobbler g2;
    
    private final StringBuffer output = new StringBuffer(256);

    public StreamMerger(InputStream is1, InputStream is2) {
        this.is1 = is1;
        this.is2 = is2;
        g1 = new StreamGobbler(is1, output);
        g2 = new StreamGobbler(is2, output);
    }
    
    public void run() {
        g1.start();
        g2.start();
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
        return (g1==null || g1.isCompleted()) && (g2==null || g2.isCompleted());
    }
}
