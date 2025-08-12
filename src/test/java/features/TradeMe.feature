    @TradeMe
    Feature: TradeMe UI API Test

      Rule: This is a good place to put wich business rule we are testing whit this feature

      Scenario: "As a user, I can see all the car makes an the makes dropdown"
          Given I navigate to the TradeMe Motor page
          Then I can verify that number of car makes is 89

      Scenario Outline: "As a user, I can validate that each make has the right amount of cars listed"
          Given I navigate to the TradeMe Motor page
          When I select the car make <Make>
          Then I can see it has <Amount> records in the results

              Examples:
                  | Make    | Amount  |
                  | Ferrari | 56      |
                  | BMW     | 2,739   |
                  | Mazda   | 5,524   |
                  | Honda   | 2,594   |

      Scenario: "As a user, I want to verify the amount of car makes throught the API"
          Given I send the request to the endpoint
          Then I can see there are 88 car makes
