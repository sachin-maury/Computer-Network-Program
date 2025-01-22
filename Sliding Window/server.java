import java.net.*;
import java.io.*;

public class server {
    public static void main(String[] args) throws Exception {
        ServerSocket ser = new ServerSocket(12345);
        System.out.println("Server started. Waiting for a client...");

        try (Socket s = ser.accept()) {
            System.out.println("Client connected.");

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in1 = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintStream p = new PrintStream(s.getOutputStream());

            String sbuff[] = new String[8];
            int sptr = 0, sws = 8, nf, ano, i;
            String ch;

            do {
                System.out.print("Enter the number of frames: ");
                nf = Integer.parseInt(in.readLine());
                p.println(nf);

                if (nf <= sws - 1) {
                    System.out.println("Enter " + nf + " Messages to be sent");

                    for (i = 1; i <= nf; i++) {
                        sbuff[sptr] = in.readLine();
                        p.println(sbuff[sptr]);
                        sptr = ++sptr % 8;
                    }

                    sws -= nf;
                    System.out.print("Acknowledgment received for ");
                    ano = Integer.parseInt(in1.readLine());
                    System.out.println(ano + " frames");
                    sws += nf;
                } else {
                    System.out.println("The number of frames exceeds window size");
                    break;
                }

                System.out.print("\nDo you want to send some more frames: ");
                ch = in.readLine();
                p.println(ch);
            } while (ch.equals("yes"));

            System.out.println("Closing connection.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ser.close();
    }
}
