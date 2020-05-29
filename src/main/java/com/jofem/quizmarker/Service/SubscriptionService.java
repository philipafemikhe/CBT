package com.jofem.quizmarker.Service;

import com.jofem.quizmarker.Repository.SubscriptionRepository;
import com.jofem.quizmarker.model.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionService {
    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Transactional
    public Subscription save(Subscription subscription){
       return subscriptionRepository.save(subscription);
    }

    @Transactional
    public Subscription getSubscription(Long id){
        return subscriptionRepository.findById(id).get();
    }

    @Transactional
    public List<Subscription> getSubscription(){
        List<Subscription> subscriptionList = new ArrayList<>();
        subscriptionRepository.findAll()
                .forEach(subscriptionList::add);
        return subscriptionList;
    }

    @Transactional
    public void delete(Long id){
        subscriptionRepository.deleteById(id);
        return;
    }


    @Transactional
    public Subscription update(Subscription packages, Long id){
        Subscription theSubscription =  subscriptionRepository.findById(id).get();
        if(theSubscription != null){
            theSubscription.setDescription(packages.getDescription());
            theSubscription.setPackageName(packages.getPackageName());
            subscriptionRepository.save(theSubscription);
        }
        return packages;
    }


}
