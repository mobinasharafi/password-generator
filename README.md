This lab project is a command-line password generator written in Java and built using Maven.

The original task was to create a structured multi-package application and improve it using Maven, CLI parsing and unit testing. I followed the required structure and also then extended the generator with additional validation and strength evaluation.

The program generates secure random passwords and allows the user to configure rules from the command line.

Features include:

Secure password generation using SecureRandom

Custom length (-l)

Generate multiple passwords (-n)

Include or exclude:

Uppercase letters

Lowercase letters

Digits

Symbols

Enforce required character classes (e.g. must include a digit)

Strength estimation using entropy (--strength)

Unit tests using JUnit 5

How to Build and Run

Build:

mvn clean package

Run:

java -jar target/password-generator-1.0-SNAPSHOT.jar

Example:

java -jar target/password-generator-1.0-SNAPSHOT.jar -l 16 --require-digit --strength

Run tests:

mvn test

Design Decisions
SecureRandom

I used SecureRandom instead of Random because password generation requires stronger unpredictability. Random is deterministic and not suitable for security-sensitive generation.

Required Character Constraints

If the user requires certain character types, the generator inserts at least one character from each required group and then fills the rest randomly. It also checks for impossible configurations (for example, length smaller than required groups).

Fisher–Yates Shuffle

When required characters are inserted first, they would appear in predictable positions. To avoid this structural bias, I applied the Fisher–Yates shuffle algorithm.

I selected this algorithm because it is a well-known method for producing an unbiased random permutation in O(n) time. It is commonly described in algorithm  and computer science literature as the standard way to shuffle arrays uniformly.


Strength Evaluation

Password strength is estimated using entropy:

entropy = length × log2(pool size)

This gives a mathematical estimate of unpredictability instead of a subjective label.

What I Learned

Correct Maven project structure (src/main vs src/test)

Managing dependencies and plugins (commons-cli, shade plugin)

Writing unit tests with JUnit 5

Defensive validation of user input

Thinking about security and not just simple randomness

This project satisfies the lab requirements and extends them with additional validation, better CLI usability, and strength estimation while keeping the code modular and testable.