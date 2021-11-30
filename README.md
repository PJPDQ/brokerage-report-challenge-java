# brokerage-report-challenge-java

Brokerage Report
To create a program that will process a transaction report (see Input section) and produce a formatted summary report (see Output section).

# Assumptions
* Timestamps are ISO format in UTC
* Numbers do not contain commas
* Adviser codes will not have commas

# Constraints

* You can use and version of Java or Kotlin
* You do not need to use any particular build tools
* You do not need to use any third party libraries

# Input
The input files will be in a CSV format with the following schema:
1. txnid: number
2. timestamp: UTC Timestamp
3. account: String
4. adviser: String
5. value: decimal

The file will always have a header

# Output

Output a report to the screen that contains three lines per adviser

* The first row is the adviser code
* The second row is the total value of the transactions for that adviser
* The total transaction value should be formatted with two decimal places, with a thousand separator.
* The total transaction value column should be padded to 10 characters
* The third row shows the date/time of the oldest and newest transaction for the adviser
* The date should be shown in Sydney local time
* The date should be 2 digit day, 3 character month and 4 character year

# Example
ABC

  5,712.00

11 Oct 2020 -> 15 Oct 2020
**Note: correction from 12 Oct 2020 as txnid => 2
 

CBA

    653.00

16 Oct 2020 -> 16 Oct 2020
