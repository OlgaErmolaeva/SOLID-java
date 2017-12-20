package learning.smells;

public class Bloat {

    //smell: Primitive Obsession
    static class Service1 {
        String dbUrl, username, password;
        int port, timeout;
        double x, y, z;

        //smell: Long Parameter List
        void initialize(String dbUrl, String username, String password, int port, int timeout, double x, double y, double z) {
            this.dbUrl = dbUrl;
            this.username = username;
            this.password = password;
            this.port = port;
            this.timeout = timeout;
            this.x = x;
            this.y = y;
            this.z = z;
        }

        void someBusinessMethod() {
            //doing something with all my fields
        }
    }

    //smell: Data Clumps, Duplicate Code
    static class Service2 {
        String dbUrl, username, password;
        int port, timeout;
        double x, y, z;

        void initialize(String dbUrl, String username, String password, int port, int timeout, double x, double y, double z) {
            this.dbUrl = dbUrl;
            this.username = username;
            this.password = password;
            this.port = port;
            this.timeout = timeout;
            this.x = x;
            this.y = y;
            this.z = z;
        }

        void anotherBusinessMethod() {
            //doing something with all my fields
        }
    }
}
