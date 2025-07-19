package api.stringandstringbufferstringbuilder;

public class AppendTest {
    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        System.out.println(builder.capacity());
        StringBuilder builder1 = new StringBuilder(5);
        StringBuilder builder2 = new StringBuilder("test");
    }
}
