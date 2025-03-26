package Pingandtraceroutecommands;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
class pingserver
{
public static void main(String args[])throws Exception
{
ServerSocket ss=new ServerSocket(5555);
Socket s=ss.accept();
int c=0;
while(c<4)
{
DataInputStream dis=new DataInputStream(s.getInputStream());
PrintStream out=new PrintStream(s.getOutputStream());
String str=dis.readLine();
out.println("Reply from"+InetAddress.getLocalHost()+";Length"+str.length());
c++;
}
s.close();
}
}