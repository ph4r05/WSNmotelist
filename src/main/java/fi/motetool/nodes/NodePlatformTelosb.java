/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.motetool.nodes;

import java.util.Properties;

/**
 *
 * @author ph4r05
 */
public class NodePlatformTelosb extends NodePlatformGeneric{
    public static final int platformId = NodePlatformFactory.NODE_PLATFORM_TELOSB; 
    private static final String TOSBSL_PATH = "/usr/bin/tos-bsl";    
    
   /**
    * tx output power level
    */
    public static final int[] signalLevel = {31,27,23,19,15,11,7,3};

   /**
    * Corresponding power levels to signalLevel;
    * Power level at TX power 31 = powerLevel[0], 31 = signalLevel[0];
    */
    public static final double[] powerLevel = {0., -1., -3., -5., -7., -10., -15., -25.};

   /**
    * Tunable tx-rx channel
    */
    public static final int[] channels = {11,12,13,14,15,16,17,18,19,20,21,23,24,25,26};

    @Override
    public String getPlatform() {
        return "TelosB";
    }

    /**
     * Platform numeric ID.
     * Must correspond to platform ID defined in tinyOS program in reporting motes.
     * @return
     */
    @Override
    public int getPlatformId() {
        return NodePlatformTelosb.platformId;
    }

    @Override
    public int[] getTxLevels() {
        return NodePlatformTelosb.signalLevel;
    }

    @Override
    public double[] getTxOutputPower() {
        return NodePlatformTelosb.powerLevel;
    }
    
    @Override
    public String getConnectionStringSignature() {
        return "telosb";
    }

    @Override
    public boolean isPlatformFromNodeDescription(String desc) {
        if (desc==null){
            throw new NullPointerException("Platform description is empty");
        }
        
        return "XBOW Crossbow Telos Rev.B".equalsIgnoreCase(desc.trim());
    }
    
    @Override
    public String getPlatformReflashId() {
        return "telosb";
    }

    /**
     * Caution here.
     * HW reset can be performed only if node is connected by serial (there is any
     * device present for it)
     * 
     * @return 
     */
    @Override
    public boolean canHwReset() {
        return true;
    }

    @Override
    public String hwResetCommand(String device, Properties prop) {
        return TOSBSL_PATH + " --telosb -r -c " + device;
    }
}
