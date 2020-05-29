package com.jofem.quizmarker.Service;

import com.jofem.quizmarker.Repository.LicenseRepository;
import com.jofem.quizmarker.model.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class LicenseService {

    @Autowired
    LicenseRepository licenseRepository;
    //License subscriber = new License();

    @Transactional
    public License save(License license){
//        License subscriber1 = new License(license.getSubscriberName(),license.getAddress(), license.getEmail(), license.getPhone());
        licenseRepository.save(license);
        return license;
    }

    @Transactional
    public License getSubscriber(Long id){
        return licenseRepository.findById(id).get();
    }

    @Transactional
    public List<License> getAllSubscribers(){
        List<License> licenses = new ArrayList<>();
        licenseRepository.findAll()
                .forEach(licenses::add);
        return licenses;
    }

    @Transactional
    public void delete(Long id){
        licenseRepository.deleteById(id);
        return;
    }

    @Transactional
    public License update(License license, Long id){
        License theLicense = licenseRepository.findById(id).get();
        if(theLicense != null){
            theLicense.setUser(license.getUser());
            theLicense.setAddress(license.getAddress());
            theLicense.setEmail(license.getEmail());
            theLicense.setPhone(license.getPhone());
            theLicense.setSubscriberName(license.getSubscriberName());
            licenseRepository.save(theLicense);
        }
        return license;
    }

    @Transactional
    public License getSubscriberByUserId(Long id){
        return licenseRepository.findById(id).get();
    }

    public List<License> getPendingLicenses() {
        return this.licenseRepository.findByStatus("pending");
    }

    public License findById(Long licenseId) {
        return this.licenseRepository.findById(licenseId).get();
    }

    public License findByUserId(Long userId) {
      return this.licenseRepository.findByUserId(userId);
    }
}
