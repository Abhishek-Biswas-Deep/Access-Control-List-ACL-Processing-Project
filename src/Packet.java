public class Packet extends extendedACLProgram{
    public String sourceIP;
    public String destIP;
    public int port;

    public Packet(String sourceIP, String destIP, int port) {
        this.sourceIP = sourceIP;
        this.destIP = destIP;
        this.port = port;
    }


    public static Packet takenFromString(String line) {
        String[] token = line.split("\\s+");
        String sourceIP = token[0];
        String destIP = token[1];
        int port = Integer.parseInt(token[2]);
        return new Packet(sourceIP, destIP, port);
    }
}
