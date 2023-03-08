import com.vominh.algorithm.RomanNumberUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.IllegalFormatException;

public class RomanNumberTest {

    @Test
    void characterToValueTest() {
        RomanNumberUtils utils = new RomanNumberUtils();
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> {
            utils.characterToValue("K");
        });
        Assertions.assertEquals(5, utils.characterToValue("V"));
        Assertions.assertEquals(10, utils.characterToValue("X"));

        Assertions.assertEquals(28, utils.characterToValue("XXVIII"));
        Assertions.assertEquals(9, utils.characterToValue("IX"));
        Assertions.assertEquals(11, utils.characterToValue("XI"));
        Assertions.assertEquals(14, utils.characterToValue("XIV"));


        Assertions.assertEquals(3, utils.characterToValue("III"));
        Assertions.assertEquals(58, utils.characterToValue("LVIII"));
        Assertions.assertEquals(1994, utils.characterToValue("MCMXCIV"));
    }

    @Test
    void valueToCharacterTest() {
        RomanNumberUtils utils = new RomanNumberUtils();
        Assertions.assertEquals("CXVIII", utils.valueToCharacter(118));
        Assertions.assertEquals("CXIX", utils.valueToCharacter(119));
        Assertions.assertEquals("DCCCXCIX", utils.valueToCharacter(899));
//        Assertions.assertEquals("V", utils.valueToCharacter(5));
//        Assertions.assertEquals("III", utils.valueToCharacter(3));
//        Assertions.assertEquals("LVIII", utils.valueToCharacter(58));
//        Assertions.assertEquals("MCMXCIV", utils.valueToCharacter(1994));
    }
}
