# TransactionInSpringBoot

✈️ Imagine This Scenario
You're booking a flight. Two things happen:

The system saves your passenger details (name, email, etc.).

It saves your payment (amount, payment method, etc.).

Now, what if the payment fails? You wouldn’t want your name to be saved without a ticket, right?

🛡️ That’s Where @Transactional Comes In It’s like a protective wrapper around both actions.

If both passenger and payment are saved successfully → ✅ Everything is committed to the database.

If one fails (like payment is rejected) → ❌ Everything is canceled or rolled back. Nothing is saved.

It keeps your data clean and consistent—no half-booked tickets!
