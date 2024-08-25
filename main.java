import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.net.Socket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class main {

    private static final Random random = new Random();
    private static final int MAX_THREADS = 20000;

    public static void main(String[] args) {
        while (true) {
            List<String> ipList = new ArrayList<>();
            for (int i = 0; i < 20000; i++) {
                ipList.add(createRandomIp());
            }

            List<Thread> threads = new ArrayList<>();
            for (String ip : ipList) {
                while (threads.size() >= MAX_THREADS) {
                    Iterator<Thread> iterator = threads.iterator();
                    while (iterator.hasNext()) {
                        Thread t = iterator.next();
                        if (!t.isAlive()) {
                            iterator.remove();
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Thread t = new Thread(() -> check(ip));
                t.start();
                threads.add(t);
            }

            for (Thread t : threads) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String createRandomIp() {
        int a, b, c, d;
        List<Integer> privateOctets = List.of(10, 172, 192);
        do {
            a = random.nextInt(254) + 1;
        } while (privateOctets.contains(a) || a == 127);

        b = random.nextInt(254 - 9) + 10;
        c = random.nextInt(254) + 1;
        d = random.nextInt(254) + 1;

        return a + "." + b + "." + c + "." + d;
    }

    private static void check(String ip) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(InetAddress.getByName(ip), 25565), 1000);
            System.out.println(" " + ip + ":25565 open ");
        } catch (Exception e) {
        }
    }
}
