<img src="https://github.com/user-attachments/assets/2ad86f70-12b4-4500-997d-9f8c1874a9b5" alt="Dal logo" width="80"/>
<h1>Associated with Dalhousie University</h1>

### CSCI4174
### Access Control List ACL Processing Project
This project implements Access Control List (ACL) processing in Java, including both standard and extended ACLs. It reads ACL configurations and IP addresses from files, processes them, and determines whether packets should be permitted or denied based on the ACL rules.

#### Project Structure
The project consists of the following main components:

1. standardACLProgram.java: Implements processing for standard ACLs.
2. extendedACLProgram.java: Implements processing for extended ACLs.
3. ACLStatement.java: Defines the structure for ACL statements.
4. Packet.java: Defines the structure for network packets.

#### Input Files
The project uses several input files:

- aclinputfile1.txt: Contains standard ACL rules.
- extendedACLfile1.txt: Contains extended ACL rules.
- IPaddresses.txt: List of IP addresses to be checked against the standard ACL.
- source-destination-port-file2.txt: Contains source IP, destination IP, and port information for extended ACL processing.

#### How to Use

1. Clone this repository.
2. Update the file paths in standardACLProgram.java and extendedACLProgram.java to match local file structure.
3. Compile the Java files.
4. Run the standard ACL program.
5. Run the extended ACL program.

#### Standard ACL Processing
The standardACLProgram reads ACL rules from aclinputfile1.txt and IP addresses from IPaddresses.txt. It then determines whether each IP address is permitted or denied based on the ACL rules.

#### Extended ACL Processing
The extendedACLProgram reads extended ACL rules from extendedACLfile1.txt and packet information from source-destination-port-file2.txt. It processes each packet against the extended ACL rules to determine if it should be permitted or denied.

#### Classes

- ACLStatement: Represents an individual ACL statement with attributes like action, protocol, source IP, destination IP, and port range.
- Packet: Represents a network packet with source IP, destination IP, and port.

#### Sample input and output
```
Standard ACL Program:
Inputs:
access-list 3 deny 172.16.4.0 0.0.0.255
access-list 3 permit 172.16.0.0 0.0.255.255
interface EO
ip access-group 3 out

172.16.4.1
172.16.3.5
201.15.3.4

Outputs:
Packet from 172.16.4.1 denied
Packet from 172.16.3.5 permitted
Packet from 201.15.3.4 denied

Process finished with exit code 0

Extended ACL Program:
Inputs:
access-list 101 deny tcp 172.16.0.0 0.0.255.255 172.16.3.0 0.0.0.255 range 20-21
access-list 101 permit ip 172.16.0.0 0.0.255.255 172.16.3.0 0.0.0.255
interface EO
ip access-group 101 out

172.16.4.4 172.16.3.1 20
172.16.4.4 172.16.3.5 22
172.25.3.1 172.16.3.4 22

Outputs:
Packet from 172.16.4.4 to 172.16.3.1 on port 20 denied
Packet from 172.16.4.4 to 172.16.3.5 on port 22 permitted
Packet from 172.25.3.1 to 172.16.3.4 on port 22 denied

Process finished with exit code 0
```
