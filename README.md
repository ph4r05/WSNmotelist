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