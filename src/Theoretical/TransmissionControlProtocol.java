package Theoretical;

import java.util.HashMap;
import java.util.Map;

// DESCRIPTION: http://www.medianet.kent.edu/techreports/TR2005-07-22-tcp-EFSM.pdf
public class TransmissionControlProtocol {

    public static String traverseStates(String[] events) {
        String state = "CLOSED"; // initial state, always
        //TODO: use 2 sets of enums instead (states and events)!
        Map<String, Map<String, String>> stateTransitions = new HashMap<>()
        {{
            put("CLOSED", new HashMap<>() {{put("APP_PASSIVE_OPEN","LISTEN");
                                            put("APP_ACTIVE_OPEN","SYN_SENT");}});

            put("LISTEN", new HashMap<>() {{put("RCV_SYN","SYN_RCVD");
                                            put("APP_SEND","SYN_SENT");
                                            put("APP_CLOSE","CLOSED");}});

            put("SYN_RCVD", new HashMap<>() {{put("APP_CLOSE","FIN_WAIT_1");
                                              put("RCV_ACK","ESTABLISHED");}});

            put("SYN_SENT", new HashMap<>() {{put("RCV_SYN","SYN_RCVD");
                                              put("RCV_SYN_ACK","ESTABLISHED");
                                              put("APP_CLOSE","CLOSED");}});

            put("ESTABLISHED", new HashMap<>() {{put("APP_CLOSE","FIN_WAIT_1");
                                                 put("RCV_FIN","CLOSE_WAIT");}});

            put("FIN_WAIT_1", new HashMap<>() {{put("RCV_FIN","CLOSING");
                                                put("RCV_FIN_ACK","TIME_WAIT");
                                                put("RCV_ACK","FIN_WAIT_2");}});

            put("CLOSING", new HashMap<>()    {{put("RCV_ACK","TIME_WAIT");}});
            put("FIN_WAIT_2", new HashMap<>() {{put("RCV_FIN","TIME_WAIT");}});
            put("TIME_WAIT", new HashMap<>()  {{put("APP_TIMEOUT","CLOSED");}});
            put("CLOSE_WAIT", new HashMap<>() {{put("APP_CLOSE","LAST_ACK");}});
            put("LAST_ACK", new HashMap<>()   {{put("RCV_ACK","CLOSED");}});
            put("ERROR", new HashMap<>()   {{put("ERROR","ERROR");}});

        }};
        for(String e: events){ state = stateTransitions.getOrDefault(state, stateTransitions.get("ERROR"))
                                                       .getOrDefault(e, "ERROR");}
        return state;
    }

    public static void main(String[] args) {
        System.out.println(TransmissionControlProtocol.traverseStates(new String[] {"APP_ACTIVE_OPEN","RCV_SYN_ACK","RCV_FIN"}));
        System.out.println(TransmissionControlProtocol.traverseStates(new String[] {"APP_PASSIVE_OPEN", "RCV_SYN","RCV_ACK"}));
        System.out.println(TransmissionControlProtocol.traverseStates(new String[] {"APP_ACTIVE_OPEN","RCV_SYN_ACK","RCV_FIN","APP_CLOSE"}));
        System.out.println(TransmissionControlProtocol.traverseStates(new String[] {"APP_ACTIVE_OPEN"}));
        System.out.println(TransmissionControlProtocol.traverseStates(new String[] {"o","RCV_SYN","RCV_ACK","APP_CLOSE","APP_SEND"}));
    }
}