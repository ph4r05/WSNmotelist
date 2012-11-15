WSNmotelist
===========

Tool for mass mote management. 
Can {reset, reprogram} all nodes in testbed in one time, with one command


Help:
```
java -jar motetool/target/motetool-1.0-SNAPSHOT.jar --help
"--help" is not a valid option
java SampleMain [options...] arguments...
 --debug (-d)                    : enables debug output
 --detect-nodes                  : performs node detection, read-only operation
 --ignore-motes (-i) VAL         : comma separated list of motes serial numbers
                                   to ignore in experiment.
 --motelist VAL                  : sets path to motelist command
 --reprogram-nodes-with (-f) VAL : path to node software directory to reprogram
                                   nodes with. Must contain tinyos makefile
 --reset (-r)                    : reset nodes
 --show-binding                  : returns database binding for connected nodes
 --threads (-t) N                : thread count to use during reprogramming
 --use-motes (-m) VAL            : comma separated list of motes serial numbers
                                   to use in experiment. If ALL present, all
                                   defined nodes will be used
```

Standard output for detected nodes:
```
java -jar motetool/target/motetool-1.0-SNAPSHOT.jar --reset
Dumping output (by nodeID): 
Node serial: M4AOCF99;  NodeID: 4;	  Dev: /dev/ttyUSB26;	 Alias: /dev/mote_tmote04;	 Description: Moteiv tmote sky;	          USB: usb-0000:00:13.5-5.4.2.4.4.2
Node serial: XBTO3UIT;	 NodeID: 5;	  Dev: /dev/ttyUSB1;	  Alias: /dev/mote_telos05;	 Description: XBOW Crossbow Telos Rev.B;	 USB: usb-0000:00:13.5-1.4.3
Node serial: XBTO3I5E;	 NodeID: 6;	  Dev: /dev/ttyUSB9;	  Alias: /dev/mote_telos06;	 Description: XBOW Crossbow Telos Rev.B;	 USB: usb-0000:00:13.5-5.4.7
Node serial: XBTO3I7K;	 NodeID: 7;	  Dev: /dev/ttyUSB22;	 Alias: /dev/mote_telos07;	 Description: XBOW Crossbow Telos Rev.B;	 USB: usb-0000:00:13.5-2.4.4.4.3.6
Node serial: XBTO6EKQ;	 NodeID: 10;	 Dev: /dev/ttyUSB4;	  Alias: /dev/mote_telos10;	 Description: XBOW Crossbow Telos Rev.B;	 USB: usb-0000:00:13.5-1.4.6
Node serial: XBTO68UR;	 NodeID: 12;	 Dev: /dev/ttyUSB7;	  Alias: /dev/mote_telos12;	 Description: XBOW Crossbow Telos Rev.B;	 USB: usb-0000:00:13.5-5.4.5
Node serial: XBTO405O;	 NodeID: 13;	 Dev: /dev/ttyUSB16;	 Alias: /dev/mote_telos13;	 Description: XBOW Crossbow Telos Rev.B;	 USB: usb-0000:00:13.5-5.4.1.4.5
Node serial: XBTO6CV1;	 NodeID: 14;	 Dev: /dev/ttyUSB20;	 Alias: /dev/mote_telos14;	 Description: XBOW Crossbow Telos Rev.B;	 USB: usb-0000:00:13.5-2.4.4.4.3.4
Node serial: XBTO3UA2;	 NodeID: 15;	 Dev: /dev/ttyUSB18;	 Alias: /dev/mote_telos15;	 Description: XBOW Crossbow Telos Rev.B;	 USB: usb-0000:00:13.5-2.4.4.4.3.2
Node serial: XBTO68RZ;	 NodeID: 17;	 Dev: /dev/ttyUSB23;	 Alias: /dev/mote_telos17;	 Description: XBOW Crossbow Telos Rev.B;	 USB: usb-0000:00:13.5-2.4.4.4.3.7
Node serial: XBTO6H9G;	 NodeID: 22;	 Dev: /dev/ttyUSB28;	 Alias: /dev/mote_telos22;	 Description: XBOW Crossbow Telos Rev.B;	 USB: usb-0000:00:13.5-5.4.2.4.4.4
Node serial: XBTO6BLG;	 NodeID: 25;	 Dev: /dev/ttyUSB8;	  Alias: /dev/mote_telos25;	 Description: XBOW Crossbow Telos Rev.B;	 USB: usb-0000:00:13.5-5.4.6
Node serial: XBTO40GK;	 NodeID: 28;	 Dev: /dev/ttyUSB27;	 Alias: /dev/mote_telos28;	 Description: XBOW Crossbow Telos Rev.B;	 USB: usb-0000:00:13.5-5.4.2.4.4.3
Node serial: XBTO6CH7;	 NodeID: 29;	 Dev: /dev/ttyUSB2;	  Alias: /dev/mote_telos29;	 Description: XBOW Crossbow Telos Rev.B;	 USB: usb-0000:00:13.5-1.4.4
Node serial: XBTOCCSR;	 NodeID: 30;	 Dev: /dev/ttyUSB14;	 Alias: /dev/mote_telos30;	 Description: XBOW Crossbow Telos Rev.B;	 USB: usb-0000:00:13.5-5.4.1.4.2
Node serial: XBTO3Z64;	 NodeID: 31;	 Dev: /dev/ttyUSB29;	 Alias: /dev/mote_telos31;	 Description: XBOW Crossbow Telos Rev.B;	 USB: usb-0000:00:13.5-5.4.2.4.4.6
Node serial: XBTO6G7C;	 NodeID: 32;	 Dev: /dev/ttyUSB5;	  Alias: /dev/mote_telos32;	 Description: XBOW Crossbow Telos Rev.B;	 USB: usb-0000:00:13.5-1.4.7
Node serial: XBTO3XMF;	 NodeID: 33;	 Dev: /dev/ttyUSB24;	 Alias: /dev/mote_telos33;	 Description: XBOW Crossbow Telos Rev.B;	 USB: usb-0000:00:13.5-2.4.4.4.7.4
Node serial: XBTO3TI0;	 NodeID: 35;	 Dev: /dev/ttyUSB13;	 Alias: /dev/mote_telos35;	 Description: XBOW Crossbow Telos Rev.B;	 USB: usb-0000:00:13.5-5.4.1.4.1
Node serial: XBTO6C0L;	 NodeID: 36;	 Dev: /dev/ttyUSB17;	 Alias: /dev/mote_telos36;	 Description: XBOW Crossbow Telos Rev.B;	 USB: usb-0000:00:13.5-5.4.1.4.6
Node serial: XBTO6AVP;	 NodeID: 37;	 Dev: /dev/ttyUSB21;	 Alias: /dev/mote_telos37;	 Description: XBOW Crossbow Telos Rev.B;	 USB: usb-0000:00:13.5-2.4.4.4.3.5
Node serial: XBTO3TIQ;	 NodeID: 41;	 Dev: /dev/ttyUSB25;	 Alias: /dev/mote_telos41;	 Description: XBOW Crossbow Telos Rev.B;	 USB: usb-0000:00:13.5-5.4.2.4.4.1
Node serial: XBTO6EGB;	 NodeID: 42;	 Dev: /dev/ttyUSB15;	 Alias: /dev/mote_telos42;	 Description: XBOW Crossbow Telos Rev.B;	 USB: usb-0000:00:13.5-5.4.1.4.4
Node serial: XBTO3W81;	 NodeID: 43;	 Dev: /dev/ttyUSB19;	 Alias: /dev/mote_telos43;	 Description: XBOW Crossbow Telos Rev.B;	 USB: usb-0000:00:13.5-2.4.4.4.3.3
Node serial: XBTO40WD;	 NodeID: 44;	 Dev: /dev/ttyUSB6;	  Alias: /dev/mote_telos44;	 Description: XBOW Crossbow Telos Rev.B;	 USB: usb-0000:00:13.5-5.4.4
Node serial: XBTO3OZ5;	 NodeID: 46;	 Dev: /dev/ttyUSB11;	 Alias: /dev/mote_telos46;	 Description: XBOW Crossbow Telos Rev.B;	 USB: usb-0000:00:13.5-2.4.4.4.5
Node serial: XBTO3OFO;	 NodeID: 47;	 Dev: /dev/ttyUSB12;	 Alias: /dev/mote_telos47;	 Description: XBOW Crossbow Telos Rev.B;	 USB: usb-0000:00:13.5-2.4.4.4.6
Node serial: XBTO3VKQ;	 NodeID: 48;	 Dev: /dev/ttyUSB10;	 Alias: /dev/mote_telos48;	 Description: XBOW Crossbow Telos Rev.B;	 USB: usb-0000:00:13.5-2.4.4.4.4
Node serial: XBTO3MLL;	 NodeID: 50;	 Dev: /dev/ttyUSB3;	  Alias: /dev/mote_telos50;	 Description: XBOW Crossbow Telos Rev.B;	 USB: usb-0000:00:13.5-1.4.5
Node serial: M4AOCF7L;	 NodeID: null;	 Dev: /dev/ttyUSB0;	Alias: /dev/mote_tmoteXX;	 Description: Moteiv tmote sky;	          USB: usb-0000:00:13.2-2
```