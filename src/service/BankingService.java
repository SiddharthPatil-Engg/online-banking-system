package service;

import dao.TransactionDAO;
import dao.UserDAO;
import model.User;

public class BankingService {

    private UserDAO userDAO = new UserDAO();
    private TransactionDAO transactionDAO = new TransactionDAO();

    public User login(String username, String password) throws Exception {
        return userDAO.login(username, password);
    }

    public void deposit(User user, double amount) throws Exception {
        user.setBalance(user.getBalance() + amount);
        userDAO.updateBalance(user.getId(), user.getBalance());
        transactionDAO.addTransaction(user.getId(), "DEPOSIT", amount);
    }

    public void withdraw(User user, double amount) throws Exception {
        if (user.getBalance() >= amount) {
            user.setBalance(user.getBalance() - amount);
            userDAO.updateBalance(user.getId(), user.getBalance());
            transactionDAO.addTransaction(user.getId(), "WITHDRAW", amount);
        } else {
            System.out.println("Insufficient Balance!");
        }
    }

    public void showTransactions(User user) throws Exception {
        transactionDAO.getTransactions(user.getId());
    }
}
