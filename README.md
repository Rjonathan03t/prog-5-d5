 # Coffee Machine

## Description

This project simulates an automatic coffee machine. The user inserts money, chooses a drink, and receives their coffee if all conditions are met.

---

## Use Cases

- **Pay**: The user inserts an amount of money.
- **Choose a coffee**: The user selects a drink.
- **Get coffee**: The machine serves the coffee if the payment is sufficient and the drink is available.
- **Cancel**: The user can cancel the operation and get their money back.
- **Give change**: The machine returns change if the payment exceeds the price.

---

## Business Model

### Entities

- **Coffee**
  - `name: String`
  - `price: double`
  - `stock: int`

- **CoffeeMachine**
  - `coffees: Map<String, Coffee>`
  - `balance: double`

### Main Methods

- `void pay(double amount)`
- `void choose(String coffeeName)`
- `Coffee serve()`
- `double cancel()`
- `double giveChange()`

---

## Handled Errors

- Insufficient payment
- Out of stock
- Invalid selection
- No coffee selected

---

## Possible Optimizations

- Multi-currency support
- Customizable drinks
- Loyalty program
- Automatic restocking
- Touch or web interface

---

## Tests to Include

- Exact or surplus payment
- Insufficient payment
- Out of stock
- Invalid choice
- Operation cancellation

---

## Project Structure

```
coffee-machine/
├── src/
│   ├── Coffee.java
│   ├── CoffeeMachine.java
│   └── Application.java
├── test/
│   └── CoffeeMachineTest.java
├── README.md
└── LICENSE
```