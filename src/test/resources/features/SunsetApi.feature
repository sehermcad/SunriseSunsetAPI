Feature:


  Scenario Outline:

    Given  I set GET request the Latitude as "<lat>" and Longtitude as "<long>"
    When Verify valid HTTP response code 200
    Then Verify that we received the "<Expected Sunrise>" and "<Expected Sunset>" times without date.

    Examples:
      | lat        | long       | Expected Sunrise | Expected Sunset |
      | 36.7201600 | -4.4203400 | 6:42:19 AM       | 5:20:10 PM      |



  Scenario Outline:

    Given  I set GET request the Latitude as "<lat>" and Longtitude as "<long>"
    When Verify valid HTTP response code 200
    And Verify that we received the "<Expected Sunrise>" and "<Expected Sunset>" times with given "<date>"
    And Verify that we received the "<Expected Sunrise>" and "<Expected Sunset>" times with given "<date>"

    Examples:
      | lat        | long       | date       | Expected Sunrise | Expected Sunset |
      | 36.7201600 | -4.4203400 | today      | 6:42:19 AM       | 5:20:10 PM      |
      | 36.7201600 | -4.4203400 | 2020-10-28 | 6:38:20 AM       | 5:24:27 PM      |


  Scenario Outline:

    Given  I set GET request the Latitude as "<lat>" and Longtitude as "<long>"
    When Verify valid HTTP response code 200
    And Request an "<formatted>" response returns unformatted "<Expected Sunrise>" when "<Expected Sunset>"

    Examples:
      | lat        | long       | formatted       | Expected Sunrise                | Expected Sunset |
      | 36.7201600 | -4.4203400 | 0               | 2020-11-01T06:42:19+00:00       | 2020-11-01T17:20:10+00:00 |


  Scenario Outline:

    Given  I set GET request the Latitude as "<lat>" and Longtitude as "<long>"
    When Verify valid HTTP response code 200
    And Verify passing an invalid "<date>" returns with a status of "<status>"

    Examples:
      | lat        | long       | date       | status       |
      | 36.7201600 | -4.4203400 | 0          | INVALID_DATE |


  Scenario Outline:

    Given  I set GET request the Latitude as "<lat>" and Longtitude as "<long>"
    When Verify valid HTTP response code 200
    And Verify the "<Time>" between Sunrise and Sunset of "<date>"

    Examples:
      | lat        | long       | date  | Time     |
      | 36.7201600 | -4.4203400 | today | 10:37:51 |
