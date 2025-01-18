Feature: Compare BAI2 files ignoring dates

  @NotepadVsNotePad
  Scenario: Compare two BAI2 files
    Given I have two BAI2 files "C:/Users/Admin/Desktop/File_Compare/Actual.txt" and "C:/Users/Admin/Desktop/File_Compare/Expected.txt"
    When I compare the files ignoring the dates
    Then the files should be identical
