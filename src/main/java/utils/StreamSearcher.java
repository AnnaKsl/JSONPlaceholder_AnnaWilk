package utils;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamSearcher {
    public static void main(String[] args) {

    }
    public static <T> Stream<T> returnStream (T[] someArray){
        Stream<T> streamOfElements = Arrays.stream(someArray);
        return streamOfElements;
    }
}
