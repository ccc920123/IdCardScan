package a.a.a;

public class LiceseByte {
    public static byte[] a = new byte[]{0, 0, 0, -52};
    public static byte[] b = new byte[]{0, 0, 0, -55};

    public static int a(byte[] var0) {
        short var1 = 255;
        boolean var2 = false;
        int var3 = 0;

        for(int var4 = 0; var4 < var0.length; ++var4) {
            var3 <<= 8;
            int var5 = var0[var4] & var1;
            var3 |= var5;
        }

        return var3;
    }
}
