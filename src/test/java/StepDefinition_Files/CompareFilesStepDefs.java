package StepDefinition_Files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CompareFilesStepDefs {

    private List<String> actualLines;
    private List<String> expectedLines;

    @Given("I have two BAI2 files {string} and {string}")
    public void iHaveTwoBAI2Files(String actualFilePath, String expectedFilePath) throws IOException {
        actualLines = Files.readAllLines(Paths.get(actualFilePath));
        expectedLines = Files.readAllLines(Paths.get(expectedFilePath));
    }

    @When("I compare the files ignoring the dates")
//    public void iCompareTheFilesIgnoringTheDates() {
//        // Filter out lines that start with "02,"
//        actualLines = actualLines.stream()
//                                 .filter(line -> !line.startsWith("02,"))
//                                 .map(this::removeDates)
//                                 .collect(Collectors.toList());
//        expectedLines = expectedLines.stream()
//                                     .filter(line -> !line.startsWith("02,"))
//                                     .map(this::removeDates)
//                                     .collect(Collectors.toList());
//    }

    private String removeDates(String line) {
        // Example regex to remove dates, adjust according to your file's date format
        return line.replaceAll("\\b\\d{8}\\b", "");
    }

    @Then("the files should be identical")
    public void theFilesShouldBeIdentical() {
        if (actualLines.size() != expectedLines.size()) {
            fail("Files have different number of lines after filtering out lines starting with '02,'.");
        }

        for (int i = 0; i < actualLines.size(); i++) {
            String actualLine = actualLines.get(i);
            String expectedLine = expectedLines.get(i);
            if (!actualLine.equals(expectedLine)) {
                System.out.println("Difference found at line " + (i + 1) + ":");
                System.out.println("Actual  : " + actualLine);
                System.out.println("Expected: " + expectedLine);
                fail("Files differ at line " + (i + 1));
            }
        }
    }
}