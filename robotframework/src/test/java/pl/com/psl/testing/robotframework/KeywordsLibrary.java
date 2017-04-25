package pl.com.psl.testing.robotframework;

import org.robotframework.javalib.library.AnnotationLibrary;

/**
 * Created by psl on 25.04.17.
 */
public class KeywordsLibrary extends AnnotationLibrary {

    public KeywordsLibrary() {
        super("pl/com/psl/testing/robotframework/keywords/*.class");
    }
}
