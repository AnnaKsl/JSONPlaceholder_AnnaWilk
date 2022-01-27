package utils;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamSearcher {
    public static void main(String[] args) {

    }
    public static <T> Stream<T> returnStream (T[] someArray){
        return Arrays.stream(someArray);
    }
}
