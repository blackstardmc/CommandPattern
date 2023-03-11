package com.company;

import java.util.ArrayList;

interface Command {
    void execute();

    void undo();
}

public class BankAccount {
    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        balance += amount;
    }
}

class BankAccountCommand implements Command {
    private BankAccount bankAccount;
    private Action action;
    private int amount;

    BankAccountCommand(BankAccount bankAccount, Action action, int amount) {
        this.bankAccount = bankAccount;
        this.action = action;
        this.amount = amount;
    }

    @Override
    public void execute() {
        switch (action) {
            case DEPOSIT:
                bankAccount.deposit(amount);
                break;
            case WITHDRAW:
                bankAccount.withdraw(amount);
                break;
        }
    }

    @Override
    public void undo() {
        switch (action) {
            case WITHDRAW:
                bankAccount.deposit(amount);
                break;
            case DEPOSIT:
                bankAccount.withdraw(amount);
                break;
        }
    }

    public enum Action {
        WITHDRAW, DEPOSIT
    }
}

class CommandBankAccountHistory {
    ArrayList<Command> history = new ArrayList<>();

    public void push(Command c) {
        c.execute();
        history.add(c);
    }

    public Command pop() {
        if (history.isEmpty()) {
            throw new IllegalStateException("The command history is empty");
        }
        Command command = history.get(history.size() - 1);
        history.remove(history.size() - 1);
        return command;
    }
}