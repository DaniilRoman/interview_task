package tinkoff.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tinkoff.entity.Application;
import tinkoff.exception.ApplicationNotFoundException;
import tinkoff.repository.ApplicationRepository;
import tinkoff.service.interfaces.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    ApplicationRepository applicationRepository;

    @Override
    public Application findLatestByContactId(Integer contactId) {
        return applicationRepository.findFirstByContacts_ContactIdOrderByCreatedDesc(contactId)
                .orElseThrow(()-> new ApplicationNotFoundException(contactId));
    }
}
