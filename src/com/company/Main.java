package com.company;

public class Main {

    public static void main(String[] args) {
        //A example of the Pattern Command
        BankAccount account = new BankAccount(300);
        CommandBankAccountHistory history = new CommandBankAccountHistory();

        history.push(new BankAccountCommand(account, BankAccountCommand.Action.DEPOSIT, 200));
        history.push(new BankAccountCommand(account, BankAccountCommand.Action.WITHDRAW, 50));

        System.out.println(account.getBalance());

        history.pop().undo();

        System.out.println(account.getBalance());

        history.pop().execute();

        System.out.println(account.getBalance());
    }
}
