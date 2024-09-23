public class ACLStatement extends extendedACLProgram {

    public String action;
    public String protocol;
    public String sourceIP;
    public String newSource;
    public String destIP;
    public String newDest;
    public String rangeOfPort;

    public ACLStatement(String action, String protocol, String sourceIP, String newSource, String destIP,
                        String newDest, String rangeOfPort) {
        this.action = action;
        this.protocol = protocol;
        this.sourceIP = sourceIP;
        this.newSource = newSource;
        this.destIP = destIP;
        this.newDest = newDest;
        this.rangeOfPort = rangeOfPort;
    }

    @Override
    public String toString() {
        return "ACLStatement{" +
                "action='" + action + '\'' +
                ", protocol='" + protocol + '\'' +
                ", sourceIP='" + sourceIP + '\'' +
                ", newSource='" + newSource + '\'' +
                ", destIP='" + destIP + '\'' +
                ", newDest='" + newDest + '\'' +
                ", rangeOfPort='" + rangeOfPort + '\'' +
                '}';
    }

}
