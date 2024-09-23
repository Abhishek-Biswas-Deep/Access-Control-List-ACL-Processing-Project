import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class standardACLProgram {
    public static void main(String[] args) throws FileNotFoundException {

        //Reading from file1
        ArrayList<String> standardACL = new ArrayList<String>();
        Scanner in = new Scanner(new File("/Users/abhishekbiswasdeep/IdeaProjects/Assignment 3-4174/src/aclinputfile1.txt"));
        while(in.hasNextLine()) {
            standardACL.add(in.nextLine());
        }
        in.close();

        //Reading the IP addresses
        ArrayList<String> address = new ArrayList<String>();
        in = new Scanner(new File("/Users/abhishekbiswasdeep/IdeaProjects/Assignment 3-4174/src/IPaddresses.txt"));
        while(in.hasNextLine()) {
            address.add(in.nextLine());
        }
        in.close();

        //Looping through the files
        for(String addresses : address) {
            boolean permit = false;

            for (String accessList : standardACL) {
                String[] partitions = accessList.split(" ");
                if(partitions[2].equals("any") || addresses.startsWith(partitions[2])) {
                    if(partitions[1].equals("permit")) {
                        permit = true;
                    } else if(partitions[1].equals("deny")) {
                        permit = false;
                    }
                }
            }

            //Printing
            if(permit) {
                System.out.println("Packet from " + addresses + " permitted");
            } else {
                System.out.println("Packet from " + addresses + " denied");
            }
        }
    }
}
