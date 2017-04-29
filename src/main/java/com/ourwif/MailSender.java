package com.ourwif;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;

import com.ourwif.model.Email;

public class MailSender{

    private MailSender mailSender;
    private SimpleMailMessage templateMessage;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }

    public void makeEmail(Email email) {

        // Do the business calculations...

        // Call the collaborators to persist the order...

        // Create a thread safe "copy" of the template message and customize it
        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
       // msg.setTo(order.getCustomer().getEmailAddress());
       // msg.setText(
       //     "Dear " + order.getCustomer().getFirstName()
       //         + order.getCustomer().getLastName()
       //         + ", thank you for placing order. Your order number is "
       //         + order.getOrderNumber());
       //  try{
       //     this.mailSender.send(msg);
       //  }
       //  catch(MailException ex) {
            // simply log it and go on...
       //     System.err.println(ex.getMessage());            
       // }
    }
}