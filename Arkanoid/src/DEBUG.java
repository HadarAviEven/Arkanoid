

public class DEBUG {

    public static boolean debug = true;

    public static void MSG(String msg) {
        if (debug) {
            System.out.println(msg);
        }
    }

    public static void trace() {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();

        int maxLines = 5;
        int length =  elements.length > maxLines ? maxLines : elements.length;

        for(int i = 0; i<length;i++) {
            MSG(elements.toString());
        }
    }
}
