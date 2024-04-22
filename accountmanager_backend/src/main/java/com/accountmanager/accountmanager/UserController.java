package com.accountmanager.accountmanager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    // list all users
    @GetMapping("/accounts")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // create user rest api
    @PostMapping("/accounts")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // get user by id rest api
    @GetMapping("/accounts/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("No user mathed the entered ID: " + id));
        return ResponseEntity.ok(user);
    }

    // // update user rest api: intended for admin privileges
    // @PutMapping("/accounts/{id}")
    // public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails){

    //     User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("No user matched the entered ID: " + id));

    //     user.setFirstName(userDetails.getFirstName());
    //     user.setLastName(userDetails.getLastName());
    //     user.setBalance(userDetails.getBalance());

    //     User userUpdated = userRepository.save(user);

    //     return ResponseEntity.ok(userUpdated);
    // }

    // // delete user rest api: intended for admin privileges
    // @DeleteMapping("/accounts/{id}")
    // public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id){
    //     User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("No user matched the entered ID: " + id));
    //     userRepository.delete(user);
    //     Map<String, Boolean> response = new HashMap<>();
    //     response.put("deleted", Boolean.TRUE);
    //     return ResponseEntity.ok(response);
    // }

    // get all transactions
    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // create transaction rest api
    @PostMapping("/transactions")
    public Transaction createTransaction(@RequestBody Transaction transaction) {

        long fromAccountIdLongFormat = (long) transaction.getFromAccountId(); 
        long toAccountIdLongFormat = (long) transaction.getToAccountId(); 

        User fromUser = userRepository.findById(fromAccountIdLongFormat).orElseThrow(() -> new NotFoundException("Ingen konto matcher fra konto: " + fromAccountIdLongFormat));
        User toUser = userRepository.findById(toAccountIdLongFormat).orElseThrow(() -> new NotFoundException("Ingen konto matcher til konto: " + toAccountIdLongFormat));

        while(true) {
            if (fromUser.getBalance() >= transaction.getTransferAmount()) {
                fromUser.setBalance(fromUser.getBalance()-transaction.getTransferAmount());
                toUser.setBalance(toUser.getBalance()+transaction.getTransferAmount());
        
                User fromUserUpdated = userRepository.save(fromUser);
                User toUserUpdated = userRepository.save(toUser);
        
                ResponseEntity.ok(fromUserUpdated);
                ResponseEntity.ok(toUserUpdated);
        
                transaction.setFromName(fromUser.getFirstName()+' '+fromUser.getLastName());
                transaction.setToName(toUser.getFirstName()+' '+toUser.getLastName());
                transaction.setNewBalance(fromUser.getBalance());
        
                return transactionRepository.save(transaction);
            } else {
                new NotFoundException("Du kan kun overføre mindre end indeværende beløb: "+fromUser.getBalance());
                return transaction;
            }
        }
    }

}
