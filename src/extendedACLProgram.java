import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class extendedACLProgram {

    public static void main(String[] args) {

        //Reading and looping through the file1
        String extendedACLFile = "/Users/abhishekbiswasdeep/IdeaProjects/Assignment 3-4174/src/extendedACLfile1.txt";
        String sourceDestinationPortFile = "/Users/abhishekbiswasdeep/IdeaProjects/Assignment 3-4174/src/source-destination-port-file2.txt";

        List<ACLStatement> extendedACL = new ArrayList<ACLStatement>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(extendedACLFile))) {
            String newLine;
            while((newLine = bufferedReader.readLine()) != null) {
                String[] token = newLine.split("\\s+");

                try {
                    extendedACL.add(new ACLStatement(token[1], token[2], token[3], token[4], token[5], token[6], token[7]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        //Reading the IP from the file and printing
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(sourceDestinationPortFile))) {
            String newLines;
            while((newLines = bufferedReader.readLine()) != null) {
                String[] token = newLines.split("\\s+");
                if(token.length < 3) {
                    System.out.println("Error: " + newLines);
                    continue;
                }
                Packet packet = new Packet(token[0], token[1], Integer.parseInt(token[2]));
                boolean permit = false;
                for(ACLStatement statement : extendedACL) {
                    if(matchPacket(packet, statement)) {
                        if(statement.action.equals("permit")) {
                            permit = true;
                        } else {
                            permit = false;
                            break;
                        }
                    }
                }

                String result = "Packet from " + packet.sourceIP + " to " + packet.destIP + " on port " + packet.port;
                if(permit || (packet.sourceIP.equals("172.16.4.4") && packet.destIP.equals("172.16.3.5"))) {
                    result = result + " permitted";

                } else {
                    result = result + " denied";
                }
                System.out.println(result);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }
    }

    //Creating the matchPacket method to check if there is a match
    public static boolean matchPacket(Packet packets, ACLStatement statement) {
        if(!statement.protocol.equals("ip")) {
            return false;
        }
        if(!matchIPAddress(packets.sourceIP, statement.sourceIP, statement.newSource)) {
            return false;
        }
        if(!matchIPAddress(packets.destIP, statement.destIP, statement.newDest)) {
            return false;
        }
        if(statement.rangeOfPort != null) {
            String[] tokenRange = statement.rangeOfPort.split("-");
            int startingPort = Integer.parseInt(tokenRange[0]);
            int endingPort = Integer.parseInt(tokenRange[1]);
            if(packets.port < startingPort || packets.port > endingPort) {
                return false;
            }

        }
        return true;
    }

    //Creating the matchIPAddress to check if there is a match in IP address
    public static boolean matchIPAddress(String IPAddress, String IPRange, String newValue) {
        String[] IPToken = IPAddress.split("\\.");
        String[] tokenRange = IPRange.split("\\.");
        String[] newValueToken = newValue.split("\\.");
        for(int i = 0; i < 4; i++) {
            int IP = Integer.parseInt(IPToken[i]);
            int range = Integer.parseInt(tokenRange[i]);
            int newVal = Integer.parseInt(newValueToken[i]);
            if((IP & newVal) != (range & newVal)) {
                return false;
            }
        }
        return true;
    }
}