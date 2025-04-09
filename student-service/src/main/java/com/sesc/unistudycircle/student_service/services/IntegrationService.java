package com.sesc.unistudycircle.student_service.services;

import com.sesc.unistudycircle.student_service.entities.Account;
import com.sesc.unistudycircle.student_service.entities.Invoice;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class IntegrationService {


    private final RestClient restClient;

    public IntegrationService( RestClient restClient) {
        this.restClient = restClient;
    }

    //used to get check if a student finance account exists inside  finance app
    public Account getStudentAccount(String studentId) {
        try {
            return restClient.get()
                    .uri("http://financeapp:8081/accounts/student/" + studentId)
                    .retrieve()
                    .body(Account.class);
        } catch (Exception e) {
            System.out.println("Failed to fetch student account for ID: " + studentId + " - " + e.getMessage());
            return null;
        }
    }

    //create an invfoice from finance app, and return it
    public Invoice createCourseFeeInvoice(Invoice invoice){
        try{
            return restClient.post()
                    .uri("http://financeapp:8081/invoices/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(invoice)
                    .retrieve()
                    .body(Invoice.class);
        }
        //if it fails
        catch (Exception e){
            System.out.println("Failed to create course fee invoice + " + e.getMessage());
            return null;
        }
    }

    //create a finance account
    public void createFinancialAccount(Account account){
        try{
            restClient.post()
                    .uri("http://financeapp:8081/accounts/")
                    .body(account)
                    .retrieve()
                    .body(Account.class);
        }
        catch (Exception e){
            System.out.println("FAILED TO CREATE ACCOUNT:  "+ e);
        }
    }

    //worth trying out /api/register here to test the proxy bypasssss
    public void createLibraryAccount(Account account){
        restClient.post()
                .uri("http://libraryapp:80/api/register")
                .body(account)
                .retrieve()
                .toBodilessEntity();
    }
}
