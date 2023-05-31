import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

class HorseTest {
    private final String testName = "testName";
    private final Double testSpeed = 1.0;
    private final Double testDistance = 2.0;
    private final Horse horseWithTwoParameters = new Horse(testName, testSpeed);
    private final Horse horseWithThreeParameters = new Horse(testName, testSpeed, testDistance);
    @Test
    void constructorIfFirstParameterNullShouldThrowException() {
        System.out.println("Test: constructorIfFirstParameterNullShouldThrowException");
        assertThrows(IllegalArgumentException.class, ()-> new Horse(null, testSpeed));
    }
    @Test
    void constructorIfFirstParameterNullShouldThrowExceptionAndMessage() {
        String expected = "Name cannot be null.";
        String result = assertThrows(IllegalArgumentException.class, ()-> new Horse(null, testSpeed)).
                getMessage();
        assertEquals(expected, result);
        System.out.printf("Test: constructorIfFirstParameterNullShouldThrowExceptionAndMessage %s.\n",
                result.equals(expected) ? "passed": "failed");
    }
    @ParameterizedTest
    @ValueSource(strings = {""," ","\t","\n"})
    void constructorIfFirstParameterEmptyOrSpaceShouldThrowException(String testString) {
        System.out.print("Test: constructorIfFirstParameterEmptyOrSpaceShouldThrowException ");

        Object result = assertThrows(IllegalArgumentException.class, ()-> new Horse(testString,testSpeed, testDistance)).
                        getClass();
        System.out.println(result.equals(IllegalArgumentException.class) ? "passed" : "failed");
    }

    @ParameterizedTest
    @ValueSource(strings = {""," ","\t","\n"})
    void constructorIfFirstParameterEmptyOrSpaceShouldThrowExceptionAndGetMessage(String testString) {
        System.out.print("Test: constructorIfFirstParameterEmptyOrSpaceShouldThrowExceptionAndGetMessage ");
        String expect = "Name cannot be blank.";
        String result =
                assertThrows(IllegalArgumentException.class, ()-> new Horse(testString,testSpeed, testDistance)).
                        getMessage();
        System.out.println(expect.equals(result) ? "passed" : "failed");
    }

    @Test
    void constructorIfSecondParameterIsNegativeShouldThrowException() {
        System.out.print("Test: constructorIfSecondParameterIsNegativeShouldThrowException ");
        Object result =
                assertThrows(IllegalArgumentException.class, ()-> new Horse(testName, -1.0, testDistance)).
                        getClass();
        System.out.println(result.equals(IllegalArgumentException.class) ? "passed" : "failed");
    }

    @Test
    void constructorIfSecondParameterIsNegativeShouldThrowExceptionAndGetMessage() {
        System.out.print("Test: constructorIfSecondParameterIsNegativeShouldThrowExceptionAndGetMessage ");
        String result =
                assertThrows(IllegalArgumentException.class, ()-> new Horse(testName, -1.0, testDistance)).
                        getMessage();
        String expect = "Speed cannot be negative.";
        System.out.println(expect.equals(result) ? "passed" : "failed");
    }

    @Test
    void constructorIfThirdParameterIsNegativeShouldThrowException() {
        System.out.print("Test: constructorIfThirdParameterIsNegativeShouldThrowException ");
        Object result =
                assertThrows(IllegalArgumentException.class, ()-> new Horse(testName, testSpeed, -1.0)).
                        getClass();
        System.out.println(result.equals(IllegalArgumentException.class) ? "passed" : "failed");
    }

    @Test
    void constructorIfThirdParameterIsNegativeShouldThrowExceptionAndGetMessage() {
        System.out.print("Test: constructorIfThirdParameterIsNegativeShouldThrowExceptionAndGetMessage ");
        String result =
                assertThrows(IllegalArgumentException.class, ()-> new Horse(testName, testSpeed, -1.0)).
                        getMessage();
        String expect = "Distance cannot be negative.";
        System.out.println(expect.equals(result) ? "passed" : "failed");
    }

    @Test
    void getName() { //testingStringOfTheFirstParameterFromConstruction
        String result = horseWithTwoParameters.getName();
        assertEquals(testName, result);
        System.out.printf("Test: getName %s.\n", result.equals(testName) ? "passed": "failed");
    }

    @Test
    void getSpeedReturnedNumberOfTheSecondParameterFromConstruction() {
        Double result = horseWithTwoParameters.getSpeed();
        assertEquals(testSpeed, result);
        System.out.printf("Test: getSpeedReturnedNumberOfTheSecondParameterFromConstruction %s.\n",
                result.equals(testSpeed) ? "passed": "failed");
    }

    @Test //Проверить, что метод возвращает ноль, если объект был создан с помощью конструктора с двумя параметрами;
    void getDistanceReturnedZeroViaConstructorWithTwoParameter() {
        Double result = horseWithTwoParameters.getDistance();
        assertEquals(0, result);
        System.out.printf("Test: getDistanceReturnedZeroViaConstructorWithTwoParameter %s\n",
                result.equals(0.0) ? "passed": "failed");
    }

    @Test //Проверить, что метод возвращает число, которое было передано третьим параметром в конструктор;
    void getDistanceReturnedNumberViaConstructorWithThreeParameter() {
        assertEquals(testDistance, horseWithThreeParameters.getDistance());
        System.out.printf("Test: getDistanceReturnedNumberViaConstructorWithThreeParameter %s\n",
                testDistance.equals(horseWithThreeParameters.getDistance()) ? "passed": "failed");
    }

    @Test
    void moveCheckCallInternalMethodGetRandomDoubleWithTwoParameters() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseWithTwoParameters.move();
            horseMockedStatic.verify(() ->
                    Horse.getRandomDouble(Mockito.anyDouble(), Mockito.anyDouble()), times(1));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.3, 0.4, 0.5})
    void moveCheckWhatMethodAssignValueToDistance(double result) {
        Horse horse = new Horse("testHorse1", testSpeed, testDistance);

        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(result);
            double expected = testDistance + testSpeed * result;
            horse.move();
            assertEquals(expected, horse.getDistance());
            System.out.printf("Test: moveCheckWhatMethodAssignValueToDistance %s\n",
                    expected == horse.getDistance() ? "passed" : "failed");
        }
    }
}