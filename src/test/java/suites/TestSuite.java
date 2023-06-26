package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.ArticleTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArticleTests.class
})
public class TestSuite {
}
