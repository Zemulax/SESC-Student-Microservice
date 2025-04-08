package com.sesc.unistudycircle.student_service.services;

import com.sesc.unistudycircle.student_service.entities.Account;
import com.sesc.unistudycircle.student_service.entities.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class IntegrationService {


    private final RestClient restClient;

    public IntegrationService( RestClient restClient) {
        this.restClient = restClient;
    }

    public Account getStudentAccount(String studentId) {
        return restClient.get()
                .uri("http://financeapp:8081/accounts/student/"+ studentId)
                .retrieve()
                .body(Account.class);
    }


    public ResponseEntity<Void> createCourseFeeInvoice(Invoice invoice){
        return restClient.post()
                .uri("http://financeapp:8081/invoices/")
                .contentType(MediaType.APPLICATION_JSON)
                .body(invoice)
                .retrieve()
                .toBodilessEntity();

        //Invoice studentInvoice = restClient.post().uri("http://localhost:8081/invoices/")
                //.retrieve()
                //.body(Invoice.class);
        //return studentInvoice;
    }

    public ResponseEntity<Void> createFinancialAccount(Account account){
        try{
            return restClient.post()
                    .uri("http://financeapp:8081/accounts/")
                    .body(account)
                    .retrieve()
                    .toBodilessEntity();
        }
        catch (Exception e){
            System.out.println("FAILED TO CREATE ACCOUNT:  "+ e);
        }
        return  ResponseEntity.noContent().build();

    }

    public ResponseEntity<Void> createLibraryAccount(Account account){
        return restClient.post()
                .uri("http://libraryapp:80/api/register")
                .body(account)
                .retrieve()
                .toBodilessEntity();
    }

//    public Account createFinancialAccount(Account account){
//        Account createStudentFinanceAccount = restClient.post()
//                .uri()
//                .
//                .body(Account.class);
//        return createStudentFinanceAccount;
//
//    }
//
//    public Account createLibraryAccount(Account account){
//
//        //return restTemplate.postForObject("http://localhost:80/api/register", account, Account.class);
//    }



}
