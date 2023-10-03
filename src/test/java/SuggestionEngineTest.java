import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class SuggestionEngineTest {
    private SuggestionEngine suggestionengine;
@Before
    public void setUp(){
        suggestionengine = new SuggestionEngine();
    }
@Test
    public void testCorrectWord(){
        suggestionengine.getWordSuggestionDB().put("apple", 1);
        suggestionengine.getWordSuggestionDB().put("banana", 1);
        suggestionengine.getWordSuggestionDB().put("vehicle", 1);

        String suggestions1 = suggestionengine.generateSuggestions("apple");
        String suggestions2 = suggestionengine.generateSuggestions("banana");
        String suggestions3 = suggestionengine.generateSuggestions("vehicle");

        assertEquals("", suggestions1);
        assertEquals("", suggestions2);
        assertEquals("", suggestions3);
    }
@Test
    public void testSuggestCorrections() throws IOException{
        Path dictionaryPath = Paths.get("src/main/words.txt");
        suggestionengine.loadDictionaryData(dictionaryPath);
        assertEquals("apple\nample\nalle\nale\nape\nmaple\nsaple\nable\nacle\nagle", suggestionengine.generateSuggestions("aple"));
        assertEquals("banana\nbandana\nbanna\nbandanna\nanana\nbanaba\nbananas\nbannat\nmanana\ntanana", suggestionengine.generateSuggestions("bannana"));
        assertEquals("vachil\nvachill\nvexil\narchil\nbechic\ncecil\nchil\ndecil\nechis\nkechel", suggestionengine.generateSuggestions("vechil"));
    }
}
