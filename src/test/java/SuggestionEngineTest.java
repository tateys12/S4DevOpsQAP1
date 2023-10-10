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
        suggestionengine.getWordSuggestionDB().put("ambulance", 1);
        suggestionengine.getWordSuggestionDB().put("sleep", 1);

        String suggestions1 = suggestionengine.generateSuggestions("apple");
        String suggestions2 = suggestionengine.generateSuggestions("banana");
        String suggestions3 = suggestionengine.generateSuggestions("ambulance");
        String suggestions4 = suggestionengine.generateSuggestions("sleep");

        assertEquals("", suggestions1);
        assertEquals("", suggestions2);
        assertEquals("", suggestions3);
        assertEquals("", suggestions4);
    }
@Test
    public void testSuggestCorrections() throws IOException{
        Path dictionaryPath = Paths.get("src/main/resources/words.txt");
        suggestionengine.loadDictionaryData(dictionaryPath);

        String suggestions1 = suggestionengine.generateSuggestions("aple");
        System.out.println("Suggestions for 'aple':\n" + suggestions1);

        String suggestions2 = suggestionengine.generateSuggestions("bannana");
        System.out.println("Suggestions for 'bannana':\n" + suggestions2);

        String suggestions3 = suggestionengine.generateSuggestions("ambulence");
        System.out.println("Suggestions for 'ambulence':\n" + suggestions3);

        assertEquals("apple\nample\nalle\nale\nape\nmaple\nsaple\nable\nacle\nagle", suggestions1);
        assertEquals("banana\nbandana\nbanna\nbandanna\nanana\nbanaba\nbananas\nbannat\nmanana\ntanana", suggestions2);
        assertEquals("ambulance\nambience\nambulanced\nambulancer\nambulances\nambulante", suggestions3);
        
    }
}
