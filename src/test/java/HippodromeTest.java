import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class HippodromeTest {

    @Test
    void constructorThrowExceptionWhenTransferredNull() {
        System.out.print("Test: constructorThrowExceptionWhenTransferredNull\n");
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void constructorThrowExceptionAndGetMessageWhenTransferredNull() {
        System.out.print("Test: constructorThrowExceptionAndGetMessageWhenTransferredNull\n");
        assertEquals("Horses cannot be null.",
                assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null)).getMessage());
    }

    @Test
    void constructorThrowExceptionWhenTransferredEmpty() {
        System.out.print("Test: constructorThrowExceptionWhenTransferredEmpty\n");
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    void constructorThrowExceptionAndGetMessageWhenTransferredEmpty() {
        System.out.print("Test: constructorThrowExceptionAndGetMessageWhenTransferredEmpty\n");
        assertEquals("Horses cannot be empty.",
            assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>())).getMessage());
    }

    @Test
    void getHorsesCheckReturnedListHorses() {
        System.out.println("Test: getHorsesCheckReturnedListHorses");
        List<Horse> listHorses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            listHorses.add(new Horse("Name" + String.valueOf(i), i, 0));
        }

        Hippodrome hippodrome = new Hippodrome(listHorses);
//        List<Horse> list = new ArrayList<>();
//        list.add(new Horse("1", 2, 3));

        assertEquals(listHorses, hippodrome.getHorses());
    }

    @Test
    void moveCheckCallOnAllHorses() {
        System.out.println("Test: moveCheckCallOnAllHorses");

        List<Horse> listHorses = new ArrayList<>();
        Horse horseMock = null;
        for (int i = 0; i < 50; i++) {
            horseMock = Mockito.mock(Horse.class);
            listHorses.add(horseMock);
        }
        Hippodrome hippodrome = new Hippodrome(listHorses);
        hippodrome.move();

        for(Horse horse : listHorses) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    void getWinnerCheckWhatThisMethodReturnHorseWithMaxValueOfDistance() {
        System.out.println("Test: getWinnerCheckWhatThisMethodReturnHorseWithMaxValueOfDistance");
        Horse horse1 = new Horse("Name1", 1.0, 1.0);
        Horse horse2 = new Horse("Name2", 1.0, 2.0);
        Horse horse3 = new Horse("Name3", 1.0, 3.0);

        List<Horse> listHorses = new ArrayList<>(Arrays.asList(horse1, horse2, horse3));
        Hippodrome hippodrome = new Hippodrome(listHorses);
        assertEquals(horse3, hippodrome.getWinner());
    }
}