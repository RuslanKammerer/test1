package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.ArticleTests;
import tests.ChangeAppConditionTests;
import tests.MyListsTests;
import tests.SearchTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArticleTests.class,
        SearchTests.class,
        MyListsTests.class,
        ChangeAppConditionTests.class
})
public class TestSuite {
}
