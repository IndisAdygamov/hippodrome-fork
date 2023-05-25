import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    private String testName = "testName";
    private Double testSpeed = 1.0;
    private Double testDistance = 2.0;
    private Horse horseWithTwoParameters = new Horse(testName, testSpeed);
    private Horse horseWithThreeParameters = new Horse(testName, testSpeed, testDistance);
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
    @ValueSource(chars = {' ', '\t'})
    void constructorIfFirstParameterEmptyOrSpaceShouldThrowException() {
        String result;

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
    void move() {


    }
}