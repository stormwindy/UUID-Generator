
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;

public class UUID {
    private String uuid = "";
    private InetAddress ip;
    StringBuilder uuidBuilder;
    private String mac() {
        String returnMac;
        StringBuilder builder = new StringBuilder();
        try {
            ip = InetAddress.getLocalHost();

            NetworkInterface interfc = NetworkInterface.getByInetAddress(ip);
            byte[] mac = interfc.getHardwareAddress();

            for (int i = 0; i < mac.length; i++) {
                builder.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        returnMac = builder.toString();
        return returnMac;
    }

    private String nanoTime() {
        long nanoStart = System.nanoTime();
        long defNano = System.nanoTime() - nanoStart;
        String nanoString = Long.toHexString(defNano);
        return nanoString;
    }

    private String rand4() {
        Random rand = new Random();
        int randResult = 1 * 10^4 + rand.nextInt(9 * 10^4); //4 digits.
        String returnRandom = String.valueOf(randResult);
        return returnRandom;
    }

    public String getUUID(){
        return uuidBuilder.toString();
    }

    public UUID() {
        uuidBuilder = new StringBuilder();
        String macAddr = mac();
        uuidBuilder.append(macAddr.substring(7));
        uuidBuilder.append(macAddr.substring(3, 7));
        uuidBuilder.append('1');
        uuidBuilder.append(macAddr.substring(0, 3));
        uuidBuilder.append(rand4());
    }
}
